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
<style>
.layui-elem-field legend{font-size: 16px}
</style>
<body>
<div class="layui-layout">
	 <div class="layui-side" style="width: 380px">
	    <div class="layui-side-scroll" style="width: 350px">
	      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
	      <div style="display: inline-block;padding: 10px;">
		 	<ul id="treeDemo" class="ztree" style="width: 350px"></ul>
		  </div>
	    </div>
	  </div>
	 <div class="layui-body" style="left: 380px;right: 50px">
    	<!-- 内容主体区域 -->
    	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
		  <legend><span id="domainName"></span>规则</legend>
		</fieldset>
	 
		<div class="layui-form">
		  <table class="layui-table" id="rule">
		    <colgroup>
		      <col width="50">
		      <col width="550">
		      <col width="20">
		    </colgroup>
		    <thead>
		      <tr>
		        <th><input type="checkbox" name="eee" lay-skin="primary" lay-filter="allChoose"></th>
		        <th>规则内容</th>
		        <th>
					    <i class="layui-icon" style="cursor: pointer;" onclick="addRule()">&#xe654;</i>
		        </th>
		      </tr> 
		    </thead>
		    <tbody>
		      <tr>
		        <td><input type="checkbox" name="eee" lay-skin="primary"></td>
		        <td><span style="color: red">暂无任何内容</span></td>
		        <td>
		        </td>
		      </tr>
		    </tbody>
		  </table>
		</div>
		
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
		  <legend>模板</legend>
		</fieldset>
		<div class="layui-form">
		  <table class="layui-table">
		    <colgroup>
		      <col width="50">
		      <col width="150">
		      <col width="400">
		      <col width="20">
		    </colgroup>
		    <thead>
		      <tr>
		        <th><input type="checkbox" name="eee" lay-skin="primary" lay-filter="allChoose"></th>
		        <th>模板名称</th>
		        <th>作用</th>
		        <th>
		        	<i class="layui-icon" style="cursor: pointer;" onclick="addRule()">&#xe654;</i>
		        </th>
		      </tr> 
		    </thead>
		    <tbody>
		      <tr>
		        <td><input type="checkbox" name="eee" lay-skin="primary"></td>
		        <td><span style="color: red">暂无任何内容</span></td>
		        <td></td>
		        <td>
		        </td>
		      </tr>
		    </tbody>
		  </table>
		</div>
  	</div>
</div>	
	
<script>
var layer;
var element;
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
        }    
        ,async: {    
            enable: true,    
            url:"/iamp/ontologyRule/getChildDomain",    
            autoParam:["id", "name"],
            contentType: "application/x-www-form-urlencoded; charset=GBK",    
//             otherParam:{"otherParam":"zTreeAsyncTest"},    
//          dataType: "text",//默认text  
         	type:"post",//默认post  
            dataFilter: filter  //异步返回后经过Filter  
        }  
        ,callback:{  
//          beforeAsync: zTreeBeforeAsync,      // 异步加载事件之前得到相应信息    
            asyncSuccess: zTreeOnAsyncSuccess,//异步加载成功的fun    
            asyncError: zTreeOnAsyncError,   //加载错误的fun    
            onClick:getRuleAndTemplateByDomain //捕获单击节点事件回调函数  
        }  
    };

var zNodes =[
	{ name:"领域实例", isParent:true}
];
$(function(){
	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
});

function filter(treeId, parentNode, childNodes) {
    if (childNodes.length==0) alert("该节点已经是末节点");    
    return childNodes;    
}    
  
function getRuleAndTemplateByDomain(event,treeId,treeNode){
	var domainId = treeNode.id;
	if(typeof domainId=="undefined" || domainId==""){
		layer.msg('此节点不能选择！',{time: 1000});
		return;
	}
	$.ajax({
		url:'/iamp/ontologyRule/ruleAndTemplateByDomain',
		type:'post',
		data:{"domainId":domainId},
		success:function(data){
			var html="";
			if(data!=null && data.length>0){
				$.each(data,function(i,n){
					html +='<tr>'+
						    	'<td><input type="checkbox" name="eee" lay-skin="primary"></td>'+
						        '<td><span id="'+n.ruleId+'">'+n.ruleContent+'</span></td>'+
						        '<td>                                                          '+
								'	  <i class="layui-icon" style="cursor: pointer;" onclick="editRule(\''+n.ruleId+'\')">&#xe642;</i>                     '+
								'	  <i class="layui-icon" style="cursor: pointer;" onclick="deleteRule(\''+n.ruleId+'\')">&#xe640;</i>                     '+
						        '</td>                                                         '+
						    '</tr>';
				})
			}
			$("#rule tbody").empty();
			$("#rule tbody").append(html);
			$("#domainName").text(treeNode.name);
		},
		error:function(e){
			alert(e);
		}
	})
}  
  
function zTreeOnAsyncError(event, treeId, treeNode){    
    alert("异步加载失败!");    
}    
  
function zTreeOnAsyncSuccess(event, treeId, treeNode, msg){    
      
}

function editRule(ruleId){
	var ruleContent = $("#"+ruleId).text();
	layer.prompt({
		title:'请输入规则内容：',
		value: ruleContent
	},
	function(val, index){
		editRuleSave(ruleId,val);
		layer.close(index);
	});
}

function deleteRule(ruleId){
	layer.confirm('是否确认删除？', {icon: 3, title:'提示'}, function(index){
		  delRule(ruleId);
		  layer.close(index);
		});
}

//删除规则
function delRule(ruleId){
	$.ajax({
		url:'/iamp/ontologyRule/delete',
		type:'post',
		contentType: "application/x-www-form-urlencoded; charset=GBK",
		data:{"ruleId":ruleId},
		success:function(data){
			if(data=="success"){
				$("#"+ruleId).parent().parent().remove();
				layer.msg('规则删除成功！',{time: 1000});
			}else{
				layer.msg('规则删除失败，请稍后再试！',{time: 1000});
			}
		}
	})
}

//添加规则
function addRule(){
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	var nodes = treeObj.getSelectedNodes();
	var conceptId = nodes[0].id;
	if(conceptId=="" || typeof conceptId=="undefined"){
		layer.msg('请选择正确的节点！',{time: 1000});
		return;
	}else{
		layer.prompt({
				title:'请输入规则内容：'
			},
			function(val, index){
				saveRule(val);
				layer.close(index);
		});
	}
}

//保存规则
function saveRule(ruleContent){
	if(ruleContent==null || ruleContent=="")return;
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	var nodes = treeObj.getSelectedNodes();
	var conceptId = nodes[0].id;
	var conceptName = nodes[0].name;
	$.ajax({
		url:'/iamp/ontologyRule/add',
		type:'post',
		contentType: "application/x-www-form-urlencoded; charset=GBK",
		data:{"conceptId":conceptId,"conceptName":conceptName,"ruleContent":ruleContent},
		success:function(data){
			if(data!=null){
				var html ='<tr>'+
					'<td><input type="checkbox" name="eee" lay-skin="primary"></td>'+
				    '<td><span id="'+data.ruleId+'">'+data.ruleContent+'</span></td>'+
				    '<td>                                                          '+
					'	  <i class="layui-icon" style="cursor: pointer;" onclick="editRule(\''+data.ruleId+'\')">&#xe642;</i>                     '+
					'	  <i class="layui-icon" style="cursor: pointer;" onclick="deleteRule(\''+data.ruleId+'\')">&#xe640;</i>                     '+
				    '</td>                                                         '+
				'</tr>';
				 $("#rule tbody").append(html);
				 layer.msg('规则内容添加成功！',{time: 1000});
			}
		}
	})
}

function editRuleSave(ruleId,ruleContent){
	if(ruleContent==null || ruleContent=="")return;
	$.ajax({
		url:'/iamp/ontologyRule/update',
		type:'post',
		contentType: "application/x-www-form-urlencoded; charset=GBK",
		data:{"ruleId":ruleId,"ruleContent":ruleContent},
		success:function(data){
			if(data=="success"){
				$("#"+ruleId).text(ruleContent);
				layer.msg('规则内容修改成功！',{time: 1000});
			}else{
				layer.msg('规则内容修改失败，请稍后再试！',{time: 1000});
			}
		}
	})
}
</script>
</body>
</html>