package java0.conc0303.homework;

/**
 * 应用 volatile 机制
 */
public class AsyncResultImpl1 implements AsyncResult {
    private volatile int result = -1;
    private volatile boolean hadComputed = false;
    @Override
    public int getResult() throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                result = sum();
                hadComputed = true;
            }
        }).start();

        while (!hadComputed){
            Thread.yield();// 防止一直占用线程
        }
        return result;
    }
}
