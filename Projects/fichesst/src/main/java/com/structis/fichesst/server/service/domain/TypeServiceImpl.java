package com.structis.fichesst.server.service.domain;

import org.springframework.stereotype.Service;

import com.structis.fichesst.server.bean.domain.Type;
import com.structis.fichesst.server.dao.TypeDao;

@Service
public class TypeServiceImpl extends BasicServiceImpl<Type, Integer, TypeDao> implements TypeService {
}
