package edu.io.pubsub;

public interface Subscriber<T> {
    void update(T data);
}
