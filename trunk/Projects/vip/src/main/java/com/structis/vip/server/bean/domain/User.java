package com.structis.vip.server.bean.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.structis.vip.server.bean.core.AbstractShowAbleBean;
import com.structis.vip.server.bean.domain.core.Identifiable;

@Entity
@Table(name = "USR_USER")
public class User extends AbstractShowAbleBean implements Identifiable<Integer> {

    @Id
    @GeneratedValue
    @Column(name = "usr_id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "usr_username")
    private String userName;

    @Column(name = "usr_firstname")
    private String firstName;

    @Column(name = "usr_lastname")
    private String lastName;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "ent_id", nullable = false)
    private Entite entite;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "col_id", nullable = true)
    private Collaborateur collaborateur;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "dmn_id", nullable = true)
    private Domain domain;

    @Column(name = "usr_password", nullable = true)
    private String password;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "per_id", nullable = true)
    private Perimetre perimetre;

    @Transient
    private List<UserRole> userRoles;

    @Override
    public Integer getPrimaryKey() {
        return this.getId();
    }

    @Override
    public boolean isPrimaryKeySet() {
        return (this.getId() != null);
    }

    @Override
    public void setPrimaryKey(Integer id) {
        this.setId(id);
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Entite getEntite() {
        return this.entite;
    }

    public void setEntite(Entite entite) {
        this.entite = entite;
    }

    public List<UserRole> getUserRoles() {
        return this.userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public Collaborateur getCollaborateur() {
        return this.collaborateur;
    }

    public void setCollaborateur(Collaborateur collaborateur) {
        this.collaborateur = collaborateur;
    }

    public Domain getDomain() {
        return this.domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Perimetre getPerimetre() {
        return this.perimetre;
    }

    public void setPerimetre(Perimetre perimetre) {
        this.perimetre = perimetre;
    }

    @Override
    protected void beanToString(StringBuffer sb) {
    }
}
