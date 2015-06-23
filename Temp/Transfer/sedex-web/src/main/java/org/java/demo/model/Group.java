package org.java.demo.model;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.java.demo.model.core.Account;
import org.java.demo.model.core.SimpleEntity;

@Entity
@Table(name = "Groups")
@AttributeOverride(name = SimpleEntity.PROP_NAME, column = @Column(name = "group_name", nullable = false, unique = true))
public class Group extends SimpleEntity {

    private List<Account> accounts;

    public Group() {
        this(null);
    }

    public Group(Long id) {
        super.setId(id);
    }

    @ManyToMany(targetEntity = Account.class, mappedBy = Account.PROP_GROUPS, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
