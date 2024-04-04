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
    NoticeRepository noticeRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ReserveCanceled'"
    )
    public void wheneverReserveCanceled_AlertToUser(
        @Payload ReserveCanceled reserveCanceled
    ) {
        ReserveCanceled event = reserveCanceled;
        System.out.println(
            "\n\n##### listener AlertToUser : " + reserveCanceled + "\n\n"
        );

        // Sample Logic //
        Notice.alertToUser(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='TradeCanceled'"
    )
    public void wheneverTradeCanceled_AlertToUser(
        @Payload TradeCanceled tradeCanceled
    ) {
        TradeCanceled event = tradeCanceled;
        System.out.println(
            "\n\n##### listener AlertToUser : " + tradeCanceled + "\n\n"
        );

        // Sample Logic //
        Notice.alertToUser(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='StockDecresed'"
    )
    public void wheneverStockDecresed_AlertToUser(
        @Payload StockDecresed stockDecresed
    ) {
        StockDecresed event = stockDecresed;
        System.out.println(
            "\n\n##### listener AlertToUser : " + stockDecresed + "\n\n"
        );

        // Sample Logic //
        Notice.alertToUser(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ReserveCompleted'"
    )
    public void wheneverReserveCompleted_AlertToUser(
        @Payload ReserveCompleted reserveCompleted
    ) {
        ReserveCompleted event = reserveCompleted;
        System.out.println(
            "\n\n##### listener AlertToUser : " + reserveCompleted + "\n\n"
        );

        // Sample Logic //
        Notice.alertToUser(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='TradeCompleted'"
    )
    public void wheneverTradeCompleted_AlertToUser(
        @Payload TradeCompleted tradeCompleted
    ) {
        TradeCompleted event = tradeCompleted;
        System.out.println(
            "\n\n##### listener AlertToUser : " + tradeCompleted + "\n\n"
        );

        // Sample Logic //
        Notice.alertToUser(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
