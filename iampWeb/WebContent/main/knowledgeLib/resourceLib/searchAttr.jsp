<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>筛选属性</title>
</head> 
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="/iamp/js/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="/iamp/js/layui/css/global.css" media="all">
<link type="text/css" href="/iamp/js/codemirror-5.2/lib/codemirror.css" rel="stylesheet" >
<link type="text/css" href="/iamp/js/codemirror-5.2/addon/hint/show-hint.css" rel="stylesheet"></link>
<script src="/iamp/js/codemirror-5.2/lib/codemirror.js"></script>
<script src="/iamp/js/codemirror-5.2/mode/python/python.js"></script>
<script src="/iamp/js/codemirror-5.2/addon/hint/show-hint.js"></script>
<script type="text/javascript" src="/iamp/js/layui/layui.js"></script>
<script src="/iamp/js/jquery-1.7.2.min.js"></script>
<script src="/iamp/js/jquery.form.js"></script>
<script type="text/javascript" charset="gbk" src="/iamp/js/ueeditor/ueditor.config.js"></script>
<script type="text/javascript" charset="gbk" src="/iamp/js/ueeditor/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="gbk" src="/iamp/js/ueeditor/lang/zh-cn/zh-cn.js"></script>
<style type="text/css">
.foot{
	position:relative;
	bottom:5px;  
	overflow:hidden;
}
</style>
<body id="bodyT">	
	<form class="layui-form" action="" style="height:230px">
	<table  class="layui-table" lay-skin="line" id="attrTable" >
		  <colgroup>
	    <col width="250">
	    <col >
	    <col width="60">
	  </colgroup>
	  <thead>
	    <tr>
	     <th >属性名</th><th >属性值</th><th><i class="layui-icon" id="addParam" style="font-size: 15px;color: #009688;">&#xe608;</i></th>
	    </tr> 
	  </thead>
	  <tbody>
		  <tr>
		  	<td>
		  	<!-- <input type="text" name="attrName" name="title" required value="" lay-verify="required"  autocomplete="off" class="layui-input"> -->
		      		<select lay-filter="paramType"  name="paramType"  >
       		 			     <option value="title" >标题</option>
						   	 <option value="belongDomain" >归属领域</option>
						   	 <option value="source">来源</option>
						   	 <option value="publisher">发布者</option>
						   	 <option value="publishDate">发布日期</option>
						   	 <option value="attr">扩展属性</option>
			      	 </select>
		  	</td>
	      	<td><input type="text" name="value" name="title" required value="" lay-verify="required"  autocomplete="off" class="layui-input"></td>
			<td><i class="layui-icon removeParam" style="font-size: 15px; color: #1E9FFF;">&#x1006;</i></td>
		</tr>
	  </tbody>
	</table>
	 <center>
			<span class="layui-btn layui-btn layui-btn-small foot"  id="save" >确定</span>
			<span class="layui-btn layui-btn-danger layui-btn-small foot" id="cancel" >取消</span>
	  </center>
	</form>
	<input type="hidden" id="attrData">
</body>
<script>
$(function(){
	$("#save").on("click",function(){
		var paramNum = $("#attrTable").find("input[name='value']").length;
		var arr = [];
		for(var i=0; paramNum>i; i++){
			var param = new Object();
			if(typeof($(".removeParam").closest('td').prev().prev().find("input[name='attrName']")[i]) == 'undefined'){
				param.basic = $(".removeParam").closest('td').prev().prev().find("select[name='paramType']")[0].value;
			}else{
				param.attrName = $(".removeParam").closest('td').prev().prev().find("input[name='attrName']")[i].value;	
			}
			param.value = $(".removeParam").closest('td').prev().find("input[name='value']")[i].value;
			arr.push(param);
		}
		console.log(arr);
		var data = JSON.stringify(arr);
		window.parent.getAttrVal("xxzywh",data);
		closeCurrentWin();
	});
	
	layui.use(['form','element','layer','form'], function(){
		var form = layui.form();
		window.form = form;
		  var layer = layui.layer;
		  window.layer = layer;
		  
		//扩展属性  根据数据类型 改变默认值的框
		form.on('select(paramType)', function(data){
			if (data.value == 'attr') {
				var td = $(this).closest('td');
				var input = $('<input type="text"  name="attrName"  autocomplete="off" class="layui-input">');
				td.append(input);
			}else if(data.value == 'publishDate'){
				var td = $(this).closest('td').next();
				td.empty();
				var input = $('<input type="text" name="value" name="value"  placeholder="请按 YYYY-MM-dd 格式输入" autocomplete="off" class="layui-input">');
				td.append(input);
			}else{
				var td = $(this).closest('td').find("input[name='attrName']");
				td.remove();
			} 
			form.render();
		}); 
	});
	
	$("#cancel").on("click",function(){
		closeCurrentWin();
		
	});
	
	//添加参数
	$("#addParam").on("click",function(){
		var tr = $(addParam());
		$("#attrTable Tbody").append(tr);
		window.form.render();
	});
	
	//添加参数；
	function addParam(){
		var tr ='<tr><td>'+
      		'<select  lay-filter="paramType" name="paramType"  >'+
		    '<option value="title" >标题</option>'+
	   	 	'<option value="belongDomain" >归属领域</option>'+
	   	 	'<option value="source">来源</option>'+
	   	 	'<option value="publisher">发布者</option>'+
	   	 	'<option value="publishDate">发布日期</option><option value="attr">扩展属性</option></select></td>'
		+'<td><input type="text" name="value" name="title" required value="" lay-verify="required"  autocomplete="off" class="layui-input">'
		+'</td><td><i class="layui-icon removeParam" style="font-size: 15px; color: #1E9FFF;">&#x1006;</i></td></tr>';
		return tr;
	}
	
	//关闭当前窗口
	function closeCurrentWin(){
	   var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	   parent.layer.close(index);
	}
});
</script>
</html>