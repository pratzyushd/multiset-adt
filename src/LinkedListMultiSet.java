public class LinkedListMultiSet implements MultiSet {
    private Node<Object> front;
    private int size;

    public LinkedListMultiSet() {
        this.front = null;
        this.size = 0;
    }

    @Override
    public boolean add(Object item) {
        Node<Object> newNode = new Node<>(item);
        newNode.setNext(this.front);
        this.front = newNode;
        this.size++;
        return true;
    }

    @Override
    public void remove(Object item) {
        Node<Object> cur = this.front;
        Node<Object> prev = null;
        while (cur != null) {
            if (cur.getItem() == item) {
                this.size--;
                if (prev != null) {
                    prev.setNext(cur.getNext());
                } else { /* first item */
                    this.front = cur.getNext();
                }
                return;
            }
            prev = cur;
            cur = cur.getNext();

        }
        /* item not found */
    }

    @Override
    public boolean contains(Object item) {
        Node<Object> cur = this.front;
        while (cur != null) {
            if (cur.getItem() == item) {
                return true;
            }
            cur = cur.getNext();
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return this.front == null;
    }

    @Override
    public int count(Object item) {
        int numSeen = 0;
        Node<Object> cur = this.front;
        while (cur != null) {
            if (cur.getItem() == item) {
                numSeen++;
            }
            cur = cur.getNext();
        }
        return numSeen;
    }

    @Override
    public int size() {
        return this.size;
    }
}
