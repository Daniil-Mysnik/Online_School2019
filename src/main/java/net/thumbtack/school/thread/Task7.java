package net.thumbtack.school.thread;

import java.util.concurrent.Semaphore;

public class Task7 {
    public static void main(String[] args) {
        PingPong pingPong = new PingPong();

        Thread ping = new Thread(new Ping(pingPong));
        Thread pong = new Thread(new Pong(pingPong));

        ping.start();
        pong.start();
    }
}

class PingPong {
    private static Semaphore semPing = new Semaphore(1);
    private static Semaphore semPong = new Semaphore(0);

    public void ping() {
        try {
            semPing.acquire();
            System.out.println("ping");
        } catch (InterruptedException ex) {
            System.out.println("InterruptedException caught");
        } finally {
            semPong.release();
        }
    }

    public void pong() {
        try {
            semPong.acquire();
            System.out.println("    pong");
        } catch (InterruptedException e) {
            System.out.println("InterruptedException caught");
        } finally {
            semPing.release();
        }
    }
}

class Ping extends Thread {
    private PingPong pingPong;

    public Ping(PingPong pingPong) {
        this.pingPong = pingPong;
    }

    @Override
    public void run() {
        while (true) {
            pingPong.ping();
        }
    }
}

class Pong extends Thread {
    private PingPong pingPong;

    public Pong(PingPong pingPong) {
        this.pingPong = pingPong;
    }

    @Override
    public void run() {
        while (true) {
            pingPong.pong();
        }
    }
}
