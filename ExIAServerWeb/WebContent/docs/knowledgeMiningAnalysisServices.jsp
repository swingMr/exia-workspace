<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>֪ʶ�ھ�&���������÷���</title>
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
		  <legend typeVal="calculate.services">3. ֪ʶ�ھ�&���������÷���</legend>
		</fieldset>
		
		<!--  -->
		<span class="method">3.1 �����ı��ִ�</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="0">���е���</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">����·��</td><td colspan="3">/ExIAServer/services/calculate/participle</td></tr>
		</thead>
			<tr><td class="titleName">���÷�ʽ</td><td colspan="3">HTTP����</td></tr>
			<tr><td class="titleName">��������</td><td colspan="3">���ݷ������ƻ�ȡ�������ϸ��Ϣ</td></tr>
			<tr><td class="titleName" rowspan="5">������</td><td>������</td><td>��������</td><td>����˵��</td></tr>
			<tr><td>extoken</td><td>String</td><td>[�����ṩ]���ʷ��������</td></tr>
			<tr><td>text</td><td>String</td><td>[�����ṩ]��Ҫ�ִʵ��ı����ݡ�</td></tr>
			<tr><td>tagPart</td><td>boolean</td><td>[��ѡ��]�Ƿ��ע���ԣ�Ĭ��Ϊfalse</td></tr>
			<tr><td>patten</td><td>int</td><td>[��ѡ��] �ִ�ģʽ��Ĭ��Ϊ1��ģʽ�����1--��׼�ִʣ�2--�����ִʣ�3--ȫ�ִʣ�4--���ٷִʣ�5--CRF�ִʡ�</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">{<br>
				 "status":1,<br>
				 "msg":"",    #�쳣����<br>
				 "data":    #���ؽ��<br>
				   [<br>
				     {"word":��,"part":[����1, ����2]},<br>
				     {"word":��,"part":[����1, ����2]},<br>
				     {"word":��,"part":[����1, ����2]}<br>
				   ]<br>
				   
				  }<br>
				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method">3.2  �ؼ��ʺ�ժҪ��ȡ</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="1">���е���</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">����·��</td><td colspan="3">/ExIAServer/services/calculate/abstract</td></tr>
		</thead>
			<tr><td class="titleName">���÷�ʽ</td><td colspan="3">HTTP����</td></tr>
			<tr><td class="titleName">��������</td><td colspan="3">��ȡĳ���ı��Ĺؼ��ʺ�ժҪ</td></tr>
			<tr><td class="titleName" rowspan="6">������</td><td>������</td><td>��������</td><td>����˵��</td></tr>
			<tr><td>extoken</td><td>String</td><td>[�����ṩ]���ʷ��������</td></tr>
			<tr><td>title</td><td>String</td><td>[��ѡ��]�ı����ݶ�Ӧ���ļ����⣬���Բ��ṩ��</td></tr>
			<tr><td>text</td><td>String</td><td>[�����ṩ]��Ҫ��ȡ�ؼ��ʺ�ժҪ���ı����ݡ�</td></tr>
			<tr><td>keyWordLimit</td><td>int</td><td>[��ѡ��]���صĹؼ�����������Ĭ��Ϊ20��</td></tr>
			<tr><td>abstractLength</td><td>Int</td><td>[��ѡ��]���ص�ժҪ�ĳ��ȣ���������Ĭ��Ϊ250��</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">{<br>
				 "status":1,<br>
				 "msg":"",    #�쳣����<br>
				 "data":    #���ؽ��<br>
				  {<br>
				    "abstract":ժҪ����,<br>
				    "keywords":<br>
				    [<br>
				      {"word":�ؼ���," relevantLevel":��ض�},<br>
				      {"word":�ؼ���," relevantLevel":��ض�},<br>
				      {"word":�ؼ���," relevantLevel":��ض�}<br>
				    ]<br>
				  }<br>

				}<br>
				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method">3.3 �����ı����ݵı�ǩ</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="2">���е���</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">����·��</td><td colspan="3">/ExIAServer/services/calculate/gentag</td></tr>
		</thead>
			<tr><td class="titleName">���÷�ʽ</td><td colspan="3">HTTP����</td></tr>
			<tr><td class="titleName">��������</td><td colspan="3">����ָ���ı����ݵı�ǩ</td></tr>
			<tr><td class="titleName" rowspan="7">������</td><td>������</td><td>��������</td><td>����˵��</td></tr>
			<tr><td>extoken</td><td>String</td><td>[�����ṩ]���ʷ��������</td></tr>
			<tr><td>title</td><td>String</td><td>[��ѡ��]�ı����ݶ�Ӧ���ļ����⣬���Բ��ṩ��</td></tr>
			<tr><td>text</td><td>String</td><td>[�����ṩ]��Ҫ��ȡ�ؼ��ʺ�ժҪ���ı����ݡ�</td></tr>
			<tr><td>keyWordLimit</td><td>int</td><td>[��ѡ��]�Ƿ���ݱ���ʶ���������Ĭ��Ϊfalse</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">{<br>
				 "status":1,<br>
				 "msg":"",    #�쳣����<br>
				 "data":    #���ؽ��<br>
				  {<br>
				   "domains": [{"content":"�����","similarity":���ƶ�,"parentNames":[]}],<br>
				   "acts":[{"content":"��Ϊ��","similarity":���ƶ�,"parentNames":[]}}],<br>
				   "organdpersons":[{"content":"�����","similarity":���ƶ�,"parentNames":[]}}],<br>
				   "objects": [{"content":"�����","similarity":���ƶ�,"parentNames":[]}}],<br>
				   "spaces":[{"content":"�ռ��","similarity":���ƶ�,"parentNames":[]}}],<br>
				   "times":[{"content":"ʱ���","similarity":���ƶ�,"parentNames":[]}}],<br>
				   "keywords":[{"content":"�ؼ���","similarity":���ƶ�}]
				  }<br>


				}<br>
				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method">3.4  �������������ȡ�ؼ�����</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="3">���е���</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">����·��</td><td colspan="3">/ExIAServer/services/calculate/keypara</td></tr>
		</thead>
			<tr><td class="titleName">���÷�ʽ</td><td colspan="3">HTTP����</td></tr>
			<tr><td class="titleName">��������</td><td colspan="3">�������������ȡĳ���ı��Ĺؼ�����</td></tr>
			<tr><td class="titleName" rowspan="5">������</td><td>������</td><td>��������</td><td>����˵��</td></tr>
			<tr><td>extoken</td><td>String</td><td>[�����ṩ]���ʷ��������</td></tr>
			<tr><td>reqSubject</td><td>String</td><td>[�����ṩ] ���������json��ʽ��[{"content":"�����1","relevantLevel":��ض�},{"content":"�����2","relevantLevel":��ض�}]</td></tr>
			<tr><td>text</td><td>String</td><td>[�����ṩ]��Ҫ��ȡ�ؼ��ʺ�ժҪ���ı����ݡ�</td></tr>
			<tr><td>abstractLength</td><td>int</td><td>[��ѡ��]���عؼ�����ĳ��ȣ���������Ĭ��Ϊ250</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">{<br>
				 "status":1,<br>
				 "msg":"",    #�쳣����<br>
				 "data":    #���ؽ��<br>
				  �ؼ���������<br>

				}<br>
				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method">3.5 �ı�����</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="4">���е���</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">����·��</td><td colspan="3">/ExIAServer/services/calculate/classify</td></tr>
		</thead>
			<tr><td class="titleName">���÷�ʽ</td><td colspan="3">HTTP����</td></tr>
			<tr><td class="titleName">��������</td><td colspan="3">��ĳ���ı����ݽ��з���</td></tr>
			<tr><td class="titleName" rowspan="6">������</td><td>������</td><td>��������</td><td>����˵��</td></tr>
			<tr><td>extoken</td><td>String</td><td>[�����ṩ]���ʷ��������</td></tr>
			<tr><td>text</td><td>String</td><td>[�����ṩ]��Ҫ��ȡ�ؼ��ʺ�ժҪ���ı����ݡ�</td></tr>
			<tr><td>modelName</td><td>int</td><td>[�����ṩ]ģ��/ģ�����ƣ�����ģ�ͻ�����ģ�͡�</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">{<br>
				 "status":1,<br>
				 "msg":"",    #�쳣����<br>
				 "data":    #���ؽ��<br>
				 [<br>
				   {"word":������," relevantLevel":��ض�},<br>
				   {"word":������," relevantLevel":��ض�},<br>
				   {"word":������," relevantLevel":��ض�}<br>
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