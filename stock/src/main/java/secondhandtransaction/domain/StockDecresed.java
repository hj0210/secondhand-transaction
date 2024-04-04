package secondhandtransaction.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import secondhandtransaction.domain.*;
import secondhandtransaction.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class StockDecresed extends AbstractEvent {

    private Long id;
    private Long productid;
    private String productname;
    private Integer qty;
    private String status;

    public StockDecresed(Inventory aggregate) {
        super(aggregate);
    }

    public StockDecresed() {
        super();
    }
}
//>>> DDD / Domain Event
