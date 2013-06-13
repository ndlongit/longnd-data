package com.structis.vip.client.panel;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.data.DataProxy;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.client.constant.ClientConstant;
import com.structis.vip.client.exception.AsyncCallBackDecorator;
import com.structis.vip.client.exception.ExceptionMessageMapper;
import com.structis.vip.client.navigation.HistoryHelper;
import com.structis.vip.client.navigation.NavigationFactory;
import com.structis.vip.client.navigation.NavigationService;
import com.structis.vip.shared.config.ReferencePagingLoadConfig;
import com.structis.vip.shared.model.BaseModelDataActivable;
import com.structis.vip.shared.model.ModelActivable;

/**
 * Abstract content panel pour la pagination de type Remote
 * 
 * @author b.brotosumpeno
 * 
 * @param <M>
 */
public abstract class AbstractRefRemoteContentPanel<M extends BaseModelDataActivable> extends AbstractReferenceContentPanel<M> {

    private NavigationService navigation = NavigationFactory.getNavigation();
    private ExceptionMessageMapper mapper = new ExceptionMessageMapper();

    /**
     * Variable pour stocker les propri�t�s de recherches
     */
    protected Map<String, String> searchProperties;

    /**
     * Appel loader remote paging
     * 
     * @param loadConfig
     * @param callback
     */
    protected abstract void loadPaging(PagingLoadConfig loadConfig, AsyncCallback<PagingLoadResult<M>> callback);

    @Override
    protected boolean isRemote() {
        return true;
    }

    @Override
    protected void onLoadApplication(Map<String, String> params) {
        ReferencePagingLoadConfig pagingLoadConfig = new ReferencePagingLoadConfig();
        pagingLoadConfig.setLimit(this.pagingSize);
        pagingLoadConfig.setOffset(0);
        pagingLoadConfig.setReload(true);
        addSearch(pagingLoadConfig, params);
        loader.load(pagingLoadConfig);
    }

    /**
     * Ajoute les parametre de search dans loadConfig
     * 
     * @param pagingLoadConfig
     * @param params
     */
    private void addSearch(ReferencePagingLoadConfig pagingLoadConfig, Map<String, String> params) {
        Set<String> keys = params.keySet();
        Map<String, String> searchs = new HashMap<String, String>();
        for (String key : keys) {
            if (key.startsWith(ClientConstant.SEARCH_PREFIXE)) {
                searchs.put(key.substring(ClientConstant.SEARCH_PREFIXE.length()), params.get(key));
            }
        }
        this.searchProperties = searchs;
    }

    /**
     * Ajoute le param search dans le map pour l'histoire
     * 
     * @param pagingLoadConfig
     * @return
     */
    private void addParamSearch(ReferencePagingLoadConfig pagingLoadConfig, Map<String, String> params) {
        Map<String, String> searchs = pagingLoadConfig.getRechercheProperties();
        Set<String> keys = searchs.keySet();
        for (String key : keys) {
            params.put(ClientConstant.SEARCH_PREFIXE + key, searchs.get(key));
        }
    }

    @Override
    protected void onLoadApplicationFromURL(Map<String, String> params) {
        ReferencePagingLoadConfig pagingLoadConfig = new ReferencePagingLoadConfig();

        // Cr�ation le configuration de base
        pagingLoadConfig.setLimit(Integer.valueOf(params.get("limit")));
        pagingLoadConfig.setOffset(Integer.valueOf(params.get("offset")));
        String sortFields = params.get("sortField");
        String[] sortFieldArray = sortFields.split(",");
        pagingLoadConfig.setSortFields(sortFieldArray);
        pagingLoadConfig.setSortDir(SortDir.findDir(params.get("sortDir")));

        // R�load ou pas
        pagingLoadConfig.setReload(true);

        // Ajoute le reste des parametres
        addSearch(pagingLoadConfig, params);
        loader.load(pagingLoadConfig);
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected DataProxy getProxy() {
        // Remote paging
        DataProxy proxy = new RpcProxy<PagingLoadResult<M>>() {

            @Override
            protected void load(Object loadConfig, AsyncCallback<PagingLoadResult<M>> callback) {
                ReferencePagingLoadConfig pagingLoadConfig = null;
                if (loadConfig instanceof ReferencePagingLoadConfig) {
                    pagingLoadConfig = (ReferencePagingLoadConfig) loadConfig;
                    pagingLoadConfig.setRechercheProperties(AbstractRefRemoteContentPanel.this.searchProperties);
                } else {
                    PagingLoadConfig base = (PagingLoadConfig) loadConfig;
                    pagingLoadConfig = new ReferencePagingLoadConfig();
                    pagingLoadConfig.setProperties(base.getProperties());
                    pagingLoadConfig.setSortField(base.getSortField());
                    pagingLoadConfig.setRechercheProperties(AbstractRefRemoteContentPanel.this.searchProperties);
                }

                if (null == pagingLoadConfig.getSortField()) {
                    pagingLoadConfig.setSortFields(AbstractRefRemoteContentPanel.this.getDefaultSort());
                    pagingLoadConfig.setSortDir(SortDir.ASC);
                }

                StringBuffer sbf = new StringBuffer();
                for (int i = 0; i < pagingLoadConfig.getSortFields().length; i++) {
                    sbf.append(pagingLoadConfig.getSortFields()[i]);
                    if (!(i == pagingLoadConfig.getSortFields().length - 1)) {
                        sbf.append(",");
                    }
                }
                String sortFiels = sbf.toString();

                // Cr�er le map
                Map<String, String> map = new HashMap<String, String>();
                map.put("limit", pagingLoadConfig.getLimit() + "");
                map.put("offset", pagingLoadConfig.getOffset() + "");
                map.put("sortField", sortFiels);
                map.put("sortDir", pagingLoadConfig.getSortDir() + "");
                map.put(ClientConstant.PAGINATION, "ok");
                if (pagingLoadConfig.getRechercheProperties() != null) {
                    AbstractRefRemoteContentPanel.this.addParamSearch(pagingLoadConfig, map);
                }
                HistoryHelper.newItem(AbstractRefRemoteContentPanel.this.navigation.getActionActuelle().getLabel(), map, false);

                AsyncCallBackDecorator<PagingLoadResult<M>> callDecorated = new AsyncCallBackDecorator<PagingLoadResult<M>>(callback) {

                    private static final long serialVersionUID = 1L;

                    @Override
                    public void afterSucces(PagingLoadResult<M> result) {
                    }

                    @Override
                    public void afterFailure(Throwable caught) {
                        AbstractRefRemoteContentPanel.this.mapper.map(caught);
                    }
                };

                AbstractRefRemoteContentPanel.this.loadPaging(pagingLoadConfig, callDecorated);
            }
        };
        return proxy;
    }

    @Override
    protected void updateListeModel(BaseEvent be) {
        // Fais rien
    }

    protected String[] getDefaultSort() {
        return new String[] { ModelActivable.BASE_LIBELLE };
    }

}
