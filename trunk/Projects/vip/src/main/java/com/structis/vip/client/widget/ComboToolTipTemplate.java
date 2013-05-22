package com.structis.vip.client.widget;

public class ComboToolTipTemplate {

    public static native String getComboTemplate() /*-{ 
                                                   return  [ 
                                                   '<tpl for=".">', 
                                                   '<div class="x-combo-list-item" title="{libelle}" >{libelle}</div>', 
                                                   '</tpl>' 
                                                   ].join(""); 
                                                   }-*/;

    public static native String getComboLabelUniteTemplate() /*-{ 
                                                             return  [ 
                                                             '<tpl for=".">',
                                                             '<tpl if="dummy">',
                                                             '<div class="x-combo-list-item">&nbsp;</div>',
                                                             '</tpl>',
                                                             '<tpl if="!dummy && active">',
                                                             '<div class="x-combo-list-item" title="{libelle} ({unite})" >{libelle} ({unite})</div>',
                                                             '</tpl>',
                                                             '<tpl if="!dummy && !active">',
                                                             '',
                                                             '</tpl>',    
                                                             '</tpl>' 
                                                             ].join(""); 
                                                             }-*/;
}
