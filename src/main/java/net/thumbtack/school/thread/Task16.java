package net.thumbtack.school.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Task16 {
    public static void main(String[] args) {
        int numOfDevelopers = 3;
        int numOfExecutors = 5;
        BlockingQueue<Task> queue = new LinkedBlockingQueue<>();
        Thread[] developers = new Thread[numOfDevelopers];
        Thread[] executors = new Thread[numOfExecutors];

        for (int i = 0; i < numOfExecutors; i++) {
            executors[i] = new Thread(new Executor(queue));
            executors[i].start();
        }

        for (int i = 0; i < numOfDevelopers; i++) {
            developers[i] = new Thread(new Developer(queue, 5));
            developers[i].start();
        }

        for (int i = 0; i < numOfDevelopers; i++) {
            try {
                developers[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < numOfExecutors; i++) {
            try {
                queue.put(new Poison2());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class Developer implements Runnable {
    private BlockingQueue<Task> queue;
    private int count;

    public Developer(BlockingQueue<Task> queue, int count) {
        this.queue = queue;
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 1; i < count + 1; i++) {
            try {
                queue.put(new Task(i));
                System.out.println("Developer posted Task" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Executor implements Runnable {
    private BlockingQueue<Task> queue;

    public Executor(BlockingQueue<Task> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Task task = queue.take();
                if (task instanceof Poison2)
                    break;
                task.execute();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Task implements Executable {
    private int number;

    public Task(int number) {
        this.number = number;
    }

    public Task() {
    }

    @Override
    public void execute() {
        System.out.println("Executor complete Task" + number);
    }
}

interface Executable {
    void execute();
}

class Poison2 extends Task {
}
