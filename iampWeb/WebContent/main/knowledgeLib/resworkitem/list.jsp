<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>��Ϣ��Դ������</title>
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
	    <span class="layui-btn  layui-btn-normal layui-btn-small" id="importwcm" title="��wcm����"><i class="layui-icon">&#xe632;</i></span>
	    <span class="layui-btn layui-btn-normal layui-btn-small" id="importexcel" title="��excel����"><i class="layui-icon">&#xe62d;</i></span>
	    <span class="layui-btn layui-btn-normal layui-btn-small" id="importfolder" title="���ļ��е���"><i class="layui-icon">&#xe624;</i></span>
	    <span class="layui-btn  layui-btn-warm  layui-btn-small" id="prehandle">Ԥ����</span>
		<span class="layui-btn layui-btn-danger layui-btn-small" id="del">ɾ��</span>
	  </div>
			<div style="float:right;position:relative;" class="btn"><span class="layui-btn  layui-btn-small" style="margin-right:10px;" id="query">��ѯ</span></div>
	    	<div style="float:right;position:relative;width:120px" class="btn"> 
				<select name="dbCode">
					<option value="">ȫ��</option>
			      	<option value="wcmmetatableflfg" ${!empty dbCode && dbCode == 'wcmmetatableflfg'?'selected':'' }>���ɷ����</option>
			        <option value="wcmmetatablepartyliterature" ${!empty dbCode && dbCode == 'wcmmetatablepartyliterature'?'selected':'' }>�������׿�</option>
			        <option value="wcmmetatabletechnicalstandard" ${!empty dbCode && dbCode == 'wcmmetatabletechnicalstandard'?'selected':'' }>������׼��</option>
			        <option value="wcmmetatablespecialpolicy" ${!empty dbCode && dbCode == 'wcmmetatablespecialpolicy'?'selected':'' }>ר�����߿�</option>
			        <option value="wcmmetatablescientificresearch" ${!empty dbCode && dbCode == 'wcmmetatablescientificresearch'?'selected':'' }>��ѧ�о���</option>
			        <option value="wcmmetatablepublicopinion" ${!empty dbCode && dbCode == 'wcmmetatablepublicopinion'?'selected':'' }>ý����Ѷ��</option>
			        <option value="wcmmetatableleadershipinstruction" ${!empty dbCode && dbCode == 'wcmmetatableleadershipinstruction'?'selected':'' }>�쵼ָʾ��</option>
			        <option value="wcmmetatabletypicalevents" ${!empty dbCode && dbCode == 'wcmmetatabletypicalevents'?'selected':'' }>�����¼���</option>
			        <option value="wcmmetatableforeignresources" ${!empty dbCode && dbCode == 'wcmmetatableforeignresources'?'selected':'' }>������Դ��</option>
			      </select>
	    	</div>
	    	<div style="float:right;position:relative;width:90px" class="btn"> 
			      <select  name="workStatus">
			      	<option value="1" ${!empty workStatus && workStatus == 1?'selected':'' }>������</option>
			        <option value="9" ${!empty workStatus && workStatus == 9?'selected':'' }>�����</option>
			      </select>
	    	</div>
	    	<div style="float:right;position:relative;" class="btn"> 
				<label class="layui-form-label">���⣺</label>
			    <div class="layui-input-block">
			      <input type="text" name="resTitle"  style="width:150px"  placeholder="���������" autocomplete="off" class="layui-input" value='${resTitle }'>
			   	<input type="hidden" name="pageNo" value="${page.pageNum}">
			   	<input type="hidden" name="pageSize" value="${page.pageSize}">
			    </div>
	    	</div>
	    	<div style="float:right;position:relative;"> 
			    <span class="layui-btn btn layui-btn-small" id="selectDomain">ѡ��</span>
	    	</div>
	    	<div style="float:right;position:relative;" class="btn"> 
				<label class="layui-form-label">����</label>
			    <div class="layui-input-block" >
			      <input type="text" name="belongDomain"  id="belongDomain" style="width:150px"  placeholder="����������" autocomplete="off" class="layui-input" value='${belongDomain}'>
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
	     <th>����</th><th>��������</th><th>�����</th><th>״̬</th>
	     <!-- <th>����ʱ��</th> --><!-- <th>ִ����</th> --><th>���ʱ��</th>
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
		  				������
		  			</c:when>
		  			<c:when test="${item.workStatus == -1 }">
		  				ɾ��
		  			</c:when>
		  			<c:when test="${item.workStatus == 9 }">
		  				�����
		  			</c:when>
		  		</c:choose>
		  		</td>
		 	 	<%-- <td>
		 	 	<fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd" type="date"/>
		 	 	</td> --%>
		 	 	<%-- <td>${item.actUserName}</td> --%>
		 	 	<td><c:choose>
		  			<c:when test="${item.workStatus == 1 }">
		  				δ���
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
			    ,pages: '${page.pages}' //��ҳ��
			    ,groups: 5 //������ʾ��ҳ��
			    ,curr : '${page.pageNum}'
			    ,jump: function(obj, first){
			        if(!first){
			          layer.msg('�� '+ obj.curr +' ҳ');
			          $('input[name="pageNo"]').val(obj.curr);
			          $('form').submit();
			          //location.href='/iamp/app/list.do?pageNo='+obj.curr;
			        }
			      }
			  }); 
	});
	
	$("#importwcm").on("click",function(){
		var title="��wcm�������ݿ�";
		var url = "/iamp/main/knowledgeLib/resworkitem/importwcm.jsp";
		window.parent.detailWin(title,url);
		/* var layer = window.layer;
		layer.open({
			title:'�½�Ӧ��',
			type: 2, 
			area: ['1000px', '580px'],
			content:"/iamp/app/create.do"
		});  */
	});
	
	$("#update").on("click",function(){
		var  checks = $('input:checkbox[name=appId]:checked');
		if(checks.length > 0) {
		var appId = checks.eq(0).attr('id');
		var title="�޸�Ӧ��";
		var url = "/iamp/app/update.do?appId="+appId;
		window.parent.detailWin(title,url);
		/* var layer = window.layer;
			layer.open({
				title:'�޸�Ӧ��',
				type: 2, 
				area: ['1000px', '580px'],
				content:"/iamp/app/update.do?appId="+appId
			});  */
	} else {
		var layer = window.layer;
		layer.open({
          type: 1
          ,title:"����"
          ,content: '<div style="padding: 20px 100px;">��ѡ������!</div>'
          ,btn: '�ر�'
          ,btnAlign: 'c' //��ť����
          ,shade: 0 //����ʾ����
          ,yes: function(){
        	  layer.closeAll();
          }
        });	
	}
	});
	
	//�����½��ɹ���ˢ��
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
			var title="����Ԥ����";
			var url = "/iamp/resworkitem/prehandle.do?workItemIds="+workItemIds;
			window.parent.detailWins(title,url,'1200px','700px');
		} else {
			var layer = window.layer;
			layer.open({
	          type: 1
	          ,title:"����"
	          ,content: '<div style="padding: 20px 100px;">��ѡ������!</div>'
	          ,btn: '�ر�'
	          ,btnAlign: 'c' //��ť����
	          ,shade: 0 //����ʾ����
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
		layer.confirm('ȷ��ɾ����������?', {
				  btn: ['ȷ��', 'ȡ��'] //�������޸���ť
				  ,btnAlign: 'c'
				  ,yes: function(index, layero){
				  		var workItemId = checks.eq(0).attr('id');
					$.ajax({
				                type: "GET",
				                url:'/iamp/resworkitem/delete.do',
				                data:{workItemId:workItemId},// ���formid
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
								          ,title:'����'
								          ,content: '<div style="padding: 20px 100px;">'+data.msg+'</div>'
								          ,btn: '�ر�'
								          ,btnAlign: 'c' //��ť����
								          ,shade: 0 //����ʾ����
								          ,yes: function(){
								        	  layer.closeAll();
											  window.location.href=mainUrl;
								          }
								        });	
				                	} else {
				                		showMsg('����',data.msg);
										return;
				                	}
				                }
				            });
				  }})
	} else {
		var layer = window.layer;
		layer.open({
          type: 1
          ,title:"����"
          ,content: '<div style="padding: 20px 100px;">��ѡ������!</div>'
          ,btn: '�ر�'
          ,btnAlign: 'c' //��ť����
          ,shade: 0 //����ʾ����
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
		     layer.close(index);
		  },
		  btn2: function(index, layero){
		    layer.close(index);
		  }
		});
	});
</script>
</html>