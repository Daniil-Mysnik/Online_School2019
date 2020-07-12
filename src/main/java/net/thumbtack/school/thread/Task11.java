package net.thumbtack.school.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Task11 {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        ThePingPong thePingPong = new ThePingPong(lock);
        new PingThread(thePingPong).start();
        new PongThread(thePingPong).start();
    }
}

class ThePingPong {
    private Lock lock;
    private Condition ping;
    private Condition pong;
    private boolean flag;

    public ThePingPong(Lock lock) {
        this.lock = lock;
        this.ping = lock.newCondition();
        this.pong = lock.newCondition();
    }

    public void ping() {
        lock.lock();
        try {
            while (flag) {
                ping.await();
            }
            System.out.println("Ping");
            flag = true;
            pong.signal();
        } catch (InterruptedException e) {
            System.out.println("Interrupted exception caught!");
        }
        finally {
            lock.unlock();
        }
    }

    public void pong() {
        lock.lock();
        try {
            while (!flag) {
                pong.await();
            }
            System.out.println("    Pong");
            flag = false;
            ping.signal();
        } catch (InterruptedException e) {
            System.out.println("Interrupted exception caught!");
        } finally {
            lock.unlock();
        }
    }

}

class PingThread extends Thread {
    private ThePingPong thePingPong;

    public PingThread(ThePingPong thePingPong) {
        this.thePingPong = thePingPong;
    }

    @Override
    public void run() {
        while (true) {
            thePingPong.ping();
        }
    }
}

class PongThread extends Thread {
    private  ThePingPong thePingPong;

    public PongThread(ThePingPong thePingPong) {
        this.thePingPong = thePingPong;
    }

    @Override
    public void run() {
        while (true) {
            thePingPong.pong();
        }
    }
}
