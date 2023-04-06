 /**
    This program runs two threads in parallel.
 */
 public class ThreadTester
 {
    public static void main(String[] args)
    {
       BoundedQueue<String> queue = new BoundedQueue<String>(10);
       final int GREETING_COUNT = 100;
       Runnable run1 = new Producer("Goodbye",
             queue, GREETING_COUNT, 50);
       Runnable run2 = new Producer("Goodbye, World!",
             queue, GREETING_COUNT, 101);
       Runnable run3 = new Consumer(queue, GREETING_COUNT);
       Runnable run4 = new Consumer(queue, GREETING_COUNT);
       
       Thread thread1 = new Thread(run1);
       Thread thread2 = new Thread(run2);
       Thread thread3 = new Thread(run3);
       Thread thread4 = new Thread(run4);
       
 
       thread1.start();
       thread2.start();
       thread3.start();
       thread4.start();
       
       //thread3.interrupt();
       
    }
 }