package com.algaworks.algasensors.temperature.monitoring.domain.model;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Embeddable
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class TemperatureLogId implements Serializable {

    private UUID value;

    private TemperatureLogId(UUID value) {
        this.value = Objects.requireNonNull(value);
    }

    public static TemperatureLogId of(UUID value) {
        return new TemperatureLogId(value);
    }

    public static TemperatureLogId of(String value) {
        return new TemperatureLogId(UUID.fromString(value));
    }

}
