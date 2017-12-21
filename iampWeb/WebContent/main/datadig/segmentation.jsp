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
<script type="text/javascript" src="/iamp/js/loading.js"></script>
<script src="/iamp/js/jquery-1.7.2.min.js"></script>
<script src="/iamp/js/jquery.form.js"></script>
<style type="text/css">
.foot{
	position:relative;
	bottom:5px;  
	overflow:hidden;
}
</style>
<body>	
	<form target="layui-upload-iframe" method="post" key="set-mine" id="form" enctype="multipart/form-data" action="/iamp/fileHander/participle.do">
        <label class="layui-form-label">选择文件：</label>
        <input type="file" name="file" id="demo-upload-unwrap" >
		<div class="layui-btn-group">
		  <span id="upload" class="layui-btn layui-btn-normal">提取</span>
		</div>
		 <!-- <div class="layui-form-item layui-form-text">
		</div> -->
        </form>
        <div class="layui-form">
	<div class="site-tips" style="margin-top:10px">
        	文本内容
     </div>
     <div style="padding:10px">
     	<textarea id="fileText" placeholder="文本内容" class="layui-textarea" style="height:170px;"></textarea>
     </div>
	 <div class="site-tips" style="margin-top:10px">
        	分词结果<span style="margin-left:20px"><input type="checkbox" id="tagPart" name="tagPart" title="显示词性" lay-filter="tagPart" lay-skin="primary" /></span>
     </div>  
     <div style="padding:10px">
    <textarea id="participleResult" placeholder="分词结果" class="layui-textarea" style="height:170px;"></textarea>
     </div>    
     </div>
</body>
<script>
var ajaxSave = '/iamp/service/save.do'
var participleResult = "";//不显示词性的结果集
var participleTagResult = "";//显示词性后的结果集
var isShowParticiple = "";
$(function(){	
	/*layUI相关操作 有些需要依赖才能使用  */
	layui.use(['form','element','layer'], function(){
		var element = layui.element();
		var form = layui.form();
		var layer = layui.layer;
		window.element = element;
		window.form = form;
		window.layer = layer;
		
		form.on('checkbox(tagPart)', function(data){
			isShowParticiple = $(this).attr('checked');
			if($(this).attr('checked') =='checked'){
				$("#participleResult").val("");
				$("#participleResult").val(participleTagResult);
			}else{
				$("#participleResult").val("");
				$("#participleResult").val(participleResult);
			}
		});
	});
	
	$("#upload").on("click",function(){
		//每次点提取先清空文本信息
		$("#participleResult").val("");
		$("#fileText").val("");
		var str = document.getElementById("demo-upload-unwrap").value;
		if(str.length==0)
		{
			layer.open({
			  content: '请选择上传文件！'
			});
			return false;
		}
		showLoadingMsg('正在加载,请稍后...');
		$("#form").ajaxSubmit({
   			dataType: "json",
            success: function (data) {
            	var fileText = data.fileText;
            	participleResult = data.participleResult;
            	participleTagResult = data.participleTagResult;
            	$("#fileText").val(fileText);
            	if(isShowParticiple.indexOf("checked") != -1){
            		$("#participleResult").val(participleTagResult);
            	}else{
            		$("#participleResult").val(participleResult);
            	}
            	removeLoadingMsg();
            }
         });
	});
});
</script>
</html>