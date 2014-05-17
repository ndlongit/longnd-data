package org.java.demo.dao.core;

import java.io.Serializable;

import org.java.demo.dao.core.jpa.GenericJpaDao;
import org.java.demo.model.core.BasicEntity;

public abstract class AbstractDao<T extends BasicEntity<?>, ID extends Serializable> extends GenericJpaDao<T, ID> implements BasicDao<T, ID> {
}
