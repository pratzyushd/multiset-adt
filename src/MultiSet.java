public interface MultiSet {
   boolean add(Object item);
   void remove(Object item);
   boolean contains(Object item);
   boolean isEmpty();
   int count(Object item);
   int size();
}
