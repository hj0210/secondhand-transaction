package secondhandtransaction.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import secondhandtransaction.domain.*;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "reserves", path = "reserves")
public interface ReserveRepository
    extends PagingAndSortingRepository<Reserve, Long> {
        java.util.Optional<Reserve> findBystatus(String status);

    }
