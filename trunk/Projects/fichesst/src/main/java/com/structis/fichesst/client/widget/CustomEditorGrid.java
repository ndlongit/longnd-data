package com.structis.fichesst.client.widget;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.grid.GridSelectionModel;
import com.structis.fichesst.shared.dto.AbstractDto;
import com.structis.fichesst.shared.dto.GestionDto;
import com.structis.fichesst.shared.dto.RoleModel;
import com.structis.fichesst.shared.dto.UtilisateurGrpModel;

public class CustomEditorGrid<M extends AbstractDto> extends EditorGrid<M> {

	public CustomEditorGrid(ListStore<M> store, ColumnModel cm) {
		this(store, cm, null, null);
	}

	public CustomEditorGrid(ListStore<M> store, ColumnModel cm, final RoleModel role, final UtilisateurGrpModel user) {
		super(store, cm);
		// setView(new CustomGridView());
		setBorders(true);
		setStripeRows(true);
		setColumnLines(false);
		setColumnReordering(false);
		setAutoExpandMax(2000);
		setAutoExpandMin(100);

		setSelectionModel(new GridSelectionModel<M>());
		setStyleAttribute("border-color", "black");
		setHeight(200);

		if (view != null) {
			view.setAutoFill(false);
		}

		addListener(Events.BeforeEdit, new Listener<GridEvent<AbstractDto>>() {
			@Override
			public void handleEvent(final GridEvent<AbstractDto> be) {
				AbstractDto model = be.getModel();
				int rowindex = be.getRowIndex();
				if (rowindex == -1) {
					return;
				}

				int columnIndex = be.getColIndex();
				if (columnIndex == 1) { // Do not lock the Fig. (Lock) column itself
					return;
				}
				be.setCancelled(isLocked(model));
			}

			public boolean isLocked(AbstractDto model) {
				if (model instanceof GestionDto) {
					Boolean lock = ((GestionDto) model).getLock();
					return Boolean.TRUE.equals(lock);
				} else {
					return false;
				}
			}
		});
	}

	@Override
	public void setAutoHeight(boolean autoHeight) {
		super.setAutoHeight(autoHeight);
		this.addStyleName("autoHeightGrid");
	}
}
