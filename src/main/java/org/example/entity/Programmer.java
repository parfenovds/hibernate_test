package org.example.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue(value = "programmer")
public class Programmer extends User{

    @Enumerated(value = EnumType.STRING)
    private Language language;

    @Builder
    public Programmer(Long id, Long credentials, Language language) {
        super(id, credentials);
        this.language = language;
    }
}
