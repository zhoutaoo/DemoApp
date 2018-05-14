package com.jlt.kata.chineseToPinyin;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class PropertiesHandle {
    private static Logger logger = Logger.getLogger(ChineseToPinyin.class.getName());
    private Map<String, String> properties = new HashMap();

    public PropertiesHandle(String filename) {
        BufferedReader reader = com.jlt.kata.chineseToPinyin.FileReader.getReader(filename);
        String line = readLine(reader);
        while (line != null) {
            line = readLine(reader);
            addMap(line);
        }
        com.jlt.kata.chineseToPinyin.FileReader.closeRead(reader);
    }

    private String readLine(BufferedReader reader) {
        String line = "";
        try {
            line = reader.readLine();
        } catch (IOException e) {
            logger.info("读取文件异常!");
        }
        return line;
    }

    private void addMap(String line) {
        if (line == null)
            return;
        String[] keyValues = line.split("=");
        this.properties.put(keyValues[0], keyValues[1]);
    }

    private String get(String key) {
        String value = this.properties.get(key);
        return value == null ? "?" : value;
    }

    public String getPinyin(String name) {
        String allPinyin = "", firstLetter = "";
        for (int i = 0; i < name.length(); i++) {
            String pinyin = this.get(name.substring(i, i + 1));
            allPinyin = allPinyin + LetterUtil.firstUpperCase(pinyin);
            firstLetter = firstLetter + LetterUtil.firstCapitalLetter(pinyin);
        }
        return allPinyin + ";" + firstLetter;
    }
}