package com.jlt.jesque;

public class Queues {
    interface Queue {
        String name();
    }

    public enum QUEUE_GO implements Queue {
        QUEUE_GO1,
        QUEUE_GO2
    }

    public enum QUEUE_DELAY implements Queue {
        QUEUE_DELAY1,
        QUEUE_DELAY2
    }

    public enum QUEUE_RECURRING implements Queue {
        QUEUE_RECURRING1,
        QUEUE_RECURRING2
    }
}
