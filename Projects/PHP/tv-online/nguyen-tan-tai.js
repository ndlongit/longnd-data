loadstatustext = '<b>&#272;ang T&#7843;i -- Loading</b><br><img src=\"images/wait.gif\">'; 
  function makePOSTAddRequest(url, parameters) { 
     http_request = false; 
     function ajaxObject() 
       { 
       if(window.XMLHttpRequest || window.ActiveXObject) 
          { 
            return (window.XMLHttpRequest)?new XMLHttpRequest():new ActiveXObject("Microsoft.XMLHTTP"); 
          } 
       return false; 
       } 
     http_request = ajaxObject() ;
     if (!http_request) { 
         alert('Cannot create XMLHTTP instance'); 
         return false; 
     } 
     http_request.onreadystatechange = alertAdd; 
     http_request.open('GET', url, true); 
     http_request.setRequestHeader("Content-type", "application/x-www-form-urlencoded"); 
     http_request.setRequestHeader("Content-length", parameters.length); 
     http_request.setRequestHeader("Connection", "close"); 
     http_request.send(parameters); 
   } 
   function alertAdd() { 
       document.getElementById('channels').innerHTML = loadstatustext; 
     if (http_request.readyState == 4) { 
         if (http_request.status == 200) { 
           //alert(http_request.responseText); 
           result = http_request.responseText; 
           document.getElementById('channels').innerHTML = '&nbsp;'; 
           document.getElementById('channels').innerHTML = result;            
         } else { 
           alert('Có L&#7895;i!'); 
         } 
     } 
   } 
function showtab(id) { 
     var poststr ="?tab="+id;
	 //alert(poststr);
     makePOSTAddRequest('show.php'+poststr, poststr); 
	 scroll('channels','400');
   }
function showlang(id) { 
     var poststr ="?lng="+id;
	 //alert(poststr);
     makePOSTAddRequest('show.php'+poststr, poststr); 
	 scroll('channels','700');
   } 
function showchannel(id) { 
     var poststr ="?ch="+id;
	 //alert(poststr);
     makePOSTAddRequest('show.php'+poststr, poststr); 
	 scroll('channels','700');
   } 


function kgttabtv(id){
	document.getElementById(id).className="submenuselect";
}
function newwindow(id,tab,ch){
if (id=="add"){
	window.open('tool.php?act=add','','scrollbars=1,menubar=0,status=1,width=300,height=200,left=200,top=110')
	}
	else if (id=="help"){
		window.open('tool.php?act=help','p','scrollbars=1,menubar=0,status=1,width=700,height=350,left=50,top=110');
	}
	else if (id=="lichphatsong"){
		window.open('tool.php?act=lichphatsong','p','scrollbars=1,menubar=0,status=1,width=620,height=540,left=50,top=110');
	}
	else if (id=="error"){
		window.open('tool.php?act=error&channel=tab-'+tab+',xem-'+ch,'','scrollbars=1,menubar=0,status=1,width=700,height=250,left=50,top=110');
	}
	else if (id=="new"){
		window.open('newwindow.php?tab='+tab+'&xem='+ch+'&type=2','','scrollbars=1,menubar=0,status=0,width=700,height=500,left=100,top=0');
		document.getElementById('MPlayer').innerHTML="";
	}
}
function addFavorite()
{
	var url=window.location;if(document.all)
	window.external.AddFavorite(url,'KênhGi&#7843;iTrí! Tivi');else if(window.sidebar)
	window.sidebar.addPanel('KênhGi&#7843;iTrí! Tivi',url,"")
}

function setHomepage(obj,url)
{
    obj.style.behavior='url(#default#homepage)';
    if (document.all)
    {
        obj.setHomePage(url);
    }
    else
    {
        alert("Kéo Logo c&#7911;a site chúng tôi vào nút Home &#273;&#7875; &#273;&#7863;t site chúng tôi làm trang ch&#7911;");
    }
}