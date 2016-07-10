package com.jlt.jesque;

import net.greghaines.jesque.Config;
import net.greghaines.jesque.ConfigBuilder;
import net.greghaines.jesque.Job;
import net.greghaines.jesque.client.Client;
import net.greghaines.jesque.client.ClientPoolImpl;
import net.greghaines.jesque.utils.PoolUtils;
import net.greghaines.jesque.worker.*;

import java.util.Arrays;

import static net.greghaines.jesque.utils.JesqueUtils.entry;
import static net.greghaines.jesque.utils.JesqueUtils.map;

public class JesqueMain {

    public static void main(String arg[]) {

        // Configuration
        final Config config = new ConfigBuilder().build();

        // Add a job to the queue
        final Job job = new Job(WorkerMan.class.getName(), new Object[]{1, "test"});
        final Client jesqueClientPool = new ClientPoolImpl(config, PoolUtils.createJedisPool(config));
        jesqueClientPool.enqueue("queueGo", job);

        // Add a job to the delayed queue
        final Job job1 = new Job(WorkerMan.class.getName(), new Object[]{2, "test2"});
        final long delay = 10; // in seconds
        final long future = System.currentTimeMillis() + (delay * 1000); // timestamp

        jesqueClientPool.delayedEnqueue("queueDelay", job1, future);

        // Start a worker to run jobs from the queue
        final Worker worker = new WorkerImpl(config,
                Arrays.asList("queueGo", "queueDelay"), new MapBasedJobFactory(map(entry(WorkerMan.class.getName(), WorkerMan.class))));
        final Thread workerThread = new Thread(worker);
        workerThread.start();

        //job事件监听
        worker.getWorkerEventEmitter().addListener(new WorkerListener() {
            public void onEvent(WorkerEvent event, Worker worker, String queue, Job job,
                                Object runner, Object result, Throwable t) {
                System.out.println("---------触发事件-------" + result + event.name() + "|" + worker.getName() + "|" + queue + "|" + job.getClassName());
                //
                if (runner instanceof WorkerMan) {
                    WorkerMan runner1 = (WorkerMan) runner;
                    runner1.setNum(1);
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
