package com.jlt.jesque;

import net.greghaines.jesque.Job;
import net.greghaines.jesque.worker.Worker;
import net.greghaines.jesque.worker.WorkerEvent;
import net.greghaines.jesque.worker.WorkerListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class EventListener implements WorkerListener {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Class workerClass;

    public EventListener(Class workerClass) {
        this.workerClass = workerClass;
    }

    @Override
    public void onEvent(WorkerEvent event, Worker worker, String queue, Job job, Object runner, Object result, Throwable t) {
        if (logger.isDebugEnabled()) {
            logger.debug("Event Fire:[{}][{}][{}][{}][{}][{}]", new Object[]{event.name(), worker.getName(), queue, runner.getClass(), result, t});
        }
        if (!runner.getClass().equals(workerClass)) {
            logger.error("Error:[{}][{}][{}][{}][{}][{}]", new Object[]{event.name(), worker.getName(), queue, runner.getClass(), result, t});
            return;
        }
        switch (event) {
            case WORKER_START:
                workStart(worker, queue, job, runner, result, t);
                break;
            case WORKER_POLL:
                workPoll(worker, queue, job, runner, result, t);
                break;
            case WORKER_ERROR:
                workError(worker, queue, job, runner, result, t);
                break;
            case WORKER_STOP:
                workStop(worker, queue, job, runner, result, t);
                break;
            case JOB_PROCESS:
                jobProcess(worker, queue, job, runner, result, t);
                break;
            case JOB_EXECUTE:
                jobExecute(worker, queue, job, runner, result, t);
                break;
            case JOB_SUCCESS:
                jobSuccess(worker, queue, job, runner, result, t);
                break;
            case JOB_FAILURE:
                jobFailure(worker, queue, job, runner, result, t);
                break;
        }
    }

    public void workStart(Worker worker, String queue, Job job, Object runner, Object result, Throwable t) {
        logger.trace("Error:[{}][{}][{}][{}][{}][{}]", new Object[]{worker.getName(), job.getClassName(), queue, runner.getClass(), result, t});
    }

    public void workPoll(Worker worker, String queue, Job job, Object runner, Object result, Throwable t) {
        logger.trace("Error:[{}][{}][{}][{}][{}][{}]", new Object[]{worker.getName(), job.getClassName(), queue, runner.getClass(), result, t});
    }

    public void workError(Worker worker, String queue, Job job, Object runner, Object result, Throwable t) {
        logger.trace("Error:[{}][{}][{}][{}][{}][{}]", new Object[]{worker.getName(), job.getClassName(), queue, runner.getClass(), result, t});
    }

    public void workStop(Worker worker, String queue, Job job, Object runner, Object result, Throwable t) {
        logger.trace("Error:[{}][{}][{}][{}][{}][{}]", new Object[]{worker.getName(), job.getClassName(), queue, runner.getClass(), result, t});
    }

    public void jobProcess(Worker worker, String queue, Job job, Object runner, Object result, Throwable t) {
        logger.trace("Error:[{}][{}][{}][{}][{}][{}]", new Object[]{worker.getName(), job.getClassName(), queue, runner.getClass(), result, t});
    }

    public void jobSuccess(Worker worker, String queue, Job job, Object runner, Object result, Throwable t) {
        logger.trace("Error:[{}][{}][{}][{}][{}][{}]", new Object[]{worker.getName(), job.getClassName(), queue, runner.getClass(), result, t});
    }

    public void jobFailure(Worker worker, String queue, Job job, Object runner, Object result, Throwable t) {
        logger.trace("Error:[{}][{}][{}][{}][{}][{}]", new Object[]{worker.getName(), job.getClassName(), queue, runner.getClass(), result, t});
    }

    public void jobExecute(Worker worker, String queue, Job job, Object runner, Object result, Throwable t) {
        logger.trace("Error:[{}][{}][{}][{}][{}][{}]", new Object[]{worker.getName(), job.getClassName(), queue, runner.getClass(), result, t});
    }
}
