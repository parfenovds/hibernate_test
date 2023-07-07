package org.example.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.example.entity.Deathday;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

@Converter
public class DeathdayConverter implements AttributeConverter<Deathday, Date> {
    @Override
    public Date convertToDatabaseColumn(Deathday deathday) {

        return Optional.ofNullable(deathday)
                .map(Deathday::date)
                .map(Date::valueOf)
                .orElse(null);
    }

    @Override
    public Deathday convertToEntityAttribute(Date date) {
        return Optional.ofNullable(date)
                .map(Date::toLocalDate)
                .map(date1 -> new Deathday(LocalDate.now(),date1))
                .orElse(null);
    }
}
