package com.structis.fichesst.client.service;

import java.util.List;

import com.structis.fichesst.shared.dto.SimpleDto;
import com.google.gwt.user.client.rpc.AsyncCallback;
public interface ClientRefTypeBudjServiceAsync{
	void findAll(AsyncCallback<List<SimpleDto>> callback);
}
