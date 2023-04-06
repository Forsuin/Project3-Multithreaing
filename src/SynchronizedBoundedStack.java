import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedBoundedStack extends BoundedStack {

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    /**
     * Constructor for a new SynchronizedBoundedStack object.
     *
     * @param capacity : The capacity of this stack.
     */
    public SynchronizedBoundedStack(int capacity) {
        super(capacity);
    }

    /**
     * Pushes the provided element onto the top of the stack.
     *
     * @param t : The item to push.
     */
    @Override
    public void push(int t) throws InterruptedException {
        lock.lock();

        try {
            while(isFull()) {
                notFull.await();
            }
            elements[top] = t;
            top++;

            notEmpty.signalAll();
        }
        finally {
            lock.unlock();
        }
    }

    /**
     * Removes the top value from the stack and returns it.
     *
     * @return : The item popped from the stack.
     */
    @Override
    public int pop() throws InterruptedException {
        lock.lock();

        try{
            while(isEmpty()){
                notEmpty.await();
            }

            int i = elements[--top];

            notFull.signalAll();
            return i;
        }
        finally {
            lock.unlock();
        }
    }

    /**
     * Returns true if the stack is empty and false otherwise.
     *
     * @return : Whether the stack is empty.
     */
    @Override
    public boolean isEmpty() {
        return top == 0;
    }

    /**
     * Returns true if the stack is full, and false otherwise.
     *
     * @return : Whether the stack is full.
     */
    @Override
    public boolean isFull() {
        return top == capacity;
    }

    /**
     * Returns the number of elements currently on the stack.
     *
     * @return : The size of the stack.
     */
    @Override
    public int getSize() {
        return top;
    }


    public void print() {
        lock.lock();

        StringBuilder output = new StringBuilder();

        for(int i : elements){
            output.append(i).append(", ");
        }

        System.out.println(output);

        lock.unlock();
    }
}
