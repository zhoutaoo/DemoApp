package concurrent;

public class Counter implements Runnable {
    Concurrent concurrent;

    public Counter(Concurrent concurrent) {
        this.concurrent = concurrent;
    }

    public void run() {
        for (int i = 0; i < 50; i++) {
            concurrent.addCounter();
            System.out.println(Thread.currentThread().getName() + ":" + concurrent.getCounter());
        }
    }
}