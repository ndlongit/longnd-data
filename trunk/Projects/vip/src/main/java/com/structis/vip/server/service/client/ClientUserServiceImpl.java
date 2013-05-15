package com.structis.vip.server.service.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.structis.vip.client.service.ClientUserService;
import com.structis.vip.server.bean.domain.User;
import com.structis.vip.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.vip.server.core.ManagerCallBack;
import com.structis.vip.server.mapper.ModelBeanMapperIfc;
import com.structis.vip.server.service.domain.DomPerimetreService;
import com.structis.vip.server.service.domain.DomUserService;
import com.structis.vip.shared.exception.ExceptionType;
import com.structis.vip.shared.exception.UserException;
import com.structis.vip.shared.model.PerimetreModel;
import com.structis.vip.shared.model.UserModel;

@Service("clientUserService")
public class ClientUserServiceImpl extends DependencyInjectionRemoteServiceServlet implements ClientUserService {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(ClientUserServiceImpl.class);

	@Autowired
	private DomUserService domUserService;
	@Autowired
	private DomPerimetreService domPerimetreService;

	@Autowired
	ModelBeanMapperIfc modelBeanMapper;

	@SuppressWarnings("unchecked")
	@Override
	public List<UserModel> findUsers() {
		ManagerCallBack callBack = new ManagerCallBack() {
			public Object execute(Object... inputs) {
				return domUserService.findUsers();
			}
		};
		return (List<UserModel>) callManager(callBack);
	}

	@Override
	public List<UserModel> findUsersByEntite(final String entiteId, UserModel userModel) {
		List<UserModel> lstResult = new ArrayList<UserModel>();
		for (User user : domUserService.findUsersByEntite(entiteId)) {
			UserModel usr = findUserById(user.getId());
			usr.setRoles();
			if (!usr.isSuperUser()) {
//				if (usr.isApplicationAdmin()) {
//					if (userModel.isSuperUser()) {
//						lstResult.add(usr);
//					}
//				} else if (usr.isUoAdmin()) {
				if (userModel.isApplicationAdmin()) {
					lstResult.add(usr);
				} else if (!usr.isApplicationAdmin()){					
					if (isChild(usr.getPerimetre(), userModel.getPerimetre())) {
						if (usr.isUoAdmin()) {
							if (userModel.isUoAdmin()) {
								lstResult.add(usr);					
							} 
						}else {
							lstResult.add(usr);
						}
					}
				}				
			}
		}
		return lstResult;
	}
	
	private boolean isChild(PerimetreModel perimetre, PerimetreModel perimetre2) {
		if (perimetre == null || perimetre2 == null) {
			return false;
		}
		return domPerimetreService.isPerimetreChild(perimetre.getPerId(), perimetre2.getPerId());
	}

	@Override
	public UserModel findUserById(int id) {
		User user = domUserService.findUserById(id);
		return (UserModel) modelBeanMapper.map(user);
	}

	@Override
	public UserModel findUserByUserName(String userName, String domain) {
		User user = domUserService.findUserByUserName(userName, domain);
		return (UserModel) modelBeanMapper.map(user);
	}

	@Override
	public UserModel insert(UserModel userModel) throws UserException {
		User user = (User) modelBeanMapper.map(userModel);
		user = domUserService.insert(user);
		return (UserModel) modelBeanMapper.map(user);
	}

	@Override
	public UserModel update(UserModel userModel) throws UserException {
		User user = (User) modelBeanMapper.map(userModel);		
		user = domUserService.update(user);
		return (UserModel) modelBeanMapper.map(user);
	}

	@Override
	public Boolean delete(UserModel userModel) {
		User user = (User) modelBeanMapper.map(userModel);
		return domUserService.deleteWithRoles(user);
	}

	@Override
	public UserModel getAuthorization(final String name, final Integer domainId,
			final String password) throws UserException{
		User user =  domUserService.getAuthorization(name, domainId, password);
		return (UserModel) modelBeanMapper.map(user);
//		if (user == null) {
//			throw new UserException();
//		} else {
//			return (UserModel) modelBeanMapper.map(user);
//		}
						
	}

	@Override
	public ExceptionType changePassword(Integer userId, String value, String newValue) {		
		return domUserService.changePassword(userId, value, newValue);
	}

	@Override
	public UserModel updateNoRoles(UserModel userModel) {
		User user = (User) modelBeanMapper.map(userModel);		
		user = domUserService.updateNoRoles(user);
		return (UserModel) modelBeanMapper.map(user);
	}

	@Override
	public PagingLoadResult<UserModel> findUsersByEntiteRemote(PagingLoadConfig config, String entId, UserModel userModel) {
		List<UserModel> all = new ArrayList<UserModel>();
		all = findUsersByEntite(entId, userModel);
		
		if (config.getSortInfo().getSortField() != null) {
		      final String sortField = config.getSortInfo().getSortField();
		      if (sortField != null) {
		        Collections.sort(all, config.getSortInfo().getSortDir().comparator(new Comparator<UserModel>() {
		          public int compare(UserModel p1, UserModel p2) {
		            if (sortField.equals("userName")) {
		              return p1.getUserName().compareTo(p2.getUserName());
		            } else if (sortField.equals("firstName") && p1.getFirstName() != null && p2.getFirstName() != null) {
		              return p1.getFirstName().compareTo(p2.getFirstName());
		            } else if (sortField.equals("lastName") && p1.getLastName() != null && p2.getLastName() != null) {
		              return p1.getLastName().compareTo(p2.getLastName());
		            }
		            return 0;
		          }
		        }));
		      }
		    }

		
		ArrayList<UserModel> sublist = new ArrayList<UserModel>();
	    int start = config.getOffset();
	    int limit = all.size();
	    if (config.getLimit() > 0) {
	      limit = Math.min(start + config.getLimit(), limit);
	    }
	    for (int i = config.getOffset(); i < limit; i++) {
	      sublist.add(all.get(i));
	    }
	    
	    return new BasePagingLoadResult<UserModel>(sublist, config.getOffset(), all.size());
	}

	@Override
	public List<UserModel> findByPerimetre(final String perId) {
		ManagerCallBack callBack = new ManagerCallBack() {
			public Object execute(Object... inputs) {
				return domUserService.findByPerimetre(perId);
			}
		};
		return (List<UserModel>) callManager(callBack);
	}
}
