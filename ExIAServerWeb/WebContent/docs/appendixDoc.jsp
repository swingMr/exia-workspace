<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>��¼</title>
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
		  <legend>1. Python�ű���дһЩע������</legend>
		</fieldset>
		
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
		  <legend>2. JSON���ݸ�ʽ�ĸ��ֶ���</legend>
		</fieldset>
		<span class="method">2.1 context�����ĵ�JSON��ʽ</span> 
		<pre class="layui-code">{
	    "user": {
	        "id": "��ǰid", 
	        "account": "��ǰ�û��˺�", 
	        "ip": "��ǰIP��ַ", 
	        "name": "��ǰ�û�����", 
	        "exploreName": "���������", 
	        "exploreVersion": "������汾", 
	        "osName": "����ϵͳ����", 
	        "osVersion": "����ϵͳ�汾"
	    }, 
	    "org": {
	        "unitName": "���ڵ�λ����", 
	        "unitCode": "���ڵ�λ��֯����", 
	        "superiorUnitName": "�ϼ���λ����"
	    }, 
	    "location": {
	        "countryName": "���ڹ��ҵ�������", 
	        "provinceName": "����ʡ��������", 
	        "cityName": "���ڳ�������"
	    }, 
	    "time": {
	        "year": "��ǰ���", 
	        "month": "��ǰ���£���ʽ��yyyy-MM", 
	        "date": "��ǰ���ڣ���ʽ��yyyy-MM-dd"
	    }
		}</pre> 
		
		<span class="method">2.2���ڡ���ϵͳ��Ҫ�ء���֪ʶ�����ʶ��JSON��ʽ</span> 
		<pre class="layui-code">{
			"domains":
				    [{"id": "����ID","content":"��������","relevantLevel":"��ض�", "synonyms":[],"relevantConcepts":[{"id": "����ID","content":"��������","relevantLevel":"��ض�", "synonyms":[]},{"id": "����ID","content": "��������","relevantLevel":"��ض�","relevantConcepts":"��ظ����ض�"}],
			"acts":
				    [{"id": "��ΪID","content":"��Ϊ����","relevantLevel":"��ض�", "synonyms":[],"relevantConcepts":"��ظ����ض�"},{"id": "��ΪID","content": "��Ϊ����","relevantLevel":"��ض�","relevantConcepts":"��ظ����ض�"}],
			"organdpersons":
				    [{"id": "����ID","content":"��������","relevantLevel":"��ض�","relevantConcepts":"��ظ����ض�"},{"id": "����ID","content": "��������","relevantLevel":"��ض�","relevantConcepts":"��ظ����ض�"}],
			"objects":
				    [{"id": "����ID","content":"��������","relevantLevel":"��ض�","relevantConcepts":"��ظ����ض�"},{"id": "����ID","content": "��������","relevantLevel":"��ض�","relevantConcepts":"��ظ����ض�"}],
			"spaces":
				    [{"id": "�ռ�ID","content":"�ռ�����","relevantLevel":"��ض�","relevantConcepts":"��ظ����ض�"},{"id": "�ռ�ID","content": "�ռ�����","relevantLevel":"��ض�"}],
			"times":
				    [{"id": "ʱ��ID","content":"ʱ������","relevantLevel":"��ض�","relevantConcepts":"��ظ����ض�"},{"id": "ʱ��ID","content": "ʱ������","relevantLevel":"��ض�","relevantConcepts":"��ظ����ض�"}],
			"keywords":
				    [{"content":"�����1","relevantLevel":"��ض�"},{"content":"�����2","relevantLevel":"��ض�"}]
		}</pre> 
		
		<span class="method">2.3 ��Ϣ��Դ��ѯ����JSON��ʽ</span> 
		<pre class="layui-code">{
				    "title":"��Դ���⣬����׼��ѯ",
				    "titles":[��Դ�����ַ������飬����׼��ѯ],
				    "originalAddress":"ԭ�ĵ�ַ������׼��ѯ",
				    "source":"��Դ��Դƽ̨���ƣ�����׼��ѯ",
				    "keywords":[������ַ������飬����׼��ѯ],
				    "publishDateMin":"����������Сֵ����ʽ��yyyy-MM-dd",
				    "publishDateMax":"�����������ֵ����ʽ��yyyy-MM-dd"
		}</pre> 
		
		<span class="method">2.4 ��Ϣ��Դ��������ķ�ҳ���JSON��ʽ</span> 
		<pre class="layui-code">{
	    "num": 627, 
	    "page": 1, 
	    "pageSize": 10, 
	    "informations": [
	        {
	            "id": 34174, 
	            "title": "�л����񹲺͹��ܷ�������", 
	            "url": "http: //law.npc.gov.cn/FLFG/flfgByID.action?flfgID=156&keyword=&zlsxid=08", 
	            "attributes": {
	                "content": "", 
	                "abstract": "", 
	                "genre": "", 
	                "symbol": "", 
	                "form": "", 
	                "keywords": "���ɹ涨;���徭����֯;�л����񹲺͹�;�ܷ�;��������", 
	                "belong": "wcmmetatableflfg", 
	                "bbrq": "1993-3-29"
	            }, 
	            "viewPageUrl": "http: //law.npc.gov.cn/FLFG/flfgByID.action?fssc"
	        },��
	    ]
		}</pre> 
		
		
	</div>
<script src="/ExIAServer/js/layui-v2/layui.js" charset="utf-8"></script>
<!-- ע�⣺�����ֱ�Ӹ������д��뵽���أ�����js·����Ҫ�ĳ��㱾�ص� -->
<script>
layui.use('code', function(){ //����codeģ��
	layui.code({
		about: false
		});
	});
</script>
</body>
</html>