package com.jlt.game.chineseToPinyin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * PropertiesHandle Tester.
 *
 * @author <Authors name>
 * @version 1.0
 */
public class PropertiesHandleTest {
    private PropertiesHandle propertiesHandle;

    @Before
    public void setup() {
        propertiesHandle = new PropertiesHandle("D:\\Work\\Workspaces\\Ideas\\DemoApp\\src\\main\\resources\\com\\jlt\\game\\chineseToPinyin\\chinesePinyinMap.properties");
    }

    @Test
    public void testGetProperties() throws Exception {
        String pinyin = propertiesHandle.getPinyin("章子怡");
        Assert.assertEquals("ZhangZiYi;ZZY", pinyin);
    }

    @Test
    public void testGetProperties_unknow() throws Exception {
        String pinyin = propertiesHandle.getPinyin("章子中");
        Assert.assertEquals("ZhangZi?;ZZ?", pinyin);
    }

} 
