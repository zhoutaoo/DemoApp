package com.jlt.kata.chineseToPinyin;

import java.io.*;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: zhoutao
 * Date: 13-12-19
 * Time: 上午12:21
 * To change this template use File | Settings | File Templates.
 */
public class FileReader {
    private static Logger logger = Logger.getLogger(ChineseToPinyin.class.getName());

    public static BufferedReader getReader(String filename) {
        InputStream is = getInputStream(filename);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        return reader;
    }

    public static void closeRead(BufferedReader reader) {
        try {
            reader.close();
            //is.close();
        } catch (IOException e) {
            logger.info("关闭文件异常!");
        }
    }

    private static InputStream getInputStream(String filename) {
        InputStream is = null;
        try {
            is = new FileInputStream(filename);
        } catch (FileNotFoundException e) {
            logger.info("无此文件!");
        }
        return is;
    }
}
