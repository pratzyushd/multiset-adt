public class Node<T> {
    private T item;
    private Node<T> next;

    public Node(T item) {
        this.item = item;
        this.next = null;
    }
}