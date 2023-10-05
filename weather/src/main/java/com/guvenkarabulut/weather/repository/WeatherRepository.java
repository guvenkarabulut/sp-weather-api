package com.guvenkarabulut.weather.repository;

import com.guvenkarabulut.weather.model.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<WeatherEntity, String> {
}
