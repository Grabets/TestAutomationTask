package Core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class TestDataProvider {

    private static final String DEFAULT_PREFIX = "default";
    private static final Logger LOGGER = Logger.getLogger(TestDataProvider.class.getName());

    private Properties prop = new Properties();
    private String prefix = DEFAULT_PREFIX;

    public TestDataProvider() {
        InputStream input = null;
        try {
            prop.load(TestDataProvider.class.getClassLoader().getResourceAsStream("testData.properties"));
        } catch (IOException ex) {
            LOGGER.warning("Failed to read test data property file");
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    LOGGER.warning("Failed to close stream for test data property file");
                }
            }
        }
    }

    public TestDataProvider(String defaultPrefix) {
        this();
        this.prefix = defaultPrefix;
    }

    public String getProperty(String propertyName) {
        String effectivePropertyName = prefix + "." + propertyName;
        String propertyFromFile = prop.getProperty(effectivePropertyName);
        return propertyFromFile != null ? propertyFromFile : prop.getProperty(DEFAULT_PREFIX + "." + propertyName);
    }


}