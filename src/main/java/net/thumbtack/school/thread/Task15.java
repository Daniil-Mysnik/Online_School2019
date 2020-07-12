package net.thumbtack.school.thread;

import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Task15 {
    public static void main(String[] args) {
        int producersCount = 3;
        int consumersCount = 10;

        BlockingQueue<Data> dataBlockingQueue = new LinkedBlockingQueue<>();
        Thread[] producers = new Thread[producersCount];

        for (int i = 0; i < consumersCount; i++) {
            new Thread(new ConsumerThread(dataBlockingQueue)).start();
        }

        for (int i = 0; i < producersCount; i++) {
            producers[i] = new Thread(new ProducerThread(dataBlockingQueue, 5));
            producers[i].start();
        }

        for (int i = 0; i < producersCount; i++) {
            try {
                producers[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < consumersCount; i++) {
            try {
                dataBlockingQueue.put(new Poison());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class ProducerThread implements Runnable {
    private BlockingQueue<Data> queue;
    private int dataCount;

    public ProducerThread(BlockingQueue<Data> queue, int dataCount) {
        this.queue = queue;
        this.dataCount = dataCount;
    }

    public void run() {
        try {
            for (int i = 1; i <= dataCount; i++) {
                int[] data = getPrevious(i);
                queue.put(new Data(data));
                System.out.println("Producer added. Data: " + Arrays.toString(data));

            }
            System.out.println("Producer finished");
        } catch (InterruptedException exc) {
            exc.printStackTrace();
        }
    }

    private int[] getPrevious(int a) {
        int[] res = new int[a];
        for (int i = 0; i < a; i++) {
            res[i] = i;
        }
        return res;
    }

}

class ConsumerThread implements Runnable {
    private BlockingQueue<Data> queue;

    public ConsumerThread(BlockingQueue<Data> queue) {
        this.queue = queue;
    }

    public void run() {
        while (true) {
            try {
                Data data = queue.take();
                if (data instanceof Poison) {
                    System.out.println("Consumer poisoned");
                    break;
                }
                System.out.println("Consumer get. " + data.toString());
            } catch (InterruptedException exc) {
                exc.printStackTrace();
            }
        }
    }

}

class Data {
    private int[] data;

    public Data() {
    }

    public Data(int[] data) {
        this.data = data;
    }

    public int[] get() {
        return data;
    }

    @Override
    public String toString() {
        return "Data: " + Arrays.toString(data);
    }

}

class Poison extends Data {
}