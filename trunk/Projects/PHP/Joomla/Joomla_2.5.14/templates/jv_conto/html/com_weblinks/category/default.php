<?php // @version $Id: default.php 11917 2009-05-29 19:37:05Z ian $
defined('_JEXEC') or die('Restricted access');
?>
<div class="jv-rounded-corners2">
	<div class="jv-tc"><div class="jv-tl"><div class="jv-tr"></div></div></div>
	<div class="jv-c"><div class="jv-cl"><div class="jv-cr">
    	
		<?php if ($this->params->get('show_page_title', 1)) : ?>
		<h1 class="componentheading<?php echo $this->escape($this->params->get('pageclass_sfx')); ?>">
			<?php echo $this->escape($this->params->get('page_title')); ?>
		</h1>
		<?php endif; ?>


		<div class="weblinks<?php echo $this->escape($this->params->get('pageclass_sfx')); ?>">

			<?php if ( $this->category->image || $this->category->description) : ?>
			<div class="contentdescription<?php echo $this->escape($this->params->get('pageclass_sfx')); ?>">

				<?php if ($this->category->image) :
					echo $this->category->image;
				endif; ?>

				<?php echo $this->category->description; ?>

				<?php if ($this->category->image) : ?>
				<div class="wrap_image">&nbsp;</div>
				<?php endif; ?>

			</div>
			<?php endif; ?>

			<?php echo $this->loadTemplate('items'); ?>

		</div>

	</div></div></div>
	<div class="jv-bc"><div class="jv-bl"><div class="jv-br"></div></div></div>
</div>