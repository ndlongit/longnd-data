window.addEvent('load', function(){

	var StyleCookie = new Hash.Cookie('JVContoStyleCookieSite');
	var settings = { colors: '' };
	var style_1, style_2;
	//new Asset.css(StyleCookie.get('colors'));

	/* Style 1 */
	if($('jvcolor1')){$('jvcolor1').addEvent('click', function(e) {
		e = new Event(e).stop();
		if (style_1) style_1.remove();
		new Asset.css(jvpathcolor + 'lightblue.css', {id: 'lightblue'});
		style_1 = $('lightblue');
		settings['colors'] = jvpathcolor + 'lightblue.css';
		StyleCookie.empty();
		StyleCookie.extend(settings);
	});}

	/* Style 2 */
	if($('jvcolor2')){$('jvcolor2').addEvent('click', function(e) {
		e = new Event(e).stop();
		if (style_2) style_2.remove();
		new Asset.css(jvpathcolor + 'grey.css', {id: 'grey'});
		style_2 = $('grey');
		settings['grey'] = jvpathcolor + 'grey.css';
		StyleCookie.empty();
		StyleCookie.extend(settings);
	});}

});
