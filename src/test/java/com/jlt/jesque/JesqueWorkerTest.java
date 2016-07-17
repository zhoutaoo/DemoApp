package com.jlt.jesque;

import org.junit.Test;

public class JesqueWorkerTest {

    private final JesqueWorkerStarter jesqueWorkerStarter = new JesqueWorkerStarter();

    @Test
    public void createWork() throws Exception {
        jesqueWorkerStarter.start();
    }
}