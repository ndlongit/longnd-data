package com.structis.vip.client.panel;

import java.util.List;

import com.extjs.gxt.ui.client.data.DataProxy;
import com.extjs.gxt.ui.client.data.PagingModelMemoryProxy;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.structis.vip.shared.model.BaseModelDataActivable;

/**
 * Abstract content panel pour la pagination de type Local
 * 
 * @author b.brotosumpeno
 * 
 * @param <M>
 */
public abstract class AbstractRefLocalContentPanel<M extends BaseModelDataActivable> extends AbstractReferenceContentPanel<M> {

    @Override
    protected boolean isRemote() {
        return false;
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected DataProxy getProxy() {
        // Paging local
        return new PagingModelMemoryProxy(this.listeModels);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void updateListeModel(BaseEvent be) {
        if (null != be.getSource()) {
            this.listeModels.addAll((List<M>) be.getSource());
        }
    }
}
