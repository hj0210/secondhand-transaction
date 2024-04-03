package secondhandtransaction.domain;

import java.util.*;
import lombok.*;
import secondhandtransaction.domain.*;
import secondhandtransaction.infra.AbstractEvent;

@Data
@ToString
public class TradeCompleted extends AbstractEvent {

    private Long id;
    private Long productid;
    private String userid;
    private String productname;
    private String qty;
    private String price;
}
