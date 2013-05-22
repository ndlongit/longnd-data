package com.structis.vip.server.bean.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.structis.vip.server.bean.core.AbstractShowAbleBean;
import com.structis.vip.server.bean.domain.core.Identifiable;

@Entity
@Table(name = "EXC_EXTERN_CONTROL")
public class ExternController extends AbstractShowAbleBean implements Identifiable<Integer> {

    @Id
    @GeneratedValue
    @Column(name = "exc_id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "exc_name")
    private String name;

    @Column(name = "exc_address")
    private String address;

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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    protected void beanToString(StringBuffer sb) {
    }

}
