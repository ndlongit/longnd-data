package com.structis.vip.server.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.ConnectException;

import org.apache.log4j.Logger;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.structis.vip.server.core.Constants;

public class PDFUtil {

    public static final String scriptFile = "doc2pdf.vbs";

    private static final Logger LOGGER = Logger.getLogger(PDFUtil.class);

    public static void createPdf(String inputFileName, String outputFileName) throws ConnectException {
        File inputFile = new File(inputFileName);
        File outputFile = new File(outputFileName);

        // connect to an OpenOffice.org instance running on port 8100
        OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
        connection.connect();

        // convert
        DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
        converter.convert(inputFile, outputFile);

        // close the connection
        connection.disconnect();
    }

    public static void createPdfWithWord(String inputFile, String path) throws Exception {
        String realScriptFile = CatalinaPropertiesUtil.getVipDirectory(path) + Constants.SCRIPT_FILE_PATH + File.separator + scriptFile;
        StringBuilder builder = new StringBuilder(0);
        builder.append("cscript");
        builder.append(" /nologo \"" + realScriptFile + "\"");
        builder.append(" /nologo \"" + inputFile + "\"");

        LOGGER.info(builder.toString());

        Process p = Runtime.getRuntime().exec(builder.toString());
        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(p.getInputStream()));

        String line;
        while ((line = bufferReader.readLine()) != null) {
            LOGGER.info(line);
            if ("OK".equalsIgnoreCase(line)) {
                break;
            }
        }
        bufferReader.close();
    }
}
