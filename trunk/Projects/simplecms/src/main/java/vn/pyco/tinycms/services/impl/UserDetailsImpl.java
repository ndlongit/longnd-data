// [LICENCE-HEADER]
//
package vn.pyco.tinycms.services.impl;

import org.springframework.security.GrantedAuthority;
import org.springframework.security.userdetails.UserDetails;

import vn.pyco.tinycms.model.User;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;
    
    private User _user;
    
    public UserDetailsImpl(User user) {
        _user = user;
    }
    
    /**
     * @return the user
     */
    public User getUser() {
        return _user;
    }

    /*
     * @see org.springframework.security.userdetails.UserDetails#getAuthorities()
     */
    @Override
    public GrantedAuthority[] getAuthorities() {
        return _user.getAuthorities().toArray(new GrantedAuthority[0]);
    }

    /*
     * @see org.springframework.security.userdetails.UserDetails#getPassword()
     */
    @Override
    public String getPassword() {
        return _user.getPassword();
    }

    /*
     * @see org.springframework.security.userdetails.UserDetails#getUsername()
     */
    @Override
    public String getUsername() {
        return _user.getUsername();
    }

    /*
     * @see org.springframework.security.userdetails.UserDetails#isAccountNonExpired()
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /*
     * @see org.springframework.security.userdetails.UserDetails#isAccountNonLocked()
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /*
     * @see org.springframework.security.userdetails.UserDetails#isCredentialsNonExpired()
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /*
     * @see org.springframework.security.userdetails.UserDetails#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        return _user.isActive();
    }

}
