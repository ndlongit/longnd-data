<?php
/**
* @version 1.5.x
* @package JoomVision Project
* @email webmaster@joomvision.com
* @copyright (C) 2010 http://www.JoomlaVision.com. All rights reserved.
*/

/* 
 *DEFINE 
 */
defined( '_JEXEC' ) or die( 'Restricted access' );

$jvconfig = new JConfig();
$menustyle = $this->params->get('jv_menustyle');
$jvTools = new JV_Tools($this);
///-----------------------------------------------------///

$jvcolorstyle = "JVContoStyleCookieSite";

$get_ms = JRequest::getVar('menustyle');
$cookie_ms = $jvTools->get_cookie('jvmenustyle_conto');

$get_rtl = JRequest::getVar('direction');
$cookie_rtl = $jvTools->get_cookie('jv_rtl_conto');

if( $cookie_ms && $cookie_ms != "" )
	$menustyle = $cookie_ms;

if($get_ms) {
	$menustyle = $get_ms;
	$jvTools->set_cookie('jvmenustyle_conto',$get_ms);
}

if($this->params->get('jv_rtl')) {
	$jvrtl = 'rtl';
} else {
	$jvrtl = 'ltr';
}

if( $cookie_rtl && $cookie_rtl != "" )
	$jvrtl = $cookie_rtl;

if($get_rtl && ($get_rtl == 'rtl' || $get_rtl == 'ltr') ) {
	$jvrtl = $get_rtl;
	$jvTools->set_cookie('jv_rtl_conto',$get_rtl);
}

/*
 * Behavior 
 */
JHTML::_('behavior.mootools');

/*
 * VARIABLES 
 */
$default_menu = $this->params->get('menutype');
if (!$default_menu) $default_menu = 'mainmenu';
$menu = new MenuSystem($menustyle,$default_menu,$this->template,$jvrtl);

/* 
 *GZIP Compression
 */
$gzip  = ($this->params->get('gzip', 1)  == 0)?"false":"true";
///-----------------------------------------------------/// 
$showTools = $this->params->get('showtools',1);
/*
 *Auto Collapse Divs Functions
 */ 
$modLeft = $this->countModules('left');
$modRight = $this->countModules('right');

if(!$modLeft && !$modRight) {
	$jv_width = "-full";
} elseif(!$modLeft) {
	$jv_width = "-right";
} elseif(!$modRight) {
	$jv_width = "-left";
} else {
	$jv_width = "";
}

$changecolor = '
	<img style="cursor: pointer; margin-right:5px;" id="jvcolor1" src="'.$jvTools->templateurl().'/images/color_1.png" alt="'.JText::_('Lightblue').'" title="'.JText::_('Lightblue').'" />
	<img style="cursor: pointer; margin-right:5px;" id="jvcolor2" src="'.$jvTools->templateurl().'/images/color_2.png" alt="'.JText::_('Gray').'" title="'.JText::_('Gray').'" />';

$changefont = '
	<img style="cursor: pointer;" src="'.$jvTools->templateurl().'images/A-minus.jpg" onclick="switchFontSize(\''.$this->template.'_jv_font\',\'dec\'); return false;" alt="" />
	<img style="cursor: pointer;" src="'.$jvTools->templateurl().'images/A-def.jpg" onclick="switchFontSize(\''.$this->template.'_jv_font\','.$jvTools->getParam("jv_font").'); return false;" alt="" />
	<img style="cursor: pointer;" src="'.$jvTools->templateurl().'images/A-plus.jpg" onclick="switchFontSize(\''.$this->template.'_jv_font\',\'inc\'); return false;" alt="" />
	<script type="text/javascript">var CurrentFontSize=parseInt(\''.$jvTools->getParam("jv_font").'\');</script>';

?>