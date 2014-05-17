/** Define/Customize some JS functions - Begin */

/** indexOf() */
if (!Array.indexOf) {
	Array.prototype.indexOf = function(obj, start) {
		for (var i = (start || 0); i < this.length; i++) {
			if (this[i] == obj) {
				return i;
			}
		}
		return -1;
	};
}
/** Define/Customize some JS functions - End */

// Remove heading and trailing spaces of a string
function trim(value) {
	if (value == null) {
		return null;
	}

	// Remove heading spaces
	value = value.replace(/^\s+/, '');

	// Remove trailing spaces
	value = value.replace(/\s+$/, '');
	return value;
}

function openPopup(url, width, height, windowName, options) {
	if (!width) {
		width = 800;
	}

	if (!height) {
		height = 600;
	}

	if (!windowName) {
		windowName = "_blank";
	}

	if (!options) {
		options = "fullscreen=yes, location=yes, menubar=yes, resizable=yes, scrollbars=yes, status=yes, titlebar=yes, toolbar=yes, top=o, left=0";
	}

	var winSize = ", width=" + width + ", height=" + height;
	var specs = options + winSize;

	var win = window.open(url, windowName, specs);
	if (win) {
		win.focus();
		win.moveTo(0, 0);
	}
}
