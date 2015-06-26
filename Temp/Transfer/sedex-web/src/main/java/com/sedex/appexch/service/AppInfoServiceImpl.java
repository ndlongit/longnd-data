package com.sedex.appexch.service;

import com.sedex.appexch.model.AppInfo;
import com.sedex.appexch.dao.AppInfoDao;
import com.sedex.appexch.service.AppInfoService;
import com.sedex.appexch.service.base.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class AppInfoServiceImpl extends
		AbstractService<AppInfo, Long, AppInfoDao> implements AppInfoService {
}
