package servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.EngineException;
import org.eclipse.birt.report.engine.api.HTMLRenderOption;
import org.eclipse.birt.report.engine.api.IRenderTask;
import org.eclipse.birt.report.engine.api.IReportDocument;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunTask;
import org.eclipse.birt.report.engine.api.PDFRenderOption;
import org.eclipse.birt.report.engine.api.ReportEngine;
import org.eclipse.birt.report.model.api.CellHandle;
import org.eclipse.birt.report.model.api.ColumnHandle;
import org.eclipse.birt.report.model.api.DataItemHandle;
import org.eclipse.birt.report.model.api.DesignConfig;
import org.eclipse.birt.report.model.api.ElementFactory;
import org.eclipse.birt.report.model.api.GridHandle;
import org.eclipse.birt.report.model.api.IDesignEngine;
import org.eclipse.birt.report.model.api.IDesignEngineFactory;
import org.eclipse.birt.report.model.api.LabelHandle;
import org.eclipse.birt.report.model.api.PropertyHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.RowHandle;
import org.eclipse.birt.report.model.api.ScriptDataSetHandle;
import org.eclipse.birt.report.model.api.SessionHandle;
import org.eclipse.birt.report.model.api.SlotHandle;
import org.eclipse.birt.report.model.api.StructureFactory;
import org.eclipse.birt.report.model.api.TableHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.eclipse.birt.report.model.api.elements.structures.ComputedColumn;
import org.eclipse.birt.report.model.core.DesignElement;
import org.eclipse.birt.report.model.elements.GridItem;
import org.eclipse.core.internal.registry.RegistryProviderFactory;

import com.ibm.icu.util.ULocale;

/**
 * Servlet implementation class CreatePdf
 */
@SuppressWarnings("deprecation")
public class GenerateReport extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static ReportDesignHandle reportDesignHandle;

	private static ElementFactory elementFactory;

	private static ReportEngine engine;

	private static IReportRunnable runnable;

	private static final String fileFolder = "D:/Data-Store/Project/birt-demo/WebContent/birt/reports";

	private static final String inputReportFile = fileFolder + "/" + "ScriptedDsataSet.rptdesign";

	private static final String outputReportFile;

	static {
		outputReportFile = createTempFile(fileFolder).getAbsolutePath();
		try {
			initDesigner();
		} catch (BirtException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GenerateReport() {
		super();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		generateReport();

		response.getWriter().write("<center>----------- Create Report DONE. -----------</center>");
	}

	private static void generateReport() throws IOException {
		try {
			test02();

			// Save the design and close it.
			reportDesignHandle.saveAs(outputReportFile);
			reportDesignHandle.close();

			createPdf();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Delete temporary file
		File outputFile = new File(outputReportFile);
		if (outputFile.exists()) {
			outputFile.delete();
		}
	}

	private static void initDesigner() throws BirtException {
		DesignConfig config = new DesignConfig();
		Platform.startup(config);
		IDesignEngineFactory factory = (IDesignEngineFactory) Platform.createFactoryObject(IDesignEngineFactory.EXTENSION_DESIGN_ENGINE_FACTORY);
		IDesignEngine engine = factory.createDesignEngine(config);
		SessionHandle session = engine.newSessionHandle(ULocale.ENGLISH);

		reportDesignHandle = session.openDesign(inputReportFile);
		elementFactory = reportDesignHandle.getElementFactory();
	}

	private static void initEngine() {
		EngineConfig conf = new EngineConfig();

		// Create new Report engine based off of the configuration
		engine = new ReportEngine(conf);

		// With our new engine, lets try to open the report design
		try {
			runnable = engine.openReportDesign(outputReportFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void createPdf() throws EngineException {

		initEngine();

		// Create task to run the report - use the task to execute and run the report,
		IRunTask runTask = engine.createRunTask(runnable);

		// Create rptdocument
		File document = createTempFile(fileFolder);
		runTask.run(document.getAbsolutePath());

		// Open rptdocument
		IReportDocument rptdoc = engine.openReportDocument(document.getAbsolutePath());

		// Create Render Task
		IRenderTask renderTask = engine.createRenderTask(rptdoc);

		PDFRenderOption optionsPDF = new PDFRenderOption();
		optionsPDF.setOutputFileName("D:/Temp/output.pdf");
		optionsPDF.setOutputFormat(HTMLRenderOption.OUTPUT_FORMAT_PDF);
		renderTask.setRenderOption(optionsPDF);

		// render
		renderTask.render();

		// render the report and destroy the engine
		// Note - If the program stays resident do not shutdown the Platform or the Engine
//		rptdoc.close();
		renderTask.close();
		runTask.close();
		engine.shutdown();
		Platform.shutdown();
		RegistryProviderFactory.releaseDefault();

		if (document.exists()) {
			document.delete();
		}

		System.out.println("Finished");
	}

	private static void test10() throws SemanticException, IOException {
		TableHandle table = createSampleTable(null, 3, 1, 16, 1, true);
		reportDesignHandle.getBody().add(table);
	}

	private static GridHandle createGrid(String name, int columnNum, int rowNum) {
		return reportDesignHandle.getElementFactory().newGridItem(name, columnNum, rowNum);
	}

	private static TableHandle createSampleTable(String name, int columnNum, int headerRow, int detailRow, int footerRow, boolean border)
			throws SemanticException {
		TableHandle table = reportDesignHandle.getElementFactory().newTableItem(name, columnNum, headerRow, detailRow, footerRow);

		RowHandle myHeader = (RowHandle) table.getHeader().get(0);
		for (int i = 0; i < myHeader.getCells().getCount(); i++) {
			CellHandle cell = (CellHandle) myHeader.getCells().get(i);
			LabelHandle label = createLabel("Column " + i);
			cell.getContent().add(label);
			if (border) {
				setBorders(cell);
			}
		}

		for (int i = 0; i < table.getDetail().getCount(); i++) {
			RowHandle myDetail = (RowHandle) table.getDetail().get(i);
			for (int j = 0; j < myDetail.getCells().getCount(); j++) {
				CellHandle cell = (CellHandle) myDetail.getCells().get(j);
				LabelHandle label = createLabel("Cell " + i + ":" + j);
				cell.getContent().add(label);
				if (border) {
					setBorders(cell);
				}
			}
		}

		table.setProperty("repeatHeader", "true");
		return table;
	}

	private static GridHandle createSampleGrid(String name, int columnNum, int rowNum, boolean border) throws SemanticException {
		GridHandle grid = createGrid(name, columnNum, rowNum);

		RowHandle headerRow = (RowHandle) grid.getRows().get(0);

		for (int i = 0; i < headerRow.getCells().getCount(); i++) {
			CellHandle cell = (CellHandle) headerRow.getCells().get(i);
			if (border) {
				setBorders(cell);
			}
			LabelHandle label = createLabel("Column " + i);
			cell.getContent().add(label);
		}

		RowHandle detailRow = null;
		for (int i = 0; i < rowNum; i++) {
			detailRow = createRow(columnNum);
			for (int j = 0; j < grid.getColumnCount(); j++) {
				CellHandle cell = (CellHandle) detailRow.getCells().get(j);
				if (border) {
					setBorders(cell);
				}
				LabelHandle label = createLabel("Cell " + i + ":" + j);
				cell.getContent().add(label);
			}
			grid.getElement().add(detailRow.getElement(), GridItem.ROW_SLOT);
		}

		return grid;
	}

	private static RowHandle createRow(int cellNum) {
		return reportDesignHandle.getElementFactory().newTableRow(cellNum);
	}

	private static CellHandle createCell() {
		return reportDesignHandle.getElementFactory().newCell();
	}

	private static LabelHandle createLabel(String text) throws SemanticException {
		LabelHandle label = elementFactory.newLabel(null);
		label.setText(text);
		return label;
	}

	public static void addGridRow(GridHandle grid, boolean border) throws SemanticException {
		int columnNum = grid.getColumnCount();
		RowHandle row = reportDesignHandle.getElementFactory().newTableRow(columnNum);

		if (border) {
			for (int i = 0; i < columnNum; i++) {
				CellHandle cell = (CellHandle) row.getCells().get(i);
				setBorders(cell);
			}
		}

		DesignElement deRow = row.getElement();
		grid.getElement().add(deRow, GridItem.ROW_SLOT);
	}

	private static void setBorders(CellHandle cell) throws SemanticException {
		cell.setProperty("borderBottomColor", "#000000");
		cell.setProperty("borderBottomStyle", "solid");
		cell.setProperty("borderBottomWidth", "thin");
		cell.setProperty("borderLeftColor", "#000000");
		cell.setProperty("borderLeftStyle", "solid");
		cell.setProperty("borderLeftWidth", "thin");
		cell.setProperty("borderRightColor", "#000000");
		cell.setProperty("borderRightStyle", "solid");
		cell.setProperty("borderRightWidth", "thin");
		cell.setProperty("borderTopColor", "#000000");
		cell.setProperty("borderTopStyle", "solid");
		cell.setProperty("borderTopWidth", "thin");
	}

	private static File createTempFile(String fileFolder) {
		String tempFileName = "Temp" + Long.toString(System.nanoTime()) + ".tmp";
		File f = new File(fileFolder, tempFileName);
		return f;
	}

	public static TableHandle createTable(int rows) throws Exception {
		String dataSet = "Data Set";
		ScriptDataSetHandle dataSetHandle = elementFactory.newScriptDataSet(dataSet);
		dataSetHandle.setDataSource("scriptedDataSource");

		dataSetHandle.setOpen("i=0;");

		dataSetHandle.setFetch("if ( i < " + rows + "){" + "row[\"Month\"] = (i + 1);" + "row[\"Product\"] = 'Product ' + i;" + "i++;"
				+ "return true;" + "} else return false;");

		// Set computed columns
		ComputedColumn cs1 = StructureFactory.createComputedColumn();
		cs1.setName("Month");
		cs1.setExpression("row[\"Month\"]");
		cs1.setDataType("integer");

		ComputedColumn cs2 = StructureFactory.createComputedColumn();
		cs2.setName("Product");
		cs2.setExpression("row[\"Product\"]");
		cs2.setDataType("string");

		ComputedColumn cs3 = StructureFactory.createComputedColumn();
		cs3.setName("Amount");
		cs3.setExpression("row[\"Amount\"]");
		cs3.setDataType("integer");

		PropertyHandle computedSet = dataSetHandle.getPropertyHandle("computedColumns");
		computedSet.addItem(cs1);
		computedSet.addItem(cs2);
		computedSet.addItem(cs3);

		reportDesignHandle.getDataSets().add(dataSetHandle);

		TableHandle mytable = elementFactory.newTableItem(null, 3, 1, 1, 1);
		mytable.setWidth("100%");
		mytable.setProperty("dataSet", dataSet);

		computedSet = mytable.getColumnBindings();

		cs1.setExpression("dataSetRow[\"Month\"]");
		computedSet.addItem(cs1);

		cs2.setExpression("dataSetRow[\"Product\"]");
		computedSet.addItem(cs2);

		cs3.setExpression("dataSetRow[\"Amount\"]");
		computedSet.addItem(cs3);

		// Header
		RowHandle myheader = (RowHandle) mytable.getHeader().get(0);

		CellHandle tcell = (CellHandle) myheader.getCells().get(0);
		setBorders(tcell);
		LabelHandle mylabel = elementFactory.newLabel(null);
		mylabel.setText("Col1");
		tcell.getContent().add(mylabel);

		tcell = (CellHandle) myheader.getCells().get(1);
		setBorders(tcell);
		// tcell.setProperty(propName, value);
		mylabel = elementFactory.newLabel(null);
		mylabel.setText("Col2");
		tcell.getContent().add(mylabel);

		tcell = (CellHandle) myheader.getCells().get(2);
		setBorders(tcell);
		mylabel = elementFactory.newLabel(null);
		mylabel.setText("Col3");
		tcell.getContent().add(mylabel);

		RowHandle mydetail = (RowHandle) mytable.getDetail().get(0);
		tcell = (CellHandle) mydetail.getCells().get(0);
		setBorders(tcell);
		DataItemHandle mydata = elementFactory.newDataItem(null);
		mydata.setResultSetColumn("Month");
		tcell.getContent().add(mydata);

		tcell = (CellHandle) mydetail.getCells().get(1);
		mydata = elementFactory.newDataItem(null);
		mydata.setResultSetColumn("Product");
		tcell.getContent().add(mydata);

		CellHandle lastCell = (CellHandle) mydetail.getCells().get(2);
		lastCell.drop();
		tcell.setColumnSpan(2);
		setBorders(tcell);

		mytable.getColumns().get(0).setProperty("width", "45%");
		mytable.getColumns().get(1).setProperty("width", "45%");
		mytable.getColumns().get(2).setProperty("width", "10%");

		return mytable;
	}

	public static void test02() throws Exception {
		SlotHandle container = null;

		TableHandle table = createTable(100);
		// GridHandle outerGrid = (GridHandle) reportDesignHandle
		// .findElement("outerGrid");
		// container = outerGrid.getCell(1, 1).getContent();
		container = reportDesignHandle.getBody();
		// container.add(table);
	}

	@SuppressWarnings("unused")
	public static void test04() throws SemanticException, IOException {
		GridHandle gridHandle = (GridHandle) reportDesignHandle.findElement("myGrid");
		int rowCount = gridHandle.getRows().getCount(); // = 1
		System.out.println("rowCount = " + rowCount);
		int colCount = gridHandle.getColumns().getCount(); // = 1
		// System.out.println("colCount = " + colCount);
		// ############# create row
		RowHandle row = reportDesignHandle.getElementFactory().newTableRow();
		DesignElement deRow = row.getElement();
		gridHandle.getElement().add(deRow, GridItem.ROW_SLOT);

		int rowCount2 = gridHandle.getRows().getCount(); // = 2
		System.out.println("rowCount2 = " + rowCount2);
		int colCount2 = gridHandle.getColumns().getCount(); // = 1
		// System.out.println("colCount2 = " + colCount2);

		// create label for cell in row 1 / column 0
		LabelHandle lblHandle = elementFactory.newLabel(null);
		lblHandle.setText("Cell x:x");
		CellHandle cell = gridHandle.getCell(1, 0);
		cell.getContent().add(lblHandle);

		// ############# create column
		ColumnHandle column = reportDesignHandle.getElementFactory().newTableColumn();
		DesignElement deColumn = column.getElement();
		gridHandle.getElement().add(deColumn, GridItem.COLUMN_SLOT);

		int rowCount3 = gridHandle.getRows().getCount(); // = 2
		int colCount3 = gridHandle.getColumns().getCount(); // = 2

		// create label for cell in row 0 / column 1
		LabelHandle lblHandle2 = elementFactory.newLabel(null);
		lblHandle.setText("Cell y:y");
		// this.setStyle(lblHandle2, "borderSolidThin");
		CellHandle cell2 = gridHandle.getCell(0, 1);
		cell2.getContent().add(lblHandle2);
		System.out.println("Finished");
	}

	public static void test06() throws Exception {
		GridHandle gridHandle = (GridHandle) reportDesignHandle.findElement("myGrid5");
		TableHandle table = createTable(30);
		CellHandle cell2 = gridHandle.getCell(0, 1);
		cell2.getContent().add(table);
		System.out.println("Finished");
	}

	public static void test08() throws Exception {
		GridHandle gridHandle = (GridHandle) reportDesignHandle.findElement("outerGrid");
		gridHandle.getCell(0, 1).drop();
		gridHandle.getCell(1, 1).drop();
		gridHandle.getCell(2, 1).drop();
		System.out.println("Finished");
	}

	public static boolean isNumeric1(String str) {
		try {
			String numericPattern = "([0-9]*)[.][0-9]*";
			return str.matches(numericPattern);
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	public static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		generateReport();
	}
}
