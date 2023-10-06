package com.guvenkarabulut.weather.controller;

import com.guvenkarabulut.weather.dto.WeatherDto;
import com.guvenkarabulut.weather.service.WeatherService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/weather")
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

     @GetMapping("/{cityName}")
    public ResponseEntity<WeatherDto> getWeather(@PathVariable(name = "cityName") @NotBlank String cityName){
        return ResponseEntity.ok(weatherService.getWeatherByCityName(cityName));
     }
}
