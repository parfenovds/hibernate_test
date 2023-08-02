package org.example.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue(value = "manager")
public class Manager extends User{

    private String projectName;

    @Builder
    public Manager(Long id, Long credentials, String projectName) {
        super(id, credentials);
        this.projectName = projectName;
    }
}
