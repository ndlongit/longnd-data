package com.structis.fichesst.server.service.domain;
import com.structis.fichesst.server.bean.domain.RefTransfertPP;
import com.structis.fichesst.server.dao.RefTransfertppDao;
import com.structis.fichesst.server.service.domain.RefTransfertppService;
import com.structis.fichesst.server.service.domain.BasicServiceImpl;
import org.springframework.stereotype.Service;
@Service("refTransfertppService")
public class RefTransfertppServiceImpl extends BasicServiceImpl<RefTransfertPP, Integer, RefTransfertppDao> implements RefTransfertppService {
	
}
