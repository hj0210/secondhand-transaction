package secondhandtransaction.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import secondhandtransaction.domain.*;

@Component
public class TradeHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Trade>> {

    @Override
    public EntityModel<Trade> process(EntityModel<Trade> model) {
        return model;
    }
}
