package carman.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyRunable implements Runnable {
    public void run() {
        System.out.println("Hi!");
    }
}

class MyCallable implements Callable<Integer> {
    public Integer call() {
        return 123;
    }
}

class Singleton {
    private volatile static Singleton uniqueInstance;
    private Singleton() {

    }
    public static Singleton getUniqueInstance() {
        if (uniqueInstance == null) {
            synchronized(Singleton.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Singleton();
                }
            }
        }
        return uniqueInstance;
    }
    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getUniqueInstance2() {
        return SingletonHolder.INSTANCE;
    }
}

public class Run {
    public static void main(String[] args) throws ExecutionException, InterruptedException{
        MyRunable instance = new MyRunable();
        Thread thread = new Thread(instance);
        thread.start();

        MyCallable mc = new MyCallable();
        FutureTask<Integer> ft = new FutureTask<>(mc);
        Thread thread2 = new Thread(ft);
        thread2.start();
        System.out.println(ft.get());
    }
}