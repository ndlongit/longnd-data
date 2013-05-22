package com.structis.vip.client.widget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HTML;
import com.structis.vip.client.navigation.Action;
import com.structis.vip.client.navigation.NavigationFactory;
import com.structis.vip.client.navigation.NavigationService;

/**
 * HTML Widget qui contient une action de navigation qui fait lorsqu'on clique le HTML, l'�cran principal se dirige vers le composant qui correspond �
 * l'action
 * 
 * @author b.brotosumpeno
 * 
 */
public class ActionHTML extends HTML {

    private final static String CSS_ACTION_HTML = "gwt-ActionHTML";

    private NavigationService navigation = NavigationFactory.getNavigation();

    /**
     * Constructeur ActionHTML
     * 
     * @param html
     *            � afficher
     * @param action
     *            � diriger
     */
    public ActionHTML(String html, final Action action) {
        super(html);
        this.getElement().setId(action.getLabel());
        this.setStyleName(CSS_ACTION_HTML);
        this.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                ActionHTML.this.navigation.goToEcran(action);
            }
        });
    }

}
