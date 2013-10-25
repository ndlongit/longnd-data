<?php // no direct access
defined('_JEXEC') or die('Restricted access'); ?>
<div class="breadcrumbs pathway">
	<ul>
	<?php for ($i = 0; $i < $count; $i ++) :

		// If not the last item in the breadcrumbs add the separator
		if ($i < $count -1) {
			if(!empty($list[$i]->link)) {
				echo '<li class="item'.$i.'"><a href="'.$list[$i]->link.'" class="pathway"><span>'.$list[$i]->name.'</span></a></li>';
			} else {
				//echo $list[$i]->name;
				echo '<li class="item'.$i.'"><span>'.$list[$i]->name.'</span></li>';
			}
			echo '<li class="separator"></li>';
		}  else if ($params->get('showLast', 1)) { // when $i == $count -1 and 'showLast' is true
			//echo $list[$i]->name;
			echo '<li class="item'.$i.'"><span>'.$list[$i]->name.'</span></li>';
		}
	endfor; ?>
	</ul>
</div>
