<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>文本分词</title>
</head> 
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="/iamp/js/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="/iamp/js/layui/css/global.css" media="all">
<script type="text/javascript" src="/iamp/js/layui/layui.js"></script>
<script src="/iamp/js/jquery-1.7.2.min.js"></script>
<style type="text/css">
.foot{
	position:relative;
	bottom:5px;  
	overflow:hidden;
}
</style>
<body>	
 	<form class="layui-form" action="">
 	<table lay-skin="nob" class="layui-table" style="width:85%">
 	<tr>
	 	<td>
		    <label class="layui-form-label">文件来源：</label>
		    <div class="layui-input-block">
		      <input type="radio"  value="1" title="本地文件">
		      <input type="radio"  value="2" title="本地文件夹" >
		      <input type="radio"  value="3" title="剪贴板" checked>
	   		 </div>
	    </td>
	    <td>
		    <label class="layui-form-label"><span style="color:red">*</span>分类方式：</label>
		    <div class="layui-input-block">
		      <input type="radio"  value="1" title="特征匹配">
		      <input type="radio"  value="2" title="规则匹配" checked>
		    </div>
		    </div>
	    </td>
    </tr>
    <tr>
   	 <td colspan="2">
    	<label class="layui-form-label"><span style="color:red">*</span>分类模板：</label>
   		<div class="layui-input-block">
	      <select lay-filter="serviceType" lay-verify="required" id="serviceType" >
	        <option value="1" >test1</option>	        	
	        <option value="2" >test2</option>	        	
	      </select>
	    </div>
     </td>
    </tr>
    <tr>
   	 <td colspan="2">
    	<label class="layui-form-label"></span>选择文件：</label>
   		<input type="file" id="demo-upload-unwrap">
		<div class="layui-btn-group">
		  <button class="layui-btn layui-btn-small">浏览</button><span style="margin-left:10px;"></span>
		  <button class="layui-btn layui-btn-small">上传</button><span style="margin-left:10px;"></span>
		  <button class="layui-btn layui-btn-small">分类</button>
		</div>
     </td>
    </tr>
</table>
</form>
	
    <div class="site-tips">
    分类结果
    </div>
 	<table class="layui-table">
	  <colgroup>
	    <col>
	    <col width="200">
	    <col>
	  </colgroup>
	  <thead>
	    <tr>
	     <th>标题</th><th>所属分类</th>
	    </tr> 
	  </thead>
	  <tbody>
	  </tbody>
	</table> 
</body>
<script>
var ajaxSave = '/iamp/service/save.do'
var layer;
$(function(){	
	/*layUI相关操作 有些需要依赖才能使用  */
	layui.use(['form','element','layer'], function(){
		var element = layui.element();
		var form = layui.form();
		var layer = layui.layer;
		window.element = element;
		window.form = form;
		window.layer = layer;
		/* 根据服务类型判断 是否显示维护脚本按钮 */
		form.on('select(serviceType)', function(data){
			 var val =  data.value;
			if(val == "2"){
				$("#script").show();
			}else{
				$("#script").hide();
			}
		});
	});		});
</script>
</html>