

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.model.api.CellHandle;
import org.eclipse.birt.report.model.api.DataItemHandle;
import org.eclipse.birt.report.model.api.DataSetHandle;
import org.eclipse.birt.report.model.api.DataSourceHandle;
import org.eclipse.birt.report.model.api.DesignConfig;
import org.eclipse.birt.report.model.api.DesignElementHandle;
import org.eclipse.birt.report.model.api.DesignFileException;
import org.eclipse.birt.report.model.api.ElementFactory;
import org.eclipse.birt.report.model.api.GridHandle;
import org.eclipse.birt.report.model.api.IDesignEngine;
import org.eclipse.birt.report.model.api.IDesignEngineFactory;
import org.eclipse.birt.report.model.api.LabelHandle;
import org.eclipse.birt.report.model.api.LibraryHandle;
import org.eclipse.birt.report.model.api.PropertyHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.ResultSetColumnHandle;
import org.eclipse.birt.report.model.api.RowHandle;
import org.eclipse.birt.report.model.api.SessionHandle;
import org.eclipse.birt.report.model.api.SimpleMasterPageHandle;
import org.eclipse.birt.report.model.api.StructureFactory;
import org.eclipse.birt.report.model.api.TableHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.eclipse.birt.report.model.api.command.ContentException;
import org.eclipse.birt.report.model.api.command.NameException;
import org.eclipse.birt.report.model.api.elements.structures.ComputedColumn;

import com.ibm.icu.util.ULocale;

public class DesignTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// create the report design engine configuration pointing to the
			// BIRT runtime
			DesignConfig dconfig = new DesignConfig();
			dconfig.setBIRTHome("C:/BIRT_RUNTIME_2_2/birt-runtime-2_2_0/ReportEngine");
			IDesignEngine engine = null;

			// try to start up the eclipse platform to load any plugins and
			// create
			// a new design engine
			Platform.startup(dconfig);
			IDesignEngineFactory factory = (IDesignEngineFactory) Platform
					.createFactoryObject(IDesignEngineFactory.EXTENSION_DESIGN_ENGINE_FACTORY);
			engine = factory.createDesignEngine(dconfig);

			// create a new session
			SessionHandle session = engine.newSessionHandle(ULocale.ENGLISH);

			// create a design or a template. Then create a report element
			// factory
			ReportDesignHandle design = session.createDesign();
			ElementFactory efactory = design.getElementFactory();

			// set my initial properties
			design.setDisplayName("my Test Report");
			design.setDescription("test");
			design.setIconFile("/templates/blank_report.gif");
			design.setFileName("c:/TEMP/sample.rptdesign");
			design.setDefaultUnits("in");
			design.setProperty("comments", "what not and what have you");

			SimpleMasterPageHandle element = efactory
					.newSimpleMasterPage("Page Master");
			DesignElementHandle footerText = efactory.newTextItem("test");
			footerText.setProperty("contentType", "html");
			footerText.setStringProperty("content", "MyTest");

			// Add in a simple page footer to our master page
			element.getPageFooter().add(footerText);

			// try to add the footer to the Master Page
			try {
				design.getMasterPages().add(element);
			} catch (ContentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NameException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// create a new grid element, and set the width to 100 percent of
			// the page design
			GridHandle grid = efactory.newGridItem(null, 1, 1);
			grid.setWidth("100%");

			// Add the grid to the report body
			design.getBody().add(grid);

			// create a new row
			RowHandle row = (RowHandle) grid.getRows().get(0);

			// Create a label and add it to the first cell.
			LabelHandle label = efactory.newLabel("Hello, world!");
			label.setText("Hello, World!");
			CellHandle cell = (CellHandle) row.getCells().get(0);
			cell.getContent().add(label);

			// save the report design
			design.saveAs("c:/TEMP/sample.rptdesign");
			design.close();
			System.out.println("Finished");
		} catch (ContentException e) {
			e.printStackTrace();
		} catch (NameException e) {
			e.printStackTrace();
		} catch (SemanticException e) {
			e.printStackTrace();
		} catch (BirtException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private boolean createReport(String reportName, List dataSetNames) {
		try {
			DesignConfig dconfig = new DesignConfig();
			DataSetHandle dataSetHandleToUse = null;
			DataSourceHandle dataSourceHandle = null;
			dconfig.setBIRTHome("C:/BIRT_RUNTIME_2_2/birt-runtime-2_2_0/ReportEngine");
			IDesignEngine dengine = null;

			// try to start up the eclipse platform
			IDesignEngineFactory factory = (IDesignEngineFactory) Platform
					.createFactoryObject(IDesignEngineFactory.EXTENSION_DESIGN_ENGINE_FACTORY);
			dengine = factory.createDesignEngine(dconfig);

			// create a new session, open the library, and retrieve the first
			// data source since it is uniform in our library
			SessionHandle session = dengine.newSessionHandle(ULocale.ENGLISH);
			LibraryHandle design = session
					.openLibrary("C:/eclipse/GWTBirt/BIRTGwt/src/reports/DataSets.rptlibrary");
			dataSourceHandle = (DataSourceHandle) design.getDataSources()
					.get(0);

			// create a new report
			ReportDesignHandle reportDesign = session.createDesign();
			reportDesign.getDataSources().add(dataSourceHandle);

			// find the correct data set based on dateSetName
			int dataSetCount = 0;
			for (Iterator dataSetIterator = dataSetNames.iterator(); dataSetIterator
					.hasNext();) {
				dataSetCount++;
				String dataSetName = (String) dataSetIterator.next();

				for (Iterator i = design.getDataSets().iterator(); i.hasNext();) {
					DataSetHandle dataSetHandle = (DataSetHandle) i.next();

					if (dataSetHandle.getName().contains(dataSetName)) {

						dataSetHandleToUse = dataSetHandle;
						dataSetHandleToUse.setName(dataSetHandle.getName());
					}
				}

				// Add the current data set to the report design
				boolean hasDataSetAlready = false;
				for (Iterator i = reportDesign.getDataSets().iterator(); i
						.hasNext();) {
					DataSetHandle dataSetInReport = (DataSetHandle) i.next();

					if (dataSetInReport.getName().equalsIgnoreCase(
							dataSetHandleToUse.getName())) {
						hasDataSetAlready = true;
					}
				}
				if (hasDataSetAlready == false)
					reportDesign.getDataSets().add(dataSetHandleToUse);

				// get the columns from the selected dataset
				List columnList = new ArrayList();
				for (Iterator i = dataSetHandleToUse.getCachedMetaDataHandle()
						.getResultSet().iterator(); i.hasNext();) {
					ResultSetColumnHandle colInfo = (ResultSetColumnHandle) i
							.next();

					columnList.add(colInfo.getColumnName());
				}

				// create new table, set the data set
				TableHandle reportTable = reportDesign.getElementFactory()
						.newTableItem(
								"testTable" + dataSetHandleToUse.getName(),
								columnList.size());
				reportTable.setWidth("100%");
				reportTable.setDataSet(dataSetHandleToUse);

				// create a new detail row and add to the report
				RowHandle detailRow = (RowHandle) reportTable.getDetail()
						.get(0);
				int x = 0; // used to mark current column position

				// go through column list and create a new column binding,
				// otherwise data will not be populated into the report
				// Then add a new column to our row
				for (Iterator i = columnList.iterator(); i.hasNext();) {
					String columnName = (String) i.next();

					ComputedColumn computedColumn = StructureFactory
							.createComputedColumn();
					computedColumn.setName(columnName);
					computedColumn.setExpression("dataSetRow[\"" + columnName
							+ "\"]");
					PropertyHandle computedSet = reportTable
							.getColumnBindings();
					reportTable.getColumnBindings().addItem(computedColumn);

					// add new data item and cell
					DataItemHandle data = reportDesign.getElementFactory()
							.newDataItem(columnName);
					data.setResultSetColumn(columnName);
					CellHandle cell = (CellHandle) detailRow.getCells().get(x);
					cell.getContent().add(data);
					x++; // advance position
				}

				// add the table to my report
				reportDesign.getBody().add(reportTable);
			}
			// set my initial properties for the new report
			reportDesign.setDisplayName(reportName);
			reportDesign.setDescription(reportName);
			reportDesign.setIconFile("/templates/blank_report.gif");
			reportDesign.setFileName("C:/eclipse/GWTBirt/BIRTGwt/src/reports/"
					+ reportName + ".rptdesign");
			reportDesign.setDefaultUnits("in");
			reportDesign.setProperty("comments", reportName);
			reportDesign.setProperty(IReportRunnable.TITLE, reportName);

			// save report design
			reportDesign.saveAs("C:/eclipse/GWTBirt/BIRTGwt/src/reports/"
					+ reportName + ".rptdesign");

			return true;
		} catch (ContentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DesignFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SemanticException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}
}
