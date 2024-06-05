package von.seiji;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

public class T1 {

    // 创建一个字符串列表
    List<String> list = new ArrayList<>();

    // 添加字符串到列表，如果已存在则打印错误信息
/*    private synchronized void t(String arg){
        if(list.contains(arg)) {
            System.err.println("Already exists");
        }else{
            list.add(arg);
        }
    }*/


 /*   private void t(String arg){
        synchronized (this){
            if(list.contains(arg)) {
                System.err.println("Already exists");
            }else{
                list.add(arg);
            }
        }
    }*/
//拦截的是相同的内存地址的对象？
/*    private void t(String arg){
//        System.out.println(arg.hashCode() + "<>" + System.identityHashCode(arg));
        synchronized (arg.intern()){
            System.out.println(arg + "->" + System.identityHashCode(arg) + "[" + System.identityHashCode(arg.intern()));
            if(list.contains(arg)) {
                System.err.println(arg+" " +"Already exists");
            }else{
                list.add(arg);
            }
        }
    }*/

    //再来一种方法 实现 原子性(这种的在该场和下力度太大，不建议使用)
/*    Object lock = new Object();
    private void t(String arg){
        System.out.println(arg+ "->" + lock.hashCode() + "<>" + System.identityHashCode(lock));
        synchronized (lock){
            if(list.contains(arg)) {
                System.err.println(arg+" " +"Already exists");
            }else{
                list.add(arg);
            }
        }
    }*/


    //再来一种方法 实现 原子性（这个力度也大，锁粒度和上一个一样）
   /* ReentrantLock lock = new ReentrantLock();
    private void t(String arg) {
        lock.lock();
        if (list.contains(arg)) {
            System.err.println(arg + " " + "Already exists");
        } else {
            list.add(arg);
        }
        lock.unlock();
    }
*/




    // 主函数
/*    public static void main(String[] args) throws InterruptedException {
        T1 t1 = new T1();
        int count = 10;
        CountDownLatch latch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            // 创建并启动多个线程，每个线程调用t()方法添加字符串到列表
            new Thread(() -> {
                t1.t("hello" + new Random().nextInt(10));
                latch.countDown();
            }).start();
        }

        // 等待所有线程执行完毕
        latch.await();
        System.out.println(t1.list);
    }*/





















}
