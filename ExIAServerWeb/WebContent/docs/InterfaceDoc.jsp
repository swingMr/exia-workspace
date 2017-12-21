<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>接口文档</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="/ExIAServer/js/layui-v2/css/layui.css"  media="all">
 <link rel="stylesheet" href="/ExIAServer/js/layui-v2/css/layui.mobile.css"  media="all">
  <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<style type='text/css'>
.sys-title {
    display: inline-block;
    line-height: 60px;
    color: #fff;
/*     text-shadow: 0 0 8px #1E9FFF,0 0 8px #009688; */
    font-size: 24px;
    letter-spacing: 6px;
    cursor: pointer;
}
</style>
<body>

<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <span class="sys-title" style="margin-left:10px;">智慧化应用支撑平台</span>
    <ul class="layui-nav" pc="" style="float:right">
      <li class="layui-nav-item layui-this">
        <a href="#">文档</a>
      </li>
      <li class="layui-nav-item ">
        <a href="/ExIAServer/tools/debugger.jsp" target="_self">调试工具</a>
      </li>
    <span class="layui-nav-bar" style="left: 54px; top: 55px; width: 0px; opacity: 0;"></span></ul>
  </div>
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree" lay-filter="demo" style="height:100%;">
      	  <li class="layui-nav-item layui-nav-itemed">
		    <a href="javascript:gotoUrl('startDoc.jsp');">基本说明</a>
		    <dl class="layui-nav-child">
		      <dd><a href="javascript:gotoUrl('startDoc.jsp');" >开始使用</a></dd>
		      <dd><a href="javascript:;">安全认证</a></dd>
		      <dd><a href="javascript:;">常见问题</a></dd>
		    </dl>
		  </li>
		  <li class="layui-nav-item">
		    <a href="javascript:gotoUrl('basicServicesDoc.jsp');">基础服务接口</a>
		    <dl class="layui-nav-child">
		      <dd><a href="javascript:gotoUrl('appBaseServiceDoc.jsp');" >APP基础服务</a></dd>
		      <dd><a href="javascript:gotoUrl('memberAccountServiceDoc.jsp');">会员账户服务</a></dd>
		    </dl>
		  </li>
		  <li class="layui-nav-item">
		    <a href="javascript:gotoUrl('semanticIdentificationRetrievalServiceDoc.jsp');">语义识别&检索服务</a>
		    <dl class="layui-nav-child">
		      <dd><a href="javascript:gotoUrl('identifyKnowledgeDoc.jsp');" >识别知识概念</a></dd>
		      <dd><a href="javascript:gotoUrl('retrieveOntologyDoc.jsp');">检索知识本体</a></dd>
		      <dd><a href="javascript:;">获取本体引文</a></dd>
		      <dd><a href="javascript:gotoUrl('retrieveResourcesDoc.jsp');">检索信息资源</a></dd>
		    </dl>
		  </li>
		  <li class="layui-nav-item ">
		    <a href="javascript:gotoUrl('knowledgeMiningAnalysisServicesDoc.jsp');">知识挖掘&分析服务</a>
		    <dl class="layui-nav-child">
		      <dd><a href="javascript:gotoUrl('serviceInquiriesDoc.jsp');" >服务查询</a></dd>
		      <dd><a href="javascript:gotoUrl('extensionService.jsp');">调用扩展服务</a></dd>
		      <dd><a href="javascript:gotoUrl('knowledgeMiningAnalysisServices.jsp');">知识挖掘&分析的内置服务</a></dd>
		    </dl>
		  </li>
		  <li class="layui-nav-item ">
		    <a href="javascript:gotoUrl('knowledgeMiningAnalysisServicesDoc.jsp');">信息资源获取</a>
		    <dl class="layui-nav-child">
		      <dd><a href="javascript:gotoUrl('InfoResourceLibrary.jsp');" >信息资源库</a></dd>
		      <dd><a href="javascript:gotoUrl('InfoResource.jsp');">信息资源</a></dd>
		    </dl>
		  </li>
		  <li class="layui-nav-item ">
		    <a href="javascript:gotoUrl('fileFormatConversionServiceDoc.jsp');">文件格式转换服务</a>
		    <dl class="layui-nav-child">
		      <dd><a href="javascript:gotoUrl('uploadDownloadFiles.jsp');" >上传下载文件</a></dd>
		      <dd><a href="javascript:gotoUrl('fileIntoTXT.jsp');">文件转成TXT</a></dd>
		      <dd><a href="javascript:gotoUrl('fileIntoPDF.jsp');" >文件转成PDF</a></dd>
		      <dd><a href="javascript:gotoUrl('fileIntoHTML.jsp');">文件转成HTML</a></dd>
		      <dd><a href="javascript:gotoUrl('fileIntoPic.jsp');" >上传下载图片</a></dd>
		      <dd><a href="javascript:gotoUrl('fileIntoWORD.jsp');">文件转成WORD</a></dd>
		    </dl>
		  </li>
		  <li class="layui-nav-item ">
		    <a href="javascript:gotoUrl('appendixDoc.jsp');">附录</a>
		  </li>
		  <!-- <li class="layui-nav-item">
		  	<a href="javascript:gotoUrl('exkeDoc.jsp');">知识本体识别和检索</a>
		  	<dl class="layui-nav-child">
		      <dd><a href="javascript:gotoUrl('exkeDoc.jsp');">本体识别</a></dd>
		      <dd><a href="javascript:gotoUrl('exkeDoc.jsp');">本体检索</a></dd>
		    </dl>
		  </li>
		  <li class="layui-nav-item">
		  	<a href="javascript:gotoUrl('infoDoc.jsp');">信息资源检索和获取</a>
		  	<dl class="layui-nav-child">
		      <dd><a href="javascript:gotoUrl('infoDoc.jsp');">全文检索</a></dd>
		      <dd><a href="javascript:gotoUrl('infoDoc.jsp');">资源获取</a></dd>
		    </dl>
		  </li>
		  <li class="layui-nav-item">
		  	<a href="javascript:;">文本挖掘和分析</a>
		  	<dl class="layui-nav-child">
		      <dd><a href="javascript:gotoUrl('splitWordDoc.jsp')">中文分词</a></dd>
		      <dd><a href="javascript:gotoUrl('keywordDoc.jsp');">提取关键词</a></dd>
		      <dd><a href="javascript:gotoUrl('textClusterDoc.jsp');">文本聚类</a></dd>
		      <dd><a href="javascript:gotoUrl('textClassifyDoc.jsp')">自动分类</a></dd>
		      <dd><a href="javascript:gotoUrl('summaryDoc.jsp')">自动摘要</a></dd>
		      <dd><a href="javascript:gotoUrl('semblanceDoc.jsp')">相似度计算</a></dd>
		      <dd><a href="javascript:gotoUrl('rejectwordDoc.jsp');">词义排歧</a></dd>
		    </dl>
		  
		  </li>
		  <li class="layui-nav-item">
		    <a href="javascript:;">文件格式转换</a>
		    <dl class="layui-nav-child">
		      <dd><a href="javascript:;">文件转TXT</a></dd>
		      <dd><a href="javascript:;">文件转HTML</a></dd>
		      <dd><a href="javascript:;">文件转PDF</a></dd>
		      <dd><a href="javascript:;">文件转WORD</a></dd>
		    </dl>
		  </li> -->
		</ul>
    </div>
  </div>
  <div class="layui-body" style="overflow:hidden">
    <!-- 内容主体区域 -->
	<iframe id="mainIframe"  src="" frameborder="0" style="width: 100%;height:100%;"></iframe>
  </div>
 <div class="layui-footer">
  	<p style="line-height:44px;text-align:center;">版权所有 @2016-2026 京华信息科技股份有限公司 保留所有权利</p>
 </div> 
</div>
 
<script src="/ExIAServer/js/layui-v2/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
function gotoUrl(url){
	if(url=="")
	{
		return;
	}
	document.getElementById("mainIframe").setAttribute("src",url);	
	
}
layui.use('element', function(){
  var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
  //监听导航点击
  element.on('nav(demo)', function(elem){
    console.log(elem)
    //layer.msg(elem.text());
  });
});
</script>

</body>
</html>