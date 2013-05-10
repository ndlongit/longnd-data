package org.java.demo.dao;

import org.java.demo.dao.core.BasicDaoImpl;
import org.java.demo.model.Account;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoImpl extends BasicDaoImpl<Account, Long> implements AccountDao {

    @Override
    public Account getByLoginName(final String loginName) {
        Account result = super.findUniqueByProperty(Account.PROP_LOGIN_NAME, loginName);
        return result;
    }
}
