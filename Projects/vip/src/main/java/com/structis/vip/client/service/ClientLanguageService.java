package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.structis.vip.shared.exception.EntiteJuridiqueException;
import com.structis.vip.shared.exception.LanguageException;
import com.structis.vip.shared.model.LanguageModel;

@RemoteServiceRelativePath("springGwtServices/clientLanguageService")
public interface ClientLanguageService extends RemoteService {
	List<LanguageModel> getLanguages();
	LanguageModel findById(Integer languageId);
	
	Boolean delete(LanguageModel model) throws LanguageException;	
	LanguageModel insert(LanguageModel model);
	LanguageModel update(LanguageModel model);
}
