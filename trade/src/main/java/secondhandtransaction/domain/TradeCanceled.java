package secondhandtransaction.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import secondhandtransaction.domain.*;
import secondhandtransaction.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class TradeCanceled extends AbstractEvent {

    private Long id;
    private Long productid;
    private String userid;
    private String productname;
    private String qty;
    private String price;
    private String status;

    public TradeCanceled(Trade aggregate) {
        super(aggregate);
    }

    public TradeCanceled() {
        super();
    }
}
//>>> DDD / Domain Event
