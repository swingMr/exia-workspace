<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>信息资源维护</title>
</head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="/iamp/js/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="/iamp/css/fonts/iconfont.css" type="text/css">
<link rel="stylesheet" href="/iamp/js/layui/css/global.css" media="all">
<script src="/iamp/js/jquery-1.7.2.min.js"></script>
<script src="/iamp/js/layui/layui.js" ></script>
<link rel="stylesheet" href="/iamp/js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="/iamp/js/ztree/js/jquery.ztree.core.js"></script>
<style type="text/css">
.dropbtn {
    background-color: #4CAF50;
    color: white;
    padding: 4px;
    font-size: 16px;
    border: none;
    cursor: pointer;
}

.dropdown {
    position: relative;
    display: inline-block;
}

.dropdown-content {
    display: none;
    position: absolute;
    background-color: #F2F2F2;/* 
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2); */
}

.dropdown-content span {
    color: black;
    text-decoration: none;
    display: block;
}

.dropdown:hover .dropdown-content {
    display: block;
}

.dropdown:hover .dropbtn {
    background-color: #1AA094;
}

	.btn{
		margin-top:10px;
		margin-left:10px;
	}
	.title{
		
	}
	.wenjian-content {
		margin: 10px 0px;
	}
	.wenjian-content li {
		display: inline-block;
		width: 275px;
		height: 420px;
		margin: 0 12px 14px 0;
	}
	.wenjian-box {
		position: absolute;
		width: 275px;
		height: 420px;
		-webkit-box-shadow: 0 0 10px rgba(0, 0, 0, .2);
		-moz-box-shadow: 0 0 10px rgba(0, 0, 0, .2);
		box-shadow: 0 0 10px rgba(0, 0, 0, .2);
	}
	.wenjian-content li img {
		width: 275px;
		height: 350px;
	}
	.wenjian-title {
		margin: 13px 10px;
		word-break:break-all;
		display:-webkit-box;
		-webkit-line-clamp:2;
		-webkit-box-orient:vertical;
		overflow:hidden;
		line-height: 1.5;
		height: 40px;
		
	}
	.wenjian-more {
		height: 30px;
		text-align: center;
		line-height: 50px;
		color: #666666;
		margin: 0 6px;
		border-top: 1px dashed #e2e2e2;
	}
	.wenjian-more a:hover {
		color: #52A2F5;
	}
	.wenjian-listcontent{
		margin: 0 0 20px 0;
	}
	.wenjian-listcontent li{
		padding: 5px 10px 15px 10px;
		border-bottom: 1px dashed #e2e2e2;
	}
	
	.wenjian-listcontent li:last-child{
		border: none;
	}
	.wenjian-listcontent li spam{
		color: #555555;
	}

	.work-biaoqian {
		position: absolute;
		top: 0;
		padding: 6px 10px;
		font-size: 12px;
		color: #fff;
	}
	.blue {
		background-color: #5B98FB;
	}
	.red {
		background-color: #DB4949;
	}
	.yellow {
		background-color: #f5b02b;
	}
	.green {
		background-color: #52CC75;
	}
	.work-del {
		position: absolute;
		right: 10px;
		top: 10px;
	}
	.del {
		color: #f79d9d;
		margin: 0 10px;
		float: left;
	}
	.del:hover {
		color: #E2585A;
	}
</style>

<body class="layui-layout">	
	<!-- 公用表单按钮 -->
  <form class="layui-form" id="commonForm" action="/iamp/resource/list.do" >
	  <div class="layui-btn-group btn dropdown" style="float:left;position:relative;">
	  <span class="layui-btn  layui-btn-normal layui-btn-small " >新建</span>
	  <div class="dropdown-content">
	   <span class="layui-btn  layui-btn-small" style="background-color: #F2F2F2" id="addhtml" title="新建HTML">新建HTML</span>
       <span class="layui-btn  layui-btn-small" style="background-color: #F2F2F2" id="addword" title="新建word">新建word</span>
       <span class="layui-btn  layui-btn-small" style="background-color: #F2F2F2" id="addexcel" title="新建Excel">新建Excel</span>
       <span class="layui-btn  layui-btn-small" style="background-color: #F2F2F2" id="addtxt" title="新建txt">新建txt</span>
	  </div>

	</div>
	<span class="layui-btn  layui-btn-normal btn layui-btn-small" id="upload">上传</span>
	<span class="layui-btn  layui-btn-warm btn layui-btn-small" id="update">修改</span>
	<span class="layui-btn layui-btn-danger btn layui-btn-small" id="del">删除</span>
	<span class="layui-btn btn layui-btn-small" id="input">导入</span>
	<span class="layui-btn btn layui-btn-small" id="output">导出</span>
	<span class="layui-btn btn layui-btn-small" id="setBelongDomain">设置领域</span>
	<span class="layui-btn btn layui-btn-small" id="editCatalog">修改目录</span>
	<div style="float:right;position:relative;" class="btn"><i class="layui-icon showFileDiv" style="font-size: 15px; margi-right:10px; color: #1E9FFF;">&#xe647;</i>&nbsp;&nbsp;</div>
	<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;" id="search">查询</span></div>
	<div style="float:right;position:relative;" class="btn"> 
	      <input type="text" style="width:100px" name="searchAttr" id="searchAttr" value="${searchAttr}" required  lay-verify="required" placeholder="筛选属性要求" autocomplete="off" class="layui-input">
   		  <input type="hidden" id="catalogId" name="catalogId" value="${catalogId}">
   		  <input type="hidden" name="ids">
	      <input type="hidden" id="libNum" name="libNum" value="${libNum}">
	      <input type="hidden" id="catalogNum" name="catalogNum" value="${catalogNum}">
   	</div>
  </form>
  <!-- 列表DIV -->
  <div class="mainDiv">
  <form id="resourceForm" action="/iamp/resource/list.do">
 	<table class="layui-table" lay-skin="line" id="resourceTable">
	  <colgroup>
	  	<col width="40">
	    <col>
	    <col width="150">
	    <col width="150">
	    <col width="150">
	    <col width="110">
	   <%--  <col width="120">
	    <col width="150"> --%>
	  </colgroup>
	  <thead>
	    <tr>
	     <th></th><th id="orderByTitle">标题&nbsp;&nbsp;
	     <c:choose>
	     	<c:when test="${orderByTitle =='0'}">
	     			 <i class="layui-icon" style="font-size: 15px;color: #009688;">↓</i>
	     	</c:when>
	     	<c:when test="${orderByTitle =='1'}">
			     	 <i class="layui-icon"  style="font-size: 15px;color: #009688;">↑</i>
	     	</c:when>
	     	<c:otherwise>
	     	</c:otherwise>
	     </c:choose>
	     </th><th>归属领域</th><th >来源</th>
	     <th >发布者</th><th  id="orderByDate">发布日期&nbsp;&nbsp;
	     <c:choose>
	     	<c:when test="${order =='0'}">
	     			 <i class="layui-icon"  style="font-size: 15px;color: #009688;">↓</i>
	     	</c:when>
	     	<c:when test="${order =='1'}">
	     			 <i class="layui-icon"  style="font-size: 15px;color: #009688;">↑</i>
	     	</c:when>
	     	<c:otherwise>
	     			
	     	</c:otherwise>
	     </c:choose>
	     <input type="hidden" name="orderByDate"  value="${order}"/>
	     <input type="hidden" name="orderByTitle"  value="${orderByTitle}"/>
	     </th><!-- <th >创建人</th><th>创建时间</th> -->
	    </tr> 
	  </thead>
	  <tbody>
	  <c:forEach var="list" items="${list}" varStatus="status" >
	  	<tr>
		  	<td><input type="checkbox" name="id" value="${list.id}"></td>
	  		<td><a class="showView" href="#" name="${list.id}">${list.title}</a></td>
	  		<td><div style="text-overflow: ellipsis;white-space: nowrap;width:150px;overflow: hidden;"  title="${list.domainstr}" >${list.domainstr}</div></td>
	  		<td><input type="hidden" class="validTime" value="<fmt:formatDate value='${list.validTime}'   pattern='yyyy-MM-dd HH:mm' type='date' dateStyle='long' />">${list.source}</td><td>${list.publisher}</td><td><fmt:formatDate value="${list.publishDate}"   pattern="yyyy-MM-dd" type="date" dateStyle="long" /></td>
	  		 <%-- <td>${list.creatorName}</td><td><fmt:formatDate value="${list.createDate}"   pattern="yyyy-MM-dd HH:mm" type="date" dateStyle="long" /></td> --%>
	  	</tr>
	  </c:forEach>
	  </tbody>
	</table>
  </form> 
	<div id="page" style="position:absolute;right:50px;"></div>
  </div>
  <!-- 文件DIV -->
  <div class="fileDiv">
	 <div class="wenjian-content">
              <ul>
              <c:forEach var="list" items="${list}" varStatus="status">
                <li><div class="wenjian-box"> <a href=""><img src="" alt=""/></a>
                  <div class="wenjian-title"> <a href="#">${list.title}</a> </div>
                  <div class="work-biaoqian green">改革发展</div>
			 		<a class="work-del del"><i class="iconfont  icon-shanchu"  style="font-size: 14px;"></i></a> 
			 	</div></li>
              </c:forEach>
              </ul>
            </div>
            <div class="wenjian-more"><a href="#">加载更多</a></div>
 </div>
  <div class="clear"></div>
</body>
<script type="text/javascript" src="/iamp/js/layui/layui.js"></script>
<script>
var layer;
var element;
var ajaxDel = '/iamp/resource/del.do';
var mainUrl = '/iamp/resource/list.do';
$(function(){
	var searchAttrs = '${searchAttr}';
	if(searchAttrs !=null && searchAttrs != ""){
		$("#searchAttr").val(searchAttrs);
	}
	var displaymode  = '${mode}';
	if(displaymode == 0){
		$(".mainDiv").show();
		$(".fileDiv").hide();
	}else{
		$(".mainDiv").hode();
		$(".fileDiv").show();	
	}
	
	$(".layui-layout").on("click",".showFileDiv",function(){
		$(".mainDiv").hide();
		$(".fileDiv").show();
		 $(this).addClass("showMainDiv");
		 $(this).removeClass("showFileDiv");
	});
	
	$(".layui-layout").on("click",".showMainDiv",function(){
		$(".mainDiv").show();
		$(".fileDiv").hide();
		 $(this).addClass("showFileDiv");
		 $(this).removeClass("showMainDiv");
	});
	
	//新建
	$("#addhtml").on("click",function(){
		var title ='新建信息资源';
		var libNum  = $("#libNum").val();
		var catalogNum = $("#catalogNum").val();
		var catalogId = $('#catalogId').val();
		if(catalogNum !=""){
			var url ="/iamp/resource/showDetailView.do?libNum="+libNum+"&catalogNum="+catalogNum+"&catalogId="+catalogId;
			window.parent.detailWin(title,url);
		}else{
			var data = new Object();
			data.console = "请选择节点!";
			warning(data);
		}
	});
	
	$("#upload").on("click",function(){
		var libNum  = $("#libNum").val();
		var catalogNum = $("#catalogNum").val();
		var catalogId = $("#catalogId").val();
		layer.open({
		  type: 2,
		  title: '上传文件',
  		  shade: false,
          area: ['800px', '550px'],
		  content: '/iamp/main/knowledgeLib/resourceLib/uploadFile.jsp?catalogNum='+catalogNum+'&libNum='+libNum+'&catalogId='+catalogId
		});  
	});

	//浏览界面
	$(".showView").on("click",function(){
		var catalogNum = $("#catalogNum").val();
		var catalogId = $('#catalogId').val();
		var libNum  = $("#libNum").val();
		var id = $(this).attr("name");
		var title ="查看信息资源";
		var url ="/iamp/resource/showDetail?id="+id+"&catalogNum="+catalogNum+"&libNum="+libNum+'&catalogId='+catalogId;
		window.parent.detailWin(title,url);
	});
	//修改目录功能；
	$("#editCatalog").on("click",function(){
		var data = new Object();
		var  checks = $('input:checkbox[name=id]:checked');
		var catalogNum = $("#catalogNum").val();
		if(catalogNum !=""){
			var libNum = $("#libNum").val();
			if(checks.length == 1) {
				var id = checks[0].value;
/* 	 			var title ="修改目录";
	 			var url ="/iamp/resource/showCatalogView?resourceId="+id+"&libNum="+libNum;
	 			window.parent.detailWin(title,url); */
	 			
	 			var layer = window.layer;
	 			layer.open({
	 				title:"修改目录",
	 				maxmin: true,
	 				type: 2, 
	 				area: ['355px', '500px'],
	 				btnAlign: 'c',
	 				content:"/iamp/resource/showCatalogView?resourceId="+id+"&libNum="+libNum,
	 				btn: ['确定', '取消'],
	 				yes: function(index, layero){
	 				    //按钮【按钮一】的回调
	 				    var treeObj = $(layero).find("iframe")[0].contentWindow.treeObj;
	 				    var catalogId = treeObj.getSelectedNodes()[0].id;
	 				    console.log(treeObj.getSelectedNodes()[0]);
						//修改信息资源的目录；
						$.post(
							"/iamp/resource/updateCatalog",
							{
							"resourceId":id,
							"catalogId":catalogId,
							"libNum":libNum
							},function(datas){
								if(datas.msg == "success"){
									data.console='修改目录成功！';
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
									    			parent.reload();
									    		}
									    		refresh();
									      }
									    });
									
								}else{
									data.console='修改目录失败！';
									warning(data);
								}
							},"json"
						);
						//刷新页面；
	 				 },
	 			  	success: function(layero, index){
	 				    var body = layer.getChildFrame('body', index);
	 				    var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
	 						window.child = iframeWin;
	 				    }
	 			});
	 			
	 			
	 			
			}else if(checks.length == 0){
				data.console = "请选择数据!";
				warning(data);
			}else{
				data.console = "请选择一条数据!";
				warning(data);
			}
		}else{
			var data = new Object();
			data.console = "请选择节点!";
			warning(data);
		}
	});
	
	//修改功能
	$("#update").on("click",function(){
		var catalogNum = $("#catalogNum").val();
		var catalogId = $('#catalogId').val();
		var libNum  = $("#libNum").val();
		var data = new Object();
		var  checks = $('input:checkbox[name=id]:checked');
		var catalogNum = $("#catalogNum").val();
		if(catalogNum !=""){
			if(checks.length == 1) {
				var id = checks[0].value;
				$.post(
					"/iamp/resource/judgeExist.do",
					{
						"id":id,
						"libNum":libNum
					},function(datas){
						if(datas.msg != "success"){
							//资源可能被删除了；
							data.console='该资源可能已经被删除了,请刷新完重试。';
							warning(data);
						}else{
				 			var title ="修改信息资源";
				 			var url ="/iamp/resource/showDetailView.do?id="+id+"&catalogNum="+catalogNum+"&libNum="+libNum+'&catalogId='+catalogId;
				 			window.parent.detailWin(title,url);
						}
					},"json"
				);
			}else if(checks.length == 0){
				data.console = "请选择数据!";
				warning(data);
			}else{
				data.console = "请选择一条数据!";
				warning(data);
			}
		}else{
			var data = new Object();
			data.console = "请选择节点!";
			warning(data);
		}
	});
	
	//删除功能
	$("#del").on("click",function(){
		var layer = window.layer;
		var libNum  = $("#libNum").val();
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
							"ids" : ids,
							"libNum":libNum
						},
						function(datas){
							var data = new Object();
							if(datas.result == 'success'){
								data.console='删除成功！';
								warning(data);
							}else{
								data.console='删除失败！该资源可能已经被删除了,请刷新完重试。';
								warning(data);
							}	
						},"json"
					);
		  }
		})
	});
});

	$("#search").on("click",function(){
		  $(".layui-form").submit();
	});
	
	//日期排序
	$("#orderByDate").on("click",function(){
		var judge = $("input[name='orderByDate']").val();
		if(judge =="0"){
			$("input[name='orderByDate']").val("1");
		}else{
			$("input[name='orderByDate']").val("0");
		}
		$("input[name='orderByTitle']").val("");
		$("#resourceForm").submit();
	});
	
	//标题排序
	$("#orderByTitle").on("click",function(){
		var judge = $("input[name='orderByTitle']").val();
		if(judge =="0"){
			$("input[name='orderByTitle']").val("1");
		}else{
			$("input[name='orderByTitle']").val("0");
		}
		$("input[name='orderByDate']").val("");
		$("#resourceForm").submit();
	});
	
	//属性弹出框
	$("#searchAttr").on("click",function(){
		var url = "/iamp/resource/searchAttr";
		window.parent.detailWins("筛选属性字段",url,"1000px","350px");
	});
	
	function getAttrVal(data){
		$("#searchAttr").val(data);
	}

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
	    			parent.reload();
	    		}
	    		window.location.reload();
	      }
	    });
	}
	
	function refresh(){
		location.href = mainUrl;
	}
	
	//预防没有节点时新建
	function resetPoint(){
		$("#catalogNum").val("");
		$("#catalogId").val("");
	}
	
	layui.use(['form','element','layer','laypage'], function(){
		var element = layui.element();
		var form = layui.form();
		var layer = layui.layer;
		window.element = element;
		window.form = form;
		window.layer = layer;
		  var laypage = layui.laypage; 
		  laypage({
		    cont: 'page'
		    ,pages: '${pages}' //总页数
		    ,groups: 5 //连续显示分页数
		    ,curr : '${currentPage}'
		    ,jump: function(obj, first){
		        if(!first){
		          var catalogNum = $("#catalogNum").val();
		          var catalogId = $('#catalogId').val();
		          if(typeof(catalogNum)=='undefined'){
		        	  catalogNum="";
		          }
		          var libNum  = $("#libNum").val();
		          if(typeof(libNum)=='undefined'){
		        	  libNum="";
		          }
		  			var judge = $("input[name='orderByDate']").val();
		          location.href='/iamp/resource/list.do?pageNo='+obj.curr+'&catalogNum='+catalogNum+'&libNum='+libNum+'&catalogId='+catalogId+'&orderByDate='+judge;
		        }
		      }
		  }); 
	});
	
	$("#setBelongDomain").on("click",function(){
	     var selectList = $("#resourceTable").find('input:checkbox[name="id"]:checked');
	     var libNum  = $("#libNum").val();
	     if(selectList.length > 0){
	     	var resourceIds = "";
	     	var resourceTitles = "";
		     var validTimes = "";
			 selectList.each(function(){
				 var resourceId = $(this).val();
				 var resourceTitle = $(this).closest("td").next().html();
				 var validTime = $(this).closest("td").next().next().next().find("input[class='validTime']")[0].value;
				 if(resourceIds){
				 	resourceIds += ";";
				 }
				 resourceIds += resourceId;
				 if(resourceTitles){
					 resourceTitles += ";";
				 }
				 resourceTitles += resourceTitle;
				 if(validTimes){
					 if(validTime == ""){
						 validTime ="forever";//默认永久
					 }
					 validTimes += ";";
				 }
				 if(validTime == ""){
					 validTime ="forever";//默认永久
				 }
				 validTimes += validTime;
			 });
			layer.open({
			  type: 2,
			  title: '批量设置归属领域',
	  		  shade: false,
	          area: ['600px', '220px'],
			  content: '/iamp/main/knowledgeLib/resourceLib/selectBelongDomain.jsp',
			  btnAlign: 'c',
			  btn: ['确定', '取消'],
			  yes: function(index, layero){
			     var loadingEleId="logdingView";
			     var body = layer.getChildFrame('body', index);
			     var iframeHtml = layer.getChildFrame('body');
			     var belongName = iframeHtml.find("#form").find("#belongDomain").val();
			     if(belongName){
			     	$.ajax({
			     		url : "/iamp/resource/updateBelongDomain",
			     		type : "post",
			     		data : {"belongName":belongName,"resourceIds":resourceIds,"libNum":libNum,"resourceTitles":resourceTitles,"validTimes":validTimes},
			     		dataType : "text",
			     		success : function(data){
			     			if(data == '0' || data == 0){
	                    		layer.msg('修改失败,请检查操作是否正确!', {
								  icon: 1,
								  time: 2000 //2秒关闭（如果不配置，默认是3秒）
								}, function(){
								  layer.close(index);
								  window.location.reload();
								});
	                    	}else{
	                    		  layer.msg('修改成功,共修改'+data+'个!', {
								  icon: 1,
								  time: 2000 //2秒关闭（如果不配置，默认是3秒）
								}, function(){
								  layer.close(index);
								  window.location.reload();
								});
	                    	}
			     		}
			     	});
		     		
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
	     }else{
	     	layer.open({
			  content: '请选择要修改的数据！'
			});
	     }
	     
	});
</script>
</html>