//��ȡ�����ҳ��ɼ��߶ȺͿ��
var _PageHeight = document.documentElement.clientHeight,
  _PageWidth = document.documentElement.clientWidth;
//����loading����붥�����󲿵ľ��루loading��Ŀ��Ϊ215px���߶�Ϊ61px��
var _LoadingTop = _PageHeight > 61 ? (_PageHeight - 61) / 2 : 0,
  _LoadingLeft = _PageWidth > 215 ? (_PageWidth - 215) / 2 : 0;
//��ҳ��δ�������֮ǰ��ʾ��loading Html�Զ�������
//var _LoadingHtml = '<div id="loadingDiv" style="position:absolute;left:0;width:100%;height:' + _PageHeight + 'px;top:0;background:rgba(0,0,0,0.55);opacity:0.8;filter:alpha(opacity=80);z-index:10000;"><div style="position: absolute; cursor1: wait; left: ' + _LoadingLeft + 'px; top:' + _LoadingTop + 'px; width: auto; height: 57px; line-height: 57px; padding-left: 50px; padding-right: 5px; background: #fff url(/ExKEGov/tools/style/image/loading.gif) no-repeat scroll 5px 10px; border: 2px solid #95B8E7; color: #696969; font-family:\'Microsoft YaHei\';">ҳ������У���ȴ�...</div></div>';
//����loadingЧ��
//document.write(_LoadingHtml);
//������ʾ
var loadingEleId="logdingView";
function showLoadingMsg(msg){
	removeLoadingMsg();
	var loadingHtml = '<div id="'+loadingEleId+'" style="position:absolute;left:0;width:100%;height:' + _PageHeight + 'px;top:0;background:rgba(0,0,0,0.55);opacity:0.8;filter:alpha(opacity=80);z-index:10000;"><div style="position: absolute; cursor1: wait; left: ' + _LoadingLeft + 'px; top:' + _LoadingTop + 'px; width: auto; height: 57px; line-height: 57px; padding-left: 50px; padding-right: 5px; background: #fff url(/iamp/image/loading.gif) no-repeat scroll 5px 10px; border: 2px solid #95B8E7; color: #696969; font-family:\'Microsoft YaHei\';">'+msg+'</div></div>';
	$(document.body).append(loadingHtml); 
}

function removeLoadingMsg(){
	//setTimeout(function(){
		try{
			$('#'+loadingEleId).remove();
		}catch(e){
			
		}
		//},200);
}



