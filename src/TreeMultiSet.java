public class TreeMultiSet implements MultiSet {
    private final Tree tree;

    public TreeMultiSet() {
        this.tree = new Tree();
    }

    @Override
    public boolean add(Object item) {
        if (!(item instanceof Integer)) {
            throw new IllegalArgumentException("TreeMultiSet only accepts Integers.");
        }
        this.tree.insert((Integer) item);
        return true;
    }

    @Override
    public void remove(Object item) {
        if (!(item instanceof Integer)) {
            throw new IllegalArgumentException("TreeMultiSet only accepts Integers.");
        }
        this.tree.deleteItem((Integer) item);
    }

    @Override
    public boolean contains(Object item) {
        if (!(item instanceof Integer)) {
            throw new IllegalArgumentException("TreeMultiSet only accepts Integers.");
        }
        return this.tree.contains((Integer) item);
    }

    @Override
    public boolean isEmpty() {
        return this.tree.isEmpty();
    }

    @Override
    public int count(Object item) {
        if (!(item instanceof Integer)) {
            throw new IllegalArgumentException("TreeMultiSet only accepts Integers.");
        }
        returh this.tree.count((Integer) item);
    }

    @Override
    public int size() {
        return this.tree.size();
    }
}
