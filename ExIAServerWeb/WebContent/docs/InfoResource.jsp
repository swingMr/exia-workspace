<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>��Ϣ��Դ</title>
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
		  <legend typeVal="resource">2. ��Ϣ��Դ</legend>
		</fieldset>
		
		<!--  -->
		<span class="method">2.1 ��ȡָ����Ϣ��Դ��������Ϣ</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="0">���е���</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">����·��</td><td colspan="3">/ExIAServer/services/res/{��Դ����}/{��ԴΨһID}/info</td></tr>
		</thead>
			<tr><td class="titleName">���÷�ʽ</td><td colspan="3">HTTP����</td></tr>
			<tr><td class="titleName">��������</td><td colspan="3">��ȡָ����Ϣ��Դ��������Ϣ</td></tr>
			<tr><td class="titleName" rowspan="3">������</td><td>������</td><td>��������</td><td>����˵��</td></tr>
			<tr><td>extoken</td><td>String</td><td>[�����ṩ]���ʷ��������</td></tr>
			<tr><td>context</td><td>String</td><td>[�����ṩ]�����Ĳ�����Ϣ��JSON�ַ����������ʽ�ο���context�����ĵ�JSON��ʽ��</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">{<br>
				 "status":1,<br>
				 "msg":"",    #�쳣����<br>
				 "data":    #���ؽ��<br>
				 {<br>
				    "id": 34174,<br>
				    "title": "�л����񹲺͹��ܷ�������",<br>
				    "url": "http: //law.npc.gov.cn/FLFG/flfgByID.action?flfgID=156&keyword=&zlsxid=08",<br>
				"attributes":{<br>
				   "content": "",<br>
				   "abstract": "",<br>
				   "genre": "",<br>
				   "symbol": "",<br>
				   "form": "",<br>
				   "keywords": "���ɹ涨;���徭����֯;�л����񹲺͹�;�ܷ�;��������",<br>
				   "belong": "wcmmetatableflfg",<br>
				   "bbrq": "1993-3-29",<br>
				   "textFileExt":"���ĸ�ʽ��HTML��DOC",<br>
				   },<br>
				   "viewPageUrl":"http: //law.npc.gov.cn/FLFG/flfgByID.action?fssc"<br>
				  } <br>


				  }<br>
				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method">2.2 ��ȡ��Ϣ��Դ����������</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="1">���е���</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">����·��</td><td colspan="3">/ExIAServer/services/res/{��Դ����}/{��ԴΨһID}/text</td></tr>
		</thead>
			<tr><td class="titleName">���÷�ʽ</td><td colspan="3">HTTP����</td></tr>
			<tr><td class="titleName">��������</td><td colspan="3">��ȡ��Ϣ��Դ����������</td></tr>
			<tr><td class="titleName" rowspan="3">������</td><td>������</td><td>��������</td><td>����˵��</td></tr>
			<tr><td>extoken</td><td>String</td><td>[�����ṩ]���ʷ��������</td></tr>
			<tr><td>context</td><td>String</td><td>[�����ṩ]�����Ĳ�����Ϣ��JSON�ַ����������ʽ�ο���context�����ĵ�JSON��ʽ��</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">{<br>
					"status":1,<br>
					 "msg":"",    #�쳣����<br>
					 "data":�������ݵ�������   #���ؽ��<br>
				}<br>
				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method">2.3  ��ȡ��Ϣ��Դָ���������ļ�����</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="2">���е���</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">����·��</td><td colspan="3">/ExIAServer/services/res/{��Դ����}/{��ԴΨһID}/attach/{����ID}</td></tr>
		</thead>
			<tr><td class="titleName">���÷�ʽ</td><td colspan="3">HTTP����</td></tr>
			<tr><td class="titleName">��������</td><td colspan="3">��ȡ��Ϣ��Դָ���������ļ�����</td></tr>
			<tr><td class="titleName" rowspan="3">������</td><td>������</td><td>��������</td><td>����˵��</td></tr>
			<tr><td>extoken</td><td>String</td><td>[�����ṩ]���ʷ��������</td></tr>
			<tr><td>context</td><td>String</td><td>[�����ṩ]�����Ĳ�����Ϣ��JSON�ַ����������ʽ�ο���context�����ĵ�JSON��ʽ��</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">{<br>
					"status":1,<br>
					 "msg":"",    #�쳣����<br>
					 "data":�������ݵ�������   #���ؽ��<br>
				}<br>
				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method">2.4  ��ȡ��Ϣ��Դ������</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="3">���е���</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">����·��</td><td colspan="3">/ExIAServer/services/res/count</td></tr>
		</thead>
			<tr><td class="titleName">���÷�ʽ</td><td colspan="3">HTTP����</td></tr>
			<tr><td class="titleName">��������</td><td colspan="3">��ȡ��Ϣ��Դ������</td></tr>
			<tr><td class="titleName" rowspan="4">������</td><td>������</td><td>��������</td><td>����˵��</td></tr>
			<tr><td>extoken</td><td>String</td><td>[�����ṩ]���ʷ��������</td></tr>
			<tr><td>lib</td><td>String</td><td>���������編�ɷ����</td></tr>
			<tr><td>channel</td><td>String</td><td>��Ŀ���������Ŀ�����á��ܷ�����ط�/�ܷ�����ʾ</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">{<br>
					 "status":1,<br>
					 "msg":"",    #�쳣����<br>
					 "data":    #���ؽ��<br>
					 {<br>
					   "count":10 <br>
					 }<br>

				}<br>
				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method">2.5  ͨ����Դurl��ȡ��Դ�б�</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="4">���е���</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">����·��</td><td colspan="3">/ExIAServer/services/res/resourceListByUrl</td></tr>
		</thead>
			<tr><td class="titleName">���÷�ʽ</td><td colspan="3">HTTP����</td></tr>
			<tr><td class="titleName">��������</td><td colspan="3">ͨ����Դurl��ȡ��Դ�б�</td></tr>
			<tr><td class="titleName" rowspan="5">������</td><td>������</td><td>��������</td><td>����˵��</td></tr>
			<tr><td>extoken</td><td>String</td><td>[�����ṩ]���ʷ��������</td></tr>
			<tr><td>url</td><td>String</td><td>[�����ṩ]��Դurl������http://<host>/wcm/��Դ��/���ɷ����/�ܷ�</td></tr>
			<tr><td>pageNo</td><td>int</td><td>[�����ṩ]</td></tr>
			<tr><td>pageSize</td><td>int</td><td>[�����ṩ]</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">{<br>
					 "status":1,<br>
					 "msg":"",    #�쳣����<br>
					 "data":    #���ؽ��<br>
					 {<br>
					  documents:[<br>
						  {<br>
							id: 1,<br>
							text: xxx,   // �ĵ�����<br>
							channelId: 3, // �ĵ��ĸ���Ŀid<br>
					        url: http://<host>/wcm/���ɷ����/�ܷ�/xxx��<br>
					        cruser: admin, // �ṩ��<br>
					        createTime: 2017-08-21 12:32:21 // ����ʱ��<br>
						  },<br>
						  ...<br>
						],<br>
					  total:20<br>
					 }<br>

				}<br>
				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method"> 2.6 ͨ����Դurl��ȡ��Դ����ϸ����</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="5">���е���</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">����·��</td><td colspan="3">/ExIAServer/services/res/text</td></tr>
		</thead>
			<tr><td class="titleName">���÷�ʽ</td><td colspan="3">HTTP����</td></tr>
			<tr><td class="titleName">��������</td><td colspan="3">ͨ����Դurl��ȡ��Դ�б�</td></tr>
			<tr><td class="titleName" rowspan="4">������</td><td>������</td><td>��������</td><td>����˵��</td></tr>
			<tr><td>extoken</td><td>String</td><td>[�����ṩ]���ʷ��������</td></tr>
			<tr><td>url</td><td>String</td><td>[�����ṩ]��Դurl������http://<host>/wcm/��Դ��/���ɷ����/�ܷ�</td></tr>
			<tr><td>attributes</td><td>String</td><td>��ʽΪ��{title: ��xxx��}</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">{<br>
					 outputstream���ͣ���ʽΪ��<br>
					{<br>
					 "status":1,<br>
					 "msg":"",    #�쳣����<br>
					 "data":    #���ؽ��<br>
					&lt;root&gt;&lt;htmlContent&gt;&lt;![CDATA[��ҳ����]]&gt;&lt;/htmlContent&gt;&lt;genre&gt;&lt;![CDATA[���]]&gt;&lt;/genre&gt;&lt;title&gt;��&lt;/title&gt;��. &lt;/root&gt;<br>
					}<br>

				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method">2.7 ͨ��������ȡ��Դ�б�</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="6">���е���</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">����·��</td><td colspan="3">/ExIAServer/services/res/resourceList</td></tr>
		</thead>
			<tr><td class="titleName">���÷�ʽ</td><td colspan="3">HTTP����</td></tr>
			<tr><td class="titleName">��������</td><td colspan="3">ͨ��������ȡ��Դ�б�</td></tr>
			<tr><td class="titleName" rowspan="8">������</td><td>������</td><td>��������</td><td>����˵��</td></tr>
			<tr><td>extoken</td><td>String</td><td>[�����ṩ]���ʷ��������</td></tr>
			<tr><td>id</td><td>int</td><td>[�����ṩ]�ڵ�id</td></tr>
			<tr><td>text</td><td>String</td><td>[�����ṩ]�ڵ�����</td></tr>
			<tr><td>type</td><td>String</td><td>[�����ṩ]�ڵ����ͣ�library��site��channel</td></tr>
			<tr><td>parentPath</td><td>String</td><td>[�����ṩ]�ýڵ�ĸ�·�������� ��Դ��/���ɷ����</td></tr>
			<tr><td>condition</td><td>String</td><td>����������������ʽΪ��{title: ��xxx��}</td></tr>
			<tr><td>pageNo</td><td>int</td><td>��ǰҳ</td></tr>
			<tr><td>pageSize</td><td>int</td><td>ÿҳ��������</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">{<br>
					 "status":1,<br>
					 "msg":"",    #�쳣����<br>
					 "data":    #���ؽ��<br>
					 {<br>
					  documents:[<br>
						  {<br>
							id: 1,<br>
							text: xxx,   // �ĵ�����<br>
							channelId: 3, // �ĵ��ĸ���Ŀid<br>
					        url: http://<host>/wcm/���ɷ����/�ܷ�/xxx��<br>
					        cruser: admin, // �ṩ��<br>
					        createTime: 2017-08-21 12:32:21 // ����ʱ��<br>
						  },<br>
						  ...<br>
						],<br>
					  total:20<br>
					 }<br>

				}<br>
				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method"> 2.8 �½���Դ</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="7">���е���</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">����·��</td><td colspan="3">/ExIAServer/services/res/createResource</td></tr>
		</thead>
			<tr><td class="titleName">���÷�ʽ</td><td colspan="3">HTTP����</td></tr>
			<tr><td class="titleName">��������</td><td colspan="3">�½���Դ</td></tr>
			<tr><td class="titleName" rowspan="6">������</td><td>������</td><td>��������</td><td>����˵��</td></tr>
			<tr><td>extoken</td><td>String</td><td>[�����ṩ]���ʷ��������</td></tr>
			<tr><td>channelId</td><td>int</td><td>[�����ṩ]��Ŀid</td></tr>
			<tr><td>parentPath</td><td>String</td><td>[�����ṩ]��Ŀ�ĸ�·�������� ��Դ��/���ɷ����</td></tr>
			<tr><td>title</td><td>String</td><td>[�����ṩ]��Դ����</td></tr>
			<tr><td>content</td><td>String</td><td>[�����ṩ]��Դ����</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">{<br>
					 "status":1,<br>
					 "msg":"",    #�쳣����<br>
					 "data":    #���ؽ��<br>
					 { <br>
					  id: 1, // �ѱ����id<br>
					  url: http://<host>/wcm/���ɷ����/�ܷ�/xxx��<br>
					}<br>

				}<br>
				</td>
			</tr>
		</table><br> 
		
		<!--  -->
		<span class="method"> 2.9 ������Դ����</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="8">���е���</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">����·��</td><td colspan="3">/ExIAServer/services/res/updateResource</td></tr>
		</thead>
			<tr><td class="titleName">���÷�ʽ</td><td colspan="3">HTTP����</td></tr>
			<tr><td class="titleName">��������</td><td colspan="3">������Դ����</td></tr>
			<tr><td class="titleName" rowspan="6">������</td><td>������</td><td>��������</td><td>����˵��</td></tr>
			<tr><td>extoken</td><td>String</td><td>[�����ṩ]���ʷ��������</td></tr>
			<tr><td>channelId</td><td>int</td><td>[�����ṩ]��Ŀid</td></tr>
			<tr><td>docId</td><td>int</td><td>[�����ṩ]��Դid</td></tr>
			<tr><td>libName</td><td>String</td><td>[�����ṩ]��������Դ�⡢���ֿ⣩</td></tr>
			<tr><td>attributes</td><td>String</td><td>[�����ṩ]Ҫ���µ���Դ���ԣ�json��ʽ��{genre: ����á�}</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">{<br>
					 "status":1,<br>
 					"msg":""    #�쳣����<br>

					}<br>

				}<br>
				</td>
			</tr>
		</table><br> 
		
		<!--  -->
		<span class="method">2.10  ��ȡ��ԴĿ¼</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="9">���е���</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">����·��</td><td colspan="3">/ExIAServer/services/res/catalog</td></tr>
		</thead>
			<tr><td class="titleName">���÷�ʽ</td><td colspan="3">HTTP����</td></tr>
			<tr><td class="titleName">��������</td><td colspan="3">��ȡ��ԴĿ¼</td></tr>
			<tr><td class="titleName" rowspan="4">������</td><td>������</td><td>��������</td><td>����˵��</td></tr>
			<tr><td>extoken</td><td>String</td><td>[�����ṩ]���ʷ��������</td></tr>
			<tr><td>url</td><td>String</td><td>��Դurl������http://<host>/wcm/��Դ��/���ɷ����/�ܷ�?article=xxx</td></tr>
			<tr><td>layer</td><td>int</td><td>Ŀ¼չ�����ڼ��㣻����������������������ȡ�������ȣ���Ҷ����Ŀ�������ֵΪnull����ȡ����������������ֵ���������ڻ��ߵ���0�����Ϊ0�򷵻�""</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">{<br>
					 "status":1,<br>
					 "msg":"",    #�쳣����<br>
					 "data":    #���ؽ��<br>
					 {<br>
					    sites: [{<br>
							site1: { // վ�㣨�⣩<br>
							  id: xx,<br>
							  text: xx,<br>
							  children: [<br>
								channel1:{<br>
							  	  id: xx,<br>
								  text: xx,<br>
								  children: [<br>
									document1: {<br>
									  id: xx,<br>
									  text: xx,<br>
					                  url: xx, <br>
					                  cruser: admin, // �ṩ��<br>
					                  createTime: 2017-08-21 12:32:21 // ����ʱ��<br>
									},<br>
									document2: {},<br>
									...<br>
								]},<br> 
								channel2:{},<br>
								...<br>
							]},<br>
							site2: {},<br>
							...<br>
						}],<br>
						total:10, // ��������<br>
					  }<br>

				}<br>
				</td>
			</tr>
		</table><br> 
		
		<!--  -->
		<span class="method"> 2.11 ��ȡ��������ӳ�伯��</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="10">���е���</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">����·��</td><td colspan="3">/ExIAServer/services/res/attributeMappings</td></tr>
		</thead>
			<tr><td class="titleName">���÷�ʽ</td><td colspan="3">HTTP����</td></tr>
			<tr><td class="titleName">��������</td><td colspan="3">��ȡ��������ӳ�伯��</td></tr>
			<tr><td class="titleName" rowspan="2">������</td><td>������</td><td>��������</td><td>����˵��</td></tr>
			<tr><td>extoken</td><td>String</td><td>[�����ṩ]���ʷ��������</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">{<br>
					 "status":1,<br>
					 "msg":"",    #�쳣����<br>
					 "data":    #���ؽ��<br>
					 {<br>
					  title: ����,<br>
					  content: ���Ĵ��ı�,<br>
					  ...<br>
					 }<br>


					}<br>

				}<br>
				</td>
			</tr>
		</table><br> 
		
		<!--  -->
		<span class="method"> 2.12	ͨ��url��ȡ��Դ�����Լ���</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="11">���е���</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">����·��</td><td colspan="3">/ExIAServer/services/res/attributes</td></tr>
		</thead>
			<tr><td class="titleName">���÷�ʽ</td><td colspan="3">HTTP����</td></tr>
			<tr><td class="titleName">��������</td><td colspan="3">��ȡ��������ӳ�伯��</td></tr>
			<tr><td class="titleName" rowspan="3">������</td><td>������</td><td>��������</td><td>����˵��</td></tr>
			<tr><td>extoken</td><td>String</td><td>[�����ṩ]���ʷ��������</td></tr>
			<tr><td>url</td><td>String</td><td>[�����ṩ] ��Դurl������http://<host>/wcm/��Դ��/���ɷ����/�ܷ�?article=xxx</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">{<br>
					 "status":1,<br>
					 "msg":"",    #�쳣����<br>
					 "data":    #���ؽ��<br>
					{resourceName:��Դ����, attributes:{{genre:���}, {form:��ʽ}, ...}}<br>

					}<br>

				}<br>
				</td>
			</tr>
		</table><br> 
		
		<!--  -->
		<span class="method"> 2.13 ��ȡÿ���Ƽ�����</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="12">���е���</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">����·��</td><td colspan="3">/ExIAServer/services/res/getDailyPushData</td></tr>
		</thead>
			<tr><td class="titleName">���÷�ʽ</td><td colspan="3">HTTP����</td></tr>
			<tr><td class="titleName">��������</td><td colspan="3">��ȡÿ���Ƽ�����</td></tr>
			<tr><td class="titleName" rowspan="5">������</td><td>������</td><td>��������</td><td>����˵��</td></tr>
			<tr><td>extoken</td><td>String</td><td>[�����ṩ]���ʷ��������</td></tr>
			<tr><td>memberId</td><td>String</td><td>[�����ṩ] ��Աid</td></tr>
			<tr><td>page</td><td>Int</td><td>[��ѡ��]��ǰҳ�룬Ĭ��Ϊ1��</td></tr>
			<tr><td>pageSize</td><td>Int</td><td>[��ѡ��]��ҳ��С��Ĭ��Ϊ8��</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">{<br>
					  "status":1,<br>
					 "msg":"",    #�쳣��ʾ��Ϣ<br>
					 "data":   #���ؽ��<br>
					   ��Ϣ��Դ��������ķ�ҳ���JSON��ʽ<br>

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