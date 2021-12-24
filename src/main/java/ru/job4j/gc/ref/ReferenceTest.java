package ru.job4j.gc.ref;

import ru.job4j.collections.question.User;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

public class ReferenceTest {

    public static void main(String[] args) throws InterruptedException {
        User u = new User(1, "1");
        ReferenceQueue<User> queue = new ReferenceQueue<>();
        PhantomReference<User> phantom = new PhantomReference<>(u, queue);

        u = null;

        System.gc();

        TimeUnit.SECONDS.sleep(3);
        System.out.println("from link " + phantom.get());
        System.out.println("from queue " + queue.poll());
    }

    private static void example0() throws InterruptedException {
        Object object = new Object() {
            @Override
            protected void finalize() throws Throwable {
                System.out.println("Removed");
            }
        };
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        WeakReference<Object> weak = new WeakReference<>(object, queue);
        object = null;

        System.gc();

        TimeUnit.SECONDS.sleep(3);
        System.out.println("from link " + weak.get());
        System.out.println("from queue " + queue.poll());
    }

    private void example1() throws InterruptedException {
        User user1 = new User(1, "User1");
        User user2 = new User(2, "User2");
        WeakHashMap<User, Integer> map = new WeakHashMap<>();
        map.put(user1, 1000);
        map.put(user2, 2000);
        System.out.println(map);
        user1 = null;
        System.gc();
        System.out.println("\n --- After Garbage Collector is called: --- \n");
        Thread.sleep(3000);
        System.out.println(map);
    }

}
