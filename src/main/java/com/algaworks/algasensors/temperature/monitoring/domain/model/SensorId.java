package com.algaworks.algasensors.temperature.monitoring.domain.model;

import io.hypersistence.tsid.TSID;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@ToString
@EqualsAndHashCode
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SensorId implements Serializable {

    private TSID value;

    private SensorId(TSID value) {
        this.value = Objects.requireNonNull(value);
    }

    public static SensorId of(TSID value) {
        return new SensorId(value);
    }

    public static SensorId of(Long value) {
        return new SensorId(TSID.from(Objects.requireNonNull(value)));
    }

    public static SensorId of(String value) {
        return new SensorId(TSID.from(Objects.requireNonNull(value)));
    }

}
