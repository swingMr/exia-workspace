<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>APP��������</title>
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
		  <legend typeVal="base.security">1.APP��������</legend>
		</fieldset>
		 
		<!-- gettoken -->
		<span class="method">1.1  ��ȡ�������Token</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="0">���е���</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">����·��</td><td colspan="3">/ExIAServer/services/base/security/gettoken</td></tr>
		</thead>
			<tr><td class="titleName">���÷�ʽ</td><td colspan="3"></td></tr>
			<tr><td class="titleName">��������</td><td colspan="3"></td></tr>
			<tr><td class="titleName" rowspan="4">������</td><td>������</td><td>��������</td><td>����˵��</td></tr>
			<tr><td>appCode</td><td>String</td><td>APPӦ�õĴ���</td></tr>
			<tr><td>pwd</td><td>String</td><td>��¼��APP����</td></tr>
			<tr><td>timestamp</td><td>int</td><td>ʱ���</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">{<br>
				"status":1,<br>
				 "msg":"",    #�쳣����<br>
				 "tokenid":"" #�����ַ���ֵ<br>
				}<br>
				</td>
			</tr>
		</table><br>
		
		<!-- clearCache -->
		<span class="method">1.2 ���memcached����</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="1">���е���</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">����·��</td><td colspan="3">/ExIAServer/services/base/security/clearCache</td></tr>
		</thead>
			<tr><td class="titleName">���÷�ʽ</td><td colspan="3"></td></tr>
			<tr><td class="titleName">��������</td><td colspan="3">���memcached����</td></tr>
			<tr><td class="titleName" rowspan="2">������</td><td>������</td><td>��������</td><td>����˵��</td></tr>
			<tr><td>group</td><td>String</td><td>������飬����key��ͳһ��ʽ��ʾ���飬��[exke]_xxx</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">{<br>
				"status":1,<br>
				 "msg":"",    #�쳣����<br>
				}<br>
				</td>
			</tr>
		</table><br>
		
		<!-- member -->
		<span class="method">1.3  ��Ȩĳ����Աʹ��APP</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="2">���е���</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">����·��</td><td colspan="3">/ExIAServer/services/base/security/grant/member</td></tr>
		</thead>
			<tr><td class="titleName">���÷�ʽ</td><td colspan="3">HTTP����</td></tr>
			<tr><td class="titleName">��������</td><td colspan="3">��Ȩĳ����Աʹ��APP���Ѿ���ע�Ļ�Ա������Ȩ</td></tr>
			<tr><td class="titleName" rowspan="3">������</td><td>������</td><td>��������</td><td>����˵��</td></tr>
			<tr><td>extoken</td><td>String</td><td>[�����ṩ]���ʷ��������</td></tr>
			<tr><td>memberId</td><td>String</td><td>[�����ṩ] ��ԱΨһ��ʶ</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">{<br>
				 "status":1,#�������ֵ�� -1���ʻ��Ѿ�����<br>
				 "msg":"", #�쳣����<br>
				 "data":   #���ؽ��<br>
				 {<br>
				    "menberId":"ע��ɹ��Ļ�ԱID",<br>
				    "appMenberId":"��Ա��עID"<br>
				 }<br>
				}<br>
				</td>
			</tr>
		</table><br>
		
	</div>
<script type="text/javascript" src="/ExIAServer/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/ExIAServer/js/interfaces.js"></script>
</body>
</html>