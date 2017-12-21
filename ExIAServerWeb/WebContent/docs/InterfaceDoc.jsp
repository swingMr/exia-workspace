<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>�ӿ��ĵ�</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="/ExIAServer/js/layui-v2/css/layui.css"  media="all">
 <link rel="stylesheet" href="/ExIAServer/js/layui-v2/css/layui.mobile.css"  media="all">
  <!-- ע�⣺�����ֱ�Ӹ������д��뵽���أ�����css·����Ҫ�ĳ��㱾�ص� -->
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
    <!-- ͷ�����򣨿����layui���е�ˮƽ������ -->
    <span class="sys-title" style="margin-left:10px;">�ǻۻ�Ӧ��֧��ƽ̨</span>
    <ul class="layui-nav" pc="" style="float:right">
      <li class="layui-nav-item layui-this">
        <a href="#">�ĵ�</a>
      </li>
      <li class="layui-nav-item ">
        <a href="/ExIAServer/tools/debugger.jsp" target="_self">���Թ���</a>
      </li>
    <span class="layui-nav-bar" style="left: 54px; top: 55px; width: 0px; opacity: 0;"></span></ul>
  </div>
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- ��ർ�����򣨿����layui���еĴ�ֱ������ -->
      <ul class="layui-nav layui-nav-tree" lay-filter="demo" style="height:100%;">
      	  <li class="layui-nav-item layui-nav-itemed">
		    <a href="javascript:gotoUrl('startDoc.jsp');">����˵��</a>
		    <dl class="layui-nav-child">
		      <dd><a href="javascript:gotoUrl('startDoc.jsp');" >��ʼʹ��</a></dd>
		      <dd><a href="javascript:;">��ȫ��֤</a></dd>
		      <dd><a href="javascript:;">��������</a></dd>
		    </dl>
		  </li>
		  <li class="layui-nav-item">
		    <a href="javascript:gotoUrl('basicServicesDoc.jsp');">��������ӿ�</a>
		    <dl class="layui-nav-child">
		      <dd><a href="javascript:gotoUrl('appBaseServiceDoc.jsp');" >APP��������</a></dd>
		      <dd><a href="javascript:gotoUrl('memberAccountServiceDoc.jsp');">��Ա�˻�����</a></dd>
		    </dl>
		  </li>
		  <li class="layui-nav-item">
		    <a href="javascript:gotoUrl('semanticIdentificationRetrievalServiceDoc.jsp');">����ʶ��&��������</a>
		    <dl class="layui-nav-child">
		      <dd><a href="javascript:gotoUrl('identifyKnowledgeDoc.jsp');" >ʶ��֪ʶ����</a></dd>
		      <dd><a href="javascript:gotoUrl('retrieveOntologyDoc.jsp');">����֪ʶ����</a></dd>
		      <dd><a href="javascript:;">��ȡ��������</a></dd>
		      <dd><a href="javascript:gotoUrl('retrieveResourcesDoc.jsp');">������Ϣ��Դ</a></dd>
		    </dl>
		  </li>
		  <li class="layui-nav-item ">
		    <a href="javascript:gotoUrl('knowledgeMiningAnalysisServicesDoc.jsp');">֪ʶ�ھ�&��������</a>
		    <dl class="layui-nav-child">
		      <dd><a href="javascript:gotoUrl('serviceInquiriesDoc.jsp');" >�����ѯ</a></dd>
		      <dd><a href="javascript:gotoUrl('extensionService.jsp');">������չ����</a></dd>
		      <dd><a href="javascript:gotoUrl('knowledgeMiningAnalysisServices.jsp');">֪ʶ�ھ�&���������÷���</a></dd>
		    </dl>
		  </li>
		  <li class="layui-nav-item ">
		    <a href="javascript:gotoUrl('knowledgeMiningAnalysisServicesDoc.jsp');">��Ϣ��Դ��ȡ</a>
		    <dl class="layui-nav-child">
		      <dd><a href="javascript:gotoUrl('InfoResourceLibrary.jsp');" >��Ϣ��Դ��</a></dd>
		      <dd><a href="javascript:gotoUrl('InfoResource.jsp');">��Ϣ��Դ</a></dd>
		    </dl>
		  </li>
		  <li class="layui-nav-item ">
		    <a href="javascript:gotoUrl('fileFormatConversionServiceDoc.jsp');">�ļ���ʽת������</a>
		    <dl class="layui-nav-child">
		      <dd><a href="javascript:gotoUrl('uploadDownloadFiles.jsp');" >�ϴ������ļ�</a></dd>
		      <dd><a href="javascript:gotoUrl('fileIntoTXT.jsp');">�ļ�ת��TXT</a></dd>
		      <dd><a href="javascript:gotoUrl('fileIntoPDF.jsp');" >�ļ�ת��PDF</a></dd>
		      <dd><a href="javascript:gotoUrl('fileIntoHTML.jsp');">�ļ�ת��HTML</a></dd>
		      <dd><a href="javascript:gotoUrl('fileIntoPic.jsp');" >�ϴ�����ͼƬ</a></dd>
		      <dd><a href="javascript:gotoUrl('fileIntoWORD.jsp');">�ļ�ת��WORD</a></dd>
		    </dl>
		  </li>
		  <li class="layui-nav-item ">
		    <a href="javascript:gotoUrl('appendixDoc.jsp');">��¼</a>
		  </li>
		  <!-- <li class="layui-nav-item">
		  	<a href="javascript:gotoUrl('exkeDoc.jsp');">֪ʶ����ʶ��ͼ���</a>
		  	<dl class="layui-nav-child">
		      <dd><a href="javascript:gotoUrl('exkeDoc.jsp');">����ʶ��</a></dd>
		      <dd><a href="javascript:gotoUrl('exkeDoc.jsp');">�������</a></dd>
		    </dl>
		  </li>
		  <li class="layui-nav-item">
		  	<a href="javascript:gotoUrl('infoDoc.jsp');">��Ϣ��Դ�����ͻ�ȡ</a>
		  	<dl class="layui-nav-child">
		      <dd><a href="javascript:gotoUrl('infoDoc.jsp');">ȫ�ļ���</a></dd>
		      <dd><a href="javascript:gotoUrl('infoDoc.jsp');">��Դ��ȡ</a></dd>
		    </dl>
		  </li>
		  <li class="layui-nav-item">
		  	<a href="javascript:;">�ı��ھ�ͷ���</a>
		  	<dl class="layui-nav-child">
		      <dd><a href="javascript:gotoUrl('splitWordDoc.jsp')">���ķִ�</a></dd>
		      <dd><a href="javascript:gotoUrl('keywordDoc.jsp');">��ȡ�ؼ���</a></dd>
		      <dd><a href="javascript:gotoUrl('textClusterDoc.jsp');">�ı�����</a></dd>
		      <dd><a href="javascript:gotoUrl('textClassifyDoc.jsp')">�Զ�����</a></dd>
		      <dd><a href="javascript:gotoUrl('summaryDoc.jsp')">�Զ�ժҪ</a></dd>
		      <dd><a href="javascript:gotoUrl('semblanceDoc.jsp')">���ƶȼ���</a></dd>
		      <dd><a href="javascript:gotoUrl('rejectwordDoc.jsp');">��������</a></dd>
		    </dl>
		  
		  </li>
		  <li class="layui-nav-item">
		    <a href="javascript:;">�ļ���ʽת��</a>
		    <dl class="layui-nav-child">
		      <dd><a href="javascript:;">�ļ�תTXT</a></dd>
		      <dd><a href="javascript:;">�ļ�תHTML</a></dd>
		      <dd><a href="javascript:;">�ļ�תPDF</a></dd>
		      <dd><a href="javascript:;">�ļ�תWORD</a></dd>
		    </dl>
		  </li> -->
		</ul>
    </div>
  </div>
  <div class="layui-body" style="overflow:hidden">
    <!-- ������������ -->
	<iframe id="mainIframe"  src="" frameborder="0" style="width: 100%;height:100%;"></iframe>
  </div>
 <div class="layui-footer">
  	<p style="line-height:44px;text-align:center;">��Ȩ���� @2016-2026 ������Ϣ�Ƽ��ɷ����޹�˾ ��������Ȩ��</p>
 </div> 
</div>
 
<script src="/ExIAServer/js/layui-v2/layui.js" charset="utf-8"></script>
<!-- ע�⣺�����ֱ�Ӹ������д��뵽���أ�����js·����Ҫ�ĳ��㱾�ص� -->
<script>
function gotoUrl(url){
	if(url=="")
	{
		return;
	}
	document.getElementById("mainIframe").setAttribute("src",url);	
	
}
layui.use('element', function(){
  var element = layui.element; //������hoverЧ���������˵��ȹ��ܣ���Ҫ����elementģ��
  //�����������
  element.on('nav(demo)', function(elem){
    console.log(elem)
    //layer.msg(elem.text());
  });
});
</script>

</body>
</html>