<style type="text/css">
/* custom template width */
/*
.container
{
width: <?php echo $this->params->get('customTemplateWidth');?> !important;
max-width: <?php echo $this->params->get('customTemplateWidth');?> !important;
}
*/
/* start custom color */
body
{
 font-family:<?php echo $FONTTYPE_P ?>;
}
</style>


<!-- START CSS -->
<!-- ################ -->
<style type="text/css">
/* BODY FONT SIZE */
body
{
font-size: <?php echo ($body_fontsize); ?>
}
body.site
{
background-color: <?php echo $this->params->get('templateBackgroundColor');?>
}
/* EXTRA BODY */
body.site
{
background-image: url(<?php echo $this->baseurl ?>/templates/<?php echo $this->template ?>/images/backgrounds/<?php echo $body_bg_image_default ?>.png) !important;
}
<?php if($this->params->get('body_bg_image_file') == '') : ?>
body.site
{
background-image: url(<?php echo $this->baseurl ?>/templates/<?php echo $this->template ?>/images/backgrounds/<?php echo $body_bg_image_default ?>.png) !important;
}

<?php elseif($this->params->get('body_bg_image_file') != '') : ?>
body.site
{
background-image: url(<?php echo $this->baseurl ?>/<?php echo $body_bg_image_file; ?>) !important;
}
<?php endif; ?>

/* start horizontal navigation */
#hor_nav ul li a
{
color: <?php echo ($hornav_font_color); ?>
}
#hor_nav LI.active A
{
background-color: <?php echo $this->params->get('templateColor');?> !important;
}
#hor_nav LI A:hover, #hor_nav LI:hover A, #hor_nav LI.sfHover A
{
background-color: <?php echo $this->params->get('templateColor');?> !important;
}
#hor_nav LI LI A, #hor_nav LI LI.active A, #hor_nav LI LI A:hover
{
background-color: <?php echo $this->params->get('templateColor');?> !important;
}
/* hor nav border color */
#hor_nav LI A
{
border-right: 1px solid <?php echo ($hornav_border_color); ?>
}
#hor_nav LI LI A, #hor_nav LI LI.active A, #hor_nav LI LI A:hover
{
border-top: 1px solid <?php echo $this->params->get('hornav_sub_border_color');?> !important;
border-right: 1px solid <?php echo $this->params->get('hornav_sub_border_color');?> !important;
}
</style>
<!-- END CSS -->
<!-- ################ -->

<!-- START IE -->
<!-- ################ -->

<!--[if lt IE 9]>
<script src="<?php echo $this->baseurl ?>/media/jui/js/html5.js"></script>
<![endif]-->

<!-- END IE -->
<!-- ################ -->

