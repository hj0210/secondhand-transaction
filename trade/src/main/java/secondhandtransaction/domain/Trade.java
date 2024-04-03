package secondhandtransaction.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import secondhandtransaction.TradeApplication;
import secondhandtransaction.domain.TradeCanceled;
import secondhandtransaction.domain.TradeCompleted;

@Entity
@Table(name = "Trade_table")
@Data
//<<< DDD / Aggregate Root
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long productid;

    private String userid;

    private String productname;

    private String qty;

    private String price;

    @PostPersist
    public void onPostPersist() {
        TradeCompleted tradeCompleted = new TradeCompleted(this);
        tradeCompleted.publishAfterCommit();

        TradeCanceled tradeCanceled = new TradeCanceled(this);
        tradeCanceled.publishAfterCommit();
    }

    public static TradeRepository repository() {
        TradeRepository tradeRepository = TradeApplication.applicationContext.getBean(
            TradeRepository.class
        );
        return tradeRepository;
    }

    //<<< Clean Arch / Port Method
    public static void startTrade(ReserveCompleted reserveCompleted) {
        //implement business logic here:

        /** Example 1:  new item 
        Trade trade = new Trade();
        repository().save(trade);

        */

        /** Example 2:  finding and process
        
        repository().findById(reserveCompleted.get???()).ifPresent(trade->{
            
            trade // do something
            repository().save(trade);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
