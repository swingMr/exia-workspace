<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="gbk"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>��Դ��Ŀ¼</title>
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
	 <div class="layui-side" style="width: 330px">
	    <div class="layui-side-scroll" style="width: 320px">
	      <!-- ��ർ�����򣨿����layui���еĴ�ֱ������ -->
	      <div style="width:320px;display:inline-block;margin:10px">
	      <form class="layui-form" id="form1"  method="post">
	 		<select name="libNum"  lay-filter="libNum" id="libNum">
					<option value="test">���Կ�</option>
					<option value="test2">�¿�2</option>
				</select>
				<div class="layui-btn-group">
		    <span class="layui-btn  layui-btn-normal layui-btn-small" id="createCatalog" title="����Ŀ¼"><i class="layui-icon">&#xe654;</i></span>
		    <span class="layui-btn layui-btn-warm layui-btn-small" id="updateCatalog" title="����Ŀ¼"><i class="layui-icon">&#xe63c;</i></span>
			<span class="layui-btn layui-btn-danger layui-btn-small" id="delCatalog" title="ɾ��Ŀ¼"><i class="layui-icon">&#x1006;</i></span>
	 	 </div>
				</form>
				</div>
	      <div >
	      
		 	<ul id="treeDemo" class="ztree" style="width: 330px"></ul>
		  </div>
	    </div>
	  </div>
	
<script>
var layer;
var element;
//���ڵ�
var treeObj;
var libNum = $("#libNum option:selected").val();

layui.use(['form','element','layer'], function(){
		var element = layui.element();
		var form = layui.form();
		var layer = layui.layer;
		window.element = element;
		window.form = form;
		window.layer = layer;
		/* ���ݷ��������ж� �Ƿ���ʾά���ű���ť */
		form.on('select(libNum)', function(data){
			 var val =  data.value;
			 libNum = val;
			 treeObj = $.fn.zTree.init($("#treeDemo"), getSetting(libNum));
		});
	});

function getSetting(libNum) {
var setting = {    
        data: {    
            simpleData: {    
                enable: true,
             	idKey:"id"
//              pIdKey:"pId",  
            }    
        },
        check: {
			enable: true,
			chkStyle: "checkbox",
			chkboxType: { "Y": "", "N": "" },
			nocheckInherit: false,
			chkDisabledInherit: false
		},    
        async: {    
            enable: true,    
            url:"/iamp/resourceCatalog/tree.do?libNum="+libNum,
            contentType: "application/x-www-form-urlencoded; charset=GBK",    
            autoParam:["catelogNum"],    
//             otherParam:{"otherParam":"zTreeAsyncTest"},    
//          dataType: "text",//Ĭ��text  
         	type:"post",//Ĭ��post  
            dataFilter: filter  //�첽���غ󾭹�Filter  
        }  
        ,callback:{  
//          beforeAsync: zTreeBeforeAsync,      // �첽�����¼�֮ǰ�õ���Ӧ��Ϣ    
            asyncSuccess: zTreeOnAsyncSuccess,//�첽���سɹ���fun    
            asyncError: zTreeOnAsyncError,   //���ش����fun  
            onCheck: zTreeOnCheck            //��ѡ��ѡ���¼�
            //onClick:getRuleAndTemplateByDomain //���񵥻��ڵ��¼��ص�����  
        }  
    };
}	
var setting = {    
        data: {    
            simpleData: {    
                enable: true,
             	idKey:"id"
//              pIdKey:"pId",  
            }    
        },
        check: {
			enable: true,
			chkStyle: "checkbox",
			chkboxType: { "Y": "", "N": "" },
			nocheckInherit: false,
			chkDisabledInherit: false
		},    
        async: {    
            enable: true,    
            url:"/iamp/resourceCatalog/tree.do?libNum="+libNum,
            contentType: "application/x-www-form-urlencoded; charset=GBK",    
            autoParam:["catelogNum"],    
//             otherParam:{"otherParam":"zTreeAsyncTest"},    
//          dataType: "text",//Ĭ��text  
         	type:"post",//Ĭ��post  
            dataFilter: filter  //�첽���غ󾭹�Filter  
        }  
        ,callback:{  
//          beforeAsync: zTreeBeforeAsync,      // �첽�����¼�֮ǰ�õ���Ӧ��Ϣ    
            asyncSuccess: zTreeOnAsyncSuccess,//�첽���سɹ���fun    
            asyncError: zTreeOnAsyncError,   //���ش����fun  
            onCheck: zTreeOnCheck            //��ѡ��ѡ���¼�
            //onClick:getRuleAndTemplateByDomain //���񵥻��ڵ��¼��ص�����  
        }  
    };

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
    if (childNodes.length==0) 
    //alert("�ýڵ��Ѿ���ĩ�ڵ�");    
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
</script>
</html>