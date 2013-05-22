package com.structis.vip.client.exception;

import com.extjs.gxt.ui.client.widget.MessageBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.rpc.StatusCodeException;
import com.structis.vip.client.message.ErrorMessages;
import com.structis.vip.client.message.Messages;

public class ExceptionMessageMapper {

    private final Messages messages = GWT.create(Messages.class);
    private final ErrorMessages errorMessages = GWT.create(ErrorMessages.class);

    private RegExp regex = RegExp.compile("500 [t|f][0-9][0-9][0-9]");

    public void map(Throwable caught) {
        String header = this.messages.commonErreurInconnu();
        String content = caught.getMessage();
        if (caught instanceof StatusCodeException) {
            StatusCodeException scex = (StatusCodeException) caught;
            if (null != scex.getMessage() && this.regex.test(scex.getMessage())) {

                String code = scex.getMessage().substring(4);

                if (code.startsWith("t")) {
                    header = this.messages.commonTechErreurHeader();
                    content = this.errorMessages.getString(code);
                } else if (code.startsWith("f")) {
                    header = this.messages.commonFonctErreurHeader();
                    content = this.errorMessages.getString(code);
                }
            }
        }

        MessageBox.alert(header, content, null);

    }
}
