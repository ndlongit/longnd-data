<?php
/**
 * JEvents Component for Joomla 1.5.x
 *
 * @version     $Id: view.html.php 3155 2012-01-05 12:01:16Z geraintedwards $
 * @package     JEvents
 * @copyright   Copyright (C) 2008-2009 GWE Systems Ltd
 * @license     GNU/GPLv2, see http://www.gnu.org/licenses/gpl-2.0.html
 * @link        http://www.jevents.net
 */

// Check to ensure this file is included in Joomla!
defined('_JEXEC') or die();

/**
 * HTML View class for the component frontend
 *
 * @static
 */
class DefaultViewICalevent extends JEventsDefaultView 
{
	
	function detail($tpl = null)
	{
		JEVHelper::componentStylesheet($this);

		$document =& JFactory::getDocument();
		// TODO do this properly
		//$document->setTitle(JText::_( 'BROWSER_TITLE' ));
						
		$params = JComponentHelper::getParams(JEV_COM_COMPONENT);
		//$this->assign("introduction", $params->get("intro",""));
		
		$this->data = $this->datamodel->getEventData( $this->evid, $this->jevtype, $this->year, $this->month, $this->day, $this->uid );

		// Dynamic pathway
		if (isset($this->data['row'])){
			$pathway =& JFactory::getApplication()->getPathway();

			$pathway->addItem($this->data['row']->title() ,"");
		}
	}	
}
