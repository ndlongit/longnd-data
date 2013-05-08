package com.structis.fichesst.server.service.domain;
import com.structis.fichesst.server.bean.domain.RefDecenale;
import com.structis.fichesst.server.dao.RefDecenaleDao;
import com.structis.fichesst.server.service.domain.RefDecenaleService;
import com.structis.fichesst.server.service.domain.BasicServiceImpl;
import org.springframework.stereotype.Service;
@Service
public class RefDecenaleServiceImpl extends BasicServiceImpl<RefDecenale, Integer, RefDecenaleDao> implements RefDecenaleService {}
