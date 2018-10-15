package carman.designs;
interface Aggregate {
    Iterator createIterator();
}
class ConcreateAggregate implements Aggregate {
    private Integer[] items;
    public ConcreateAggregate() {
        items = new Integer[10];
        for(int i = 0; i < items.length; i++) {
            items[i] = i;
        }
    }
    @Override
    public Iterator createIterator() {
        return new ConcreteIterator<Integer>(items);
    }
}
interface Iterator<Item> {
    Item next();
    boolean hasNext();
}
class ConcreteIterator<Item> implements Iterator {
    private Item[] items;
    private int position = 0;
    public ConcreteIterator(Item[] items) {
        this.items = items;
    }
    @Override
    public Item next() {
        return items[position++];
    }
    @Override
    public boolean hasNext() {
        return position < items.length;
    }
}
public class IteratorT {
    public static void main(String[] args) {
        Aggregate aggregate = new ConcreateAggregate();
        Iterator<Integer> iterator = aggregate.createIterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}