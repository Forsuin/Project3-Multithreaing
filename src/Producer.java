 /**
    An action that repeatedly inserts a greeting into a queue.
 */
 public class Producer implements Runnable
 {
    /**
       Constructs the producer object.
       @param aGreeting the greeting to insert into a queue
       @param aQueue the queue into which to insert greetings
       @param count the number of greetings to produce
    */
    public Producer(String aGreeting, BoundedQueue<String> aQueue, int count, int startIndex)
    {
       greeting = aGreeting;
       queue = aQueue;
       greetingCount = count;
       this.startIndex = startIndex;
   }
 
    public void run()
    {
       try
       {
          int i = 1;
          while (i <= greetingCount)
          {
             queue.add(startIndex + ": " + greeting);
             System.out.println("Producer wrote:" + greeting);
             i++;
             startIndex++;
             Thread.sleep((int) (Math.random() * DELAY));
          }
       }
       catch (InterruptedException exception)
       {
           //System.out.println("Producer interrupted, going to terminate.");
       }
    }

    private int startIndex;
    private String greeting;
    private BoundedQueue<String> queue;
    private int greetingCount;
 
    private static final int DELAY = 10;
 }