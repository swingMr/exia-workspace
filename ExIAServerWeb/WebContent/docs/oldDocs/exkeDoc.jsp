<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>֪ʶ���������ĵ�</title>
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
		  <legend>1.����</legend>
		</fieldset>
		<blockquote class="layui-elem-quote">
		        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;֪ʶ�����ǻ���JAVAƽ̨���п����ģ��������õĿ�ƽ̨���ԡ�����Ҫ���ܰ���ʶ����ȣ��������壬�������ȣ������´ʡ�֪ʶ������http��ʽ�����ṩ��������Ҫ�ӿ��л�ȡ�������ϸ���ݣ�ͨ������ʻ�ȡ����ϣ���ȡ��ر��壬��ȡ�������ͨ·�������ҹ����ĵ�������������б�......
		���ĵ������˾���֪ʶ����ϵͳ�����ṩ�Ľӿںͽ��������ϵͳ�Ľӿڹ淶������ͨ���Ķ����ӿ��ĵ��������������ĵ�Ԥ�ڵĶ���Ϊ������Ӧ�ÿ�����Ա��ϵͳ������Ա����ؼ�����Ա�ȡ�
		</blockquote>
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
		  <legend>2.�����ṩ�Ľӿ�</legend>
		</fieldset>
		<span class="subHeand">2.1 ֪ʶ���������ѯ</span><br><br>
		<blockquote class="layui-elem-quote">����·����
		����:�˿�/ExKP/searchKnowledge.do?action=������&��������
		<br>�磺<br>
		http://localhost:8080/ExKP/searchKnowledge.do?action=getConcept&id=concept_concrete/55795F3A-D6BC-44E1-C889-60418266B359</blockquote><br>
		
		<!-- getConcept -->
		<span class="method">2.1.1 getConcept</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">������</td><td colspan="3">getConcept</td></tr>
		</thead>
			<tr><td class="titleName">��������</td><td colspan="3">��ȡĳ���������ϸ���ݡ�</td></tr>
			<tr ><td class="titleName" rowspan="2">������</td><td>������</td><td>��������</td><td>����˵��</td></tr>
			<tr><td>id</td><td>String</td><td>����id</td></tr>
		</table>
		
		<!-- getOntologiesByKeywords -->
		<span class="method">2.1.2 getOntologiesByKeywords</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">������</td><td colspan="3">getOntologiesByKeywords</td></tr>
		</thead>
			<tr><td class="titleName">��������</td><td colspan="3">ͨ������ʻ�ȡ���弯�ϡ�</td></tr>
			<tr ><td class="titleName" rowspan="2">����</td><td >������</td><td >��������</td><td>����˵��</td></tr>
			<tr><td>word</td><td>String</td><td>�����json�ַ������飬��[������Ժ��,����������]</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">������ر��弯�ϣ������+��ϵ���ϣ�����ο�3.1��3.2
				��ṹ���£�
				[{
				��concepts��:<a href="#�����">�����</a>,
				��relations��:<a href="#��ϵ����">��ϵ����</a>
				}]</td>
			</tr>
			<tr><td class="titleName">����</td><td colspan="3">�����뿴3.1<a href="#�����">�����</a>��3.2<a href="#��ϵ����">��ϵ����</a></td></tr>
		</table>
		
		<!-- getOntologies -->
		<span class="method">2.1.3 getOntologies</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">������</td><td colspan="3">getOntologies</td></tr>
		</thead>
			<tr><td class="titleName">��������</td><td colspan="3">��ȡ��ر���</td></tr>
			<tr ><td class="titleName" rowspan="4">����</td><td >������</td><td >��������</td><td>����˵��</td></tr>
			<tr><td>conceptId</td><td>String</td><td>����id</td></tr>
			<tr><td>conditions</td><td>String</td><td>����������json�ַ������飬����ʱ���������е�����˳����м�������������������������������һ��������������������Թ�ϵ�ģ��ڶ���������������Ը���ģ���Ӹ���������ȸ��ݵ�һ����ϵ���������͵����������������������չһ�㣬Ȼ����ݵڶ�����ϵ��������������չһ�㣬������ÿһ�����
				elementType - �����������ͣ������ϵ������ѡֵΪconcept,relation
				type - �������� clazz/concrete
				direction - ��ϵ���򣬵�������������Ϊ��ϵʱ��Ч����ѡֵΪinbound,outbound,any
				content - �������ƣ���������������Ϊ����ʱ��Ч
				���elementTypeΪrelationʱ��contentΪ��ϵ�����������ơ�
				parentIds - ��������id����
				parentNames - ��ϵ���ͻ������������
				attributes - �������飬����ÿһ�����
				   Name - ������,�硱�Ա�
				   Value - ����ֵ������ֵ���飬��[���С�,��Ů��]
				   Operator - �������ţ���>,<,=,>=,like...
				   arrayPreOperator - ����������� any,all,none</td></tr>
   			<tr><td>level</td><td>int</td><td>֪ʶչ���㼶��Ĭ��Ϊ1</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">������ر��弯�ϣ������+��ϵ���ϣ�����ο�3.1��3.2
				��ṹ���£�
				[{
				��concepts��:<a href="#�����">�����</a>,
				��relations��:<a href="#��ϵ����">��ϵ����</a>
				}]</td>
			</tr>
			<tr><td class="titleName">����</td><td colspan="3">�����뿴3.1<a href="#�����">�����</a>��3.2<a href="#��ϵ����">��ϵ����</a></td></tr>
		</table>
		
				<!-- getOntologiesByIds -->
		<span class="method">2.1.4 getOntologiesByIds</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">������</td><td colspan="3">getOntologiesByIds</td></tr>
		</thead>
			<tr><td class="titleName">��������</td><td colspan="3">��ȡid��ر��弯��</td></tr>
			<tr ><td class="titleName" rowspan="2">����</td><td >������</td><td >��������</td><td>����˵��</td></tr>
			<tr><td>ids</td><td>String</td><td>id���飬��������ÿһ�������������id����[��xxx��,��xxxx��]</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">���ر��弯�ϣ����и���ϣ�����ο�3.1</td>
			</tr>
			<tr><td class="titleName">����</td><td colspan="3">�����뿴3.1<a href="#�����">�����</a></td></tr>
		</table>
		
				<!-- getOntologies -->
		<span class="method">2.1.5 getRoute(�ݲ�����)</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">������</td><td colspan="3">getRoute</td></tr>
		</thead>
			<tr><td class="titleName">��������</td><td colspan="3">��ȡ�������ͨ·��</td></tr>
			<tr ><td class="titleName" rowspan="4">����</td><td >������</td><td >��������</td><td>����˵��</td></tr>
			<tr><td>conceptId1</td><td>String</td><td>����id</td></tr>
			<tr><td>conceptId2</td><td>String</td><td>����id</td></tr>
			<tr><td>conditions</td><td>String</td><td>����������json�ַ������飬����ʱ���������е�����˳����м�������������������������������һ��������������������Թ�ϵ�ģ��ڶ���������������Ը���ģ���Ӹ���������ȸ��ݵ�һ����ϵ���������͵����������������������չһ�㣬Ȼ����ݵڶ�����ϵ��������������չһ�㣬������ÿһ�����
				elementType - �����������ͣ������ϵ������ѡֵΪconcept,relation
				type - �������� clazz/concrete
				direction - ��ϵ���򣬵�������������Ϊ��ϵʱ��Ч����ѡֵΪinbound,outbound,any
				content - �������ƣ���������������Ϊ����ʱ��Ч
				���elementTypeΪrelationʱ��contentΪ��ϵ�����������ơ�
				parentIds - ��������id����
				parentNames - ��ϵ���ͻ������������
				attributes - �������飬����ÿһ�����
				   Name - ������,�硱�Ա�
				   Value - ����ֵ������ֵ���飬��[���С�,��Ů��]
				   Operator - �������ţ���>,<,=,>=,like...
				   arrayPreOperator - ����������� any,all,none</td></tr>
			<tr><td class="titleName">����ֵ</td> 
				<td colspan="3">������ر��弯�ϣ������+��ϵ���ϣ�����ο�3.1��3.2
				��ṹ���£�
				[{
				��concepts��:<a href="#�����">�����</a>,
				��relations��:<a href="#��ϵ����">��ϵ����</a>
				}]</td>
			</tr>
			<tr><td class="titleName">����</td><td colspan="3">�����뿴3.1<a href="#�����">�����</a>��3.2<a href="#��ϵ����">��ϵ����</a></td></tr>
		</table>
		
		<!-- getInformationsByIdsAndCondition -->
		<span class="method">2.1.6 getInformationsByIdsAndCondition</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">������</td><td colspan="3">getOntologiesByIds</td></tr>
		</thead>
			<tr><td class="titleName">��������</td><td colspan="3">ͨ��������ҹ�����Ϣ</td></tr>
			<tr ><td class="titleName" rowspan="3">����</td><td >������</td><td >��������</td><td>����˵��</td></tr>
			<tr><td>ids</td><td>String</td><td>����json�������飬���������������id����["xxxx","xxxx"]</td></tr>
			<tr><td>infoCondition</td><td>String</td><td>��Ϣ��Դ��������json�ַ���</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">���ع�����Ϣ��������ο�3.3</td>
			</tr>
			<tr><td class="titleName">����</td><td colspan="3">�������ʵ��
				ids��["concept_concrete/D83C32D8-F485-1BFB-619B-39C26D9A7458"]
				infoCondition��{"����":"����","type":"WCMģ������","�ܷ�":"�⽻"}
				���ؽ��ʵ����
				�����뿴3.3<a href="#������Ϣ����">������Ϣ����</a></td></tr>
		</table>
		
		<!-- getInformationsByKeywordsAndCondition -->
		<span class="method">2.1.7 getInformationsByKeywordsAndCondition</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">������</td><td colspan="3">getInformationsByKeywordsAndCondition</td></tr>
		</thead>
			<tr><td class="titleName">��������</td><td colspan="3">ͨ���ؼ��ʲ��ҹ����ĵ�</td></tr>
			<tr ><td class="titleName" rowspan="3">����</td><td >������</td><td >��������</td><td>����˵��</td></tr>
			<tr><td>words</td><td>String</td><td>�����json���飬��["����Ժ","��������"}]</td></tr>
			<tr><td>infoCondition</td><td>String</td><td>��Ϣ��Դ��������json�ַ���</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">���ع�����Ϣ��������ο�3.3</td>
			</tr>
			<tr><td class="titleName">����</td><td colspan="3">�������ʵ��
				words:["�⽻����","��������"]
				infoCondition: {"type":"WCMģ������","�ܷ�":"�⽻","���":"����"}
				���ؽ��ʵ����
				�����뿴3.3<a href="#������Ϣ����">������Ϣ����</a></td></tr>
		</table>
		
		<!-- getInformations -->
		<span class="method">2.1.8 getInformations</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">������</td><td colspan="3">getInformationsByKeywordsAndCondition</td></tr>
		</thead>
			<tr><td class="titleName">��������</td><td colspan="3">���ҹ����ĵ�</td></tr>
			<tr ><td class="titleName" rowspan="4">����</td><td >������</td><td >��������</td><td>����˵��</td></tr>
			<tr><td>conceptId</td><td>String</td><td>����id</td></tr>
			<tr><td>conditions</td><td>String</td><td>����������json�ַ������飬����ʱ���������е�����˳����м�������������������������������һ��������������������Թ�ϵ�ģ��ڶ���������������Ը���ģ���Ӹ���������ȸ��ݵ�һ����ϵ���������͵����������������������չһ�㣬Ȼ����ݵڶ�����ϵ��������������չһ�㣬������ÿһ�����
				elementType - �����������ͣ������ϵ������ѡֵΪconcept,relation
				type - �������� clazz/concrete
				direction - ��ϵ���򣬵�������������Ϊ��ϵʱ��Ч����ѡֵΪinbound,outbound,any
				content - �������ƣ���������������Ϊ����ʱ��Ч
				���elementTypeΪrelationʱ��contentΪ��ϵ�����������ơ�
				parentIds - ��������id����
				parentNames - ��ϵ���ͻ������������
				attributes - �������飬����ÿһ�����
				   Name - ������,�硱�Ա�
				   Value - ����ֵ������ֵ���飬��[���С�,��Ů��]
				   Operator - �������ţ���>,<,=,>=,like...
				   arrayPreOperator - ����������� any,all,none</td></tr>
			<tr><td>infoCondition</td><td>String</td><td>��Ϣ��Դ��������json�ַ���</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">���� <a href="#������Ϣ����">������Ϣ����</a>����ο�3.3</td>
			</tr>
			<tr><td class="titleName">����</td><td colspan="3">�������ʵ��
				Id: concept_class/D17541D2-2B55-B342-AD60-AA31A5719EE8
				conditions:[{"elementType":"concept","content":"�⽻#����Ŀ��"}]
				infoCondition:{"type":"WCMģ������","�ܷ�":"�⽻","���":"����"}
				�����뿴3.3<a href="#������Ϣ����">������Ϣ����</a></td></tr>
		</table>
		
		<!-- getThemeWordsOutputStream -->
		<span class="method">2.1.9 getThemeWordsOutputStream</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">������</td><td colspan="3">getThemeWordsOutputStream</td></tr>
		</thead>
			<tr><td class="titleName">��������</td><td colspan="3">��ȡ�����������</td></tr>
			<tr ><td class="titleName" >����</td><td >������</td><td >��������</td><td>����˵��</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">������ַ��������ݣ������֮����\n���������磺����Ժ\n�칫��\n������...</td>
			</tr>
		</table>
		
		<!-- getTermsByText -->
		<span class="method">2.1.10 getTermsByText</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">������</td><td colspan="3">getTermsByText</td></tr>
		</thead>
			<tr><td class="titleName">��������</td><td colspan="3">���ı���ν��зִ�</td></tr>
			<tr ><td class="titleName" rowspan="2">����</td><td >������</td><td >��������</td><td>����˵��</td></tr>
			<tr ><td >text</td><td >String</td><td >�ı��������</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">�ַ�����JSON�����ַ����磺["�������ĳ���","������","�㽭"]</td>
			</tr>
		</table>		
				
		<!-- getActionConceptDefsByBodyAndObject -->
		<span class="method">2.1.11 getActionConceptDefsByBodyAndObject</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">������</td><td colspan="3">getActionConceptDefsByBodyAndObject</td></tr>
		</thead>
			<tr><td class="titleName">��������</td><td colspan="3">���ݵ�ǰ����͵�ǰ�����ö�Ӧ����Ϊ�����б�</td></tr>
			<tr ><td class="titleName" rowspan="4">����</td><td >������</td><td >��������</td><td>����˵��</td></tr>
			<tr><td>context</td><td>String</td><td>�����Ĳ���:
				{ 
					CURRENT_BOBY_IDS����ǰ����ID����
					CURRENT_ACTION_IDS����ǰ��ΪID����
					CURRENT_OBJECT_IDS����ǰ����ID����
					CURRENT_SPACE_IDS����ǰ�ռ�ID����
					CURRENT_TIME_IDS����ǰʱ��ID����
				}
				��{
				    "CURRENT_BOBY_IDS": [
				        "xxx",
				        "xxx"
				    ],
				    "CURRENT_ACTION_IDS": [
				        "xxx",
				        "xxx"
				    ]
				}</td></tr>
			<tr><td>bodyConceptId</td><td>String</td><td>��ǰ����ĸ���ID</td></tr>
			<tr><td>objConceptId</td><td>String</td><td>��ǰ����ĸ���ID</td></tr>
			<tr><td class="titleName">����ֵ</td> 
				<td colspan="3"><a href="#�����">�����</a></td>
			</tr>
			<tr><td class="titleName">����</td><td colspan="3">�����뿴3.1<a href="#�����">�����</a></td></tr>
		</table>
				
		<!-- getRelatedKnowledgesByAction -->
		<span class="method">2.1.12 getRelatedKnowledgesByAction</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">������</td><td colspan="3">getRelatedKnowledgesByAction</td></tr>
		</thead>
			<tr><td class="titleName">��������</td><td colspan="3">��ȡĳ����Ϊ�����֪ʶ</td></tr>
			<tr ><td class="titleName" rowspan="3">����</td><td >������</td><td >��������</td><td>����˵��</td></tr>
			<tr><td>context</td><td>String</td><td>�����Ĳ���:
			{ 
				CURRENT_BOBY_IDS����ǰ����ID����
				CURRENT_ACTION_IDS����ǰ��ΪID����
				CURRENT_OBJECT_IDS����ǰ����ID����
				CURRENT_SPACE_IDS����ǰ�ռ�ID����
				CURRENT_TIME_IDS����ǰʱ��ID����
			}
			��{
			    "CURRENT_BOBY_IDS": [
			        "xxx",
			        "xxx"
			    ],
			    "CURRENT_ACTION_IDS": [
			        "xxx",
			        "xxx"
			    ]
			}</td></tr>
			<tr><td>actConceptId</td><td>String</td><td>��ǰ��Ϊ�ĸ���ID</td></tr>
			<tr><td class="titleName">����ֵ</td> 
				<td colspan="3">����Json���󣬶���ṹ���£�
				{
				��������塱:[<a href="#��������">��������(�ο�3.4)</a>],
				��������ݡ�:[<a href="#��������">��������(�ο�3.4)</a>],
				����زο���:[<a href="#��������">��������(�ο�3.4)</a>],
				�������Ϊ��:[<a href="#��������">��������(�ο�3.4)</a>],
				����ؿ��塱:[<a href="#��������">��������(�ο�3.4)</a>],
				�����ʱ�䡱:[<a href="#��������">��������(�ο�3.4)</a>],
				����ؿռ䡱:[<a href="#��������">��������(�ο�3.4)</a>],
				���������:[<a href="#��������">��������(�ο�3.4)</a>]
				}</td>
			</tr>
		</table>
								
		<!-- getOntologiesAndInfosByKeywords -->
		<span class="method">2.1.13 getOntologiesAndInfosByKeywords</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">������</td><td colspan="3">getOntologiesAndInfosByKeywords</td></tr>
		</thead>
			<tr><td class="titleName">��������</td><td colspan="3">��ȡ�ʶ�Ӧ�ĸ�������������Ϣ��Դ�б�</td></tr>
			<tr ><td class="titleName" rowspan="4">����</td><td >������</td><td >��������</td><td>����˵��</td></tr>
			<tr><td>context</td><td>String</td><td>�����Ĳ���:
				{
					CURRENT_BOBY_IDS����ǰ����ID����
					CURRENT_ACTION_IDS����ǰ��ΪID����
					CURRENT_OBJECT_IDS����ǰ����ID����
					CURRENT_SPACE_IDS����ǰ�ռ�ID����
					CURRENT_TIME_IDS����ǰʱ��ID����
				}
				��{
				    "CURRENT_BOBY_IDS": [
				        "xxx",
				        "xxx"
				    ],
				    "CURRENT_ACTION_IDS": [
				        "xxx",
				        "xxx"
				    ]
				}</td></tr>
			<tr><td>words</td><td>String</td><td>�����json�ַ������飬��[������Ժ��,����������]</td></tr>
			<tr><td>inforMaxSize</td><td>Int</td><td>������Ϣ��Դ����������</td></tr>
			<tr><td class="titleName">����ֵ</td> 
				<td colspan="3">����<a href="#��������">���弯�ϣ�������ϣ����иø����������������Ϣ��Դ��������ο�3.4</a></td>
			</tr>
			<tr><td class="titleName">����</td><td colspan="3"><a href="#��������">�����뿴3.4����������Ϣ�ĸ����</a></td></tr>
		</table>
		
		<!-- getConceptsByKeywords -->
		<span class="method">2.1.14 getConceptsByKeywords</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">������</td><td colspan="3">getConceptsByKeywords</td></tr>
		</thead>
			<tr><td class="titleName">��������</td><td colspan="3">ͨ���ؼ��ʻ�ȡ��������</td></tr>
			<tr ><td class="titleName" rowspan="2">����</td><td >������</td><td >��������</td><td>����˵��</td></tr>
			<tr><td>words</td><td>String</td><td>�����json�ַ������飬��[������Ժ��,����������]</td></tr>
			<tr><td class="titleName">����ֵ</td> 
				<td colspan="3">����<a href="#�����">�����</a></td>
			</tr>
			<tr><td class="titleName">����</td><td colspan="3"><a href="#�����">�����뿴3.1�����</a></td></tr>
		</table>
		
		<!-- getExtensionalConcepts -->
		<span class="method">2.1.15 getExtensionalConcepts</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">������</td><td colspan="3">getExtensionalConcepts</td></tr>
		</thead>
			<tr><td class="titleName">��������</td><td colspan="3">��ȡһ����������ӣ��ں�->���ӣ������</td></tr>
			<tr ><td class="titleName" rowspan="4">����</td><td >������</td><td >��������</td><td>����˵��</td></tr>
			<tr><td>conceptId</td><td>String</td><td>����id</td></tr>
			<tr><td>type</td><td>String</td><td>�������ͣ�clazz/concrete���������������Ͷ�����</td></tr>
			<tr><td>num</td><td>Int</td><td>��������</td></tr>
			<tr><td class="titleName">����ֵ</td> 
				<td colspan="3"><a href="#�����">����ϣ��ο�3.1</a></td>
			</tr>
		</table>
		
		<!-- getIntensionalConcepts -->
		<span class="method">2.1.16 getIntensionalConcepts</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">������</td><td colspan="3">getIntensionalConcepts</td></tr>
		</thead>
			<tr><td class="titleName">��������</td><td colspan="3">��ȡһ��������ں�������->�ں��������</td></tr>
			<tr ><td class="titleName" rowspan="2">����</td><td >������</td><td >��������</td><td>����˵��</td></tr>
			<tr><td>conceptId</td><td>String</td><td>����id</td></tr>
			<tr><td class="titleName">����ֵ</td> 
				<td colspan="3"><a href="#�����">����ϣ��ο�3.1</a></td>
			</tr>
		</table>
		
		<!-- getIntensionalConcepts -->
		<span class="method">2.1.17 getBodysOfObjects</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">������</td><td colspan="3">getBodysOfObjects</td></tr>
		</thead>
			<tr><td class="titleName">��������</td><td colspan="3">��ȡĳЩ������ṩ��λ�����壩</td></tr>
			<tr ><td class="titleName" rowspan="2">����</td><td >������</td><td >��������</td><td>����˵��</td></tr>
			<tr><td>objectIds</td><td>String</td><td>����Ids����</td></tr>
			<tr><td class="titleName">����ֵ</td> 
				<td colspan="3"><a href="#�����">����ϣ��ο�3.1</a></td>
			</tr>
		</table>
		
		<!-- getOntologiesByConceptIdsAndConditions -->
		<span class="method">2.1.18 getOntologiesByConceptIdsAndConditions</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">������</td><td colspan="3">getOntologiesByConceptIdsAndConditions</td></tr>
		</thead>
			<tr><td class="titleName">��������</td><td colspan="3">ͨ������id���Ϻ͹�ϵ����������ȡ����</td></tr>
			<tr ><td class="titleName" rowspan="3">����</td><td >������</td><td >��������</td><td>����˵��</td></tr>
			<tr><td>conceptIds</td><td>String</td><td>����conceptIds����</td></tr>
			<tr><td>conditions</td><td>String</td><td>��ϵ��������,����ÿһ�������ϵ��relationName����ϵ����direction[{'relationName':'����', 'direction':'outbound'},{'relationName':'����', 'direction':'any'}...]</td></tr>
			<tr><td class="titleName">����ֵ</td> 
				<td colspan="3"><a href="#�����">������ر��弯�ϣ������+��ϵ���ϣ�����ο�3.1��3.2</a>
				��ṹ���£�
				[{
				��concepts��:<a href="#�����">�����</a>,
				��relations��:<a href="#��ϵ����">��ϵ����</a>
				}]</td>
			</tr>
		</table>
		
		<!-- getRelationDefinitionsByNames -->
		<span class="method">2.1.19 getRelationDefinitionsByNames</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">������</td><td colspan="3">getRelationDefinitionsByNames</td></tr>
		</thead>
			<tr><td class="titleName">��������</td><td colspan="3">ͨ�����ֻ�ȡ��ϵ���弯��</td></tr>
			<tr ><td class="titleName" rowspan="2">����</td><td >������</td><td >��������</td><td>����˵��</td></tr>
			<tr><td>names</td><td>String</td><td>��ϵ����������</td></tr>
			<tr><td class="titleName">����ֵ</td> 
				<td colspan="3"><a href="#�����">�����</a></td>
			</tr>
		</table>
		
		<!-- getRecommendedKeywords -->
		<span class="method">2.1.20 getRecommendedKeywords</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">������</td><td colspan="3">getRecommendedKeywords</td></tr>
		</thead>
			<tr><td class="titleName">��������</td><td colspan="3">��ȡĳ�������ʵ��Ƽ������ʻ�</td></tr>
			<tr ><td class="titleName" rowspan="2">����</td><td >������</td><td >��������</td><td>����˵��</td></tr>
			<tr><td>words</td><td>String</td><td>����������</td></tr>
			<tr><td class="titleName">����ֵ</td> 
				<td colspan="3">�ַ���JSON�����磺["�й�","������"]</td>
			</tr>
		</table>
		
		<!-- searchConceptDefsOfText -->
		<span class="method">2.1.21 searchConceptDefsOfText</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">������</td><td colspan="3">searchConceptDefsOfText</td></tr>
		</thead>
			<tr><td class="titleName">��������</td><td colspan="3">���ݹؼ��ʾ�������֪ʶ</td></tr>
			<tr ><td class="titleName" rowspan="3">����</td><td >������</td><td >��������</td><td>����˵��</td></tr>
			<tr><td>context</td><td>String</td><td>�����Ĳ���:
				{
					CURRENT_BOBY_IDS����ǰ����ID����
					CURRENT_ACTION_IDS����ǰ��ΪID����
					CURRENT_OBJECT_IDS����ǰ����ID����
					CURRENT_SPACE_IDS����ǰ�ռ�ID����
					CURRENT_TIME_IDS����ǰʱ��ID����
				}
				��{
				    "CURRENT_BOBY_IDS": [
				        "xxx",
				        "xxx"
				    ],
				    "CURRENT_ACTION_IDS": [
				        "xxx",
				        "xxx"
				    ]
				}</td></tr>
			<tr><td>text</td><td>String</td><td>�ؼ��ʾ�</td></tr>
			<tr><td class="titleName">����ֵ</td> 
				<td colspan="3">����Json���󣬶���ṹ���£�
					{
					��������塱:[<a href="#��������">��������(�ο�3.4)</a>],
					��������ݡ�:[<a href="#��������">��������(�ο�3.4)</a>],
					����زο���:[<a href="#��������">��������(�ο�3.4)</a>],
					�������Ϊ��:[<a href="#��������">��������(�ο�3.4)</a>],
					����ؿ��塱:[<a href="#��������">��������(�ο�3.4)</a>],
					�����ʱ�䡱:[<a href="#��������">��������(�ο�3.4)</a>],
					����ؿռ䡱:[<a href="#��������">��������(�ο�3.4)</a>],
					���������:[<a href="#��������">��������(�ο�3.4)</a>]
					}</td>
			</tr>
		</table>
				
		<!-- searchRelevantContent -->
		<span class="method">2.1.22 searchRelevantContent</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">������</td><td colspan="3">searchRelevantContent</td></tr>
		</thead>
			<tr><td class="titleName">��������</td><td colspan="3">��ѯ�����ĸ���</td></tr>
			<tr ><td class="titleName" rowspan="4">����</td><td >������</td><td >��������</td><td>����˵��</td></tr>
			<tr><td>text</td><td>String</td><td>�ı�����</td></tr>
			<tr><td>domainIds</td><td>String</td><td>����id json�����ַ���</td></tr>
			<tr><td>parentIds</td><td>String</td><td>����Id json �����ַ���</td></tr>
			<tr><td class="titleName">����ֵ</td> 
				<td colspan="3"><a href="#�����">����ϣ��ο�3.1</a></td>
			</tr>
		</table>
		
		<!-- 23ruleCATClassifyText -->
		<span class="method">2.1.23 ruleCATClassifyText</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">������</td><td colspan="3">ruleCATClassifyText</td></tr>
		</thead>
			<tr><td class="titleName">��������</td><td colspan="3">�����ݽ���������࣬��������������������</td></tr>
			<tr ><td class="titleName" rowspan="2">����</td><td >������</td><td >��������</td><td>����˵��</td></tr>
			<tr><td>text</td><td>String</td><td>�ı����ݣ����ö��ŷָ�����ʣ����繲����,�³�</td></tr>
			<tr><td class="titleName">����ֵ</td> 
				<td colspan="3">JSON���飬ÿ��Ԫ�ش���һ��������࣬�磺[������ʵ��\\�⽻��,������ʵ��\\������, ��]</td>
			</tr>
		</table>
		
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
		  <legend>3.����ֵ������˵��</legend>
		</fieldset>
		
		<span class="method"><a name="�����">3.1 �����</a></span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">����ֵ</td><td colspan="3">�������ϸ���ݣ���ṹΪ��׼json�ַ�����<br>
			���ַ��������������ݣ�<br>
			id - ����id<br>
			content - ��������<br>
			definition - �����<br>
			parentNames - ������������<br>
			parentIds- ��������ID����<br>
			keywords - �ؼ�������<br>
			synonyms - ͬ�������<br>
			attributes - �������ԣ�json�����ַ���������ÿһ��Ϊjson���󣬰���name��dateType��value�� valueΪ����ֵ���飬�� [{"value": ["����"],"type": "string","name": "��Χ"}<br>
			createDate�C�����ʱ��,<br>
			type-  �������ͣ�<br>concrete/clazz,</td></tr>
		</thead>
			<tr><td class="titleName">����</td><td >���ؽ��ʵ����<br>
				[{<br>
				"id": "concept_concrete/D83C32D8-F485-1BFB-619B-39C26D9A7458",<br>
				"content": "�⽻#����Ŀ��",<br>
				"parentNames": [],<br>
				"parentIds": [],<br>
				"keywords": [<br>
				"�⽻����",<br>
				"��������"<br>
				    ],<br>
				"definition": "",<br>
				"synonyms": [],<br>
				"attributes": [<br>
				        {<br>
				"dataType": "array",<br>
				"name": "BELONG_DOMAIN_IDS",<br>
				"value": [<br>
				"concept_concrete/8C15BDC1-B245-069F-176A-25EE992E3FDC"<br>
				            ]<br>
				        },<br>
				        {<br>
				"dataType": "concept",<br>
				"name": "����",<br>
				"value": {}<br>
				        },<br>
				        {<br>
				"dataType": "concept",<br>
				"name": "Ŀ��",<br>
				"value": {}<br>
				        },<br>
				        {<br>
				"dataType": "array",<br>
				"name": "BELONG_DOMAIN_NAMES",<br>
				"value": [<br>
				"ס���ͳ��罨��"<br>
				            ]<br>
				        },<br>
				        {<br>
				"dataType": "string",<br>
				"name": "IS_COMMON",<br>
				"value": "true"<br>
				        },<br>
				        {<br>
				"dataType": "concept",<br>
				"name": "ָ��",<br>
				"value": {}<br>
				        },<br>
				        {<br>
				"dataType": "string",<br>
				"name": "ALIAS",<br>
				"value": "����Ŀ��"<br>
				        }<br>
				    ],<br>
				"createDate": "2017-01-06 11:46:32",<br>
				"type": "concrete",<br>
				"creator": "$ANYBODY"<br>
				}]</td></tr>
		</table>
		
		<span class="method" ><a name="��ϵ����">3.2 ��ϵ����</a></span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">����ֵ</td><td colspan="3">��Ӧ��ȡ���Ĺ�ϵ���飬������ÿһ��Ϊ��ϵ���飬����<br>
			content- ��ϵ����<br>
			  startNodeId - ��ϵ��ʼ�ĸ���id<br>
			  endNodeId - ��ϵ�����ĸ���id<br>
			attributes - �������ԣ�json�����ַ���������ÿһ��Ϊjson���󣬰���name��dateType��value�� valueΪ����ֵ���飬�� [{"value": ["����"],"type": "string","name": "��Χ"}<br>
			createDate�C����ʱ��,<br>
			type: ��ϵ����id<br>
			 creator: - ������<br>
			 keyword �C�ؼ���</td></tr>
		</thead>
			<tr><td class="titleName">����</td><td >���ؽ��ʵ����<br>
        [<br>
            {<br>
                "id": "relation_word/58576747",<br>
                "content": "������ʪ�ر�����",<br>
                "keywords": [],<br>
                "endNodeId": "word/153787B9-014C-C4A8-A221-83C603B5E359",<br>
                "attributes": [],<br>
                "createDate": "2017-01-12 14:20:23",<br>
                "type": "concept_class/C952D48E-2B90-FB4C-5BAD-FD592F2FEY8",<br>
                "startNodeId": "concept_concrete/C952D48E-2B90-FB4C-5BAD-FD592F2FE2F6",<br>
                "creator": "$ANYBODY"<br>
            }<br>
        ]</td></tr>
		</table>
		
		<span class="method"><a name="������Ϣ����">3.3 ������Ϣ����</a></span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">����ֵ</td><td colspan="3">�ĵ�json���鼯�ϣ�<br>
				���������ÿһ������������ݣ�<br>
				title - ���±���<br>
				type - ���·���<br>
				id - ����id<br>
				url - ��ԴUrl<br>
				viewPageUrl - �鿴��Դurl<br>
				attributes -<br>
				_url �C�ù�����Ϣ��url<br>
				��key��:��value�� -���Լ�ֵ��<br>
				��.<br>
				creator �C������</td></tr>
		</thead>
			<tr><td class="titleName">����</td><td >���ؽ��ʵ����<br>
			{<br>
			"informations": [<br>
			        {<br>
			"id": "information/D9B2D703-BEB6-2DC6-E5A2-27BC6EC32CF3",<br>
			"title": "�������⽻�����⣩",<br>
			"attributes": {<br>
			"_rev": "56594007",<br>
			"����": "����",<br>
			"�ܷ�": "�⽻",<br>
			"_key": "D9B2D703-BEB6-2DC6-E5A2-27BC6EC32CF3",<br>
			"_url": "http://<host>/wcmSearch#title=%25%E5%A4%96%E4%BA%A4%25"<br>
			            },<br>
			"type": "WCMģ������",<br>
			"createDate": "2017-01-09 17:16:02",<br>
			"url": "http://<host>/wcmSearch#title=%25%E5%A4%96%E4%BA%A4%25"<br>,
			"viewPageUrl": "http://<host>/wcmSearch#title=%25%E5%A4%96%E4%BA%A4%25",<br>
			"creator": "$ANYBODY"<br>
			        }<br>
			    ]<br>
			}</td></tr>
		</table>
		
		<span class="method"><a name="��������">3.4 ����������Ϣ�ĸ����</a></span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">����ֵ</td><td colspan="3">id - ����id<br>
			content - ��������<br>
			definition - �����<br>
			parentNames - ������������<br>
			parentIds- ��������ID����<br>
			keywords - �ؼ�������<br>
			synonyms - ͬ�������<br>
			attributes - �������ԣ�json�����ַ���������ÿһ��Ϊjson���󣬰���name��dateType��value�� valueΪ����ֵ���飬�� [{"value": ["����"],"type": "string","name": "��Χ"}<br>
			createDate�C�����ʱ��,<br>
			type-  �������ͣ�concrete/clazz,<br>
			info �C<br>
			title - ���±���<br>
			type - ���·���<br>
			id - ����id<br>
			  url - ��ԴUrl<br>
			  viewPageUrl - �鿴��Դurl<br>
			attributes -<br>
			_url �C�ù�����Ϣ��url<br>
			��key��:��value�� -���Լ�ֵ��<br>
			��.<br>
			creator �C������</td></tr>
		</thead>
			<tr><td class="titleName">����</td><td >���ؽ��ʵ����<br>
			[<br>
			    {<br>
			        "id": "concept_concrete/55795F3A-D6BC-44E1-C889-60418266B359",<br>
			        "content": "���ǿ",<br>
			        "parentNames": [<br>
			            "����",<br>
			            "����",<br>
			            "��Ȼ��",<br>
			            "����"<br>
			        ],<br>
			        "parentIds": [<br>
			            "concept_class/thing",<br>
			            "concept_class/046E7344-35C0-7ADA-8B11-81F721ECFAF8",<br>
			            "concept_class/09242942-EDF6-843A-1AB5-729A798F6706",<br>
			            "concept_class/53070072-8509-E71B-D985-686293D55F53"<br>
			        ],<br>
			        "keywords": [],<br>
			        "infos": [<br>
			            {<br>
			                "id": "information/D9B2D703-BEB6-2DC6-E5A2-27BC6EC32CF3",<br>
			                "title": "�������⽻�����⣩",<br>
			                "attributes": {<br>
			                    "_rev": "56594007",<br>
			                    "����": "����",<br>
			                    "�ܷ�": "�⽻",<br>
			                    "_key": "D9B2D703-BEB6-2DC6-E5A2-27BC6EC32CF3",<br>
			                    "_url": "http://<host>/wcmSearch#title=%25%E5%A4%96%E4%BA%A4%25"<br>
			                },<br>
			                "type": "WCMģ������",<br>
			                "createDate": "2017-01-09 17:16:02",<br>
			                "url": "http://<host>/wcmSearch#title=%25%E5%A4%96%E4%BA%A4%25",<br>
			                "viewPageUrl": "http://<host>/wcmSearch#title=%25%E5%A4%96%E4%BA%A4%25",<br>
			                "creator": "$ANYBODY"<br>
			            }<br>
			        ],<br>
			        "definition": "",<br>
			        "synonyms": [],<br>
			        "attributes": [<br>
			            {<br>
			                "dataType": "string",<br>
			                "name": "�Ա�",<br>
			                "value": "��"<br>
			            },<br>
			            {<br>
			                "dataType": "string",<br>
			                "name": "IS_COMMON",<br>
			                "value": "true"<br>
			            },<br>
			            {<br>
			                "dataType": "string",<br>
			                "name": "���֤����",<br>
			                "value": "425324523452452435452452"<br>
			            },<br>
			            {<br>
			                "dataType": "string",<br>
			                "name": "ALIAS",<br>
			                "value": "���ǿ"<br>
			            }<br>
			        ],<br>
			        "createDate": "2016-12-24 11: 04: 10",<br>
			        "type": "concrete",<br>
			        "creator": "$ANYBODY"<br>
			    }<br>
			]</td></tr>
		</table>
		
		<span class="method">3.5 TRS��Ϣ��Դ����</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">����ֵ</td><td colspan="3">num: ������,<br>
			page: ��ǰҳ��,<br>
			pageSize: ��ǰҳ����,<br>
			data- ��Ϣ��Դ���ݣ�json�����ַ���������ÿһ��Ϊjson���󣬰���id��content��abstract�ȣ�<br></td></tr>
		</thead>
			<tr><td class="titleName">����</td><td >���ؽ��ʵ����<br>
			{<br>
			    "num": 627,<br>
			    "page": 1,<br>
			    "pageSize": 10,<br>
			    "data": [<br>
			        {<br>
			            "id": 34174,<br>
			            "content": "",<br>
			            "abstract": "",<br>
			            "genre": "",<br>
			            "title": "�л����񹲺͹��ܷ�������",<br>
			            "keywords": "���ɹ涨;���徭����֯;�л����񹲺͹�;�ܷ�;��������",<br>
			            "belong": "wcmmetatableflfg",<br>
			            "bbrq": "1993-3-29",<br>
			            "html_content": "�й�����������ίԱ������޸��ܷ��������ݵĽ���",<br>
			            "relevance": "100%",<br>
			            "url": "http: //law.npc.gov.cn/FLFG/flfgByID.action?flfgID=156&keyword=&zlsxid=08"<br>
			        },��.<br>
			    ]<br>
			}</td></tr>
		</table>
	</div>
</body>
</html>