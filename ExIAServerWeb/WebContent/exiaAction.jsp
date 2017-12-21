<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="/ExIAServer/js/jquery-1.7.2.min.js"></script>
<style type="text/css">
.remark {
	color: #007799;
	padding-top: 2px;
	font-size: 14px
}
</style>
</head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<body>
	<div>
		<div style="float: left; width: 600px">
			<form
				action="/ExIAServer/services/ontology/search/getConcept"
				method='post'>
				<input type="text" name="getConcept" placeholder="��д����ID"
					title="���ӣ�concept_class/thing"> <input type="button"
					value="��ȡ����" id="getConcept">
			</form>
			<div class="remark">���ú�����getConcept</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/search/getOntologiesByKeywords"
				method='post'>
				<input type="text" name="getOntologiesByKeywords"
					placeholder="��д�����" title="���ӣ�[&quot;����Ժ&quot;]"> <input
					type="button" value="ͨ���ؼ��ʻ�ȡ" id="getOntologiesByKeywords">
			</form>
			<div class="remark">���ú�����getOntologiesByKeywords</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/search/getOntologiesByIds"
				method='post'>
				<input type="text" name="getOntologiesByIds" value=""
					placeholder="��д����IDs" title="���ӣ�[&quot;concept_class/thing&quot;]">
				<input type="button" value="ͨ��ID��ȡ��ر���,���ö��Ÿ���"
					id="getOntologiesByIds">
			</form>
			<div class="remark">���ú�����getOntologiesByIds</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/search/getOntologies"
				method='post'>
				<input type="text" name="conceptId1" placeholder="��дconceptId"
					title="���ӣ�concept_class/thing"> <input type="text"
					name="cdit1" placeholder="�����������"
					title="���ӣ�[&quot;elementType&quot;:&quot;concept&quot;]"> <input
					type="button" value="��ȡ��ر���" id="getOntologies">
			</form>
			<div class="remark">���ú�����getOntologies</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/getinfo/getInformationsByIdsAndCondition"
				method='post'>
				<input type="text" name="conceptId2" placeholder="��д����IDs"
					title="���ӣ�[&quot;concept_class/thing&quot;]"> <input
					type="text" name="cdit3" placeholder="��д�����������"
					title="���ӣ�[&quot;���&quot;:&quot;����&quot;]"> <input
					type="button" value="ͨ��������ҹ�����Ϣ"
					id="getInformationsByIdsAndCondition">
			</form>
			<div class="remark">���ú�����getInformationsByIdsAndCondition</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/getinfo/getInformationsByKeywordsAndConditionn"
				method='post'>
				<input type="text" name="keywords" placeholder="��д�ؼ���"
					title="���ӣ�[&quot;����Ժ&quot;]"> <input type="text"
					name="cdit5" placeholder="��д�����������"
					title="���ӣ�[&quot;���&quot;:&quot;����&quot;]"> <input
					type="button" value="ͨ���ؼ��ʲ��ҹ����ĵ�"
					id="getInformationsByKeywordsAndCondition">
			</form>
			<div class="remark">���ú�����getInformationsByKeywordsAndCondition</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/getinfo/getInformations"
				method='post'>
				<input type="text" name="conceptId3" placeholder="��д����ID"
					title="���ӣ�concept_class/thing"> <input type="text"
					name="cdit7" placeholder="��д��������"
					title="���ӣ�[&quot;elementType&quot;:&quot;concept&quot;]"> <input
					type="text" name="incdit2" placeholder="��д��Ϣ��Դ��������"
					title="���ӣ�[&quot;���&quot;:&quot;����&quot;]"> <input
					type="button" value="���ҹ����ĵ�" id="getInformations">
			</form>
			<div class="remark">���ú�����getInformations</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/getinfo/getInformationsByUrlAndAttribute"
				method='post'>
				<input type="text" name="url" placeholder="��дURL"
					title="���ӣ�http://<host>/knowledgeDocAttr?���={���}"> <input
					type="text" name="attribute" placeholder="��д����"
					title="���ӣ�[&quot;���&quot;:&quot;����&quot;]"> <input
					type="button" value="ͨ��url�����Բ��ҹ�����Ϣ"
					id="getInformationsByUrlAndAttribute">
			</form>
			<div class="remark">���ú�����getInformationsByUrlAndAttribute</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/search/getTermsByText"
				method='post'>
				<input type="text" name="text" placeholder="��д�ı�"
					title="���ӣ����ǿ�ڹ���Ժ����"> <input type="button" value="�ִ�"
					id="getTermsByText">
			</form>
			<div class="remark">���ú�����getTermsByText</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/search/getActionConceptDefsByBodyAndObject"
				method='post'>
				<input type="text" name="context1" placeholder="��д������"
					title="���ӣ�{&quot;CURRENT_BOBY_IDS&quot;:[&quot;xxx&quot;]}">
				<input type="text" name="bodyConceptId" placeholder="�������id"
					title="���ӣ�bodyConceptId"> <input type="text"
					name="objConceptId" placeholder="�������id" title="���ӣ�objConceptId">
				<input type="button" value="������Ϊ"
					id="getActionConceptDefsByBodyAndObject">
			</form>
			<div class="remark">���ú�����getActionConceptDefsByBodyAndObject</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/search/getRelatedKnowledgesByAction"
				method='post'>
				<input type="text" name="context2" placeholder="��д������"
					title="���ӣ�{&quot;CURRENT_BOBY_IDS&quot;:[&quot;xxx&quot;]}">
				<input type="text" name="actConceptId" placeholder="��Ϊ����ID"
					title="���ӣ�actConceptId"> <input type="button"
					value="ͨ����Ϊ���Ҹ���" id="getRelatedKnowledgesByAction">
			</form>
			<div class="remark">���ú�����getRelatedKnowledgesByAction</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/search/getOntologiesAndInfosByKeywords"
				method='post'>
				<input type="text" name="context3" placeholder="��д������"
					title="���ӣ�{&quot;CURRENT_BOBY_IDS&quot;:[&quot;xxx&quot;]}">
				<input type="text" name="words1" placeholder="�����"
					title="���ӣ�[&quot;����Ժ&quot;]"> <input type="button"
					value="���Ҹ������Դ" id="getOntologiesAndInfosByKeywords">
			</form>
			<div class="remark">���ú�����getOntologiesAndInfosByKeywords</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/search/=getConceptsByKeywords"
				method='post'>
				<input type="text" name="getConceptsByKeywords" placeholder="��д�����"
					title="���ӣ�[&quot;����Ժ&quot;]"> <input type="button"
					value="ͨ���ؼ��ʻ�ȡ" id="getConceptsByKeywords">
			</form>
			<div class="remark">���ú�����getConceptsByKeywords</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/search/getExtensionalConcepts"
				method='post'>
				<input type="text" name="getExtensionalConcepts" placeholder="id"
					title="���ӣ�concept_class/thing"> <input type="text"
					name="getExtensionalConceptsType" placeholder="type"
					title="���ӣ�clazz"> <input type="text"
					name="getExtensionalConceptsNum" placeholder="num" title="���ӣ�1">
				<input type="button" value="��ȡ���Ӹ���" id="getExtensionalConcepts">
			</form>
			<div class="remark">���ú�����getExtensionalConcepts</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/search/getIntensionalConcepts"
				method='post'>
				<input type="text" name="getIntensionalConcepts" placeholder="id"
					title="���ӣ�concept_class/thing"> <input type="button"
					value="��ȡ�ں�����" id="getIntensionalConcepts">
			</form>
			<div class="remark">���ú�����getIntensionalConcepts</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/search/getBodysOfObjects"
				method='post'>
				<input type="text" name="getBodysOfObjects" placeholder="ids"
					title="���ӣ�[&quot;concept_class/thing&quot;]"> <input
					type="button" value="��ȡĳЩ������ṩ��λ�����壩" id="getBodysOfObjects">
			</form>
			<div class="remark">���ú�����getBodysOfObjects</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/search/getOntologiesByConceptIdsAndConditions"
				method='post'>
				<input type="text" name="getOntologiesByConceptIds"
					placeholder="ids" title="���ӣ�[&quot;concept_class/thing&quot;]">
				<input type="text" name="getOntologiesByConditions"
					placeholder="Condition"
					title="���ӣ�[{'relationName':'����', 'direction':'outbound'},{'relationName':'����', 'direction':'any'}...]">
				<input type="button" value="ͨ������id���Ϻ͹�ϵ����������ȡ����"
					id="getOntologiesByConceptIdsAndConditions">
			</form>
			<div class="remark">���ú�����getOntologiesByConceptIdsAndConditions</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/search/getRelationDefinitionsByNames"
				method='post'>
				<input type="text" name="getRelationDefinitionsByNameArr"
					placeholder="names" title="���ӣ�[&quot;����Ժ&quot;]"> <input
					type="button" value="ͨ�����ֻ�ȡ��ϵ����" id="getRelationDefinitionsByNames">
			</form>
			<div class="remark">���ú�����getRelationDefinitionsByNames</div>
			<br>

			<!-- <form action="/ExIAServer/service/getKnowledge/"
				method='post'>
				<input type="text" name="mount" placeholder="���ص�" title="���ӣ�xiaoliu">
				<input type="text" name="params" placeholder="����"
					title="���ӣ�id= concept_class/thing&condition={aaa:111}"> <input
					type="button" value="�Զ���ű�" id="customScript">
			</form>
			<div class="remark">�������</div> -->
			<br>

			<form
				action="/ExIAServer/services/ontology/search/getRecommendedKeywords"
				method='post'>
				<input type="text" name="getRecommendedKeywords" placeholder="names"
					title="���ӣ�[&quot;����Ժ&quot;]"> <input type="button"
					value="��ȡĳ�������ʵ��Ƽ������ʻ�" id="getRecommendedKeywords">
			</form>
			<div class="remark">���ú�����getRecommendedKeywords</div>
			<br>

			<form
				action="/ExIAServer/services/ontology/search/searchConceptDefsOfText"
				method='post'>
				<input type="text" name="searchConceptDefsOfText"
					placeholder="names" title="���ӣ��л����񹲺͹�ס���ͳ��罨�貿"> <input
					type="button" value="���ݹؼ��ʾ�������֪ʶ" id="searchConceptDefsOfText">
			</form>
			<div class="remark">���ú�����searchConceptDefsOfText</div>
			<br>

			<form
				action="/ExIAServer/services/ontology/search/getRelatedFiveElements"
				method='post'>
				<input type="text" name="getRelatedFiveElements" placeholder="names"
					title="���ӣ�[&quot;����滮&quot;]"> <input type="button"
					value="���ݹؼ��ʲ�ѯ5Ҫ��" id="getRelatedFiveElements">
			</form>
			<div class="remark">���ú�����getRelatedFiveElements</div>
			<br />

			<form
				action="/ExIAServer/services/ontology/search/ruleCATClassifyText"
				method='post'>
				<input type="text" name="ruleCATClassifyText" placeholder="�ı�����"
					title="���ӣ�һЩ��Ա�ɲ��͵���֯��ѧϰ״�������ֹ�"/><br/>
				<input type="button"
					value="�����������ݲ�����������" id="btnRuleCATClassifyText">
			</form>
			<div class="remark">���ú�����ruleCATClassifyText</div>
			<br/>

			<form
				action="/ExIAServer/services/ontology/search/searchRelevantContent"
				method='post'>
				<input type="text" name="searchRelevantContentText" placeholder="�ı�����"
					title="���ӣ�һЩ��Ա�ɲ��͵���֯��ѧϰ״�������ֹ�"/><br/>
				<input type="text" name="searchRelevantContentDomainIds" placeholder="����IDs"
					title="���ӣ�[&quot;concept_class/70C565CE-0FAC-2E64-1521-BE95108A9213&quot;]"/> <br/>
				<input type="text" name="searchRelevantContentParentIds" placeholder="����IDs"
					title="���ӣ�[&quot;concept_class/70C565CE-0FAC-2E64-1521-BE95108A9213&quot;]"/><br/>
				<input type="button"
					value="�����������ݲ�������Ը���" id="btnSearchRelevantContent">
			</form>
			<div class="remark">���ú�����searchRelevantContent</div>
			<br/>
			
<!-- 			<form -->
<!-- 				action="/ExIAServer/services/ontology/search/searchRelevantResource" -->
<!-- 				method='post'> -->
<!-- 				<input type="text" name="searchRelevantResourceText" placeholder="�ı�����" -->
<!-- 					title="���ӣ�������,�����ƶ�"/><br/> -->
<!-- 				<input type="text" name="searchRelevantResourceDomains" placeholder="names" -->
<!-- 					title="���ӣ�[&quot;�⽻&quot;,&quot;����&quot;]"/> <br/> -->
<!-- 				<input type="button" -->
<!-- 					value="�����������ݲ�������Ը���" id="btnSearchRelevantResource"> -->
<!-- 			</form> -->
<!-- 			<div class="remark">���ú�����searchRelevantResource</div> -->
<!-- 			<br/> -->
			
			<form
				action="/ExIAServer/services/ontology/textsearch/searchText"
				method='post'>
				<input type="text" name="searchText" placeholder="�ı�����"
					title="���ӣ�������,�����ƶ�"/><br/>
				<input type="button"
					value="ȫ�ļ���" id="btnSearchText">
			</form>
			<div class="remark">���ú�����searchText</div>
			<br/>
			
			<form
				action="/ExIAServer/services/ontology/textsearch/absText"
				method='post'>
				<textarea class="absText" style="width:400px;height:400px" placeholder="�ı�����"
					title="���ӣ�������,�����ƶ�"></textarea><br/>
				<input type="button"
					value="��ȡ�ı�ժҪ�������" id="btnAbsText">
			</form>
			<div class="remark">���ú�����absText</div>
			<br/>
			
			<form
				action="/ExIAServer/services/ontology/search/nagaoText"
				method='post'>
				<textarea class="nagaoText" style="width:400px;height:400px" placeholder="�ı�����"
					title="���ӣ�������ʮ���������ҹ�������ҵ��С���󣬷�չ�Ͽ죬ȡ���������ĳɼ����ر����ڵ���ʮһ������ȫ���Ժ�����ҵ�ķ�չ��ΪѸ�١�"></textarea><br/>
				<input type="button"
					value="Nagao�㷨��ȡ��" id="btnNagaoText">
			</form>
			<div class="remark">���ú�����nagaoText</div>
			<br>
			
			<form
				action="/ExIAServer/services/ontology/recognise/concepts"
				method='post'>
				<input type="title" name="recogniseTitle" placeholder="�ĵ�����"
					title="���ӣ������г�������滮��"><br/>
				<input type="keyWord" name="recogniseKeyWord" placeholder="�����"
					title="���ӣ�[&quot;����Ժ&quot;]"><br/>
				<input type="text" name="recogniseText" placeholder="�ĵ�����"
					title="���ӣ������г�������滮��"><br/>
				<input type="text" name="clsNames" placeholder="�޶���Ҫ������"
					title="���ӣ�[&quot;����&quot;]">
				<input type="button"
					value="���ݱ���ʶ����Ҫ��" id="btnRecogniseOntology">
			</form>
			<div class="remark">���ú�����getConcepts</div>
			<br>
		</div>
		<div style="float: left; width: 600px">
			<textarea id="result" style="width: 600px; height: 800px"></textarea>
		</div>
	</div>

</body>

<script type="text/javascript">
	var basePath = '/ExIAServer/';
	$('input[type="button"]')
			.on(
					'click',
					function() {
						var url;
						var datas;
						if ($(this).attr('id') == 'getConcept') {
							url = basePath
									+ "services/ontology/search/getConcept";
							var content = $('input[name="getConcept"]').val();
							datas = {
								"id" : content
							};
						} else if ($(this).attr('id') == 'getOntologiesByKeywords') {
							url = basePath
									+ "services/ontology/search/getOntologiesByKeywords";
							var contents = $(
									'input[name="getOntologiesByKeywords"]')
									.val();
							datas = {
								"words" : contents
							};
						} else if ($(this).attr('id') == 'getOntologiesByIds') {
							url = basePath
									+ "services/ontology/search/getOntologiesByIds";
							var content = $('input[name="getOntologiesByIds"]')
									.val();
							/* 	  		var res = content.split(","); */
							datas = {
								"ids" : content
							};
						} else if ($(this).attr('id') == 'getOntologies') {
							url = basePath
									+ "services/ontology/search/getOntologies";
							var id = $('input[name="conceptId1"]').val();
							var condi = $('input[name="cdit1"]').val();
							datas = {
								"conceptId" : id,
								"conditions" : condi
							};
						} else if ($(this).attr('id') == 'getInformationsByIdsAndCondition') {
							url = basePath
									+ "services/ontology/getinfo/getInformationsByIdsAndCondition";
							var id = $('input[name="conceptId2"]').val();
							var condi = $('input[name="cdit3"]').val();
							datas = {
								"ids" : id,
								"infoCondition" : condi
							};
						} else if ($(this).attr('id') == 'getInformationsByKeywordsAndCondition') {
							url = basePath
									+ "services/ontology/getinfo/getInformationsByKeywordsAndCondition";
							var keywords = $('input[name="keywords"]').val();
							var condi = $('input[name="cdit5"]').val();
							datas = {
								"words" : keywords,
								"infoCondition" : condi
							};
						} else if ($(this).attr('id') == 'getInformations') {
							url = basePath
									+ "services/ontology/getinfo/getInformations";
							var conceptId = $('input[name="conceptId3"]').val();
							var condi = $('input[name="cdit7"]').val();
							var incdit = $('input[name="incdit2"]').val();
							datas = {
								"conceptId" : conceptId,
								"conditions" : condi,
								"infoCondition" : incdit
							};
						} else if ($(this).attr('id') == 'getInformationsByUrlAndAttribute') {
							url = basePath
									+ "services/ontology/getinfo/getInformationsByUrlAndAttribute";
							datas = {};
							datas.url = encodeURIComponent($(
									"input[name='url']").val());
							datas.attribute = $("input[name='attribute']")
									.val();
						} else if ($(this).attr('id') == 'getTermsByText') {
							url = basePath
									+ 'services/ontology/search/getTermsByText';
							datas = {};
							datas.text = $("input[name='text']").val();
						} else if ($(this).attr('id') == 'getActionConceptDefsByBodyAndObject') {
							url = basePath
									+ 'services/ontology/search/getActionConceptDefsByBodyAndObject';
							datas = {};
							datas.context = $("input[name='context1']").val();
							datas.bodyConceptId = $(
									"input[name='bodyConceptId']").val();
							datas.objConceptId = $("input[name='objConceptId']")
									.val();
						} else if ($(this).attr('id') == 'getRelatedKnowledgesByAction') {
							url = basePath
									+ 'services/ontology/search/getRelatedKnowledgesByAction';
							datas = {};
							datas.context = $("input[name='context2']").val();
							datas.actConceptId = $("input[name='actConceptId']")
									.val();
						} else if (($(this).attr('id') == 'getOntologiesAndInfosByKeywords')) {
							//getOntologiesAndInfosByKeywords
							url = basePath
									+ 'services/ontology/search/getOntologiesAndInfosByKeywords';
							datas = {};
							datas.context = $("input[name='context3']").val();
							datas.words = $("input[name='words1']").val();
						} else if ($(this).attr('id') == 'getConceptsByKeywords') {
							url = basePath
									+ 'services/ontology/search/getConceptsByKeywords';
							datas = {};
							datas.words = $(
									"input[name='getConceptsByKeywords']")
									.val();
						} else if ($(this).attr('id') == 'getExtensionalConcepts') {
							url = basePath
									+ 'services/ontology/search/getExtensionalConcepts';
							datas = {};
							datas.conceptId = $(
									"input[name='getExtensionalConcepts']")
									.val();
							datas.type = $(
									"input[name='getExtensionalConceptsType']")
									.val();
							datas.num = $(
									"input[name='getExtensionalConceptsNum']")
									.val();
						} else if ($(this).attr('id') == 'getIntensionalConcepts') {
							url = basePath
									+ 'services/ontology/search/getIntensionalConcepts';
							datas = {};
							datas.conceptId = $(
									"input[name='getIntensionalConcepts']")
									.val();
						} else if ($(this).attr('id') == 'getBodysOfObjects') {
							url = basePath
									+ 'services/ontology/search/getBodysOfObjects';
							datas = {};
							datas.objectIds = $(
									"input[name='getBodysOfObjects']").val();
						} else if ($(this).attr("id") == 'getOntologiesByConceptIdsAndConditions') {
							url = basePath
									+ 'services/ontology/search/getOntologiesByConceptIdsAndConditions';
							datas = {};
							datas.conceptIds = $(
									"input[name='getOntologiesByConceptIds']")
									.val();
							datas.conditions = $(
									"input[name='getOntologiesByConditions']")
									.val();
						} else if ($(this).attr("id") == 'getRelationDefinitionsByNames') {
							url = basePath
									+ 'services/ontology/search/getRelationDefinitionsByNames';
							datas = {};
							datas.names = $(
									"input[name='getRelationDefinitionsByNameArr']")
									.val();
						} else if ($(this).attr("id") == 'customScript') {
							var mount = $("input[name='mount']").val();
							var params = $("input[name='params']").val();
							url = basePath + 'service/' + mount + "?" + params;
							datas = {};
						} else if ($(this).attr("id") == 'getRecommendedKeywords') {
							console.log(1111);
							var words = $(
									"input[name='getRecommendedKeywords']")
									.val();
							url = basePath
									+ 'services/ontology/search/getRecommendedKeywords';
							datas = {};
							datas.words = words;
						} else if ($(this).attr("id") == 'searchConceptDefsOfText') {
							var text = $(
									"input[name='searchConceptDefsOfText']")
									.val();
							url = basePath
									+ 'services/ontology/search/searchConceptDefsOfText';
							datas = {};
							datas.text = text;
						} else if ($(this).attr("id") == 'getRelatedFiveElements') {
							var text = $("input[name='getRelatedFiveElements']")
									.val();
							url = basePath
									+ 'services/ontology/search/getRelatedFiveElements';
							datas = {};
							datas.keywords = text;
						} else if($(this).attr('id')== 'btnSearchRelevantContent'){
							url = basePath
								+ 'services/ontology/search/searchRelevantContent';
							datas = {};
							datas.text = $("input[name='searchRelevantContentText']").val();
							datas.domainIds =  $("input[name='searchRelevantContentDomainIds']").val();
							datas.parentIds =  $("input[name='searchRelevantContentParentIds']").val();
						} else if($(this).attr('id') == 'btnRuleCATClassifyText'){
							url = basePath
								+ 'services/ontology/search/ruleCATClassifyText';
							datas = {};
							datas.text = $("input[name='ruleCATClassifyText']").val();
						} else if($(this).attr('id') == 'btnSearchRelevantResource'){
							url = basePath
								+ 'services/ontology/search/searchRelevantResource';
							datas = {};
							datas.text = $("input[name='searchRelevantResourceText']").val();
							datas.domains =  $("input[name='searchRelevantResourceDomains']").val();
						} else if($(this).attr('id') == 'btnSearchText'){
							url = basePath
								+ 'services/ontology/textsearch/searchText';
							datas = {};
							var text = $("input[name='searchText']").val();
							var conditions = {};
							conditions.text = text;
							datas.conditions = JSON.stringify(conditions);
						} else if($(this).attr('id') == 'btnAbsText'){
							url = basePath
								+ 'services/ontology/textsearch/absText';
							datas = {};
							var text = $(".absText").val();
							var conditions = {};
							conditions.text = text;
							datas = conditions;
						}else if($(this).attr('id') == 'btnNagaoText'){
							url = basePath
								+ 'services/ontology/search/nagaoText';
							datas = {};
							var text = $(".nagaoText").val();
							var conditions = {};
							conditions.text = text;
							datas = conditions;
						}else if($(this).attr('id') == 'btnRecogniseOntology'){
							url = basePath
								+ 'services/ontology/recognise/concepts';
							datas = {};
							var title = $("input[name='recogniseTitle']").val();
							var keyWord = $("input[name='recogniseKeyWord']").val();
							var text = $("input[name='recogniseText']").val();
							var clsNames = $("input[name='clsNames']").val();
							var conditions = {};
							conditions.title = title;
							conditions.keyWord = keyWord;
							conditions.text = text;
							conditions.clsNames = clsNames;
							datas = conditions;
						}
						

						$('#result').val('');
						
						$.ajax({
							//�ύ���ݵ����� POST GET
							type : "POST",
							//�ύ����ַ
							url : url,
							//�ύ������
							data : datas,
							//�������ݵĸ�ʽ
							datatype : "json",
							//����ִ�к���õĺ���
							success : function(result) {
								//console.log(result);
								$('#result').val(formatJson(result, ''));
							},
							//���ó���ִ�еĺ���
							error : function() {
								//���������
							}
						});
					});

	// Example usage: http://jsfiddle.net/q2gnX/

	function formatJson(json, options) {
		var reg = null, formatted = '', pad = 0, PADDING = '    '; // one can also use '\t' or a different number of spaces

		// optional settings
		options = options || {};
		// remove newline where '{' or '[' follows ':'
		options.newlineAfterColonIfBeforeBraceOrBracket = (options.newlineAfterColonIfBeforeBraceOrBracket === true) ? true
				: false;
		// use a space after a colon
		options.spaceAfterColon = (options.spaceAfterColon === false) ? false
				: true;

		// begin formatting...
		if (typeof json !== 'string') {
			// make sure we start with the JSON as a string
			json = JSON.stringify(json);
		} else {
			// is already a string, so parse and re-stringify in order to remove extra whitespace
			json = JSON.parse(json);
			json = JSON.stringify(json);
		}

		// add newline before and after curly braces
		reg = /([\{\}])/g;
		json = json.replace(reg, '\r\n$1\r\n');

		// add newline before and after square brackets
		reg = /([\[\]])/g;
		json = json.replace(reg, '\r\n$1\r\n');

		// add newline after comma
		reg = /(\,)/g;
		json = json.replace(reg, '$1\r\n');

		// remove multiple newlines
		reg = /(\r\n\r\n)/g;
		json = json.replace(reg, '\r\n');

		// remove newlines before commas
		reg = /\r\n\,/g;
		json = json.replace(reg, ',');

		// optional formatting...
		if (!options.newlineAfterColonIfBeforeBraceOrBracket) {
			reg = /\:\r\n\{/g;
			json = json.replace(reg, ':{');
			reg = /\:\r\n\[/g;
			json = json.replace(reg, ':[');
		}
		if (options.spaceAfterColon) {
			reg = /\:/g;
			json = json.replace(reg, ': ');
		}

		$.each(json.split('\r\n'), function(index, node) {
			var i = 0, indent = 0, padding = '';

			if (node.match(/\{$/) || node.match(/\[$/)) {
				indent = 1;
			} else if (node.match(/\}/) || node.match(/\]/)) {
				if (pad !== 0) {
					pad -= 1;
				}
			} else {
				indent = 0;
			}

			for (i = 0; i < pad; i++) {
				padding += PADDING;
			}

			formatted += padding + node + '\r\n';
			pad += indent;
		});

		return formatted;
	};
	//�ô���Ƭ��������: http://www.sharejs.com/codes/javascript/5452
</script>
</html>