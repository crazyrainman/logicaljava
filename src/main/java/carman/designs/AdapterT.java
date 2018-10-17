package carman.designs;
interface Duck2 {
    void quack();
}
interface Turkey {
    void gobble();
}
class WildTurkey implements Turkey {
    @Override
    public void gobble() {
        System.out.println("gobble!");
    }
}
class TurkeyAdapter implements Duck2 {
    Turkey turkey;
    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }
    @Override
    public void quack() {
        turkey.gobble();
    }
}
public class AdapterT {
    public static void main(String[] args) {
        Turkey turkey = new WildTurkey();
        Duck2 duck = new TurkeyAdapter(turkey);
        duck.quack();
    }
}