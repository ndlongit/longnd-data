package com.mkyong.common.controller;

import java.util.Formatter;
import java.util.Locale;

public class Account {

    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account() {
        this(1, "Account01");
    }

    public Account(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb, Locale.US);
        formatter.format("[Id=%d; name=%s]", id, name);

        String s = formatter.toString();
        formatter.close();

        return s;
//        return super.toString();
    }
}
