/**
 * 创建日期：2013年10月25日
 * 作   者： 周涛
 *
 * 
 */
package com.jlt.freemarker;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author 周涛
 * 
 */
public class FreeMarkerMain {

	/**
	 * @param args
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static void main(String[] args) throws IOException, TemplateException {
		Configuration cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File("src/main/resources/com/jlt/freemarker/templates"));
		Map<String, String> roorMap = new HashMap<String, String>();
		roorMap.put("username", "elim");
		Template template = cfg.getTemplate("freemarker.ftl");
		template.process(roorMap, new OutputStreamWriter(System.out));
	}

}
