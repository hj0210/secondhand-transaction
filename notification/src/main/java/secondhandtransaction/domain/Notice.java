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
        //implement business logic here:

        /** Example 1:  new item 
        Notice notice = new Notice();
        repository().save(notice);

        */

        /** Example 2:  finding and process
        
        repository().findById(reserveCanceled.get???()).ifPresent(notice->{
            
            notice // do something
            repository().save(notice);


         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void alertToUser(TradeCanceled tradeCanceled) {
        //implement business logic here:

        /** Example 1:  new item 
        Notice notice = new Notice();
        repository().save(notice);

        */

        /** Example 2:  finding and process
        
        repository().findById(tradeCanceled.get???()).ifPresent(notice->{
            
            notice // do something
            repository().save(notice);


         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void alertToUser(StockDecresed stockDecresed) {
        //implement business logic here:

        /** Example 1:  new item 
        Notice notice = new Notice();
        repository().save(notice);

        */

        /** Example 2:  finding and process
        
        repository().findById(stockDecresed.get???()).ifPresent(notice->{
            
            notice // do something
            repository().save(notice);


         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void alertToUser(ReserveCompleted reserveCompleted) {
        //implement business logic here:

        /** Example 1:  new item 
        Notice notice = new Notice();
        repository().save(notice);

        */

        /** Example 2:  finding and process
        
        repository().findById(reserveCompleted.get???()).ifPresent(notice->{
            
            notice // do something
            repository().save(notice);


         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void alertToUser(TradeCompleted tradeCompleted) {
        //implement business logic here:

        /** Example 1:  new item 
        Notice notice = new Notice();
        repository().save(notice);

        */

        /** Example 2:  finding and process
        
        repository().findById(tradeCompleted.get???()).ifPresent(notice->{
            
            notice // do something
            repository().save(notice);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
