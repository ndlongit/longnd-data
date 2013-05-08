import java.io.File;
import java.util.logging.Level;

import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.EngineException;
import org.eclipse.birt.report.engine.api.HTMLRenderOption;
import org.eclipse.birt.report.engine.api.IRenderTask;
import org.eclipse.birt.report.engine.api.IReportDocument;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportEngineFactory;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunTask;
import org.eclipse.birt.report.engine.api.PDFRenderOption;

public class RunTask {
	private static final String fileFolder = "D:/Data-Store/Project/birt-demo/WebContent/birt/reports";

	private static final String inputReportFile = fileFolder + "/"
			+ "ScriptedDsataSet.rptdesign";

	private static final String outputReportFile = inputReportFile;

	static {
//		outputReportFile = createTempFile(fileFolder).getAbsolutePath();
//		try {
//			initDesigner();
//		} catch (BirtException e) {
//			e.printStackTrace();
//		}
	}

	private static File createTempFile(String fileFolder) {
		String tempFileName = "Temp" + Long.toString(System.nanoTime())
				+ ".tmp";
		File f = new File(fileFolder, tempFileName);
		return f;
	}

	static void executeReport() throws EngineException {

		IReportEngine engine = null;
		EngineConfig config = null;
		try {
			config = new EngineConfig();
			config.setLogConfig(null, Level.FINE);

			Platform.startup(config);
			IReportEngineFactory factory = (IReportEngineFactory) Platform
					.createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
			engine = factory.createReportEngine(config);
			engine.changeLogLevel(Level.WARNING);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		IReportRunnable runnable = null;

		runnable = engine.openReportDesign(outputReportFile);

		// Create task to run the report - use the task to execute and run the report,
		IRunTask task2 = engine.createRunTask(runnable);

		// Create rptdocument
		task2.run("D:/Temp/TOCTest.rptdocument");

		// Open rptdocument
		IReportDocument rptdoc = engine
				.openReportDocument("D:/Temp/TOCTest.rptdocument");

		// Create Render Task
		IRenderTask rtask = engine.createRenderTask(rptdoc);

		PDFRenderOption optionsPDF = new PDFRenderOption();
		optionsPDF.setOutputFileName("D:/Temp/output.pdf");
		optionsPDF.setOutputFormat(HTMLRenderOption.OUTPUT_FORMAT_PDF);
		rtask.setRenderOption(optionsPDF);

		// render
		rtask.render();

		// render the report and destroy the engine
		// Note - If the program stays resident do not shutdown the Platform or
		// the Engine
		task2.close();
		engine.shutdown();
		Platform.shutdown();
		System.out.println("Finished");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			executeReport();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
