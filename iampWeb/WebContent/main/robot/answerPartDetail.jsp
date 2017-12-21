<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>添加部件规则</title>
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
</style>

<body>	
 	<table class="layui-table" id="partRuleTable">
	  <colgroup>
	    <col width="180">
	    <col>
	    <col width="60">
	  </colgroup>
	  <thead>
	    <tr>
	     <th>名称</th><th>匹配规则</th><th><i class="layui-icon btnfont" style="color:#3B6771;cursor:pointer;" title="添加规则" name="addNameAndRule">&#xe608;</i></th>
	    </tr> 
	  </thead>
	  <tbody id="partRuleTbody">
	  	<tr>
		  	<td><input name="ruleName" class="layui-input" type="text"/></td>
		  	<td name="ruleTd">
		  		<div name="ruleDiv">	
				    <div class="layui-input-inline" style="width:92%">
				      <input name="ruleContent" class="layui-input" type="text" />
				    </div>
				    <div class="layui-form-mid layui-word-aux" style="float:right"><i class="layui-icon btnfont" style="color:#3B6771;cursor:pointer;" title="添加" name="addRule">&#xe654;</i></div>
		  		</div>
		  	</td>
		  	<td><i class="layui-icon btnfont" style="color:red;cursor:pointer;" title="删除" name="removeNameAndRule">&#xe640;</i></td>
	  	</tr>
	  </tbody>
	</table> 
</body>
<script type="text/javascript" src="/iamp/js/layui/layui.js"></script>
<script type="text/javascript">
	$(document).on("click","i[name='addRule']",function(){
		var appendTr = '<div name="ruleDiv" style="margin-top:10px"><div class="layui-input-inline" style="width:92%">'+
				       '<input name="ruleContent" class="layui-input" type="text" /></div><div class="layui-form-mid layui-word-aux" style="float:right">'+
				       '<i class="layui-icon btnfont" style="color:red;cursor:pointer;" title="删除" name="removeRule">&#x1006;</i></div></div>'; 
		$(this).closest("td").append(appendTr);
	});
	
	$(document).on("click","i[name='removeRule']",function(){
		$(this).closest("div[name='ruleDiv']").remove();
	});
	
	$(document).on("click","i[name='addNameAndRule']",function(){
		var appendTr = '<tr><td><input name="ruleName" class="layui-input" type="text"/></td><td name="ruleTd">'+
					'<div name="ruleDiv"><div class="layui-input-inline" style="width:92%">'+
				    '<input name="ruleContent" class="layui-input" type="text" /></div><div class="layui-form-mid layui-word-aux" style="float:right">'+
				    '<i class="layui-icon btnfont" style="color:#3B6771;cursor:pointer;" title="添加" name="addRule">&#xe654;</i></div></div>'+
				    '</td><td><i class="layui-icon btnfont" style="color:red;cursor:pointer;" title="删除" name="removeNameAndRule">&#xe640;</i></td></tr>';
		$(this).closest("#partRuleTable").find("#partRuleTbody").append(appendTr);
	});
	
	$(document).on("click","i[name='removeNameAndRule']",function(){
		$(this).closest("tr").remove();
	});


</script>
</html>