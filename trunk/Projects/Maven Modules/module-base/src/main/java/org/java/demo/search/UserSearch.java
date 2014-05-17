package org.java.demo.search;

import org.java.demo.model.User;
import org.java.demo.search.core.AbstractSearch;

public class UserSearch extends AbstractSearch<User> {

    public UserSearch() {
        this(null);
    }

    public UserSearch(User model) {
        super(model);
    }
}
