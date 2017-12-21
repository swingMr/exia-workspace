<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


	<title>京华智慧化应用支撑平台—接口调试工具</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="Content-Type" content="text/html; charset=GBK">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">	
	 <link rel="stylesheet" href="/ExIAServer/js/layui-v2/css/layui.css"  media="all">
 	<link rel="stylesheet" href="/ExIAServer/js/layui-v2/css/layui.mobile.css"  media="all">
	<script type="text/javascript" src="/ExIAServer/js/jquery-1.7.2.min.js"></script>
	<style type='text/css'>
		.layui-elem-quote{border-color:#199ED8;}
		.layui-field-title{margin:0px;padding:10px;border:1px solid black;background:black;font-size:20px;color:#FFF;}
		.sys-title {display: inline-block;line-height: 60px;color: #fff;font-size: 24px;letter-spacing: 6px;cursor: pointer;}
		.span_0{background:red;}
		.span_1{background:yellow;}
		.span_2{background:blur;}
	</style>
	
</head>
<body>
<div class="layui-layout layui-layout-admin">
	<div class="layui-header">
		<!-- 头部区域（可配合layui已有的水平导航） -->
		<span class="sys-title" style="margin-left:10px;">  智慧化应用支撑平台</span>
		<ul class="layui-nav" pc="" style="float:right">
			<li class="layui-nav-item">
		        <a href="/ExIAServer/docs/InterfaceDoc.jsp">文档</a>
			</li>
			<li class="layui-nav-item layui-this">
			  <a href="#" target="_self">调试工具</a>
			</li>
			<span class="layui-nav-bar" style="left: 54px; top: 55px; width: 0px; opacity: 0;"></span>
		</ul>
	</div>
	
	<div style="margin-left:15px; margin-top:10px;">
		<blockquote class="layui-elem-quote">
		此工具旨在帮助开发者检测调用【京华智慧化应用支撑平台API】时发送的请求参数是否正确，提交相关信息后可获得服务器的验证结果
		</blockquote>
		
		<blockquote class="layui-elem-quote">
		使用说明：<br/>
  		（1）选择合适的接口。<br/>
  		（2）系统会生成该接口的参数表，您可以直接在文本框内填入对应的参数值。（红色星号表示该字段必填）<br/>
  		（3）点击检查问题按钮，即可得到相应的调试信息。<br/>
		</blockquote>

		<fieldset class="layui-elem-field">
			<legend style="font-size: 16px">调试工具</legend>
			<table class="layui-table" lay-skin="nob">
				<colgroup>
					<col width="100">
					<col width="350">
					<col width="100">
					<col>
				</colgroup>
				<tbody>
					<tr>
						<td colspan="4">
							<button class="layui-btn layui-btn-small layui-btn-normal" 
								lay-submit="" lay-filter="submit" id="btnSubmit">执  行</button>
							<span>执行时间：<span id="spanRunTime">0</span>ms</span>
						</td>
					</tr>
					<tr>
						<td align="right" valign="middle">接口类型：</td>
						<td>
							<form class="layui-form layui-form-pane" action="">
								<select id="selInterfaceTypes" lay-filter="interfaceTypes">
									<option value="">--选择接口类型--</option>
								</select>
							</form>
						</td>
						<td align="right" valign="middle">接口方法：</td>
						<td>
							<form class="layui-form layui-form-pane" action="">
								<select id="selInterfaces" lay-filter="interfaces">
									<option value="">--选择接口方法--</option>
								</select>
							</form>
						</td>
					</tr>
					<tr>
						<td align="right" valign="middle">接口URL：</td>
						<td colspan="3" id="tdURL">--</td>
					</tr>
					<tr>
						<td align="right" valign="top" style="padding-top:20px;">接口参数：</td>
						<td colspan="3">
							<form id="frmInvoke" action="">
							<table class="layui-table">
								<colgroup>
									<col width="100">
									<col width="100">
									<col>
								</colgroup>
								<thead>
									<tr>
										<th>参数名</th>
										<th>数据类型</th>
										<th>参数值</th>
									</tr>
								</thead>
								<tbody id="tblParameters"></tbody>
							</table>
							</form>
						</td>
					</tr>
					<tr>
						<td align="right" valign="top" style="padding-top:20px;">返回结果：</td>
						<td valign="top" colspan="3"><pre id="codeViewer" class="layui-code"></pre></td>
					</tr>
				</tbody>
			</table>
		</fieldset>
	</div>
</div>
</body>
<script type="text/javascript" src="/ExIAServer/js/layui-v2/layui.js"></script>
<script type="text/javascript" src="/ExIAServer/js/interfaces.js#123"></script>
<script type="text/javascript">
	var layer;
	var form;
	var formatJson = function(json, options) {
		var reg = null,
			formatted = '',
			pad = 0,
			PADDING = '    '; // one can also use '\t' or a different number of spaces
	 
		// optional settings
		options = options || {};
		// remove newline where '{' or '[' follows ':'
		options.newlineAfterColonIfBeforeBraceOrBracket = (options.newlineAfterColonIfBeforeBraceOrBracket === true) ? true : false;
		// use a space after a colon
		options.spaceAfterColon = (options.spaceAfterColon === false) ? false : true;
	 
		// begin formatting...
		if (typeof json !== 'string') {
			// make sure we start with the JSON as a string
			json = JSON.stringify(json);
		} else {
			// is already a string, so parse and re-stringify in order to remove extra whitespace
			json = JSON.parse(json);
			json = JSON.stringify(json);
		}
	 
		// add newline before and after curly braces
		reg = /([\{\}])/g;
		json = json.replace(reg, '\r\n$1\r\n');
	 
		// add newline before and after square brackets
		reg = /([\[\]])/g;
		json = json.replace(reg, '\r\n$1\r\n');
	 
		// add newline after comma
		reg = /(\,)/g;
		json = json.replace(reg, '$1\r\n');
	 
		// remove multiple newlines
		reg = /(\r\n\r\n)/g;
		json = json.replace(reg, '\r\n');
	 
		// remove newlines before commas
		reg = /\r\n\,/g;
		json = json.replace(reg, ',');
	 
		// optional formatting...
		if (!options.newlineAfterColonIfBeforeBraceOrBracket) {			
			reg = /\:\r\n\{/g;
			json = json.replace(reg, ':{');
			reg = /\:\r\n\[/g;
			json = json.replace(reg, ':[');
		}
		if (options.spaceAfterColon) {			
			reg = /\:/g;
			json = json.replace(reg, ':');
		}
	 
		$.each(json.split('\r\n'), function(index, node) {
			var i = 0, indent = 0, padding = '';
			if (node.match(/\{$/) || node.match(/\[$/)) {
				indent = 1;
			} else if (node.match(/\}/) || node.match(/\]/)) {
				if (pad !== 0)pad -= 1;
			} else {
				indent = 0;
			}
			
			for (i = 0; i < pad; i++) {
				padding += PADDING;
			}
			
			console.log(pad);
			formatted += padding + node + '\r\n';
			pad += indent;
		});
		console.log(formatted);
		return formatted;
	};


	var currentURL = null;
	$(document).ready(function(){
		/* 构建接口类型列表 */
		for(var i = 0 ; i < INTERFACE_TYPES.length; i++){
			var interfaceType = INTERFACE_TYPES[i];
			var description = interfaceType['description'];
			var type = interfaceType['type'];
			if(interfaceType['children'] && interfaceType['children'].length > 0){
				var optgroup = $('<optgroup label="' + description +'"></optgroup>');
				var children = interfaceType['children'];
				for(var j = 0 ; j < children.length; j++){
					var childInterfaceType = children[j];
					var childDescription = childInterfaceType['description'];
					var childType = childInterfaceType['type'];
					optgroup.append('<option value="'+ childType + '">' + childDescription + '</option>');
				}
				$('#selInterfaceTypes').append(optgroup);
			}else{
				$('#selInterfaceTypes').append('<option value="'+ type + '">' + description + '</option>');
			}
		}

		$('#btnSubmit').bind('click', function(){
			//console.log($('#frmInvoke').serialize());
			var url = currentURL;
			$('#tdURL').find('input').each(function(){
				var title = $(this).attr('title');
				url = url.replace('{' +title+ '}', $(this).val());
			});

			var startTimestamp = new Date().getTime();;
			
			layer.load();

			var ajaxObject = {
                type: "POST",
                dataType: "json",
                url: '/ExIAServer' + url, 
                async: true ,
                cache: false,
                success: function (result) {
                	var resultJsonStr = formatJson(result);
              		$('#codeViewer').html(resultJsonStr);
              		layui.code(); //引用code方法    
              		layer.closeAll('loading');
					var endTimestamp = new Date().getTime(); 
					$('#spanRunTime').html((endTimestamp - startTimestamp));
                },
                error : function(){
                	layer.closeAll('loading');
                	window.open('/ExIAServer' + url);
                	var endTimestamp = new Date().getTime(); 
                	$('#spanRunTime').html((endTimestamp - startTimestamp));
				}
			};
			var data = $('#frmInvoke').serialize();
			var files = $('#frmInvoke').find('input[type="file"]');
			if(files.length>0){
				 data = new FormData();
				files.each(function(){
					var file = $(this);
					var attrName = file.attr('name');
					data.append(attrName,this.files[0]);
				});
				var inputs = $('#frmInvoke').find('input[type="text"]');
				inputs.each(function(){
					var input = $(this);
					var attrName = input.attr('name');
					data.append(attrName,input.val());
				});
				ajaxObject.contentType=false;  
				ajaxObject.processData=false;
			}
			ajaxObject.data = data;
			$.ajax(ajaxObject);
		});
		
		layui.use(['form', 'layedit', 'laydate', 'code', 'upload'], function(){ //加载code模块
			layui.code(); //引用code方法
			form = layui.form;
			form.on('select(interfaceTypes)', function(data){
				currentURL = null;
				$('#selInterfaces').empty();
				$('#tblParameters').html('');
				$('#codeViewer').html('');
				$('#tdURL').html('--');
          		layui.code(); //引用code方法    
				$('#selInterfaces').append('<option value="">--选择接口方法--</option>');
				if(INTERFACES[data.value]){
					/* 构建方法列表 */
					var interfaces = INTERFACES[data.value];
					for(var i = 0 ; i < interfaces.length ; i++){
						var inf = interfaces[i];
						var description = inf['description'];
						var url = inf['url'];
						$('#selInterfaces').append('<option value="'+ data.value + ":" + i + '">' 
							+ description + '：' + url + '</option>');
					}
				}
				form.render('select');
			});
			
			form.on('select(interfaces)', function(data){
				$('#tblParameters').html('');
				$('#codeViewer').html('');
          		layui.code(); //引用code方法    
				var value = data.value;
				var type = value.substring(0, value.indexOf(':'));
				var index = value.substring(value.indexOf(':')+1);
				if(!INTERFACES[type] || !INTERFACES[type][index])return;
				var inf = INTERFACES[type][index];
				var arguments = inf['arguments'];
				currentURL = inf['url'];

				var html = currentURL.replace(/\{(.+?)\}/g, function(match){
					var replaceStr = match.toString();
					replaceStr = replaceStr.substring(0,replaceStr.length-1);
					replaceStr = replaceStr.substring(1);
					return '<input class="layui-input" style="width:100px;display:inline;" type="text" title="' + replaceStr + '" />';
				});
				$('#tdURL').html(html);
				
				/* 构建参数表格 */
				for(var i = 0 ; i < arguments.length ; i++){
					var argument = arguments[i];
					var html = '<tr>';
					html += '<td valign="middle">' + ((argument['notNull']) ? '<font color="red">*</font>' : '' ) +argument['name']+'</td>';
					html += '<td valign="middle">' + argument['type']+'</td>';
					html += '<td>';
					var type = $.trim(argument['type']).toLowerCase();
					if(type=='int'){
						html += '<input type="text" name="' + argument['name'] + '" class="layui-input"';
						html += 'onkeyup="this.value=this.value.replace(/[^0-9.]/g,\'\');'
							 +'if(this.value.indexOf(\'.\')==0)this.value = this.value.substring(1);'
							 +'if(this.value.indexOf(\'.\')!=this.value.lastIndexOf(\'.\'))this.value=this.value.substring(0, this.value.length-1);'
							 +'var digits=6;if(this.value.indexOf(\'.\')!=-1 && this.value.length > this.value.indexOf(\'.\')+digits+1)'
							 +'this.value = this.value.substring(0, this.value.indexOf(\'.\')+digits+1);" />';
					}else if(type=='date'){
						html += '<input type="text" name="' + argument['name'] + '" lay-verify="date" '
							 +'placeholder="yyyy-mm-dd" autocomplete="off" class="layui-input" onclick="layui.laydate({elem: this})">';
					}else if(type=='string[]'){
						html += '<button class="layui-btn layui-btn-mini layui-btn-normal" id="btnAddItem">+</button>';
						html += '<button class="layui-btn layui-btn-mini layui-btn-danger" id="btnDelItem" style="margin:0px;">-</button><br/>';
						html += '<input type="text" name="' + argument['name'] + '" class="layui-input"/>';
					}else if(type=='boolean'){
						html += '<input type="checkbox" name="' + argument['name'] + '" value="true" />';
					}else if(type=='file'){
						html += '<input type="file" name="' + argument['name'] + '" />'; 
					}else{
						html += '<input type="text" name="' + argument['name'] + '" class="layui-input"/>';
					}
					html += '</td></tr>';
					$('#tblParameters').append(html);
				}
			});

			$('#btnAddItem').live('click', function(){
				var parent = $(this).parent();
				var input = $(parent.find('input')[0]);
				parent.append('<input type="text" name="' + input.attr('name') + '" class="layui-input"/>');
				return false;
			});

			$('#btnDelItem').live('click', function(){
				var parent = $(this).parent();
				if(parent.find('input').length > 1){
					var lastInput = parent.find('input')[parent.find('input').length-1];
					$(lastInput).remove();
				}
				return false;
			});
		});
		if(window.location.href.split('?')[1]){
			getParam();
		}
	});

	function getParam(){
		var param = window.location.href.split('?')[1];
		var typeVals = param.split('=')[1]; 
		var typeVal = typeVals.split('&')[0]; 
		var typeNum = param.split('=')[2];
		$('#selInterfaceTypes').val(typeVal);
		
		$('#selInterfaces').empty();
		$('#tblParameters').html('');
		$('#codeViewer').html('');
		$('#tdURL').html('--');
		$('#selInterfaces').append('<option value="">--选择接口方法--</option>');
		if(INTERFACES[typeVal]){
			var interfaces = INTERFACES[typeVal];
			for(var i = 0 ; i < interfaces.length ; i++){
				var inf = interfaces[i];
				var description = inf['description'];
				var url = inf['url'];
				$('#selInterfaces').append('<option value="'+ typeVal + ":" + i + '">' 
					+ description + '：' + url + '</option>');
			}
		}
		$('#selInterfaces').val(typeVal+':'+typeNum); 

		$('#tblParameters').html('');
		$('#codeViewer').html('');
		var value = typeVal+':'+typeNum;
		var type = value.substring(0, value.indexOf(':'));
		var index = value.substring(value.indexOf(':')+1);
		if(!INTERFACES[type] || !INTERFACES[type][index])return;
		var inf = INTERFACES[type][index];
		var arguments = inf['arguments'];
		currentURL = inf['url'];

		var html = currentURL.replace(/\{(.+?)\}/g, function(match){
			var replaceStr = match.toString();
			replaceStr = replaceStr.substring(0,replaceStr.length-1);
			replaceStr = replaceStr.substring(1);
			return '<input class="layui-input" style="width:100px;display:inline;" type="text" title="' + replaceStr + '" />';
		});
		$('#tdURL').html(html);
		
		/* 构建参数表格 */
		for(var i = 0 ; i < arguments.length ; i++){
			var argument = arguments[i];
			var html = '<tr>';
			html += '<td valign="middle">' + ((argument['notNull']) ? '<font color="red">*</font>' : '' ) +argument['name']+'</td>';
			html += '<td valign="middle">' + argument['type']+'</td>';
			html += '<td>';
			var type = $.trim(argument['type']).toLowerCase();
			if(type=='int'){
				html += '<input type="text" name="' + argument['name'] + '" class="layui-input"';
				html += 'onkeyup="this.value=this.value.replace(/[^0-9.]/g,\'\');'
					 +'if(this.value.indexOf(\'.\')==0)this.value = this.value.substring(1);'
					 +'if(this.value.indexOf(\'.\')!=this.value.lastIndexOf(\'.\'))this.value=this.value.substring(0, this.value.length-1);'
					 +'var digits=6;if(this.value.indexOf(\'.\')!=-1 && this.value.length > this.value.indexOf(\'.\')+digits+1)'
					 +'this.value = this.value.substring(0, this.value.indexOf(\'.\')+digits+1);" />';
			}else if(type=='date'){
				html += '<input type="text" name="' + argument['name'] + '" lay-verify="date" '
					 +'placeholder="yyyy-mm-dd" autocomplete="off" class="layui-input" onclick="layui.laydate({elem: this})">';
			}else if(type=='string[]'){
				html += '<button class="layui-btn layui-btn-mini layui-btn-normal" id="btnAddItem">+</button>';
				html += '<button class="layui-btn layui-btn-mini layui-btn-danger" id="btnDelItem" style="margin:0px;">-</button><br/>';
				html += '<input type="text" name="' + argument['name'] + '" class="layui-input"/>';
			}else if(type=='boolean'){
				html += '<input type="checkbox" name="' + argument['name'] + '" value="true" />';
			}else if(type=='file'){
				html += '<input type="file" name="' + argument['name'] + '" />'; 
			}else{
				html += '<input type="text" name="' + argument['name'] + '" class="layui-input"/>';
			}
			html += '</td></tr>';
			$('#tblParameters').append(html);
		}
	}
</script>
</html>