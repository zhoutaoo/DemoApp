package com.jlt.jesque;

import net.greghaines.jesque.worker.Worker;
import net.greghaines.jesque.worker.WorkerEvent;
import net.greghaines.jesque.worker.WorkerListener;

import java.util.concurrent.Executors;

public class JesqueWorkerStarter {

    public static final int MILLIS = 0;
    private final JesqueWorker jesqueWorker = new JesqueWorker();
    private Worker worker;

    JesqueWorkerStarter(Class<?> workerClass, int workerNumber, Queues.Queue... queueGos) {
        worker = jesqueWorker.createWork(workerClass, workerNumber, queueGos);
    }

    public void start(EventListener workerListener) throws InterruptedException {
        Executors.newCachedThreadPool().submit(worker);
        bindingEvent(workerListener);
        worker.join(MILLIS);
    }

    public void start() throws InterruptedException {
        Executors.newCachedThreadPool().submit(worker);
        worker.join(MILLIS);
    }

    public void stop() {
        worker.end(true);
    }

    private void bindingEvent(WorkerListener workerListener) {
        worker.getWorkerEventEmitter().addListener(workerListener, WorkerEvent.values());
    }
}
