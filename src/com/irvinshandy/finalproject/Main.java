package com.irvinshandy.finalproject;

import com.rapplogic.xbee.api.XBeeException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Main {
    private final static Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) throws XBeeException {
        PropertyConfigurator.configure("log4j.properties");

        ConfigTuner configTuner = new ConfigTuner();

        Runnable commHandler = new CommHandler(configTuner);
        Thread thread1 = new Thread(commHandler);
        thread1.setName("Communication Handler");

        thread1.start();
    }
}