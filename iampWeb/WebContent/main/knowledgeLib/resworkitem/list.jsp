<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>信息资源工作项</title>
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
</style>
<body>
	<form class="layui-form" id="form" action="/iamp/resworkitem/list.do" method="post">
	   <div class="layui-btn-group btn" style="float:left;position:relative;">
	    <span class="layui-btn  layui-btn-normal layui-btn-small" id="importwcm" title="从wcm导入"><i class="layui-icon">&#xe632;</i></span>
	    <span class="layui-btn layui-btn-normal layui-btn-small" id="importexcel" title="从excel导入"><i class="layui-icon">&#xe62d;</i></span>
	    <span class="layui-btn layui-btn-normal layui-btn-small" id="importfolder" title="从文件夹导入"><i class="layui-icon">&#xe624;</i></span>
	    <span class="layui-btn  layui-btn-warm  layui-btn-small" id="prehandle">预处理</span>
		<span class="layui-btn layui-btn-danger layui-btn-small" id="del">删除</span>
	  </div>
			<div style="float:right;position:relative;" class="btn"><span class="layui-btn  layui-btn-small" style="margin-right:10px;" id="query">查询</span></div>
	    	<div style="float:right;position:relative;width:120px" class="btn"> 
				<select name="dbCode">
					<option value="">全部</option>
			      	<option value="wcmmetatableflfg" ${!empty dbCode && dbCode == 'wcmmetatableflfg'?'selected':'' }>法律法规库</option>
			        <option value="wcmmetatablepartyliterature" ${!empty dbCode && dbCode == 'wcmmetatablepartyliterature'?'selected':'' }>党的文献库</option>
			        <option value="wcmmetatabletechnicalstandard" ${!empty dbCode && dbCode == 'wcmmetatabletechnicalstandard'?'selected':'' }>技术标准库</option>
			        <option value="wcmmetatablespecialpolicy" ${!empty dbCode && dbCode == 'wcmmetatablespecialpolicy'?'selected':'' }>专项政策库</option>
			        <option value="wcmmetatablescientificresearch" ${!empty dbCode && dbCode == 'wcmmetatablescientificresearch'?'selected':'' }>科学研究库</option>
			        <option value="wcmmetatablepublicopinion" ${!empty dbCode && dbCode == 'wcmmetatablepublicopinion'?'selected':'' }>媒体资讯库</option>
			        <option value="wcmmetatableleadershipinstruction" ${!empty dbCode && dbCode == 'wcmmetatableleadershipinstruction'?'selected':'' }>领导指示库</option>
			        <option value="wcmmetatabletypicalevents" ${!empty dbCode && dbCode == 'wcmmetatabletypicalevents'?'selected':'' }>典型事件库</option>
			        <option value="wcmmetatableforeignresources" ${!empty dbCode && dbCode == 'wcmmetatableforeignresources'?'selected':'' }>国外资源库</option>
			      </select>
	    	</div>
	    	<div style="float:right;position:relative;width:90px" class="btn"> 
			      <select  name="workStatus">
			      	<option value="1" ${!empty workStatus && workStatus == 1?'selected':'' }>待处理</option>
			        <option value="9" ${!empty workStatus && workStatus == 9?'selected':'' }>已完成</option>
			      </select>
	    	</div>
	    	<div style="float:right;position:relative;" class="btn"> 
				<label class="layui-form-label">标题：</label>
			    <div class="layui-input-block">
			      <input type="text" name="resTitle"  style="width:150px"  placeholder="请输入标题" autocomplete="off" class="layui-input" value='${resTitle }'>
			   	<input type="hidden" name="pageNo" value="${page.pageNum}">
			   	<input type="hidden" name="pageSize" value="${page.pageSize}">
			    </div>
	    	</div>
	    	<div style="float:right;position:relative;"> 
			    <span class="layui-btn btn layui-btn-small" id="selectDomain">选择</span>
	    	</div>
	    	<div style="float:right;position:relative;" class="btn"> 
				<label class="layui-form-label">领域：</label>
			    <div class="layui-input-block" >
			      <input type="text" name="belongDomain"  id="belongDomain" style="width:150px"  placeholder="请输入领域" autocomplete="off" class="layui-input" value='${belongDomain}'>
			      <input type="hidden" id="belongDomainId" name="belongDomainId"/>
			    </div>
	    	</div>
	</form>
 	<table class="layui-table" lay-skin="line">
	  <colgroup>
	  	<col width="50">
	    <col>
	    <col width="120">
	    <col width="250">
	    <col width="120">
	    <%-- <col width="120"> --%>
	   <%--  <col width="120"> --%>
	    <col width="150">
	  </colgroup>
	  <thead>
	    <tr>
	     <th><input type="checkbox" id="checkAll" name="checkAll"></th>
	     <th>标题</th><th>归属领域</th><th>主题词</th><th>状态</th>
	     <!-- <th>创建时间</th> --><!-- <th>执行者</th> --><th>完成时间</th>
	    </tr> 
	  </thead>
	  <tbody>
	  <c:if test="${!empty page.list}">
		  <c:forEach var="item" items="${page.list}" varStatus="status">
		  	<tr>
		  		<td><input type="checkbox" id="${item.workItemId}" name="workItemId"></td>
		  		<td>${item.resTitle}</td> <td>${item.belongDomain}</td>
		  		<td>${item.keyWord}</td>
		  		<td>
		  		<c:choose>
		  			<c:when test="${item.workStatus == 1 }">
		  				待处理
		  			</c:when>
		  			<c:when test="${item.workStatus == -1 }">
		  				删除
		  			</c:when>
		  			<c:when test="${item.workStatus == 9 }">
		  				已完成
		  			</c:when>
		  		</c:choose>
		  		</td>
		 	 	<%-- <td>
		 	 	<fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd" type="date"/>
		 	 	</td> --%>
		 	 	<%-- <td>${item.actUserName}</td> --%>
		 	 	<td><c:choose>
		  			<c:when test="${item.workStatus == 1 }">
		  				未完成
		  			</c:when>
		  			<c:when test="${item.workStatus == 9 }">
		  				<fmt:formatDate value="${item.completeDate}" pattern="yyyy-MM-dd HH:mm" type="date"/>
		  			</c:when>
		  		</c:choose></td>
		  	</tr>
		  </c:forEach>
	  </c:if>
	  </tbody>
	</table> 
	<div id="page" style="position:absolute;right:50px;"></div>
</body>
<script type="text/javascript" src="/iamp/js/layui/layui.js"></script>
<script>
var mainUrl = '/iamp/resworkitem/list.do';
var layer;
	layui.use(['layer','laypage','form','element'], function(){
		var form = layui.form();
		var element = layui.element();
		  layer = layui.layer;
		  window.layer = layer;
		  var laypage = layui.laypage; 
			  laypage({
			    cont: 'page'//id
			    ,pages: '${page.pages}' //分页数
			    ,groups: 5 //连续显示分页数
			    ,curr : '${page.pageNum}'
			    ,jump: function(obj, first){
			        if(!first){
			          layer.msg('第 '+ obj.curr +' 页');
			          $('input[name="pageNo"]').val(obj.curr);
			          $('form').submit();
			          //location.href='/iamp/app/list.do?pageNo='+obj.curr;
			        }
			      }
			  }); 
	});
	
	$("#importwcm").on("click",function(){
		var title="从wcm导入数据库";
		var url = "/iamp/main/knowledgeLib/resworkitem/importwcm.jsp";
		window.parent.detailWin(title,url);
		/* var layer = window.layer;
		layer.open({
			title:'新建应用',
			type: 2, 
			area: ['1000px', '580px'],
			content:"/iamp/app/create.do"
		});  */
	});
	
	$("#update").on("click",function(){
		var  checks = $('input:checkbox[name=appId]:checked');
		if(checks.length > 0) {
		var appId = checks.eq(0).attr('id');
		var title="修改应用";
		var url = "/iamp/app/update.do?appId="+appId;
		window.parent.detailWin(title,url);
		/* var layer = window.layer;
			layer.open({
				title:'修改应用',
				type: 2, 
				area: ['1000px', '580px'],
				content:"/iamp/app/update.do?appId="+appId
			});  */
	} else {
		var layer = window.layer;
		layer.open({
          type: 1
          ,title:"提醒"
          ,content: '<div style="padding: 20px 100px;">请选择数据!</div>'
          ,btn: '关闭'
          ,btnAlign: 'c' //按钮居中
          ,shade: 0 //不显示遮罩
          ,yes: function(){
        	  layer.closeAll();
          }
        });	
	}
	});
	
	//用于新建成功后刷新
	function refresh(){
		location.href = mainUrl;
	}
	
	
	$('#checkAll').on('change',function() {
		if($(this).attr('checked') == 'checked') {
			$('input[name="workItemId"]').attr('checked','');
		} else {
			$('input[name="workItemId"]').removeAttr('checked','');
		}
	})
	
	$('#prehandle').on('click',function() {
		var  checks = $('input:checkbox[name=workItemId]:checked');
		if(checks.length > 0) {
			var workItemIdsArr = [];
			checks.each(function() {
				var id = $(this).attr('id');
					workItemIdsArr.push(id);
			})
			var workItemIds=workItemIdsArr.join(';');
			var title="批量预处理";
			var url = "/iamp/resworkitem/prehandle.do?workItemIds="+workItemIds;
			window.parent.detailWins(title,url,'1200px','700px');
		} else {
			var layer = window.layer;
			layer.open({
	          type: 1
	          ,title:"提醒"
	          ,content: '<div style="padding: 20px 100px;">请选择数据!</div>'
	          ,btn: '关闭'
	          ,btnAlign: 'c' //按钮居中
	          ,shade: 0 //不显示遮罩
	          ,yes: function(){
	        	  layer.closeAll();
	          }
	        });	
		}
	})
	
	$("#del").on("click",function(){
	var  checks = $('input:checkbox[name=workItemId]:checked');
	if(checks.length > 0) {
		var layer = window.layer;
		layer.confirm('确定删除该数据吗?', {
				  btn: ['确定', '取消'] //可以无限个按钮
				  ,btnAlign: 'c'
				  ,yes: function(index, layero){
				  		var workItemId = checks.eq(0).attr('id');
					$.ajax({
				                type: "GET",
				                url:'/iamp/resworkitem/delete.do',
				                data:{workItemId:workItemId},// 你的formid
				                async: false,
				                error: function(request) {
				                    alert("Connection error");
				                },
				                dataType:"json",
				                success: function(data) {
				                	if(data.result) {
				                	var layer = window.layer;
				                		layer.open({
								          type: 1
								          ,title:'提醒'
								          ,content: '<div style="padding: 20px 100px;">'+data.msg+'</div>'
								          ,btn: '关闭'
								          ,btnAlign: 'c' //按钮居中
								          ,shade: 0 //不显示遮罩
								          ,yes: function(){
								        	  layer.closeAll();
											  window.location.href=mainUrl;
								          }
								        });	
				                	} else {
				                		showMsg('提醒',data.msg);
										return;
				                	}
				                }
				            });
				  }})
	} else {
		var layer = window.layer;
		layer.open({
          type: 1
          ,title:"提醒"
          ,content: '<div style="padding: 20px 100px;">请选择数据!</div>'
          ,btn: '关闭'
          ,btnAlign: 'c' //按钮居中
          ,shade: 0 //不显示遮罩
          ,yes: function(){
        	  layer.closeAll();
          }
        });	
	}
	});
	$('#query').on('click',function() {
		$('#form').submit();
	})
	
	$("#selectDomain").on("click",function(){
		layer.open({
		  type: 2,
		  title: '选择领域',
  		  shade: false,
          area: ['350px', '600px'],
		  content: '/iamp/main/datadig/domainTree.jsp',
		  btn: ['确定', '取消'],
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
		     layer.close(index);
		  },
		  btn2: function(index, layero){
		    layer.close(index);
		  }
		});
	});
</script>
</html>