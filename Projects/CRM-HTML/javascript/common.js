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

/**  2014.4.17 t.miyagi add print_preview  **/
function PrintPreview() {

	if(window.ActiveXObject == null || document.body.insertAdjacentHTML == null) return;
	var sWebBrowserCode = '<object width="0" height="0" classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>';
	document.body.insertAdjacentHTML('beforeEnd', sWebBrowserCode);
	var objWebBrowser = document.body.lastChild;
	if(objWebBrowser == null) return;
	objWebBrowser.ExecWB(7, 1);
	document.body.removeChild(objWebBrowser);

}
/**  2014.4.17 t.miyagi add print_preview  **/

/****************** Vietnam team added for transition from C100 to C107 start********************************/
//Flag to get the information from parent screen that [open_search] area is opened before going to child screen or not
var open_search = "";
//Parent screen Id
var parentScreenId = "";

//This function to open a screen from another
function fncOpen(transitionScreenId, currentScreenId) {
	var url = transitionScreenId + ".html";
	if (open_search != null && open_search != "") {
		url = url + "#" + open_search;
	}
	url = url + "#" + currentScreenId;
	window.open(url, "_self");
}

//Changing the URL from the current screen to support when backing from [Back] button in browser
function fncChangeURLForBack() {
	if (open_search != null && open_search != "") {
		if(window.location.href.indexOf("#" + open_search) == -1) {	
			location.href = window.location.href + "#" + open_search;		
		}
	}
	return true;
}

function fncOnLoadForChildScreen() {
	var hash = location.hash;
	if (hash.indexOf('open_search') != -1) {
		open_search = "open_search";
	}
	if (hash.lastIndexOf('#') != -1) {
		parentScreenId = hash.substr(hash.lastIndexOf('#') + 1, 4);
	}
}

function fncCancel() {
	var url = parentScreenId + ".html";
	if (open_search != null && open_search != "") {
		url = url + "#" + open_search;
	}
	window.open(url, "_self");
}
/****************** Vietnam team added for transition from C100 to C107 end********************************/