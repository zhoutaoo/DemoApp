package com.jlt.kettle;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;

public class KettleDemo {

	public static void main(String[] args) throws Exception {
		// 初始化环境
		KettleEnvironment.init();
		// 选择转换
		String filename = "D:/Program Files/pentaho/reponsitory/move.ktr";
		TransMeta transMeta = new TransMeta(filename);
		Trans trans = new Trans(transMeta);
		trans.execute(null);
		trans.waitUntilFinished();// 等待直到数据结束
		if (trans.getErrors() > 0) {
			System.out.println("transformation error");
		} else {
			System.out.println("transformation successfully");
		}
	}

}
