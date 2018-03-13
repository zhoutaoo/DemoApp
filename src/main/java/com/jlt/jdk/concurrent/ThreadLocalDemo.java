package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhoutaoo on 06/06/2017.
 */
public class ThreadLocalDemo implements Concurrent {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<Long>() {
        protected Long initialValue() {
            return 0L;
        }
    };

    @Override
    public long getCounter() {
        return threadLocal.get();
    }

    @Override
    public void addCounter() {
        threadLocal.set(threadLocal.get() + 1);
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            executorService.submit(new Counter(new ThreadLocalDemo()));
        }
        executorService.shutdown();
    }
}

