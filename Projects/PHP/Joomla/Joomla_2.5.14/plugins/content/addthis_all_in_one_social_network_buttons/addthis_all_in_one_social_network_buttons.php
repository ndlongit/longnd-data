<?php
/**
 * @version		1.0
 * @Project		AddThis All In One Social Network Buttons
 * @author 		Leon Wood, CMSVoteUp.com
 * @package		
 * @copyright	Copyright (C) 2011 CMSVoteUp.com. All rights reserved.
 * @license 	http://www.gnu.org/licenses/old-licenses/gpl-2.0.html GNU/GPL version 2
*/

// Check to ensure this file is included in Joomla!
defined( '_JEXEC' ) or die( 'Restricted access' );

jimport( 'joomla.plugin.plugin' );

class plgContentaddthis_all_in_one_social_network_buttons extends JPlugin {

	function plgContentaddthis_all_in_one_social_network_buttons( &$subject, $config )
	{
		parent::__construct( $subject, $config );
	}

	function onContentPrepare($context, &$article, &$params, $page=0)
	{	
		
		$document	= & JFactory::getDocument();
		$view		= JRequest::getCmd('view');
		$position          = $this->params->get( 'position',  '' );
		
		if ( $view != 'article' ) { 
			return;	
		} else {
			$title= htmlentities( $article->title, ENT_QUOTES, "UTF-8");
			$url = $this->getPageUrl($article);
			
			if ($position == '1'){
				$article->text =  $this->getPlugInHTML($params, $article, $url, $title) . $article->text;  
			}
			if ($position == '2'){
				$article->text = $article->text . $this->getPlugInHTML($params, $article, $url, $title);
			}
			if ($position == '3'){
				$article->text =  $this->getPlugInHTML($params, $article, $url, $title) . $article->text . $this->getPlugInHTML($params, $article, $url, $title);
			}			
		}
			
	}
	
	private function  getPlugInHTML($params, $article, $url, $title) { 
		$category_tobe_excluded   = $this->params->get('category_tobe_excluded', '' );
		$content_tobe_excluded           = $this->params->get('content_tobe_excluded', '' );
		$excludedCatList = @explode ( ",", $category_tobe_excluded );	
		$excludedContentList 	   = @explode ( ",", $content_tobe_excluded );		
		if ( in_array ( $article->id, $excludedContentList ) || in_array ( $article->catid, $excludedCatList ) ) 
		{	return;
		}
		
		$pub_id   		= $this->params->get( 'pub_id' );
		$track_this          	= $this->params->get( 'track_this');
		if ($track_this == 1) {
			$track_this = "true";
		} else {
			$track_this = "false";
		}
		
		$style               = $this->params->get('style');		
		$credit_to_Author           = $this->params->get( 'credit_to_Author', 1);
		$display_cms_vote_up           = $this->params->get( 'display_cms_vote_up');
		$verb_to_display  = $this->params->get( 'verb_to_display');
		if ($verb_to_display == 1) {
			$verb_to_display  = "like";
		} else {
			$verb_to_display = "recommend";
		}
		$layout_style_cms_vote_up       = $this->params->get( 'layout_style_cms_vote_up');		
		
		
		$htmlCode ="<div>";
		$display_add_this = 1;
		//Digg Button
		//http://www.addthis.com/gallery/share-dock/
		if ($display_add_this ) {
			switch($style){
            case "toolbox_16x16":
                $htmlCode .= "<!-- AddThis Button BEGIN --> \n";
				$htmlCode .= "<div class=\"addthis_toolbox addthis_default_style\"> \n";
				$htmlCode .= "<a class=\"addthis_button_compact\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_googlebuzz\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_twitter\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_facebook\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_linkedin\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_stumbleupon\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_digg\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_misterwong\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_multiply\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_hellotxt\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_bitly\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_googletranslate\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_myspace\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_bebo\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_amazonwishlist\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_baidu\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_blinklist\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_blip\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_blogger\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_faves\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_formspring\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_googlereader\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_hackernews\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_hotmail\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_instapaper\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_myaol\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_netvibes\"></a> \n";
				$htmlCode .= "<a title=\"Send to CMSVoteUp\" href=\"http://cmsvoteup.com/index.php?page=submit1&url=$url\"><img border=\"0\" src=\"http://cmsvoteup.com/images/cmsvoteup-icon-16x16-2.png\"></a> \n";
				$htmlCode .= "<a title=\"Send to DontClickOn\" href=\"http://dontclickon.com/index.php?page=submit1&url=$url\"><img border=\"0\" src=\"http://dontclickon.com/images/dco-icon-16x16.png\"></a> \n";
				$htmlCode .= "</div> \n";
				$htmlCode .= "<script type=\"text/javascript\">var addthis_config = {\"data_track_clickback\":$track_this};</script> \n";
				$htmlCode .= "<script type=\"text/javascript\" src=\"http://s7.addthis.com/js/250/addthis_widget.js#pubid=$pub_id\"></script> ";
                break;
			case "toolbox_32x32":
                $htmlCode .= "<!-- AddThis Button BEGIN --> \n";
				$htmlCode .= "<div class=\"addthis_toolbox addthis_default_style addthis_32x32_style\"> \n";
				$htmlCode .= "<a class=\"addthis_button_compact\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_googlebuzz\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_twitter\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_facebook\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_linkedin\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_stumbleupon\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_digg\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_misterwong\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_multiply\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_hellotxt\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_bitly\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_googletranslate\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_myspace\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_bebo\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_amazonwishlist\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_baidu\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_blinklist\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_blip\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_blogger\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_faves\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_formspring\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_googlereader\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_hackernews\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_hotmail\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_instapaper\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_myaol\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_netvibes\"></a> \n";
				$htmlCode .= "<a title=\"Send to CMSVoteUp\" href=\"http://cmsvoteup.com/index.php?page=submit1&url=$url\"><img border=\"0\" src=\"http://cmsvoteup.com/images/cmsvoteup-icon-32x32-2.png\"></a> \n";
				$htmlCode .= "<a title=\"Send to DontClickOn\" href=\"http://dontclickon.com/index.php?page=submit1&url=$url\"><img border=\"0\" src=\"http://dontclickon.com/images/dco-icon-32x32.png\"></a> \n";
				$htmlCode .= "</div> \n";
				$htmlCode .= "<script type=\"text/javascript\">var addthis_config = {\"data_track_clickback\":$track_this};</script> \n";
				$htmlCode .= "<script type=\"text/javascript\" src=\"http://s7.addthis.com/js/250/addthis_widget.js#pubid=$pub_id\"></script> ";
                break;
			case "sharecount_vertical":
                $htmlCode .= "<!-- AddThis Button BEGIN -->";
				$htmlCode .= "<div class=\"addthis_toolbox addthis_default_style \">\n";
				$htmlCode .= "<a class=\"addthis_counter\"></a>\n";
				$htmlCode .= "</div>\n";
				$htmlCode .= "<script type=\"text/javascript\">var addthis_config = {\"data_track_clickback\":$track_this};</script> \n";
				$htmlCode .= "<script type=\"text/javascript\" src=\"http://s7.addthis.com/js/250/addthis_widget.js#pubid=$pub_id\"></script> ";
                break;
			case "sharecount_horizontal":
                $htmlCode .= "<!-- AddThis Button BEGIN -->";
				$htmlCode .= "<div class=\"addthis_toolbox addthis_default_style \"> \n";
				$htmlCode .= "<a class=\"addthis_counter addthis_pill_style\"></a>\n";
				$htmlCode .= "<a class=\"addthis_button_facebook_like\" fb:like:layout=\"button_count\"></a>\n";
				$htmlCode .= "<a class=\"addthis_button_tweet\"></a>\n";
				if ($display_cms_vote_up ) {
					$htmlCode .= "<script type=\"text/javascript\" src=\"http://cmsvoteup.com/socialbuttons/1/vote-up.js\"></script>\n";
				}
				$htmlCode .= "</div>\n";
				$htmlCode .= "<script type=\"text/javascript\">var addthis_config = {\"data_track_clickback\":$track_this};</script> \n";
				$htmlCode .= "<script type=\"text/javascript\" src=\"http://s7.addthis.com/js/250/addthis_widget.js#pubid=$pub_id\"></script> ";
                break;
			case "toolbox_style1":
                $htmlCode .= "<!-- AddThis Button BEGIN --> \n";
				$htmlCode .= "<div class=\"addthis_toolbox addthis_default_style \"> \n";
				$htmlCode .= "<a href=\"http://www.addthis.com/bookmark.php?v=250&amp;pubid=$pub_id\" class=\"addthis_button_compact\">Share</a> \n";
				$htmlCode .= "<span class=\"addthis_separator\">|</span> \n";
				$htmlCode .= "<a class=\"addthis_button_preferred_1\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_preferred_2\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_preferred_3\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_preferred_4\"></a>\n";
				$htmlCode .= "</div>\n";
				$htmlCode .= "<script type=\"text/javascript\">var addthis_config = {\"data_track_clickback\":$track_this};</script>\n";
				$htmlCode .= "<script type=\"text/javascript\" src=\"http://s7.addthis.com/js/250/addthis_widget.js#pubid=$pub_id\"></script>\n";
				$htmlCode .= "<!-- AddThis Button END -->";
                break;
			case "toolbox_style2":
                $htmlCode .= "<!-- AddThis Button BEGIN --> \n";
				$htmlCode .= "<div class=\"addthis_toolbox addthis_default_style \"> \n";
				$htmlCode .= "<a href=\"http://www.addthis.com/bookmark.php?v=250&amp;pubid=$pub_id\" class=\"addthis_button_compact\">Share</a> \n";
				$htmlCode .= "</div>\n";
				$htmlCode .= "<script type=\"text/javascript\">var addthis_config = {\"data_track_clickback\":$track_this};</script>\n";
				$htmlCode .= "<script type=\"text/javascript\" src=\"http://s7.addthis.com/js/250/addthis_widget.js#pubid=$pub_id\"></script>\n";
				$htmlCode .= "<!-- AddThis Button END -->";
                break;
			case "toolbox_style3":
                $htmlCode .= "<!-- AddThis Button BEGIN --> \n";
				$htmlCode .= "<div class=\"addthis_toolbox addthis_default_style \"> \n";
				$htmlCode .= "<a class=\"addthis_button_preferred_1\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_preferred_2\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_preferred_3\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_preferred_4\"></a>\n";
				$htmlCode .= "<a class=\"addthis_button_compact\"></a>\n";
				$htmlCode .= "</div>\n";
				$htmlCode .= "<script type=\"text/javascript\">var addthis_config = {\"data_track_clickback\":$track_this};</script>\n";
				$htmlCode .= "<script type=\"text/javascript\" src=\"http://s7.addthis.com/js/250/addthis_widget.js#pubid=$pub_id\"></script>\n";
				$htmlCode .= "<!-- AddThis Button END -->";
                break;
			case "toolbox_style4":
                $htmlCode .= "<!-- AddThis Button BEGIN --> \n";
				$htmlCode .= "<div class=\"addthis_toolbox addthis_default_style addthis_32x32_style\"> \n";
				$htmlCode .= "<a class=\"addthis_button_preferred_1\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_preferred_2\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_preferred_3\"></a> \n";
				$htmlCode .= "<a class=\"addthis_button_preferred_4\"></a>\n";
				$htmlCode .= "<a class=\"addthis_button_compact\"></a>\n";
				$htmlCode .= "</div>\n";
				$htmlCode .= "<script type=\"text/javascript\">var addthis_config = {\"data_track_clickback\":$track_this};</script>\n";
				$htmlCode .= "<script type=\"text/javascript\" src=\"http://s7.addthis.com/js/250/addthis_widget.js#pubid=$pub_id\"></script>\n";
				$htmlCode .= "<!-- AddThis Button END -->";
                break;
			case "button_style1":
                $htmlCode .= "<!-- AddThis Button BEGIN --> \n";
				$htmlCode .= "<a class=\"addthis_button\" href=\"http://www.addthis.com/bookmark.php?v=250&amp;pubid=$pub_id\"><img src=\"http://s7.addthis.com/static/btn/v2/lg-share-en.gif\" width=\"125\" height=\"16\" alt=\"Bookmark and Share\" style=\"border:0\"/></a>\n";
				$htmlCode .= "<script type=\"text/javascript\">var addthis_config = {\"data_track_clickback\":$track_this};</script>\n";
				$htmlCode .= "<script type=\"text/javascript\" src=\"http://s7.addthis.com/js/250/addthis_widget.js#pubid=$pub_id\"></script>\n";
				$htmlCode .= "<!-- AddThis Button END -->";
                break;
			case "button_style2":
                $htmlCode .= "<!-- AddThis Button BEGIN --> \n";
				$htmlCode .= "<a class=\"addthis_button\" href=\"http://www.addthis.com/bookmark.php?v=250&amp;pubid=$pub_id\"><img src=\"http://s7.addthis.com/static/btn/sm-share-en.gif\" width=\"83\" height=\"16\" alt=\"Bookmark and Share\" style=\"border:0\"/></a>\n";
				$htmlCode .= "<script type=\"text/javascript\">var addthis_config = {\"data_track_clickback\":$track_this};</script>\n";
				$htmlCode .= "<script type=\"text/javascript\" src=\"http://s7.addthis.com/js/250/addthis_widget.js#pubid=$pub_id\"></script>\n";
				$htmlCode .= "<!-- AddThis Button END -->";
                break;
            }			
		}
		if ($credit_to_Author) {
			$htmlCode .= "<a href=\"http://dontclickon.com/\" title=\"AddThis All In One Social Network Buttons for Joomla\" target=\"_blank\"><img src=\"http://dontclickon.com/images/power_by_2x2.gif\" border=\"0\"/></a>"; 
		}
		$htmlCode .="</div>";
	
     return $htmlCode; 
	}
	
	private function getPageUrl($obj)
	{
		if (!is_null($obj)) 
		{
			$url = JRoute::_(ContentHelperRoute::getArticleRoute($obj->slug, $obj->catslug, $obj->sectionid));
			$uri = JURI::getInstance();
      		$base  = $uri->toString( array('scheme', 'host', 'port'));
			$url = $base . $url;
			$url = JRoute::_($url, true, 0);
			return $url;
		}
	}
}
?>