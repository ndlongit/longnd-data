package org.java.demo.model;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.java.demo.model.core.SimpleEntity;

@Entity
@Table(name = "Groups")
@AttributeOverride(name = SimpleEntity.PROP_NAME, column = @Column(name = "group_name"))
public class Group extends SimpleEntity {

    private List<User> users;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = User.PROP_GROUPS, targetEntity = User.class)
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
