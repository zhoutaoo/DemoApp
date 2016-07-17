package com.jlt.jesque;

import java.util.Date;

public class WorkerMan extends Thread {

    private int num = 0;

    public int getNum() {
        return num;
    }

    public WorkerMan(int num, String name) {
        super(name);
        System.out.println(num);
        System.out.println(name);
        this.num = num;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "|" + new Date() + "---------job processing-------------");
    }
}
