package secondhandtransaction.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import secondhandtransaction.config.kafka.KafkaProcessor;
import secondhandtransaction.domain.*;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    ReserveRepository reserveRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='StatusUpdated'"
    )
    public void wheneverStatusUpdated_UpdateStatus(
        @Payload StatusUpdated statusUpdated
    ) {
        StatusUpdated event = statusUpdated;
        System.out.println(
            "\n\n##### listener UpdateStatus : " + statusUpdated + "\n\n"
        );

        // Sample Logic //
        Reserve.updateStatus(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
