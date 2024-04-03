package secondhandtransaction.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import secondhandtransaction.domain.*;
import secondhandtransaction.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class TradeCompleted extends AbstractEvent {

    private Long id;
    private Long productid;
    private String userid;
    private String productname;
    private String qty;
    private String price;

    public TradeCompleted(Trade aggregate) {
        super(aggregate);
    }

    public TradeCompleted() {
        super();
    }
}
//>>> DDD / Domain Event
