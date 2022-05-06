package testData;

	import java.io.BufferedReader;
	import java.io.FileNotFoundException;
	import java.io.FileReader;
	import java.io.IOException;
	import java.util.Properties;

	public class ConfigFileReader {
		
		private Properties properties;
		private final String propertyFilePath= System.getProperty("user.dir") + "\\Source\\Configuration.properties";

		
		public ConfigFileReader(){
			BufferedReader reader;
			try {
				reader = new BufferedReader(new FileReader(propertyFilePath));
				properties = new Properties();
				try {
					properties.load(reader);
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
			}		
		}
		
		public String getBrowser(){
			String browser = properties.getProperty("browser");
			if(browser!= null) return browser;
			else throw new RuntimeException("browser not specified in the Configuration.properties file.");		
		}
		
		public long getImplicitlyWait() {		
			String implicitlyWait = properties.getProperty("implicitlyWait");
			if(implicitlyWait != null) return Long.parseLong(implicitlyWait);
			else throw new RuntimeException("implicitlyWait not specified in the Configuration.properties file.");		
		}
		
		public String getApplicationUrl() {
			String url = properties.getProperty("url");
			if(url != null) return url;
			else throw new RuntimeException("url not specified in the Configuration.properties file.");
		}
		public String getCVPath() {
			String cvPath = properties.getProperty("cvPath");
			if(cvPath != null) return cvPath;
			else throw new RuntimeException("cv Path not specified in the Configuration.properties file.");
		}
		public String getImagePath() {
			String imagePath = properties.getProperty("imagePath");
			if(imagePath != null) return imagePath;
			else throw new RuntimeException("image Path not specified in the Configuration.properties file.");
		}
}
