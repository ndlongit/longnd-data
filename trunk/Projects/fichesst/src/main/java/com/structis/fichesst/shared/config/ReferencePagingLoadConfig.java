package com.structis.fichesst.shared.config;

import java.util.List;
import java.util.Map;

import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.FilterPagingLoadConfig;
import com.extjs.gxt.ui.client.data.FilterConfig;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;

public class ReferencePagingLoadConfig extends BasePagingLoadConfig implements PagingLoadConfig, FilterPagingLoadConfig {

	private static final long serialVersionUID = 1L;

	private boolean prepareLoadConfig = false;

	@Override
	public void setSortField(String sortField) {
		super.setSortField(sortField);
		set("arraySort", new String[] { sortField });
	}

	public void setSortFields(String[] fields) {
		if( null != fields && fields.length > 0 ) {
			super.setSortField(fields[0]);
		}
		set("arraySort", fields);
	}

	public String[] getSortFields() {
		return get("arraySort");
	}

	public void setRechercheProperties(Map<String, String> properties) {
		set("recherche", properties);
	}

	public Map<String, String> getRechercheProperties() {
		return get("recherche");
	}

	public List<FilterConfig> getFilterConfigs() {
		return get("filterConfig");
	}

	public void setFilterConfigs(List<FilterConfig> configs) {
		set("filterConfig", configs);
	}

	public Boolean getReload() {
		return get("reload");
	}

	public void setReload(Boolean reload) {
		set("reload", reload);
	}

	public String getSessionId() {
		return get("sessionId");
	}

	public void setSessionId(String sessionId) {
		set("sessionId", sessionId);
	}

	public boolean isPrepareLoadConfig() {
		return prepareLoadConfig;
	}

	public void setPrepareLoadConfig(boolean prepareLoadConfig) {
		this.prepareLoadConfig = prepareLoadConfig;
	}

}
