package com.algaworks.algasensors.temperature.monitoring.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SensorAlertInputDTO {

    private Double maxTemperature;
    private Double minTemperature;

}
