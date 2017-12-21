<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>${roleName}�Ĺ�ע����</title>
</head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="/iamp/js/layui-v2/css/layui.css" media="all" />
<script src="/iamp/js/jquery-1.7.2.min.js"></script>
<style type="text/css">
	.btn{
		margin-top:10px;
		margin-left:10px;
	}
</style>
<body>
	<form class="layui-form" id="form" action="/iamp/userDomain/domainList.do" method="post">
		<span class="layui-btn  layui-btn-normal btn layui-btn-small" id="add">���</span>
		<span class="layui-btn layui-btn-danger btn layui-btn-small" id="del">ɾ��</span>
		<input type="hidden" id="userDomainIds" name="userDomainIds"/> 
		<input type="hidden" name="memberId" value="${memberId }"/> 
		<input type="hidden" name="memberName" value="${roleName }"/> 
		<input type="hidden" name="pageNo"/> 
	</form>
 	<table class="layui-table" lay-skin="line" id="domainTable">
	  <colgroup>
	  	<col width="50">
	    <col width="200">
	    <col width="120">
	    <col width="120">
	    <col width="50">
	    <col>
	  </colgroup>
	  <thead>
	    <tr>
	     <th><input type="checkbox" id="checkAll" name="checkAll"></th>
	     <th>��������</th><th>������</th><th>����ʱ��</th>
	    </tr> 
	  </thead>
	  <tbody id="domainList">
	  <c:if test="${!empty page.list}">
		  <c:forEach var="domain" items="${page.list}" varStatus="status">
		  	<tr>
		  		<td><input type="checkbox" id="${domain.userDomainId}" name="userDomainId"></td>
		  		<td>${domain.domainName}</td> <td>${domain.creatorName}</td>
		 	 	<td>
		 	 	<fmt:formatDate value="${domain.createDate}" pattern="yyyy-MM-dd HH:mm" type="date"/>
		 	 	</td>
		  	</tr>
		  </c:forEach>
	  </c:if>
	  </tbody>
	</table> 
	<div id="page" style="position:absolute;right:50px;"></div>
</body>
<script type="text/javascript" src="/iamp/js/layui-v2/layui.js"></script>
<script>
var  userDomainIds = "";//��Ҫɾ��ɾ��������id
var  memberId = "${memberId}";
var layer;
	layui.use(['layer','laypage','form','element'], function(){
		var form = layui.form;
		var element = layui.element;
		  layer = layui.layer;
		  window.layer = layer;
		  var laypage = layui.laypage; 
		  	  laypage.render({
			    elem: 'page' //ע�⣬����� test1 �� ID�����ü� # ��
			    ,count:'${page.total}'//�������� //�ӷ���˵õ�
			    ,limit:10
			    ,groups: 5
			    ,curr : '${page.pageNum}'
			    ,pages: '${page.pages}'
			    ,jump: function(obj, first){
			        if(!first){
			          layer.msg('�� '+ obj.curr +' ҳ');
			          $('input[name="pageNo"]').val(obj.curr);
			          $('form').submit();
			        }
			      }
			  });
	});
	
	$("#add").on("click",function(){
		layer.open({
		  type: 2,
		  title: 'ѡ������',
  		  shade: false,
          area: ['350px', '500px'],
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
		     
		     if(domainNames){
			     	$.ajax({
			     		url : "/iamp/userDomain/create.do",
			     		type : "post",
			     		data : {"domainNames":domainNames,"memberIds":memberId},
			     		dataType : "json",
			     		success : function(data){
			     			if(data.total == '0' || data.total == 0){
	                    		layer.msg('�޸�ʧ��,��������Ƿ���ȷ!', {
								  icon: 1,
								  time: 2000 //2��رգ���������ã�Ĭ����3�룩
								}, function(){
								  layer.close(index);
								  window.location.reload();
								});
	                    	}else{
	                    		 /*  var result = data.data;
	                    		  if(result){
	                    		  	for(var i=0; i< result.length; i++){
							     		var appendTr = '<tr><td><input type="checkbox" id="'+result.userDomainId+'" name="userDomainId"></td>'+
							  					   		'<td>'+result.domainName+'</td> <td>'+result.creatorName+'</td><td>'+result.createDate+'</td></tr>';
							  			$("#domainList").append(appendTr);
							     	}
	                    		  } */
	                    		  layer.msg('�޸ĳɹ�,���޸�'+data.total+'��!', {
								  icon: 1,
								  time: 2000 //2��رգ���������ã�Ĭ����3�룩
								}, function(){
								  layer.close(index);
								  window.location.reload();
								});
	                    	}
			     		}
			     	});
		     		
			     }else{
			     	layer.open({
					  content: '������Ϊ�գ�'
					});
					return;
			     }
		     
		     
		     /*  if(domainNames){
		     	var domainArr = domainNames.split(";");
		     	for(var i=0; i< domainArr.length; i++){
		     		var appendTr = '<tr><td><input type="checkbox" id="" name="userDomainId"></td>'+
		  					   		'<td>'+domainArr[i]+'</td> <td></td><td></td></tr>';
		  			$("#domainList").append(appendTr);
		     	}
		     	 layer.close(index);
		     }else{
		     	layer.open({
				  content: '������Ϊ�գ�'
				});
				return;
		     } */
		    layer.close(index);
		  },
		  btn2: function(index, layero){
		    layer.close(index);
		  }
		});
	
	});
	
	
	//�����½��ɹ���ˢ��
	function refresh(){
		location.href = mainUrl;
	}
	
	$("#del").on("click",function(){
	var  checks = $('input:checkbox[name=userDomainId]:checked');
	if(checks.length > 0) {
		var layer = window.layer;
		layer.confirm('ȷ��ɾ����������?', {
				  btn: ['ȷ��', 'ȡ��'] //�������޸���ť
				  ,btnAlign: 'c'
				  ,yes: function(index, layero){
				  		checks.each(function(){
							var userDomainId = $(this).attr("id");
							if(userDomainIds){
								userDomainIds += ";";
							}
							if(userDomainId){
								userDomainIds += userDomainId;
							}
							//$(this).closest("tr").remove();
						});
				  		$.ajax({
			     		url : "/iamp/userDomain/delete.do",
			     		type : "post",
			     		data : {"userDomainIds":userDomainIds},
			     		dataType : "text",
			     		success : function(data){
			     			if(data == '0' || data == 0){
	                    		layer.msg('ɾ��ʧ��,��������Ƿ���ȷ!', {
								  icon: 1,
								  time: 2000 //2��رգ���������ã�Ĭ����3�룩
								}, function(){
								  layer.close(index);
								  window.location.reload();
								});
	                    	}else{
	                    		  layer.msg('ɾ���ɹ�,���޸�'+data+'��!', {
								  icon: 1,
								  time: 2000 //2��رգ���������ã�Ĭ����3�룩
								}, function(){
								  layer.close(index);
								  window.location.reload();
								});
	                    	}
			     		}
			     	});
				  		layer.close(index);
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
	
	$('input[name="checkAll"]').change(function(){
		var selectList = $("#domainTable").find("input[name='userDomainId']");
		if(this.checked){
			selectList.each(function(){
				 $(this).prop("checked",'true');
			 });
		}else{
			selectList.each(function(){
				$(this).removeAttr("checked");
			 });
		}
	});
	
	
	$('#query').on('click',function() {
		$('#form').submit();
	})
	
	function showMsg(title, msg) {
		layer.open({
          type: 1
          ,title:title
          ,content: '<div style="padding: 20px 100px;">'+msg+'</div>'
          ,btn: '�ر�'
          ,btnAlign: 'c' //��ť����
          ,shade: 0 //����ʾ����
          ,yes: function(){
        	  layer.closeAll();
          }
        });	
	}
	
	$("#domainHander").on("click",function(){
	     var selectList = $("#memberTable").find('input:checkbox[name="memberId"]:checked');
	     if(selectList.length > 0){
	     	var memberIds = "";
			 selectList.each(function(){
				 var memberId = $(this).attr("id");
				 if(memberIds){
				 	memberIds += ";";
				 }
				 memberIds += memberId;
			 });
			layer.open({
			  type: 2,
			  title: '������������',
	  		  shade: false,
	          area: ['600px', '220px'],
			  content: '/iamp/main/knowledgeLib/resourceLib/selectBelongDomain.jsp',
			  btnAlign: 'c',
			  btn: ['ȷ��', 'ȡ��'],
			  yes: function(index, layero){
			     var body = layer.getChildFrame('body', index);
			     var iframeHtml = layer.getChildFrame('body');
			     var domainNames = iframeHtml.find("#form").find("#belongDomain").val();
			     if(domainNames){
			     
		     		
			     }else{
			     	layer.open({
					  content: '������Ϊ�գ�'
					});
					return;
			     }
			  },
			  btn2: function(index, layero){
			    layer.close(index);
			  }
			});
	     }else{
	     	layer.open({
			  content: '��ѡ��Ҫ�޸ĵ����ݣ�'
			});
	     }
	     
	});
	
	
</script>
</html>