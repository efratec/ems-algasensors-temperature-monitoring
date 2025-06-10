package com.algaworks.algasensors.temperature.monitoring.api.controller;

import com.algaworks.algasensors.temperature.monitoring.api.common.SensorAlertMapper;
import com.algaworks.algasensors.temperature.monitoring.api.dto.SensorAlertInputDTO;
import com.algaworks.algasensors.temperature.monitoring.api.dto.SensorAlertOutputDTO;
import com.algaworks.algasensors.temperature.monitoring.domain.model.SensorAlert;
import com.algaworks.algasensors.temperature.monitoring.domain.model.SensorId;
import com.algaworks.algasensors.temperature.monitoring.domain.repository.SensorAlertRepository;
import io.hypersistence.tsid.TSID;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/sensors/{sensorId}/alert")
@RequiredArgsConstructor
public class SensorAlertController {

    private final SensorAlertMapper sensorAlertMapper;
    private final SensorAlertRepository repository;

    @GetMapping
    public ResponseEntity<SensorAlertOutputDTO> getDetails(@PathVariable @Valid TSID sensorId) {
        var sensorAlert = findById(sensorId);
        return ok(sensorAlertMapper.toDTO(sensorAlert));
    }

    @PutMapping
    public ResponseEntity<SensorAlertOutputDTO> createOrUpdate(@PathVariable @Valid TSID sensorId,
                                                               @RequestBody SensorAlertInputDTO input) {
        var sensorAlert = findByIdOrDefault(sensorId);
        sensorAlert.setMinTemperature(input.getMinTemperature());
        sensorAlert.setMaxTemperature(input.getMaxTemperature());
        repository.saveAndFlush(sensorAlert);
        return ok(sensorAlertMapper.toDTO(sensorAlert));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable @Valid TSID sensorId) {
        repository.delete(findById(sensorId));
        return ResponseEntity.noContent().build();
    }

    private SensorAlert findById(TSID sensorId) {
        return repository.findById(SensorId.of(sensorId))
                 .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    private SensorAlert findByIdOrDefault(TSID sensorId) {
        return repository.findById(SensorId.of(sensorId))
                .orElse(SensorAlert.builder()
                        .id(SensorId.of(sensorId))
                        .minTemperature(null)
                        .maxTemperature(null)
                        .build());
    }

}
