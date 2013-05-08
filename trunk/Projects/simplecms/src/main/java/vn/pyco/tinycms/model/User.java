// [LICENCE-HEADER]
//
package vn.pyco.tinycms.model;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import vn.pyco.commons.model.impl.StatefulEntity;
import vn.pyco.tinycms.utils.CacheConstants;
import vn.pyco.tinycms.utils.EntityConstants;

/**
 * <Briefly describing the purpose of the class/interface...>
 * 
 */
@Entity
@Table(name=EntityConstants.TBL_USERS)
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE, region=CacheConstants.REGION_CONTENTS)
public class User extends StatefulEntity implements Comparable<User> {
    private static final long serialVersionUID = 1L;
    
    public static final String PROP_USERNAME = "username";
    public static final String PROP_PASSWORD = "password";
    public static final String PROP_FIRST_NAME = "firstName";
    public static final String PROP_LAST_NAME = "lastName";
    public static final String PROP_EMAIL = "email";
    public static final String PROP_PHONE_NUMBER = "fontNumber";
    
    public enum UserType {
        SA,
        BO,
        FO,
        NA
    }
    
    private String _username;
    private String _password;
    private String _firstName;
    private String _lastName;
    private String _email;
    private String _phoneNumber;
    private UserType _type;
    private List<Authority> _authorities;
    
    public User() {
        this(UserType.NA);
    }
    
    public User(UserType type) {
        _type = type;
    }
    
    /**
     * @return the username
     */
    @Basic
    @Column(name="USERNAME", length=24, unique=true, nullable=false)
    public String getUsername() {
        return _username;
    }
    
    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        _username = username;
    }
    
    /**
     * @return the password
     */
    @Basic
    @Column(name="PASSWORD", length=32, nullable=true)
    public String getPassword() {
        return _password;
    }
    
    /**
     * @param password the password to set
     */
    @NonVisual
    public void setPassword(String password) {
        _password = password;
    }
    
    /**
     * @return the firstName
     */
    @Basic
    @Column(name="FIRST_NAME", length=16, nullable=true)
    public String getFirstName() {
        return _firstName;
    }
    
    /**
     * @param firstName the firstName to set
     */
    @Validate("required")
    public void setFirstName(String firstName) {
        _firstName = firstName;
    }
    
    /**
     * @return the lastName
     */
    @Basic
    @Column(name="LAST_NAME", length=16, nullable=true)
    public String getLastName() {
        return _lastName;
    }
    
    /**
     * @param lastName the lastName to set
     */
    @Validate("required")
    public void setLastName(String lastName) {
        _lastName = lastName;
    }
    
    @Transient
    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }
    
    /**
     * @return the email
     */
    @Basic
    @Column(name="EMAIL", length=24, nullable=false)
    public String getEmail() {
        return _email;
    }
    
    /**
     * @param email the email to set
     */
    @Validate("required,email")
    public void setEmail(String email) {
        _email = email;
    }
    
    /**
     * @return the phoneNumber
     */
    @Basic
    @Column(name="PHONE_NUMBER", length=16, nullable=true)    
    public String getPhoneNumber() {
        return _phoneNumber;
    }
    
    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        _phoneNumber = phoneNumber;
    }
    
    /**
     * @return the type
     */
    @Enumerated(EnumType.STRING)
    @Column(name="TYPE", nullable=false)
    public UserType getType() {
        return _type;
    }
    
    /**
     * @param type the type to set
     */
    @Validate("required")
    public void setType(UserType type) {
        _type = type;
    }
    
    public void setType(String type) {
        _type = Enum.valueOf(UserType.class, type);
    }
    
    /**
     * @return the authorities
     */
    @ManyToMany(targetEntity=Authority.class, fetch=FetchType.LAZY)
    @JoinTable(name=EntityConstants.TBL_USER_AUTHORITIES, 
                                    joinColumns={@JoinColumn(name="USER_ID")}, 
                                    inverseJoinColumns={@JoinColumn(name="AUTHORITY_ID")})
    public List<Authority> getAuthorities() {
        return _authorities;
    }
    
    /**
     * @param authorities the authorities to set
     */
    public void setAuthorities(List<Authority> authorities) {
        _authorities = authorities;
    }
    
    @Transient
    public boolean isSA() {
        return _type == UserType.SA;
    }
    
    @Transient
    public boolean isBO() {
        return _type == UserType.BO;
    }
    
    @Transient
    public boolean isFO() {
        return _type == UserType.FO;
    }
    
    /*
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(User user) {
        return getFullName().compareToIgnoreCase(user.getFullName());
    }
    
    /*
     * @see vn.pyco.commons.model.impl.StatefulEntity#toString()
     */
    @Override
    public String toString() {
        return String.format("User{id: %s, fullName: %s, email: %s}", getId(), getFullName(), getEmail());
    }
}
