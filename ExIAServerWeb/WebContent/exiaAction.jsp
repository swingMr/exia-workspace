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
				<input type="text" name="getConcept" placeholder="填写概念ID"
					title="例子：concept_class/thing"> <input type="button"
					value="获取概念" id="getConcept">
			</form>
			<div class="remark">调用函数：getConcept</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/search/getOntologiesByKeywords"
				method='post'>
				<input type="text" name="getOntologiesByKeywords"
					placeholder="填写主题词" title="例子：[&quot;国务院&quot;]"> <input
					type="button" value="通过关键词获取" id="getOntologiesByKeywords">
			</form>
			<div class="remark">调用函数：getOntologiesByKeywords</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/search/getOntologiesByIds"
				method='post'>
				<input type="text" name="getOntologiesByIds" value=""
					placeholder="填写概念IDs" title="例子：[&quot;concept_class/thing&quot;]">
				<input type="button" value="通过ID获取相关本体,请用逗号隔开"
					id="getOntologiesByIds">
			</form>
			<div class="remark">调用函数：getOntologiesByIds</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/search/getOntologies"
				method='post'>
				<input type="text" name="conceptId1" placeholder="填写conceptId"
					title="例子：concept_class/thing"> <input type="text"
					name="cdit1" placeholder="概念过滤条件"
					title="例子：[&quot;elementType&quot;:&quot;concept&quot;]"> <input
					type="button" value="获取相关本体" id="getOntologies">
			</form>
			<div class="remark">调用函数：getOntologies</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/getinfo/getInformationsByIdsAndCondition"
				method='post'>
				<input type="text" name="conceptId2" placeholder="填写概念IDs"
					title="例子：[&quot;concept_class/thing&quot;]"> <input
					type="text" name="cdit3" placeholder="填写概念过滤条件"
					title="例子：[&quot;体裁&quot;:&quot;法律&quot;]"> <input
					type="button" value="通过概念查找关联信息"
					id="getInformationsByIdsAndCondition">
			</form>
			<div class="remark">调用函数：getInformationsByIdsAndCondition</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/getinfo/getInformationsByKeywordsAndConditionn"
				method='post'>
				<input type="text" name="keywords" placeholder="填写关键词"
					title="例子：[&quot;国务院&quot;]"> <input type="text"
					name="cdit5" placeholder="填写概念过滤条件"
					title="例子：[&quot;体裁&quot;:&quot;法律&quot;]"> <input
					type="button" value="通过关键词查找关联文档"
					id="getInformationsByKeywordsAndCondition">
			</form>
			<div class="remark">调用函数：getInformationsByKeywordsAndCondition</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/getinfo/getInformations"
				method='post'>
				<input type="text" name="conceptId3" placeholder="填写概念ID"
					title="例子：concept_class/thing"> <input type="text"
					name="cdit7" placeholder="填写过滤条件"
					title="例子：[&quot;elementType&quot;:&quot;concept&quot;]"> <input
					type="text" name="incdit2" placeholder="填写信息资源过滤条件"
					title="例子：[&quot;体裁&quot;:&quot;法律&quot;]"> <input
					type="button" value="查找关联文档" id="getInformations">
			</form>
			<div class="remark">调用函数：getInformations</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/getinfo/getInformationsByUrlAndAttribute"
				method='post'>
				<input type="text" name="url" placeholder="填写URL"
					title="例子：http://<host>/knowledgeDocAttr?体裁={体裁}"> <input
					type="text" name="attribute" placeholder="填写属性"
					title="例子：[&quot;体裁&quot;:&quot;法律&quot;]"> <input
					type="button" value="通过url和属性查找关联信息"
					id="getInformationsByUrlAndAttribute">
			</form>
			<div class="remark">调用函数：getInformationsByUrlAndAttribute</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/search/getTermsByText"
				method='post'>
				<input type="text" name="text" placeholder="填写文本"
					title="例子：李克强在国务院工作"> <input type="button" value="分词"
					id="getTermsByText">
			</form>
			<div class="remark">调用函数：getTermsByText</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/search/getActionConceptDefsByBodyAndObject"
				method='post'>
				<input type="text" name="context1" placeholder="填写上下文"
					title="例子：{&quot;CURRENT_BOBY_IDS&quot;:[&quot;xxx&quot;]}">
				<input type="text" name="bodyConceptId" placeholder="主体概念id"
					title="例子：bodyConceptId"> <input type="text"
					name="objConceptId" placeholder="客体概念id" title="例子：objConceptId">
				<input type="button" value="查找行为"
					id="getActionConceptDefsByBodyAndObject">
			</form>
			<div class="remark">调用函数：getActionConceptDefsByBodyAndObject</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/search/getRelatedKnowledgesByAction"
				method='post'>
				<input type="text" name="context2" placeholder="填写上下文"
					title="例子：{&quot;CURRENT_BOBY_IDS&quot;:[&quot;xxx&quot;]}">
				<input type="text" name="actConceptId" placeholder="行为概念ID"
					title="例子：actConceptId"> <input type="button"
					value="通过行为查找概念" id="getRelatedKnowledgesByAction">
			</form>
			<div class="remark">调用函数：getRelatedKnowledgesByAction</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/search/getOntologiesAndInfosByKeywords"
				method='post'>
				<input type="text" name="context3" placeholder="填写上下文"
					title="例子：{&quot;CURRENT_BOBY_IDS&quot;:[&quot;xxx&quot;]}">
				<input type="text" name="words1" placeholder="主题词"
					title="例子：[&quot;国务院&quot;]"> <input type="button"
					value="查找概念和资源" id="getOntologiesAndInfosByKeywords">
			</form>
			<div class="remark">调用函数：getOntologiesAndInfosByKeywords</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/search/=getConceptsByKeywords"
				method='post'>
				<input type="text" name="getConceptsByKeywords" placeholder="填写主题词"
					title="例子：[&quot;国务院&quot;]"> <input type="button"
					value="通过关键词获取" id="getConceptsByKeywords">
			</form>
			<div class="remark">调用函数：getConceptsByKeywords</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/search/getExtensionalConcepts"
				method='post'>
				<input type="text" name="getExtensionalConcepts" placeholder="id"
					title="例子：concept_class/thing"> <input type="text"
					name="getExtensionalConceptsType" placeholder="type"
					title="例子：clazz"> <input type="text"
					name="getExtensionalConceptsNum" placeholder="num" title="例子：1">
				<input type="button" value="获取外延概念" id="getExtensionalConcepts">
			</form>
			<div class="remark">调用函数：getExtensionalConcepts</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/search/getIntensionalConcepts"
				method='post'>
				<input type="text" name="getIntensionalConcepts" placeholder="id"
					title="例子：concept_class/thing"> <input type="button"
					value="获取内涵概念" id="getIntensionalConcepts">
			</form>
			<div class="remark">调用函数：getIntensionalConcepts</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/search/getBodysOfObjects"
				method='post'>
				<input type="text" name="getBodysOfObjects" placeholder="ids"
					title="例子：[&quot;concept_class/thing&quot;]"> <input
					type="button" value="获取某些客体的提供单位（主体）" id="getBodysOfObjects">
			</form>
			<div class="remark">调用函数：getBodysOfObjects</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/search/getOntologiesByConceptIdsAndConditions"
				method='post'>
				<input type="text" name="getOntologiesByConceptIds"
					placeholder="ids" title="例子：[&quot;concept_class/thing&quot;]">
				<input type="text" name="getOntologiesByConditions"
					placeholder="Condition"
					title="例子：[{'relationName':'外延', 'direction':'outbound'},{'relationName':'输入', 'direction':'any'}...]">
				<input type="button" value="通过概念id集合和关系过滤条件获取本体"
					id="getOntologiesByConceptIdsAndConditions">
			</form>
			<div class="remark">调用函数：getOntologiesByConceptIdsAndConditions</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/search/getRelationDefinitionsByNames"
				method='post'>
				<input type="text" name="getRelationDefinitionsByNameArr"
					placeholder="names" title="例子：[&quot;国务院&quot;]"> <input
					type="button" value="通过名字获取关系定义" id="getRelationDefinitionsByNames">
			</form>
			<div class="remark">调用函数：getRelationDefinitionsByNames</div>
			<br>

			<!-- <form action="/ExIAServer/service/getKnowledge/"
				method='post'>
				<input type="text" name="mount" placeholder="挂载点" title="例子：xiaoliu">
				<input type="text" name="params" placeholder="参数"
					title="例子：id= concept_class/thing&condition={aaa:111}"> <input
					type="button" value="自定义脚本" id="customScript">
			</form>
			<div class="remark">推理服务</div> -->
			<br>

			<form
				action="/ExIAServer/services/ontology/search/getRecommendedKeywords"
				method='post'>
				<input type="text" name="getRecommendedKeywords" placeholder="names"
					title="例子：[&quot;国务院&quot;]"> <input type="button"
					value="获取某个检索词的推荐检索词汇" id="getRecommendedKeywords">
			</form>
			<div class="remark">调用函数：getRecommendedKeywords</div>
			<br>

			<form
				action="/ExIAServer/services/ontology/search/searchConceptDefsOfText"
				method='post'>
				<input type="text" name="searchConceptDefsOfText"
					placeholder="names" title="例子：中华人民共和国住房和城乡建设部"> <input
					type="button" value="根据关键词句检索相关知识" id="searchConceptDefsOfText">
			</form>
			<div class="remark">调用函数：searchConceptDefsOfText</div>
			<br>

			<form
				action="/ExIAServer/services/ontology/search/getRelatedFiveElements"
				method='post'>
				<input type="text" name="getRelatedFiveElements" placeholder="names"
					title="例子：[&quot;城乡规划&quot;]"> <input type="button"
					value="根据关键词查询5要素" id="getRelatedFiveElements">
			</form>
			<div class="remark">调用函数：getRelatedFiveElements</div>
			<br />

			<form
				action="/ExIAServer/services/ontology/search/ruleCATClassifyText"
				method='post'>
				<input type="text" name="ruleCATClassifyText" placeholder="文本内容"
					title="例子：一些党员干部和党组织的学习状况不容乐观"/><br/>
				<input type="button"
					value="根据输入内容查找所属领域" id="btnRuleCATClassifyText">
			</form>
			<div class="remark">调用函数：ruleCATClassifyText</div>
			<br/>

			<form
				action="/ExIAServer/services/ontology/search/searchRelevantContent"
				method='post'>
				<input type="text" name="searchRelevantContentText" placeholder="文本内容"
					title="例子：一些党员干部和党组织的学习状况不容乐观"/><br/>
				<input type="text" name="searchRelevantContentDomainIds" placeholder="领域IDs"
					title="例子：[&quot;concept_class/70C565CE-0FAC-2E64-1521-BE95108A9213&quot;]"/> <br/>
				<input type="text" name="searchRelevantContentParentIds" placeholder="父类IDs"
					title="例子：[&quot;concept_class/70C565CE-0FAC-2E64-1521-BE95108A9213&quot;]"/><br/>
				<input type="button"
					value="根据输入内容查找相关性概念" id="btnSearchRelevantContent">
			</form>
			<div class="remark">调用函数：searchRelevantContent</div>
			<br/>
			
<!-- 			<form -->
<!-- 				action="/ExIAServer/services/ontology/search/searchRelevantResource" -->
<!-- 				method='post'> -->
<!-- 				<input type="text" name="searchRelevantResourceText" placeholder="文本内容" -->
<!-- 					title="例子：共产党,规章制度"/><br/> -->
<!-- 				<input type="text" name="searchRelevantResourceDomains" placeholder="names" -->
<!-- 					title="例子：[&quot;外交&quot;,&quot;教育&quot;]"/> <br/> -->
<!-- 				<input type="button" -->
<!-- 					value="根据输入内容查找相关性概念" id="btnSearchRelevantResource"> -->
<!-- 			</form> -->
<!-- 			<div class="remark">调用函数：searchRelevantResource</div> -->
<!-- 			<br/> -->
			
			<form
				action="/ExIAServer/services/ontology/textsearch/searchText"
				method='post'>
				<input type="text" name="searchText" placeholder="文本内容"
					title="例子：共产党,规章制度"/><br/>
				<input type="button"
					value="全文检索" id="btnSearchText">
			</form>
			<div class="remark">调用函数：searchText</div>
			<br/>
			
			<form
				action="/ExIAServer/services/ontology/textsearch/absText"
				method='post'>
				<textarea class="absText" style="width:400px;height:400px" placeholder="文本内容"
					title="例子：共产党,规章制度"></textarea><br/>
				<input type="button"
					value="提取文本摘要和主题词" id="btnAbsText">
			</form>
			<div class="remark">调用函数：absText</div>
			<br/>
			
			<form
				action="/ExIAServer/services/ontology/search/nagaoText"
				method='post'>
				<textarea class="nagaoText" style="width:400px;height:400px" placeholder="文本内容"
					title="例子：建国三十五年来，我国的民航事业从小到大，发展较快，取得了显著的成绩，特别是在党的十一届三中全会以后，民航事业的发展更为迅速。"></textarea><br/>
				<input type="button"
					value="Nagao算法提取词" id="btnNagaoText">
			</form>
			<div class="remark">调用函数：nagaoText</div>
			<br>
			
			<form
				action="/ExIAServer/services/ontology/recognise/concepts"
				method='post'>
				<input type="title" name="recogniseTitle" placeholder="文档标题"
					title="例子：广州市城乡总体规划书"><br/>
				<input type="keyWord" name="recogniseKeyWord" placeholder="主题词"
					title="例子：[&quot;国务院&quot;]"><br/>
				<input type="text" name="recogniseText" placeholder="文档内容"
					title="例子：广州市城乡总体规划书"><br/>
				<input type="text" name="clsNames" placeholder="限定的要素类型"
					title="例子：[&quot;领域&quot;]">
				<input type="button"
					value="根据标题识别五要素" id="btnRecogniseOntology">
			</form>
			<div class="remark">调用函数：getConcepts</div>
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
							//提交数据的类型 POST GET
							type : "POST",
							//提交的网址
							url : url,
							//提交的数据
							data : datas,
							//返回数据的格式
							datatype : "json",
							//调用执行后调用的函数
							success : function(result) {
								//console.log(result);
								$('#result').val(formatJson(result, ''));
							},
							//调用出错执行的函数
							error : function() {
								//请求出错处理
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
	//该代码片段来自于: http://www.sharejs.com/codes/javascript/5452
</script>
</html>