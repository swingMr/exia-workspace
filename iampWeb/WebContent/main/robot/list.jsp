<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>机器人定义</title>
</head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="/iamp/js/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="/iamp/js/layui/css/global.css" media="all">
<script src="/iamp/js/jquery-1.7.2.min.js"></script>
<style type="text/css">
	.btn{
		margin-top:10px;
		margin-left:10px;
	}
	.title{
		
	}
</style>

<body>	
	<form class="layui-form" action="/iamp/robotDef/list.do" >
		<span class="layui-btn  layui-btn-normal btn layui-btn-small" id="add">新建</span>
		<span class="layui-btn  layui-btn-warm btn layui-btn-small" id="update">修改</span>
		<span class="layui-btn layui-btn-danger btn layui-btn-small" id="del">删除</span>
		<div style="float:right;position:relative;right:5px" class="btn"> 
		  <label class="layui-form-label">归属应用：</label>
		    <div class="layui-input-block" style="width:150px">
		      <select name="type" id="type" lay-filter="type">
		     	 <option value="all" >选择应用</option>
		     <c:forEach var="appList" items="${appList}" varStatus="status">
<%-- 		     		<c:choose>  					  
					   <c:when test="${!empty typeId && typeId==list.typeId}"> 
					   </c:when>
					   <c:otherwise>
					   </c:otherwise>
				    </c:choose> --%>
		     	 <option value="${appList.appCode}" ${!empty type && type == appList.appCode ?'selected':'' }>${appList.appName}</option>
		     </c:forEach>
		      </select>
		  </div>
    	</div>
	</form>
 	<table class="layui-table" lay-skin="line">
	  <colgroup>
	  	<col width="50">
	    <col width="120">
	    <col width="120">
	    <col width="120">
	    <col width="120">
	    <col>
	    <col width="150">
	  </colgroup>
	  <thead>
	    <tr>
	     <th></th><th >归属应用</th><th >机器人编号</th><th >机器人昵称</th>
	     <th>服务时间</th><th >欢迎词</th><th>创建时间</th>
	    </tr> 
	  </thead>
	  <tbody>
	  <c:forEach var="list" items="${list}" varStatus="status">
		  <tr>
		  	<td><input type="checkbox" name="id" value="${list.robotId}"></td> <td>${list.appName}</td><td>${list.robotCode}</td>
		  	<td>${list.robotName}</td>
		  	<td>${list.serviceTime}</td>
		  	<td>${list.serviceGreeting}</td><td><fmt:formatDate value="${list.createDate}"   pattern="yyyy-MM-dd HH:mm" type="date" dateStyle="long" /></td>
		  </tr>
	  </c:forEach>
	  <input type="hidden" name="ids">
	  </tbody>
	</table> 
	<div id="page" style="position:absolute;right:50px;"></div>
</body>
<script type="text/javascript" src="/iamp/js/layui/layui.js"></script>
<script>
var mainUrl = '/iamp/robotDef/list.do';
var ajaxDel = '/iamp/robotDef/del.do';
var layer; 
layui.use(['layer','laypage','form'], function(){
	var form = layui.form();
	  var layer = layui.layer;
	  window.layer = layer;
	  var laypage = layui.laypage; 
		  laypage({
		    cont: 'page'
		    ,pages: '${pages}' //总页数
		    ,groups: 5 //连续显示分页数
		    ,curr : '${currentPage}'
		    ,jump: function(obj, first){
		        if(!first){
		          var type = $("#type").val();
		          if(type == "all"){
		        	  type="";
		        	  alert(123);
		          }
		          alert(type);
		          location.href='/iamp/robotDef/list.do?pageNo='+obj.curr+'&type='+type;
		        }
		      }
		  }); 
		  
		  form.on('select(type)', function(data){
			  $(".layui-form").submit();
		  });
});

	//删除功能
	$("#del").on("click",function(){
		var layer = window.layer;
		var  checks = $('input:checkbox[name=id]:checked');
		if(checks.length <= 0){
			var data = new Object();
			data.console ="请选择数据!"; 
			warning(data);
			return;
		}
		var ids = new Array();
		for(var i=0; checks.length>i; i++){
			var id = checks[i].value;
			ids.push(id);
		}
		var ids = JSON.stringify(ids);
 		layer.confirm('确定删除该数据吗?', {
		  btn: ['确定', '取消'] //可以无限个按钮
		  ,btnAlign: 'c', yes: function(index, layero){
					var layer = window.layer;
					$("input[name='ids']").val(ids);
					$.post(
						ajaxDel,
						{
							"ids" : ids
						},
						function(data){
							var data = data;
							if(data.result == 'success'){
								data.console='删除成功！';
								warning(data);
							}	
						},"json"
					);
		  }
		});
	});
	
	//修改功能
	$("#update").on("click",function(){
		var data = new Object();
		var  checks = $('input:checkbox[name=id]:checked');
		if(checks.length == 1) {
			var id = checks[0].value;
 			var title ="修改信息资源库";
 			var url ="/iamp/robotDef/showDetail.do?id="+id;
 			window.parent.detailWin(title,url);
		}else if(checks.length == 0){
			data.console = "请选择数据!";
			warning(data);
		}else{
			data.console = "请选择一条数据!";
			warning(data);
		}
	});
	
	//用于新建成功后刷新
	function refresh(){
		location.href = mainUrl;
	}
	
	//新建
	$("#add").on("click",function(){
		var title ='新建/修改机器人';
		var url ="/iamp/robotDef/showDetail.do";
		window.parent.detailWins(title,url,"1000px","500px");
	});
	
	//通用提示框
	function warning(data){
		var data = data;
		var layer = window.layer;
		layer.open({
          type: 1
          ,title:"提醒"
          ,content: '<div style="padding: 20px 100px;">'+data.console+'</div>'
          ,btn: '关闭'
          ,btnAlign: 'c' //按钮居中
          ,shade: 0 //不显示遮罩
          ,yes: function(){
        	  layer.close(layer.index);
	    		if(typeof(data.result) !='undefined' && data.result == 'success'){
	      			refresh();
	    		}
          }
        });
	}
	
</script>
</html>