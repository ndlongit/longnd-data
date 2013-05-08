package com.structis.fichesst.client.panel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.SummaryColumnConfig;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.structis.fichesst.client.message.Messages;
import com.structis.fichesst.client.navigation.NavigationFactory;
import com.structis.fichesst.client.navigation.NavigationService;
import com.structis.fichesst.client.service.ClientChantierService;
import com.structis.fichesst.client.service.ClientChantierServiceAsync;
import com.structis.fichesst.client.service.ClientRoleService;
import com.structis.fichesst.client.service.ClientRoleServiceAsync;
import com.structis.fichesst.client.service.ClientUtilsateurGrpService;
import com.structis.fichesst.client.service.ClientUtilsateurGrpServiceAsync;
import com.structis.fichesst.client.util.GuiUtil;
import com.structis.fichesst.shared.dto.AbstractDto;
import com.structis.fichesst.shared.dto.RoleModel;
import com.structis.fichesst.shared.dto.SimpleDto;
import com.structis.fichesst.shared.dto.SimpleFigDto;
import com.structis.fichesst.shared.dto.UtilisateurGrpModel;
import com.structis.fichesst.shared.util.Constants;

public abstract class AbstractPanel extends LayoutContainer {

	protected static final String BACKGROUD_COLOR_2 = Constants.BACKGROUD_COLOR_2;

	protected static final String BUTTONS_SPACE = Constants.BUTTONS_SPACE;

	public static final String CURRENCY_FORMAT = Constants.CURRENCY_FORMAT;

	protected static final String DATE_FORMAT = Constants.DATE_FORMAT;

	protected static final String DEFAULT_BACKGROUD_COLOR = Constants.DEFAULT_BACKGROUD_COLOR;

	protected static final int DELETE_BUTTON_WIDTH = 36;

	protected static final String HEADER_BACKGROUND_COLOR = Constants.HEADER_BACKGROUND_COLOR;

	protected static final String INTEGER_FORMAT = Constants.INTEGER_FORMAT;

	protected static final String LINKS_SPACE = Constants.LINKS_SPACE;

	protected static final int MAX_LENGTH_1 = 50;

	protected static final int MAX_LENGTH_2 = 60;

	protected static final int MAX_LENGTH_3 = 80;

	protected static final int MAX_LENGTH_4 = 100;

	protected static final int MAX_LENGTH_5 = 255;

	protected static Messages messages = GWT.create(Messages.class);

	protected static final int MIN_WIDTH = Constants.MIN_WIDTH;

	protected static final int MIN_WIDTH_2 = Constants.MIN_WIDTH_2;

	protected static final String NUMBER_FORMAT = Constants.NUMBER_FORMAT;

	protected static final String NUMBER_FORMAT_2 = Constants.NUMBER_FORMAT_2;

	protected static final int PADDING = 4;

	protected static final int PADDING_2 = 8;

	protected static final RootPanel ROOTPANEL = RootPanel.get("appContent");

	protected static final String SPACES = "&nbsp;&nbsp;&nbsp;&nbsp;";

	protected static final String SPACES_2 = "&nbsp;&nbsp;&nbsp;";

	protected static final String SPACES_3 = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

	protected static final String SPACES_4 = "&nbsp;&nbsp;";

	private static String addCellBorder(Object value) {
		return "<div class='input-cell-border'>" + (value == null ? " " : value.toString()) + "</div>";
	}

	protected static List<SimpleFigDto> buildNonOuiOptions() {
		List<SimpleFigDto> results = new ArrayList<SimpleFigDto>();
		SimpleFigDto option1 = new SimpleFigDto();
		option1.setId(1);
		option1.setLabel(messages.yes());// Oui
		option1.setValue(true);
		results.add(option1);

		SimpleFigDto option2 = new SimpleFigDto();
		option2.setId(0);
		option2.setLabel(messages.no());// Non
		option2.setValue(false);
		results.add(option2);
		return results;
	}

	private static String buildRenderString(final RoleModel role, final UtilisateurGrpModel user, Object value) {
		if( role != null && user != null ) {
			if( isAdminOrContributor(role, user) ) {
				return addCellBorder(value);
			}
			else {
				return value == null ? "" : value.toString();
			}
		}
		else {
			return addCellBorder(value);
		}
	}

	protected static String combineProps(String... properties) {
		String result = "";
		if( properties != null ) {
			for( int i = 0 ; i < properties.length ; i++ ) {

				if( result == "" ) {
					result = properties[i];
				}
				else {
					result += "." + properties[i];
				}
			}
		}

		return result;
	}

	@SuppressWarnings("rawtypes")
	protected static void commitDataGrids(EditorGrid... editorGrids) {
		if( editorGrids != null ) {
			for( EditorGrid editorGrid : editorGrids ) {
				if( editorGrid != null && editorGrid.getStore() != null ) {
					editorGrid.getStore().commitChanges();
				}
			}
		}
	}

	public static Button createCancelButton(final Listener<MessageBoxEvent> callback) {
		Button cancelButton = new Button(messages.cancelChanges(), new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				createConfirmBox(callback, "", "Êtes-vous sure pour quitter cette page?");
			}
		});

		return cancelButton;
	}

	@SuppressWarnings("rawtypes")
	protected static ColumnModel createColumnModel(String[] headers, String[] ids, int[] columnsWidth,
			Boolean isAdminOrContributeur, HorizontalAlignment... horizontalAlignments) {
		List<ColumnConfig> columns = new ArrayList<ColumnConfig>();
		SummaryColumnConfig column = null;
		for( int i = 0 ; i < headers.length ; i++ ) {
			column = new SummaryColumnConfig();
			column.setHeader(headers[i]);
			column.setToolTip(headers[i]);
			column.setId(ids[i]);
			column.setMenuDisabled(true);
			column.setWidth(columnsWidth[i]);
			if( horizontalAlignments.length > 1 ) {
				column.setAlignment(horizontalAlignments[i]);
			}
			else {
				column.setAlignment(horizontalAlignments[0]);
			}
			if( !isAdminOrContributeur && i == 0 ) {
				column = new SummaryColumnConfig("", "", 0);
			}
			columns.add(column);
		}

		ColumnModel cm = new ColumnModel(columns);
		return cm;
	}

	@SuppressWarnings("rawtypes")
	protected static ColumnModel createColumnModel(String[] headers, String[] ids, int[] columnsWidth,
			HorizontalAlignment... horizontalAlignments) {
		List<ColumnConfig> columns = new ArrayList<ColumnConfig>();
		SummaryColumnConfig column = null;
		for( int i = 0 ; i < headers.length ; i++ ) {
			column = new SummaryColumnConfig();
			column.setHeader(headers[i]);
			column.setToolTip(headers[i]);
			column.setId(ids[i]);
			column.setMenuDisabled(true);
			column.setWidth(columnsWidth[i]);
			if( horizontalAlignments.length > 1 ) {
				column.setAlignment(horizontalAlignments[i]);
			}
			else {
				column.setAlignment(horizontalAlignments[0]);
			}
			columns.add(column);
		}

		ColumnModel cm = new ColumnModel(columns);
		return cm;
	}

	protected static MessageBox createConfirmBox(final Listener<MessageBoxEvent> callback, String title, String message) {
		MessageBox box = new MessageBox();
		box.setButtons(MessageBox.OKCANCEL);
		((Button) box.getDialog().getButtonBar().getItem(0)).setText(messages.yes());
		box.setIcon(MessageBox.QUESTION);
		box.setTitle(title);
		box.addCallback(callback);
		box.setMessage(message);
		box.show();
		return box;
	}

	protected static LayoutContainer createContainer() {
		LayoutContainer container = new LayoutContainer();
		container.setBorders(false);
		setWhiteBackgroundColor(container);
		return container;
	}

	protected static DateField createDateField() {
		DateField dateField = new DateField();
		dateField.getPropertyEditor().setFormat(DateTimeFormat.getFormat(DATE_FORMAT));
		return dateField;
	}

	protected static GridCellRenderer<AbstractDto> createDateRenderer() {
		return createDateRenderer(-1);
	}

	protected static GridCellRenderer<AbstractDto> createDateRenderer(final int fieldWidth) {
		return createDateRendererWithPermision(fieldWidth, null, null);
	}

	protected static GridCellRenderer<AbstractDto> createDateRendererWithPermision(final int fieldWidth,
			final RoleModel role, final UtilisateurGrpModel user) {
		GridCellRenderer<AbstractDto> dateRenderer = new GridCellRenderer<AbstractDto>() {
			@Override
			public Object render(final AbstractDto model, String property, ColumnData config, final int rowIndex,
					final int colIndex, ListStore<AbstractDto> store, Grid<AbstractDto> grid) {

				DateField field = new DateField();
				if( fieldWidth > 0 ) {
					field.setWidth(fieldWidth);
				}

				if( role != null && user != null ) {
					if( isAdminOrContributor(role, user) ) {
						field.enable();
					}
					else {
						field.disable();
					}
				}

				field.setValue((Date) model.get(property));
				return field;
			}
		};

		return dateRenderer;
	}

	public static Widget createDeleteButton(final Listener<MessageBoxEvent> callback) {
		HTML deleteButton = new HTML("<img src='./images/supprimer.png'/>");
		deleteButton.setTitle(messages.delete());
		deleteButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				MessageBox box = createConfirmBox(callback, messages.deleteConfirmTitle(), messages.deleteConfirmMessage());
				box.show();
			}
		});

		return deleteButton;
	}

	public static Widget createDeleteButton(final Listener<MessageBoxEvent> callback, final String message) {
		HTML deleteButton = new HTML("<img src='./images/supprimer.png'/>");
		deleteButton.setTitle(messages.delete());
		deleteButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				createConfirmBox(callback, messages.deleteConfirmTitle(), message);
			}
		});

		return deleteButton;
	}

	public static GridCellRenderer<AbstractDto> createDeleteButtonRenderer() {
		GridCellRenderer<AbstractDto> deleteButtonRenderer = new GridCellRenderer<AbstractDto>() {
			@Override
			public Object render(final AbstractDto model, String property, ColumnData config, final int rowIndex,
					final int colIndex, final ListStore<AbstractDto> store, Grid<AbstractDto> grid) {

				final Listener<MessageBoxEvent> callback = new Listener<MessageBoxEvent>() {
					@Override
					public void handleEvent(MessageBoxEvent be) {
						Button btt = be.getButtonClicked();
						if( Dialog.OK.equals(btt.getItemId()) ) {
							if( store != null ) {
								store.remove(model);
							}
						}
					}
				};

				return createDeleteButton(callback);
			}
		};

		return deleteButtonRenderer;
	}

	public static GridCellRenderer<AbstractDto> createDeleteButtonRenderer(final Boolean isAdminOrContributeur) {
		if( !isAdminOrContributeur )
			return null;
		GridCellRenderer<AbstractDto> deleteButtonRenderer = new GridCellRenderer<AbstractDto>() {
			@Override
			public Object render(final AbstractDto model, String property, ColumnData config, final int rowIndex,
					final int colIndex, final ListStore<AbstractDto> store, Grid<AbstractDto> grid) {

				final Listener<MessageBoxEvent> callback = new Listener<MessageBoxEvent>() {
					@Override
					public void handleEvent(MessageBoxEvent be) {
						Button btt = be.getButtonClicked();
						if( Dialog.OK.equals(btt.getItemId()) ) {
							if( store != null ) {
								store.remove(model);
							}
						}
					}
				};
				return createDeleteButton(callback);
			}
		};
		return deleteButtonRenderer;
	}

	protected static NumberField createIntegerField(String label) {
		return createIntegerField(label, true);
	}

	protected static NumberField createIntegerField(String label, boolean formatNumber) {
		NumberField numberField = createNumericField();
		numberField.setPropertyEditorType(Integer.class);

		if( formatNumber ) {
			numberField.setFormat(NumberFormat.getFormat(INTEGER_FORMAT));
		}

		if( label != null ) {
			numberField.setFieldLabel(label);
		}
		return numberField;
	}

	protected static GridCellRenderer<AbstractDto> createIntegerRenderer(final int fieldWidth) {
		return createIntegerRendererWithPermission(fieldWidth, null, null);
	}

	protected static GridCellRenderer<AbstractDto> createIntegerRendererWithPermission(final int fieldWidth,
			final RoleModel role, final UtilisateurGrpModel user) {
		GridCellRenderer<AbstractDto> numberRenderer = new GridCellRenderer<AbstractDto>() {
			@Override
			public Object render(AbstractDto model, String property, ColumnData config, int rowIndex, int colIndex,
					ListStore<AbstractDto> store, Grid<AbstractDto> grid) {
				Integer value = (Integer) model.get(property);

				return buildRenderString(role, user, value);
			}
		};

		return numberRenderer;
	}

	protected static NumberField createLockNumberFieldWithPermission(String label, RoleModel role, UtilisateurGrpModel user) {
		NumberField field = createNumericField();
		field.setFormat(NumberFormat.getFormat(NUMBER_FORMAT_2));

		if( label != null ) {
			field.setFieldLabel(label);
		}
		if( role != null && user != null ) {
			if( isAdminOrContributor(role, user) ) {
				field.enable();
			}
			else {
				field.disable();
			}
		}
		return field;
	}

	protected static NumberField createNumberField(String label) {
		return createLockNumberFieldWithPermission(label, null, null);
	}

	protected static GridCellRenderer<AbstractDto> createNumberRenderer(final int fieldWidth) {
		return createNumberRendererWithPermission(fieldWidth, null, null);
	}

	protected static GridCellRenderer<AbstractDto> createNumberRendererWithPermission(final int fieldWidth,
			final RoleModel role, final UtilisateurGrpModel user) {
		GridCellRenderer<AbstractDto> numberRenderer = new GridCellRenderer<AbstractDto>() {
			@Override
			public Object render(AbstractDto model, String property, ColumnData config, int rowIndex, int colIndex,
					ListStore<AbstractDto> store, Grid<AbstractDto> grid) {
				Double value = (Double) model.get(property);

				return buildRenderString(role, user, value);

			}
		};

		return numberRenderer;
	}

	protected static NumberField createNumericField() {
		return createNumericField(15);
	}

	protected static NumberField createNumericField(int maxLength) {
		NumberField field = new NumberField() {
			@Override
			protected void onRender(Element target, int index) {
				super.onRender(target, index);
				getInputEl().setElementAttribute("maxLength", getMaxLength());
			}

			@Override
			public void setMaxLength(int max) {
				super.setMaxLength(max);
				if( rendered ) {
					getInputEl().setElementAttribute("maxLength", max);
				}
			}
		};
		field.setInputStyleAttribute("textAlign", "right");
		field.setMaxLength(maxLength);

		return field;
	}

	/*
	 * if( role == null || user == null ) { return false; } else { return Boolean.TRUE.equals(user.getBadmin()) ||
	 * Boolean.TRUE.equals(role.getBcontributeur()); }
	 */
	protected static Image createPrintButton() {
		Image img = new Image("./images/imprimer.png");
		img.setTitle("Imprimer");
		return img;
	}

	protected static GridCellRenderer<AbstractDto> createTextAreaRenderer() {
		return createTextAreaRenderer(100, 33);
	}

	protected static GridCellRenderer<AbstractDto> createTextAreaRenderer(final int fieldWidth, final int fieldHeight) {
		return createTextAreaRendererWithPermission(fieldWidth, fieldHeight, null, null);
	}

	protected static GridCellRenderer<AbstractDto> createTextAreaRendererWithPermission(final int fieldWidth,
			final int fieldHeight, final RoleModel role, final UtilisateurGrpModel user) {
		GridCellRenderer<AbstractDto> textFieldRenderer = new GridCellRenderer<AbstractDto>() {
			@Override
			public Object render(AbstractDto model, String property, ColumnData config, int rowIndex, int colIndex,
					ListStore<AbstractDto> store, Grid<AbstractDto> grid) {
				String value = (String) model.get(property);
				if(value == null) {
					value = "";
				}
				
//				TextArea field = new TextArea();
//				field.setValue(value);
//
//				if( fieldWidth > 0 ) {
//					field.setWidth(fieldWidth);
//				}
//
//				if( fieldHeight > 0 ) {
//					field.setHeight(fieldHeight);
//				}
//
//				if( role != null && user != null ) {
//					if( isAdminOrContributor(role, user) ) {
//						field.enable();
//					}
//					else {
//						field.disable();
//					}
//				}
//				return field;
				
				return "<div style='text-align:left; display:inline-block; height:" + fieldHeight + "px; width: " + fieldWidth + "px'>" + value + "</div>";
			}
		};

		return textFieldRenderer;
	}

	protected static TextField<String> createTextField() {
		return createTextField(50);
	}

	protected static TextField<String> createTextField(int maxLength) {
		TextField<String> field = new TextField<String>() {
			@Override
			protected void onRender(Element target, int index) {
				super.onRender(target, index);
				getInputEl().setElementAttribute("maxLength", getMaxLength());
			}

			@Override
			public void setMaxLength(int max) {
				super.setMaxLength(max);
				if( rendered ) {
					getInputEl().setElementAttribute("maxLength", max);
				}
			}
		};
		field.setInputStyleAttribute("textAlign", "right");
		field.setMaxLength(maxLength);

		return field;
	}

	protected static GridCellRenderer<AbstractDto> createTextFieldRenderer(final int fieldWidth) {
		return createTextFieldRendererWithPermission(fieldWidth, null, null);
	}

	protected static GridCellRenderer<AbstractDto> createTextFieldRendererWithPermission(final int fieldWidth,
			final RoleModel role, final UtilisateurGrpModel user) {
		GridCellRenderer<AbstractDto> textFieldRenderer = new GridCellRenderer<AbstractDto>() {
			@Override
			public Object render(AbstractDto model, String property, ColumnData config, int rowIndex, int colIndex,
					ListStore<AbstractDto> store, Grid<AbstractDto> grid) {
				String value = (String) model.get(property);

				return buildRenderString(role, user, value);
			}
		};

		return textFieldRenderer;
	}

	protected static Image createViewButton() {
		Image viewButton = new Image("./images/voir.png");
		viewButton.setTitle("Voir");
		return viewButton;
	}

	protected static boolean isAdminOrContributor(RoleModel role, UtilisateurGrpModel user) {
		if( user != null ) {
			if( (user.getBadmin() != null && user.getBadmin()) || (role.getBcontributeur() != null && role.getBcontributeur()) ) {
				return true;
			}
		}
		else {
			if( role == null )
				return false;
			else {
				if( role.getBcontributeur() != null && role.getBcontributeur() ) {
					return true;
				}
				else if( role.getBlecteur() )
					return false;
			}
		}
		return false;
	}

	protected static void setBackgroundColor(Component c, String color) {
		c.setStyleAttribute("background-color", color);
	}

	protected static void setDefaultBackgroundColor(Component c) {
		setBackgroundColor(c, DEFAULT_BACKGROUD_COLOR);
	}

	protected static void setPadding(Component c) {
		GuiUtil.setPadding(c);
	}

	protected static void setWhiteBackgroundColor(Component c) {
		GuiUtil.setWhiteBackgroundColor(c);
	}

	protected static void showLoading(Component c) {
		GuiUtil.showLoading(c);
	}

	protected static void showSaving(Component c) {
		GuiUtil.showSaving(c);
	}

	protected SimpleEventBus bus;

	protected NavigationService navigation = NavigationFactory.getNavigation();

	protected final ClientChantierServiceAsync service = GWT.create(ClientChantierService.class);

	protected final ClientRoleServiceAsync serviceRole = GWT.create(ClientRoleService.class);

	protected final ClientUtilsateurGrpServiceAsync serviceUser = GWT.create(ClientUtilsateurGrpService.class);

	protected AbstractPanel() {
		setBorders(false);
		setWhiteBackgroundColor(this);
	}

	/**
	 * @param params
	 * @return
	 */
	protected String append(Object... params) {
		NumberFormat numberFormat = NumberFormat.getFormat(Constants.NUMBER_FORMAT);
		DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat(Constants.DATE_FORMAT);
		String srcParam = "";
		for( Object param : params ) {
			if( param != null ) {
				if( param instanceof String ) {
					srcParam += param;
				}
				if( param instanceof Integer ) {
					srcParam += numberFormat.format(((Integer) param).intValue());
				}
				if( param instanceof Double ) {
					srcParam += numberFormat.format(((Double) param).doubleValue());
				}
				if( param instanceof Float ) {
					srcParam += numberFormat.format(((Float) param).floatValue());
				}
				if( param instanceof Date ) {
					srcParam += dateTimeFormat.format((Date) param);
				}
			}
			else {
				srcParam += "null";
			}
			srcParam += Constants.SEPRATE;
		}
		return srcParam;
	}

	/* Sub-classes should implement this method */
	protected void commitDataChange() {
	}

	protected GridCellRenderer<AbstractDto> createCheckBoxRenderer() {
		GridCellRenderer<AbstractDto> checkboxRenderer = new GridCellRenderer<AbstractDto>() {
			@Override
			public Object render(AbstractDto model, String property, ColumnData config, int rowIndex, int colIndex,
					ListStore<AbstractDto> store, Grid<AbstractDto> grid) {
				Boolean value = (Boolean) model.get(property);
				CheckBox field = new CheckBox();
				field.setValue(value);
				return field;
			}
		};

		return checkboxRenderer;
	}

	protected GridCellRenderer<AbstractDto> createComboBoxRenderer() {
		GridCellRenderer<AbstractDto> comboBoxRenderer = new GridCellRenderer<AbstractDto>() {
			@Override
			public Object render(AbstractDto model, String property, ColumnData config, int rowIndex, int colIndex,
					ListStore<AbstractDto> store, Grid<AbstractDto> grid) {
				SimpleDto value = (SimpleDto) model.get(property);
				if( value == null ) {
					return null;
				}
				else {

					return value.getLabel();
				}
			}
		};

		return comboBoxRenderer;
	}

	/* Sub-classes should implement this method */
	protected void rejectDataChange() {
	}

	protected void setBackgroundColor(String color) {
		setBackgroundColor(this, color);
	}

	protected void setDefaultBackgroundColor() {
		setBackgroundColor(this, DEFAULT_BACKGROUD_COLOR);
	}
}
