<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>应用管理</title>
</head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="/iamp/js/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="/iamp/js/layui/css/global.css" media="all">
<!-- <link rel="stylesheet" href="/iamp/js/ztree/css/demo.css" type="text/css"> -->
<link rel="stylesheet" href="/iamp/js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="/iamp/js/ztree/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="/iamp/js/ztree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="/iamp/js/layui/layui.js"></script>
<style type="text/css">
	.btn{
		margin-top:10px;
		margin-left:10px;
	}
</style>
<body>
	<div class="layui-layout layui-layout-admin">
	<div class="layui-header" style="background: #ffffff">
	<form class="layui-form" id="form" action="/iamp/resworkitem/list.do" method="post">
		<div class="layui-input-block btn" style="float:left;position:relative;">
			<select lay-filter="workStatus" name="workStatus" style="width:60%">
				<option value=""></option>
			</select>
		</div>
			
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;" id="query">查询</span></div>
		<div style="float:right;position:relative;" class="btn"> 
			<label class="layui-form-label">标题:</label>
		    <div class="layui-input-block">
		      <input type="text" name="resTitle"  style="width:150px"  placeholder="请输入标题" autocomplete="off" class="layui-input" value='${resTitle }'>
		   	<input type="hidden" name="pageNo" value="${page.pageNum}">
		   	<input type="hidden" name="pageSize" value="${page.pageSize}">
		    </div>
    	</div>
    	<div style="float:right;position:relative;" class="btn"> 
			<label class="layui-form-label">领域:</label>
		    <div class="layui-input-block">
		      <input type="text" name="belongDomain"  style="width:150px"  placeholder="请输入领域" autocomplete="off" class="layui-input" value='${belongDomain}'>
		    </div>
    	</div>
	</form>
	</div>
	<div class="layui-side" style="background-color:#bc1010">
		<div class="layui-side-scroll" style="background-color:#ffffff">
			<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
			<ul id="treeDemo" class="ztree" style="height:100%">
			</ul>
			<!-- <iframe src="tree.jsp" style="height:100%"></iframe> -->
		</div>
	</div>
  	<div class="layui-body">
		<table lay-skin="nob" class="layui-table">
			<colgroup>
				<col width="50">
				<col>
				<col width="120">
				<col width="120">
		  	</colgroup>
			<thead>
				<tr>
					<th><input type="checkbox" id="checkAll" name="checkAll"></th>
					<th>标题</th><th>归属领域</th><th>创建时间</th>
				</tr> 
			</thead>
			<tbody id="bodyList">
			</tbody>
		</table>
		<center>
			<button class="layui-btn layui-btn-normal layui-btn-normal" lay-submit lay-filter="*" id="save">确定</button>
			<button class="layui-btn layui-btn-normal layui-btn-warm" lay-submit lay-filter="*" id="cancel">取消</button>
		</center>
		<div id="page" style="position:absolute;right:50px;bottom:0;"></div>
	</div>
</div>
</body>

<script type="text/javascript">
	var zTree;
	var layer;
		
	layui.use(['element','layer','form'], function(){
		var element = layui.element();
		layer = layui.layer;
		window.element = element;
	});
	
	var setting = {//设置setting 对象  
	    callback: {//回调函数  
	        onClick: function(event, treeId, treeNode){//点击事件的触发函数  
	            appendNode(treeNode);
	            getResources(treeNode,1);
	        },
	        onExpand:function(event, treeId, treeNode){
	        	appendNode(treeNode);
	        }  
	    }  
	};
	var laypage;
	layui.use(['layer','laypage','form','element'], function(){
		var form = layui.form();
		var element = layui.element();
		layer = layui.layer;
		window.layer = layer;
		laypage = layui.laypage; 

	});

	/**
	 * 转换子数据结构
	 * 
	 * @param parentPath 父路径
	 * @param children 子数据
	 */
	function transforChildrenStructure(parentPath, children) {
        $.each(children, function(index, child){
			child.name = child.text;
			delete child.text;
			child.isParent = child.hasChildren;
			delete child.hasChildren;
			child.id = child.type + "-" + child.id;
			child.parentPath = parentPath;
        });
	}

	function optionValue2Map(optionValue) {
		var optionValues = optionValue.split(";");
		var map = {};
		$.each(optionValues, function(index, value) {
			map[value.split(":")[0]] = value.split(":")[1];
		});
		return map;
	}

	function loadTree(libData) {
		$.ajax({
            type: "POST",
            url:'/iamp/resworkitem/searchChildren.do',
            data: libData,
            async: false,
            error: function(request) {
                alert("出现异常");
            },
            dataType:"json",
            success: function(children) {
                // 转换结构
                children = children.searchResult;
                transforChildrenStructure('资源库/' + libData.text, children);
                
            	zTree = $.fn.zTree.init($("#treeDemo"), setting, children);
            }
       	});
	}
	
	
	// 初始化获取wcm资源库站点
	$.ajax({
		type: "POST",
		url:'/iamp/resworkitem/getsources.do',
		data:{url: encodeURIComponent('http://<host>/wcm/资源库')},
		async: false,
        error: function(request) {
            alert("出现异常");
        },
        dataType:"json",
        success: function(data) {
            var sites = data.sites;
            $.each(sites, function(index, site){
            	var valueStr = "id:" + site.id + ";parentPath:" + site.parent + ";type:" + site.type;
            	$('select[name="workStatus"]')
            		.append('<option value=\'' + valueStr + '\' ' + (index == 0 ? 'selected':'') + '>' 
                    		+ site.text 
                    		+ '</option>');
            });
            // 默认选择加载第一个库下的节点
            var $firstNode = $('select[name="workStatus"]>option:selected');
            var libData = optionValue2Map($firstNode.val());
            libData.text = $firstNode.text();
            loadTree(libData);
        }
	});
	
	// 切换库重新加载树
	layui.use('form', function() {
		var form = layui.form();
		form.on('select(workStatus)', function(data) {
			var libData = optionValue2Map(data.value);
			libData.text = data.elem.options[data.elem.selectedIndex].text;
			loadTree(libData);
		});
	});
	
	function appendNode(treeNode) {
		if (treeNode.isParent) {
			if(treeNode.isLoaded == true) {
				return;
			} else {
				treeNode.isLoaded = true;
				var data = {};
				data.id = treeNode.id.split("-")[1];
				data.text = encodeURIComponent(treeNode.name);
				data.type = treeNode.type;
				data.parentPath = encodeURIComponent(treeNode.parentPath);
				$.ajax({
	               type: "POST",
	               url:'/iamp/resworkitem/searchChildren.do',
	               data:data,
	               async: false,
	               error: function(request) {
	                   alert("出现异常");
	               },
	               dataType:"json",
	               success: function(children) {
	            	// 转换结构
	                   children = children.searchResult;
	                   transforChildrenStructure(treeNode.parentPath + "/" + treeNode.name, children);
	                   
	                   zTree.addNodes(treeNode,children);
	               }
	           });
			}
		}
	}
	
	function getResources(treeNode,pageNo) {
		$('#checkAll').attr('checked',false); //清除全选
		if (!treeNode.isParent) {
			$('#bodyList').empty();
			var data = {};
			data.id = treeNode.id.split("-")[1];
			data.text = encodeURIComponent(treeNode.name);
			data.type = treeNode.type;
			data.parentPath = encodeURIComponent(treeNode.parentPath);
			data.pageNo = pageNo;
			data.pageSize = 5;
			$.ajax({
	            type: "POST",
	            url:'/iamp/resworkitem/searchChildren.do',
	            data: data,
	            async: false,
	            error: function(request) {
	                alert("出现异常");
	            },
	            dataType:"json",
	            success: function(json) {
		            if (json.searchResult) {
			            var docs = json.searchResult;
			            $.each(docs, function(index, doc) {
			            	var tr= $('<tr><td><input type="checkbox" name="doc_checkbox"></td>'
			            		+ '<td class="title">' + doc.text + '</td>'
			            		+ '<td>司法</td>'
			            		+ '<td class="createTime">' + doc.createTime + '</td>'
			            		+ '<td><input type="hidden" name="id" value="' + doc.id + '"></td>'
			            		+ '<td><input type="hidden" name="url" value="' + doc.url + '"></td>'
			            		+ '<td><input type="hidden" name="channelId" value="' + doc.channelId + '"></td></tr>');
							$('#bodyList').append(tr);
				        });
			            laypage({
		        			cont: 'page'//id
		        			,pages: Math.ceil(json.total/5)
		        			,groups: 5 //连续显示分页数
		        			,curr : pageNo
		        			,jump: function(obj, first){
		        				if(!first){
		        					layer.msg('第 '+ obj.curr +' 页');
		        					getResources(treeNode,obj.curr);
		        		        }
		        			}
		        		});
		            }
	            }
	        });
		}
	}
	
	//请求列表放在这里
	function loadResource(id) {
		
	}

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
	
	$('#save').click(function() {
		// 查找出被选中的文本资源
		var $trs = $('#bodyList').find("input[name='doc_checkbox']:checked").closest("tr");
		if(!$trs.size() > 0) {
			showMsg('提醒', '请选择文档');
			return;
		} else {
			var docs = [];
			var data = {};
			$.each($trs, function(index, tr) {
				var $tr = $(tr);
				var doc = {};
				doc.id = $tr.find('input[name="id"]').val();
				doc.channelId = $tr.find('input[name="channelId"]').val();
				doc.title = $tr.find('.title').text();
				doc.createTime = $tr.find('.createTime').text();
				doc.url = $tr.find('input[name="url"]').val();
				docs.push(doc);
			})
			data.docs = encodeURIComponent(JSON.stringify(docs));
			$.ajax({
                type: "POST",
                url: '/iamp/resworkitem/saveDocs.do',
                data: data,
                async: false,
                error: function(request) {
                    alert("出现异常");
                },
                dataType:"json",
                success: function(data) {
                	if(data.result) {
                		layer.open({
				          type: 1
				          ,title:'提醒'
				          ,content: '<div style="padding: 20px 100px;">'+data.msg+'</div>'
				          ,btn: '关闭'
				          ,btnAlign: 'c' //按钮居中
				          ,shade: 0 //不显示遮罩
				          ,yes: function(){
							var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				      		parent.layer.close(index);
				    		parentRefresh();
				          }
				        });	
                	} else {
                		showMsg('提醒',data.msg);
						return;
                	}
                }
            });
		}
	})
	
	function parentRefresh(){
	    var frameName = 'xxzycl';
		window.parent.loadIframe(frameName);
	}

	$('#checkAll').bind('change',function() {
		if($(this).attr('checked')) {
			$('input[name="doc_checkbox"]').attr('checked',true);
		} else {
			$('input[name="doc_checkbox"]').attr('checked',false);
		}
	})
</script>
</html>