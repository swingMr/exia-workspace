<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>测试服务</title>
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
		style="text-align:center;";
	}
</style>
<body style="width:98%;margin-left:10px;">	
	 <div>输入参数：</div>
	<form id="paramForm">
    <table class="layui-table" id="paramTable">
	  <colgroup>
	    <col width="150">
	    <col width="200">
	    <col>
	  </colgroup>
	  <thead>
	    <tr>
	     </th><th style="text-align:center;";>参数名称</th><th style="text-align:center;";>参数值</th>
	    </tr> 
	  </thead>
	  <tbody>
	  </tbody>
	  <input type="hidden" id="pythonPath" value="${!empty obj? obj.pythonPath:''}">
	</table> 
	</form> 
	 <div>运行日志：</div>
 	  <div class="layui-form-item layui-form-text">
	     <textarea  id="content" placeholder="" class="layui-textarea" style="height:200px"> </textarea>
	</div> 
		<center> <button class="layui-btn layui-btn-normal layui-btn-small foot" id="execute" >确定</button> 
		<button class="layui-btn layui-btn-danger layui-btn-small foot" id="close" >取消</button> 
		</center>
</body>
<script type="text/javascript" src="/iamp/js/layui/layui.js"></script>
<script>
var executeUrl = '/iamp/python/executePython.do'
var layer; 
layui.use(['layer','form'], function(){
	var form = layui.form();
	  var layer = layui.layer;
});

function addTr(){
  	var tr = '<tr><td><span class="key"></span></td><td><input type="text" name="paramVal" required  lay-verify="required" placeholder="参数值" autocomplete="off" class="layui-input"></td></tr>';
	return tr;
}
$(function(){
	var param = '${obj.programInput}'
	if(param != ''){
		var list = JSON.parse(param);
		console.log(list);
		for(var i=0; list.length>i; i++){
			var tr =$(addTr());
			tr.find(".key").html(list[i].paramName);
			tr.find("input[name='paramVal']").val(list[i].paramValue);
			$("#paramTable tbody").append(tr);
		}
	}
	
	$("#execute").on("click",function(){
		var paramNum = $("#paramTable").find("input[name='paramVal']").length;
		var pythonPath = $("#pythonPath").val();
		var val = [];
		for(var i=0; paramNum>i; i++){
			var param = new Object();
			val.push($("input[name='paramVal']")[i].value);
		}

		val = JSON.stringify(val);
 		$.post(
 			executeUrl,
			{
			 "val":val,
			 "pythonPath":pythonPath
			 },
			function(data){
				console.log(data.content);
				$("#content").text(data.content);
			},"json"		
		); 
	});
});

	$("#close").on("click",function(){
		closeCurrentWin();
	});
	function closeCurrentWin(){
	   var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	   parent.layer.close(index);
	}
</script>
</html>