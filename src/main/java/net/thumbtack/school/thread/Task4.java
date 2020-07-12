package net.thumbtack.school.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Task4 {
    private static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        AddThread addThread = new AddThread(list);
        DeleteThread deleteThread = new DeleteThread(list);
        addThread.start();
        deleteThread.start();
    }

}

class Add {
    private List<Integer> list;

    public Add(List<Integer> list) {
        this.list = list;
    }

    public void add() {
        System.out.println("Adding to list");
        list.add(new Random().nextInt(9999));
    }

}

class Delete {
    private List<Integer> list;

    public Delete(List<Integer> list) {
        this.list = list;
    }

    public void delete() {
        if (list.size() > 0) {
            System.out.println("Deleting from list");
            list.remove(new Random().nextInt(list.size()));
        }
    }

}

class AddThread extends Thread {
    private List<Integer> list;

    public AddThread(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        Add add = new Add(list);
        for (int i = 0; i < 10000; i++) {
            synchronized (list) {
                add.add();
            }
        }
    }

}

class DeleteThread extends Thread {
    private List<Integer> list;

    public DeleteThread(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        Delete delete = new Delete(list);
        for (int i = 0; i < 10000; i++) {
            synchronized (list) {
                delete.delete();
            }
        }
    }

}
