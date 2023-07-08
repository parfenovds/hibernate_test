package org.example.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Builder
public class PersonInfo {

    private String userName;
    private String firstName;
    private String lastName;
    private Birthday birthDay;
    private Deathday deathDay;
}
