package org.example.entity;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public record Deathday(LocalDate birthDate,  LocalDate date) {
    public Long getSummaryAge(){

        return ChronoUnit.YEARS.between(date,birthDate);
    }
}
