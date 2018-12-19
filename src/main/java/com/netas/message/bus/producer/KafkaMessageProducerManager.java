package com.netas.message.bus.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.netas.message.bus.config.SingletonTopic;

@Service
public class KafkaMessageProducerManager implements MessageProducerService {

    private static final Logger           LOG = LoggerFactory.getLogger(KafkaMessageProducerManager.class);

    @Autowired
    private SingletonTopic                singletonTopic;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Async
    @Override
    public void sendMessage(final String message) {
        LOG.debug("[sendCreateDeviceEvent] DeviceCredentials object is sending.. -> {}", message);

        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(singletonTopic.getTopic(), message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                LOG.debug("[sendCreateDeviceEvent] sent message='{}' with offset={}", message, result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                LOG.error("[sendCreateDeviceEvent] unable to send message='{}'", message, ex);
            }

        });
    }

}
