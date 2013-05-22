package com.structis.vip.client.message;

import com.google.gwt.i18n.client.Constants;

/**
 * Cette interface est pour stocker les messages qui contient {}
 * 
 * @author b.brotosumpeno
 * 
 */
public interface ConstantMessages extends Constants {

    /* ### Les actions */
    @Key("common.pagingdisplaymessages")
    String commonPagingDisplayMessages();

    @Key("common.textfielddisplaymessages")
    String commonTextFieldDisplayMessages();

    @Key("common.fieldnotdecimal")
    String commonFieldNotDecimal();

    @Key("common.negative")
    String commonNegativeMessages();

}
