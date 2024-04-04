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
    private Integer qty;
    private String price;
    private String status;
}
