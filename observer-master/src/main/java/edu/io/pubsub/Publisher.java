package edu.io.pubsub;

import java.util.ArrayList;
import java.util.List;

public class Publisher<T> {

    List<Subscriber<T>> subs = new ArrayList<>();

    public void subscribe(Subscriber<T> subscriber){
        subs.add(subscriber);
        System.out.println(subscriber);
    }

    public void unsubscribe(Subscriber<T> subscriber){
        subs.remove(subscriber);
    }

    public void publish(T data){
        for (Subscriber sub : subs){
            sub.update(data);
        }
    }
}
