<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>�ı����Ϲ���</title>
</head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="/iamp/js/layui-v2/css/layui.css" media="all" />
<link rel="stylesheet" href="/iamp/js/layui-v2/css/global.css" media="all">
<script src="/iamp/js/jquery-1.7.2.min.js"></script>
<script src="/iamp/js/jquery.form.js"></script>
<style type="text/css">
.layui-form-item .layui-input-inline {
    float: left;
    width: 280px;
    margin-right: 10px;
}
</style>
<body>	
 	<form class="layui-form" action="/iamp/textcorpus/${method}.do" id="form" enctype="multipart/form-data" method="post">
 	<table lay-skin="nob" class="layui-table">
	  <colgroup>
	    <col width="150">
	    <col width="200">
	    <col>
	  </colgroup>
		<tr >
		  	<td>
			  <div class="layui-form-item">
			    <label class="layui-form-label"><font color="red">*</font>���⣺</label>
			    <div class="layui-input-inline">
			      <input type="text" id="corpusTitle" name="corpusTitle" required  lay-verify="required" placeholder="���������" autocomplete="off" class="layui-input" value="${!empty corpus ? corpus.corpusTitle:'' }">
			      <input type="hidden" name="corpusId" value="${!empty corpus ? corpus.corpusId:''  }">
			    </div>
			  </div>
	    	</td>
	    	<td>
	    		<div class="layui-form-item">
	    		<label class="layui-form-label"><font color="red">*</font>����</label>
			    <div class="layui-input-inline">
			   	 	<input type="text" id="belongDomain" name="belongDomain" required  lay-verify="required" placeholder="����������" autocomplete="off" class="layui-input" value="${!empty corpus ? corpus.belongDomain:'' }">
			    	<input type="hidden" id="belongDomainId" name="belongDomainId"/>
			    </div>
			    <div class="layui-form-mid layui-word-aux" style="padding-top:5px;"><span class="layui-btn btn layui-btn-small" id="selectDomain">ѡ��</span></div>
			    </div>
	    	</td>
	    </tr>
		<tr >
		  	<td colspan=2>
			  <div class="layui-form-item">
			    <label class="layui-form-label">ѡ���ļ���</label>
			    <c:choose>
			    	<c:when test="${method == 'create'}">
			    		<div class="layui-input-block">
			    			<input class="layui-input" type="file" id="file" name="file" lay-verify="required">
			   			 </div>
			    	</c:when>
			    	<c:otherwise>
			    		<div class="layui-input-block">
			    			<input class="layui-input" type="file" id="file" name="file">
			   			 </div>
			    	</c:otherwise>
			    </c:choose>
			  </div>
	    	</td>
	    </tr>
		<tr>
		  	<td colspan="2">
			  <div class="layui-form-item layui-form-text">
			    <label class="layui-form-label">����ʣ�</label>
			    <div class="layui-input-block">
			      <textarea name="keyWord" placeholder="��;�ָ�" class="layui-textarea">${!empty corpus ? corpus.keyWord:'' }</textarea>
			    </div>
			  </div>
  	    	</td>
	    </tr>
    </table>
    <center>
	<button class="layui-btn layui-btn-normal layui-btn-normal" lay-submit lay-filter="save" id="save">ȷ��</button>
	<span class="layui-btn layui-btn-danger layui-btn-normal" id="cancel">ȡ��</span>
</center>
	</form> 
</body>
<script type="text/javascript" src="/iamp/js/layui-v2/layui.js"></script>
<script>
var layer;
	
	layui.use(['element','layer','form','laypage'], function(){
		var element = layui.element;
		layer = layui.layer;
		window.element = element;
		var form = layui.form;
		form.on('submit(save)', function (data) {
			submit();
			return false;
		}) 
	});
	
	
	function showMsg(title, msg) {
		layer.open({
          type: 1
          ,title:title
          ,content: '<div style="padding: 20px 100px;">'+msg+'</div>'
          ,btn: '�ر�'
          ,btnAlign: 'c' //��ť����
          ,shade: 0 //����ʾ����
          ,yes: function(){
        	  layer.closeAll();
          }
        });	
	}
	
	function parentRefresh(){
	    var frameName = 'wbylgl';
		window.parent.loadIframe(frameName);
	}
	
	function submit() {
		$("#form").ajaxSubmit({
                    success: function (data) {
                        if(data.result) {
                		layer.open({
				          type: 1
				          ,title:'����'
				          ,content: '<div style="padding: 20px 100px;">'+data.msg+'</div>'
				          ,btn: '�ر�'
				          ,btnAlign: 'c' //��ť����
				          ,shade: 0 //����ʾ����
				          ,yes: function(){
							var index = parent.layer.getFrameIndex(window.name); //�ȵõ���ǰiframe�������
				      		parent.layer.close(index);
				    		parentRefresh();
				          }
				        });	
                	} else {
                		showMsg('����',data.msg);
						return;
                	}
                    },
                    error: function (error) { showMsg('����',error); },
                    dataType: "json" /*���÷���ֵ����Ϊ�ı�*/
                });
	}
	
	$("#cancel").on("click",function(){
		closeCurrentWin();
	});

	function closeCurrentWin(){
	   var index = parent.layer.getFrameIndex(window.name); //�ȵõ���ǰiframe�������
	   parent.layer.close(index);
	}
	
	$("#selectDomain").on("click",function(){
		parent.layer.open({
		  type: 2,
		  title: 'ѡ������',
  		  shade: false,
          area: ['350px', '600px'],
		  content: '/iamp/main/datadig/domainTree.jsp',
		  btn: ['ȷ��', 'ȡ��'],
		  yes: function(index, layero){
		     var body = layer.getChildFrame('body', index);
		     var iframeHtml = layer.getChildFrame('body');
		     var treeObj = $(layero).find("iframe")[0].contentWindow.treeObj;
		     var sNodes = treeObj.getCheckedNodes(true);
		     var domainNames = "";
		     var domainIds = "";
		     for(var i=0;i<sNodes.length;i++){
		     	var domianName = sNodes[i].name;
		     	var domianId = sNodes[i].id;
		     	if(domainNames){
		     		domainNames += ";";
		     	}
		     	if(domainIds){
		     		domainIds += ";";
		     	}
		     	domainNames += domianName;
		     	domainIds += domianId;
		     }
		     $("#belongDomain").val(domainNames);
		     $("#belongDomainId").val(domainIds);
		     parent.layer.close(index);
		  },
		  btn2: function(index, layero){
		    parent.layer.close(index);
		  }
		});
	});
</script>
</html>