package com.sedex.appexch.dao;

import com.sedex.appexch.model.AppInfo;
import com.sedex.appexch.dao.AppInfoDao;
import com.sedex.appexch.dao.base.AbstractDao;
import org.springframework.stereotype.Repository;

@Repository
public class AppInfoDaoImpl extends AbstractDao<AppInfo, Long> implements
		AppInfoDao {
}
