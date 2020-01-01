package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyHandling {
	
	public static String currdir=System.getProperty("user.dir");
	public static String readProperty(String propertyfile, String key) throws IOException{
		FileInputStream fis=new FileInputStream(currdir+"//src//main//java//config//"+propertyfile);
		Properties prop=new Properties();
		prop.load(fis);
		return prop.getProperty(key);
	}
}
