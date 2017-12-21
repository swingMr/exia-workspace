<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>�ı����Ϲ���</title>
</head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="/iamp/js/layui-v2/css/layui.css" media="all" />
<script src="/iamp/js/jquery-1.7.2.min.js"></script>
<script src="/iamp/js/jquery.form.js"></script>
<script type="text/javascript" src="/iamp/js/ztree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="/iamp/js/ztree/js/jquery.ztree.excheck.js"></script>
<style type="text/css">
	.btn{
		margin-top:10px;
		margin-left:10px;
	}
</style>
<body>
	<form class="layui-form" id="form" action="/iamp/textcorpus/list.do" method="post">
		<div class="layui-btn-group btn" style="float:left;position:relative;">
		   <span class="layui-btn layui-btn-normal layui-btn-small" id="importexcel" title="��excel����"><i class="layui-icon">&#xe62d;</i></span>
	       <span class="layui-btn layui-btn-normal layui-btn-small" id="exportexcel" title="����excel"><i class="layui-icon">&#xe624;</i></span>
		</div>
		<span class="layui-btn  layui-btn-normal btn layui-btn-small" id="add">�½�</span>
		<span class="layui-btn  layui-btn-warm btn layui-btn-small" id="update">�޸�</span>
		<span class="layui-btn layui-btn-danger btn layui-btn-small" id="del">ɾ��</span>
			<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;" id="query">��ѯ</span></div>
	    	<%-- <div style="float:right;position:relative;" class="btn"> 
				<label class="layui-form-label">����ʣ�</label>
			    <div class="layui-input-block" >
			      <input type="text" name="keyWord"  style="width:150px"  placeholder="�����������" autocomplete="off" class="layui-input" value='${keyWord}'>
			    </div>
	    	</div> --%>
	    	<div style="float:right;position:relative;"> 
			    <span class="layui-btn btn layui-btn-small" id="selectDomain">ѡ��</span>
	    	</div>
	    	<div style="float:right;position:relative;" class="btn"> 
				<label class="layui-form-label">����</label>
			    <div class="layui-input-block" >
			      <input type="text" id="belongDomain" name="belongDomain"  style="width:150px"  placeholder="����������" autocomplete="off" class="layui-input" value='${belongDomain}'>
			      <input type="hidden" id="belongDomainId" name="belongDomainId"/>
			    </div>
	    	</div>
	    	
	    	<div style="float:right;position:relative;" class="btn"> 
				<label class="layui-form-label">���⣺</label>
			    <div class="layui-input-block">
			      <input type="text" name="corpusTitle"  style="width:150px"  placeholder="���������" autocomplete="off" class="layui-input" value='${corpusTitle}'>
			   	<input type="hidden" name="pageNo" value="${page.pageNum}">
			   	<input type="hidden" name="pageSize" value="${page.pageSize}">
			    </div>
	    	</div>
	</form>
 	<table class="layui-table" lay-skin="line" id="corpusTable">
	  <colgroup>
	  	<col width="50">
	    <col>
	    <col width="120">
	    <col width="250">
	    <%-- <col width="120"> --%>
	    <col width="150">
	   <%--  <col width="150"> --%>
	  </colgroup>
	  <thead>
	    <tr>
	     <th><input type="checkbox" id="checkAll" name="checkAll"></th>
	     <th>����</th><th>��������</th><th>�����</th><!-- <th>������</th> -->
	     <th>����ʱ��</th><!-- <th>����ʱ��</th> -->
	    </tr> 
	  </thead>
	  <tbody>
	  <c:if test="${!empty page.list}">
		  <c:forEach var="item" items="${page.list}" varStatus="status">
		  	<tr>
		  		<td><input type="checkbox" id="${item.corpusId}" name="corpusId"></td>
		  		<td name="corpusTitle">${item.corpusTitle}</td> <td>${item.belongDomain}</td>
		  		<td>${item.keyWord}</td>
		  		<%-- <td>${item.creatorName}</td> --%>
		 	 	<td name="dateStr">
		 	 	<fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm" type="date"/>
		 	 	</td>
		 	 	<%-- <td>
		 	 	<fmt:formatDate value="${item.updateDate}" pattern="yyyy-MM-dd HH:mm" type="date"/>
		 	 	</td> --%>
		  	</tr>
		  </c:forEach>
	  </c:if>
	  </tbody>
	</table> 
	<div id="page" style="position:absolute;right:50px;"></div>
	
	<form class="layui-form" id="exportForm" action="/iamp/textcorpus/exportTextCorpus.do" method="post">
	   	<input type="hidden" id="selectedData" name="selectedData" value="">
	</form>
</body>
<script type="text/javascript" src="/iamp/js/layui-v2/layui.js"></script>
<script>
var mainUrl = '/iamp/textcorpus/list.do';
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
			          $('#form').submit();
			        }
			      }
			  });
	});
	
	$("#add").on("click",function(){
		var title="�½��ı�����";
		var url = "/iamp/textcorpus/create.do";
		window.parent.detailWins(title,url,'1000px','400px');
	});
	
	$("#update").on("click",function(){
		var  checks = $('input:checkbox[name=corpusId]:checked');
		if(checks.length > 0) {
		var corpusId = checks.eq(0).attr('id');
		var title="�޸��ı�����";
		var url = "/iamp/textcorpus/update.do?corpusId="+corpusId;
		window.parent.detailWins(title,url,'1000px','400px');
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
	
	$("#del").on("click",function(){
	var  checks = $('input:checkbox[name=corpusId]:checked');
	if(checks.length > 0) {
	
	var layer = window.layer;
		layer.confirm('ȷ��ɾ����������?', {
				  btn: ['ȷ��', 'ȡ��'] //�������޸���ť
				  ,btnAlign: 'c'
				  ,yes: function(index, layero){
				 	 var corpusId = checks.eq(0).attr('id');
				  		$.ajax({
                type: "GET",
                url:'/iamp/textcorpus/delete.do',
                data:{corpusId:corpusId},// ���formid
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
	
	$("#importexcel").on("click",function(){
		layer.open({
		  type: 2,
		  title: '��Excel����',
  		  shade: false,
          area: ['600px', '300px'],
		  content: '/iamp/main/knowledgeLib/textcorpus/importFromExcel.jsp',
		  btnAlign: 'c',
		  btn: ['ȷ��', 'ȡ��'],
		  yes: function(index, layero){
		     var loadingEleId="logdingView";
		     var body = layer.getChildFrame('body', index);
		     var iframeHtml = layer.getChildFrame('body');
		     var _PageHeight = $(layero).find("iframe")[0].contentWindow._PageHeight;
		     var _LoadingTop = $(layero).find("iframe")[0].contentWindow._LoadingTop;
		     var _LoadingLeft = $(layero).find("iframe")[0].contentWindow._LoadingLeft;
		     var loadingHtml = '<div id="'+loadingEleId+'" style="position:absolute;left:0;width:100%;height:300px;top:0;background:rgba(0,0,0,0.55);opacity:0.8;filter:alpha(opacity=80);z-index:10000;"><div style="position: absolute; cursor1: wait; left: ' + _LoadingLeft + 'px; top:' + _LoadingTop + 'px; width: auto; height: 57px; line-height: 57px; padding-left: 50px; padding-right: 5px; background: #fff url(/iamp/image/loading.gif) no-repeat scroll 5px 10px; border: 2px solid #95B8E7; color: #696969; font-family:\'Microsoft YaHei\';">���ڴ���....</div></div>';
		     var belongName = iframeHtml.find("#form").find("#belongDomain").val();
		     var file = iframeHtml.find("#form").find("#file").val();
		     $("#layui-layer1").append(loadingHtml);//�������ڼ��ؿ� 
		     if(belongName){
		     	if(file.length != 0){
		     		var fileExt = file.split(".")[1];
		     		//�����txt�ļ�,�ύ��txt������
		     		if(fileExt.indexOf("txt") != -1){
		     			 iframeHtml.find("#form").attr("action","/iamp/textcorpus/createCorpusByTxt.do");
		     		}
		     		iframeHtml.find("#form").ajaxSubmit({
			     		dataType: "text",/*���÷���ֵ����Ϊ�ı�*/
	                    success: function (data) {
	                    	$("#layui-layer1").find('#'+loadingEleId).remove();
	                    	if(data == '0' || data == 0){
	                    		layer.msg('����ʧ��,�����ĵ���ʽ�Ƿ���ȷ!', {
								  icon: 1,
								  time: 2000 //2��رգ���������ã�Ĭ����3�룩
								}, function(){
								  layer.close(index);
								  window.location.reload();
								});
	                    	}else{
	                    		  layer.msg('����ɹ�,���½��ı�����'+data+'��!', {
								  icon: 1,
								  time: 2000 //2��رգ���������ã�Ĭ����3�룩
								}, function(){
								  layer.close(index);
								  window.location.reload();
								});
	                    	}
	                    	
	                    },
	             	});
		     	}else{
		     	    $("#layui-layer1").find('#'+loadingEleId).remove();
		     		layer.open({
					  content: '��ѡ���ϴ��ļ���'
					});
					return;
		     	}
		     }else{
		        $("#layui-layer1").find('#'+loadingEleId).remove();$("#layui-layer1").find('#'+loadingEleId).remove();
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
	});
	
	$("#exportexcel").on("click",function(){
		var domainVal = $('input[name="belongDomain"]').val();
		if(domainVal == '') {
			showMsg('��ʾ','���������!');
			return;
		}
		$('#form').attr('action','/iamp/textcorpus/exportTextCorpus.do');
  		$('#form').submit();
  		$('#form').attr('action','/iamp/textcorpus/list.do');
		
		/* var selected = $("#corpusTable").find('input:checkbox[name="corpusId"]:checked');
		var arr = [];
		if(selected.length > 0){
			selected.each(function(){
				var obj = {};
				var corpusTitle = $(this).closest("tr").find("td[name='corpusTitle']").text();
				var createDate = $(this).closest("tr").find("td[name='dateStr']").text().trim();
				obj.corpusTitle = corpusTitle;
				obj.createDate = createDate;
				arr.push(obj);
			});
			$("#selectedData").val(JSON.stringify(arr));
			$("#exportForm").submit();
		}else{
			layer.open({
			  content: '��ѡ����Ҫ��������Ϣ��'
			});
		} */
	});
	
	$('input[name="checkAll"]').change(function(){
		var selectList = $("#corpusTable").find("input[name='corpusId']");
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
</script>
</html>