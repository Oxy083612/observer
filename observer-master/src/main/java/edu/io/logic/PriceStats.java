package edu.io.logic;

import edu.io.pubsub.Subscriber;

public class PriceStats<T> implements Subscriber<T> {

    private double min = Double.MAX_VALUE;
    private double max = 0;
    private double avg = 0;
    private double standard_deviation = 0;
    private double lastM2 = 0;
    private int counter = 0;

    public double getMin(){
        return min;
    }

    public double getMax(){
        return max;
    }

    public double getAvg(){
        return avg;
    }

    public double getCounter(){
        return counter;
    }

    public double getStandardDeviation(){
        return standard_deviation;
    }

    private void updateMin(double price){
        if(min > price){
            min = price;
        }
    }

    private void updateMax(double price){
        if(max < price){
            max = price;
        }
    }

    private void updateAll(double price){
        double delta = price - avg;
        avg += delta / counter;
        double delta2 = price - avg;

        lastM2 += delta * delta2;

        if (counter >= 2){
            standard_deviation = Math.sqrt(lastM2 / counter);
        } else {
            standard_deviation = 0;
        }

        updateMin(price);
        updateMax(price);

    }

    public void printStats(){
        System.out.println("Minimum: " + String.format("%.2f", min));
        System.out.println("Maximum: " + String.format("%.2f", max));
        System.out.println("Average: " + String.format("%.2f", avg));
        System.out.println("Standard deviation: " + String.format("%.2f", standard_deviation));
    }

    @Override
    public void update(T data) {
        if(data instanceof DataPack){
            counter++;
            double price =  ((DataPack) data).price();
            updateAll(price);
        }
    }


}
