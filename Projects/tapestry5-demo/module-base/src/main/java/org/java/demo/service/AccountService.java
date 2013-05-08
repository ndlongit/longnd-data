package org.java.demo.service;

import org.java.demo.model.Account;
import org.java.demo.service.core.BasicService;

public interface AccountService extends BasicService<Account, Long> {

	Account getByLoginName(String loginName);

}
