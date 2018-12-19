package com.netas.message.bus.config;


import org.springframework.stereotype.Component;

@Component
public class SingletonTopic {

    private static String value= "bilal";
    private static String topic;
    public static String getValue() {
        return value;
    }
    public static void setValue(String value) {
        SingletonTopic.value = value;
    }
    public static String getTopic() {
        if (topic==null) topic = value;
        return topic;
    }
    public static void setTopic(String topic) {
        SingletonTopic.topic = topic;
    }
    @Override
    public String toString() {
        return "SingletonTopic []";
    }

}
