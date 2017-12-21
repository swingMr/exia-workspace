<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="gbk"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>本体识别配置</title>
</head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="/iamp/js/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="/iamp/js/layui/css/global.css" media="all">
<script src="/iamp/js/jquery-1.7.2.min.js"></script>
<script src="/iamp/js/layui/layui.js" ></script>
<link rel="stylesheet" href="/iamp/js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="/iamp/js/ztree/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="/iamp/js/ztree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="/iamp/js/ztree/js/jquery.ztree.excheck.js"></script>
<style>
.layui-elem-field legend{font-size: 16px}
</style>
<body>
<div class="layui-layout">
	 <div class="layui-side" style="width: 100%">
	    <div class="layui-side-scroll" style="width: 350px">
	      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
	      <div style="display: inline-block;padding: 10px;">
		 	<ul id="treeDemo" class="ztree" style="width: 350px"></ul>
		  </div>
	    </div>
	  </div>
</div>	
	
<script>
var layer;
var element;
var treeObj;
var rootName = '${rootName}';
var postUrl = "/iamp/resourceCatalog/conceptTree.do?rootName="+encodeURI(encodeURI(rootName));
//一般直接写在一个js文件中
layui.use(['element','layer'], function(){
  element = layui.element();
  layer = layui.layer;
});

var setting = {    
        data: {    
            simpleData: {    
                enable: true,
             	idKey:"id"  
//              pIdKey:"pId",  
            }    
        },
        check: {
			enable: false,
			chkStyle: "checkbox",
			chkboxType: { "Y": "", "N": "" },
			nocheckInherit: false,
			chkDisabledInherit: false
		},    
        async: {    
            enable: true,    
            url:postUrl,
            contentType: "application/x-www-form-urlencoded; charset=GBK",    
            autoParam:["id", "name"],    
//             otherParam:{"otherParam":"zTreeAsyncTest"},    
//          dataType: "text",//默认text  
         	type:"post",//默认post  
            dataFilter: filter  //异步返回后经过Filter  
        }  
        ,callback:{  
//          beforeAsync: zTreeBeforeAsync,      // 异步加载事件之前得到相应信息    
            asyncSuccess: zTreeOnAsyncSuccess,//异步加载成功的fun    
            asyncError: zTreeOnAsyncError,   //加载错误的fun  
            onCheck: zTreeOnCheck            //复选框选择事件
            //onClick:getRuleAndTemplateByDomain //捕获单击节点事件回调函数  
        }  
    };

var zNodes =[
	{ name:rootName, isParent:true}
];
$(function(){
	treeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
	//默认打开根节点
	var rootNode = treeObj.getNodesByFilter(function (node) { return node.level == 0 }, true);
	treeObj.expandNode(rootNode, true, true, true);  
});

function filter(treeId, parentNode, childNodes) {
    if (childNodes.length==0) alert("该节点已经是末节点");    
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

</script>
</body>
</html>