package edu.io.logic;

import edu.io.pubsub.Subscriber;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PriceLog implements Subscriber {    // pliki zapisuje coś ten
    private final Logger logger = Logger.getLogger(PriceLog.class.getName());

    public PriceLog(){
        // FileHandler
    }

    @Override
    public void update(Object data){
        //logger.log(Level.INFO, "PriceLog: " + data);
    }


}
