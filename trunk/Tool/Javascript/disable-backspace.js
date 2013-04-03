//Disable Backspace key on reandonly fields (textfield, textarea) 
//to prevent triggering back button (history.back()) on browsers

//========================================
    // Disable Backspace key in IE and Firefox
    //========================================
    // Trap Backspace(8) except on text/textareas (unless they are read only)

    document.onkeypress = function(event) {
        if (typeof window.event != 'undefined') { // ie
            event = window.event;
            event.target = event.srcElement; // make ie confirm to standards !!
        }
        var kc = event.keyCode;
        var tt = event.target.type;
        if ((kc == 8)) {
            if (tt == 'text' || tt == 'textarea') {
                var readOnly = event.target.attributes.getNamedItem('readOnly');
                if (readOnly != null) {
                    if (readOnly.value == 'true')
                        return false;
                    else
                        return true;
                }
                else
                    return true;
            }
            else
                return false;
        }
        else
            return true;
    }

    if (typeof window.event != 'undefined') { // ie
        document.onkeydown = document.onkeypress; // Trap bksp in ie.
    }