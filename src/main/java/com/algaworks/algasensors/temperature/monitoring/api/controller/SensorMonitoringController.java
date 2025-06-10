package com.algaworks.algasensors.temperature.monitoring.api.controller;

import com.algaworks.algasensors.temperature.monitoring.api.dto.SensorMonitoringOutputDTO;
import com.algaworks.algasensors.temperature.monitoring.domain.model.SensorId;
import com.algaworks.algasensors.temperature.monitoring.domain.model.SensorMonitoring;
import com.algaworks.algasensors.temperature.monitoring.domain.repository.SensorMonitoringRepository;
import io.hypersistence.tsid.TSID;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/sensors/{sensorId}/monitoring")
@RequiredArgsConstructor
public class SensorMonitoringController {

    private final SensorMonitoringRepository repository;

    @GetMapping
    public ResponseEntity<SensorMonitoringOutputDTO> getDetails(@PathVariable("sensorId") @Valid TSID sensorId) {
        var sensorMonitoring = findByIdOrDefault(sensorId);
        return ok(SensorMonitoringOutputDTO
                .builder()
                .id(sensorMonitoring.getId().getValue())
                .enabled(sensorMonitoring.getEnabled())
                .lastTemperature(sensorMonitoring.getLastTemperature())
                .updatedAt(sensorMonitoring.getUpdatedAt())
                .build());
    }

    @PutMapping("/enable")
    public ResponseEntity<Void> enabled(@PathVariable("sensorId") @Valid TSID sensorId) {
        var sensorMonitoring = findByIdOrDefault(sensorId);
        sensorMonitoring.setEnabled(true);
        repository.saveAndFlush(sensorMonitoring);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/enable")
    public ResponseEntity<Void> disabled(@PathVariable("sensorId") @Valid TSID sensorId) {
        var sensorMonitoring = findByIdOrDefault(sensorId);
        sensorMonitoring.setEnabled(false);
        repository.saveAndFlush(sensorMonitoring);
        return ResponseEntity.noContent().build();
    }

    private SensorMonitoring findByIdOrDefault(TSID sensorId) {
        return repository.findById(SensorId.of(sensorId))
                .orElse(SensorMonitoring.builder()
                        .id(SensorId.of(sensorId))
                        .enabled(false)
                        .lastTemperature(null)
                        .updatedAt(null)
                        .build());
    }

}
