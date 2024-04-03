package secondhandtransaction.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import secondhandtransaction.domain.*;
import secondhandtransaction.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ReserveCanceled extends AbstractEvent {

    private Long id;
    private Long productid;
    private String userid;
    private String productname;
    private Integer qty;
    private Date reserveDt;
    private String address;
    private Integer price;
    private String status;

    public ReserveCanceled(Reserve aggregate) {
        super(aggregate);
    }

    public ReserveCanceled() {
        super();
    }
}
//>>> DDD / Domain Event
