<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>������Ϣ��Դ</title>
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
		  <legend typeVal="ontology.textsearch">4.������Ϣ��Դ</legend>
		</fieldset>
		
		<!--  -->
		<span class="method">4.1 ������Ҫ�ؼ�����Ϣ��Դ</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="0">���е���</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">����·��</td><td colspan="3">/ExIAServer/services/ontology/textsearch/byontology</td></tr>
		</thead>
			<tr><td class="titleName">���÷�ʽ</td><td colspan="3">HTTP����</td></tr>
			<tr><td class="titleName">��������</td><td colspan="3">���ݲ��û��ڡ���ϵͳ��Ҫ�ء�������֪ʶ���������Ϣ��Դ����</td></tr>
			<tr><td class="titleName" rowspan="8">������</td><td>������</td><td>��������</td><td>����˵��</td></tr>
			<tr><td>extoken</td><td>String</td><td>[�����ṩ]���ʷ��������</td></tr>
			<tr><td>context</td><td>String</td><td>[�����ṩ]�����Ĳ�����Ϣ��JSON�ַ����������ʽ�ο���context�����ĵ�JSON��ʽ��</td></tr>
			<tr><td>ontoOfReqJson</td><td>String</td><td>[�����ṩ] ���ڡ���ϵͳ��Ҫ�ء�������֪ʶ���󡣲ο������ڡ���ϵͳ��Ҫ�ء���֪ʶ�����ʶ��JSON��ʽ��</td></tr>
			<tr><td>infoSources</td><td>String[]</td><td>[��ѡ��]��Ϣ��Դ��Ĵ���</td></tr>
			<tr><td>infoYears</td><td>String</td><td>[��ѡ��] ����Ϣ��Դ���������ƣ���ʽ��[{"infoSource":"��Ϣ��Դ��Ĵ���","years":[2010,2011,2012,2013,2014,2015]},{"infoSource":"��Ϣ��Դ��Ĵ���","years":[2010,2011,2012,2013,2014,2015]}]</td></tr>
			<tr><td>page</td><td>Int</td><td>[��ѡ��]��ǰҳ�룬Ĭ��Ϊ1</td></tr>
			<tr><td>pageSize</td><td>Int</td><td>[��ѡ��]��ҳ��С��Ĭ��Ϊ50��</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">{<br>
				 "status":1,<br>
				 "msg":"",    #�쳣��ʾ��Ϣ<br>
				 "data":   #���ؽ��<br>
				   ��Ϣ��Դ��������ķ�ҳ���JSON��ʽ<br>
				 }<br>
				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method">4.2ȫ�ļ���SQL��������Դ</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="1">���е���</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">����·��</td><td colspan="3">/ExIAServer/services/ontology/textsearch/sql</td></tr>
		</thead>
			<tr><td class="titleName">���÷�ʽ</td><td colspan="3">HTTP����</td></tr>
			<tr><td class="titleName">��������</td><td colspan="3">����SQL������ȫ�ļ���</td></tr>
			<tr><td class="titleName" rowspan="7">������</td><td>������</td><td>��������</td><td>����˵��</td></tr>
			<tr><td>extoken</td><td>String</td><td>[�����ṩ]���ʷ��������</td></tr>
			<tr><td>context</td><td>String</td><td>[�����ṩ]�����Ĳ�����Ϣ��JSON�ַ����������ʽ�ο���context�����ĵ�JSON��ʽ��</td></tr>
			<tr><td>sql</td><td>String</td><td>[�����ṩ] ȫ�ļ�����SQL��䡣</td></tr>
			<tr><td>infoYears</td><td>String</td><td>[��ѡ��] ����Ϣ��Դ���������ƣ���ʽ��[{"infoSource":"��Ϣ��Դ��Ĵ���","years":[2010,2011,2012,2013,2014,2015]},{"infoSource":"��Ϣ��Դ��Ĵ���","years":[2010,2011,2012,2013,2014,2015]}]</td></tr>
			<tr><td>page</td><td>Int</td><td>[��ѡ��]��ǰҳ�룬Ĭ��Ϊ1</td></tr>
			<tr><td>pageSize</td><td>Int</td><td>[��ѡ��]��ҳ��С��Ĭ��Ϊ50��</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">{<br>
				 "status":1,<br>
				 "msg":"",    #�쳣����<br>
				 "data":   #���ؽ��<br>
				  ��Ϣ��Դ��������ķ�ҳ���JSON��ʽ,<br>
				}<br>
				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method">4.3����������ѯ��Ϣ��Դ</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="2">���е���</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">����·��</td><td colspan="3">/ExIAServer/services/ontology/textsearch/byontology</td></tr>
		</thead>
			<tr><td class="titleName">���÷�ʽ</td><td colspan="3">HTTP����</td></tr>
			<tr><td class="titleName">��������</td><td colspan="3">���ݲ��û��ڡ���ϵͳ��Ҫ�ء�������֪ʶ���������Ϣ��Դ����</td></tr>
			<tr><td class="titleName" rowspan="9">������</td><td>������</td><td>��������</td><td>����˵��</td></tr>
			<tr><td>extoken</td><td>String</td><td>[�����ṩ]���ʷ��������</td></tr>
			<tr><td>context</td><td>String</td><td>[�����ṩ]�����Ĳ�����Ϣ��JSON�ַ����������ʽ�ο���context�����ĵ�JSON��ʽ��</td></tr>
			<tr><td>resOfReqJson</td><td>String</td><td>[�����ṩ]��Ϣ��Դ��ѯ������JSON�ַ����������ʽ�ο���</td></tr>
			<tr><td>infoSources</td><td>String</td><td>[��ѡ��]��Ϣ��Դ��Ĵ���</td></tr>
			<tr><td>orderName</td><td>String</td><td>��ѡ��]�����ֶ����ƣ�title-��������publishDate-������������</td></tr>
			<tr><td>orderType</td><td>String</td><td>[��ѡ��]����ʽ��asc-����desc-����</td></tr>
			<tr><td>page</td><td>Int</td><td>[��ѡ��]��ǰҳ�룬Ĭ��Ϊ1</td></tr>
			<tr><td>pageSize</td><td>Int</td><td>[��ѡ��]��ҳ��С��Ĭ��Ϊ50��</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">{<br>
				 "status":1,<br>
				 "msg":"",    #�쳣����<br>
				 "data":   #���ؽ��<br>
				  ��Ϣ��Դ��������ķ�ҳ���JSON��ʽ,<br>
				}<br>
				</td>
			</tr>
		</table><br>
		
	</div>
<script type="text/javascript" src="/ExIAServer/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/ExIAServer/js/interfaces.js"></script>
</body>
</html>