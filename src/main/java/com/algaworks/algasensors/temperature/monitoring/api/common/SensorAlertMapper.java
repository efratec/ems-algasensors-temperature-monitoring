package com.algaworks.algasensors.temperature.monitoring.api.common;

import com.algaworks.algasensors.temperature.monitoring.api.dto.SensorAlertOutputDTO;
import com.algaworks.algasensors.temperature.monitoring.domain.model.SensorAlert;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SensorAlertMapper {

    SensorAlertMapper INSTANCE = Mappers.getMapper(SensorAlertMapper.class);

    @Mapping(target = "id", source = "id.value")
    SensorAlertOutputDTO toDTO(SensorAlert sensorAlert);

}
