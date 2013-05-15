package com.structis.vip.server.service.client;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.structis.vip.client.service.ClientAuthenticationService;
import com.structis.vip.server.bean.domain.User;
import com.structis.vip.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.vip.server.service.domain.DomUserService;
import com.structis.vip.shared.exception.AppException;
import com.structis.vip.shared.exception.AppExceptionType;
import com.structis.vip.shared.exception.TechnicalException;
import com.structis.vip.shared.model.UserModel;

//@Transactional(readOnly = true)
@Service("clientAuthenticationService")
public class ClientAuthenticationServiceImpl extends DependencyInjectionRemoteServiceServlet implements ClientAuthenticationService {
	private static final long serialVersionUID = 1L;

	@Autowired
	private DomUserService domUserService;
	
	@Autowired
	private DozerBeanMapper dozerMapper;
	
	private static final Logger LOGGER = Logger.getLogger(ClientAuthenticationServiceImpl.class);

	@Override
	public UserModel login(String userName, String passWord){
		UserModel info = null;
		// User user = userDao.retrieveUser(action.getLogin());
		User user = domUserService.findUserByUserName(userName, null);
		try {
			if (user != null) {
				if (isValidLogin(passWord, user)) {
					ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
					HttpSession session = attr.getRequest().getSession();
					if (session.getAttribute("login") != null) {
						session.removeAttribute("login");
					}
					session.setAttribute("login", user);
					info = dozerMapper.map(user, UserModel.class);
				} else {
					throw new AppException(AppExceptionType.WRONG_PASS);
				}
			} else {
				throw new AppException(AppExceptionType.USER_UNKNOWN);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			throw new TechnicalException("unknow_exception");
		}

		return info;
	}

	private Boolean isValidLogin(String password, User user) {
		// un comment when use login form
//		if (domUserService.isUserPasses(FieldVerifier.getMd5Pass(password),
//				user.getIdUtilisateur())) {
//			return true;
//		}
//		return false;
		return true;
	}

	@Override
	public UserModel ssoLogin(){

		UserModel info = null;
		User user;
		// check the session user
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		String remoteUser = "";
		if (attr.getRequest().getUserPrincipal() != null) {
			remoteUser = attr.getRequest().getUserPrincipal().getName();
		}
		
		// for testing
		LOGGER.info("===========REMOTE PRINCIPAL: " + remoteUser);
		LOGGER.info("===========REMOTE USER: " + getLogonedUserName(remoteUser));
		LOGGER.info("===========REMOTE DOMAIN: " + getLogonedDomain(remoteUser));
		LOGGER.info("===========REQUEST INFO:" + attr.getRequest().getMethod() + "--" + attr.getRequest().getContextPath());
		
		if (remoteUser != null) {
			user = domUserService.findUserByUserName(getLogonedUserName(remoteUser), getLogonedDomain(remoteUser));
			if (user != null) {
				LOGGER.info("===========REMOTE USER FOUND: " + user);
				session.setAttribute("login", user);
				info = dozerMapper.map(user, UserModel.class);
				return info;
			}
		}
		
		LOGGER.info("===========NOT LOGGED");
		return null;
	}

	@Override
	public void ssoLogout() throws TechnicalException {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		session.removeAttribute("login");
	}

	private String getLogonedUserName(String userName) {
		char userDelimiter = '@';
		if (userName != null) {
			if (userName.indexOf(userDelimiter) != -1) {
				return userName.substring(0, userName.indexOf(userDelimiter));
			} else {
				return userName;
			}
		}
		return userName;
	}

	private String getLogonedDomain(String userName) {
		char userDelimiter = '@';
		if (userName != null) {
			if (userName.lastIndexOf(userDelimiter) != -1) {
				return userName.substring(userName.lastIndexOf(userDelimiter) + 1, userName.length());
			} else {
				return null;
			}
		}
		return null;
	}
}