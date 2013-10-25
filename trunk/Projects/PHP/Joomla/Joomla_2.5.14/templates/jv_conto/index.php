<?php
/**
 * @copyright	Copyright (C) 2008 - 2009 JoomVision.com. All rights reserved.
 * @license		GNU/GPL, see LICENSE.php
 * Joomla! is free software. This version may have been modified pursuant
 * to the GNU General Public License, and as distributed it includes or
 * is derivative of works licensed under the GNU General Public License or
 * other free or open source software licenses.
 * See COPYRIGHT.php for copyright notices and details.
 */

// no direct access
defined( '_JEXEC' ) or die( 'Restricted access' );
include_once (dirname(__FILE__).DS.'libs'.DS.'jv_tools.php');
include_once (dirname(__FILE__).DS.'jv_menus'.DS.'jv.common.php');
include_once (dirname(__FILE__).DS.'libs'.DS.'jv_vars.php');
unset($this->_scripts[$this->baseurl . '/media/system/js/caption.js']);
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="<?php echo $this->language; ?>" lang="<?php echo $this->language; ?>">
<head>
<jdoc:include type="head" />
<?php JHTML::_('behavior.mootools'); ?>
<link rel="stylesheet" href="<?php echo $jvTools->baseurl() ; ?>templates/system/css/system.css" type="text/css" />
<link rel="stylesheet" href="<?php echo $jvTools->baseurl() ; ?>templates/system/css/general.css" type="text/css" />

	<?php if($gzip == "true") : ?>
		<link rel="stylesheet" href="<?php echo $jvTools->templateurl(); ?>css/template.css.php?rtl=<?php if($jvrtl == 'rtl'){echo '1';}else{echo '0';}; ?>&googlefont=<?php if($jvTools->getParam('jv_google_fonts')){echo '1';}else{echo '0';} ?>" type="text/css" />
	<?php else: ?>
		<link rel="stylesheet" href="<?php echo $jvTools->templateurl(); ?>css/default.css" type="text/css" />
		<link rel="stylesheet" href="<?php echo $jvTools->templateurl(); ?>css/template.css" type="text/css" />

		<?php if($jvrtl == 'rtl') : ?>
		<link rel="stylesheet" href="<?php echo $jvTools->templateurl(); ?>css/template_rtl.css" type="text/css" />
		<link rel="stylesheet" href="<?php echo $jvTools->templateurl(); ?>css/typo_rtl.css" type="text/css" />
		<?php else : ?>
		<link rel="stylesheet" href="<?php echo $jvTools->templateurl(); ?>css/typo.css" type="text/css" />
		<?php endif; ?>

		<?php if($jvTools->getParam('jv_google_fonts')) : ?>
		<link rel="stylesheet" href="<?php echo $jvTools->templateurl(); ?>css/googlefonts.css" type="text/css" />
		<?php endif; ?>

	<?php endif; ?>
	<link href="<?php echo $jvTools->parse_jvcolor_cookie($jvcolorstyle); ?>" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
		var baseurl = "<?php echo $jvTools->baseurl() ; ?>";
		var jvpathcolor = '<?php echo $jvTools->templateurl(); ?>css/colors/';
		var tmplurl = '<?php echo $jvTools->templateurl();?>';
		var CurrentFontSize = parseInt('<?php echo $jvTools->getParam('jv_font');?>');
	</script>
	<script type="text/javascript" src="<?php echo $jvTools->templateurl() ?>js/jv.script.js"></script>
	<!--[if lte IE 6]>
	<link rel="stylesheet" href="<?php echo $jvTools->templateurl(); ?>css/ie6.css" type="text/css" />
	<script type="text/javascript" src="<?php echo $jvTools->templateurl() ?>js/ie_png.js"></script>
	<script type="text/javascript">
	window.addEvent ('load', function() {
	   ie_png.fix('.png');
	});
	</script>
	<![endif]-->
	<!--[if lte IE 7]>
	<link rel="stylesheet" href="<?php echo $jvTools->templateurl(); ?>css/ie7.css" type="text/css" />
	<![endif]-->
	<!--[if IE 8]>
	<link rel="stylesheet" href="<?php echo $jvTools->templateurl(); ?>css/ie8.css" type="text/css" />
	<![endif]-->
</head>
<body id="bd" class="fs<?php echo $jvTools->getParam('jv_font'); ?> <?php echo $jvTools->getParam('jv_display'); ?> <?php echo $jvTools->getParam('jv_display_style'); ?> <?php echo $jvrtl; ?>">

	<div id="jv-wrapper">
		<div id="jv-wrapper-inner">
			
			<div id="jv-header"  class="clearfix">
				<div class="jv-wrapper">
					<div id="jv-header-inner">
				   
						<div id="jv-logo">
							<h1><a class="png" href="<?php echo $jvTools->baseurl() ; ?>" title="<?php echo $jvTools->sitename() ; ?>"><span><?php echo $jvTools->sitename() ; ?></span></a></h1>
						</div>
					
						<div id="jv-header-right">
							<div id="jv-headerlink">
								<jdoc:include type="modules" name="top_menu" />
							</div>
							
							<?php if($this->countModules('search')) : ?>
							<div id="jv-search" class="clearfix">
								<jdoc:include type="modules" name="search" style="xhtml"  />
							</div>
							<?php endif; ?>
						</div>							
						
						<div id="jv-mainmenu" class="<?php if($this->countModules('slideshow')) : ?>menu-and-slide<?php else: ?>menu-no-slide<?php endif ?>">
							<div id="jv-mainmenu-left"></div>
							<div id="jv-mainmenu-center">
								<?php $menu->show(); ?>
							</div>
							<div id="jv-mainmenu-right"></div>
						</div>

					</div>
				</div>
			</div>
			
			<?php if($this->countModules('slideshow')) : ?>
			<div id="jv-userwrap1" class="clearfix">
				<div class="jv-wrapper">
					<div id="jv-userwrap1-inner">
						
						<div class="jv-rounded-corners">
							<div class="jv-tc">
								<div class="jv-tl"></div>
								<div class="jv-tr"></div>
							</div>
							<div class="jv-c clearfix"><div class="jv-cl"><div class="jv-cr">
								<jdoc:include type="modules" name="slideshow" />
							</div></div></div>
							<div class="jv-bc clearfix">
								<div class="jv-bl"></div>
								<div class="jv-br"></div>
							</div>
						</div>

					</div>
				</div>
			</div>
			<?php endif; ?>
			
			<?php
			$spotlight = array ('user1','user2','user3','user4');
			$consl = $jvTools->calSpotlight($spotlight,$jvTools->isOP()?100:100,'%');
			if( $consl) :
			?>
			<div id="jv-userwrap2" class="clearfix">
				<div class="jv-wrapper">
					<div id="jv-userwrap2-inner">
					
						<div class="jv-rounded-corners">
							<div class="jv-tc">
								<div class="jv-tl"></div>
								<div class="jv-tr"></div>
							</div>
							<div class="jv-c clearfix">
								<div class="jv-cl">
									<div class="jv-cr">
										<div class="jv-c-inner">
											<?php if($this->countModules('user1')) : ?>
											<div id="jv-user1" class="jv-user jv-box<?php echo $consl['user1']['class']; ?>" style="width: <?php echo $consl['user1']['width']; ?>;">
												<div class="jv-box-inside">
													<jdoc:include type="modules" name="user1" style="jvxhtml2" />
												</div>
											</div>
											<?php endif; ?>
											
											<?php if($this->countModules('user2')) : ?>
											<div id="jv-user2" class="jv-user jv-box<?php echo $consl['user2']['class']; ?>" style="width: <?php echo $consl['user2']['width']; ?>;">
												<div class="jv-box-inside">
													<jdoc:include type="modules" name="user2" style="jvxhtml2" />
												</div>
											</div>
											<?php endif; ?>
											
											<?php if($this->countModules('user3')) : ?>
											<div id="jv-user3" class="jv-user jv-box<?php echo $consl['user3']['class']; ?>" style="width: <?php echo $consl['user3']['width']; ?>;">
												<div class="jv-box-inside">
													<jdoc:include type="modules" name="user3" style="jvxhtml2" />
												</div>
											</div>
											<?php endif; ?>
											
											<?php if($this->countModules('user4')) : ?>
											<div id="jv-user4" class="jv-user jv-box<?php echo $consl['user4']['class']; ?>" style="width: <?php echo $consl['user4']['width']; ?>;">
												<div class="jv-box-inside">
													<jdoc:include type="modules" name="user4" style="jvxhtml2" />
												</div>
											</div>
											<?php endif; ?>
										</div>
									</div>
								</div>
							</div>
							<div class="jv-bc clearfix">
								<div class="jv-bl"></div>
								<div class="jv-br"></div>
							</div>
						</div>

					</div>
				</div>
			</div>
			<?php endif; ?>
			
			<!-- MAINBODY -->
			<div id="jv-mainbody<?php echo $jv_width;?>" class="clearfix">
				<div class="jv-wrapper">
					<div id="jv-mainbody-inner">
						<!-- CONTAINER -->
						<div id="jv-container" class="clearfix">
							<?php if($this->countModules('left')) : ?>
							<div id="jv-left">
								<div id="jv-left-inner">
									<div class="jv-rounded-corners">
										<div class="jv-tc">
											<div class="jv-tl"></div>
											<div class="jv-tr"></div>
										</div>
										<div class="jv-c clearfix">
											<div class="jv-cl">
												<div class="jv-cr">
													<div class="jv-c-inner">
														<jdoc:include type="modules" name="left" style="jvxhtml2" />
													</div>
												</div>
											</div>
										</div>
										<div class="jv-bc clearfix">
											<div class="jv-bl"></div>
											<div class="jv-br"></div>
										</div>
									</div>
								</div>
							</div>
							<?php endif; ?>
							
							<div id="jv-content">
								<div id="jv-maincontent-inset">
									<div class="jv-rounded-corners">
										<div class="jv-tc">
											<div class="jv-tl"></div>
											<div class="jv-tr"></div>
										</div>
										<div class="jv-c clearfix">
											<div class="jv-cl">
												<div class="jv-cr">
													<?php if($this->countModules('breadcrumb')) : ?>
													<div id="jv-breadcrumb">
														<jdoc:include type="modules" name="breadcrumb" />
													</div>
													<?php endif; ?>
													
													<?php if($this->countModules('user5')) : ?>
													<div id="jv-user5" class="clearfix">
														<jdoc:include type="modules" name="user5" style="jvxhtml" />
													</div>
													<?php endif; ?>
													
													<div class="jv-rounded-corners2">
														<div class="jv-tc">
															<div class="jv-tl"></div>
															<div class="jv-tr"></div>
														</div>
														<div class="jv-c clearfix"><div class="jv-cl"><div class="jv-cr">
															<div id="jv-component" class="clearfix">
																<jdoc:include type="message" />
																<jdoc:include type="component" />
															</div>
															
															<?php if($this->countModules('user6')) : ?>
															<div id="jv-user6" class="clearfix">
																<jdoc:include type="modules" name="user6" style="jvxhtml" />
															</div>
															<?php endif; ?>
														</div></div></div>
														<div class="jv-bc clearfix">
															<div class="jv-bl"></div>
															<div class="jv-br"></div>
														</div>
													</div>
													
												</div>
											</div>
										</div>
										<div class="jv-bc clearfix">
											<div class="jv-bl"></div>
											<div class="jv-br"></div>
										</div>
									</div>
								</div>
							</div>
							
						</div>
						<!-- END CONTAINER -->
					</div>
				</div>
			</div>
			<!-- END MAINBODY -->
			<?php
			$spotlight = array ('user7','user8','user9','user10');
			$botsl3 = $jvTools->calSpotlight ($spotlight,$jvTools->isOP()?100:100,'%');
			if( $botsl3 ) :
			?>
			<div id="jv-userwrap4" class="clearfix">
				<div class="jv-wrapper">
					<div id="jv-userwrap4-inner">
					
						<div class="jv-rounded-corners">
							<div class="jv-tc">
								<div class="jv-tl"></div>
								<div class="jv-tr"></div>
							</div>
							<div class="jv-c clearfix"><div class="jv-cl"><div class="jv-cr">
								<?php if($this->countModules('user7')) : ?>
								<div id="jv-user7" class="jv-user jv-box<?php echo $botsl3['user7']['class']; ?>" style="width: <?php echo $botsl3['user7']['width']; ?>;">
									<div class="jv-box-inside">
										<jdoc:include type="modules" name="user7" style="jvxhtml2" />
									</div>
								</div>
								<?php endif; ?>
								
								<?php if($this->countModules('user8')) : ?>
								<div id="jv-user8" class="jv-user jv-box<?php echo $botsl3['user8']['class']; ?>" style="width: <?php echo $botsl3['user8']['width']; ?>;">
									<div class="jv-box-inside">
										<jdoc:include type="modules" name="user8" style="jvxhtml2" />
									</div>
								</div>
								<?php endif; ?>
								
								<?php if($this->countModules('user9')) : ?>
								<div id="jv-user9" class="jv-user jv-box<?php echo $botsl3['user9']['class']; ?>" style="width: <?php echo $botsl3['user9']['width']; ?>;">
									<div class="jv-box-inside">
										<jdoc:include type="modules" name="user9" style="jvxhtml2" />
									</div>
								</div>
								<?php endif; ?>
								
								<?php if($this->countModules('user10')) : ?>
								<div id="jv-user10" class="jv-user jv-box<?php echo $botsl3['user10']['class']; ?>" style="width: <?php echo $botsl3['user10']['width']; ?>;">
									<div class="jv-box-inside">
										<jdoc:include type="modules" name="user10" style="jvxhtml2" />
									</div>
								</div>
								<?php endif; ?>
							</div></div></div>
							<div class="jv-bc clearfix">
								<div class="jv-bl"></div>
								<div class="jv-br"></div>
							</div>
						</div>
						
					</div>
				</div>
			</div>
			<?php endif; ?>
			
			<div id="jv-bottom" class="clearfix">
				<div class="jv-wrapper">
					<div id="jv-bottom-inner">
						<div class="jv-bgt"></div>
						<div class="jv-bgc">
							<?php
							$spotlight = array ('user11','user12','user13','user14');
							$botsl3 = $jvTools->calSpotlight ($spotlight,$jvTools->isOP()?100:100,'%');
							if( $botsl3 ) :
							?>
							<div id="jv-userwrap5" class="clearfix">
								<div id="jv-userwrap5-inner">
									<?php if($this->countModules('user11')) : ?>
									<div id="jv-user11" class="jv-user jv-box<?php echo $botsl3['user11']['class']; ?>" style="width: <?php echo $botsl3['user11']['width']; ?>;">
										<div class="jv-box-inside">
											<jdoc:include type="modules" name="user11" style="jvxhtml" />
										</div>
									</div>
									<?php endif; ?>
									
									<?php if($this->countModules('user12')) : ?>
									<div id="jv-user12" class="jv-user jv-box<?php echo $botsl3['user12']['class']; ?>" style="width: <?php echo $botsl3['user12']['width']; ?>;">
										<div class="jv-box-inside">
											<jdoc:include type="modules" name="user12" style="jvxhtml" />
										</div>
									</div>
									<?php endif; ?>
									
									<?php if($this->countModules('user13')) : ?>
									<div id="jv-user13" class="jv-user jv-box<?php echo $botsl3['user13']['class']; ?>" style="width: <?php echo $botsl3['user13']['width']; ?>;">
										<div class="jv-box-inside">
											<jdoc:include type="modules" name="user13" style="jvxhtml" />
										</div>
									</div>
									<?php endif; ?>
									
									<?php if($this->countModules('user14')) : ?>
									<div id="jv-user14" class="jv-user jv-box<?php echo $botsl3['user14']['class']; ?>" style="width: <?php echo $botsl3['user14']['width']; ?>;">
										<div class="jv-box-inside">
											<jdoc:include type="modules" name="user14" style="jvxhtml" />
										</div>
									</div>
									<?php endif; ?>
								</div>
							</div>
							<?php endif; ?>
						</div>
						<div class="jv-bgb">
							<div class="jv-bgb-c">
								<div class="jv-bgb-t">
									<div class="jv-bgb-b">
										<div id="jv-footer">
											<div id="jv-footer-links"><jdoc:include type="modules" name="footer" /></div>
										</div>
										<div id="jv-copyright">
											<?php if($jvTools->getParam('jv_function')) : ?>
											<div class="change-color"><?php echo $changecolor; ?></div>
											<?php endif; ?>
											<div id="jv-copyright-inner">
												<?php if($jvTools->getParam('jv_footer')) : ?>
												<?php echo $jvTools->getParam('jv_footer_text'); ?>
												<?php else : ?>
												Copyright &copy; 2008 - <?php echo date('Y'); ?> <a href="http://www.zootemplate.com" title="Joomla Templates">Joomla Templates</a>  by <a href="http://www.zootemplate.com" title="ZooTemplate">ZooTemplate.Com</a>. All rights reserved.
												<?php endif; ?>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>


		</div>
	</div>
	
</body>
</html>

