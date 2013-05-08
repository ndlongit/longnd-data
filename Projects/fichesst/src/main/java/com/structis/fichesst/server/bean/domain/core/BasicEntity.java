package com.structis.fichesst.server.bean.domain.core;

import java.io.Serializable;

public interface BasicEntity<ID extends Serializable> extends Serializable {
	
	//Properties names
	public static final String PROP_ID = "id";

	public static final String DEFAULT_ID_REF_SITRAVAUX = "abc";

	public ID getId();

	public void setId(ID id);
}
