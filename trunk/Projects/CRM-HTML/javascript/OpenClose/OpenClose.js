// JavaScript Document

var folder = ''
var image = ''
var hd = "img/minus.png"
var sw = "img/plus.png"

function showFolder(subobj, imgobj) {
	folder = document.all(subobj).style
	image = document.all(imgobj)
	if (folder.display == "none") {
		folder.display = ""
		image.src = hd
	} else {
		folder.display = "none"
		image.src = sw
	}
}
