package com.sedex.appexch.util.listener;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

public class MyEntityListener {

    @PrePersist
    void onPrePersist(Object o) {
        System.out.println(o);
    }

    @PostPersist
    void onPostPersist(Object o) {
        System.out.println(o);
    }

    @PostLoad
    void onPostLoad(Object o) {
        System.out.println(o);
    }

    @PreUpdate
    void onPreUpdate(Object o) {
        System.out.println(o);
    }

    @PostUpdate
    void onPostUpdate(Object o) {
        System.out.println(o);
    }

    @PreRemove
    void onPreRemove(Object o) {
        System.out.println(o);
    }

    @PostRemove
    void onPostRemove(Object o) {
        System.out.println(o);
    }
}
