import java.util.ArrayList;

public class ArrayListMultiSet implements MultiSet {
    private ArrayList<Object> list;

    public ArrayListMultiSet() {
        this.list = new ArrayList<Object>();
    }

    @Override
    public boolean add(Object item) {
        this.list.add(item);
        return true;
    }

    @Override
    public void remove(Object item) {
        this.list.remove(item);
    }

    @Override
    public boolean contains(Object item) {
        return this.list.contains(item);
    }

    @Override
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    @Override
    public int count(Object item) {
        int count = 0;
        for (Object el : this.list) {
            if (el.equals(item)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int size() {
        return this.list.size();
    }
}
