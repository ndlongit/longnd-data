package com.structis.fichesst.client.image;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ImageBundle;

@SuppressWarnings({ "deprecation" })
public interface Images extends ImageBundle {
	@Resource("ajouter.png")
	AbstractImagePrototype addIcon();

	@Resource("imprimer.png")
	AbstractImagePrototype printIcon();

	@Resource("sauvegarder.png")
	AbstractImagePrototype saveIcon();

	@Resource("voir.png")
	AbstractImagePrototype searchIcon();

	@Resource("supprimer.png")
	AbstractImagePrototype deleteIcon();
}
