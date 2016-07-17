package com.jlt.jesque;

import net.greghaines.jesque.Job;
import org.junit.Test;

import java.util.Date;
import java.util.Random;

public class JesqueClientTest {

    private JesqueClient jesqueClient = new JesqueClient();

    @Test
    public void testAddEnqueue() throws Exception {
        for (int i = 0; i < 1000; i++) {
            Job goJob = new Job(WorkerMan.class.getName(), new Object[]{new Random().nextInt(), new Date().toString()});
            jesqueClient.addEnqueue(Queues.QUEUE_GO.QUEUE_GO1, goJob);
        }
    }

    @Test
    public void testAddDelayedEnqueue() throws Exception {
        Job delayJob = new Job(WorkerMan.class.getName(), new Object[]{new Random().nextInt(), new Date()});
        jesqueClient.addDelayedEnqueue(Queues.QUEUE_DELAY.QUEUE_DELAY1, delayJob, 30);
    }

    @Test
    public void testAddRecurringEnqueue() throws Exception {
        Job recurringJob = new Job(WorkerMan.class.getName(), new Object[]{new Random().nextInt(), new Date().toString()});
        jesqueClient.addRecurringEnqueue(Queues.QUEUE_RECURRING.QUEUE_RECURRING1, recurringJob, 30, 5);
    }

    @Test
    public void testRemoveDelayedEnqueue() throws Exception {
        Job delayJob = new Job(WorkerMan.class.getName(), new Object[]{new Random().nextInt(), new Date()});
        Job delayJob1 = new Job(WorkerMan.class.getName(), new Object[]{new Random().nextInt(), new Date()});
        jesqueClient.addDelayedEnqueue(Queues.QUEUE_DELAY.QUEUE_DELAY1, delayJob, 60);
        jesqueClient.addDelayedEnqueue(Queues.QUEUE_DELAY.QUEUE_DELAY1, delayJob1, 120);
        jesqueClient.removeEnqueue(Queues.QUEUE_DELAY.QUEUE_DELAY1, delayJob);
    }

    @Test
    public void testRemoveRecurringEnqueue() throws Exception {
        Job recurringJob = new Job(WorkerMan.class.getName(), new Object[]{new Random().nextInt(), new Date().toString()});
        jesqueClient.removeEnqueue(Queues.QUEUE_RECURRING.QUEUE_RECURRING1, recurringJob);
    }
}