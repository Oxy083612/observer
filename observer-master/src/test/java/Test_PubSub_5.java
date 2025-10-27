import edu.io.logic.*;
/*
import edu.io.logic.DataSource;
import edu.io.logic.HttpDataSource;
*/
import edu.io.pubsub.Publisher;
import edu.io.pubsub.Subscriber;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.List;

class Test_PubSub_5 {

    @Test
    void can_process_only_data_packs(){
        var ps = new PriceStats<String>();

        ps.update("DataPack");

        Assertions.assertEquals(0, ps.getCounter());
        Assertions.assertEquals(0, ps.getAvg());
        Assertions.assertEquals(0, ps.getStandardDeviation());
    }

    @Test
    void updates_stats_incrementally(){
        var ps = new PriceStats<DataPack>();

        List<DataPack> prices = List.of(
                new DataPack(1000, 0),
                new DataPack(999, 1),
                new DataPack(1001, 2));

        for (DataPack x : prices){
            ps.update(x);
        }

        Assertions.assertEquals(999, ps.getMin());
        Assertions.assertEquals(1001, ps.getMax());
        Assertions.assertEquals(1000, ps.getAvg());
        Assertions.assertEquals((new BigDecimal(0.81649658092773).setScale(7, RoundingMode.HALF_UP)), (new BigDecimal(ps.getStandardDeviation()).setScale(7, RoundingMode.HALF_UP)));
    }

}