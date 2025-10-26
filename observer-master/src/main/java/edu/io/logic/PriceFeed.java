package edu.io.logic;

import edu.io.pubsub.Publisher;
import edu.io.pubsub.Subscriber;

public class PriceFeed {
    public final Publisher<DataPack> publisher = new Publisher();
    private DataSource source;

    public PriceFeed(DataSource dataSource){
        this.source = dataSource;
    }

    public PriceFeed(){

    }

    public void refresh(){

    }

    public DataPack getData(){
        return new DataPack(3, 4);
    }
}
