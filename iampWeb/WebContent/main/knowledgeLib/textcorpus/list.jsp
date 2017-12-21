<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>文本语料管理</title>
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
		   <span class="layui-btn layui-btn-normal layui-btn-small" id="importexcel" title="从excel导入"><i class="layui-icon">&#xe62d;</i></span>
	       <span class="layui-btn layui-btn-normal layui-btn-small" id="exportexcel" title="导出excel"><i class="layui-icon">&#xe624;</i></span>
		</div>
		<span class="layui-btn  layui-btn-normal btn layui-btn-small" id="add">新建</span>
		<span class="layui-btn  layui-btn-warm btn layui-btn-small" id="update">修改</span>
		<span class="layui-btn layui-btn-danger btn layui-btn-small" id="del">删除</span>
			<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;" id="query">查询</span></div>
	    	<%-- <div style="float:right;position:relative;" class="btn"> 
				<label class="layui-form-label">主题词：</label>
			    <div class="layui-input-block" >
			      <input type="text" name="keyWord"  style="width:150px"  placeholder="请输入主题词" autocomplete="off" class="layui-input" value='${keyWord}'>
			    </div>
	    	</div> --%>
	    	<div style="float:right;position:relative;"> 
			    <span class="layui-btn btn layui-btn-small" id="selectDomain">选择</span>
	    	</div>
	    	<div style="float:right;position:relative;" class="btn"> 
				<label class="layui-form-label">领域：</label>
			    <div class="layui-input-block" >
			      <input type="text" id="belongDomain" name="belongDomain"  style="width:150px"  placeholder="请输入领域" autocomplete="off" class="layui-input" value='${belongDomain}'>
			      <input type="hidden" id="belongDomainId" name="belongDomainId"/>
			    </div>
	    	</div>
	    	
	    	<div style="float:right;position:relative;" class="btn"> 
				<label class="layui-form-label">标题：</label>
			    <div class="layui-input-block">
			      <input type="text" name="corpusTitle"  style="width:150px"  placeholder="请输入标题" autocomplete="off" class="layui-input" value='${corpusTitle}'>
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
	     <th>标题</th><th>归属领域</th><th>主题词</th><!-- <th>创建人</th> -->
	     <th>创建时间</th><!-- <th>更新时间</th> -->
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
			    elem: 'page' //注意，这里的 test1 是 ID，不用加 # 号
			    ,count:'${page.total}'//数据总数 //从服务端得到
			    ,limit:10
			    ,groups: 5
			    ,curr : '${page.pageNum}'
			    ,pages: '${page.pages}'
			    ,jump: function(obj, first){
			        if(!first){
			          layer.msg('第 '+ obj.curr +' 页');
			          $('input[name="pageNo"]').val(obj.curr);
			          $('#form').submit();
			        }
			      }
			  });
	});
	
	$("#add").on("click",function(){
		var title="新建文本语料";
		var url = "/iamp/textcorpus/create.do";
		window.parent.detailWins(title,url,'1000px','400px');
	});
	
	$("#update").on("click",function(){
		var  checks = $('input:checkbox[name=corpusId]:checked');
		if(checks.length > 0) {
		var corpusId = checks.eq(0).attr('id');
		var title="修改文本语料";
		var url = "/iamp/textcorpus/update.do?corpusId="+corpusId;
		window.parent.detailWins(title,url,'1000px','400px');
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
	
	$("#del").on("click",function(){
	var  checks = $('input:checkbox[name=corpusId]:checked');
	if(checks.length > 0) {
	
	var layer = window.layer;
		layer.confirm('确定删除该数据吗?', {
				  btn: ['确定', '取消'] //可以无限个按钮
				  ,btnAlign: 'c'
				  ,yes: function(index, layero){
				 	 var corpusId = checks.eq(0).attr('id');
				  		$.ajax({
                type: "GET",
                url:'/iamp/textcorpus/delete.do',
                data:{corpusId:corpusId},// 你的formid
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
	
	$("#importexcel").on("click",function(){
		layer.open({
		  type: 2,
		  title: '从Excel导入',
  		  shade: false,
          area: ['600px', '300px'],
		  content: '/iamp/main/knowledgeLib/textcorpus/importFromExcel.jsp',
		  btnAlign: 'c',
		  btn: ['确定', '取消'],
		  yes: function(index, layero){
		     var loadingEleId="logdingView";
		     var body = layer.getChildFrame('body', index);
		     var iframeHtml = layer.getChildFrame('body');
		     var _PageHeight = $(layero).find("iframe")[0].contentWindow._PageHeight;
		     var _LoadingTop = $(layero).find("iframe")[0].contentWindow._LoadingTop;
		     var _LoadingLeft = $(layero).find("iframe")[0].contentWindow._LoadingLeft;
		     var loadingHtml = '<div id="'+loadingEleId+'" style="position:absolute;left:0;width:100%;height:300px;top:0;background:rgba(0,0,0,0.55);opacity:0.8;filter:alpha(opacity=80);z-index:10000;"><div style="position: absolute; cursor1: wait; left: ' + _LoadingLeft + 'px; top:' + _LoadingTop + 'px; width: auto; height: 57px; line-height: 57px; padding-left: 50px; padding-right: 5px; background: #fff url(/iamp/image/loading.gif) no-repeat scroll 5px 10px; border: 2px solid #95B8E7; color: #696969; font-family:\'Microsoft YaHei\';">正在处理....</div></div>';
		     var belongName = iframeHtml.find("#form").find("#belongDomain").val();
		     var file = iframeHtml.find("#form").find("#file").val();
		     $("#layui-layer1").append(loadingHtml);//弹出正在加载框 
		     if(belongName){
		     	if(file.length != 0){
		     		var fileExt = file.split(".")[1];
		     		//如果是txt文件,提交到txt处理方法
		     		if(fileExt.indexOf("txt") != -1){
		     			 iframeHtml.find("#form").attr("action","/iamp/textcorpus/createCorpusByTxt.do");
		     		}
		     		iframeHtml.find("#form").ajaxSubmit({
			     		dataType: "text",/*设置返回值类型为文本*/
	                    success: function (data) {
	                    	$("#layui-layer1").find('#'+loadingEleId).remove();
	                    	if(data == '0' || data == 0){
	                    		layer.msg('导入失败,请检查文档格式是否正确!', {
								  icon: 1,
								  time: 2000 //2秒关闭（如果不配置，默认是3秒）
								}, function(){
								  layer.close(index);
								  window.location.reload();
								});
	                    	}else{
	                    		  layer.msg('导入成功,共新建文本语料'+data+'个!', {
								  icon: 1,
								  time: 2000 //2秒关闭（如果不配置，默认是3秒）
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
					  content: '请选择上传文件！'
					});
					return;
		     	}
		     }else{
		        $("#layui-layer1").find('#'+loadingEleId).remove();$("#layui-layer1").find('#'+loadingEleId).remove();
		     	layer.open({
				  content: '领域不能为空！'
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
			showMsg('提示','请过滤领域!');
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
			  content: '请选择需要导出的信息！'
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
	
	function showMsg(title, msg) {
		layer.open({
          type: 1
          ,title:title
          ,content: '<div style="padding: 20px 100px;">'+msg+'</div>'
          ,btn: '关闭'
          ,btnAlign: 'c' //按钮居中
          ,shade: 0 //不显示遮罩
          ,yes: function(){
        	  layer.closeAll();
          }
        });	
	}
</script>
</html>