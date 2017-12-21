<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>应用管理</title>
</head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="/iamp/js/layui-v2/css/layui.css" media="all" />
<script src="/iamp/js/jquery-1.7.2.min.js"></script>
<script src="/iamp/js/jquery.form.js"></script>
<style type="text/css">
	.btn{
		margin-top:10px;
		margin-left:10px;
	}
	
	.layui-form-label {
	    float: left;
	    display: block;
	    padding: 0px 0px;
	    width: 80px;
	    font-weight: 400;
	    text-align: right;
	}
</style>
<body>
	<form class="layui-form" id="form" action="/iamp/app/memberList/${appId}" method="post">
		<input type="hidden" name="pageNo" value="${page.pageNum}">
		<input type="hidden" name="pageSize" value="${page.pageSize}">
		<span class="layui-btn  layui-btn-normal btn layui-btn-small" id="import">导入</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;" id="query">查询</span></div>
    	<div style="float:right;position:relative;" class="btn"> 
			<label class="layui-form-label">
				<select name="selectOpt" lay-verify="required">
			        <option value="memberAccount">账号</option>
			        <option value="memberName">姓名</option>
			      </select>
			</label>
		    <div class="layui-input-block">
		    	<input type="text" name="queryCondition"  style="width:200px" required  lay-verify="required" placeholder="请输入查询内容" autocomplete="off" class="layui-input" value="${queryCondition}">
		    </div>
    	</div>
	</form>
 	<table class="layui-table" lay-skin="line" id="memberTable">
	  <colgroup>
	  	<col width="50">
	    <col width="150">
	    <col width="150">
	    <col width="150">
	    <col>
	  </colgroup>
	  <thead>
	    <tr>
	     <th><input type="checkbox" id="checkAll" name="checkAll"></th>
	     <th>会员账号</th><th>会员姓名</th>
	     <th>创建时间</th><th>备注</th>
	    </tr> 
	  </thead>
	  <tbody>
	  	<c:forEach var="member" items="${pageList}" varStatus="status">
		  	<tr>
		  		<td><input type="checkbox" id="${member.memberId}" name="memberId" memberName="${member.memberName}"></td>
		  		<td name="memberAccount">${member.memberAccount}</td>
		  		<td name="memberName">${member.memberName}</td>
		 	 	<td name="createDate">${member.createDate}</td>
		 	 	<td name="remark">${member.remark}</td>
		  	</tr>
		  </c:forEach>
	  </tbody>
	</table> 
	<div id="page" style="position:absolute;right:50px;"></div>
	<form class="layui-form" id="exportForm" action="/iamp/member/exportMembers.do" method="post">
		<input type="hidden" name="selectedData" id="selectedData" value="">
	</form>
</body>
<script type="text/javascript" src="/iamp/js/layui-v2/layui.js"></script>
<script>
var mainUrl = '/iamp/member/list.do';
var layer;
	layui.use(['layer','laypage','form','element'], function(){
		var form = layui.form;
		var element = layui.element;
		  layer = layui.layer;
		  window.layer = layer;
		  var laypage = layui.laypage;
			laypage.render({
			    elem: 'page' //注意，这里的 test1 是 ID，不用加 # 号
			    ,count:'${page.total}'//数据总数 //从服务端得到
			    ,limit:10
			    ,groups: 5
			    ,curr : '${page.pageNum}'
			    ,pages: '${page.pages}'
			    ,jump: function(obj, first){
			        if(!first){
			          layer.msg('第 '+ obj.curr +' 页');
			          $('input[name="pageNo"]').val(obj.curr);
			          $('#form').submit();
			        }
			      }
			  });		  
	});
	
	$('#query').on('click',function() {
		$('#form').submit();
	})
	
	$(function(){
		var selectOpt = '${selectOpt}';
		$('option[value="'+selectOpt+'"]').attr("selected",true);
	})
	
	$('input[name="checkAll"]').change(function(){
		var selectList = $("#memberTable").find("input[name='memberId']");
		if(this.checked){
			selectList.each(function(){
				 $(this).prop("checked",'true');
			 });
		}else{
			selectList.each(function(){
				$(this).removeAttr("checked");
			 });
		}
	});

	$('#import').on('click',function() {
		var checks = $('input:checkbox[name=memberId]:checked');
		if(checks.length > 0) {
			var layer = window.layer;
			layer.prompt({title: '请输入所属分组！', formType: 3}, function(appGroup, index){
				  layer.close(index);
				  var memberNames = "";
			  	  var memberIds = "";
				  for(var n=0;n<checks.length;n++){
					  	memberNames+=checks.eq(n).attr('memberName');
					  	memberNames+=';';
						memberIds+=checks.eq(n).attr('id');
						memberIds+=';';
				  	}
				  importMember(appGroup,memberNames,memberIds);
				});
		}
	});
	var appId = '${appId}';
	function importMember(appGroup,memberNames,memberIds){
		$.ajax({
            type: "post",
            url:'/iamp/app/imported',
            data:{"appId":appId,"memberIds":memberIds,"appGroup":appGroup,"memberNames":memberNames},
            async: false,
            dataType:"json",
            success: function(data) {
            	if(data.result) {
            	var layer = window.layer;
            		layer.open({
			          type: 1
			          ,title:'提醒'
			          ,content: '<div style="padding: 20px 100px;">'+data.msg+'</div>'
			          ,btn: '关闭'
			          ,btnAlign: 'c' //按钮居中
			          ,shade: 0 //不显示遮罩
			          ,yes: function(){
			        	  layer.closeAll();
			        	  window.parent.refresh();
			          }
			        });	
            	} else {
            		showMsg('提醒',data.msg);
					return;
            	}
            }
        });
	}
	function showMsg(title, msg) {
		layer.open({
          type: 1
          ,title:title
          ,content: '<div style="padding: 20px 100px;">'+msg+'</div>'
          ,btn: '关闭'
          ,btnAlign: 'c' //按钮居中
          ,shade: 0 //不显示遮罩
          ,yes: function(){
        	  layer.closeAll();
          }
        });	
	}

</script>
</html>