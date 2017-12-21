<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>文件转成WORD</title>
</head>
 <meta name="renderer" content="webkit">
 <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
 <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
 <link rel="stylesheet" href="/ExIAServer/js/layui-v2/css/layui.css"  media="all">
 <link rel="stylesheet" href="/ExIAServer/js/layui-v2/css/layui.mobile.css"  media="all">
 <style type='text/css'>
	.titleName {
		width:100px;
		text-align: right;
	}
	.subHeand{
		margin-left: 15px;
		padding: 0 10px;
		font-size: 15px;
		font-weight: 300;"
	}
	.method{
		margin-left: 15px;
		padding: 0 10px;
		font-size: 15px;
		font-weight: 300;"
	}
 </style>
<body >
	<div style="margin-left:5px;">
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
		  <legend typeVal="file.convertWord">6. 文件转成WORD</legend>
		</fieldset>
		<blockquote class="layui-elem-quote">
		   支持PDF、HTML、HTM转成一个DOC或DOCX文件。
		</blockquote> 
		
		<!--  -->
		<span class="method">6.1 上传一个文件并转成WORD文件</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="0">进行调试</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法路径</td><td colspan="3">/ExIAServer/services/file/convert/word/upload</td></tr>
		</thead>
			<tr><td class="titleName">调用方式</td><td colspan="3">HTTP请求</td></tr>
			<tr><td class="titleName">方法功能</td><td colspan="3">上传一个临时文件，并把文件转成WORD。如果本身就是一个WORD，不用进行转换。</td></tr>
			<tr><td class="titleName" rowspan="5">参数：</td><td>参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>extoken</td><td>String</td><td>[必须提供]访问服务的令牌</td></tr>
			<tr><td>file</td><td>File</td><td>[必须提供]文件流对象</td></tr>
			<tr><td>fileTitle</td><td>String</td><td>[可选项]文件标题</td></tr>
			<tr><td>fileExt</td><td>String</td><td>[可选项]文件扩展名</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3"> 
					图片临时文件的唯一标识,JSON格式：<br>
					{<br>
					 "status":1,   #1-成功，-99—不支持当前文件格式转换<br>
					  "msg":"",    #异常提示信息<br>
					 "data":""    #临时文件的唯一标识<br>
					}<br>

				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method">6.2 把指定临时文件转成Word文件</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="1">进行调试</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法路径</td><td colspan="3">/ExIAServer/services/file/convert/word/upload/{临时文件的唯一标识}</td></tr>
		</thead>
			<tr><td class="titleName">调用方式</td><td colspan="3"></td></tr>
			<tr><td class="titleName">方法功能</td><td colspan="3">把指定临时文件转成Word，如果文件本身就是Word，那不用转换，返回该文件ID。</td></tr>
			<tr><td class="titleName" rowspan="2">参数：</td><td>参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>extoken</td><td>String</td><td>[必须提供]访问服务的令牌</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3"> 
					Word临时文件的唯一标识,JSON格式：<br>
					{<br>
					 "status":1,  #1-成功，-99—不支持当前文件格式转换<br>
					 "msg":"",   #异常提示信息<br>
					 "data":""   #Word临时文件的唯一标识<br>
					}<br>

				</td>
			</tr>
		</table><br>
		
	</div>
<script type="text/javascript" src="/ExIAServer/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/ExIAServer/js/interfaces.js"></script>
</body>
</html>