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
@Table(name = "COT_COLLABORATEUR_TYPE")
public class CollaborateurType extends AbstractShowAbleBean implements Identifiable<Integer> {

    @Id
    @GeneratedValue
    @Column(name = "cot_id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "cot_name")
    private String name;

    @Column(name = "[cot_description]")
    private String description;

    // @Column(name = "cot_group")
    // private Integer group;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "cot_group", nullable = false)
    private DelegantTypeGroup group;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "ent_id", nullable = false)
    private Entite entite = new Entite();

    @Override
    public Integer getPrimaryKey() {
        return this.getId();
    }

    @Override
    public void setPrimaryKey(Integer id) {
        this.setId(id);
    }

    @Override
    public boolean isPrimaryKeySet() {
        return (this.getId() != null);
    }

    @Override
    protected void beanToString(StringBuffer sb) {
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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Entite getEntite() {
        return this.entite;
    }

    public void setEntite(Entite entite) {
        this.entite = entite;
    }

    // public Integer getGroup() {
    // return group;
    // }
    //
    // public void setGroup(Integer group) {
    // this.group = group;
    // }
    public DelegantTypeGroup getGroup() {
        return this.group;
    }

    public void setGroup(DelegantTypeGroup group) {
        this.group = group;
    }

    public String getGroupName() {
        return this.group.getName() + " (" + this.name + ")";
    }

}
