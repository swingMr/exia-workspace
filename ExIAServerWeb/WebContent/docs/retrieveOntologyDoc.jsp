<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>����֪ʶ����</title>
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
		  <legend typeVal="ontology.search">2.����֪ʶ����</legend>
		</fieldset>
		
		<!--  -->
		<span class="method">2.1 ��ȡ��������������嵥</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="0">���е���</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">����·��</td><td colspan="3">/ExIAServer/services/ontology/search/domains</td></tr>
		</thead>
			<tr><td class="titleName">���÷�ʽ</td><td colspan="3">HTTP����</td></tr>
			<tr><td class="titleName">��������</td><td colspan="3">��ȡ���д������ָ��������������嵥</td></tr>
			<tr><td class="titleName" rowspan="3">������</td><td>������</td><td>��������</td><td>����˵��</td></tr>
			<tr><td>extoken</td><td>String</td><td>[�����ṩ]���ʷ��������</td></tr>
			<tr><td>domainName</td><td>String</td><td>[��ѡ��]�������ơ����ò���Ϊ�գ���ô���������嵥</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">{<br>
				 {<br>
					 "status":1,#�������ֵ<br>
					 "msg":"",    #�쳣����<br>
					 "data":   #���ؽ��<br>
					  [<br>
					    {"id":ID,"content":��������,"parentNames":[],"type":""," ALIAS":�������},<br>
					    {"id":ID,"content":��������,"parentNames":[],"type":""," ALIAS":�������},<br>
					    {"id":ID,"content":��������,"parentNames":[],"type":""," ALIAS":�������}<br>
					  ]<br>

				}<br>
				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method">2.2 ��ȡָ�������µ����йؼ���</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="1">���е���</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">����·��</td><td colspan="3">/ExIAServer/services/ontology/search/keywords</td></tr>
		</thead>
			<tr><td class="titleName">���÷�ʽ</td><td colspan="3">HTTP����</td></tr>
			<tr><td class="titleName">��������</td><td colspan="3">��ȡָ����������йؼ���</td></tr>
			<tr><td class="titleName" rowspan="4">������</td><td>������</td><td>��������</td><td>����˵��</td></tr>
			<tr><td>extoken</td><td>String</td><td>[�����ṩ]���ʷ��������</td></tr>
			<tr><td>domainName</td><td>String</td><td>[�����ṩ]��������</td></tr>
			<tr><td>limit</td><td>int</td><td>[��ѡ��]���صĹؼ���������Ĭ��200����</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">{<br>
				"status":1,#�������ֵ<br>
				 "msg":"",    #�쳣����<br>
				 "data":   #���ؽ��<br>
				  [<br>
				    {"content":�ؼ���," confidenceLevel":���Ŷ�},<br>
				    {"content":�ؼ���," confidenceLevel":���Ŷ�},<br>
				    {"content":�ؼ���," confidenceLevel":���Ŷ�}<br>
				  ]<br>

				}<br>
				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method">2.3 ��ȡָ�������µĸ����嵥</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="2">���е���</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">����·��</td><td colspan="3">/ExIAServer/services/ontology/search/concepts</td></tr>
		</thead>
			<tr><td class="titleName">���÷�ʽ</td><td colspan="3">HTTP����</td></tr>
			<tr><td class="titleName">��������</td><td colspan="3">��ȡָ����������йؼ���</td></tr>
			<tr><td class="titleName" rowspan="6">������</td><td>������</td><td>��������</td><td>����˵��</td></tr>
			<tr><td>extoken</td><td>String</td><td>[�����ṩ]���ʷ��������</td></tr>
			<tr><td>domainName</td><td>String</td><td>[�����ṩ]�������ơ�</td></tr>
			<tr><td>parentNames</td><td>String[]</td><td>[�����ṩ]���˸���ĸ��������顣</td></tr>
			<tr><td>type</td><td>String</td><td>[��ѡ��]��������ͣ���-- clazz��ʵ��-- concrete��</td></tr>
			<tr><td>limit</td><td>int</td><td>[��ѡ��]���صĸ���������Ĭ��100����</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">{<br>
				  "status":1,<br>
				 "msg":"",    #�쳣����<br>
				 "data":   #���ؽ��<br>
				  [<br>
				    {"id":ID,"content":��������,"parentNames":[],"type":""," ALIAS":����, "definition":�����,"synonyms":ͬ���,"attributes":���Զ��弰����ֵ},<br>
				    {"id":ID,"content":��������,"parentNames":[],"type":""," ALIAS":����, "definition":�����,"synonyms":ͬ���,"attributes":���Զ��弰����ֵ},<br>
				    {"id":ID,"content":��������,"parentNames":[],"type":""," ALIAS":����, "definition":�����,"synonyms":ͬ���,"attributes":���Զ��弰����ֵ}<br>
				  ]<br>

				}<br>
				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method">2.4 ���ݸ������ƻ�ȡ�������</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="3">���е���</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">����·��</td><td colspan="3">/ExIAServer/services/ontology/search/conceptsByNames</td></tr>
		</thead>
			<tr><td class="titleName">���÷�ʽ</td><td colspan="3">HTTP����</td></tr>
			<tr><td class="titleName">��������</td><td colspan="3">��ָ֤����Ա��¼����</td></tr>
			<tr><td class="titleName" rowspan="3">������</td><td>������</td><td>��������</td><td>����˵��</td></tr>
			<tr><td>extoken</td><td>String</td><td>[�����ṩ]���ʷ��������</td></tr>
			<tr><td>conceptNames</td><td>String[]</td><td>[�����ṩ]��������/ͬ������顣</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">{<br>
				  "status":1,<br>
				 "msg":"",    #�쳣����<br>
				 "data":   #���ؽ��<br>
				  [<br>
				    {"id":ID,"content":��������,"parentNames":[],"type":"","ALIAS":����, "definition":�����,"synonyms":ͬ���,"attributes":���Զ��弰����ֵ},<br>
				    {"id":ID,"content":��������,"parentNames":[],"type":"","ALIAS":����, "definition":�����,"synonyms":ͬ���,"attributes":���Զ��弰����ֵ},<br>
				    {"id":ID,"content":��������,"parentNames":[],"type":"","ALIAS":����, "definition":�����,"synonyms":ͬ���,"attributes":���Զ��弰����ֵ}<br>
				  ]<br>

				}<br>
				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method">2.5 ��ȡָ���������ظ���</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="4">���е���</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">����·��</td><td colspan="3">/ExIAServer/services/ontology/search/relevance</td></tr>
		</thead>
			<tr><td class="titleName">���÷�ʽ</td><td colspan="3">HTTP����</td></tr>
			<tr><td class="titleName">��������</td><td colspan="3">��ȡָ��������صĸ���</td></tr>
			<tr><td class="titleName" rowspan="8">������</td><td>������</td><td>��������</td><td>����˵��</td></tr>
			<tr><td>extoken</td><td>String</td><td>[�����ṩ]���ʷ��������</td></tr>
			<tr><td>srcIds</td><td>String</td><td>[�����ṩ] Դ����ID���顣</td></tr>
			<tr><td>relationDefIds</td><td>String</td><td>[��ѡ��]��ϵ����ID����,���Ϊ�գ������ƹ�ϵ��</td></tr>
			<tr><td>direction</td><td>String</td><td>[��ѡ��]��ϵ���򣬵�������������Ϊ��ϵʱ��Ч����ѡֵΪinbound,outbound,any��Ĭ��any��</td></tr>
			<tr><td>objParentNames</td><td>String[]</td><td>[��ѡ��]Ŀ��������������,���Ϊ�գ������Ƹ��ࡣ</td></tr>
			<tr><td>objType</td><td>String</td><td>[��ѡ��]Ŀ��������ͣ�clazz-�࣬concrete-ʵ����,���Ϊ�գ������Ƹ������͡�</td></tr>
			<tr><td>objLimit</td><td>Int</td><td>[��ѡ��]����Ŀ����������������Ĭ��Ϊ30��</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">{<br>
				  "status":1,<br>
				 "msg":"",    #�쳣����<br>
				 "data":   #���ؽ��<br>
				  [<br>
				    {"id":ID,"content":��������,"parentNames":[],"type":"","ALIAS":����, "definition":�����,"synonyms":ͬ���," relevantLevel":��ض�},<br>
				    {"id":ID,"content":��������,"parentNames":[],"type":"","ALIAS":����, "definition":�����,"synonyms":ͬ���," relevantLevel":��ض�},<br>
				    {"id":ID,"content":��������,"parentNames":[],"type":"","ALIAS":����, "definition":�����,"synonyms":ͬ���," relevantLevel":��ض�}<br>
				  ]<br>

				}<br>
				</td>
			</tr>
		</table><br>
		
	</div>
<script type="text/javascript" src="/ExIAServer/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/ExIAServer/js/interfaces.js"></script>
</body>
</html>