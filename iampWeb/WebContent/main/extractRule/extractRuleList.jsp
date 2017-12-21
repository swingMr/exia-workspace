<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="gbk"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>��Ϣ��ȡ��������</title>
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
.btnfont {
	font-size: 20px; 
	color: #1E9FFF;
	cursor: pointer;
}
</style>
<body>
<div class="layui-layout">
	 <div class="layui-side" style="width: 300px">
	    <div class="layui-side-scroll" style="width: 350px">
	      <!-- ��ർ�����򣨿����layui���еĴ�ֱ������ -->
	      <div style="display: inline-block;padding: 10px;">
		 	<ul id="treeDemo" class="ztree" style="width: 350px"></ul>
		  </div>
	    </div>
	  </div>
	 <div class="layui-body" style="left: 300px;right: 10px">
	 	<form class="layui-form" action="" >
	    	<span class="layui-btn  layui-btn-normal btn layui-btn-small" id="add">�Զ����ɹ���</span>
	    	<span class="layui-btn  layui-btn-normal btn layui-btn-small" id="save">����</span>
	    	<div style="float:right;position:relative;" class="btn"> 
			    <label class="layui-form-label">�ļ���ʽ��</label>
			    <div class="layui-input-block" style="width:130px">
			        <select name="fileType" lay-filter="fileType" id="fileType" lay-verify="required" >
			            <c:forEach var="list" items="${typeList}" varStatus="status">
					        <c:choose>  					  
							   <c:when test="${!empty type && type==list}"> 
							   		<option value="${list}" selected = "selected">${list}</option>
							   </c:when>     
							   <c:otherwise>
							   		<option value="${list}">${list}</option>
							   </c:otherwise>  
						    </c:choose> 
			            </c:forEach>
			      </select>
			    </div>
			</div>
		</form>
		
		<table class="layui-table" id="rule">
	    	<colgroup>
			    <col width="200">
			    <col width="88">
			    <col>
	    		<col width="60">
	  		</colgroup>
	  		<thead>
	    		<tr>
	     			<th>��������</th>
	     			<th>��������</th>
	     			<th>ʶ�����</th>
	     			<th>����</th>
	    		</tr> 
	 		</thead>
	  		<tbody>
	  		</tbody>
		</table> 
  	</div>
</div>
</body>
<script>
var currGenreId = "";
var currGenreName = "";
var layer;
var element;
var form;
//һ��ֱ��д��һ��js�ļ���
layui.use(['element','layer','form'], function(){
  element = layui.element();
  form = layui.form();
  layer = layui.layer;
  form.on('select(fileType)', function(data){
		getRulesByGenre();
	}); 
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
            url:"/iamp/extractRule/getChildGenre",    
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
            onClick: clickGenre //���񵥻��ڵ��¼��ص�����  
        }  
    };

var zNodes =[
	{ name:"���", isParent:true}
];
$(function(){
	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
});

//�Զ����ɹ���
$("#add").bind("click",function(){
	var title ='�Զ����ɽṹ�����ݳ�ȡ����';
	var url = encodeURI(encodeURI("/iamp/extractRule/selectFile?genreId="+currGenreId+"&genreName="+encodeURI(currGenreName)));
// 	window.parent.detailWins(title,url,'600px','230px');
	layer.open({
		title:title,
		maxmin: true,
		type: 2, 
		area: ['600px','230px'],
		content:url,
	  	success: function(layero, index){
		    var body = layer.getChildFrame('body', index);
		    var iframeWin = window[layero.find('iframe')[0]['name']]; //�õ�iframeҳ�Ĵ��ڶ���ִ��iframeҳ�ķ�����iframeWin.method();
				window.child = iframeWin;
		}
	});
}); 
//����
$("#save").bind("click",function(){
	var genreId = currGenreId;
	var genreName = currGenreName;
	var fileType = $('#fileType option:selected').val();
	var arr = [];
	var trs = $('#rule tbody').find('tr');
	if(trs!=null && trs.length>0) {
		$.each(trs, function(n, tr) {
			var tds = $(tr).find('td');
			var name = $(tds[0]).text();
			var type = $(tds[1]).text();
			var html = $(tds[2]).html();
			var rules = [];
			var aa = html.split('<br>');
			if(aa!=null && aa.length>0) {
				$.each(aa, function(m,a) {
					a = a.substring(a.indexOf("��")+1, a.length);
					rules.push(a);
				})
			}
			arr.push({'name': name, 'type': type, 'rule': rules});
		})
	}
	$.ajax({
		url: '/iamp/extractRule/save',
		data: {
			genreId: genreId,
			genreName: genreName,
			fileType: fileType,
			ruleArr: JSON.stringify(arr)
		},
		type: 'post',
		dataType: 'json',
		contentType: "application/x-www-form-urlencoded; charset=GBK",
		success: function(data) {
			if(data.result) {
				layer.msg('����ɹ���',{time: 1000});
			} else {
				layer.msg('����ʧ�ܣ�',{time: 1000});
			}
		}
	})
}); 

function reload() {
	getRulesByGenre();
}

function filter(treeId, parentNode, childNodes) {
    if (childNodes.length==0) 
    	layer.msg('�ýڵ��Ѿ���ĩ�ڵ㣡',{time: 1000});
    return childNodes;    
}

function getRulesByGenre() {
	var fileType = $('#fileType option:selected').val();
	$.ajax({
		url:'/iamp/extractRule/getRulesByGenre',
		type:'post',
		data:{"genreId": currGenreId, 'genre': currGenreName, 'fileType': fileType},
		dataType: 'json',
		contentType: "application/x-www-form-urlencoded; charset=GBK",
		success:function(data){
			if(data.result) {
				loadTable(data.ruleArr);
			}
		},
		error:function(e){
			layer.msg('�����ˣ�',{time: 1000});
			console.log(e);
		}
	})
}

function loadTable(ruleArr) {
	var html="";
	if(ruleArr!=null && ruleArr.length>0){
		$.each(ruleArr,function(n,d){
			var arr = d.rule;
			var str = '';
			if(arr!=null && arr.length>0) {
				$.each(arr, function(m,i) {
					if(m==0) {
						str+=(m+1)+'��'+i;
					} else {
						str+='<br/>'+(m+1)+'��'+i;
					}
				})
			}
			var num = arr.length;
			html += '<tr><td>'+d.name+'</td><td>'+d.type+'</td><td index="'+num+'">'+str+'</td>'
					+'<td><i class="layui-icon btnfont" title="���" onclick="add(this)">&#xe608;</i><i class="layui-icon btnfont" title="ɾ��" onclick="remove(this)">&#xe640;</i></td></tr>'
		})
	}
	$("#rule tbody").empty();
	$("#rule tbody").append(html);
}


function clickGenre(event,treeId,treeNode){
	var genreId = treeNode.id;
	currGenreId = genreId;
	currGenreName = treeNode.name;
	if(typeof genreId=="undefined" || genreId==""){
		layer.msg('��ѡ��������ã�',{time: 1000});
		return;
	}
	getRulesByGenre();
}  
  
function zTreeOnAsyncError(event, treeId, treeNode){    
    alert("�첽����ʧ��!");    
}    
  
function zTreeOnAsyncSuccess(event, treeId, treeNode, msg){    
      
}

function add(i) {
	var num = $(i).parent().prev().attr('index');
	var html = $(i).parent().prev().html();
	//prompt��
	layer.prompt({title: '����ʶ�����', formType: 0}, function(text, index){
	  	if(text) {
	  		num = parseInt(num)+1;
	  		$(i).parent().prev().attr('index',num);
	  		html+='<br/>'+(num)+'��'+text;
	  		$(i).parent().prev().html(html);
	  		layer.close(index);
	  	}
	});
}

function remove(i) {
	//ѯ�ʿ�
	layer.confirm('ȷ��ɾ����������', function(index){
		  //do something
		  $(i).parent().parent().remove();
		  layer.close(index);
	});       
}

</script>

</html>