<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>��Ϣ��Դ��ȡ�ͼ����ӿ��ĵ�</title>
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
		  <legend>1.��Ϣ��Դ�����ͻ�ȡ</legend>
		</fieldset>
	
		<!-- getInformationsByUrlAndAttribute -->
		<span class="method">1.1 getInformationsByUrlAndAttribute</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">������</td><td colspan="3">getInformationsByUrlAndAttribute</td></tr>
		</thead>
			<tr><td class="titleName">��������</td><td colspan="3">����url����ȡ��Ϣ��Դ����</td></tr>
			<tr ><td class="titleName" rowspan="3">����</td><td >������</td><td>��������</td><td>����˵��</td></tr>
			<tr><td>url</td><td>String</td><td>�ο��ļ���Դ·��</td></tr>
			<tr><td>attribute</td><td>String</td><td>��Ϣ��Դ��������json�ַ���</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">��Ϣ��Դ����</td>
			</tr>
		</table>		
	
		<!-- �Զ������ -->
		<span class="method">1.2 �Զ������</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">������</td><td colspan="3">�������</td></tr>
		</thead>
			<tr><td class="titleName">��������</td><td colspan="3">����url����ȡ��Ϣ��Դ����</td></tr>
			<tr ><td class="titleName" rowspan="3">����</td><td >������</td><td>��������</td><td>����˵��</td></tr>
			<tr><td>mount</td><td>String</td><td>���ص�</td></tr>
			<tr><td>params</td><td>String</td><td>����������ַ�����json�ַ���</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">Json��ʽ����</td>
			</tr>
			<tr><td class="titleName">��������</td>
				<td colspan="3">����1<br>
				����·����<br>
				http://172.3.4.5:8080/ExKP/service/getKnowledge?id= concept_class/thing& condition={aaa:111}<br>
				//������<br>
				Id=" concept_class/thing",<br>
				condition.aaa=111<br>
				//js�ű�<br>
				var tt = id;<br>
				var ss = tt+condition.aaa<br>
				ss<br>
				//���ؽ����<br>
				concept_class/thing111<br>
				
				����2��<br>
				����·����<br>
				http://172.3.4.5:8080/ExKP/service/getKnowledge?text=[����Ժ]<br>
				//������<br>
				text =[����Ժ] <br>
				//Js�ű����ݣ�<br>
				var tt = JSON.parse(_searchKnowledgeService.getOntologiesByKeywords(text).toString());<br>
				var concepts = new Array();<br>
				for(var i = 0 ; i < tt.length; i++){<br>
					var temp = tt[i]['concepts'];<br>
					if(temp.length>1){<br>
						var concept = JSON.parse(_searchKnowledgeService.getConcept(temp[1]["id"]));<br>
						_out.println(concept)<br>
						concepts.push(concept);<br>
					}<br>
				}<br>
				concepts; <br>
				//���صĽ��<br>
				���ؽ���ṹ�������</td>
			</tr>
		</table>		
	
		<!-- searchText��ȫ�ļ����ӿڣ� -->
		<span class="method">1.3 searchText��ȫ�ļ����ӿڣ�</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">������</td><td colspan="3">searchText��ȫ�ļ����ӿڣ�</td></tr>
		</thead>
			<tr><td class="titleName">��������</td><td colspan="3">ȫ�ļ���֪ʶ��</td></tr>
			<tr ><td class="titleName" rowspan="3">����</td><td >������</td><td>��������</td><td>����˵��</td></tr>
			<tr><td>conditions</td><td>String</td><td>���ص�</td></tr>
			<tr><td>params</td><td>String</td><td>����������json�����ַ�����key���£�<br>
				//��ѯ�ʣ������ѯ���á�,���ָ�������<br>
				"text": ""<br>
				//��ǰҳ��������<br>
				"page":1<br>
				//ÿҳ����������<br>
				"pageSize":10<br>
				//����ʽ��ѡ��0����ض�����Ĭ�ϣ�,1�����ֶ�����<br>
				"sortType":1<br>
				//�����ֶΣ���sortTypeΪ1ʱ��Ч��Ĭ�ϰ����ֶ����������ֶ���ǰ���Թ��ԼӺ�'+'�����'-'���Ӻ�'+'��ʾ���򣬼���'-'��ʾ����<br>
				"sortField":"-bbrq"//���䲼���ڵ���<br>
				//��ѯ���ʶ����;���ָ���ѡ��<br>
				"infoSources":""<br>
				//���࣬��;���ָ���ѡ��<br>
				"categorys":""<br>
				//��ã���;���ָ���ѡ��<br>
				"genres":""<br>
				//��̬����;���ָ���ѡ��<br>
				"forms": ""<br>
				//���ţ���;���ָ���ѡ��<br>
				"symbols", ""<br>
				//�ƶ��������ƣ�ѡ��<br>
				"zdjg":""<br>
				//�䲼�ĺţ�ѡ��<br>
				"bbwh":""<br>
				//ʱЧ�ԣ�ѡ��<br>
				"sxx":""</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">Json�ַ���<br>
				{<br>
				"num": 627,<br>
				"page": 1,<br>
				"pageSize": 10,<br>
				<br>
				"informations": [<br>
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
				                "html_content": "�й�����������ίԱ������޸��ܷ��������ݵĽ���",<br>
				"relevance": "100%"<br>
				},<br>
				"viewPageUrl":"http: //law.npc.gov.cn/FLFG/flfgByID.action?fssc"<br>
				        },��.<br>
				    ]<br>
				}</td>
			</tr>
		</table>		
	
		<!-- getComplexUrl -->
		<span class="method">1.4 getComplexUrl</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">������</td><td colspan="3">getComplexUrl</td></tr>
		</thead>
			<tr><td class="titleName">��������</td><td colspan="3">��ȡ���ӵ���Դurl�������Ԫ��Ϊ��Դ·��+��Դ���ƣ�</td></tr>
			<tr ><td class="titleName" rowspan="2">����</td><td >������</td><td>��������</td><td>����˵��</td></tr>
			<tr><td>simpleUrl</td><td>String</td><td>�򵥵���Դurl�������Ԫ��Ϊid��</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">���ӵ���Դurl</td>
			</tr>
		</table>		
	
		<!-- getSimpleUrl -->
		<span class="method">1.5 getSimpleUrl</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">������</td><td colspan="3">getSimpleUrl</td></tr>
		</thead>
			<tr><td class="titleName">��������</td><td colspan="3">��ȡ�򵥵���Դurl�������Ԫ��Ϊid��</td></tr>
			<tr ><td class="titleName" rowspan="2">����</td><td >������</td><td>��������</td><td>����˵��</td></tr>
			<tr><td>complexUrl</td><td>String</td><td>���ӵ���Դurl�������Ԫ��Ϊ��Դ·��+��Դ���ƣ�</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">�򵥵���Դurl</td>
			</tr>
		</table>		
	
		<!-- absText -->
		<span class="method">1.6 absText</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">������</td><td colspan="3">absText</td></tr>
		</thead>
			<tr><td class="titleName">��������</td><td colspan="3">��ȡ�򵥵���Դurl�������Ԫ��Ϊid��</td></tr>
			<tr ><td class="titleName" rowspan="5">����</td><td >������</td><td>��������</td><td>����˵��</td></tr>
			<tr><td>text</td><td>String</td><td>�����ı�</td></tr>
			<tr><td>numOfSub</td><td>Integer</td><td>����ʸ�����Ĭ��5��</td></tr>
			<tr><td>percent</td><td>Integer</td><td>ժҪ����ռ���³��Ȱٷֱȣ�Ĭ��10%��ժҪ����</td></tr>
			<tr><td>dictName</td><td>String</td><td>ժҪ�ʵ����ƣ�Ϊ�ձ�ʾ����ϵͳĬ�ϴʵ䣩Ĭ�ϴʵ�Ϊ./ DICT/absdict;</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">json�ַ�������ʽΪ��{keywords:����ʣ�abstract:ժҪ}</td>
			</tr>
		</table>		
	</div>
</body>
</html>