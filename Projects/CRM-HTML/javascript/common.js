// JavaScript Document
$(function() {
	$("#commonHeader").load("./commonHeader.html");// ヘッダー読み込み
});
$(document).ready(
		function() {// コピーライト表示
			var copyYear = new Date();
			$('p#copyright,p#copyright2').text(
					'©' + copyYear.getFullYear() + ' Intelligence ,Ltd. ');
		});

function PrintPreview() {
	if (window.ActiveXObject == null
			|| document.body.insertAdjacentHTML == null)
		return;
	var sWebBrowserCode = '<object width="0" height="0" classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>';
	document.body.insertAdjacentHTML('beforeEnd', sWebBrowserCode);
	var objWebBrowser = document.body.lastChild;
	if (objWebBrowser == null)
		return;
	objWebBrowser.ExecWB(7, 1);
	document.body.removeChild(objWebBrowser);
}

/****************** Vietnam team added********************************/
function Delete(htmlPath) {
	var retVal = confirm("削除しますがよろしいですか？");
	if (retVal == true) {
		if(arguments.length == 1) {
			window.location.href = htmlPath;
		}
		return true;
	} else {
		if(arguments.length == 1) {
			window.location.href = htmlPath;
		}
		return false;
	}
}
function Regist(htmlPath) {
	var retVal = confirm("登録しますがよろしいですか？");
	if (retVal == true) {
		if(arguments.length == 1) {
			window.location.href = htmlPath;
		}
		return true;
	} else {
		if(arguments.length == 1) {
			window.location.href = htmlPath;
		}
		return false;
	}
}

function Preser(htmlPath) {
	var retVal = confirm("保存しますがよろしいですか？");
	if (retVal == true) {
		if(arguments.length == 1) {
			window.location.href = htmlPath;
		}
		return true;
	} else {
		if(arguments.length == 1) {
			window.location.href = htmlPath;
		}
		return false;
	}
}
/****************** End********************************/