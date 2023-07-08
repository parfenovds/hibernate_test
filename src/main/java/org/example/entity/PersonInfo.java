package org.example.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Builder
public class PersonInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 4750459007976027502L;

    private String userName;
    private String firstName;
    private String lastName;
    private Birthday birthDay;
    private Deathday deathDay;
}
