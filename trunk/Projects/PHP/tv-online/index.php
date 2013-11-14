<?php
include('admin/config.php');
include('admin/func.php');
session_start();
$langid=$_GET['lang'];
$slng = $_GET['lng'];
$sch = $_GET['ch'];
$error=$_GET['r'];
$_SESSION['lang']=$_COOKIE['MoTivi_LANG'];
if ($langid !=""){
	m_setcookie('MoTivi_LANG', $langid);
	$_SESSION['lang']=$langid;
}
if (!$_SESSION['lang']) {
	m_setcookie('MoTivi_LANG', $default_lang);
	$_SESSION['lang']=$default_lang;
}
$flang='lang/'.$_SESSION['lang'].'_index.php';
if (file_exists($flang)){
	include($flang);
}else{
	include('lang/'.$default_lang.'_index.php');
	m_setcookie('MoTivi_LANG', $default_lang);
}
$tab = $_GET['tab'];
$kenh = $_GET['xem'];
$kenh = strtolower($kenh);
$linktab = $link_folder.'/'.$tab.'.txt';
$loi=false;
?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
	<meta http-equiv=Content-Type content="text/html; charset=UTF-8">
	<link rel=stylesheet type="text/css" href="style.css" >
    <title>Truy&#7873;n Hinh Tr&#7921;c Tuy&#7871;n</title>
  	<meta name="keywords" content="Tivi365.net, Truyen hinh truc tuyen, truy&#7873;n h�nh tr&#7921;c tuy&#7871;n, tv, truyen hinh, truy&#7873;n h�nh, tivi, television, phim, phim Vi&#7879;t Nam, phim Viet Nam, xem tivi, xem tv, xem phim, phim online, Xem truyen hinh truc tuyen - Truy&#7873;n h�nh tr&#7921;c tuy&#7871;n - Truyen hinh truc tuyen - Xem bong da online - VTC1, VTC2, VTC5, VTC8, VTC9, VTC10, VTC14, VTC16, VTV1, VTV2,VTV3, VTV4, HTV, HTV7, HTV9, Tin t&#7913;c - Th&#7901;i s&#7921;,  Phim Vi&#7879;t Nam, Phim t�i li&#7879;u, B&#7843;n tin VTC, H�i, SCTV1, SCTV2, yantv, SCTV3, SCTV4, yeah1tv, SCTV5, SCTV6, SCTV7, SCTV8, SCTV9, SCTV10, SCTV11, SCTV12,SCTV13, SCTV14, SCTV15, SCTV16, SCTV17, SCTV18, Today TV, VTC7, VTC1HD, VTC2HD, VTC2, VTC1, VTC3, VTC4, VTC5, VTC6, VTC7, VTC8, VTC9, VTC10, VTC11, VTC12, VTC14, VTC16, Let's Vi&#7879;t, &#272;&#7891;ng Th�p, V&#297;nh Long, B�nh D&#432;&#417;ng, B�nh Ph&#432;&#7899;c, H&#7853;u Giang, S�c Tr&#259;ng, Ki�n Giang, An Giang, B&#7841;c Li�u, C� Mau, H� N&#7897;i, Nam &#272;&#7883;nh, Ph� Y�n, S�i G�n, Tp HCM, VOV, H&#432;&#7899;ng d&#7851;n xem tivi, HBO, AXN, Star Movie, Euro Sport, ESPN, SopCast, B�ng &#272;� Tr&#7921;c Tuy&#7871;n, B�ng &#272;� Online, Ngo&#7841;i h&#7841;ng anh, C&#7847;n Th&#417;, Th&#7915;a Thi�n Hu&#7871;, V&#361;ng T�u, Tuy�n quang, Qu&#7843;ng Ng�i, Thanh H�a, Ngh&#7879; An, &#272;� N&#7861;ng, Phim Ho&#7841;t H�nh, Thi&#7871;u Nhi, Kh�nh H�a, Nha Trang, L�m &#272;&#7891;ng, HTV2, Thu&#7847;n Vi&#7879;t, VTV9, VTV6, VTV5, ANTV, C�ng An Nh�n D�n, &#272;�i Ti&#7871;ng n�i Vi&#7879;t Nam, INFO TV, Nh&#7841;c S�n, Karaoke Online, Chat Box, Tivi HD, Phim HD, ITV, TTXVN, Tom and Jerry, &#272;&#7891;ng nai, Ti&#7873;n Giang, H&#7843;i Ph�ng, B&#7871;n Tre, &#272;� l&#7841;t, Gia Lai, &#272;&#7855;c L&#7855;c, MTV, Truy&#7873;n h�nh &#273;&#7883;a ph&#432;&#417;ng, Code tivi, Nghe Nh&#7841;c mp3, Video, Download Ph&#7847;n m&#7873;m, Link Sopcast, xem AOE Online, Chimsedinang, vanlove, gametv, chipdeptrai, SF online, hoang mai nhi, tutj , dota, fifa online, dot kich" />
      <meta name="description" content="Tivi365.net,Truyen hinh truc tuyen, Tv online, TV online chanel, Truy&#7873;n h�nh tr&#7921;c tuy&#7871;n, Xem truy&#7873;n h�nh tr&#7921;c tuy&#7871;n, Television online, Vietnam TV, VTV4, VTV3, VTV4 online, Hanoi online, HTV online, MTV online, ESPN online, vtc1, vtc3, VTC, vtv, vtv4, htv, vov, TV channels, Stock channel, Financial TV channel, Economic news channel, broacasting tv , cable TV online, Bong da, Foolball, Euro, sea game, Champions League, v-league, xem AOE Online, Chimsedinang, vanlove, gametv, chipdeptrai, SF online, hoang mai nhi, tutj, dota, fifa online, dot kich" />
<meta name="robots" content="index,follow" />
<meta name="Googlebot" content="index, follow" />
<META NAME="author" CONTENT= "Tivi365.net">
<META NAME="Copyright" content="www.tivi365.net">
<link href='http://www.tivi365.net/icon_tv.ico' rel='icon' type='image/x-icon'/>
</head>
<script src="nguyen-tan-tai.js" type="text/javascript"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js" type="text/javascript" language="javascript"></script>
         <script type="text/javascript" src="tooltips.js"></script>
<script type="text/javascript" src="http://tivi365.net/popup.js"></script> 
<script type='text/javascript'>
			//[Long]
			/*
            $(document).ready(function(){
                $("#shadow").css("height", $(document).height()).hide();
                $(".lightSwitcher").click(function(){
                    $("#shadow").toggle();
                    if ($("#shadow").is(":hidden"))
                        $(this).html("Tắt Đèn").removeClass("turnedOff");
                     else
                        $(this).html("Bật Đèn").addClass("turnedOff");
                });
               
            });
			*/
        </script>
         <script type="text/javascript" src="http://ad.vatgia.com/static/jsv2/advatgia.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
      <script type="text/javascript">
         var pageOptions = {
           'webId' : 237,
           'category' : 0
         };
         
         var adblock = {
                           'adblock1' : {
            					'divIdShow' : 'adVatgia_block_1',
            					'numAd'	: 3,
            					'typeShow'	: 1,
            					'style' : {'width' : 540}
            					}
                    ,
                    'adblock2' : {
      					'divIdShow' : 'AdVatgia_block_2',
      					'numAd'	: 3,
      					'typeShow'	: 0,
      					'style' : {'width' : 130}
      					},
                  'adblock3' : {
      					'divIdShow' : 'AdVatgia_block_3',
      					'numAd'	: 3,
      					'typeShow'	: 0,
      					'style' : {'width' : 130}
      					},
                    'adblock4' : {
            					'divIdShow' : 'adVatgia_block_4',
            					'numAd'	: 3,
            					'typeShow'	: 1,
            					'style' : {'width' : 540}
            					},
                      'adblock5' : {
                             'divIdShow' : 'adVatgia_block_5',
                             'numAd' : 5,
                             'typeShow' : 2,
                             'style' : {'width' : 250, 'height' : 220, 'link' : '#aaa', 'border' : '#2D81FB'}
                             }
               				
         };
         new vatgiaAd(pageOptions, adblock).Ads();
           var pageWidth = 1000;
      var widgetWidth = 130;
      $(function(){
			checkAds($(window).width(), pageWidth);
			$(window).resize(function(){
	      	checkAds($(window).width(), pageWidth);
	  		});
		});
      
		var isIE6	= (navigator.userAgent.toLowerCase().indexOf("msie 6") != -1 ? true : false);
      
		function checkAds(windowWidth, pageWidth){
			var posLeft	= (windowWidth - pageWidth)/2 - widgetWidth - 3;
			var posRight	=	(windowWidth - pageWidth)/2 - widgetWidth - 3;
         
			if(windowWidth < 1260){
			    $("#AdVatgia_block_2").hide();
			    $('#AdVatgia_block_3').hide();
			}else{
			    $("#AdVatgia_block_2").show();
			    $('#AdVatgia_block_3').show();
				 $("#AdVatgia_block_2").css({ top: 5, left: posLeft, position: (isIE6 == true ? "absolute" : "fixed")});
			 	$("#AdVatgia_block_3").css({ top: 5, right: posRight, position: (isIE6 == true ? "absolute" : "fixed") });
			}
		}
      </script>
<body>
<table width="950" align="center" border="0" cellpadding="0" cellspacing="0">
  <tbody>
  <tr><td colspan="3" align="center" bgcolor="black"><?php echo file_get_contents('header.html'); ?></td></tr>
  <tr>
    
    <td>
	<div style="background: #333333 repeat scroll 0% 0%; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial; margin-bottom:5px;margin-top:5px; border-bottom:1px #FF3F00 solid; border-top:1px #FF3F00 solid;" >
		<table cellpadding="0" cellspacing="0" >
		<tbody><tr><td   valign="left" align="center" nowrap="nowrap" style="font-size:14px">
                         <ul id="thicktabs">
					<li><a href="index.php">Trang Chủ</a></li>
                    <li><a href="http://phim.tivi365.net" target="_blank" title="Xem Phim Online">Phim Online</a></li>
                    <li><a href="http://truyen.tivi365.net" target="_blank" title="Nghe K&#7875; Truy&#7879;n Online">Truyện Audio</a></li>
                    <li><a href="http://nhac.tivi365.net" target="_blank" title="Nghe Nh&#7841;c Online">Nhạc Online</a></li>
                    <li><a href="http://bongda.tivi365.net" target="_blank" title="Xem Bóng Đá Trực Tuyến">Bóng Đá</a></li>
					<li><a href="http://sctv.tivi365.net" target="_blank" title="Xem SCTV Trực Tuyến">SCTV Trực Tuyến</a></li>
					</ul>

                 </td>
		</tr></tbody></table>
		</div>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tbody><tr>
          <td class="white" valign="top" align="center" width="200px" bgcolor="#FFFFFF">
<table width="100%" border="0" cellpadding="0" cellspacing="1">
		 <tbody><tr>
			<td align="left" style="width:200px" >
            <table class="bo-tron-goc" width="100%" border="0" cellpadding="0" cellspacing="0">
	            <tbody>
	            <tr><td class="groupmenu" valign="middle" align="center"height="30">..: <?=$lang_index['tools']?> :..</td>
				</tr>
                <tr><td class="submenu" bgcolor="#FFFFFF">
                    <a href="index.php"><?=$lang_index['hompage']?></a>
                	</td></tr>
                 <tr><td class="submenu" bgcolor="#FFFFFF">
                    <a href="javascript:void()" onclick="setHomepage(this,'<?=$mainurl?>')"><?=$lang_index['sethompage']?></a>
               	 	</td></tr>
                <tr><td class="submenu" bgcolor="#FFFFFF">
                    <a href="javascript:void()" onclick="addFavorite()"><?=$lang_index['favarite']?></a>
                    </td></tr>
                <tr><td class="submenu" bgcolor="#FFFFFF">
                    <a href="Javascript:()" onclick="newwindow('add');"><?=$lang_index['addchannel']?></a>
					</td></tr>
               <tr><td class="submenu" bgcolor="#FFFFFF">
                    <a href="Javascript:()" onclick="newwindow('lichphatsong');"><?=$lang_index['lichphatsong']?></a>
					</td></tr>
                <tr><td class="submenu" bgcolor="#FFFFFF">
                    <a href="Javascript:()" onclick="newwindow('help')"><?=$lang_index['help']?></a>
                	</td></tr>
                <tr><td class="submenu" bgcolor="#FFFFFF">
                    <a href="pro.php" target="_blank"><?=$lang_index['protable']?></a>
                	</td></tr>
                </tbody></table>
	           <table class="bo-tron-goc" width="100%" border="0" cellpadding="0" cellspacing="0">
	            <tbody>
	            <tr><td class="groupmenu" valign="middle" align="center"height="30">..: <?=$lang_index['vnchannel']?> :..</td>
				</tr>
                <?php 
				$listvn = file($link_folder.'/'.'vn_tab.txt');
				foreach ($listvn as $listvn_num => $listvn_ctn) {
					if (preg_match("#(.*?)_MOTV_(.*?)#",$listvn_ctn)){
						$solution = @split('_MOTV_',trim($listvn_ctn));
					echo'
                    <tr><td class="submenu" >
                    <div id="tab_'.$solution[0].'" class=""><a  class="white" href="javascript: showtab(\''.$solution[0].'\')" >'.$solution[1].'</a></div></td>
					</tr>';
					}
				}?>
                </tbody></table>
                 
                <table class="bo-tron-goc" width="100%" border="0" cellpadding="0" cellspacing="0">
	            <tbody>
	            <tr><td class="groupmenu" valign="middle" align="center" height="30">..: <?=$lang_index['wdchannel']?> :..</td>
				</tr><?php 
				$listvn = file($link_folder.'/'.'wd_tab.txt');
				foreach ($listvn as $listvn_num => $listvn_ctn) {
						if (preg_match("#(.*?)_MOTV_(.*?)#",$listvn_ctn)){
						$solution = @split('_MOTV_',trim($listvn_ctn));
					echo'
                    <tr><td class="submenu" >
                    <div id="tab_'.$solution[0].'" class=""><a class="white" href="javascript: showtab(\''.$solution[0].'\')" >'.$solution[1].'</a></div></td>
					</tr>';
					}
				}?>
                </tbody></table>
<!--quang cao-->
				<div id="adnet_widget_13257" style="display: none;"></div><script type="text/javascript">var is_load_adnet_lib = is_load_adnet_lib || 1;if(is_load_adnet_lib == 1) {is_load_adnet_lib = 2;if(typeof(jQuery)=='undefined') {document.write(unescape("%3Cscript src='http://s0.adnet.vn/jquery.min.js' type='text/javascript'%3E%3C/script%3E"));}document.write(unescape("%3Cscript src='http://s0.adnet.vn/js/adnet34.js' type='text/javascript'%3E%3C/script%3E"));}</script><script type="text/javascript" src="http://widget.adnet.vn/js/js.php?widget_id=13257"></script>
<!-- end quang cao-->

		</td></tr></tbody></table>
	</td>
          <td valign="top" bgcolor="#ffffff">
          	<table class="bo-tron-goc" cellpadding="0" cellspacing="0" style="width: 100%; padding:2px;margin-top:2px; position:relative;"><tbody>
            <tr><td height="30px" align="center" class="well"><b><?=$lang_index['wellcom']?></b><br>
<?=$lang_index['wellcom1']?>
</td>
          	<tr><td align="center" class="player">
<?php
//Show Channel
if (!file_exists($linktab)){
		echo file_get_contents('no.htm');
		$script = '';
}else{	
		$script = 'scrollTo("clock", 200 );';
		if ($kenh == "") {
			echo file_get_contents('no.htm');
		}
		else if ($kenh != "") {
		//Filter infomation about channel.
		$kenh_id = file($linktab);
			foreach ($kenh_id as $kenh_num => $kenh_ctn) {
				if (preg_match("#(.*?)_MOTV_(.*?)#",$kenh_ctn)){
					$solutionch = @split('_MOTV_',trim($kenh_ctn));
					if ($solutionch[0] == $kenh){
						$linkxem = $solutionch[1];
						$daxe = $solutionch[0];
						break;
					}
				}
			}
		//Check error text
			$err_file = trim(file_get_contents('kenhloi.txt'));
			$errstring ="tab=".$tab."&xem=".$daxe."";
			$chekcerr= substr_count($err_file ,$errstring);
			if ($chekcerr != 0) echo '<div align="center" style="font-weight:bold">'.$lang_index['errtext'].'</div><br/>';
		//Function to play link stream tv.
			watch_link($linkxem,"540px","450px",$lang_index['channelno'],1,$tab,$kenh);
			//if (!$loi) $script .='document.title="'.$webtitle.' - '.$lang_index['view_tv'].' '.strtoupper($daxe).'";';
		}			
	}
?>
			</td></tr>
            <tr><td align="center" height="28"  >
			<!--[Long]-->
			<!--<iframe scrolling="no" frameborder="0" width="530" height="30" allowtransparency="true" src="http://tivi365.net/thongbao.html"></iframe>-->
				<div id="daxe">
<?php
				//Tools for viewer.
				if ($daxe){
					echo '<p style="text-transform:uppercase;font-size:10px;">'.$lang_index['daxetext'].' <b>'.$daxe.'</b></p><p><a href="Javascript:()" onclick="newwindow(\'new\',\''.$tab.'\',\''.$daxe.'\')"><img border="0" src="images/tv.png" style="width:auto;height:auto"/>      '.$lang_index['newwin'].'</a> -- <a href="Javascript:()" onclick="newwindow(\'error\',\''.$tab.'\',\''.$daxe.'\')"><img border="0" src="images/error.png" style="width:auto;height:auto"/>      '.$lang_index['messerr'].'</a> -- <a href="Javascript:()" onclick="newwindow(\'help\')"><img border="0" src="images/help.png" style="width:24px;height:24px"/>      '.$lang_index['fullhelp'].'</a></p>';
}
?>
					</div></td></tr>
			<tr><td align="center"><div id="channels" align="center" style="width:520px;min-width:520px;">
<?php 
			//Show channel list in this tab selected.
			include('show.php');
?>
            
</div>
  <div id="adVatgia_block_1"></div>
<div class="chatbox">
&hearts; Giao Lưu Kết Bạn Bốn Phương &hearts;
<!--[Long]
<iframe scrolling="no" frameborder="0" width="530" height="564" src="nguyen-tan-tai.php"></iframe>-->
</div>
<!--quang cao -->
<div id="adVatgia_block_4"></div>
<div id="adnet_widget_13362" style="display: none;"></div><script type="text/javascript">var is_load_adnet_lib = is_load_adnet_lib || 1;if(is_load_adnet_lib == 1) {is_load_adnet_lib = 2;if(typeof(jQuery)=='undefined') {document.write(unescape("%3Cscript src='http://s0.adnet.vn/jquery.min.js' type='text/javascript'%3E%3C/script%3E"));}document.write(unescape("%3Cscript src='http://s0.adnet.vn/js/adnet34.js' type='text/javascript'%3E%3C/script%3E"));}</script><script type="text/javascript" src="http://widget.adnet.vn/js/js.php?widget_id=13362"></script>
<!--end -->

</td></tr>
          	</tbody></table>
          </td>
          <td class="white" valign="top" width="20%" bgcolor="#FFFFFF" style="margin-left:2px; padding:2px;">
          	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		 <tbody><tr>
			<td align="left" style="width:200px">
				<!-- [Long]
                <table class="bo-tron-goc" cellpadding="0" cellspacing="0">
	            <tbody>
	            <tr><td class="groupmenu" valign="middle" align="center" height="30">..: <?=$lang_index['searchch']?> :..</td>
				</tr><tr><td align="center">
     				<input type="text" value="Tìm Kiếm Kênh" id="searchch" onblur="if(this.value=='') this.value='Tìm Kiếm Kênh'" onfocus="javascript: if (this.value=='Tìm Kiếm Kênh') this.value=''" value="Tìm Kiếm Kênh" /><button onclick="showchannel(document.getElementById('searchch').value)"><?=$lang_index['btn_search']?></button>
                </td></tr>
                </tbody></table>
				-->
                <table width="100%" class="bo-tron-goc" cellpadding="0" cellspacing="0">
	            <tbody>
	            <tr><td class="groupmenu" valign="middle" align="center" height="30">..: <?=$lang_index['kenh']?> :..</td>
				</tr><tr>
                    <td align="center" bgcolor="#FFFFFF">
					<!-- [Long]
					<a href="?tab=the-gioi-vpop-nhac-tre&xem=karaoke" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'images/karaoke.png\' width=150>')">
							<span><img src="images/karaoke.png" border="0" style="width:162px; height:65px; margin-bottom:2px; border:2px dotted #FF0000;" alt="KaraOke" /></span></a>
					-->
					<a href="?tab=truyen-hinh-viet-nam&xem=vtv1" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'logo/vtv1.gif\' width=150>')">
							<span><img src="logo/vtv1.gif" border="0" class="cover" alt="VTV1" /></span></a>
					<a href="?tab=truyen-hinh-viet-nam&xem=vtv2" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'logo/vtv2.gif\' width=150>')">
							<span><img src="logo/vtv2.gif" border="0" class="cover" alt="VTV2" /></span></a>
					<a href="?tab=truyen-hinh-viet-nam&xem=vtv3" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'logo/vtv3.gif\' width=150>')">
							<span><img src="logo/vtv3.gif" border="0" class="cover" alt="VTV3" /></span></a>
					<a href="?tab=truyen-hinh-viet-nam&xem=vtv4" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'logo/vtv4.gif\' width=150>')">
							<span><img src="logo/vtv4.gif" border="0" class="cover" alt="VTV4" /></span></a>
					<a href="?tab=truyen-hinh-viet-nam&xem=vtv-cantho1" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'logo/vtvcantho1.gif\' width=150>')">
							<span><img src="logo/vtvcantho1.gif" border="0" class="cover" alt="VTV CanTho1" /></span></a>
					<a href="?tab=truyen-hinh-viet-nam&xem=vtv-cantho2" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'logo/vtvcantho2.gif\' width=150>')">
							<span><img src="logo/vtvcantho2.gif" border="0" class="cover" alt="VTV CanTho2" /></span></a>
					<a href="?tab=truyen-hinh-tp-hcm&xem=htv7" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'logo/htv7.gif\' width=150>')">
							<span><img src="logo/htv7.gif" border="0" class="cover" alt="HTV7" /></span></a>
					<a href="?tab=truyen-hinh-tp-hcm&xem=htv9" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'logo/htv9.gif\' width=150>')">
							<span><img src="logo/htv9.gif" border="0" class="cover" alt="HTV9" /></span></a>
					<a href="?tab=the-gioi-vpop-nhac-tre&xem=itv" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'logo/itv.gif\' width=150>')">
							<span><img src="logo/itv.gif" border="0" class="cover" alt="ITV" /></span></a>
					<a href="?tab=truyen-hinh-tong-hop&xem=yeah1tv" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'logo/yeah1tv.gif\' width=150>')">
							<span><img src="logo/yeah1tv.gif" border="0" class="cover" alt="Yeah1TV" /></span></a>
					<a href="?tab=truyen-hinh-tp-hcm&xem=htv-canhac" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'logo/htvcanhac.jpg\' width=150>')">
							<span><img src="logo/htvcanhac.jpg" border="0" class="cover" alt="HTV" /></span></a>
					<a href="?tab=the-gioi-vpop-nhac-tre&xem=vpoptv" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'logo/vpop.jpg\' width=150>')">
							<span><img src="logo/vpop.jpg" border="0" class="cover" alt="VPOP" /></span></a>
					<a href="?tab=truyen-hinh-kts-vtc&xem=vtc1" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'logo/vtc1.gif\' width=150>')">
							<span><img src="logo/vtc1.gif" border="0" class="cover" alt="VTC1" /></span></a>
					<a href="?tab=truyen-hinh-kts-vtc&xem=vtc2" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'logo/vtc2.gif\' width=150>')">
							<span><img src="logo/vtc2.gif" border="0" class="cover" alt="VTC2" /></span></a>
					<a href="?tab=truyen-hinh-kts-vtc&xem=vtc3" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'logo/vtc3.gif\' width=150>')">
							<span><img src="logo/vtc3.gif" border="0" class="cover" alt="VTC3" /></span></a>
					<a href="?tab=truyen-hinh-kts-vtc&xem=vtc10" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'logo/vtc10.gif\' width=150>')">
							<span><img src="logo/vtc10.gif" border="0" class="cover" alt="VTC10" /></span></a>
					<a href="?tab=truyen-hinh-cap-sctv&xem=sctv1" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'logo/sctv1.jpg\' width=150>')">
							<span><img src="logo/sctv1.jpg" border="0" class="cover" alt="SCTV1" /></span></a>
                                        <a href="?tab=truyen-hinh-cap-sctv&xem=sctv2" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'logo/sctv2.jpg\' width=150>')">
							<span><img src="logo/sctv2.jpg" border="0" class="cover" alt="SCTV2" /></span></a>
                                       <a href="?tab=truyen-hinh-cap-sctv&xem=sctv15" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'logo/sctv15.jpg\' width=150>')">
							<span><img src="logo/sctv15.jpg" border="0" class="cover" alt="SCTV15" /></span></a>
                                       <a href="?tab=truyen-hinh-cap-sctv&xem=sctv18" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'logo/sctv18.png\' width=150>')">
							<span><img src="logo/sctv18.png" border="0" class="cover" alt="SCTV18" /></span></a>
                                      <a href="?tab=truyen-hinh-dia-phuong&xem=hanoi1" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'logo/hn1.gif\' width=150>')">
							<span><img src="logo/hn1.gif" border="0" class="cover" alt="HN1" /></span></a>
					<a href="?tab=truyen-hinh-dia-phuong&xem=hanoi2-2" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'logo/hn2.gif\' width=150>')">
							<span><img src="logo/hn2.gif" border="0" class="cover" alt="HN2" /></span></a>
					<a href="?tab=truyen-hinh-dia-phuong&xem=vinhlong1" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'logo/thvl1.gif\' width=150>')">
							<span><img src="logo/thvl1.gif" border="0" class="cover" alt="VL1" /></span></a>
					<a href="?tab=truyen-hinh-dia-phuong&xem=vinhlong2" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'logo/thvl2.gif\' width=150>')">
							<span><img src="logo/thvl2.gif" border="0" class="cover" alt="VL2" /></span></a>
					<a href="?tab=truyen-hinh-dia-phuong&xem=dongnai1" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'logo/dn1.png\' width=150>')">
							<span><img src="logo/dn1.png" border="0" class="cover" alt="DN1" /></span></a>
					<a href="?tab=truyen-hinh-dia-phuong&xem=dongnai2" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'logo/dn2.png\' width=150>')">
							<span><img src="logo/dn2.png" border="0" class="cover" alt="DN2" /></span></a>
					<a href="?tab=truyen-hinh-dia-phuong&xem=binhphuoc1" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'logo/thbp1.gif\' width=150>')">
							<span><img src="logo/thbp1.gif" border="0" class="cover" alt="BP1" /></span></a>
					<a href="?tab=truyen-hinh-dia-phuong&xem=binhphuoc2" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'logo/thbp2.gif\' width=150>')">
							<span><img src="logo/thbp2.gif" border="0" class="cover" alt="BP2" /></span></a>
					<a href="?tab=truyen-hinh-dia-phuong&xem=binhduong1" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'logo/btv1.gif\' width=150>')">
							<span><img src="logo/btv1.gif" border="0" class="cover" alt="BTV1" /></span></a>
					<a href="?tab=truyen-hinh-dia-phuong&xem=binhduong2" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'logo/btv2.gif\' width=150>')">
							<span><img src="logo/btv2.gif" border="0" class="cover" alt="BTV2" /></span></a>
					<a href="?tab=truyen-hinh-dia-phuong&xem=binhthuan" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'logo/bt.gif\' width=150>')">
							<span><img src="logo/bt.gif" border="0" class="cover" alt="binhthuan" /></span></a>
					<a href="?tab=truyen-hinh-dia-phuong&xem=dongthap" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'logo/dongthap.gif\' width=150>')">
							<span><img src="logo/dongthap.gif" border="0" class="cover" alt="dongthap" /></span></a>
					<a href="?tab=truyen-hinh-dia-phuong&xem=quangnam" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'logo/quangnam.gif\' width=150>')">
							<span><img src="logo/quangnam.gif" border="0" class="cover" alt="quangnam" /></span></a>
					<a href="?tab=truyen-hinh-tong-hop&xem=vovtv" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'logo/vovtv.gif\' width=150>')">
							<span><img src="logo/vovtv.gif" border="0" class="cover" alt="VOVTV" /></span></a>
					<a href="?tab=truyen-hinh-tong-hop&xem=lifetv" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'logo/lifetv.jpg\' width=150>')">
							<span><img src="logo/lifetv.jpg" border="0" class="cover" alt="LIFETV" /></span></a>
					<a href="?tab=truyen-hinh-tp-hcm&xem=htv-thuanviet" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'logo/thuanviet.gif\' width=150>')">
							<span><img src="logo/thuanviet.gif" border="0" class="cover" alt="ThuanViet" /></span></a>
                                        <a href="?tab=movie&xem=hbo-vietsub" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'logo/hbo.gif\' width=150>')">
							<span><img src="logo/hbo.gif" border="0" class="cover" alt="HBO" /></span></a>
					<a href="?tab=movie&xem=megatv" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'logo/mega.jpg\' width=150>')">
							<span><img src="logo/mega.jpg" border="0" class="cover" alt="MEGA" /></span></a>
					</td>
                </tr>	
                </tbody></table>

<table width="100%" class="bo-tron-goc" cellpadding="0" cellspacing="0">
	            <tbody>
	            <tr><td class="groupmenu" valign="middle" align="center" height="30">..: <?=$lang_index['dai']?> :..</td>
				</tr><tr>
                    <td align="center" bgcolor="#FFFFFF">
					<a href="?tab=dai-phat-thanh&xem=vov1" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'logo/vov1.gif\' width=150>')">
							<span><img src="logo/vov1.gif" border="0" class="cover" alt="VOV1" /></span></a>
					<a href="?tab=dai-phat-thanh&xem=vov2" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'logo/vov2.gif\' width=150>')">
							<span><img src="logo/vov2.gif" border="0" class="cover" alt="VOV2" /></span></a>
					<a href="?tab=dai-phat-thanh&xem=vov3" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'logo/vov3.gif\' width=150>')">
							<span><img src="logo/vov3.gif" border="0" class="cover" alt="VOV3" /></span></a>
					<a href="?tab=dai-phat-thanh&xem=vov5" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'logo/vov5.gif\' width=150>')">
							<span><img src="logo/vov5.gif" border="0" class="cover" alt="VOV5" /></span></a>
					<a href="?tab=dai-phat-thanh&xem=voham" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'logo/voham.gif\' width=150>')">
							<span><img src="logo/voham.gif" border="0" class="cover" alt="VOHAM" /></span></a>
					<a href="?tab=dai-phat-thanh&xem=vohfm" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'logo/vohfm.gif\' width=150>')">
							<span><img src="logo/vohfm.gif" border="0" class="cover" alt="VOHFM" /></span></a>
					<a href="?tab=dai-phat-thanh&xem=binhduong" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'logo/bd-radio.jpg\' width=150>')">
							<span><img src="logo/bd-radio.jpg" border="0" class="cover" alt="BinhDuong" /></span></a>
					<a href="?tab=dai-phat-thanh&xem=haugiang" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'logo/haugiang-radio.jpg\' width=150>')">
							<span><img src="logo/haugiang-radio.jpg" border="0" class="cover" alt="HauGiang" /></span></a>
					<a href="?tab=dai-phat-thanh&xem=cantho" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'logo/tpct.jpg\' width=150>')">
							<span><img src="logo/tpct.jpg" border="0" class="cover" alt="CanTho" /></span></a>
					<a href="?tab=dai-phat-thanh&xem=vinhlong" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'logo/thvlfm.gif\' width=150>')">
							<span><img src="logo/thvlfm.gif" border="0" class="cover" alt="VinhLong" /></span></a>
					<a href="?tab=dai-phat-thanh&xem=nct-radio" onmouseout="hideddrivetip()" onmouseover="ddrivetip('<img src=\'logo/nct.png\' width=150>')">
							<span><img src="logo/nct.png" border="0" class="cover" alt="NCT" /></span></a>
					
					</td>
                </tr>	
                </tbody></table>
                           <table width="100%" class="bo-tron-goc" cellpadding="0" cellspacing="0">
	            <tbody>
	            <tr><td class="groupmenu" valign="middle" align="center" height="30">..: <?=$lang_index['thongke']?> :..</td>
				</tr><tr>
                    <td align="center" bgcolor="#FFFFFF">
					<a href="http://www.histats.com" alt="page hit counter" target="_blank" >
<embed src="http://s10.histats.com/407.swf"  flashvars="jver=1&acsid=1639335&domi=4"  quality="high"  width="190" height="80" name="407.swf"  align="middle" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" wmode="transparent" /></a>
<img  src="http://sstatic1.histats.com/0.gif?1639335&101" alt="site stats" border="0">	
					</td>
                </tr>	
                </tbody></table>
		</td></tr></tbody></table>
          </td>
        </tr>
      </tbody></table>
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tbody><tr>
		<td valign="bottom" bgcolor="#FFFFFF" height="50">
		  <table class="whitebold" width="95%" align="center" border="0" cellpadding="0" cellspacing="5" height="30">
            <tbody><tr>
             <td width="200" align="right" style="color:#000000; font-size:16px;"><?=$lang_index['yahoo']?> <a class="white" href="ymsgr:sendIM?<?=$admin_nick?>" ><img src="http://opi.yahoo.com/online?u=<?=$admin_nick?>&amp;m=g&amp;t=2&amp;l=us" alt="ymsgr:sendIM?<?=$admin_nick?>" style="width:100px; height:26px; margin-bottom:-8px; border:none;" /></a></td>
            </tr></tbody></table>
		 </td></tr><tr><td class="headmenu" align="left" height="30" >&nbsp;<?=$lang_index['langchange']?>:&nbsp;
		 <select id="langbox" onchange="window.location='?lang='+this.value;" style="width:150px" ><?php 
				$listlang = file('lang/list_lang.txt');
				foreach ($listlang as $listlang_num => $listlang_ctn) {
						if (preg_match("#(.*?)_MOTV_(.*?)#",$listlang_ctn)){
						$solution = split('_MOTV_',trim($listlang_ctn));
					echo'
                    <option value="'.$solution[0].'" '.(($_SESSION['lang']==$solution[0])?' selected':'').'>'.$solution[1].'</option>';
					}
				}?>
                        </select>
		 </td></tr></tbody></table>
	  </td>
  </tr>
<div id="AdVatgia_block_2"></div>
   <div id="AdVatgia_block_3"></div>
<div id="adVatgia_block_5"></div>
<!--[Long]
  <tr><td  colspan="3" align="center"><?php echo file_get_contents('footer.html'); ?></td></tr>
  -->
</tbody></table>
<script type="text/javascript" language="javascript">
<?=$script?>
</script>
</body></html>
<script type="text/javascript">
  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-27348105-1']);
  _gaq.push(['_trackPageview']);
  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();
</script>
<div class="gop-y"> 
<a href="javascript:void(window.open('lien-he/index.html',%20'','top=0,%20left=0,scrollbars=no,width=650,height=520'))"  title="Xem "> 
<img border="0" src="images/feedback.png" alt="Góp ý / Báo lỗi " width="35" height="60"></a></div>
<!--[Long]
<!--tat den
  <div class="div_fix">
  <div>
    <div id="command" align="center">
	<a class="lightSwitcher" href="#" title="Tắt Đèn Màn Hình Để Xem Tivi Rỏ Hơn">
	Tắt Đèn
	</a>
		</div>
  </div>
  </div>
  -->
<div id="shadow" ></div>
<!--end tat den-->
<!--quang cao-->
<script type="text/javascript">var is_load_adnet_lib = is_load_adnet_lib || 1;if(is_load_adnet_lib == 1) {is_load_adnet_lib = 2;if(typeof(jQuery)=='undefined') {document.write(unescape("%3Cscript src='http://s0.adnet.vn/jquery.min.js' type='text/javascript'%3E%3C/script%3E"));}document.write(unescape("%3Cscript src='http://s0.adnet.vn/js/adnet34.js' type='text/javascript'%3E%3C/script%3E"));}</script><script type="text/javascript">adnet_lib.showPopup(3642);</script>
