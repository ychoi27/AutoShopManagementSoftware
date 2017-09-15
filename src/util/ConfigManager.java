package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Loads and provides access to the properties from the property file.
 * 
 * @author Robert Jackson
 * @version 1.0
 * @since 01/27/2017
 */
public class ConfigManager {
    private final Properties prop;
    private InputStream input;
    
    public ConfigManager(){
        this.prop = new Properties();
        this.input = null;
    } 
    
    public void load()
    {
        try {
            input = new FileInputStream("./config/config.properties");
            prop.load(input);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public String getProp(String propName)
    {
        return prop.getProperty(propName);
    }
    public void close(){
    if (input != null) {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        ConfigManager test1 = new ConfigManager();
        test1.load();
      //  System.out.println(test1.getProp("dbpath"));
    }
}