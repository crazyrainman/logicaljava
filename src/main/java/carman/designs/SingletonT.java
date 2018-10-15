package carman.designs;

class Singleton {
    private Singleton() {
    }
    public String toString() {
        return "Hi";
    }
    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }
    public static Singleton getUniqueInstance() {
        return SingletonHolder.INSTANCE;
    }
}
class Singleton2 {

    private volatile static Singleton2 uniqueInstance;

    private Singleton2() {
    }

    public String toString() {
        return "Hi";
    }

    public static Singleton2 getUniqueInstance() {
        if (uniqueInstance == null) {
            synchronized (Singleton.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Singleton2();
                }
            }
        }
        return uniqueInstance;
    }
}
public class SingletonT {
    public static void main(String[] args) {
        System.out.println(Singleton.getUniqueInstance().toString());
    }
}