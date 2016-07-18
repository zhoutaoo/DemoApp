package com.jlt.jesque;

import java.util.Date;

public class WorkerMan extends Thread {

    private int num = 0;

    public WorkerMan(int num, String name) {
        super(name);
        this.num = num;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "|" + new Date() + "---------job processing-------------");
    }
}
