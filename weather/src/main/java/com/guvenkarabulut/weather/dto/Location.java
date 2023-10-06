package com.guvenkarabulut.weather.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Location(
        String name,
        String country,
        String region,
        String lat,
        String lon,
        @JsonProperty("timezone_id")
        String timezoneId,
        String localtime,
        @JsonProperty("localtime_epoch")
        String localtimeEpoch,
        @JsonProperty("utc_offset")
        String utcOffset
) { }