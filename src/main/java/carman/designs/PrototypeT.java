package carman.designs;

abstract class Prototype {
    abstract Prototype myClone();
}

class ConcretePrototype extends Prototype {
    private String filed;
    public ConcretePrototype(String filed) {
        this.filed = filed;
    }
    @Override
    Prototype myClone() {
        return new ConcretePrototype(filed);
    }
    @Override
    public String toString() {
        return filed;
    }
}
public class PrototypeT {
    public static void main(String[] args) {
        Prototype pt = new ConcretePrototype("abc");
        Prototype clone = pt.myClone();
        System.out.println(clone.toString());
    }
}
