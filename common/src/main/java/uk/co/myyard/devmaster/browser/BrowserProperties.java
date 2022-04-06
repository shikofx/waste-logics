package uk.co.myyard.devmaster.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

public class BrowserProperties {

    private static final Logger LOGGER = LoggerFactory.getLogger(BrowserProperties.class);

    private static final String DEBUG_PROPERTIES = "/properties/application-debug.properties";
    private static final String CURRENT_PROPERTIES = "/properties/application.properties";

    private final String mainPageUrl;

    public BrowserProperties() {
        mainPageUrl = loadProperty("site.url");
   }

    private String loadProperty(String name) {
        Properties properties = new Properties();
        String fromResource = System
                .getProperty(BrowserProperties.CURRENT_PROPERTIES, BrowserProperties.DEBUG_PROPERTIES);

        try {
            properties.load(BrowserProperties.class.getResourceAsStream(fromResource));
        } catch (IOException e) {
            LOGGER.error(e.getLocalizedMessage());
        }
        return properties.getProperty(name);
    }

    public String getTestPageUrl() {
        return mainPageUrl;
    }
}
