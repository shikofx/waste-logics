package uk.co.myyard.devmaster.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class TestLogger {

    public static void log(Class<?> clazz, String level, String msg) {
        final Logger LOGGER = LoggerFactory.getLogger(clazz);

        switch (level) {
            case "info" -> LOGGER.info(msg);
            case "error" -> LOGGER.error(msg);
            case "debug" -> LOGGER.debug(msg);
        }
    }

}
