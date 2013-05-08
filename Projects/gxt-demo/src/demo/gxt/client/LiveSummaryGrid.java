package demo.gxt.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.store.GroupingStore;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.GroupSummaryView;
import com.extjs.gxt.ui.client.widget.grid.SummaryColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.SummaryRenderer;
import com.extjs.gxt.ui.client.widget.grid.SummaryType;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.RootPanel;

public class LiveSummaryGrid implements EntryPoint {

	/**
	 * Constructor
	 */
	public LiveSummaryGrid() {

	}

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		System.out.println("Inside");
		List<Task> tasks = getTasks();
		GroupingStore<Task> store = new GroupingStore<Task>();
		store.groupBy("project");
		store.add(tasks);

		List<ColumnConfig> columns = new ArrayList<ColumnConfig>();

		SummaryColumnConfig<Integer> desc = new SummaryColumnConfig<Integer>("description", "Task", 65);
		desc.setSummaryType(SummaryType.COUNT);
		desc.setSummaryRenderer(new SummaryRenderer() {
			public String render(Number value, Map<String, Number> data) {
				return value.intValue() > 1 ? "(" + value.intValue() + " Tasks)" : "(1 Task)";
			}
		});

		SummaryColumnConfig<Double> project = new SummaryColumnConfig<Double>("project", "Project", 55);
		SummaryColumnConfig<Double> due = new SummaryColumnConfig<Double>("due", "Due Date", 20);

		SummaryColumnConfig<Double> estimate = new SummaryColumnConfig<Double>("estimate", "Estimate", 20);
		estimate.setRenderer(new GridCellRenderer<Task>() {
			public String render(Task model, String property, ColumnData config, int rowIndex, int colIndex,
					ListStore<Task> store, Grid<Task> grid) {
				return model.get(property) + " hours";
			}
		});
		estimate.setSummaryType(SummaryType.SUM);
		estimate.setSummaryRenderer(new SummaryRenderer() {
			public String render(Number value, Map<String, Number> data) {
				return value.intValue() + " hours";
			}
		});
		estimate.setEditor(new CellEditor(new NumberField()));

		SummaryColumnConfig<Double> rate = new SummaryColumnConfig<Double>("rate", "Rate", 20);
		rate.setNumberFormat(NumberFormat.getCurrencyFormat());
		rate.setSummaryFormat(NumberFormat.getCurrencyFormat());
		rate.setSummaryType(SummaryType.AVG);
		rate.setAlignment(HorizontalAlignment.RIGHT);

		NumberField nf = new NumberField();
		nf.setAutoValidate(true);
		CellEditor ce = new CellEditor(nf);
		ce.setCancelOnEsc(true);
		rate.setEditor(ce);

		SummaryColumnConfig<Double> cost = new SummaryColumnConfig<Double>("cost", "Cost", 20);
		cost.setSummaryFormat(NumberFormat.getCurrencyFormat());
		cost.setSummaryType(new SummaryType<Double>() {
			@Override
			public Double render(Object v, ModelData m, String field, Map<String, Object> data) {
				if( v == null ) {
					v = 0d;
				}
				Task task = (Task) m;
				return ((Double) v).doubleValue() + (task.getEstimate() * task.getRate());
			}

		});
		cost.setAlignment(HorizontalAlignment.RIGHT);
		cost.setRenderer(new GridCellRenderer<Task>() {
			public String render(Task model, String property, ColumnData config, int rowIndex, int colIndex,
					ListStore<Task> store, Grid<Task> grid) {
				Task task = (Task) model;

				/* Change made here */

				//return NumberFormat.getCurrencyFormat().format(task.getRate() * task.getEstimate());
				if( rowIndex > 0 ) {
					return NumberFormat.getCurrencyFormat().format(
							task.getRate() * ((Double) store.getModels().get(rowIndex - 1).get("estimate")));
				}
				else {
					return NumberFormat.getCurrencyFormat().format(task.getRate() * task.getEstimate());
				}
			}
		});

		columns.add(desc);
		columns.add(project);
		columns.add(due);
		columns.add(estimate);
		columns.add(rate);
		columns.add(cost);
		ColumnModel cm = new ColumnModel(columns);

		GroupSummaryView summary = new GroupSummaryView();
		summary.setForceFit(true);
		summary.setShowGroupedColumn(false);

		EditorGrid<Task> grid = new EditorGrid<Task>(store, cm);
		grid.setBorders(true);
		grid.setView(summary);
		grid.getView().setShowDirtyCells(false);

		ContentPanel panel = new ContentPanel();
		panel.setHeading("Sponsored Projects");
		panel.setCollapsible(true);
		panel.setFrame(true);
		panel.setSize(800, 450);
		panel.setLayout(new FitLayout());
		panel.add(grid);
		RootPanel.get().add(panel);

	}

	public List<Task> getTasks() {
		List<Task> tasks = new ArrayList<Task>();
		tasks.add(new Task(
				100, "Ext Forms: Field Anchoring", 112, "Integrate 2.0 Forms with 2.0 Layouts", 6, 150, "06/24/2007"));
		tasks.add(new Task(100, "Ext Forms: Field Anchoring", 113, "Implement AnchorLayout", 4, 150, "06/25/2007"));
		tasks.add(new Task(
				100, "Ext Forms: Field Anchoring", 114, "Add support for multiple types of anchors", 4, 300, "06/27/2007"));
		tasks.add(new Task(100, "Ext Forms: Field Anchoring", 115, "Testing and debugging", 8, 150, "06/29/2007"));

		tasks.add(new Task(
				101, "Ext Grid: Single-level Grouping", 101, "Add required rendering 'hooks' to GridView", 6, 100,
				"07/01/2007"));
		tasks.add(new Task(
				101, "Ext Grid: Single-level Grouping", 102, "Extend GridView and override rendering functions", 4, 0,
				"07/03/2007"));
		tasks.add(new Task(
				101, "Ext Grid: Single-level Grouping", 103, "Extend Store with grouping functionality", 4, 100,
				"07/04/2007"));
		tasks.add(new Task(101, "Ext Grid: Single-level Grouping", 121, "Default CSS Styling", 2, 350, "07/05/2007"));
		tasks.add(new Task(101, "Ext Grid: Single-level Grouping", 104, "Testing and debugging", 6, 100, "07/06/2007"));

		tasks.add(new Task(102, "Ext Grid: Summary Rows", 105, "Ext Grid plugin integration", 4, 125, "07/01/2007"));
		tasks.add(new Task(
				102, "Ext Grid: Summary Rows", 106, "Summary creation during rendering phase", 6, 125, "07/02/2007"));
		tasks.add(new Task(
				102, "Ext Grid: Summary Rows", 107, "Dynamic summary updates in editor grids", 6, 125, "07/05/2007"));
		tasks.add(new Task(102, "Ext Grid: Summary Rows", 108, "Remote summary integration", 4, 125, "07/05/2007"));
		tasks.add(new Task(102, "Ext Grid: Summary Rows", 109, "Summary renderers and calculators", 4, 325, "07/06/2007"));
		tasks.add(new Task(
				102, "Ext Grid: Summary Rows", 110, "Integrate summaries with GroupingView", 10, 125, "07/11/2007"));
		tasks.add(new Task(102, "Ext Grid: Summary Rows", 111, "Testing and debugging", 8, 125, "07/15/2007"));
		return tasks;
	}

	class Task extends BaseModelData {
		public Task(int id, String project, int taskId, String desc, double estimate, double rate, String due) {
			setId(id);
			setProject(project);
			setTaskId(taskId);
			setDescription(desc);
			setEstimate(estimate);
			setRate(rate);
			setDue(due);
		}

		public Integer getId() {
			return (Integer) get("id");
		}

		public void setId(Integer id) {
			set("id", id);
		}

		public Integer geTaskId() {
			return (Integer) get("taskId");
		}

		public void setTaskId(Integer taskId) {
			set("taskId", taskId);
		}

		public String getProject() {
			return (String) get("project");
		}

		public void setProject(String project) {
			set("project", project);
		}

		public String getDue() {
			return (String) get("due");
		}

		public void setDue(String due) {
			set("due", due);
		}

		public String getDescription() {
			return (String) get("description");
		}

		public void setDescription(String project) {
			set("description", project);
		}

		public Double getEstimate() {
			return (Double) get("estimate");
		}

		public void setEstimate(double estimate) {
			set("estimate", new Double(estimate));
		}

		public Double getRate() {
			return (Double) get("rate");
		}

		public void setRate(double rate) {
			set("rate", new Double(rate));
		}
	}
}
