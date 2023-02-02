public interface QueueOfStrings {
    void enqueue(String item);

    String dequeue();

    boolean isEmpty();

    int size();

}
