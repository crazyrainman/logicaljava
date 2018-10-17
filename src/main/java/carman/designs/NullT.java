package carman.designs;
abstract class AbstractOperation {
    abstract void request();
}
class RealOperation extends AbstractOperation {
    @Override
    void request() {
        System.out.println("do something");
    }
}
class NullOperation extends AbstractOperation{
    @Override
    void request() {
        // do nothing
    }
}
public class NullT {
    public static void main(String[] args) {
        AbstractOperation abstractOperation = func(1);
        abstractOperation.request(); 
    }
    public static AbstractOperation func(int para) {
        if (para < 0) {
            return new NullOperation();
        }
        return new RealOperation();
    }
}