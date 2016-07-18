package com.jlt.jesque;

import net.greghaines.jesque.Job;
import net.greghaines.jesque.worker.Worker;
import org.junit.Test;

public class JesqueWorkerTest {
    @Test
    public void createWork() throws Exception {
        JesqueWorkerStarter jesqueWorkerStarter = new JesqueWorkerStarter(WorkerMan.class, 2, Queues.QUEUE_GO.QUEUE_GO1);
        EventListener eventListener = new EventListener(WorkerMan.class) {
            @Override
            public void jobExecute(Worker worker, String queue, Job job, Object runner, Object result, Throwable t) {
                System.out.println("--------" + job.getClassName());
            }
        };
        jesqueWorkerStarter.start(eventListener);
    }
}