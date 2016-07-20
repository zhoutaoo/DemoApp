package com.jlt.jesque;

import net.greghaines.jesque.Config;
import net.greghaines.jesque.ConfigBuilder;
import net.greghaines.jesque.Job;
import net.greghaines.jesque.client.Client;
import net.greghaines.jesque.client.ClientPoolImpl;
import net.greghaines.jesque.utils.PoolUtils;

public class JesqueClient {

    private final Config config;
    private final Client jesqueClientPool;
    private static final int MS_UNIT = 1000;

    private String host = "127.0.0.1";
    private int port = 6379;
    private String password = "password";

    public JesqueClient() {
        config = new ConfigBuilder().withHost(host).withPort(port).withPassword(password).build();
        jesqueClientPool = new ClientPoolImpl(config, PoolUtils.createJedisPool(config));
    }

    /**
     * 添加job到立即执行的队列中
     *
     * @param enqueueName 立即执行的队列名
     * @param job         Job,要执行工作
     */
    public void addEnqueue(Queues.QUEUE_GO enqueueName, Job job) {
        jesqueClientPool.enqueue(enqueueName.name(), job);
    }

    /**
     * 添加job到延迟执行队列中
     *
     * @param enqueueName 延迟队列名
     * @param job         Job,要执行工作
     * @param delay       延迟执行的时间,单位秒
     */
    public void addDelayedEnqueue(Queues.QUEUE_DELAY enqueueName, Job job, int delay) {
        jesqueClientPool.delayedEnqueue(enqueueName.name(), job, System.currentTimeMillis() + delay * MS_UNIT);
    }

    /**
     * 添加job到循环执行队列中
     *
     * @param enqueueName 定时执行队列名
     * @param job         Job,要执行工作
     * @param delay       延迟循环的时间,单位秒
     * @param frequency   执行频率,单位秒
     */
    public void addRecurringEnqueue(Queues.QUEUE_RECURRING enqueueName, Job job, int delay, int frequency) {
        jesqueClientPool.recurringEnqueue(enqueueName.name(), job, System.currentTimeMillis() + delay * MS_UNIT, frequency * MS_UNIT);
    }

    /**
     * 删除指定的延迟队列的job
     *
     * @param enqueueName 延迟队列名
     * @param job         Job,要取消执行工作
     */
    public void removeEnqueue(Queues.QUEUE_DELAY enqueueName, Job job) {
        jesqueClientPool.removeDelayedEnqueue(enqueueName.name(), job);
    }

    /**
     * 删除指定的循环队列的job
     *
     * @param enqueueName 循环队列名
     * @param job         Job,要取消执行工作
     */
    public void removeEnqueue(Queues.QUEUE_RECURRING enqueueName, Job job) {
        jesqueClientPool.removeRecurringEnqueue(enqueueName.name(), job);
    }
}
