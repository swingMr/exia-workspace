<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="gbk"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>����ʶ������</title>
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
	      <!-- ��ർ�����򣨿����layui���еĴ�ֱ������ -->
	      <div style="display: inline-block;padding: 10px;">
		 	<ul id="treeDemo" class="ztree" style="width: 350px"></ul>
		  </div>
	    </div>
	  </div>
	 <div class="layui-body" style="left: 380px;right: 50px">
    	<!-- ������������ -->
    	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
		  <legend><span id="domainName"></span>����</legend>
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
		        <th>��������</th>
		        <th>
					    <i class="layui-icon" style="cursor: pointer;" onclick="addRule()">&#xe654;</i>
		        </th>
		      </tr> 
		    </thead>
		    <tbody>
		      <tr>
		        <td><input type="checkbox" name="eee" lay-skin="primary"></td>
		        <td><span style="color: red">�����κ�����</span></td>
		        <td>
		        </td>
		      </tr>
		    </tbody>
		  </table>
		</div>
		
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
		  <legend>ģ��</legend>
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
		        <th>ģ������</th>
		        <th>����</th>
		        <th>
		        	<i class="layui-icon" style="cursor: pointer;" onclick="addRule()">&#xe654;</i>
		        </th>
		      </tr> 
		    </thead>
		    <tbody>
		      <tr>
		        <td><input type="checkbox" name="eee" lay-skin="primary"></td>
		        <td><span style="color: red">�����κ�����</span></td>
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
//һ��ֱ��д��һ��js�ļ���
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
//          dataType: "text",//Ĭ��text  
         	type:"post",//Ĭ��post  
            dataFilter: filter  //�첽���غ󾭹�Filter  
        }  
        ,callback:{  
//          beforeAsync: zTreeBeforeAsync,      // �첽�����¼�֮ǰ�õ���Ӧ��Ϣ    
            asyncSuccess: zTreeOnAsyncSuccess,//�첽���سɹ���fun    
            asyncError: zTreeOnAsyncError,   //���ش����fun    
            onClick:getRuleAndTemplateByDomain //���񵥻��ڵ��¼��ص�����  
        }  
    };

var zNodes =[
	{ name:"����ʵ��", isParent:true}
];
$(function(){
	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
});

function filter(treeId, parentNode, childNodes) {
    if (childNodes.length==0) alert("�ýڵ��Ѿ���ĩ�ڵ�");    
    return childNodes;    
}    
  
function getRuleAndTemplateByDomain(event,treeId,treeNode){
	var domainId = treeNode.id;
	if(typeof domainId=="undefined" || domainId==""){
		layer.msg('�˽ڵ㲻��ѡ��',{time: 1000});
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
    alert("�첽����ʧ��!");    
}    
  
function zTreeOnAsyncSuccess(event, treeId, treeNode, msg){    
      
}

function editRule(ruleId){
	var ruleContent = $("#"+ruleId).text();
	layer.prompt({
		title:'������������ݣ�',
		value: ruleContent
	},
	function(val, index){
		editRuleSave(ruleId,val);
		layer.close(index);
	});
}

function deleteRule(ruleId){
	layer.confirm('�Ƿ�ȷ��ɾ����', {icon: 3, title:'��ʾ'}, function(index){
		  delRule(ruleId);
		  layer.close(index);
		});
}

//ɾ������
function delRule(ruleId){
	$.ajax({
		url:'/iamp/ontologyRule/delete',
		type:'post',
		contentType: "application/x-www-form-urlencoded; charset=GBK",
		data:{"ruleId":ruleId},
		success:function(data){
			if(data=="success"){
				$("#"+ruleId).parent().parent().remove();
				layer.msg('����ɾ���ɹ���',{time: 1000});
			}else{
				layer.msg('����ɾ��ʧ�ܣ����Ժ����ԣ�',{time: 1000});
			}
		}
	})
}

//��ӹ���
function addRule(){
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	var nodes = treeObj.getSelectedNodes();
	var conceptId = nodes[0].id;
	if(conceptId=="" || typeof conceptId=="undefined"){
		layer.msg('��ѡ����ȷ�Ľڵ㣡',{time: 1000});
		return;
	}else{
		layer.prompt({
				title:'������������ݣ�'
			},
			function(val, index){
				saveRule(val);
				layer.close(index);
		});
	}
}

//�������
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
				 layer.msg('����������ӳɹ���',{time: 1000});
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
				layer.msg('���������޸ĳɹ���',{time: 1000});
			}else{
				layer.msg('���������޸�ʧ�ܣ����Ժ����ԣ�',{time: 1000});
			}
		}
	})
}
</script>
</body>
</html>