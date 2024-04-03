package secondhandtransaction.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import secondhandtransaction.domain.*;

@Component
public class NoticeHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Notice>> {

    @Override
    public EntityModel<Notice> process(EntityModel<Notice> model) {
        return model;
    }
}
