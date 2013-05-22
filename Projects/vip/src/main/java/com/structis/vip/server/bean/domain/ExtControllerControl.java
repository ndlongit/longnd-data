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
@Table(name = "CEC_EXTCONTROLER_CONTROL")
public class ExtControllerControl extends AbstractShowAbleBean implements Identifiable<Integer> {

    @Id
    @GeneratedValue
    @Column(name = "cec_id", unique = true, nullable = false)
    private Integer id;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "ctl_id", nullable = false)
    private Control control;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "exc_id", nullable = false)
    private ExternController externalController;

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

    public Control getControl() {
        return this.control;
    }

    public void setControl(Control control) {
        this.control = control;
    }

    public ExternController getExternalController() {
        return this.externalController;
    }

    public void setExternalController(ExternController externalController) {
        this.externalController = externalController;
    }

    @Override
    protected void beanToString(StringBuffer sb) {
    }

}
