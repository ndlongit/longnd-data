package com.structis.fichesst.server.service.domain;
import com.structis.fichesst.server.bean.domain.RefDdeAgrement;
import com.structis.fichesst.server.dao.RefDdeAgrementDao;
import com.structis.fichesst.server.service.domain.RefDdeAgrementService;
import com.structis.fichesst.server.service.domain.BasicServiceImpl;
import org.springframework.stereotype.Service;
@Service
public class RefDdeAgrementServiceImpl extends BasicServiceImpl<RefDdeAgrement, Integer, RefDdeAgrementDao> implements RefDdeAgrementService {}
