package com.structis.vip.client.tools;

import java.util.List;

import com.extjs.gxt.ui.client.data.BeanModelTag;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.BaseObservable;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.google.gwt.core.client.GWT;
import com.structis.vip.client.message.Messages;

/**
 * Class "mère" des Vues, permettant de centraliser des mécanismes communs aux
 * vues quelque soit leur type...
 * 
 * @author th.bernard
 * 
 * @param <C>
 */
@SuppressWarnings("rawtypes")
public abstract class AbstractView<C extends LayoutContainer> extends BaseObservable {

	public static final int PROJECT_LEFT_MARGIN = 10;
	public static final int PROJECT_RIGHT_MARGIN = 10;
	public static final int PROJECT_BOTTOM_MARGIN = 10;
	public static final int PROJECT_TOP_MARGIN = 10;
	public static final int PROJECT_PANEL_MARGIN = 5;
	
	private static final Messages messages = GWT.create(Messages.class);	
		
	private boolean init = false;
	private C mainContainer;
	private boolean isShowAble = false;



	/**
	 * méthode d'initialisation de la fenêtre principal
	 * @param datas
	 * @param load
	 * @param clearChild
	 */
	public void init(final List datas, final boolean load, final boolean clearChild) {
		String viewClassName = this.getClass().getName();
		GWT.log("AbstractView.init for view "+viewClassName);
		if (!init) {
			GWT.log(viewClassName+" not initialized, call init");
			init = !init;
			init(mainContainer);
		} else {
			GWT.log(viewClassName+" already initialized");
			if (clearChild) {
				GWT.log(viewClassName+" call clearChild");
				clearChild(mainContainer);
			}
			}
		if(load) {
			GWT.log(viewClassName+".load (from AbstractView)");
			load(mainContainer, datas);
		}
		GWT.log("End of AbstractView.init for view "+viewClassName);
	}

	protected abstract void init(C mainContainer);

	public void addChild(AbstractView<LayoutContainer> view) {
		GWT.log(this.getClass().getName()+".addChildContent("+view.getClass().getName()+")");
		addChildContent(mainContainer, view);
	}

	protected abstract void addChildContent(C mainContainer, AbstractView<LayoutContainer> view);

	protected abstract void clearChild(C mainContainer);

	protected abstract void load(C mainContainer, List datas);

	public void close() {
		mainContainer.removeAll();
		closeChild();
		init = !init;
	}

	protected abstract void closeChild();

	/**
	 * Méthode permtettant de lancer les événements propre
	 * à l'application depuis les vues (Observables) 
	 */
	
	protected Messages getMessages() {
		return messages;
	}

	public boolean isShowAble() {
		return isShowAble;
	}

	public C get() {
		return mainContainer;
	}

	protected static String concatString(String... stringParts) {
		StringBuffer sb = new StringBuffer();
		for (String stringPart : stringParts) {
			sb.append(stringPart);
		}
		return sb.toString();
	}
	
	protected void applyCommonSettings(Grid grid) {
//		grid.getView().setForceFit(true);
//		grid.getView().setAutoFill(true);
		grid.setLoadMask(true);
		grid.setColumnLines(true);
		grid.setStripeRows(true);
		grid.setBorders(true);
	}

	protected void applyCommonSettings(C panel) {
		panel.setLayoutOnChange(true);
		panel.setBorders(false);
	}

	
	protected Label getLabelInstance(String label) {
		Label cmp = new Label(label);
		cmp.setStyleName("legend-Label");
		return cmp;
	}

	
	protected void notifyFailure(Throwable t){
		GWT.log("erreur : ",t);
		
	}
	
	
	/**
	 * Redirige la page en cours vers la nouvelle URL
	 * @param url L'url qui remplacera l'actuelle
	 */
	protected static native void openUrl(final String url)/*-{
		$wnd.location = url;
	}-*/;

	/**
	 * Ouvre dans le navigateur , une nouvelle page 
	 * ou onglet a destination de URL
	 * @param url L'url a ouvrir dans un nouvel 
	 * onglet ou un nouveau navigateur
	 */
	protected static native void openNewTab(final String url)/*-{
		$wnd.open(url);
	}-*/;	

}
