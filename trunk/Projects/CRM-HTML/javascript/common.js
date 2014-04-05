// JavaScript Document
	$(function(){
		$("#commonHeader").load("./commonHeader.html");//ヘッダー読み込み
	});
	$(document).ready(function(){//コピーライト表示
		var copyYear = new Date();
		$('p#copyright,p#copyright2').text(
			'©'+
			copyYear.getFullYear() +
			' Intelligence ,Ltd. '
		);
	});

function PrintPreview()
{
    if(window.ActiveXObject == null || document.body.insertAdjacentHTML == null) return;
    var sWebBrowserCode = '<object width="0" height="0" classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>';
    document.body.insertAdjacentHTML('beforeEnd', sWebBrowserCode);
    var objWebBrowser = document.body.lastChild;
    if(objWebBrowser == null) return;
    objWebBrowser.ExecWB(7, 1);
    document.body.removeChild(objWebBrowser);
}
