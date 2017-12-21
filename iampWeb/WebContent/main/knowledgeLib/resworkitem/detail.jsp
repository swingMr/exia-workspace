<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>预处理</title>
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
<body>	
 	<form class="layui-form" action="/iamp/resworkitem/${method}.do" id="form">
 		<table class="layui-table" lay-skin="line">
	  <colgroup>
	    <col>
	    <col width="120">
	    <col width="500">
	  </colgroup>
	  <thead>
	    <tr>
	     <th>标题</th><th>归属领域</th><th>主题词</th>
	    </tr> 
	  </thead>
	  <tbody>
	  <c:if test="${!empty list}">
		  <c:forEach var="item" items="${list}" varStatus="status">
		  	<tr>
		  		<td>${item.resTitle}<input type="hidden" value="${item.workItemId}" name="workItemId"></td>
		  		<td><input type="text"  name="belongDomain" placeholder="请输入领域" autocomplete="off" class="layui-input" value='${item.belongDomain}'></td>
		  		<td><input type="text"  name="keyword" placeholder="请输入主题词" autocomplete="off" class="layui-input" value='${item.keyWord}'></td>
		  	</tr>
		  </c:forEach>
	  </c:if>
	  </tbody>
	</table> 
	</form> 
<center>
	<span class="layui-btn layui-btn-normal layui-btn-normal" lay-submit lay-filter="*" id="save">确定</span>
	<span class="layui-btn layui-btn-normal layui-btn-danger" lay-submit lay-filter="*" id="cancel">取消</span>
</center>
</body>
<script type="text/javascript" src="/iamp/js/layui/layui.js"></script>
<script>
var layer;
	
	layui.use(['element','layer','form','validator'], function(){
		var element = layui.element();
		layer = layui.layer;
		window.element = element;
		var form = latui.form();
	});
	
	$("#cancel").on("click",function(){
		closeCurrentWin();
	});

	function closeCurrentWin(){
	   var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	   parent.layer.close(index);
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
	
	$('#save').on('click',function() {
		var arr = [];
		$('tbody').find('tr').each(function() {
			var workItemId = $(this).find('input[name="workItemId"]').val();
			var belongDomain = $(this).find('input[name="belongDomain"]').val();
			var keyword = $(this).find('input[name="keyword"]').val();
			var json = {};
			json.workItemId = workItemId;
			json.belongDomain = belongDomain;
			json.keyword = keyword;
			arr.push(json);
		})
		$.ajax({
             type: "POST",
             url:$('#form').attr('action'),
             data:{datas:JSON.stringify(arr)},// 你的formid
             async: false,
             error: function(request) {
                 alert("出现异常");
             },
             dataType:"json",
             success: function(data) {
             	if(data.result) {
             		layer.open({
	          type: 1
	          ,title:'提醒'
	          ,content: '<div style="padding: 20px 100px;">'+data.msg+'</div>'
	          ,btn: '关闭'
	          ,btnAlign: 'c' //按钮居中
	          ,shade: 0 //不显示遮罩
	          ,yes: function(){
				var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	      		parent.layer.close(index);
	    		parentRefresh();
	          }
	        });	
             	} else {
             		showMsg('提醒',data.msg);
			return;
             	}
             }
         }); 
	});
	
	function parentRefresh(){
		window.parent.loadIframe();
	}
	
	
</script>
</html>