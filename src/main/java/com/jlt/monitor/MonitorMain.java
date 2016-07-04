package com.jlt.monitor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Properties;
import java.util.Timer;

public class MonitorMain {

    /**
     * @URLString monitor.path=http://222.73.164.14:8080/
     */
    private static String URLString = "";

    /**
     * @SHString sh.path=/usr/local/jboss-4.2.3.GA/bin/run.sh -c all>>/usr/local/jboss-4.2.3.GA/bin/fe.log
     */
    private static String SHString = "";
    private static long beforeTask;
    private static long frequencyTask;

    private static void readProperties() {
        InputStream is = MonitorTimerTask.class.getClassLoader()
                .getResourceAsStream("config.properties");
        Properties p = new Properties();
        try {
            p.load(is);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(Calendar.getInstance().getTime()
                    + ":Load Properties fail!");
        }
        beforeTask = Long.parseLong(p.getProperty("before.task"));
        System.out.println(Calendar.getInstance().getTime() + ":" + beforeTask + " Minutes Later ,The Task Will Be Running!");
        frequencyTask = Long.parseLong(p.getProperty("frequency.task"));
        System.out.println(Calendar.getInstance().getTime() + ":The Task Will Be Running Every " + frequencyTask + " Minutes!");
        URLString = p.getProperty("monitor.path");
        System.out.println(Calendar.getInstance().getTime() + " Monitor URL:" + URLString);
        SHString = p.getProperty("sh.path");
        System.out.println(Calendar.getInstance().getTime() + ": Shell Path String: " + SHString);
    }

    public static void main(String[] args) {
        readProperties();
        Timer timer = new Timer();
        System.out.println("Monitor Start Successful!");
        timer.schedule(new MonitorTimerTask(URLString, SHString), beforeTask * 1000,
                frequencyTask * 1000);
    }

}
