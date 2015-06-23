package org.java.demo.model.base;

import java.util.Date;

public interface Timestampable {

    Date getCreatedDate();

    void setCreatedDate(Date date);

    Date getModifiedDate();

    void setModifiedDate(Date date);
}
