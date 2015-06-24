package com.sedex.appexch.dao.base;

import java.io.Serializable;

import com.sedex.appexch.dao.base.jpa.GenericJpaDao;
import com.sedex.appexch.model.base.BasicEntity;

public abstract class AbstractDao<T extends BasicEntity<?>, ID extends Serializable> extends GenericJpaDao<T, ID> implements BasicDao<T, ID> {
}
