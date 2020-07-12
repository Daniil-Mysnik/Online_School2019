package net.thumbtack.school.thread;

import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Task12<K, V> {
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Map<K, V> map;

    public V get(K key) {
        readWriteLock.readLock().lock();
        try {
            return map.get(key);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public void put(K key, V value) {
        readWriteLock.writeLock().lock();
        try {
            map.put(key, value);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public int size() {
        return map.size();
    }

    public void remove(K key) {
        readWriteLock.writeLock().lock();
        try {
            map.remove(key);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }
    //По сути никакой многопоточности нет

}
