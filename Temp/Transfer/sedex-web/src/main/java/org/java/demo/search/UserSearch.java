package org.java.demo.search;

import org.java.demo.model.Group;
import org.java.demo.model.User;
import org.java.demo.search.core.AbstractSearch;

public class UserSearch extends AbstractSearch {

    private User user;
    private Group group;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public UserSearch() {
        this(new User(), new Group());
    }

    public UserSearch(User user, Group group) {
        this.user = user;
        this.group = group;
    }
}
