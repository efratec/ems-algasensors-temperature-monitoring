package com.algaworks.algasensors.temperature.monitoring.api.common;

import com.algaworks.algasensors.temperature.monitoring.api.dto.TemperatureLogOutPutDTO;
import com.algaworks.algasensors.temperature.monitoring.domain.model.TemperatureLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface TemperatureLogMapper {

    TemperatureLogMapper INSTANCE = Mappers.getMapper(TemperatureLogMapper.class);

    @Mapping(target = "id", source = "id.value")
    TemperatureLogOutPutDTO toModel(TemperatureLog temperatureLog);

    default Page<TemperatureLogOutPutDTO> toModel(Page<TemperatureLog> temperatureLogs) {
        return temperatureLogs.map(this::toModel);
    }

}
