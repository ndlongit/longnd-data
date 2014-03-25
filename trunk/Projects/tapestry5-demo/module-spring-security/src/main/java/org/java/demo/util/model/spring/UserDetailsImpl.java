package org.java.demo.util.model.spring;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.java.demo.model.Account;
import org.java.demo.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    private final boolean accountNonExpired = true;
    private final boolean accountNonLocked = true;
    private final boolean credentialsNonExpired = true;

    private Account account;
    private List<GrantedAuthority> grantedAuthorities;

    public UserDetailsImpl(Account account) {
        this.account = new Account();
        if (account != null) {
            this.account = account;
            List<Role> roles = account.getRoles();
            grantedAuthorities = new ArrayList<GrantedAuthority>();
            for (int i = 0; i < roles.size(); i++) {
                grantedAuthorities.add(new SimpleGrantedAuthority(roles.get(i).getCode()));
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
    public Collection<? extends GrantedAuthority> getAuthorities() {
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return getUsername();
    }
}
