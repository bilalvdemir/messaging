package com.netas.message.bus.consumer;

public interface MessageConsumerService {

    void updateOperationalStatusEventHandler(String operationalStatus);

    void handleRuleEvent(String rule);
}
