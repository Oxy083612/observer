package edu.io.logic;

import edu.io.pubsub.Publisher;
import edu.io.pubsub.Subscriber;

public class PriceFeed {
    public final Publisher<DataPack> publisher = new Publisher();
    private DataSource source;

    public PriceFeed(DataSource dataSource){
        this.source = dataSource;
    }

    public PriceFeed(){}

    public void refresh(){
        DataPack data = source.getData();
        System.out.println("Aktualna cena: " + data.price());
        publisher.publish(data);
    }
}
