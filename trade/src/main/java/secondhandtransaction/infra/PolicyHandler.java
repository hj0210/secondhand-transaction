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
    TradeRepository tradeRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ReserveCompleted'"
    )
    public void wheneverReserveCompleted_StartTrade(
        @Payload ReserveCompleted reserveCompleted
    ) {
        ReserveCompleted event = reserveCompleted;
        System.out.println(
            "\n\n##### listener StartTrade : " + reserveCompleted + "\n\n"
        );

        // Sample Logic //
        Trade.startTrade(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
