package com.structis.fichesst.server.dao;

import org.springframework.stereotype.Repository;

import com.structis.fichesst.server.bean.domain.RefTypeBudjConf;
import com.structis.fichesst.server.service.domain.BasicServiceImpl;
import com.structis.fichesst.shared.exception.DataConstraintException;
@Repository
public class RefTypeBudjDaoImpl extends BasicDaoImpl<RefTypeBudjConf, Integer> implements
		RefTypeBudjDao {

}
