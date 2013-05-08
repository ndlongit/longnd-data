package com.structis.fichesst.client.widget;

import com.extjs.gxt.ui.client.event.ComponentEvent;
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

	private boolean autoAdjustHeight;

	public CustomEditorGrid(ListStore<M> store, ColumnModel cm) {
		this(store, cm, null, null);
	}

	public CustomEditorGrid(ListStore<M> store, ColumnModel cm, final RoleModel role, final UtilisateurGrpModel user) {
		super(store, cm);
		setBorders(true);
		setStripeRows(true);
		setColumnLines(true);
		setColumnReordering(true);
		setAutoExpandMax(2000);
		setAutoExpandMin(1000);
		setSelectionModel(new GridSelectionModel<M>());
		setStyleAttribute("border-color", "black");
		setHeight(200);

		if( view != null ) {
			view.setAutoFill(false);
		}

		addListener(Events.BeforeEdit, new Listener<GridEvent<AbstractDto>>() {
			public void handleEvent(final GridEvent<AbstractDto> be) {
				AbstractDto model = be.getModel();
				int rowindex = be.getRowIndex();
				if( rowindex == -1 ) {
					return;
				}

				int columnIndex = be.getColIndex();
				if( columnIndex == 1 ) { //Do not lock the Fig.(Lock) column itself
					return;
				}

				if( user != null && role != null ) {
					if( Boolean.TRUE.equals(user.getBadmin()) ) {
						be.setCancelled(false);
					}
					else if( Boolean.TRUE.equals(role.getBcontributeur()) ) {
						if( isLocked(model) ) {
							be.setCancelled(true);
						}
						else {
							be.setCancelled(false);
						}
					}
					else {
						be.setCancelled(true);
					}
				}
				else { // No permission is specified
					if( isLocked(model) ) {
						be.setCancelled(true);
					}
					else {
						be.setCancelled(false);
					}
				}
			}

			public boolean isLocked(AbstractDto model) {
				if( model instanceof GestionDto ) {
					Boolean lock = ((GestionDto) model).getLock();
					return Boolean.TRUE.equals(lock);
				}
				else {
					return false;
				}
			}
		});
	}

	public boolean isAutoAdjustHeight() {
		return autoAdjustHeight;
	}

	public void setAutoAdjustHeight(boolean autoAdjustHeight) {
		this.autoAdjustHeight = autoAdjustHeight;
	}

	public void hideHeader() {

		// Hide Header using CSS
		/**
		 * #noHeaderGrid .x-grid3-header { display:none; }
		 */

		setId("noHeaderGrid");
	}

	@Override
	protected void afterRender() {
		super.afterRender();
		if( !isAutoAdjustHeight() ) {
			return;
		}

		//avoid showing vertical scrollbars (in case of setting autoHeight for grouping grid)
		addListener(Events.ViewReady, new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent be) {
				adjustHeight();
			}
		});
	}

	public void adjustHeight() {
		if( isViewReady() ) {
			setAutoHeight(false);
			setHeight(getHeight() + 25);
		}
	}
}
