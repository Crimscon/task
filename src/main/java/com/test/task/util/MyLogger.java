package com.test.task.util;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MyLogger {

    private static final String LOG_FILE = "log4j.properties";

    public static Logger createAndGetLogger(Object object) throws IOException {
        Logger logger = Logger.getLogger(object.getClass());
        Properties properties = new Properties();

        properties.load(new FileInputStream(LOG_FILE));
        PropertyConfigurator.configure(properties);

        return logger;
    }
}
