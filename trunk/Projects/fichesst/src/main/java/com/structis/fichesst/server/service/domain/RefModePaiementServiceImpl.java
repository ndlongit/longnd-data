package com.structis.fichesst.server.service.domain;
import com.structis.fichesst.server.bean.domain.RefModePaiement;
import com.structis.fichesst.server.dao.RefModePaiementDao;
import com.structis.fichesst.server.service.domain.RefModePaiementService;
import com.structis.fichesst.server.service.domain.BasicServiceImpl;
import org.springframework.stereotype.Service;
@Service
public class RefModePaiementServiceImpl extends BasicServiceImpl<RefModePaiement, Integer, RefModePaiementDao> implements RefModePaiementService {}
