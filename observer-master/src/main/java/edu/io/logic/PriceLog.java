package edu.io.logic;

import edu.io.pubsub.Subscriber;

import java.io.FileWriter;
import java.io.IOException;

public class PriceLog<T> implements Subscriber<T> {
    private FileWriter writer;

    public PriceLog(){
        try{
            writer = new FileWriter("price.log", true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(T data){
        try{
            writer.write(data.toString() + "\n");
            writer.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
