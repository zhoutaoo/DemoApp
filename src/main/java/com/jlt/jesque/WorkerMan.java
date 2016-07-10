package com.jlt.jesque;

import java.util.Date;

/**
 * Created by zhoutaoo on 7/4/16.
 */
public class WorkerMan extends Thread {

    private int num = 0;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public WorkerMan(int num, String name) {
        super(name);
        System.out.println(num);
        System.out.println(name);
    }

    @Override
    public void run() {
        System.out.println(new Date() + "---------start-------------");
    }
}
