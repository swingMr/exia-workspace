<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>服务定义</title>
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
	.title{
		
	}
</style>
<body>	
 	<table class="layui-table" lay-skin="line" id="serviceTable">
	  <colgroup>
	    <col width="50">
	    <col width="120">
	    <col width="200">
	    <col width="120">
	  </colgroup>
	  <thead>
	    <tr>
	     <th><input type="checkbox" id="checkAll" name="checkAll"></th><th>服务名称</th><th>中文名称</th><th >服务类型</th>
	    </tr> 
	  </thead>
	  <tbody>
	  <c:forEach var="list" items="${pageInfo.list}" varStatus="status">
	  	<tr>
	  		<input type="hidden" value="${list.pythonPath}"  name="pythonPath">
	  		<td><input type="checkbox" name="serviceId" value="${list.serviceId}"></td><td>${list.serviceName}</td> <td>${list.serviceCname}</td> 
	  		<td>
	  			<c:choose>  					  
					   <c:when test="${!empty list && list.serviceType==1}"> 
					   		http服务
					   </c:when>     
					   <c:otherwise>
					   		python服务
					   </c:otherwise>  
				</c:choose> 
			</td>
	  	</tr>
	  </c:forEach>
	  <input type="hidden" name="ids">
	  </tbody>
	</table> 
	<div id="page" style="position:absolute;right:50px;"></div>
</body>
<script type="text/javascript" src="/iamp/js/layui-v2/layui.js"></script>
<script>
var layer; 
layui.use(['layer','laypage','form'], function(){
	var form = layui.form;
	var element = layui.element;
	  layer = layui.layer;
	  window.layer = layer;
	  var laypage = layui.laypage; 
	  laypage.render({
		    elem: 'page'//id
		    /* ,pages:'${page.pages}' //分页数 */
		    ,count:'${pageInfo.total}'
			,limit:10
		    ,groups: 5 //连续显示分页数
		    ,curr : '${pageInfo.pageNum}'
		    ,jump: function(obj, first){
		    	if(!first){
		          layer.msg('第 '+ obj.curr +' 页');
		          location.href='/iamp/service/list.do?pageNo='+obj.curr;
		        }
		      }
		  }); 
});
	$('input[name="checkAll"]').change(function(){
		var selectList = $("#serviceTable").find("input[type='checkbox']");
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
</script>
</html>