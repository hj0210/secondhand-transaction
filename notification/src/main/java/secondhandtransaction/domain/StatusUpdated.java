package secondhandtransaction.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import secondhandtransaction.domain.*;
import secondhandtransaction.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class StatusUpdated extends AbstractEvent {

    private Long id;
    private Long productid;
    private String userid;
    private String productname;
    private String status;

    public StatusUpdated(Notice aggregate) {
        super(aggregate);
    }

    public StatusUpdated() {
        super();
    }
}
//>>> DDD / Domain Event
