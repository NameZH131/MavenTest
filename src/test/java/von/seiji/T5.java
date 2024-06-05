package von.seiji;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class T5 {

    static List<String> list = new ArrayList<>(16);

    private void add(String arg) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        }
//        System.out.println(Thread.currentThread().getName() + ":" + arg + "[" + System.identityHashCode(arg));
        if(list.contains(arg)){
//            System.out.println("Element exists");
        }else{
            list.add(arg);
        }
    }


    public static void main(String[] args) throws InterruptedException {
        final int count = 1000;
        String[] array = IntStream.generate(() -> new Random().nextInt(10))
                .mapToObj(item -> ("员工" + item).intern())
                .limit(count).toArray(String[]::new);
//        System.out.println(array.length + "> " + Arrays.toString(array));
        CountDownLatch latch = new CountDownLatch(count);
//        ExecutorService pool = Executors.newFixedThreadPool(100);
        ExecutorService pool = Executors.newCachedThreadPool();
        T5 t5 = new T5();
        for (int i = 0; i < count; i++) {
            String staff = array[i];
            pool.execute(() -> {
                t5.add(staff);
                latch.countDown();
            });
        }
        latch.await();
        System.out.println("------------------------------------");
        System.out.println(list.size() + "> " + list);
    }
}
