public class Main {
    public static void main(String[] args) {
        SynchronizedBoundedStack stack = new SynchronizedBoundedStack(100);

        Thread p1 = new Thread(new Runnable() {
            int i = 1;

            @Override
            public void run() {
                while (true) {
                    try {
                        stack.push(i);
                        System.out.printf("Producer 1 pushed %d to stack\n", i);
                        i++;
                        stack.print();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });

        Thread p2 = new Thread(new Runnable() {
            int i = 101;

            @Override
            public void run() {
                while (true){
                    try {
                        stack.push(i);
                        System.out.printf("Producer 2 pushed %d to stack\n", i);
                        i++;
                        stack.print();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });

        Thread c1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.printf("Consumer 1 popped %d from the stack\n", stack.pop());
                        stack.print();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });

        Thread c2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.printf("Consumer 1 popped %d from the stack\n", stack.pop());
                        stack.print();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });

        p1.start();
        p2.start();
        c1.start();
        c2.start();
    }
}
