package edu.io.pubsub;

import java.util.ArrayList;
import java.util.List;

public class Publisher {

    List<Subscriber> subs = new ArrayList<>();

    public void subscribe(Subscriber subscriber){
        subs.add(subscriber);
    }

    public void unsubscribe(Subscriber subscriber){
        subs.remove(subscriber);
    }

    public void publish(Object data){
        for (Subscriber sub : subs){
            sub.update(data);
        }
    }
}
