package com.guvenkarabulut.weather.dto;

public record WeatherResponse (
        Request request,
        Location location,
        Current current
){

}
