package secondhandtransaction.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import secondhandtransaction.ReservationApplication;
import secondhandtransaction.domain.ReserveCompleted;

@Entity
@Table(name = "Reserve_table")
@Data
//<<< DDD / Aggregate Root
public class Reserve {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long productid;

    private String userid;

    private String productname;

    private Integer qty;

    private Date reserveDt;

    private String address;

    private Integer price;

    private String status;

    @PostPersist
    public void onPostPersist() {
        ReserveCompleted reserveCompleted = new ReserveCompleted(this);
        reserveCompleted.publishAfterCommit();
    }

    public static ReserveRepository repository() {
        ReserveRepository reserveRepository = ReservationApplication.applicationContext.getBean(
            ReserveRepository.class
        );
        return reserveRepository;
    }

    //<<< Clean Arch / Port Method
    public void reserveCancel() {
        //implement business logic here:

        ReserveCanceled reserveCanceled = new ReserveCanceled(this);
        reserveCanceled.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public static void updateStatus(StatusUpdated statusUpdated) {
        repository().findBystatus(statusUpdated.getStatus()).ifPresent(Reserve->{
            Reserve.setStatus(statusUpdated.getStatus());
         });
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
