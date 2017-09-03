package util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

/**
 * Loads and provides access to the properties from the property file.
 * 
 * @author Robert Jackson
 * @version 1.0
 * @since 01/27/2017
 */
public class ConfigManager {
	private static ConfigManager defaultInstance;
	private HashMap props = new HashMap();

	private String environment = null; /* test ||qa ||prod */

	public synchronized static ConfigManager getInstance() throws IOException {
		if (defaultInstance == null) {
			defaultInstance = new ConfigManager("config.properties");
		}
		return defaultInstance;
	}

	protected static HashMap getPropertiesFromFile(String file)
			throws IOException {

		return getProperties(ConfigManager.class.getClassLoader()
				.getResourceAsStream(file));
	}

	protected static HashMap getProperties(InputStream in) throws IOException {
		try {
			Properties app = new Properties();
			app.load(in);
			HashMap map = new HashMap();
			map.putAll(app);
			return map;
		} 
		finally {
			in.close();
		}
	}

	private ConfigManager(String propFileName) throws IOException {
		ClassLoader loader = Thread.currentThread().getContextClassLoader(); 
		InputStream inputStream = loader.getResourceAsStream(propFileName);	
		props.clear();
		// The file is not in the classpath
		if(inputStream == null)  {
	           throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
	    } 
		String env = System.getProperty("environment");
		setEnvironment(env);
		props = getPropertiesFromFile(propFileName);
		if(SystemUtils.IS_OS_LINUX){
			props.put("hdfsDir", props.get("hdfsDir.linux"));
			props.put("djavaLibPath", props.get("djavaLibPath.linux"));
		}
		else if(SystemUtils.IS_OS_WINDOWS){
			props.put("hdfsDir", props.get("hdfsDir.windows"));
			props.put("djavaLibPath", props.get("djavaLibPath.windows"));
		}
		System.out.println("properties: "+props);
	}

	private String getStoredProperty(String key, String def) {
		Object o = props.get(key);
		return o == null ? def : (String) o;
	}

	private String getStoredProperty(String key) {
		return getStoredProperty(key, null);
	}

	public String getProperty(String name) {
		return getProperty(name,getEnvironment());
	} // getProperty()
	
	public String getProperty(String name, String env) {
		
		String t = getStoredProperty(env + "." + name,
				getStoredProperty(name));
		return t;
	} // getProperty()

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public Set getPropertyNames() {
		return new TreeSet(props.keySet());
	}
}