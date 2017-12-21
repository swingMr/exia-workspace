<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>Ӧ�ù���</title>
</head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="/iamp/js/layui-v2/css/layui.css" media="all" />
<script type="text/javascript" src="/iamp/js/jquery-1.7.2.min.js"></script>
<style type="text/css">
	.btn{
		margin-top:10px;
		margin-left:10px;
	}
</style>
<body>
	<form class="layui-form" id="form" action="/iamp/app/list.do" method="post">
		<span class="layui-btn  layui-btn-normal btn layui-btn-small" id="add">�½�</span>
		<span class="layui-btn  layui-btn-warm btn layui-btn-small" id="import">����</span>
		<span class="layui-btn layui-btn-danger btn layui-btn-small" id="del">�޳�</span>
		<!-- <span class="layui-btn  layui-btn-warm btn layui-btn-small" id="update">����ά��</span> -->
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;" id="query">��ѯ</span></div>
    	<div style="float:right;position:relative;" class="btn"> 
			<label class="layui-form-label">�������飺</label>
		    <div class="layui-input-block">
			      <input id="appGroup" type="text" name="appGroup"  placeholder="" autocomplete="off" class="layui-input">  
		    </div> 
    	</div>
	</form>
 	<table class="layui-table" lay-skin="line">
	  <colgroup>
	  	<col width="50">
	    <col>
	    <col width="150">
	    <col width="150">
	    <col width="120">
	    <col width="120">
	    <col width="120">
	    <col width="120">
	    <col width="150">
	  </colgroup>
	  <thead>
	    <tr>
	     <th><input type="checkbox" id="checkAll" name="checkAll"></th>
	     <th>��Ա�˺�</th><th>��Ա����</th><th>����</th>
	     <th>QQ��</th><th>΢�ź�</th><th>�绰����</th>
	     <th>��������</th><th>��עʱ��</th>
	    </tr> 
	  </thead>
	  <tbody id="tbody">
	  </tbody>
	</table> 
	<div id="page" style="position:absolute;right:50px;"></div>
</body>

<script type="text/javascript" src="/iamp/js/layui-v2/layui.js"></script>
<script>
    var pages ;
    var pageNo ;
    var appGroup ;
    var laypage;
    var count;
		
	var layer;
	layui.use(['layer','laypage','form','element'], function(){
		var form = layui.form;
		var element = layui.element;
		layer = layui.layer;
		laypage = layui.laypage; 
		getMembers(1);
	});
	var appId = '${appId}';
	function getMembers(pageNo,appGroup){
		$('#checkAll').removeAttr('checked',''); //���ȫѡ
		var data = {};
		if(appGroup!=""){
			data.appGroup = appGroup;
		}
		data.pageNo = pageNo;
		$.ajax({
	        type: "POST",
	        url:'/iamp/app/members/'+appId,
	        data:data,
	        async: false,
	        error: function(request) {
	            alert("Connection error");
	        },
	        dataType : "json",
	        success: function(data) {
	            if(data){
	            	$('#tbody').empty();
	            	var tbody;
	            	var members = data.members;
	            	for(var i=0;i<members.length;i++){
	            		var json =  members[i] ;
						var tr ='<tr><td><input type="checkbox" appId="'+json.appId+'" memberId="'+json.memberId+'" name="memberId"></td><td>'+json.memberAccount+'</td><td>'+json.memberName+'</td><td>'+json.emailAddress
									+'</td><td>'+json.qqNum+'</td><td>'+json.wechatNum+'</td><td>'+json.phoneNum+'</td><td>'+json.appGroup+'</td><td>'+json.followTime+'</td></tr>'; 
						tbody+=tr;
	                }
	            	$('#tbody').append(tbody);
	            	pages = data.pages;
	            	pageNo = pageNo;
	            	count = data.count;
	            	if(laypage!=null){
		        		laypage.render({
						    elem: 'page' //ע�⣬����� test1 �� ID�����ü� # ��
						    ,count:count//�������� //�ӷ���˵õ�
						    ,limit:10
						    ,groups: 5
						    ,curr : pageNo
						    ,pages: pages
						    ,jump: function(obj, first){
						        if(!first){
						          layer.msg('�� '+ obj.curr +' ҳ');
						          getMembers(obj.curr,appGroup);
						        }
						      }
						  });
		            }
	        	            	
	            }
	        }
	    });
	}
	
	$('#checkAll').on('change',function() {
		if($(this).attr('checked') == 'checked') {
			$('input[name="memberId"]').attr('checked','');
		} else {
			$('input[name="memberId"]').removeAttr('checked','');
		}
	})
	
	$("#del").on("click",function(){
		var  checks = $('input:checkbox[name=memberId]:checked');
		if(checks.length > 0) {
			var layer = window.layer;
			layer.confirm('ȷ��ɾ����������?', {
					  btn: ['ȷ��', 'ȡ��'] 
					  ,btnAlign: 'c'
					  ,yes: function(index, layero){
						  		var appIds = "";
						  		var memberIds = "";
						  		for(var n=0;n<checks.length;n++){
									appIds+=checks.eq(n).attr('appId');
									appIds+=';';
									memberIds+=checks.eq(n).attr('memberId');
									memberIds+=';';
							  	}
								$.ajax({
						                type: "post",
						                url:'/iamp/app/memDel',
						                data:{"appIds":appIds,"memberIds":memberIds},// ���formid
						                async: false,
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
										        	  getMembers(pageNo,appGroup);
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

	$("#add").on("click",function(){
		var title="��ӻ�Ա";
		var url = "/iamp/member/create.do?appId="+appId;
		/* window.parent.detailWin(title,url); */
		var layer = window.layer;
		layer.open({
			title:title,
			maxmin: true,
			type: 2, 
			area: ['1000px', '580px'],
			content:url,
		  	success: function(layero, index){
			    var body = layer.getChildFrame('body', index);
			    var iframeWin = window[layero.find('iframe')[0]['name']]; //�õ�iframeҳ�Ĵ��ڶ���ִ��iframeҳ�ķ�����iframeWin.method();
				window.child = iframeWin;
			    }
		}); 

		/* var layer = window.layer;
		layer.open({
			title:'�½�Ӧ��',
			type: 2, 
			area: ['1000px', '580px'],
			content:"/iamp/app/create.do"
		});  */
	});

	//�����½��ɹ���ˢ��
	function refresh(){
		location.reload();
	}

	$("#query").on("click",function(){
		appGroup = $("#appGroup").val();
		getMembers(pageNo,appGroup);
	});

	$("#import").on("click",function(){
		var title="����û�";
		var url = "/iamp/app/memberList/"+appId;
		/* window.parent.detailWin(title,url); */
		var layer = window.layer;
		layer.open({
			title:title,
			maxmin: true,
			type: 2, 
			area: ['1000px', '480px'],
			content:url,
		  	success: function(layero, index){
			    var body = layer.getChildFrame('body', index);
			    var iframeWin = window[layero.find('iframe')[0]['name']]; //�õ�iframeҳ�Ĵ��ڶ���ִ��iframeҳ�ķ�����iframeWin.method();
				window.child = iframeWin;
			    }
		}); 
	});
	
</script>
</html>