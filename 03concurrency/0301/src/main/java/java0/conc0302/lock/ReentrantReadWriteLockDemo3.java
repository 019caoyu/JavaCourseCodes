package java0.conc0302.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockDemo3 {
    private final Map<String, Object> map = new HashMap<>();

    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public Object readWrite(String key) {
        Object value = null;
        System.out.println("1.首先开启读锁去缓存中取数据");
        rwLock.readLock().lock();
        try {
            value = map.get(key);
            if (value == null) {
                System.out.println("2.数据不存在，则释放读锁，开启写锁");
                // 锁升级
                rwLock.readLock().unlock();
                rwLock.writeLock().lock();
                try {
                    if (value == null) {
                        value = "aaaa";
                    }
                    /**
                     * 锁降级，可以使本线程一直占有锁，
                     * 如果不使用锁降级，需要释放写锁，再重新获取读锁，在这个过程中可能长时间无法获取读锁
                     * 锁降级则可以直接获取读锁，防止其他线程竞争读资源
                     */
                    System.out.println("3.设置数据成功，写锁降级降为读锁");
                    rwLock.readLock().lock();
                } finally {
                    System.out.println("4.释放写锁");
                    rwLock.writeLock().unlock();
                }
            }
        } finally {
            System.out.println("5.释放读锁");
            rwLock.readLock().unlock();
        }
        return value;
    }

    public static void main(String[] args) {
        ReentrantReadWriteLockDemo3 demo3 = new ReentrantReadWriteLockDemo3();
        demo3.readWrite("bingfabiancheng");
    }
}
