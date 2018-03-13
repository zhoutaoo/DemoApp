package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhoutaoo on 06/06/2017.
 */
public class UnsafeConcurrentDemo implements Concurrent {
    private static long counter = 0;

    public long getCounter() {
        return counter;
    }

    public void addCounter() {
        this.counter++;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 100; i++) {
            executorService.submit(new Counter(new UnsafeConcurrentDemo()));
        }
        executorService.shutdown();
    }
}


