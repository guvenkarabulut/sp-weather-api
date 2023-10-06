package com.guvenkarabulut.weather.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.guvenkarabulut.weather.constants.Constants;
import com.guvenkarabulut.weather.dto.WeatherDto;
import com.guvenkarabulut.weather.dto.WeatherResponse;
import com.guvenkarabulut.weather.model.WeatherEntity;
import com.guvenkarabulut.weather.repository.WeatherRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class WeatherService {
    private final WeatherRepository weatherRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public WeatherService(WeatherRepository weatherRepository,
                          RestTemplate restTemplate) {
        this.weatherRepository = weatherRepository;
        this.restTemplate = restTemplate;
    }

    public WeatherDto getWeatherByCityName(String cityName){
        Optional<WeatherEntity> weatherEntityOptional = weatherRepository
                .findFirstByRequestCityNameOrderByUpdateTimeDesc(cityName);
        return weatherEntityOptional.map(
                weatherEntity -> {
                    if (weatherEntity.getUpdateTime().isBefore(LocalDateTime.now().minusMinutes(30))){
                        return WeatherDto.convert(getWeatherFromWeatherStack(cityName));
                    }
                    return WeatherDto.convert(weatherEntity);
                })
                .orElseGet(() -> WeatherDto.convert(getWeatherFromWeatherStack(cityName)));
    }

    private WeatherEntity getWeatherFromWeatherStack(String cityName){
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(getWeatherStackUrl(cityName), String.class);
        try {
            WeatherResponse weatherResponse = objectMapper
                    .readValue(responseEntity.getBody(), WeatherResponse.class);
            return saveWeatherEntity(cityName, weatherResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    private String getWeatherStackUrl(String cityName){
        return Constants.API_URL + Constants.ACCES_KEY_PARAM + Constants.ACCES_KEY + Constants.QUERY_KEY_PARAM + cityName;
    }
    private WeatherEntity saveWeatherEntity(String cityName, WeatherResponse weatherResponse){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        WeatherEntity weatherEntity = new WeatherEntity(
                cityName,
                weatherResponse.location().name(),
                weatherResponse.location().country(),
                weatherResponse.current().temperature(),
                LocalDateTime.now(),
                LocalDateTime.parse(weatherResponse.location().localtime(), dateTimeFormatter)
        );
        return weatherRepository.save(weatherEntity);
    }
}
