<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>ʶ��֪ʶ����</title>
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
		  <legend typeVal="ontology.recognise">1.ʶ��֪ʶ����</legend>
		</fieldset>
		
		<!--  -->
		<span class="method">1.1 ���ı���ʶ��֪ʶ��������</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="0">���е���</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">����·��</td><td colspan="3">/ExIAServer/services/ontology/recognise/concepts</td></tr>
		</thead>
			<tr><td class="titleName">���÷�ʽ</td><td colspan="3"></td></tr>
			<tr><td class="titleName">��������</td><td colspan="3">��һ���ı�ʶ�����֪ʶ����Ϊ��﷽ʽ��֪ʶ����</td></tr>
			<tr><td class="titleName" rowspan="7">������</td><td>������</td><td>��������</td><td>����˵��</td></tr>
			<tr><td>extoken</td><td>String</td><td>[�����ṩ]���ʷ��������</td></tr>
			<tr><td>context</td><td>String</td><td>[�����ṩ]�����Ĳ�����Ϣ��JSON�ַ����������ʽ�ο���context�����ĵ�JSON��ʽ��</td></tr>
			<tr><td>title</td><td>String</td><td>[�����ṩ]�ļ�����</td></tr>
			<tr><td>keyWord</td><td>String[]</td><td>[��ѡ��]���������</td></tr>
			<tr><td>text</td><td>String</td><td>[��ѡ��]�ı�����</td></tr>
			<tr><td>clsNames</td><td>String[]</td><td>[��ѡ��]�޶���Ҫ�����ͣ�Ϊ��ʱ�����޶��������磺������Ϊ����Ȼ�ˡ����ˡ�ʱ�䡢�ռ䡢���⡢ָ�ꡢ�ؼ��ʵȡ�</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">{<br>
				 "status":1,<br>
				 "msg":"",    #�쳣����<br>
				 "data":   #���ؽ��<br>
				 {<br>
				  �ο������ڡ���ϵͳ��Ҫ�ء���֪ʶ�����ʶ��JSON��ʽ��<br>
				 }<br>
				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method">1.2 ����HTML�����ı����ݵı���</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="1">���е���</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">����·��</td><td colspan="3">/ExIAServer/services/ontology/recognise/docIndex</td></tr>
		</thead>
			<tr><td class="titleName">���÷�ʽ</td><td colspan="3"></td></tr>
			<tr><td class="titleName">��������</td><td colspan="3">����HTML�����ı����ݵı���</td></tr>
			<tr><td class="titleName" rowspan="6">������</td><td>������</td><td>��������</td><td>����˵��</td></tr>
			<tr><td>extoken</td><td>String</td><td>[�����ṩ]���ʷ��������</td></tr>
			<tr><td>context</td><td>String</td><td>[�����ṩ]�����Ĳ�����Ϣ��JSON�ַ����������ʽ�ο���context�����ĵ�JSON��ʽ��</td></tr>
			<tr><td>text</td><td>String</td><td>[�����ṩ]�ı�����</td></tr>
			<tr><td>htmlClazz</td><td>String[]</td><td>[��ѡ��]����html���ݵı�ǩ���ͣ�Ĭ��ia-link</td></tr>
			<tr><td>words</td><td>String</td><td>[��ѡ��] �Զ�������ʻ㣬json���飬��ʽ���£� {"name��:�����ơ�,"type��:����������}</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">{<br>
				"status":1,<br>
				 "msg":"",    #�쳣����<br>
				 "data":   #���ؽ��<br>
				 {#�����������<br>
				  htmlContent  #����<br>
				 }<br>
				}<br>
				��Ա����еĴʣ����ʽΪ��&lt;span class="{ htmlClazz }|ia-link" data-id="{����id}" data-content="{��������}"  data-type="{���������}" data-parentNames="{�����������;�ָ�}">{��������}&lt;/span&gt;<br>
				</td>
			</tr>
		</table><br>
		
	</div>
<script type="text/javascript" src="/ExIAServer/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/ExIAServer/js/interfaces.js"></script>
</body>
</html>