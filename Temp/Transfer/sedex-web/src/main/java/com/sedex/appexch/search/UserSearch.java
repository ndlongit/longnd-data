package com.sedex.appexch.search;

import com.sedex.appexch.model.Group;
import com.sedex.appexch.model.User;
import com.sedex.appexch.search.core.AbstractSearch;

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
