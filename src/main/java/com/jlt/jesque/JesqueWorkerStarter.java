package com.jlt.jesque;

import net.greghaines.jesque.Job;
import net.greghaines.jesque.worker.Worker;
import net.greghaines.jesque.worker.WorkerEvent;
import net.greghaines.jesque.worker.WorkerListener;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JesqueWorkerStarter {

    private final JesqueWorker jesqueWorker = new JesqueWorker();
    private Worker worker;

    JesqueWorkerStarter() {
        worker = jesqueWorker.createWork(WorkerMan.class, 2, Queues.QUEUE_GO.QUEUE_GO1, Queues.QUEUE_GO.QUEUE_GO2);
    }

    public void start() throws InterruptedException {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.submit(worker);
        jobSuccess();
        worker.join(0);
        worker.end(true);
    }

    public void jobSuccess() {
        worker.getWorkerEventEmitter().addListener(new WorkerListener() {
            public void onEvent(WorkerEvent event, Worker worker, String queue, Job job,
                                Object runner, Object result, Throwable t) {
                System.out.println("---------触发事件-------" + Thread.currentThread().getName() + "|" + result + event.name() + "|" + worker.getName() + "|" + queue + "|" + job.getClassName());
                if (runner instanceof WorkerMan) {
                    WorkerMan runner1 = (WorkerMan) runner;
                    System.out.println("---------work-------" + runner1.getNum());
                }
            }
        }, WorkerEvent.JOB_SUCCESS);
    }

}
