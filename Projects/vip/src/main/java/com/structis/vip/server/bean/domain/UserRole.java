package com.structis.vip.server.bean.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.structis.vip.server.bean.core.AbstractShowAbleBean;
import com.structis.vip.server.bean.domain.core.Identifiable;

@Entity
@Table(name = "URO_USER_ROLE")
public class UserRole extends AbstractShowAbleBean implements Identifiable<Integer> {

    @Id
    @GeneratedValue
    @Column(name = "uro_id", unique = true, nullable = false)
    private Integer id;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "usr_id", nullable = false)
    private User user;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "rol_id", nullable = false)
    private Role role;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "per_id", nullable = true)
    private Perimetre perimetre;

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

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
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
