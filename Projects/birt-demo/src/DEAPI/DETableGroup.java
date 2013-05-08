/*
 * Simple example that creates a table with a group
 */
package DEAPI;

import java.io.IOException;

import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.model.api.CellHandle;
import org.eclipse.birt.report.model.api.ColumnHandle;
import org.eclipse.birt.report.model.api.DataItemHandle;
import org.eclipse.birt.report.model.api.DesignConfig;
import org.eclipse.birt.report.model.api.DesignElementHandle;
import org.eclipse.birt.report.model.api.ElementFactory;
import org.eclipse.birt.report.model.api.IDesignEngine;
import org.eclipse.birt.report.model.api.IDesignEngineFactory;
import org.eclipse.birt.report.model.api.LabelHandle;
import org.eclipse.birt.report.model.api.OdaDataSetHandle;
import org.eclipse.birt.report.model.api.OdaDataSourceHandle;
import org.eclipse.birt.report.model.api.PropertyHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.RowHandle;
import org.eclipse.birt.report.model.api.SessionHandle;
import org.eclipse.birt.report.model.api.StructureFactory;
import org.eclipse.birt.report.model.api.TableGroupHandle;
import org.eclipse.birt.report.model.api.TableHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.eclipse.birt.report.model.api.command.ContentException;
import org.eclipse.birt.report.model.api.command.NameException;
import org.eclipse.birt.report.model.api.elements.DesignChoiceConstants;
import org.eclipse.birt.report.model.api.elements.structures.ComputedColumn;
import org.eclipse.birt.report.model.elements.interfaces.IReportItemModel;
import org.eclipse.birt.report.model.elements.interfaces.IStyleModel;

import com.ibm.icu.util.ULocale;

public class DETableGroup {

	private static final String fileFolder = "D:/Data-Store/Project/birt-demo/WebContent/birt/reports";

	private static final String outputReportFile = fileFolder + "/Test.rptdesign";

	ReportDesignHandle reportDesignHandle = null;

	ElementFactory elementFactory = null;

	ComputedColumn cs1, cs2, cs3, cs4, cs5, cs6 = null;

	public static void main(String[] args) throws SemanticException,
			IOException {
		new DETableGroup().createReport();
	}

	void createReport() throws SemanticException, IOException {
		
		// Configure the Engine and start the Platform
		DesignConfig config = new DesignConfig();
		IDesignEngine engine = null;
		try {

			Platform.startup(config);
			IDesignEngineFactory factory = (IDesignEngineFactory) Platform
					.createFactoryObject(IDesignEngineFactory.EXTENSION_DESIGN_ENGINE_FACTORY);
			engine = factory.createDesignEngine(config);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		SessionHandle session = engine.newSessionHandle(ULocale.ENGLISH);

		// Create a new report
		reportDesignHandle = session.createDesign();

		// Element factory is used to create instances of BIRT elements.
		elementFactory = reportDesignHandle.getElementFactory();

		createMasterPages();
		createDataSources();
		createDataSets();
		createBody();

		reportDesignHandle.saveAs(outputReportFile);//$NON-NLS-1$//$NON-NLS-2$
		reportDesignHandle.close();

		System.out.println("finished");

		Platform.shutdown();
	}

	void createDataSources() throws SemanticException {

		OdaDataSourceHandle dsHandle = elementFactory.newOdaDataSource(
				"Data Source", "org.eclipse.birt.report.data.oda.jdbc");
		dsHandle.setProperty("odaDriverClass",
				"org.eclipse.birt.report.data.oda.sampledb.Driver");
		dsHandle.setProperty("odaURL", "jdbc:classicmodels:sampledb");
		dsHandle.setProperty("odaUser", "ClassicModels");

		reportDesignHandle.getDataSources().add(dsHandle);

	}

	void createDataSets() throws SemanticException {

		OdaDataSetHandle dsHandle = elementFactory.newOdaDataSet("ds",
				"org.eclipse.birt.report.data.oda.jdbc.JdbcSelectDataSet");
		dsHandle.setDataSource("Data Source");
		dsHandle.setQueryText("Select * from ORDERDETAILS");

		reportDesignHandle.getDataSets().add(dsHandle);

	}

	private void createMasterPages() throws ContentException, NameException {
		DesignElementHandle simpleMasterPage = elementFactory
				.newSimpleMasterPage("Master Page");//$NON-NLS-1$
		reportDesignHandle.getMasterPages().add(simpleMasterPage);
	}

	private void createBody() throws SemanticException {

		TableHandle table = elementFactory.newTableItem(null, 3, 1, 1, 1);
		table.setProperty(IStyleModel.TEXT_ALIGN_PROP,
				DesignChoiceConstants.TEXT_ALIGN_CENTER);
		table.setWidth("80%");//$NON-NLS-1$
		table.setProperty(IReportItemModel.DATA_SET_PROP, "ds");//$NON-NLS-1$

		ColumnHandle ch = (ColumnHandle) table.getColumns().get(0);
		// ch.setProperty("Width", "3in");
		ch.getWidth().setStringValue("3in");

		PropertyHandle computedSet = table.getColumnBindings();
		cs1 = StructureFactory.createComputedColumn();
		cs2 = StructureFactory.createComputedColumn();
		cs3 = StructureFactory.createComputedColumn();
		cs4 = StructureFactory.createComputedColumn();
		cs5 = StructureFactory.createComputedColumn();
		cs6 = StructureFactory.createComputedColumn();

		cs1.setName("ORDERNUMBER");
		cs1.setExpression("dataSetRow[\"ORDERNUMBER\"]");
		cs1.setDataType("Integer");
		computedSet.addItem(cs1);

		cs2.setName("Product");
		cs2.setExpression("dataSetRow[\"PRODUCTCODE\"]");
		cs2.setDataType("String");
		computedSet.addItem(cs2);

		cs3.setName("Amount");
		cs3.setExpression("dataSetRow[\"QUANTITYORDERED\"]");
		cs3.setDataType("Integer");
		computedSet.addItem(cs3);

		cs4.setName("TotalAmount");
		cs4.setExpression("dataSetRow[\"QUANTITYORDERED\"]");
		cs4.setDataType("Integer");
		cs4.setAggregateFunction("SUM");
		computedSet.addItem(cs4);

		cs5.setName("GroupTotalAmount");
		cs5.setExpression("dataSetRow[\"QUANTITYORDERED\"]");
		cs5.setDataType("Integer");
		cs5.setAggregateOn("MyGroup");
		cs5.setAggregateFunction("SUM");
		computedSet.addItem(cs5);

		// Header
		RowHandle header = (RowHandle) table.getHeader().get(0);

		CellHandle tcell = (CellHandle) header.getCells().get(0);
		tcell.getWidth().setStringValue("3in");
		LabelHandle label = elementFactory.newLabel(null);
		label.setText("Order");//$NON-NLS-1$
		tcell.getContent().add(label);

		tcell = (CellHandle) header.getCells().get(1);
		label = elementFactory.newLabel(null);
		label.setText("Product");//$NON-NLS-1$
		tcell.getContent().add(label);

		tcell = (CellHandle) header.getCells().get(2);
		label = elementFactory.newLabel(null);
		label.setText("Amount");//$NON-NLS-1$
		tcell.getContent().add(label);

		// Table Group
		TableGroupHandle group = elementFactory.newTableGroup();
		group.setName("MyGroup");
		group.setKeyExpr("dataSetRow[\"ORDERNUMBER\"]");
		group.setInterval("none");
		group.setSortDirection("asc");

		table.getGroups().add(group);

		RowHandle groupHeader = elementFactory.newTableRow(3);
		tcell = (CellHandle) groupHeader.getCells().get(0);
		DataItemHandle data = elementFactory.newDataItem(null);

		data.setResultSetColumn(cs1.getName());
		tcell.getContent().add(data);
		group.getHeader().add(groupHeader);

		RowHandle groupFooter = elementFactory.newTableRow(3);
		tcell = (CellHandle) groupFooter.getCells().get(2);
		data = elementFactory.newDataItem(null);
		data.setResultSetColumn(cs5.getName());
		tcell.getContent().add(data);

		group.getFooter().add(groupFooter);
		RowHandle detail = (RowHandle) table.getDetail().get(0);
		data = elementFactory.newDataItem(null);

		tcell = (CellHandle) detail.getCells().get(1);
		data = elementFactory.newDataItem(null);
		data.setResultSetColumn(cs2.getName());
		tcell.getContent().add(data);

		tcell = (CellHandle) detail.getCells().get(2);
		data = elementFactory.newDataItem(null);
		data.setResultSetColumn(cs3.getName());
		tcell.getContent().add(data);

		RowHandle footer = (RowHandle) table.getFooter().get(0);
		tcell = (CellHandle) footer.getCells().get(2);
		data = elementFactory.newDataItem(null);
		data.setResultSetColumn(cs4.getName());
		tcell.getContent().add(data);

		reportDesignHandle.getBody().add(table);
	}
}
