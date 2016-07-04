package com.jlt.game.chineseToPinyin;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: zhoutao
 * Date: 13-12-16
 * Time: 下午9:28
 * To change this template use File | Settings | File Templates.
 */
public class ChineseToPinyin {
    private static Logger logger = Logger.getLogger(ChineseToPinyin.class.getName());

    public static void main(String[] args) throws IOException {
        PropertiesHandle propertiesHandle = new PropertiesHandle("D:\\Work\\Workspaces\\Ideas\\DemoApp\\src\\main\\resources\\com\\jlt\\game\\chineseToPinyin\\chinesePinyinMap.properties");
        String result = propertiesHandle.getPinyin("章子怡好");
        logger.info(result);
    }


}
