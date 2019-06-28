import java.io.*;
import java.util.*;

class Synchronized {
    public static void main(String args[]) {
        Synchronized sync = new Synchronized();
        sync.run();
    }

    public void run() {
        Stacker stacker = new Stacker(50);
        ThreadedStacker ts1 = new ThreadedStacker("1", stacker);
        ThreadedStacker ts2 = new ThreadedStacker("2", stacker);

        ts1.start();
        ts2.start();
        try {
            ts1.join();
            ts2.join();
        } catch (Exception e) {
            System.out.println("Inturrupted");
        }
    }

    public class Stacker {
        Stack<Integer> stack;

        public Stacker(int lim) {
            stack = new Stack<>();
            for(int i = 0; i < lim; ++i) {
                stack.push(i);
            }
        }

        public Integer pop() {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("Thread inturrupted.");
            }
            return stack.pop();
        }
    }

    public class ThreadedStacker extends Thread {
        Stacker stacker;
        String threadName;

        public ThreadedStacker(String threadName, Stacker stacker) {
            this.stacker = stacker;
            this.threadName = threadName;
        }

        public void run() {
            synchronized(stacker) {
                System.out.println(String.format("Thread %s popped %d", threadName, stacker.pop()));
            }
        }
    }
}
