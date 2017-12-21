<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>���Է���</title>
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
 	<table lay-skin="nob" class="layui-table">
	  <colgroup>
	    <col width="150">
	    <col width="200">
	    <col>
	  </colgroup>
		 <tr>
		  	<td>
				<label class="layui-form-label"><span style="color:red">*</span>�������ƣ�</label>
			    <div class="layui-input-block">
			      <input type="text" id="serviceName" name="serviceName" required  value="${obj.serviceName}" lay-verify="required" placeholder="" autocomplete="off" class="layui-input">
			    </div>
	    	</td>
		  	<td>
				<label class="layui-form-label"><span style="color:red">*</span>����·����</label>
			    <div class="layui-input-block">
			      <input type="text" id="servicePath" name="servicePath" required value="${obj.accessPath}" lay-verify="required" placeholder="services/base/security/gettoken.do" autocomplete="off" class="layui-input">
			    </div>
	    	</td>
    	</tr>
    </table>
	 <div>���������</div>
	<form id="paramForm">
    <table class="layui-table" id="paramTable" lay-skin="line">
	  <colgroup>
	    <col width="150">
	    <col width="200">
	    <col>
	  </colgroup>
	  <thead>
	    <tr>
	     </th><th style="text-align:center;";>��������</th><th style="text-align:center;";>����ֵ</th>
	    </tr> 
	  </thead>
	  <tbody>
	  </tbody>
	</table> 
	</form> 
	<center> <button class="layui-btn layui-btn-normal layui-btn-small foot" id="test" >����</button> </center>
	 <div>�����</div>
 	  <div class="layui-form-item layui-form-text">
	     <textarea  id="content" placeholder="" class="layui-textarea" style="height:200px"> </textarea>
	</div> 
</body>
<script type="text/javascript" src="/iamp/js/layui/layui.js"></script>
<script>
var getDataUrl = '/iamp/service/getDataByTest.do'
var layer; 
layui.use(['layer','form'], function(){
	var form = layui.form();
	  var layer = layui.layer;
});

function addTr(){
  	var tr = '<tr><td><span class="key"></span></td><td><input type="text" name="paramVal" required  lay-verify="required" placeholder="����ֵ" autocomplete="off" class="layui-input"></td></tr>';
	return tr;
}
$(function(){
	var param = '${obj.serviceInput}'
	if(param != ''){
		var list = JSON.parse(param);
		for(var i=0; list.length>i; i++){
			var tr =$(addTr());
			tr.find(".key").html(list[i].paramName);
			$("#paramTable tbody").append(tr);
		}
	}
	
	$("#test").on("click",function(){
		var paramNum = $("#paramTable").find("input[name='paramVal']").length;
		var key = [];
		var val = [];
		for(var i=0; paramNum>i; i++){
			var param = new Object();
			val.push($("input[name='paramVal']")[i].value);
			key.push($("input[name='paramVal']").closest('td').prev().find(".key")[i].innerHTML);
		}
		console.log(val);
		console.log(key);
		key = JSON.stringify(key);
		val = JSON.stringify(val);
		var serviceName = $("#serviceName").val();
		var servicePath = $("#servicePath").val();
		var code = $("#code").val();
		var password = $("#password").val();
		if(serviceName == ""){
		      layer.open({
		          type: 1
		          ,title:"����"
		          ,content: '<div style="padding: 20px 100px;">�������Ʋ���Ϊ��</div>'
		          ,btn: '�ر�'
		          ,btnAlign: 'c' //��ť����
		          ,shade: 0 //����ʾ����
		          ,yes: function(){
		            layer.closeAll();
		          }
		        });
			return 
		}
		if(servicePath == ""){
		      layer.open({
		          type: 1
		          ,title:"����"
		          ,content: '<div style="padding: 20px 100px;">����·������Ϊ��</div>'
		          ,btn: '�ر�'
		          ,btnAlign: 'c' //��ť����
		          ,shade: 0 //����ʾ����
		          ,yes: function(){
		            layer.closeAll();
		          }
		        });
			return 
		}
		if(code == ""){
		      layer.open({
		          type: 1
		          ,title:"����"
		          ,content: '<div style="padding: 20px 100px;">appCode����Ϊ��</div>'
		          ,btn: '�ر�'
		          ,btnAlign: 'c' //��ť����
		          ,shade: 0 //����ʾ����
		          ,yes: function(){
		            layer.closeAll();
		          }
		        });
			return 
		}
		if(password == ""){
		      layer.open({
		          type: 1
		          ,title:"����"
		          ,content: '<div style="padding: 20px 100px;">appPassword����Ϊ��</div>'
		          ,btn: '�ر�'
		          ,btnAlign: 'c' //��ť����
		          ,shade: 0 //����ʾ����
		          ,yes: function(){
		            layer.closeAll();
		          }
		        });
			return 
		}
 		$.post(
			getDataUrl,
			{
			 "key":key,
			 "val":val,
			 "servicePath":servicePath,
			 "serviceName":serviceName
			 },
			function(data){
				console.log(data.data);
				$("#content").text(data.data);
			},"json"		
		); 
	});
});
	
</script>
</html>