package org.java.demo.service;

import org.java.demo.dao.AccountDao;
import org.java.demo.model.Account;
import org.java.demo.service.core.BasicServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl extends BasicServiceImpl<Account, Long, AccountDao> implements AccountService {

    @Override
    public Account getByLoginName(String loginName) {
        Account account = dao.getByLoginName(loginName);
        return account;
    }
}
