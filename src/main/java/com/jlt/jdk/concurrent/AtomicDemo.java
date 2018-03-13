package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhoutaoo on 06/06/2017.
 */
public class AtomicDemo implements Concurrent {
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public long getCounter() {
        return atomicInteger.get();
    }

    @Override
    public void addCounter() {
        atomicInteger.addAndGet(1);
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 100; i++) {
            executorService.submit(new Counter(new AtomicDemo()));
        }
        executorService.shutdown();
    }
}

