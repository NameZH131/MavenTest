package von.seiji;
/*
 * Created by IntelliJ IDEA.
 * User: jake
 * Date: 2024/6/6
 * Time: 上午12:10
 * To change this template use File | Settings | File Templates.
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class T2 {

    /**
     * 同样的额
     * 另外有一点需要说的是，开启太多的子线程本身也是非常打的开销，我决定用线程池优化这一点
     *
     * 看，对于并发量大的时候，锁粒度决定的耗时差异还是很大的，另外，业务 代码耗时长的话，这点差异会更加明显，而且，像刚才的测试中，合适的选择线程池大大小也很重要，
     *  单线程池线程数不多且 count值不高的情况下，  Thread.sleep(10) 这个10ms所表现的差异不明显，唉，我只能这样说了（哦，看来并非如此，我看到了差异所在），那就不能说明该行的结论了，嗯嗯，确实不能，跟胡扯一样
     *  是因为 10000次调用中能周add的只有10个，而只有add的时候耗时才高，所以不能放在这里，
     */


    // 创建一个字符串列表
    static int count = 10;
    static List<String> list = new ArrayList<>();


    public void judge1(String s) throws InterruptedException {
        synchronized (this) {
            //给你打印下更加直接的对比锁粒度的作用（这个锁粒度：每次调用都会阻塞1秒，）
            System.out.println(Thread.currentThread().getName() + " is running" + ">" + s+"----" +s.intern()+"----"+System.identityHashCode(s)+"----"+System.identityHashCode(s.intern( )));
            Thread.sleep(2000);//忘了这个，试试 模拟添加耗时，应该放在这里才明显
            if (list.contains(s)) {
//                System.out.println(s+" already exists");
            } else {//这里换行干嘛
                list.add(s);
//                System.out.println(s+" is added");
            }
        }
    }


    public void judge2(String s) throws InterruptedException {
//        synchronized (s.intern()) {//基本上可以推断出s.intern()耗时太多了，导致不明显甚至比上一个耗时还长，
        synchronized (s) {//不用intern方法了，我就说怎么耗时还是那么长
            //这个比上面的锁粒度要小，只有当相同的s（字面量相同），才会阻塞，表现是，一次可以通过好几个（不相同的s）,而上面的一次只能通过一个
            System.out.println(Thread.currentThread().getName() + " is running" + ">" + s+"----"+s.intern()+"----"+System.identityHashCode(s)+"----"+System.identityHashCode(s.intern()));

            Thread.sleep(2000);//忘了这个，试试 模拟添加耗时
            if (list.contains(s)) {
//                System.out.println(s + " already exists");
            } else {
                list.add(s);
//                System.out.println(s + " is added");
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {

        List<String> list1 = new ArrayList<>();//那就用list
        ExecutorService pool = Executors.newFixedThreadPool(100);

        //为了消除String.intern()的影响，这里先初始化100000个字符串,对的，这玩意说的就是我想说的
        for (int i = 0; i < 10; i++) {
//            System.out.println(s + "-->" + System.identityHashCode(s));
            list1.add(("hello world" + "--" + new Random().nextInt(10)).intern());//所以要这样做
        }

        T2 c = new T2();
        CountDownLatch countDownLatch = new CountDownLatch(count);
        long l = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            pool.execute(() -> {
                try {
                    c.judge1(list1.get(new Random().nextInt(list1.size())));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println("time: " + (System.currentTimeMillis() - l));
        System.out.println(list);

        list.clear();
        System.out.println("------------------------------------------------------");

        CountDownLatch countDownLatch1 = new CountDownLatch(count);
        long l1 = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            pool.execute(() -> {
                try {
                    c.judge2(list1.get(new Random().nextInt(list1.size())));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                countDownLatch1.countDown();
            });
        }
        countDownLatch1.await();
        System.out.println("time: " + (System.currentTimeMillis() - l1));
        System.out.println(list.size() + "> " + list);
        //判断是否存在重复元素（随便写个）
//        HashSet<String> ts = new HashSet(list);
//        System.out.println("是否存在重复元素：" + (list.size() != ts.size()));//由于加了锁肯定不会有重复元素


    }


}
