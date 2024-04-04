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

    private String status;

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
    // public static void startTrade(ReserveCompleted reserveCompleted) {
    //     repository().findByProductid(reserveCompleted.getProductid()).ifPresent(trade->{
    //         trade.setStatus("거래 진행중");

    //      });

    // }
    public static void startTrade(ReserveCompleted reserveCompleted) {
        repository().findByProductid(reserveCompleted.getProductid()).ifPresent(trade->{
            try {
                if ("예약완료".equals(reserveCompleted.getStatus())) {
                    repository().save(trade);
                    trade.setStatus("거래 진행중");
                    TradeCompleted tradeCompleted = new TradeCompleted(trade);
                    tradeCompleted.publishAfterCommit();
                } else if ("예약취소".equals(reserveCompleted.getStatus())) {
                    trade.setStatus("예약취소로 거래취소");
                    repository().save(trade);
                    TradeCanceled tradeCanceled = new TradeCanceled(trade);
                    tradeCanceled.publishAfterCommit();
                } else {
                    trade.setStatus("예약상태 확인 불가");
                }
                
            } catch (NullPointerException e) {
                // reserveCompleted.getStatus()가 null이면 NullPointerException이 발생할 수 있음
                trade.setStatus("예약상태 확인 불가 - 예외 발생: " + e.getMessage());
                
            }
         });

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
