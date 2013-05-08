package com.structis.fichesst.server.bean.domain.core;

import java.util.Date;

public interface Timestampable {
	void setModifiedDate(Date date);

	Date getModifiedDate();
}
