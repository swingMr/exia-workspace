<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>信息资源 维护</title>
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
	      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
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
		    	<div style="float:left;position: relative;margin:3px"> <span class="layui-btn  layui-btn-normal layui-btn-small" id="createCatalog" title="创建目录" ><i class="layui-icon">&#xe654;</i></span></div>
		    </div>
		</form>
		</div>
		 	<ul id="treeDemo" class="ztree" style="width: 260px;overflow:auto;"></ul>
		</div>
    </div>
    	  <!-- 主体部分 -->
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
var parentCatelogNum = "root";//父目录编号，默认root
var parentCatelogId = "root";//父目录id，默认root
var libNum = $("#libNum").val();//所属资源库编号
var libName = $("#libNum option:selected").text();//所属资源库名字
/*
	nodeId             当前节点id
	parentCatelogName  父目录名字
	parentNodeName     当前节点的父节点名字
	parentNodeNum      当前节点的父节点编号
	parentNode         当前节点的父节点
	treeNode1                              新建子目录时为当前节点,新建平级目录时为当前节点的父节点
	currentNode        当前节点
*/
var nodeId,parentCatelogName,parentNodeName,parentNodeNum,parentNode,treeNode1,currentNode;
var treeObj;//树节点

layui.use(['form','element','layer'], function(){
		var element = layui.element;
		var form = layui.form;
		var layer = layui.layer;
		window.element = element;
		window.form = form;
		window.layer = layer;
		/* 根据服务类型判断 是否显示维护脚本按钮 */
		form.on('select(libNum)', function(data){
			$("#resourceListIframe").attr("src","/iamp/resource/list.do?libNum=&catalogNum=&catalogId=");
			 parentCatelogNum = "root";
			 parentCatelogName = "无";
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
//          dataType: "text",//默认text  
         	type:"post",//默认post  
            dataFilter: filter  //异步返回后经过Filter  
        }  
        ,callback:{  
//          beforeAsync: zTreeBeforeAsync,      // 异步加载事件之前得到相应信息    
            asyncSuccess: zTreeOnAsyncSuccess,//异步加载成功的fun    
            asyncError: zTreeOnAsyncError,   //加载错误的fun  
            onCheck: zTreeOnCheck,            //复选框选择事件
            onRightClick: zTreeOnRightClick ,  //右键点击事件
            onClick: zTreeOnClick,				//点击节点事件
            //onClick:getRuleAndTemplateByDomain //捕获单击节点事件回调函数  
        }  
    };
    return setting;
}	

var zNodes =[
	{ name:"领域实例", isParent:true}
];
$(function(){
	treeObj = $.fn.zTree.init($("#treeDemo"), getSetting(libNum));
	//默认打开根节点
	var rootNode = treeObj.getNodesByFilter(function (node) { return node.level == 0 }, true);
	treeObj.expandNode(rootNode, true, true, true);  
});

function filter(treeId, parentNode, childNodes) {
	if(parentNode != null) {
	    if (childNodes.length==0) alert("该节点已经是末节点");    
	}
    return childNodes;    
}    
   
  
function zTreeOnAsyncError(event, treeId, treeNode){    
    alert("异步加载失败!");    
}    
  
function zTreeOnAsyncSuccess(event, treeId, treeNode, msg){
}

function zTreeOnCheck(event, treeId, treeNode) {
    var isChecked = treeNode.checked;
    if(isChecked == true){
    	var nodeName = treeNode.name;
    	if(nodeName.indexOf("子领域") != -1 || nodeName.indexOf("主体") != -1 || nodeName.indexOf("客体") != -1 || nodeName.indexOf("行为") != -1){
    		layer.open({
			  content: '该节点不能选择！'
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
//在根目录下新建目录
$('#createCatalog').on('click',function() {
	parentCatelogName = "无";
	parentCatelogId = "root";
	parentCatelogNum = "";
	var title ="新建目录";
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
		    var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
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
var index;//右键菜单对象	
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
	//页面层  
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
     area: [w+'px', h+'px'], //宽高
     content: '<div id="rightMouseDiv"><button class="layui-btn layui-btn-primary layui-btn-small" onclick="newChildCatalog()" style="margin: 1px 0px 0px 1px;width:102px;">新建子目录</button>'+
     			'<button class="layui-btn layui-btn-primary layui-btn-small" onclick="newEqualCatalog()" style="margin: 1px 0px 0px 1px;width:102px;">新建平级目录</button>'+
     			'<button class="layui-btn layui-btn-primary layui-btn-small" onclick="modifyCatelog()" style="margin: 1px 0px 0px 1px;width:102px;">修改</button>'+
     			'<button class="layui-btn layui-btn-primary layui-btn-small" onclick="deleteCatelog()" style="margin: 1px 0px 0px 1px;width:102px;">删除</button>'+
     			'<button class="layui-btn layui-btn-primary layui-btn-small" onclick="upCatelog()" style="margin: 1px 0px 0px 1px;width:102px;">上移↑</button>'+
     			'<button class="layui-btn layui-btn-primary layui-btn-small" onclick="downCatelog()" style="margin: 1px 0px 0px 1px;width:102px;">下移↓</button></div>',
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
//新建子目录
function newChildCatalog(){
	var title ="新建子目录";
	var url ="/iamp/resourceCatalog/create.do";
	openNewCateLog(title,url);
}
//新建平级目录
function newEqualCatalog(){
	parentCatelogNum = parentNodeNum;
    parentCatelogName = parentNodeName;
    treeNode1 = parentNode;
    if(parentNode){
    	parentCatelogId = parentNode.id;
    }else{
    	parentCatelogId = "root";
    }
    
	var title ="新建平级目录";
	var url ="/iamp/resourceCatalog/create.do";
	openNewCateLog(title,url);
}

function modifyCatelog(){
	layer.close(index);
	layer.open({
		title:"修改目录",
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
		    var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
			window.child = iframeWin;
		    }
	});
}

//上移目录
function upCatelog(){
	var preNode = currentNode.getPreNode();
	if(preNode){
		layer.confirm('确定移动该数据吗?', {
					  btn: ['确定', '取消'] //可以无限个按钮
					  ,btnAlign: 'c'
					  ,yes: function(index, layero){
					  		layer.closeAll();
					  		treeObj.moveNode(preNode, currentNode, "prev","true");
					  		moveCatelog(); 
					  }});
		
	}else{
		layer.open({
		  title: '提醒'
		  ,content: '已经是最上级节点了！'
		});
	}
	
}
//下移目录
function downCatelog(){
	var nextNode = currentNode.getNextNode();
	if(nextNode){
		layer.confirm('确定移动该数据吗?', {
				  btn: ['确定', '取消'] //可以无限个按钮
				  ,btnAlign: 'c'
				  ,yes: function(index, layero){
				  		layer.closeAll();
				  		treeObj.moveNode(nextNode, currentNode, "next","true");
				  		moveCatelog();
				  }});
	}else{
		layer.open({
		  title: '提醒'
		  ,content: '已经是最下级节点了！'
		});
	}
}
//将移动后的目录保存进数据库
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
//打开新建目录窗口
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
		    var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
			window.child = iframeWin;
		    }
	});
}

function deleteCatelog(){
	layer.confirm('确定删除该数据吗?', {
				  btn: ['确定', '取消'] //可以无限个按钮
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
						          ,title:'提醒'
						          ,content: '<div style="padding: 20px 100px;">'+data+'</div>'
						          ,btn: '关闭'
						          ,btnAlign: 'c' //按钮居中
						          ,shade: 0 //不显示遮罩
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