package secondhandtransaction.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import secondhandtransaction.domain.*;
import secondhandtransaction.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ReserveCompleted extends AbstractEvent {

    private Long id;
    private Long productid;
    private String userid;
    private String productname;
    private Integer qty;
    private Date reserveDt;
    private String address;
    private Integer price;
    private String status;

    public ReserveCompleted(Reserve aggregate) {
        super(aggregate);
    }

    public ReserveCompleted() {
        super();
    }
}
//>>> DDD / Domain Event
