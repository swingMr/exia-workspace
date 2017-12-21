<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>配置文件管理界面</title>
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
	.title{
		style="text-align:center;";
	}
</style>

<body>	
 	<form class="layui-form" action="#">
 	<table class="layui-table paramTable"  lay-skin="line">
	  <colgroup>
	    <col width="150">
	    <col width="200">
	    <col>
	  </colgroup>
	  <thead>
	    <tr>
	     <th style="text-align:center;";>参数名</th><th style="text-align:center;";>参数值</th>
	    <th style="width:5%;"><i class="layui-icon" id="addParam" style="font-size: 30px;color: #009688;">&#xe608;</i></th> 
	    </tr> 
	  </thead>
	  <tbody >
	  <c:forEach var="list" items="${list}" varStatus="status">
	  	<tr>
			<td><input type="text"  name="key" required value="${list.key}" lay-verify="required" placeholder="" autocomplete="off" class="layui-input"></td>
			<td><input type="text" name="value" required value="${list.value}" lay-verify="required" placeholder="" autocomplete="off" class="layui-input"></td>
			<td><i class="layui-icon removeParam" style="font-size: 30px; color: #1E9FFF;">&#x1006;</i></td>
	  	</tr>
	  </c:forEach>
	  </tbody>
	</table> 
		<center>
			<button class="layui-btn layui-btn layui-btn-small foot" lay-submit="" lay-filter="save" id="save" >确定</button>
		</center>
	</form>
	<div id="page" style="position:absolute;right:50px;bottom:0;"></div>
</body>
<script type="text/javascript" src="/iamp/js/layui-v2/layui.js"></script>
<script>
var ajaxSave = '/iamp/config/save'
var layer;
	//添加配置行
	$("#addParam").on("click",function(){
		var tr  = $(addParam());
		$(".paramTable tbody").append(tr);
	});
	//减少配置行
	$("#paramTable").on("click",".removeParam",function(){
		$(this).closest("tr").remove();
	});
	
	layui.use(['layer','form'], function(){
	var form = layui.form;
	layer = layui.layer;
	window.layer = layer;
	form.on('submit(save)', function(data){
		fsubmit();
		return false;
  	});
	});
	
	function fsubmit(){
		var paramNum = $(".paramTable").find("input[name='key']").length;
		var arr = [];
		for(var i=0; paramNum>i; i++){
			var param = new Object();
			param.key = $(".removeParam").closest('td').prev().prev().find("input[name='key']")[i].value;
			param.value = $(".removeParam").closest('td').prev().find("input[name='value']")[i].value;
			arr.push(param);
		}
		arr = JSON.stringify(arr);
		$.post(
			ajaxSave,
			{
				"arr":arr
			},function(data){
        		layer.open({
			          type: 1
			          ,title:'提醒'
			          ,content: '<div style="padding: 20px 100px;">操作成功</div>'
			          ,btn: '关闭'
			          ,btnAlign: 'c' //按钮居中
			          ,shade: 0 //不显示遮罩
			          ,yes: function(){
			          	layer.closeAll();
			          }
			        });
			},"json"
		);
	};
	
	
	function addParam(){
		var tr ='<tr><td><input type="text"  name="key" required value="" lay-verify="required" placeholder="" autocomplete="off" class="layui-input"></td>'
			+'<td><input type="text" name="value" required value="" lay-verify="required" placeholder="" autocomplete="off" class="layui-input"></td>'
			+'<td><i class="layui-icon removeParam" style="font-size: 30px; color: #1E9FFF;">&#x1006;</i></td></tr>';
		return tr;
	}


	
	//通用提示框
	function warning(data){
		var data = data;
		var layer = window.layer;
		layer.open({
          type: 1
          ,title:"提醒"
          ,content: '<div style="padding: 20px 100px;">'+data+'</div>'
          ,btn: '关闭'
          ,btnAlign: 'c' //按钮居中
          ,shade: 0 //不显示遮罩
          ,yes: function(){
        	  layer.closeAll();
          }
        });
	}
	
	function closeCurrentWin(){
		   var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		   parent.layer.close(index);
		}
</script>
</html>