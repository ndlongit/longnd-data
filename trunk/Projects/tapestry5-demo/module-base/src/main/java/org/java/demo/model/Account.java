package org.java.demo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.java.demo.model.core.NumericIdEntity;
import org.java.demo.util.AppUtil;

@Entity
@Table(name = "Accounts")
@Inheritance(strategy = InheritanceType.JOINED)
public class Account extends NumericIdEntity {

    public static final String PROP_LOGIN_NAME = "loginName";

    private String loginName;
    private String password;

    private List<Role> roles;

    @Column(unique = true)
    public String getLoginName() {
        return this.loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // @ElementCollection
    // @CollectionTable(name = "account_roles", joinColumns = @JoinColumn(name = "account_id"))
    // public List<String> getRoles() {
    // return roles;
    // }
    //
    // public void setRoles(List<String> roleList) {
    // this.roles = roleList;
    // }

    @ManyToMany
    @JoinTable(name = "account_role", joinColumns = @JoinColumn(name = "account_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Transient
    public static void encryptPassword(Account account, String encryptedPassword) {
        String plainPassword = account.getPassword();
        if (!AppUtil.isNullOrEmpty(plainPassword)) {
            account.setPassword(encryptedPassword);
        }
    }
}
