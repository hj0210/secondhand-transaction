package secondhandtransaction.domain;

import java.util.*;
import lombok.*;
import secondhandtransaction.domain.*;
import secondhandtransaction.infra.AbstractEvent;

@Data
@ToString
public class StockDecresed extends AbstractEvent {

    private Long id;
    private Long productid;
    private String productname;
    private Integer qty;
    private String status;
}
