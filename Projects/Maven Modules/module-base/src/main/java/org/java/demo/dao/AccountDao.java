package org.java.demo.dao;

import org.java.demo.dao.core.BasicDao;
import org.java.demo.model.Account;

public interface AccountDao extends BasicDao<Account, Long> {

    Account getByLoginName(String loginName);
}
