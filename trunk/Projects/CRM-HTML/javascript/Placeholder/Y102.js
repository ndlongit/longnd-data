/**
* JavaScript Document
* Placeholder.js
*/

$(function(){
	$(window).load(function(){
		$('input[type=text],input[type=password],textarea').each(function(){
			var thisTitle = $(this).attr('title');
			if(thisTitle && !(thisTitle === '')){
				$(this).wrapAll('<span style="text-align:left;display:inline-block;position:relative;"></span>');
				$(this).parent('span').append('<span class="placeholder">' + thisTitle + '</span>');
				$('.placeholder').css({
					top:'4px',
					left:'5px',
					fontSize:'90%',
					lineHeight:'120%',
					textAlign:'left',
					color:'#a1a1a1',
					overflow:'hidden',
					position:'absolute',
					zIndex:'99'
				}).click(function(){
					$(this).prev().focus();
				});

				$(this).focus(function(){
					$(this).next('span').css({display:'none'});
				});

				$(this).blur(function(){
					var thisVal = $(this).val();
					if(thisVal === ''){
						$(this).next('span').css({display:'inline-block'});
					} else {
						$(this).next('span').css({display:'none'});
					}
				});

				var thisVal = $(this).val();
				if(thisVal === ''){
					$(this).next('span').css({display:'inline-block'});
				} else {
					$(this).next('span').css({display:'none'});
				}
			}
		});
	});
});
