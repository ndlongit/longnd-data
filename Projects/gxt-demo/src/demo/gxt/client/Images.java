package demo.gxt.client;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ImageBundle;
import com.google.gwt.user.client.ui.ImageBundle.Resource;

@SuppressWarnings({ "deprecation", "unused" })
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
