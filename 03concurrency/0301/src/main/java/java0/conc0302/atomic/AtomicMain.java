
package java0.conc0302.atomic;

/**
 * SyncCount 使用公平锁处理共享资源，
 * 由于公平锁效率较低，所有5s内没有完成计算，就返回总和，所以结果错误.
 * 同样时间非公平锁5s内可以完成计算，结果正确。
 * 时间增加到10s后结果正确。
 *
 */
public class AtomicMain {

    public static void main(String[] args) {
        final CountInf count = new SyncCount();
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        count.add();
                    }
                }
            }).start();
        }

        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("num=" + count.getNum());
    }

}
