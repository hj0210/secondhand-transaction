package secondhandtransaction.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import secondhandtransaction.domain.*;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "trades", path = "trades")
public interface TradeRepository
    extends PagingAndSortingRepository<Trade, Long> {

        java.util.Optional<Trade> findByProductid(Long id);
        java.util.Optional<Trade> findBystatus(String status);

    }
