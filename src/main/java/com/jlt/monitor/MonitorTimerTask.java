package com.jlt.monitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.TimerTask;

public class MonitorTimerTask extends TimerTask {

    private String URLString = "";
    private String SHString = "";

    MonitorTimerTask(String URLString, String SHString) {
        this.URLString = URLString;
        this.SHString = SHString;
    }

    @Override
    public void run() {
        IsRun();
    }

    private boolean IsRun() {
        HttpURLConnection conn = null;
        BufferedReader br = null;
        String html = "";
        try {
            URL url = new URL(URLString);
            //URL url = new URL("http://localhost:8080/");
            conn = (HttpURLConnection) url.openConnection();
            br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String str = br.readLine();
            while (str != null) {
                html = html + str + '\n';
                str = br.readLine();
            }

        } catch (Exception e) {
            System.out.println(Calendar.getInstance().getTime() + ":Check Site Exception");
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (Exception e) {
                System.out.println(Calendar.getInstance().getTime() + ":Close InputStream Exception");
                e.printStackTrace();
            }
            conn.disconnect();
        }

        if (html.length() < 1) {
            System.out.println(Calendar.getInstance().getTime() + ":The Site is ShutDown Now,Starting");
            ExecuteBat();
        } else {
            System.out.println(Calendar.getInstance().getTime() + ":The Site is Running");
        }
        return false;
    }

    private void ExecuteBat() {
        //String batPath="/usr/local/jboss-4.2.3.GA/bin/run.sh -c all&";

        String[] cmdArray = new String[]{"/bin/sh", "-c", SHString};

        Runtime rt = Runtime.getRuntime();
        try {
            Process p = rt.exec(cmdArray);
            p.waitFor();
            int i = p.exitValue();
            if (i == 0) {
                System.out.println("Restart Successful!!");
            } else {
                System.out.println("Restart Fail!!");
            }
        } catch (IOException e) {
            System.out.println(Calendar.getInstance().getTime() + ":Starting Exception");
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
