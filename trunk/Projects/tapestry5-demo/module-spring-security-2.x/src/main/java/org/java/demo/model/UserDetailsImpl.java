package org.java.demo.model;

import java.util.List;

import org.java.demo.model.Account;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;

    private Account account = new Account();
    private GrantedAuthority[] grantedAuthorities;

    public UserDetailsImpl(Account account) {
        if (account != null) {
            this.account = account;
             List<Role> roles = account.getRoles();
            grantedAuthorities = new GrantedAuthority[roles.size()];
            for (int i = 0; i < roles.size(); i++) {
                grantedAuthorities[i] = new GrantedAuthorityImpl(roles.get(i).getValue());
            }
        }
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getLoginName();
    }

    @Override
    public GrantedAuthority[] getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return getUsername();
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
