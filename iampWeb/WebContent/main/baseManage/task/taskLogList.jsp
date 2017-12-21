<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>查看定时任务日志</title>
</head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="/iamp/js/layui-v2/css/layui.css" media="all" />
<link rel="stylesheet" href="/iamp/js/layui-v2/css/global.css" media="all">
<script src="/iamp/js/jquery-1.7.2.min.js"></script>
<style type="text/css">
	.btn{
		margin-top:10px;
		margin-left:10px;
	}
	
</style>
<body>
	<form class="layui-form" id="form" action="/iamp/task/viewTaskLog.do" method="post">
		<input type="hidden" name="pageNo" value="${page.pageNum}">
		<input type="hidden" name="pageSize" value="${page.pageSize}">
		<input type="hidden" name="taskId" value="${taskId}">
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;" id="query">查询</span></div>
    	<div style="float:right;position:relative;" class="btn"> 
			<label class="layui-form-label">任务名称：</label>
		    <div class="layui-input-block">
		    	<input type="text" name="taskName" style="width:200px" lay-verify="required" placeholder="请输入任务名称" autocomplete="off" class="layui-input" value="${!empty taskName ? taskName : ''}"> 
		    </div>
    	</div>
    	<div style="float:right;position:relative;" class="btn"> 
				<label class="layui-form-label">任务类型：</label>
				<div class="layui-input-block" style="width:150px">
			      	<select id="logType" name="logType" lay-verify="required">
			      		<c:choose>
		       			  <c:when test="${logType==1}"> 
						   		<option value="all">所有</option>
				        		<option value="1" selected>本地定时任务</option>
				        		<option value="2">python定时任务</option>
				        		<option value="3">远程定时任务</option>
						   </c:when>
						   <c:when test="${logType==2}"> 
						   		<option value="all">所有</option>
				        		<option value="1">本地定时任务</option>
				        		<option value="2" selected>python定时任务</option>
				        		<option value="3">远程定时任务</option>
						   </c:when>
						   <c:when test="${logType==3}"> 
						   		<option value="all">所有</option>
				        		<option value="1">本地定时任务</option>
				        		<option value="2">python定时任务</option>
				        		<option value="3" selected>远程定时任务</option>
						   </c:when>  
						   <c:otherwise>
		       			 		<option value="all">所有</option>
				        		<option value="1">本地定时任务</option>
				        		<option value="2">python定时任务</option>
				        		<option value="3">远程定时任务</option>
		       			  </c:otherwise>
		       			</c:choose>
				      </select>
			    </div>
	    	</div>
	</form>
 	<table class="layui-table" lay-skin="line" id="taskTable">
	  <colgroup>
	    <col width="200">
	    <col width="120">
	    <col width="150">
	    <col width="120">
	  </colgroup>
	  <thead>
	    <tr>
	     <th>任务名称</th><th>任务类型</th><th>执行时间</th>
	     <th>任务状态</th>
	    </tr> 
	  </thead>
	  <tbody>
	  	<c:if test="${!empty page.list}">
		  <c:forEach var="task" items="${page.list}" varStatus="status">
		  	<tr>
		  		<td>${task.taskName}</td>
		  		<td>
		  			<c:if test="${task.logType=='1'}"> 
		  		 		本地定时任务
		   			</c:if>
		  		 	<c:if test="${task.logType=='2'}"> 
		  		 		python定时任务
		   			</c:if>
		   			<c:if test="${task.logType=='3'}"> 
		  		 		远程定时任务
		   			</c:if>
		  		</td>
		  		<td><fmt:formatDate value="${task.createDate}" pattern="yyyy-MM-dd HH:mm:ss" type="date"/></td>
		  		<td name="status">
		  			<c:if test="${task.taskStatus==0}">停止</c:if>
		  			<c:if test="${task.taskStatus==1}">运行</c:if>
		  			<c:if test="${task.taskStatus==-1}">禁止</c:if>
		  			<c:if test="${task.taskStatus==2}">暂停</c:if>
		  		</td>
		  		<!-- <td>
		  			<div class="layui-btn-group">
					  <button class="layui-btn layui-btn-primary layui-btn-small">
					    <i class="layui-icon">&#xe652;</i>
					  </button>
					  <button class="layui-btn layui-btn-primary layui-btn-small">
					    <i class="layui-icon">&#xe651;</i>
					  </button>
					  <button class="layui-btn layui-btn-primary layui-btn-small">
					    <i class="layui-icon">&#xe60f;</i>
					  </button>
					  <button class="layui-btn layui-btn-primary layui-btn-small">
					    <i class="layui-icon">&#xe615;</i>
					  </button>
					</div>
		  		</td> -->
			  	</tr>
			</c:forEach>
		  </c:if>
	  </tbody>
	</table> 
	<div id="page" style="position:absolute;right:50px;"></div>
</body>
<script type="text/javascript" src="/iamp/js/layui-v2/layui.js"></script>
<script>
var layer;
layui.use(['layer','laypage','form','element'], function(){
	var form = layui.form;
	var element = layui.element;
	  layer = layui.layer;
	  window.layer = layer;
	  var laypage = layui.laypage; 
	  laypage.render({
		    elem: 'page'//id
		    /* ,pages:'${page.pages}' //分页数 */
		    ,count:'${page.total}'
			,limit:10
		    ,groups: 5 //连续显示分页数
		    ,curr : '${page.pageNum}'
		    ,jump: function(obj, first){
		    	if(!first){
		          layer.msg('第 '+ obj.curr +' 页');
		          $('input[name="pageNo"]').val(obj.curr);
		          $('form').submit();
		        }
		      }
		  }); 
});

	$('#query').on('click',function() {
		$('#form').submit();
	});
	
	//用于新建成功后刷新
	function refresh(){
		location.href = "/iamp/task/list.do";
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