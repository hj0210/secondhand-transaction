package secondhandtransaction.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import secondhandtransaction.domain.*;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "inventories",
    path = "inventories"
)
public interface InventoryRepository
    extends PagingAndSortingRepository<Inventory, Long> {
        
        java.util.Optional<Inventory> findByProductid(Long id);
        java.util.Optional<Inventory> findBystatus(String status);
        java.util.Optional<Inventory> findByqty(Integer qty);

    }
