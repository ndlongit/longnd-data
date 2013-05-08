package com.structis.fichesst.client.exception;

import com.extjs.gxt.ui.client.widget.MessageBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.rpc.StatusCodeException;
import com.structis.fichesst.client.message.ErrorMessages;
import com.structis.fichesst.client.message.Messages;
import com.structis.fichesst.shared.exception.FunctionalException;
import com.structis.fichesst.shared.exception.TechnicalException;

public class ExceptionMessageMapper {

	private final Messages messages = GWT.create(Messages.class);

	private final ErrorMessages errorMessages = GWT.create(ErrorMessages.class);

	private RegExp regex = RegExp.compile("500 [t|f][0-9][0-9][0-9]");

	public void map(Throwable caught) {
		String header = messages.commonErreurInconnu();
		String content = caught.getMessage();
		if( caught instanceof StatusCodeException ) {
			StatusCodeException scex = (StatusCodeException) caught;
			if( null != scex.getMessage() && regex.test(scex.getMessage()) ) {

				String code = scex.getMessage().substring(4);

				if( code.startsWith("t") ) {
					header = messages.commonTechErreurHeader();
					content = errorMessages.getString(code);
				}
				else if( code.startsWith("f") ) {
					header = messages.commonFonctErreurHeader();
					content = errorMessages.getString(code);
				}
			}
		}
		else if( caught instanceof FunctionalException ) {
			FunctionalException f = (FunctionalException) caught;
			if( f.getCode() != null ) {
				header = messages.commonTechErreurHeader();
				content = errorMessages.getString(f.getCode());
			}
		}
		else if( caught instanceof TechnicalException ) {
			TechnicalException f = (TechnicalException) caught;
			if( f.getCode() != null ) {
				header = messages.commonTechErreurHeader();
				content = errorMessages.getString(f.getCode());
			}
		}
		MessageBox.alert(header, content, null);

	}
}
