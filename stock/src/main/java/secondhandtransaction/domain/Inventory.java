package secondhandtransaction.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import secondhandtransaction.StockApplication;
import secondhandtransaction.domain.StockDecresed;

@Entity
@Table(name = "Inventory_table")
@Data
//<<< DDD / Aggregate Root
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long productid;

    private String productname;

    private Integer qty;

    private String status;

    @PostPersist
    public void onPostPersist() {
        StockDecresed stockDecresed = new StockDecresed(this);
        stockDecresed.publishAfterCommit();
    }

    public static InventoryRepository repository() {
        InventoryRepository inventoryRepository = StockApplication.applicationContext.getBean(
            InventoryRepository.class
        );
        return inventoryRepository;
    }

    //<<< Clean Arch / Port Method
    public static void decreaseStock(TradeCompleted tradeCompleted) {
        repository().findByProductid(tradeCompleted.getProductid()).ifPresent(inventory->{
            try {
                if ("거래 진행중".equals(tradeCompleted.getStatus())) {
                    if (inventory.getQty() - tradeCompleted.getQty() > 0) {
                        repository().save(inventory);
                        inventory.setStatus("거래 진행 중 재고와 실제 거래량 일치");
                        StockDecresed stockDecresed = new StockDecresed(inventory);
                        stockDecresed.publishAfterCommit();
                    } else {
                        repository().save(inventory);
                        inventory.setStatus("거래 진행 중 재고와 실제 거래량 불일치로 거래 불가");
                        StockDecresed stockDecresed = new StockDecresed(inventory);
                        stockDecresed.publishAfterCommit();
                    }
                } else if ("예약취소로 거래취소".equals(tradeCompleted.getStatus())) {
                    inventory.setStatus("거래 취소로 인한 재고 감소 없음");
                    repository().save(inventory);
                    StockDecresed stockDecresed = new StockDecresed(inventory);
                    stockDecresed.publishAfterCommit();
                } else {
                    inventory.setStatus("거래상태 확인 불가");
                }
                
                
            } catch (NullPointerException e) {
                // reserveCompleted.getStatus()가 null이면 NullPointerException이 발생할 수 있음
                inventory.setStatus("거래상태 확인 불가 - 예외 발생: " + e.getMessage());
                
            }           

         });

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
