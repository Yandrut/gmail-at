package org.yandrut.gmail_at.drivers.utils;

import java.util.ResourceBundle;

public class DataReader {

    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle(System.getProperty("env", "test"));

    private DataReader() {
    }

    public static String getData(String key) {
        return resourceBundle.getString(key);
    }
}