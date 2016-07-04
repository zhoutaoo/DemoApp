package com.jlt.dubbo;

public class DemoServiceImpl implements DemoService {

	public String sayHello(String name) {
		System.out.println("return:"+name); // 显示调用结果
		return "Hello " + name;
	}

}