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
<style type="text/css">
	.btn{
		margin-top:10px;
		margin-left:10px;
	}
</style>
<body>
	<form class="layui-form" id="form" action="/iamp/app/list.do" method="post">
		<span class="layui-btn  layui-btn-normal btn layui-btn-small" id="add">新建</span>
		<span class="layui-btn  layui-btn-warm btn layui-btn-small" id="update">修改</span>
		<span class="layui-btn layui-btn-danger btn layui-btn-small" id="del">删除</span>
		<span class="layui-btn  btn layui-btn-small" id="member">会员管理</span>
			<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;" id="query">查询</span></div>
			<div style="float:right;position:relative;" class="btn"> 
				<label class="layui-form-label">应用名称：</label>
			    <div class="layui-input-block">
			      <input type="text" name="appName"  style="width:150px" required  lay-verify="required" placeholder="请输入名称" autocomplete="off" class="layui-input" value='${appName }'>
			   	<input type="hidden" name="pageNo" value="${page.pageNum}">
			   	<input type="hidden" name="pageSize" value="${page.pageSize}">
			    </div>
	    	</div>
	    	<div style="float:right;position:relative;" class="btn"> 
				<label class="layui-form-label">应用代码：</label>
			    <div class="layui-input-block">
			      <input type="text" name="appCode"  style="width:150px" required  lay-verify="required" placeholder="请输入代码" autocomplete="off" class="layui-input" value='${appCode }'>
			    </div>
	    	</div>
	</form>
 	<table class="layui-table" lay-skin="line">
	  <colgroup>
	  	<col width="50">
	    <col width="200">
	    <col width="120">
	    <col width="150">
	    <col width="200">
	    <col width="120">
	    <col width="150">
	    <col>
	  </colgroup>
	  <thead>
	    <tr>
	     <th></th>
	     <th>应用名称</th><th>应用代码</th><th>过期时间</th><th>使用单位</th>
	     <th>创建人</th><th>创建时间</th><th>备注</th>
	    </tr> 
	  </thead>
	  <tbody>
	  <c:if test="${!empty page.list}">
		  <c:forEach var="app" items="${page.list}" varStatus="status">
		  	<tr>
		  		<td><input type="checkbox" id="${app.appId}" name="appId"></td>
		  		<td>${app.appName}</td> <td>${app.appCode}</td>
		  		<td>
		  		${app.expireDateStr}
		  		</td>
		  		<td>${app.appUnit}</td>
		 	 	<td>${app.creatorName}</td>
		 	 	<td>
		 	 	<fmt:formatDate value="${app.createDate}" pattern="yyyy-MM-dd HH:mm" type="date"/>
		 	 	</td><td>${app.remark}</td>
		  	</tr>
		  </c:forEach>
	  </c:if>
	  </tbody>
	</table> 
	<div id="page" style="position:absolute;right:50px;"></div>
</body>
<script type="text/javascript" src="/iamp/js/layui-v2/layui.js"></script>
<script>
var mainUrl = '/iamp/app/list.do';
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
			          $('form').submit();
			        }
			      }
			  });
	});
	
	$("#add").on("click",function(){
		var title="新建应用";
		var url = "/iamp/app/create.do";
		window.parent.detailWin(title,url);
		/* var layer = window.layer;
		layer.open({
			title:'新建应用',
			type: 2, 
			area: ['1000px', '580px'],
			content:"/iamp/app/create.do"
		});  */
	});
	
	$("#update").on("click",function(){
		var  checks = $('input:checkbox[name=appId]:checked');
		if(checks.length > 0) {
		var appId = checks.eq(0).attr('id');
		var title="修改应用";
		var url = "/iamp/app/update.do?appId="+appId;
		window.parent.detailWin(title,url);
		/* var layer = window.layer;
			layer.open({
				title:'修改应用',
				type: 2, 
				area: ['1000px', '580px'],
				content:"/iamp/app/update.do?appId="+appId
			});  */
	} else {
		var layer = window.layer;
		layer.open({
          type: 1
          ,title:"提醒"
          ,content: '<div style="padding: 20px 100px;">请选择数据!</div>'
          ,btn: '关闭'
          ,btnAlign: 'c' //按钮居中
          ,shade: 0 //不显示遮罩
          ,yes: function(){
        	  layer.closeAll();
          }
        });	
	}
	});
	
	//用于新建成功后刷新
	function refresh(){
		location.href = mainUrl;
	}
	
	$("#del").on("click",function(){
	var  checks = $('input:checkbox[name=appId]:checked');
	if(checks.length > 0) {
		var layer = window.layer;
		layer.confirm('确定删除该数据吗?', {
				  btn: ['确定', '取消'] //可以无限个按钮
				  ,btnAlign: 'c'
				  ,yes: function(index, layero){
				  			 var appId = checks.eq(0).attr('id');
							$.ajax({
						                type: "GET",
						                url:'/iamp/app/delete.do',
						                data:{appId:appId},// 你的formid
						                async: false,
						                error: function(request) {
						                    alert("Connection error");
						                },
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
													  window.location.href='/iamp/app/list.do';
										          }
										        });	
						                	} else {
						                		showMsg('提醒',data.msg);
												return;
						                	}
						                }
						            });
				  
				  }})
            
	} else {
		var layer = window.layer;
		layer.open({
          type: 1
          ,title:"提醒"
          ,content: '<div style="padding: 20px 100px;">请选择数据!</div>'
          ,btn: '关闭'
          ,btnAlign: 'c' //按钮居中
          ,shade: 0 //不显示遮罩
          ,yes: function(){
        	  layer.closeAll();
          }
        });	
	}
	});
	
	$("#member").on("click",function(){
		var title="会员管理";
		var checks = $('input:checkbox[name=appId]:checked');
		var layer = window.layer;
		if(checks.length > 0) {
			var appId = checks.eq(0).attr('id');
			var url = "/iamp/app/appMembers/"+appId;
			/* layer.open({
				title:title,
				maxmin: true,
				type: 2, 
				area: ['1000px', '580px'],
				content:url,
			  	success: function(layero, index){
				 }
			});  */
			var data = new Object();
		 	data.title ='会员管理';
			data.href =url;
			data.name="hygl"
			var data = JSON.stringify(data);
			window.parent.addTab(data);
			
		}else {
			layer.open({
	          type: 1
	          ,title:"提醒"
	          ,content: '<div style="padding: 20px 100px;">请选择数据!</div>'
	          ,btn: '关闭'
	          ,btnAlign: 'c' //按钮居中
	          ,shade: 0 //不显示遮罩
	          ,yes: function(){
	        	  layer.closeAll();
	          }
	        });	
		}
		
	});
	$('#query').on('click',function() {
		$('#form').submit();
	})
	
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