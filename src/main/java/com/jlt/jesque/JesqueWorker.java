package com.jlt.jesque;

import com.google.common.collect.Lists;
import net.greghaines.jesque.Config;
import net.greghaines.jesque.ConfigBuilder;
import net.greghaines.jesque.worker.MapBasedJobFactory;
import net.greghaines.jesque.worker.Worker;
import net.greghaines.jesque.worker.WorkerImplFactory;
import net.greghaines.jesque.worker.WorkerPool;

import java.util.List;

import static net.greghaines.jesque.utils.JesqueUtils.entry;
import static net.greghaines.jesque.utils.JesqueUtils.map;

public class JesqueWorker {

    private final Config config;

    public JesqueWorker() {
        config = new ConfigBuilder().build();
    }

    /**
     * @param worker
     * @return
     */
    private MapBasedJobFactory newMapBasedJobFactory(Class<?> worker) {
        return new MapBasedJobFactory(map(entry(worker.getName(), worker)));
    }

    /**
     * 根据队列数组,获得队列名列表
     *
     * @param queues
     * @return 队列名List
     */
    private List<String> getQueues(Queues.Queue... queues) {
        List<String> list = Lists.newArrayList();
        for (Queues.Queue queue : queues) {
            list.add(queue.name());
        }
        return list;
    }

    /**
     * 创建一个立即执行工作者worker
     *
     * @param workerClass  worker类型
     * @param workerNumber 新建worker的数量,多线程处理
     * @param queueGos     队列名,可支持同时处理多个队列
     * @return 新建一个多个线程的Worker
     */
    public Worker createWork(Class<?> workerClass, int workerNumber, Queues.Queue... queueGos) {
        WorkerImplFactory workerImplFactory = new WorkerImplFactory(config, getQueues(queueGos), newMapBasedJobFactory(workerClass));
        return new WorkerPool(workerImplFactory, workerNumber);
    }

    /**
     * 创建一个循环执行工作者worker
     *
     * @param workerClass     worker类型
     * @param workerNumber    新建worker的数量,多线程处理
     * @param queueRecurrings 队列名,可支持同时处理多个循环队列
     * @return worker
     */
    public Worker createRecurringWork(Class<?> workerClass, int workerNumber, Queues.QUEUE_RECURRING... queueRecurrings) {
        return createWork(workerClass, workerNumber, queueRecurrings);
    }

    /**
     * 创建一个延时执行工作者worker
     *
     * @param workerClass  worker类型
     * @param workerNumber 新建worker的数量,多线程处理
     * @param queueDelays  队列名,可支持同时处理多个延迟队列
     * @return worker
     */
    public Worker createDelayedWork(Class<?> workerClass, int workerNumber, Queues.QUEUE_DELAY... queueDelays) {
        return createWork(workerClass, workerNumber, queueDelays);
    }
}
