package com.guvenkarabulut.weather.repository;

import com.guvenkarabulut.weather.model.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WeatherRepository extends JpaRepository<WeatherEntity, String> {
    Optional<WeatherEntity> findFirstByRequestCityNameOrderByUpdateTimeDesc(String cityName);
    // List<WeatherEntity> findAllByRequestCityNameOrderByUpdateTimeDesc(String city);
}
