package secondhandtransaction.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import secondhandtransaction.NotificationApplication;
import secondhandtransaction.domain.StatusUpdated;

@Entity
@Table(name = "Notice_table")
@Data
//<<< DDD / Aggregate Root
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long productid;

    private String userid;

    private String productname;

    private String status;

    @PostPersist
    public void onPostPersist() {
        StatusUpdated statusUpdated = new StatusUpdated(this);
        statusUpdated.publishAfterCommit();
    }

    public static NoticeRepository repository() {
        NoticeRepository noticeRepository = NotificationApplication.applicationContext.getBean(
            NoticeRepository.class
        );
        return noticeRepository;
    }

    //<<< Clean Arch / Port Method
    public static void alertToUser(ReserveCanceled reserveCanceled) {
        repository().findBystatus(reserveCanceled.getStatus()).ifPresent(Notice->{
            Notice.setStatus(reserveCanceled.getStatus());
         });

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void alertToUser(TradeCanceled tradeCanceled) {
        repository().findBystatus(tradeCanceled.getStatus()).ifPresent(Notice->{
            Notice.setStatus(tradeCanceled.getStatus());
         });

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void alertToUser(StockDecresed stockDecresed) {
        repository().findBystatus(stockDecresed.getStatus()).ifPresent(Notice->{
            Notice.setStatus(stockDecresed.getStatus());
         });
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void alertToUser(ReserveCompleted reserveCompleted) {
        repository().findBystatus(reserveCompleted.getStatus()).ifPresent(Notice->{
            Notice.setStatus(reserveCompleted.getStatus());
         });

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void alertToUser(TradeCompleted tradeCompleted) {
        repository().findBystatus(tradeCompleted.getStatus()).ifPresent(Notice->{
            Notice.setStatus(tradeCompleted.getStatus());
         });


    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
