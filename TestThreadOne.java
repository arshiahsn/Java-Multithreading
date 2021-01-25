import java.util.ArrayList;
import java.util.List;


class MyRunnable implements Runnable {
    private final long countUntil;


    MyRunnable(long countUntil) {
        this.countUntil = countUntil;

    }

    public void run() {
        long sum = 0;
        for (long i = 1; i < countUntil; i++) {
            sum += i;
        }
        System.out.println("Worker"+ Thread.currentThread().getId() +"out.");

    }

}





public class TestThreadOne {

    public static void main(String[] args) {
        // We will store the threads so that we can check if they are done
        List<Thread> threads = new ArrayList<Thread>();
        // We will create 500 threads
        for (int i = 0; i < 500; i++) {
            Runnable task = new MyRunnable(10000000L + i);
            Thread worker = new Thread(task);
            // We can set the name of the thread
            worker.setName(String.valueOf(i));
            // Start the thread, never call method run() direct
            worker.start();
            try {
                worker.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Remember the thread for later usage
            threads.add(worker);
        }


    }
}