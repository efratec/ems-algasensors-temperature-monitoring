package com.algaworks.algasensors.temperature.monitoring.api.controller;

import com.algaworks.algasensors.temperature.monitoring.api.common.TemperatureLogMapper;
import com.algaworks.algasensors.temperature.monitoring.api.dto.TemperatureLogOutPutDTO;
import com.algaworks.algasensors.temperature.monitoring.domain.model.SensorId;
import com.algaworks.algasensors.temperature.monitoring.domain.repository.TemperatureLogRepository;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sensors/{sensorId}/temperatures")
@RequiredArgsConstructor
public class TemperatureLogController {

    private final TemperatureLogMapper temperatureLogMapper;
    private final TemperatureLogRepository repository;

    @GetMapping
    public Page<TemperatureLogOutPutDTO> search(@PathVariable TSID sensorId, @PageableDefault Pageable pageable) {
        var temperatureLogs = repository.findAllBySensorId(SensorId.of(sensorId), pageable);
        return temperatureLogMapper.toModel(temperatureLogs);
    }

}
