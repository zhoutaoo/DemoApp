package com.jlt.jesque;

import net.greghaines.jesque.Config;
import net.greghaines.jesque.ConfigBuilder;
import net.greghaines.jesque.Job;
import net.greghaines.jesque.worker.*;

import java.util.Arrays;
import java.util.List;

import static net.greghaines.jesque.utils.JesqueUtils.entry;
import static net.greghaines.jesque.utils.JesqueUtils.map;

public class JesqueWorker {

    private final Config config;

    public JesqueWorker() {
        config = new ConfigBuilder().build();
    }

    public Worker createWork(String[] queueName, Class<?> worker) {
        List<String> queues = Arrays.asList(queueName);
        MapBasedJobFactory jobFactory = new MapBasedJobFactory(map(entry(worker.getName(), worker)));
        return new WorkerImpl(config, queues, jobFactory);
    }

    public static void main(String[] args) {

        JesqueWorker jesqueWorker = new JesqueWorker();
        Worker worker = jesqueWorker.createWork(new String[]{"queueGo", "queueDelay", "queueRecurring"}, WorkerMan.class);

        // Start a worker to run jobs from the queue
        final Thread workerThread = new Thread(worker);

        workerThread.start();

        //job事件监听
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

        try {
            worker.join(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

       /* // Wait a few secs then shutdown
        try {
            Thread.sleep(20000);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        // Give ourselves time to process
        worker.end(true);

       /* try {
            workerThread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
