package net.thumbtack.school.thread;

public class Task3 {
    public static void main(String args[]) {
        MyThread threadOne = new MyThread("One");
        MyThread threadTwo = new MyThread("Two");
        MyThread threadThree = new MyThread("Three");

        threadOne.start();
        threadTwo.start();
        threadThree.start();

        try {
            threadOne.join();
            threadTwo.join();
            threadThree.join();
            System.out.println("Main waiting");
        } catch (InterruptedException e) {
            System.out.println("Main thread Interrupted");
        }

        System.out.println("Main thread exiting.");
    }
}
