package com.netas.message.bus.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageConsumerManager implements MessageConsumerService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    // @Autowired
    // DeviceOperationsService deviceOperationsService;
    //
    // @Autowired
    // RuleEventHandlerService ruleEventHandlerService;

    // @Autowired
    // SmartAppCoreProperties smartAppCoreProperties;

//    @KafkaListener(topics = "#{T(com.netas.message.bus.config.SingletonTopic).getTopic()}")
//    // @KafkaListener(topics = "#{T(com.netas.message.bus.config.SingletonTopic).getTopic(smartAppCoreProperties.getAppId())}")
//    @Override
//    public void updateOperationalStatusEventHandler(String operationalStatus) {
//        LOGGER.debug("[updateOperationalStatusEventHandler] Message Received. operationalStatus: {}",
//                operationalStatus);
//        // deviceOperationsService.updateOperationalStatus(operationalStatus);
//    }

    @KafkaListener(topics = "${kafka.topic.ruleEventTopic}")
    public void handleRuleEvent(String rule) {
        LOGGER.debug("Rule Event Message Received -> {}", rule.toString());
        // ruleEventHandlerService.processRuleEvent(rule);
    }

    @Override
    public void updateOperationalStatusEventHandler(String operationalStatus) {
        // TODO Auto-generated method stub
        
    }

}
