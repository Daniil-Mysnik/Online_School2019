package net.thumbtack.school.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Task6 {
    private static List<Integer> list = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) {
        Thread addThread = new Thread(new AddThread1(list));
        Thread deleteThread = new Thread(new DeleteThread1(list));

        addThread.start();
        deleteThread.start();
    }
}

class AddThread1 extends Thread {
    private List<Integer> list;

    public AddThread1(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            list.add(new Random().nextInt(9999));
            System.out.println("Adding to list");
        }
    }
}

class DeleteThread1 extends Thread {
    private List<Integer> list;

    public DeleteThread1(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            if (list.size() > 0) {
                list.remove(new Random().nextInt(list.size()));
                System.out.println("Deleting from list");
            }
        }
    }
}


