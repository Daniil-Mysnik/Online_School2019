package net.thumbtack.school.thread;

public class Task2 {
    public static void main(String[] args) {
        MyThread myThread = new MyThread("Secondary");
        Thread thread = new Thread(myThread);
        thread.start();

        try {
            thread.join();
            System.out.println("Main waiting");
        } catch (InterruptedException e) {
        }

        System.out.println("Main thread exiting.");
    }
}

class MyThread extends Thread {
    private String name;

    public MyThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + " went to sleep");
        try {
            sleep(5000);
        } catch (InterruptedException e) {
        }
        System.out.println(name + " completed the case");
    }
}
