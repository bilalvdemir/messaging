package com.netas.message.util;

import java.text.MessageFormat;

public class Utils {

    private static final String TIMESERIES_TOPIC = "timeseries_{0}";

    public static final String getTimeseriesTopic(String appId) {
        return MessageFormat.format(TIMESERIES_TOPIC, appId);
    }
}
