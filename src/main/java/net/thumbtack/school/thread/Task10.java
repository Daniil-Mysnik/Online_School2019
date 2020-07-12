package net.thumbtack.school.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Task10 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Lock lock = new ReentrantLock();
        new AddThreadWithLock(list, lock).start();
        new DeleteThreadWithLock(list, lock).start();
    }
}

class AddThreadWithLock extends Thread {
    private List<Integer> list;
    private Lock lock;

    public AddThreadWithLock(List<Integer> list, Lock lock) {
        this.list = list;
        this.lock = lock;
    }

    @Override
    public void run() {
        Add add = new Add(list);
        for (int i = 0; i < 10000; i++) {
            try {
                lock.lock();
                add.add();
            } finally {
                lock.unlock();
            }
        }
    }
}

class DeleteThreadWithLock extends Thread {
    private List<Integer> list;
    private Lock lock;

    public DeleteThreadWithLock(List<Integer> list, Lock lock) {
        this.list = list;
        this.lock = lock;
    }

    @Override
    public void run() {
        Delete delete = new Delete(list);
        for (int i = 0; i < 10000; i++) {
            try {
                lock.lock();
                delete.delete();
            } finally {
                lock.unlock();
            }
        }
    }
}
