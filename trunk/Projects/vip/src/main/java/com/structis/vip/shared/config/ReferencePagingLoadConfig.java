package com.structis.vip.shared.config;

import java.util.List;
import java.util.Map;

import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.FilterConfig;
import com.extjs.gxt.ui.client.data.FilterPagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;

public class ReferencePagingLoadConfig extends BasePagingLoadConfig implements PagingLoadConfig, FilterPagingLoadConfig {

    private static final long serialVersionUID = 1L;

    private boolean prepareLoadConfig = false;

    @Override
    public void setSortField(String sortField) {
        super.setSortField(sortField);
        this.set("arraySort", new String[] { sortField });
    }

    public void setSortFields(String[] fields) {
        if (null != fields && fields.length > 0) {
            super.setSortField(fields[0]);
        }
        this.set("arraySort", fields);
    }

    public String[] getSortFields() {
        return this.get("arraySort");
    }

    public void setRechercheProperties(Map<String, String> properties) {
        this.set("recherche", properties);
    }

    public Map<String, String> getRechercheProperties() {
        return this.get("recherche");
    }

    @Override
    public List<FilterConfig> getFilterConfigs() {
        return this.get("filterConfig");
    }

    @Override
    public void setFilterConfigs(List<FilterConfig> configs) {
        this.set("filterConfig", configs);
    }

    public Boolean getReload() {
        return this.get("reload");
    }

    public void setReload(Boolean reload) {
        this.set("reload", reload);
    }

    public String getSessionId() {
        return this.get("sessionId");
    }

    public void setSessionId(String sessionId) {
        this.set("sessionId", sessionId);
    }

    public boolean isPrepareLoadConfig() {
        return this.prepareLoadConfig;
    }

    public void setPrepareLoadConfig(boolean prepareLoadConfig) {
        this.prepareLoadConfig = prepareLoadConfig;
    }

}
