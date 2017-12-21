<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>上传文件</title>
<script language="JavaScript" type="text/javascript" src="/iamp/js/jquery-1.7.2.min.js"></script>
<script language="JavaScript" type="text/javascript" src="/iamp/js/layui/layui.js" ></script>
 <script src="/iamp/js/swfupload/swfupload.js" type="text/javascript"></script>  
 <script src="/iamp/js/swfupload/swfupload.queue.js" type="text/javascript"></script>  
<link href="/iamp/css/fonts/iconfont.css" rel="stylesheet" type="text/css">
<link href="/iamp/js/layui/css/layui.css" rel="stylesheet" type="text/css" />  
<link href="/iamp/js/layui/css/style.css" rel="stylesheet" type="text/css"> 
</head>
<%
	String libNum = request.getParameter("libNum");
	String catalogNum = request.getParameter("catalogNum");
	String catalogId = request.getParameter("catalogId");
 %>
<body>
<div style="height: 410px;overflow:auto">
	<table class="layui-table" lay-even="" lay-skin="row">
	  <colgroup>
	    <col width="250">
	    <col width="390">
	    <col width="100">
	    <col>
	  </colgroup>
	  <thead>
	    <tr>
	      <th>文件名</th>
	      <th>上传进度</th>
	      <th>文件大小</th>
	      <th></th>
	    </tr> 
	  </thead>
	  <tbody id="fileTable">
	   
	  </tbody>
	</table>		
</div>
  <div style="height:40px;margin-top:5px;">
    <div style="border-radius: 2px;background-color: #18A594;height:38px;width:80px;float:left;margin-left:280px;"><button class="layui-btn" id="element_id">选择文件</button></div>
    <button class="layui-btn layui-btn-normal" id="uploadBtn" style="float:left;margin-left:10px;" onclick="start_upload();">上传</button>
    <button class="layui-btn layui-btn-danger" style="float:left;" onclick="closeThis();">关闭</button>  
  </div>  

<div class="clear"></div>
<script type="text/javascript">  

var layer;
var element;
var libNum = "<%= libNum%>";
var catalogNum = "<%= catalogNum%>";
var catalogId = "<%= catalogId%>";
//一般直接写在一个js文件中
layui.use(['element','layer'], function(){
  element = layui.element();
  layer = layui.layer;
});

var swfu;

$(function() {
    var settings_object = {//定义参数配置对象
        	upload_url : "/iamp/resource/upload.do",
            flash_url : "/iamp/js/swfupload/swfupload.swf",
            file_post_name : "Filedata",
            use_query_string : false,
            requeue_on_error : false,
            http_success : [201, 202],
            assume_success_timeout : 0,
//             file_types : "*.jpg;*.gif",
//             file_types_description: "Web Image Files",
            file_size_limit : ""+30*1024,
            file_upload_limit : 10,
            file_queue_limit : 10,
 
            debug : false,
     
            prevent_swf_caching : false,
            preserve_relative_urls : false,
     
            button_placeholder_id : "element_id",
            button_width : 100,
            button_height : 38, 
            button_text : '<span class="btn-txt">选择文件</span>',
            button_text_style : '.btn-txt{color: #FFFFFF;font-size:14px;font-family:"Microsoft YaHei";}',
            button_text_left_padding : 10,
            button_text_top_padding : 8,   
            button_action : SWFUpload.BUTTON_ACTION.SELECT_FILES,
            button_disabled : false,
            button_cursor : SWFUpload.CURSOR.HAND,
            button_window_mode : SWFUpload.WINDOW_MODE.TRANSPARENT,
     
            swfupload_loaded_handler : swfupload_loaded_function,
//             file_dialog_start_handler : file_dialog_start_function,
            file_queued_handler : file_queued_function,
            file_queue_error_handler : file_queue_error_function,
//             file_dialog_complete_handler : file_dialog_complete_function,
            upload_start_handler : upload_start_function,
            upload_progress_handler : upload_progress_function,
            upload_error_handler : upload_error_function,
            upload_success_handler : upload_success_function,
//             upload_complete_handler : upload_complete_function,
//             debug_handler : debug_function,
    };
     
    swfu = new SWFUpload(settings_object);//实例化一个SWFUpload，传入参数配置对象
})
var load;
function start_upload() {
	var tableHtml = $("#fileTable").find("tr");
	if(tableHtml.length > 0){
		swfu.startUpload();
	}else{
		layer.open({
		  title: '提示'
		  ,content: '请上传文件！'
		});     
	}
	
}

function upload_start_function(file) {
	var fileName = encodeURIComponent(file.name);
	var postobj = {"libNum": '${libNum}',"resourceId":'${resourceId}','fileName':fileName};
   	swfu.setPostParams(postobj);
}

function swfupload_loaded_function(){
	
}

function file_queued_function(file) {
	
	$('#fileTable').append('<tr id="'+file.id+'"><td>'+file.name+'</td><td><div class="layui-progress" lay-filter="'+file.id+'" lay-showpercent="true"><div class="layui-progress-bar" lay-percent="0%">'+
			'</div></div><td>'+file.size+'B</td><td><i class="layui-icon" style="cursor: pointer;" onclick="deleteThis(\''+file.id+'\',this)">&#xe640;</i></td></tr>');
	
}
function file_queue_error_function(file, error, message) {

	if(error==SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED) {
	 	layer.msg('选择文件不能超过10个！');
	} else if(error==SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT) {
		layer.msg('文件大小不能超过30M！');
	} else if(error==SWFUpload.QUEUE_ERROR.INVALID_FILETYPE) {
		layer.msg('不支持的文件类型！');
	}
	
}
function upload_progress_function(file, curBytes, totalBytes) {
	load = layer.msg('上传中，请稍后', {
		  icon: 16
		  ,shade: 0.5
		  ,time:0
	});
    var precent = parseInt((curBytes/totalBytes)*100);
    layui.element().progress(file.id, precent+'%');
}

function upload_success_function(file, data, response) {
	var result = data;
	result = eval('(' + result + ')');
	  for(var i=0; result.length>i; i++){
		  var info = result[i];
		  var fileInfo = info.fileInfo;
    	  parent.loadFileInfo(fileInfo);
	  }
	swfu.cancelUpload(file);
	var stats = swfu.getStats();
	var files_queued = stats.files_queued;
	if(files_queued>0) {
		swfu.startUpload();
	} else {
		var successful_uploads = stats.successful_uploads;
		var upload_errors = stats.upload_errors;
		if(upload_errors>0) {
			layer.close(load);
			layer.alert('成功上传文件'+successful_uploads+'个，失败'+upload_errors+'个', function(){
				parent.location.reload();
				closeThis();
			});  
		} else {
			layer.close(load);
			layer.alert('成功上传'+successful_uploads+'个文件', function(){
			    closeThis();
			});    
		}
	}
}

function deleteThis(fileId,tr) {
	var stats = swfu.getStats();
	swfu.cancelUpload(fileId);
	$(tr).parent().parent().remove();
}

function upload_error_function(file, error, message) {
	swfu.cancelUpload(file.id);
}

function closeThis() {
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	parent.layer.close(index);
}

</script>
</body>
</html>