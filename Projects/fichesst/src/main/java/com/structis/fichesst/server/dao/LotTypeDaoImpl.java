package com.structis.fichesst.server.dao;

import com.structis.fichesst.server.bean.domain.LotType;
import com.structis.fichesst.server.dao.LotTypeDao;
import org.springframework.stereotype.Repository;

@Repository
public class LotTypeDaoImpl extends BasicDaoImpl<LotType, Integer> implements LotTypeDao {
}
