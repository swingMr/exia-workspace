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
				<input type="text" name="getConcept" placeholder="ÌîÐ´¸ÅÄîID"
					title="Àý×Ó£ºconcept_class/thing"> <input type="button"
					value="»ñÈ¡¸ÅÄî" id="getConcept">
			</form>
			<div class="remark">µ÷ÓÃº¯Êý£ºgetConcept</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/search/getOntologiesByKeywords"
				method='post'>
				<input type="text" name="getOntologiesByKeywords"
					placeholder="ÌîÐ´Ö÷Ìâ´Ê" title="Àý×Ó£º[&quot;¹úÎñÔº&quot;]"> <input
					type="button" value="Í¨¹ý¹Ø¼ü´Ê»ñÈ¡" id="getOntologiesByKeywords">
			</form>
			<div class="remark">µ÷ÓÃº¯Êý£ºgetOntologiesByKeywords</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/search/getOntologiesByIds"
				method='post'>
				<input type="text" name="getOntologiesByIds" value=""
					placeholder="ÌîÐ´¸ÅÄîIDs" title="Àý×Ó£º[&quot;concept_class/thing&quot;]">
				<input type="button" value="Í¨¹ýID»ñÈ¡Ïà¹Ø±¾Ìå,ÇëÓÃ¶ººÅ¸ô¿ª"
					id="getOntologiesByIds">
			</form>
			<div class="remark">µ÷ÓÃº¯Êý£ºgetOntologiesByIds</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/search/getOntologies"
				method='post'>
				<input type="text" name="conceptId1" placeholder="ÌîÐ´conceptId"
					title="Àý×Ó£ºconcept_class/thing"> <input type="text"
					name="cdit1" placeholder="¸ÅÄî¹ýÂËÌõ¼þ"
					title="Àý×Ó£º[&quot;elementType&quot;:&quot;concept&quot;]"> <input
					type="button" value="»ñÈ¡Ïà¹Ø±¾Ìå" id="getOntologies">
			</form>
			<div class="remark">µ÷ÓÃº¯Êý£ºgetOntologies</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/getinfo/getInformationsByIdsAndCondition"
				method='post'>
				<input type="text" name="conceptId2" placeholder="ÌîÐ´¸ÅÄîIDs"
					title="Àý×Ó£º[&quot;concept_class/thing&quot;]"> <input
					type="text" name="cdit3" placeholder="ÌîÐ´¸ÅÄî¹ýÂËÌõ¼þ"
					title="Àý×Ó£º[&quot;Ìå²Ã&quot;:&quot;·¨ÂÉ&quot;]"> <input
					type="button" value="Í¨¹ý¸ÅÄî²éÕÒ¹ØÁªÐÅÏ¢"
					id="getInformationsByIdsAndCondition">
			</form>
			<div class="remark">µ÷ÓÃº¯Êý£ºgetInformationsByIdsAndCondition</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/getinfo/getInformationsByKeywordsAndConditionn"
				method='post'>
				<input type="text" name="keywords" placeholder="ÌîÐ´¹Ø¼ü´Ê"
					title="Àý×Ó£º[&quot;¹úÎñÔº&quot;]"> <input type="text"
					name="cdit5" placeholder="ÌîÐ´¸ÅÄî¹ýÂËÌõ¼þ"
					title="Àý×Ó£º[&quot;Ìå²Ã&quot;:&quot;·¨ÂÉ&quot;]"> <input
					type="button" value="Í¨¹ý¹Ø¼ü´Ê²éÕÒ¹ØÁªÎÄµµ"
					id="getInformationsByKeywordsAndCondition">
			</form>
			<div class="remark">µ÷ÓÃº¯Êý£ºgetInformationsByKeywordsAndCondition</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/getinfo/getInformations"
				method='post'>
				<input type="text" name="conceptId3" placeholder="ÌîÐ´¸ÅÄîID"
					title="Àý×Ó£ºconcept_class/thing"> <input type="text"
					name="cdit7" placeholder="ÌîÐ´¹ýÂËÌõ¼þ"
					title="Àý×Ó£º[&quot;elementType&quot;:&quot;concept&quot;]"> <input
					type="text" name="incdit2" placeholder="ÌîÐ´ÐÅÏ¢×ÊÔ´¹ýÂËÌõ¼þ"
					title="Àý×Ó£º[&quot;Ìå²Ã&quot;:&quot;·¨ÂÉ&quot;]"> <input
					type="button" value="²éÕÒ¹ØÁªÎÄµµ" id="getInformations">
			</form>
			<div class="remark">µ÷ÓÃº¯Êý£ºgetInformations</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/getinfo/getInformationsByUrlAndAttribute"
				method='post'>
				<input type="text" name="url" placeholder="ÌîÐ´URL"
					title="Àý×Ó£ºhttp://<host>/knowledgeDocAttr?Ìå²Ã={Ìå²Ã}"> <input
					type="text" name="attribute" placeholder="ÌîÐ´ÊôÐÔ"
					title="Àý×Ó£º[&quot;Ìå²Ã&quot;:&quot;·¨ÂÉ&quot;]"> <input
					type="button" value="Í¨¹ýurlºÍÊôÐÔ²éÕÒ¹ØÁªÐÅÏ¢"
					id="getInformationsByUrlAndAttribute">
			</form>
			<div class="remark">µ÷ÓÃº¯Êý£ºgetInformationsByUrlAndAttribute</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/search/getTermsByText"
				method='post'>
				<input type="text" name="text" placeholder="ÌîÐ´ÎÄ±¾"
					title="Àý×Ó£ºÀî¿ËÇ¿ÔÚ¹úÎñÔº¹¤×÷"> <input type="button" value="·Ö´Ê"
					id="getTermsByText">
			</form>
			<div class="remark">µ÷ÓÃº¯Êý£ºgetTermsByText</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/search/getActionConceptDefsByBodyAndObject"
				method='post'>
				<input type="text" name="context1" placeholder="ÌîÐ´ÉÏÏÂÎÄ"
					title="Àý×Ó£º{&quot;CURRENT_BOBY_IDS&quot;:[&quot;xxx&quot;]}">
				<input type="text" name="bodyConceptId" placeholder="Ö÷Ìå¸ÅÄîid"
					title="Àý×Ó£ºbodyConceptId"> <input type="text"
					name="objConceptId" placeholder="¿ÍÌå¸ÅÄîid" title="Àý×Ó£ºobjConceptId">
				<input type="button" value="²éÕÒÐÐÎª"
					id="getActionConceptDefsByBodyAndObject">
			</form>
			<div class="remark">µ÷ÓÃº¯Êý£ºgetActionConceptDefsByBodyAndObject</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/search/getRelatedKnowledgesByAction"
				method='post'>
				<input type="text" name="context2" placeholder="ÌîÐ´ÉÏÏÂÎÄ"
					title="Àý×Ó£º{&quot;CURRENT_BOBY_IDS&quot;:[&quot;xxx&quot;]}">
				<input type="text" name="actConceptId" placeholder="ÐÐÎª¸ÅÄîID"
					title="Àý×Ó£ºactConceptId"> <input type="button"
					value="Í¨¹ýÐÐÎª²éÕÒ¸ÅÄî" id="getRelatedKnowledgesByAction">
			</form>
			<div class="remark">µ÷ÓÃº¯Êý£ºgetRelatedKnowledgesByAction</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/search/getOntologiesAndInfosByKeywords"
				method='post'>
				<input type="text" name="context3" placeholder="ÌîÐ´ÉÏÏÂÎÄ"
					title="Àý×Ó£º{&quot;CURRENT_BOBY_IDS&quot;:[&quot;xxx&quot;]}">
				<input type="text" name="words1" placeholder="Ö÷Ìâ´Ê"
					title="Àý×Ó£º[&quot;¹úÎñÔº&quot;]"> <input type="button"
					value="²éÕÒ¸ÅÄîºÍ×ÊÔ´" id="getOntologiesAndInfosByKeywords">
			</form>
			<div class="remark">µ÷ÓÃº¯Êý£ºgetOntologiesAndInfosByKeywords</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/search/=getConceptsByKeywords"
				method='post'>
				<input type="text" name="getConceptsByKeywords" placeholder="ÌîÐ´Ö÷Ìâ´Ê"
					title="Àý×Ó£º[&quot;¹úÎñÔº&quot;]"> <input type="button"
					value="Í¨¹ý¹Ø¼ü´Ê»ñÈ¡" id="getConceptsByKeywords">
			</form>
			<div class="remark">µ÷ÓÃº¯Êý£ºgetConceptsByKeywords</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/search/getExtensionalConcepts"
				method='post'>
				<input type="text" name="getExtensionalConcepts" placeholder="id"
					title="Àý×Ó£ºconcept_class/thing"> <input type="text"
					name="getExtensionalConceptsType" placeholder="type"
					title="Àý×Ó£ºclazz"> <input type="text"
					name="getExtensionalConceptsNum" placeholder="num" title="Àý×Ó£º1">
				<input type="button" value="»ñÈ¡ÍâÑÓ¸ÅÄî" id="getExtensionalConcepts">
			</form>
			<div class="remark">µ÷ÓÃº¯Êý£ºgetExtensionalConcepts</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/search/getIntensionalConcepts"
				method='post'>
				<input type="text" name="getIntensionalConcepts" placeholder="id"
					title="Àý×Ó£ºconcept_class/thing"> <input type="button"
					value="»ñÈ¡ÄÚº­¸ÅÄî" id="getIntensionalConcepts">
			</form>
			<div class="remark">µ÷ÓÃº¯Êý£ºgetIntensionalConcepts</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/search/getBodysOfObjects"
				method='post'>
				<input type="text" name="getBodysOfObjects" placeholder="ids"
					title="Àý×Ó£º[&quot;concept_class/thing&quot;]"> <input
					type="button" value="»ñÈ¡Ä³Ð©¿ÍÌåµÄÌá¹©µ¥Î»£¨Ö÷Ìå£©" id="getBodysOfObjects">
			</form>
			<div class="remark">µ÷ÓÃº¯Êý£ºgetBodysOfObjects</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/search/getOntologiesByConceptIdsAndConditions"
				method='post'>
				<input type="text" name="getOntologiesByConceptIds"
					placeholder="ids" title="Àý×Ó£º[&quot;concept_class/thing&quot;]">
				<input type="text" name="getOntologiesByConditions"
					placeholder="Condition"
					title="Àý×Ó£º[{'relationName':'ÍâÑÓ', 'direction':'outbound'},{'relationName':'ÊäÈë', 'direction':'any'}...]">
				<input type="button" value="Í¨¹ý¸ÅÄîid¼¯ºÏºÍ¹ØÏµ¹ýÂËÌõ¼þ»ñÈ¡±¾Ìå"
					id="getOntologiesByConceptIdsAndConditions">
			</form>
			<div class="remark">µ÷ÓÃº¯Êý£ºgetOntologiesByConceptIdsAndConditions</div>
			<br>
			<form
				action="/ExIAServer/services/ontology/search/getRelationDefinitionsByNames"
				method='post'>
				<input type="text" name="getRelationDefinitionsByNameArr"
					placeholder="names" title="Àý×Ó£º[&quot;¹úÎñÔº&quot;]"> <input
					type="button" value="Í¨¹ýÃû×Ö»ñÈ¡¹ØÏµ¶¨Òå" id="getRelationDefinitionsByNames">
			</form>
			<div class="remark">µ÷ÓÃº¯Êý£ºgetRelationDefinitionsByNames</div>
			<br>

			<!-- <form action="/ExIAServer/service/getKnowledge/"
				method='post'>
				<input type="text" name="mount" placeholder="¹ÒÔØµã" title="Àý×Ó£ºxiaoliu">
				<input type="text" name="params" placeholder="²ÎÊý"
					title="Àý×Ó£ºid= concept_class/thing&condition={aaa:111}"> <input
					type="button" value="×Ô¶¨Òå½Å±¾" id="customScript">
			</form>
			<div class="remark">ÍÆÀí·þÎñ</div> -->
			<br>

			<form
				action="/ExIAServer/services/ontology/search/getRecommendedKeywords"
				method='post'>
				<input type="text" name="getRecommendedKeywords" placeholder="names"
					title="Àý×Ó£º[&quot;¹úÎñÔº&quot;]"> <input type="button"
					value="»ñÈ¡Ä³¸ö¼ìË÷´ÊµÄÍÆ¼ö¼ìË÷´Ê»ã" id="getRecommendedKeywords">
			</form>
			<div class="remark">µ÷ÓÃº¯Êý£ºgetRecommendedKeywords</div>
			<br>

			<form
				action="/ExIAServer/services/ontology/search/searchConceptDefsOfText"
				method='post'>
				<input type="text" name="searchConceptDefsOfText"
					placeholder="names" title="Àý×Ó£ºÖÐ»ªÈËÃñ¹²ºÍ¹ú×¡·¿ºÍ³ÇÏç½¨Éè²¿"> <input
					type="button" value="¸ù¾Ý¹Ø¼ü´Ê¾ä¼ìË÷Ïà¹ØÖªÊ¶" id="searchConceptDefsOfText">
			</form>
			<div class="remark">µ÷ÓÃº¯Êý£ºsearchConceptDefsOfText</div>
			<br>

			<form
				action="/ExIAServer/services/ontology/search/getRelatedFiveElements"
				method='post'>
				<input type="text" name="getRelatedFiveElements" placeholder="names"
					title="Àý×Ó£º[&quot;³ÇÏç¹æ»®&quot;]"> <input type="button"
					value="¸ù¾Ý¹Ø¼ü´Ê²éÑ¯5ÒªËØ" id="getRelatedFiveElements">
			</form>
			<div class="remark">µ÷ÓÃº¯Êý£ºgetRelatedFiveElements</div>
			<br />

			<form
				action="/ExIAServer/services/ontology/search/ruleCATClassifyText"
				method='post'>
				<input type="text" name="ruleCATClassifyText" placeholder="ÎÄ±¾ÄÚÈÝ"
					title="Àý×Ó£ºÒ»Ð©µ³Ô±¸É²¿ºÍµ³×éÖ¯µÄÑ§Ï°×´¿ö²»ÈÝÀÖ¹Û"/><br/>
				<input type="button"
					value="¸ù¾ÝÊäÈëÄÚÈÝ²éÕÒËùÊôÁìÓò" id="btnRuleCATClassifyText">
			</form>
			<div class="remark">µ÷ÓÃº¯Êý£ºruleCATClassifyText</div>
			<br/>

			<form
				action="/ExIAServer/services/ontology/search/searchRelevantContent"
				method='post'>
				<input type="text" name="searchRelevantContentText" placeholder="ÎÄ±¾ÄÚÈÝ"
					title="Àý×Ó£ºÒ»Ð©µ³Ô±¸É²¿ºÍµ³×éÖ¯µÄÑ§Ï°×´¿ö²»ÈÝÀÖ¹Û"/><br/>
				<input type="text" name="searchRelevantContentDomainIds" placeholder="ÁìÓòIDs"
					title="Àý×Ó£º[&quot;concept_class/70C565CE-0FAC-2E64-1521-BE95108A9213&quot;]"/> <br/>
				<input type="text" name="searchRelevantContentParentIds" placeholder="¸¸ÀàIDs"
					title="Àý×Ó£º[&quot;concept_class/70C565CE-0FAC-2E64-1521-BE95108A9213&quot;]"/><br/>
				<input type="button"
					value="¸ù¾ÝÊäÈëÄÚÈÝ²éÕÒÏà¹ØÐÔ¸ÅÄî" id="btnSearchRelevantContent">
			</form>
			<div class="remark">µ÷ÓÃº¯Êý£ºsearchRelevantContent</div>
			<br/>
			
<!-- 			<form -->
<!-- 				action="/ExIAServer/services/ontology/search/searchRelevantResource" -->
<!-- 				method='post'> -->
<!-- 				<input type="text" name="searchRelevantResourceText" placeholder="ÎÄ±¾ÄÚÈÝ" -->
<!-- 					title="Àý×Ó£º¹²²úµ³,¹æÕÂÖÆ¶È"/><br/> -->
<!-- 				<input type="text" name="searchRelevantResourceDomains" placeholder="names" -->
<!-- 					title="Àý×Ó£º[&quot;Íâ½»&quot;,&quot;½ÌÓý&quot;]"/> <br/> -->
<!-- 				<input type="button" -->
<!-- 					value="¸ù¾ÝÊäÈëÄÚÈÝ²éÕÒÏà¹ØÐÔ¸ÅÄî" id="btnSearchRelevantResource"> -->
<!-- 			</form> -->
<!-- 			<div class="remark">µ÷ÓÃº¯Êý£ºsearchRelevantResource</div> -->
<!-- 			<br/> -->
			
			<form
				action="/ExIAServer/services/ontology/textsearch/searchText"
				method='post'>
				<input type="text" name="searchText" placeholder="ÎÄ±¾ÄÚÈÝ"
					title="Àý×Ó£º¹²²úµ³,¹æÕÂÖÆ¶È"/><br/>
				<input type="button"
					value="È«ÎÄ¼ìË÷" id="btnSearchText">
			</form>
			<div class="remark">µ÷ÓÃº¯Êý£ºsearchText</div>
			<br/>
			
			<form
				action="/ExIAServer/services/ontology/textsearch/absText"
				method='post'>
				<textarea class="absText" style="width:400px;height:400px" placeholder="ÎÄ±¾ÄÚÈÝ"
					title="Àý×Ó£º¹²²úµ³,¹æÕÂÖÆ¶È"></textarea><br/>
				<input type="button"
					value="ÌáÈ¡ÎÄ±¾ÕªÒªºÍÖ÷Ìâ´Ê" id="btnAbsText">
			</form>
			<div class="remark">µ÷ÓÃº¯Êý£ºabsText</div>
			<br/>
			
			<form
				action="/ExIAServer/services/ontology/search/nagaoText"
				method='post'>
				<textarea class="nagaoText" style="width:400px;height:400px" placeholder="ÎÄ±¾ÄÚÈÝ"
					title="Àý×Ó£º½¨¹úÈýÊ®ÎåÄêÀ´£¬ÎÒ¹úµÄÃñº½ÊÂÒµ´ÓÐ¡µ½´ó£¬·¢Õ¹½Ï¿ì£¬È¡µÃÁËÏÔÖøµÄ³É¼¨£¬ÌØ±ðÊÇÔÚµ³µÄÊ®Ò»½ìÈýÖÐÈ«»áÒÔºó£¬Ãñº½ÊÂÒµµÄ·¢Õ¹¸üÎªÑ¸ËÙ¡£"></textarea><br/>
				<input type="button"
					value="NagaoËã·¨ÌáÈ¡´Ê" id="btnNagaoText">
			</form>
			<div class="remark">µ÷ÓÃº¯Êý£ºnagaoText</div>
			<br>
			
			<form
				action="/ExIAServer/services/ontology/recognise/concepts"
				method='post'>
				<input type="title" name="recogniseTitle" placeholder="ÎÄµµ±êÌâ"
					title="Àý×Ó£º¹ãÖÝÊÐ³ÇÏç×ÜÌå¹æ»®Êé"><br/>
				<input type="keyWord" name="recogniseKeyWord" placeholder="Ö÷Ìâ´Ê"
					title="Àý×Ó£º[&quot;¹úÎñÔº&quot;]"><br/>
				<input type="text" name="recogniseText" placeholder="ÎÄµµÄÚÈÝ"
					title="Àý×Ó£º¹ãÖÝÊÐ³ÇÏç×ÜÌå¹æ»®Êé"><br/>
				<input type="text" name="clsNames" placeholder="ÏÞ¶¨µÄÒªËØÀàÐÍ"
					title="Àý×Ó£º[&quot;ÁìÓò&quot;]">
				<input type="button"
					value="¸ù¾Ý±êÌâÊ¶±ðÎåÒªËØ" id="btnRecogniseOntology">
			</form>
			<div class="remark">µ÷ÓÃº¯Êý£ºgetConcepts</div>
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
							//Ìá½»Êý¾ÝµÄÀàÐÍ POST GET
							type : "POST",
							//Ìá½»µÄÍøÖ·
							url : url,
							//Ìá½»µÄÊý¾Ý
							data : datas,
							//·µ»ØÊý¾ÝµÄ¸ñÊ½
							datatype : "json",
							//µ÷ÓÃÖ´ÐÐºóµ÷ÓÃµÄº¯Êý
							success : function(result) {
								//console.log(result);
								$('#result').val(formatJson(result, ''));
							},
							//µ÷ÓÃ³ö´íÖ´ÐÐµÄº¯Êý
							error : function() {
								//ÇëÇó³ö´í´¦Àí
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
	//¸Ã´úÂëÆ¬¶ÎÀ´×ÔÓÚ: http://www.sharejs.com/codes/javascript/5452
</script>
</html>