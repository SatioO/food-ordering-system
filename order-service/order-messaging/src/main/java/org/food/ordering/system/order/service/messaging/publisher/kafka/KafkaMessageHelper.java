package org.food.ordering.system.order.service.messaging.publisher.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class KafkaMessageHelper {

    public <T> CompletableFuture<SendResult<String, T>>
    getKafkaCallback(String responseTopicName, T avroModel, String orderId, String avroModelName) {
        return new CompletableFuture<SendResult<String, T>>() {
//            @Override
//            public void onFailure(Throwable ex) {
//                log.error("Error while sending " + avroModelName +
//                        " message {} to topic {}", avroModel.toString(), responseTopicName, ex);
//            }
//
//            @Override
//            public void onSuccess(SendResult<String, T> result) {
//                RecordMetadata metadata = result.getRecordMetadata();
//                log.info("Received successful response from Kafka for order id: {}" +
//                                " Topic: {} Partition: {} Offset: {} Timestamp: {}",
//                        orderId,
//                        metadata.topic(),
//                        metadata.partition(),
//                        metadata.offset(),
//                        metadata.timestamp());
//            }
        };
    }
}
