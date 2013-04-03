function handleEnterKey(buttonId) {
	document.onkeypress = function(evt) { 
	    var event = evt || window.event; 
	    if(event.keyCode == 13) {
		document.getElementById(buttonId).click();
	    }
	}; 
}

