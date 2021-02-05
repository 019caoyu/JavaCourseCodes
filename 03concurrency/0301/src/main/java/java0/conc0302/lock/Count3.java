
package java0.conc0302.lock;

/**
 * 总结：
 * 死锁场景：
 * 获取多把锁的顺序和释放多把锁的顺序不一致，导致死锁
 * 产生死锁的必要条件：
 * 1. 已经获取锁，尝试获取其他锁
 * 2. 获取锁过程中不可中断或超时
 */
public class Count3 {

    private byte[] lock1 = new byte[1];
    private byte[] lock2 = new byte[1];

    public int num = 0;

    public void add() {
        synchronized (lock1) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock2) {
                num += 1;
            }
            System.out.println(Thread.currentThread().getName() + "_" + num);
        }
    }

    public void lockMethod() {
        synchronized (lock2) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock1) {
                num += 1;
            }
            System.out.println(Thread.currentThread().getName() + "_" + num);
        }
    }


}
