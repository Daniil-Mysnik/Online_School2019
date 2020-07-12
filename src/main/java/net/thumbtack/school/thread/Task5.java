package net.thumbtack.school.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Task5 {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        AddAndDelete addAndDelete = new AddAndDelete(list);
        new AddAndDeleteThread(Action.ADD, addAndDelete).start();
        new AddAndDeleteThread(Action.DELETE, addAndDelete).start();
    }

}

enum Action {
    ADD,
    DELETE;
}

class AddAndDelete {
    private List<Integer> list;

    public AddAndDelete(List<Integer> list) {
        this.list = list;
    }

    public synchronized void add() {
        list.add(new Random().nextInt(100));
        System.out.println("Adding to list");
    }

    public synchronized void delete() {
        if (list.size() > 0) {
            list.remove(new Random().nextInt(list.size()));
            System.out.println("Deleting from list");
        }
    }

}

class AddAndDeleteThread extends Thread {
    private Action action;
    private AddAndDelete addAndDelete;

    public AddAndDeleteThread(Action action, AddAndDelete addAndDelete) {
        this.action = action;
        this.addAndDelete = addAndDelete;
    }

    @Override
    public void run() {
        switch (action) {
            case ADD: {
                for (int i = 0; i < 10000; i++) {
                    addAndDelete.add();
                }
            }
            case DELETE: {
                for (int i = 0; i < 10000; i++) {
                    addAndDelete.delete();
                }
            }
            break;
        }
    }

}
