<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>�ļ�ת��WORD</title>
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
		  <legend typeVal="file.convertWord">6. �ļ�ת��WORD</legend>
		</fieldset>
		<blockquote class="layui-elem-quote">
		   ֧��PDF��HTML��HTMת��һ��DOC��DOCX�ļ���
		</blockquote> 
		
		<!--  -->
		<span class="method">6.1 �ϴ�һ���ļ���ת��WORD�ļ�</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="0">���е���</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">����·��</td><td colspan="3">/ExIAServer/services/file/convert/word/upload</td></tr>
		</thead>
			<tr><td class="titleName">���÷�ʽ</td><td colspan="3">HTTP����</td></tr>
			<tr><td class="titleName">��������</td><td colspan="3">�ϴ�һ����ʱ�ļ��������ļ�ת��WORD������������һ��WORD�����ý���ת����</td></tr>
			<tr><td class="titleName" rowspan="5">������</td><td>������</td><td>��������</td><td>����˵��</td></tr>
			<tr><td>extoken</td><td>String</td><td>[�����ṩ]���ʷ��������</td></tr>
			<tr><td>file</td><td>File</td><td>[�����ṩ]�ļ�������</td></tr>
			<tr><td>fileTitle</td><td>String</td><td>[��ѡ��]�ļ�����</td></tr>
			<tr><td>fileExt</td><td>String</td><td>[��ѡ��]�ļ���չ��</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3"> 
					ͼƬ��ʱ�ļ���Ψһ��ʶ,JSON��ʽ��<br>
					{<br>
					 "status":1,   #1-�ɹ���-99����֧�ֵ�ǰ�ļ���ʽת��<br>
					  "msg":"",    #�쳣��ʾ��Ϣ<br>
					 "data":""    #��ʱ�ļ���Ψһ��ʶ<br>
					}<br>

				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method">6.2 ��ָ����ʱ�ļ�ת��Word�ļ�</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="1">���е���</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">����·��</td><td colspan="3">/ExIAServer/services/file/convert/word/upload/{��ʱ�ļ���Ψһ��ʶ}</td></tr>
		</thead>
			<tr><td class="titleName">���÷�ʽ</td><td colspan="3"></td></tr>
			<tr><td class="titleName">��������</td><td colspan="3">��ָ����ʱ�ļ�ת��Word������ļ��������Word���ǲ���ת�������ظ��ļ�ID��</td></tr>
			<tr><td class="titleName" rowspan="2">������</td><td>������</td><td>��������</td><td>����˵��</td></tr>
			<tr><td>extoken</td><td>String</td><td>[�����ṩ]���ʷ��������</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3"> 
					Word��ʱ�ļ���Ψһ��ʶ,JSON��ʽ��<br>
					{<br>
					 "status":1,  #1-�ɹ���-99����֧�ֵ�ǰ�ļ���ʽת��<br>
					 "msg":"",   #�쳣��ʾ��Ϣ<br>
					 "data":""   #Word��ʱ�ļ���Ψһ��ʶ<br>
					}<br>

				</td>
			</tr>
		</table><br>
		
	</div>
<script type="text/javascript" src="/ExIAServer/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/ExIAServer/js/interfaces.js"></script>
</body>
</html>