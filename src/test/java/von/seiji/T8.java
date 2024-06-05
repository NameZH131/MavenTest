package von.seiji;
/*
 * Created by IntelliJ IDEA.
 * User: jake
 * Date: 2024/6/6
 * Time: 上午11:34
 * To change this template use File | Settings | File Templates.
 */

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class T8 {

    /**
     * 解决办法：加 synchronized 关键字，保证线程安全。
     * 使用 原子类 AtomicInteger 代替 int 变量，保证线程安全。
     * 方法不限于以上两种，但是基本是这两种方法。以上是具体的实现， 从重量级锁 和轻量级锁
     * 自旋是通过过不断的while循环来判断锁是否被释放，直到成功获取到锁。并发量高的时候while循环会消耗大量的CPU资源，所以自旋锁适用于低并发场景。
     */

    int a = 0;
    AtomicInteger b = new AtomicInteger(0);


    private void add() {
       synchronized (this) {
           a++;
       }
    }

    private void add1() {
        b.incrementAndGet();//自旋锁 ，效率高，但是不适用于高并发场景，很有意思的一个，可以了解下底层原理，
    }

    public static void main(String[] args) throws InterruptedException {
        int count = 10000;
        T8 t8 = new T8();
        T8 t81 = new T8();
        ExecutorService pool = Executors.newFixedThreadPool(1000);//如何这块很小的话，那么并发量就小，结果更加接近预期值
        CountDownLatch latch = new CountDownLatch(count);//这个count要和子线程数量一致
        for (int i = 0; i < count; i++) {
            pool.execute(() ->{
                t8.add();
                t81.add1();
                latch.countDown();
            });
        }
        latch.await();
        System.out.println(t8.a);//输出应该是100,因为每个线程都执行了一次add()方法,不加锁，并发时只可能小于100，怎么可能大于100
        System.out.println(t81.b.get()); //以为原子类内部使用了自旋锁，所以在add1()方法中,不必枷锁synchronized
        pool.shutdown();//之前忘记加这个了，作用是关闭线程池，大概和使用iostream一样


    }
}
