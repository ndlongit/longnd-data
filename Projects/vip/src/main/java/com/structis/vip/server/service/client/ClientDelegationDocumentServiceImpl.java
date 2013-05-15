package com.structis.vip.server.service.client;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.structis.vip.client.service.ClientDelegationDocumentService;
import com.structis.vip.server.bean.domain.DelegationDocument;
import com.structis.vip.server.core.Constants;
import com.structis.vip.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.vip.server.core.ManagerCallBack;
import com.structis.vip.server.mapper.ModelBeanMapperIfc;
import com.structis.vip.server.service.domain.DomDelegationDocumentService;
import com.structis.vip.server.util.CatalinaPropertiesUtil;
import com.structis.vip.shared.model.DelegationDocumentModel;

@Service("clientDelegationDocumentService")
public class ClientDelegationDocumentServiceImpl extends DependencyInjectionRemoteServiceServlet implements
		ClientDelegationDocumentService {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(ClientDelegationDocumentServiceImpl.class);

	@Autowired
	private DomDelegationDocumentService domDelegationDocumentService;

	@Autowired
	ModelBeanMapperIfc modelBeanMapper;

	@SuppressWarnings("unchecked")
	@Override
	public List<DelegationDocumentModel> getDelegationDocuments(final Integer delegationId) {
		ManagerCallBack callBack = new ManagerCallBack() {
			public Object execute(Object... inputs) {
				return domDelegationDocumentService.getDelegationDocuments(delegationId);
			}
		};
		return (List<DelegationDocumentModel>) callManager(callBack);
	}

	@Override
	public DelegationDocumentModel insert(DelegationDocumentModel delegationDocument) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		ServletContext context = attr.getRequest().getSession().getServletContext();
		String pathContext = context.getRealPath(File.separator);
		
		DelegationDocument dm = (DelegationDocument) modelBeanMapper.map(delegationDocument);
		dm = domDelegationDocumentService.insert(dm, CatalinaPropertiesUtil.getVipDirectory(pathContext));
		return (DelegationDocumentModel) modelBeanMapper.map(dm);
	}

	@Override
	public Boolean delete(DelegationDocumentModel delegationModel) {
		DelegationDocument dm = (DelegationDocument) modelBeanMapper.map(delegationModel);
		domDelegationDocumentService.delete(dm);
		deleteFile(delegationModel);
		return true;
	}

	public Boolean deleteFile(DelegationDocumentModel model) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		ServletContext context = attr.getRequest().getSession().getServletContext();
		String pathContext = context.getRealPath(File.separator);

		File f = new File(CatalinaPropertiesUtil.getVipDirectory(pathContext) + Constants.DELEGATION_DOCUMENT_FILE_PATH + "/"
				+ model.getFileName());
		LOGGER.info("DELETE OTHER DOCUMENT FILE PATH: " + f.getAbsolutePath());
		if (f.exists()) {
			LOGGER.info("OTHER DOCUMENT FILE EXISTS");
			return f.delete();
		} else {
			return true;
		}
	}
}