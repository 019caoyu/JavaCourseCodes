
package java0.conc0302.atomic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 总结：公平锁不能保证原子性
 * 非公平锁可以保证原子性，
 * 为什么？？
 *
 */
public class SyncCount implements CountInf {

    private int num = 0;

    private Lock lock = new ReentrantLock(true);

    public int add() {
        try {
            lock.lock();
            return num++;
        } finally {
            lock.unlock();
        }
    }

    public int getNum() {
        return num;
    }
}
