package com.structis.fichesst.client.filter;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.StoreFilter;

/**
 * @author tram.do Customized for search with multi fields
 * @param <M>
 */
public class CustomizedListStore<M extends ModelData> extends ListStore<M> {
	private List<String> searchFields = new ArrayList<String>();

	public CustomizedListStore() {
	}

	public CustomizedListStore(List<String> searchFields) {
		this.searchFields.addAll(searchFields);
	}

	public void addSearchField(String field) {
		this.searchFields.add(field);
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected boolean isFiltered(ModelData record, String property) {
		if( filterBeginsWith != null && property != null ) {
			Object o = record.get(property);
			if( o != null ) {
				if( !o.toString().toLowerCase().startsWith(filterBeginsWith.toLowerCase()) && !searchFilterFields(
						record, filterBeginsWith) ) {
					return true;
				}
			}
		}
		if( filters != null ) {
			for( StoreFilter filter : filters ) {
				boolean result = filter.select(this, record, record, property);
				if( !result ) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean searchFilterFields(ModelData item, String filterBeginsWith) {
		boolean ret = false;
		for( String field : searchFields ) {
			ret = ret || ((String) item.get(field)).toLowerCase().startsWith(filterBeginsWith.toLowerCase());
		}
		return ret;
	}
}
