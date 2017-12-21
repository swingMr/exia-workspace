<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>机器人定义</title>
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
<style type="text/css">
	.btn{
		margin-top:10px;
		margin-left:10px;
	}
	.title{
		
	}
</style>

<body>	
	<form class="layui-form" action="/iamp/resourceLib/list.do" >
<!-- 		<span class="layui-btn  layui-btn-normal btn layui-btn-small" id="add">新建</span>
		<span class="layui-btn  layui-btn-warm btn layui-btn-small" id="update">修改</span>
		<span class="layui-btn layui-btn-danger btn layui-btn-small" id="del">删除</span> -->
		<div style="float:right;position:relative;" class="btn"> 
		  <label class="layui-form-label">归属分类：</label>
		    <div class="layui-input-block" style="width:130px">
		      <select name="type" id="type" lay-filter="type">
		     	 <option value="all" >请选择分类</option>
		     	 <option value="1" ${!empty type && type == '1'?'selected':'' }>京华慧眼</option>
		     	 <option value="2" ${!empty type && type == '2'?'selected':'' }>公司移动办公</option>
		      </select>
		  </div>
    	</div>
	</form>
 	<table class="layui-table" id="partTable">
	  <colgroup>
	  	<col width="50">
	    <col width="100">
	    <col width="150">
	    <col width="100">
	    <col width="100">
	    <col>
	    <col width="60">
	  </colgroup>
	  <thead>
	    <tr>
	     <th></th><th >部件编号</th><th >部件名称</th><th >对应策略</th><th >归属分类</th>
	     <th>匹配规则</th><th >操作</th>
	    </tr> 
	  </thead>
	  <tbody>
	  <c:forEach var="list" items="${list}" varStatus="status">
	 		<c:choose>
       			  <c:when test="${list.partStrategy=='PatternAnswerStrategy'}"> 
				   		<tr>
						  	<td><input type="checkbox" name="id" value="${list.partId}"></td><td>${list.partCode}</td> <td>${list.partTitle}</td>
						  	<td>正则匹配</td>
						  	<td>
						  		<c:choose>
					       			  <c:when test="${list.partCategory=='remotePart'}"> 
									   		远程部件
									   </c:when>
									   <c:when test="${list.partCategory=='localPart'}"> 
									   		本地部件
									   </c:when>
						  		</c:choose>
						  	</td>
						  	<td name="partRule">${list.partRule}</td>		  	
						  	<td style="text-align: center;"><i class="layui-icon btnfont" style="color:#528E27;cursor:pointer;" title="添加规则" name="addPartRule">&#xe608;</i></td>
						</tr>
				   </c:when>
		  	</c:choose>
		  
	  </c:forEach>
	  <input type="hidden" name="ids">
	  </tbody>
	</table> 
	<div id="page" style="position:absolute;right:50px;"></div>
</body>
<script type="text/javascript" src="/iamp/js/layui/layui.js"></script>
<script>
var mainUrl = '/iamp/answerPart/list.do';
var ajaxDel = '/iamp/answerPart/del.do';
var ajaxDeletePartRule = '/iamp/answerPart/deletePartRule.do';
var ajaxAddPartRule = '/iamp/answerPart/addPartRule.do';
$(function(){
	var partRuleTd = $("#partTable").find("td[name='partRule']");
	partRuleTd.each(function(){
		var rule = $(this).text();
		var newRule = '';
		if(rule){
			var obj = eval('(' + rule + ')');
			for(var i=0;i<obj.length;i++){
				var name = obj[i].name;
				newRule += '<a><input type="hidden" name="deleteNum" value="'+i+'"/>'+(i+1)+'、'+name+'：';
				var ruleArr = obj[i].ruleArr;
				for(var j=0;j<ruleArr.length;j++){
					newRule += '"'+ruleArr[j]+'"';
					if(j != ruleArr.length-1){
						newRule += '；';
					}else{
						newRule += '</a><i class="layui-icon btnfont" style="padding-left:20px;color:red;cursor:pointer;" title="删除" name="removePartRule">&#xe640;</i><br>';
					}
				}
			}
		}
		$(this).html(newRule);
	});
});
var layer; 
layui.use(['layer','laypage','form'], function(){
	var form = layui.form();
	  var layer = layui.layer;
	  window.layer = layer;
	  var laypage = layui.laypage; 
		  laypage({
		    cont: 'page'
		    ,pages: '${pages}' //总页数
		    ,groups: 5 //连续显示分页数
		    ,curr : '${currentPage}'
		    ,jump: function(obj, first){
		        if(!first){
		          location.href='/iamp/answerPart/list.do?pageNo='+obj.curr+'&type='+type;
		        }
		      }
		  }); 
		  
		  form.on('select(type)', function(data){
			  $(".layui-form").submit();
		  });
});

	//删除功能
	$("#del").on("click",function(){
		var layer = window.layer;
		var  checks = $('input:checkbox[name=id]:checked');
		if(checks.length <= 0){
			var data = new Object();
			data.console ="请选择数据!"; 
			warning(data);
			return;
		}
		var ids = new Array();
		for(var i=0; checks.length>i; i++){
			var id = checks[i].value;
			ids.push(id);
		}
		var ids = JSON.stringify(ids);
 		layer.confirm('确定删除该数据吗?', {
		  btn: ['确定', '取消'] //可以无限个按钮
		  ,btnAlign: 'c', yes: function(index, layero){
					var layer = window.layer;
					$("input[name='ids']").val(ids);
					$.post(
						ajaxDel,
						{
							"ids" : ids
						},
						function(data){
							var data = data;
							if(data.result == 'success'){
								data.console='删除成功！';
								warning(data);
							}	
						},"json"
					);
		  }
		});
	});
	
	//修改功能
	$("#update").on("click",function(){
		var data = new Object();
		var  checks = $('input:checkbox[name=id]:checked');
		if(checks.length == 1) {
			var id = checks[0].value;
 			var title ="修改信息资源库";
 			var url ="/iamp/answerPart/showDetail.do?id="+id;
 			window.parent.detailWin(title,url);
		}else if(checks.length == 0){
			data.console = "请选择数据!";
			warning(data);
		}else{
			data.console = "请选择一条数据!";
			warning(data);
		}
	});
	
	//用于新建成功后刷新
	function refresh(){
		location.href = mainUrl;
	}
	
	//新建
	$("#add").on("click",function(){
		var title ='新建/修改机器人';
		var url ="/iamp/answerPart/showDetail.do";
		window.parent.detailWins(title,url,"1000px","500px");
	});
	
	//通用提示框
	function warning(data){
		var data = data;
		var layer = window.layer;
		layer.open({
          type: 1
          ,title:"提醒"
          ,content: '<div style="padding: 20px 100px;">'+data.console+'</div>'
          ,btn: '关闭'
          ,btnAlign: 'c' //按钮居中
          ,shade: 0 //不显示遮罩
          ,yes: function(){
        	  layer.close(layer.index);
	    		if(typeof(data.result) !='undefined' && data.result == 'success'){
	      			refresh();
	    		}
          }
        });
	}
	
	$(document).on("click","i[name='removePartRule']",function(){
		var deleteNum = $(this).prev("a").find("input[name='deleteNum']").val();
		var id = $(this).closest("tr").find("input[name='id']").val();
		layer.confirm('确定删除该数据吗?', {
		  btn: ['确定', '取消'] //可以无限个按钮
		  ,btnAlign: 'c', yes: function(index, layero){
					var layer = window.layer;
					$.post(
						ajaxDeletePartRule,
						{
							"id" : id,
							"deleteNum" : deleteNum
						},
						function(data){
							layer.open({
					          type: 1
					          ,title:"提醒"
					          ,content: '<div style="padding: 20px 100px;">'+data+'</div>'
					          ,btn: '关闭'
					          ,btnAlign: 'c' //按钮居中
					          ,shade: 0 //不显示遮罩
					          ,yes: function(){
					        	  layer.close(layer.index);
					        	  location.href = mainUrl;
					          }
					        });	
						},"text"
					); 
		  }
		});
		
	});
	
	$(document).on("click","i[name='addPartRule']",function(){
		var id = $(this).closest("tr").find("input[name='id']").val();
		layer.open({
		  type: 2,
		  title: '添加部件规则',
  		  shade: false,
          area: ['800px', '500px'],
		  content: '/iamp/main/robot/answerPartDetail.jsp',
		  btn: ['确定', '取消'],
		  yes: function(index, layero){
		     var body = layer.getChildFrame('body', index);
		     var iframeHtml = layer.getChildFrame('body');
		     var ruleTr = iframeHtml.find("#partRuleTbody").find("tr");
		     var ruleArr = [];
		     ruleTr.each(function(){
		     	var ruleObj = {};
		     	var arr = [];
		     	var ruleName = $(this).find("input[name='ruleName']").val();
		     	var ruleDiv = $(this).find("div[name='ruleDiv']");
		     	ruleDiv.each(function(){
		     		var ruleContent = $(this).find("input[name='ruleContent']").val();
		     		if(ruleContent){
		     			arr.push(ruleContent);
		     		}
		     	});
		     	if(ruleName && arr.length >= 1){
		     		ruleObj.name = ruleName;
		     		ruleObj.ruleArr = arr;
		     		ruleArr.push(ruleObj);
		     	}
		     });
		     console.log(ruleArr);
		     if(ruleArr.length >= 1){
		     	$.post(
						ajaxAddPartRule,
						{
							"id" : id,
							"addPartRule" : JSON.stringify(ruleArr) 
						},
						function(data){
							layer.open({
					          type: 1
					          ,title:"提醒"
					          ,content: '<div style="padding: 20px 100px;">'+data+'</div>'
					          ,btn: '关闭'
					          ,btnAlign: 'c' //按钮居中
					          ,shade: 0 //不显示遮罩
					          ,yes: function(){
					        	  layer.close(layer.index);
					        	  location.href = mainUrl;
					          }
					        });	
						},"text"
					);
		     }else{
		     	layer.open({
				  title: '提示'
				  ,content: '部件规则不完整，请重新修改！'
				}); 
		     }
		  },
		  btn2: function(index, layero){
		    layer.close(index);
		  }
		});
		
	});
	
</script>
</html>