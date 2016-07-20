package com.jlt.jesque;

import net.greghaines.jesque.worker.Worker;
import net.greghaines.jesque.worker.WorkerEvent;
import net.greghaines.jesque.worker.WorkerListener;

public class JesqueWorkerStarter {

    public static final int MILLIS = 0;
    private final JesqueWorker jesqueWorker = new JesqueWorker();
    private Worker worker;

    JesqueWorkerStarter(Class<?> workerClass, int workerNumber, Queues.Queue... queueGos) {
        worker = jesqueWorker.createWork(workerClass, workerNumber, queueGos);
    }

    public void start(EventListener workerListener) throws InterruptedException {
        new Thread(worker).start();
        bindingEvent(workerListener);
        worker.join(MILLIS);
    }

    public void start() throws InterruptedException {
        new Thread(worker).start();
        worker.join(MILLIS);
    }

    public void stop() {
        worker.end(true);
    }

    private void bindingEvent(WorkerListener workerListener) {
        worker.getWorkerEventEmitter().addListener(workerListener, WorkerEvent.values());
    }
}
