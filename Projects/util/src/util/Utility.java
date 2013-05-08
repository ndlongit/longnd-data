package util;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Properties;

public class Utility {

	public static Properties loadPropertiesFile(String fileName) {
		Properties prop = new Properties();
		try {
			InputStream in = new FileInputStream(fileName);
			prop.load(in);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop;
	}

	public static void readFile(String fileName) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(fileName));
			String aLine = reader.readLine();
			while (aLine != null) {
				System.out.println(aLine);
				aLine = reader.readLine();
			}
		} catch (Exception e) {

		}
	}
}
