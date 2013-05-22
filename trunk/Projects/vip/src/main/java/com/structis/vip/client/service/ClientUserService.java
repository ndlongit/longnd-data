package com.structis.vip.client.service;

import java.util.List;

import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.structis.vip.shared.exception.ExceptionType;
import com.structis.vip.shared.exception.UserException;
import com.structis.vip.shared.model.UserModel;

@RemoteServiceRelativePath("springGwtServices/clientUserService")
public interface ClientUserService extends RemoteService {

    List<UserModel> findUsers();

    List<UserModel> findUsersByEntite(String entiteId, UserModel userModel);

    UserModel findUserById(final int id);

    UserModel findUserByUserName(final String userName, String domain);

    UserModel insert(UserModel userModel) throws UserException;

    UserModel update(UserModel userModel) throws UserException;

    UserModel updateNoRoles(UserModel userModel);

    Boolean delete(UserModel userModel);

    UserModel getAuthorization(String name, Integer domainId, String password);

    ExceptionType changePassword(Integer userId, String value, String newValue);

    PagingLoadResult<UserModel> findUsersByEntiteRemote(PagingLoadConfig config, String entId, UserModel userModel);

    List<UserModel> findByPerimetre(String perId);
}
