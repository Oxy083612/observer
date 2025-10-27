package edu.io;

import edu.io.logic.*;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        PriceFeed feed = new PriceFeed(new HttpDataSource());

        PriceLog<DataPack> log = new PriceLog<>();
        PriceStats<DataPack> stats = new PriceStats<>();

        feed.publisher.subscribe(log);
        feed.publisher.subscribe(stats);

        Scanner scanner = new Scanner(System.in);

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        scheduler.scheduleAtFixedRate(() -> {
            try {
                feed.refresh();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, 0, 10, TimeUnit.SECONDS);

        while(!scanner.nextLine().equals('q')){
            stats.printStats();
        }
    }
}