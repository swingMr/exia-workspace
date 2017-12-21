<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>��Ϣ��Դ ά��</title>
</head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="/iamp/js/layui-v2/css/layui.css" media="all" />
<link rel="stylesheet" href="/iamp/css/fonts/iconfont.css" type="text/css">
<link rel="stylesheet" href="/iamp/js/layui-v2/css/global.css" media="all">
<script src="/iamp/js/jquery-1.7.2.min.js"></script>
<script src="/iamp/js/layui-v2/layui.js" ></script>
<link rel="stylesheet" href="/iamp/js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="/iamp/js/ztree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="/iamp/js/ztree/js/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="/iamp/js/ztree/js/jquery.ztree.exedit.js"></script>
<style type="text/css">
	.layui-form-mid {
	    float: left;
	    display: block;
	    padding: 5px 0 0 10px;
	    margin-right: 10px;
	}
</style>
<body class="layui-layout">	
	      <!-- ��ർ�����򣨿����layui���еĴ�ֱ������ -->
	<div class="layui-side" id="tree" style="width: 260px">
	    <div class="layui-side-scroll" style="width: 260px;">
	      <div style="display:inline-block;margin-left:5px;height:40px">
	      <form class="layui-form" id="form1"  method="post">
	      	<div class="layui-form-item">
			    <div class="layui-input-inline" style="width:180px;float:left;position: relative;">
			      	<select name="libNum"  lay-filter="libNum" id="libNum">
						<c:forEach var="list" items="${list}" varStatus="status">
							<option value="${list.libNum}">${list.libName}</option>
						</c:forEach>
					</select>
			    </div>
		    	<div style="float:left;position: relative;margin:3px"> <span class="layui-btn  layui-btn-normal layui-btn-small" id="createCatalog" title="����Ŀ¼" ><i class="layui-icon">&#xe654;</i></span></div>
		    </div>
		</form>
		</div>
		 	<ul id="treeDemo" class="ztree" style="width: 260px;overflow:auto;"></ul>
		</div>
    </div>
    	  <!-- ���岿�� -->
	  <div class="layui-body "  style="left: 246px;" >
	  	<iframe id="resourceListIframe" src="" style="width:100%;height:100%" frameborder="0"></iframe>
	  </div>
</body>
<script>
function reload(){
	var targetUrl = window.targetUrl;
	$("#resourceListIframe").attr("src",targetUrl);
}
function getAttrVal(data){
	document.getElementById("resourceListIframe").contentWindow.getAttrVal(data);
}
var layer;
var element;
var parentCatelogNum = "root";//��Ŀ¼��ţ�Ĭ��root
var parentCatelogId = "root";//��Ŀ¼id��Ĭ��root
var libNum = $("#libNum").val();//������Դ����
var libName = $("#libNum option:selected").text();//������Դ������
/*
	nodeId             ��ǰ�ڵ�id
	parentCatelogName  ��Ŀ¼����
	parentNodeName     ��ǰ�ڵ�ĸ��ڵ�����
	parentNodeNum      ��ǰ�ڵ�ĸ��ڵ���
	parentNode         ��ǰ�ڵ�ĸ��ڵ�
	treeNode1                              �½���Ŀ¼ʱΪ��ǰ�ڵ�,�½�ƽ��Ŀ¼ʱΪ��ǰ�ڵ�ĸ��ڵ�
	currentNode        ��ǰ�ڵ�
*/
var nodeId,parentCatelogName,parentNodeName,parentNodeNum,parentNode,treeNode1,currentNode;
var treeObj;//���ڵ�

layui.use(['form','element','layer'], function(){
		var element = layui.element;
		var form = layui.form;
		var layer = layui.layer;
		window.element = element;
		window.form = form;
		window.layer = layer;
		/* ���ݷ��������ж� �Ƿ���ʾά���ű���ť */
		form.on('select(libNum)', function(data){
			$("#resourceListIframe").attr("src","/iamp/resource/list.do?libNum=&catalogNum=&catalogId=");
			 parentCatelogNum = "root";
			 parentCatelogName = "��";
			 libNum = $("#libNum option:selected").val();
			 libName = $("#libNum option:selected").text();
			 var val =  data.value;
			 libNum = val;
			 treeObj = $.fn.zTree.init($("#treeDemo"), getSetting(libNum));
			 reloadCatalogNum();
		});
	});

function reloadCatalogNum(){
	var src =  $("#resourceListIframe").attr("src");
	if(src != ""){
		document.getElementById("resourceListIframe").contentWindow.resetPoint();
	}
}

function getSetting(libNum) {
var setting = {    
        data: {    
            simpleData: {    
                enable: true,
             	idKey:"id"
//              pIdKey:"pId",  
            }    
        },
        async: {    
            enable: true,    
            url:"/iamp/resourceCatalog/tree.do?libNum="+libNum,
            contentType: "application/x-www-form-urlencoded; charset=GBK",    
            autoParam:["id","name"],  
//             otherParam:{"otherParam":"zTreeAsyncTest"},    
//          dataType: "text",//Ĭ��text  
         	type:"post",//Ĭ��post  
            dataFilter: filter  //�첽���غ󾭹�Filter  
        }  
        ,callback:{  
//          beforeAsync: zTreeBeforeAsync,      // �첽�����¼�֮ǰ�õ���Ӧ��Ϣ    
            asyncSuccess: zTreeOnAsyncSuccess,//�첽���سɹ���fun    
            asyncError: zTreeOnAsyncError,   //���ش����fun  
            onCheck: zTreeOnCheck,            //��ѡ��ѡ���¼�
            onRightClick: zTreeOnRightClick ,  //�Ҽ�����¼�
            onClick: zTreeOnClick,				//����ڵ��¼�
            //onClick:getRuleAndTemplateByDomain //���񵥻��ڵ��¼��ص�����  
        }  
    };
    return setting;
}	

var zNodes =[
	{ name:"����ʵ��", isParent:true}
];
$(function(){
	treeObj = $.fn.zTree.init($("#treeDemo"), getSetting(libNum));
	//Ĭ�ϴ򿪸��ڵ�
	var rootNode = treeObj.getNodesByFilter(function (node) { return node.level == 0 }, true);
	treeObj.expandNode(rootNode, true, true, true);  
});

function filter(treeId, parentNode, childNodes) {
	if(parentNode != null) {
	    if (childNodes.length==0) alert("�ýڵ��Ѿ���ĩ�ڵ�");    
	}
    return childNodes;    
}    
   
  
function zTreeOnAsyncError(event, treeId, treeNode){    
    alert("�첽����ʧ��!");    
}    
  
function zTreeOnAsyncSuccess(event, treeId, treeNode, msg){
}

function zTreeOnCheck(event, treeId, treeNode) {
    var isChecked = treeNode.checked;
    if(isChecked == true){
    	var nodeName = treeNode.name;
    	if(nodeName.indexOf("������") != -1 || nodeName.indexOf("����") != -1 || nodeName.indexOf("����") != -1 || nodeName.indexOf("��Ϊ") != -1){
    		layer.open({
			  content: '�ýڵ㲻��ѡ��'
			});
			treeNode.checked = false;
			return false;
    	}
    }
};

function zTreeOnClick(event, treeId, treeNode){
	currentNode = treeNode;
	var catalogNum1 = treeNode.catelogNum;
	var catalogId1 = treeNode.id;
	var displayMode = treeNode.displayMode;
	var targetUrl = "/iamp/resource/list.do?libNum="+libNum+"&catalogNum="+catalogNum1+"&catalogId="+catalogId1+"&displayMode="+displayMode;
	window.targetUrl = targetUrl;
	$("#resourceListIframe").attr("src",targetUrl);
}
//�ڸ�Ŀ¼���½�Ŀ¼
$('#createCatalog').on('click',function() {
	parentCatelogName = "��";
	parentCatelogId = "root";
	parentCatelogNum = "";
	var title ="�½�Ŀ¼";
	var url ="/iamp/resourceCatalog/create.do";
	//window.parent.detailWin(title,url);
	layer.open({
		title:title,
		maxmin: true,
		type: 2, 
		area: ['1000px', '400px'],
		content:url,
	  	success: function(layero, index){
		    var body = layer.getChildFrame('body', index);
            body.contents().find("#resourceLibName").val(libName);
            body.contents().find("#resourceLibNum").val(libNum);
            body.contents().find("#resourceLib").text(libName);
            body.contents().find("#parentCateLogNum").val(parentCatelogNum);	
    	    body.contents().find("#parentCateLogName").val(parentCatelogName);	
    		body.contents().find("#parentCateLog").text(parentCatelogName);	
    		body.contents().find("#parentCateLogId").val(parentCatelogId);	
		    var iframeWin = window[layero.find('iframe')[0]['name']]; //�õ�iframeҳ�Ĵ��ڶ���ִ��iframeҳ�ķ�����iframeWin.method();
			window.child = iframeWin;
		    }
	});
});

function detailWin(title,url){
	window.parent.detailWin(title,url);
}
function detailWins(title,url,width,height){
	window.parent.detailWins(title,url,width,height);
}
var index;//�Ҽ��˵�����	
function zTreeOnRightClick(event, treeId, treeNode) {
   parentNode = treeNode.getParentNode();
   currentNode = treeNode;
   if(parentNode){
   		parentNodeName = parentNode.name;
   		parentNodeNum = parentNode.catelogNum;
   }
   parentCatelogNum = treeNode.catelogNum;
   parentCatelogName = treeNode.name;
   parentCatelogId = treeNode.id;
   treeNode1 = treeNode;
   nodeId = treeNode.id;
	//ҳ���  
   var w = 104;
   var h = 158;  
   var width = $(window).width(); 
   var height = $(window).height();
   var iHeight = event.clientY;
   var iWidth = event.clientX;
   if(height-iHeight<=h) {
   	iHeight = iHeight-h;
   }
   if(width-iWidth<=w) {
   	iWidth = iWidth-w;
   }
   
   index = layer.open({
     type: 1,
     title: false,
     offset: [iHeight,iWidth],  
     area: [w+'px', h+'px'], //���
     content: '<div id="rightMouseDiv"><button class="layui-btn layui-btn-primary layui-btn-small" onclick="newChildCatalog()" style="margin: 1px 0px 0px 1px;width:102px;">�½���Ŀ¼</button>'+
     			'<button class="layui-btn layui-btn-primary layui-btn-small" onclick="newEqualCatalog()" style="margin: 1px 0px 0px 1px;width:102px;">�½�ƽ��Ŀ¼</button>'+
     			'<button class="layui-btn layui-btn-primary layui-btn-small" onclick="modifyCatelog()" style="margin: 1px 0px 0px 1px;width:102px;">�޸�</button>'+
     			'<button class="layui-btn layui-btn-primary layui-btn-small" onclick="deleteCatelog()" style="margin: 1px 0px 0px 1px;width:102px;">ɾ��</button>'+
     			'<button class="layui-btn layui-btn-primary layui-btn-small" onclick="upCatelog()" style="margin: 1px 0px 0px 1px;width:102px;">���ơ�</button>'+
     			'<button class="layui-btn layui-btn-primary layui-btn-small" onclick="downCatelog()" style="margin: 1px 0px 0px 1px;width:102px;">���ơ�</button></div>',
     closeBtn: 0,
     shade: 0,
     shadeClose: true,
     anim: -1
   }); 
   
   $('.layui-layer-page .layui-layer-content').css("overflow","hidden");
   
   $("#rightMouseDiv").mouseleave(function(){
  		layer.close(index);
   });
    
};
//�½���Ŀ¼
function newChildCatalog(){
	var title ="�½���Ŀ¼";
	var url ="/iamp/resourceCatalog/create.do";
	openNewCateLog(title,url);
}
//�½�ƽ��Ŀ¼
function newEqualCatalog(){
	parentCatelogNum = parentNodeNum;
    parentCatelogName = parentNodeName;
    treeNode1 = parentNode;
    if(parentNode){
    	parentCatelogId = parentNode.id;
    }else{
    	parentCatelogId = "root";
    }
    
	var title ="�½�ƽ��Ŀ¼";
	var url ="/iamp/resourceCatalog/create.do";
	openNewCateLog(title,url);
}

function modifyCatelog(){
	layer.close(index);
	layer.open({
		title:"�޸�Ŀ¼",
		maxmin: true,
		type: 2, 
		area: ['1000px', '400px'],
		content:"/iamp/resourceCatalog/create.do?displayMode="+currentNode.displayMode,
	  	success: function(layero, index){
		    var body = layer.getChildFrame('body', index);
            body.contents().find("#resourceLibName").val(libName);
            body.contents().find("#resourceLibNum").val(libNum);
            body.contents().find("#resourceLib").text(libName);
            if(currentNode.getParentNode()){
            	body.contents().find("#parentCateLogNum").val(currentNode.getParentNode().catelogNum);	
	    	    body.contents().find("#parentCateLogName").val(currentNode.getParentNode().name);	
	    		body.contents().find("#parentCateLog").text(currentNode.getParentNode().name);
	    		body.contents().find("#parentCateLogId").val(currentNode.getParentNode().id);
            }
    		body.contents().find("#cateLogNum").val(currentNode.catelogNum);	
    		body.contents().find("#catelogId").text(currentNode.id);	
    		body.contents().find("#cateLogName").val(currentNode.name);	
    		body.contents().find("#displayOrder").val(currentNode.displayOrder);
    		body.contents().find("input[name='displayMode']").val(currentNode.displayMode);
		    var iframeWin = window[layero.find('iframe')[0]['name']]; //�õ�iframeҳ�Ĵ��ڶ���ִ��iframeҳ�ķ�����iframeWin.method();
			window.child = iframeWin;
		    }
	});
}

//����Ŀ¼
function upCatelog(){
	var preNode = currentNode.getPreNode();
	if(preNode){
		layer.confirm('ȷ���ƶ���������?', {
					  btn: ['ȷ��', 'ȡ��'] //�������޸���ť
					  ,btnAlign: 'c'
					  ,yes: function(index, layero){
					  		layer.closeAll();
					  		treeObj.moveNode(preNode, currentNode, "prev","true");
					  		moveCatelog(); 
					  }});
		
	}else{
		layer.open({
		  title: '����'
		  ,content: '�Ѿ������ϼ��ڵ��ˣ�'
		});
	}
	
}
//����Ŀ¼
function downCatelog(){
	var nextNode = currentNode.getNextNode();
	if(nextNode){
		layer.confirm('ȷ���ƶ���������?', {
				  btn: ['ȷ��', 'ȡ��'] //�������޸���ť
				  ,btnAlign: 'c'
				  ,yes: function(index, layero){
				  		layer.closeAll();
				  		treeObj.moveNode(nextNode, currentNode, "next","true");
				  		moveCatelog();
				  }});
	}else{
		layer.open({
		  title: '����'
		  ,content: '�Ѿ������¼��ڵ��ˣ�'
		});
	}
}
//���ƶ����Ŀ¼��������ݿ�
function moveCatelog(){
	var allNodes;
	if(currentNode.getParentNode() != null){
		allNodes = currentNode.getParentNode().children;
	}else{
		allNodes = treeObj.getNodes();
	}
	var nodesArr = [];
	for(var i=0;i<allNodes.length;i++){
		nodesArr.push(allNodes[i].id);
	}  
  	 $.ajax({
              type: "post",
              url:'/iamp/resourceCatalog/update.do',
              data:{"id":JSON.stringify(nodesArr)},
              async: false,
              error: function(request) {
                  alert("Connection error");
              },
              dataType:"text",
              success: function(data) {
              }
          });
}
//���½�Ŀ¼����
function openNewCateLog(title,url){
	layer.close(index);
	layer.open({
		title:title,
		maxmin: true,
		type: 2, 
		area: ['1000px', '400px'],
		content:url,
	  	success: function(layero, index){
		    var body = layer.getChildFrame('body', index);
            body.contents().find("#resourceLibName").val(libName);
            body.contents().find("#resourceLibNum").val(libNum);
            body.contents().find("#resourceLib").text(libName);
            body.contents().find("#parentCateLogNum").val(parentCatelogNum);	
    	    body.contents().find("#parentCateLogName").val(parentCatelogName);	
    		body.contents().find("#parentCateLog").text(parentCatelogName);
    		body.contents().find("#parentCateLogId").val(parentCatelogId);	
		    var iframeWin = window[layero.find('iframe')[0]['name']]; //�õ�iframeҳ�Ĵ��ڶ���ִ��iframeҳ�ķ�����iframeWin.method();
			window.child = iframeWin;
		    }
	});
}

function deleteCatelog(){
	layer.confirm('ȷ��ɾ����������?', {
				  btn: ['ȷ��', 'ȡ��'] //�������޸���ť
				  ,btnAlign: 'c'
				  ,yes: function(index, layero){
				  	 $.ajax({
		                type: "post",
		                url:'/iamp/resourceCatalog/delete.do',
		                data:{"id":nodeId,"libNum":libNum},
		                async: false,
		                error: function(request) {
		                    alert("Connection error");
		                },
		                dataType:"text",
		                success: function(data) {
		                	var layer = window.layer;
		                		layer.open({
						          type: 1
						          ,title:'����'
						          ,content: '<div style="padding: 20px 100px;">'+data+'</div>'
						          ,btn: '�ر�'
						          ,btnAlign: 'c' //��ť����
						          ,shade: 0 //����ʾ����
						          ,yes: function(){
						        	  layer.closeAll();
									  treeObj.reAsyncChildNodes(parentNode, "refresh");
						          }
						        });	
		                }
		            });
				  }})
}
</script>
</html>