package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users", schema = "public")
public class User {

    @Id
    @GeneratedValue(generator = "sequenceGenerator3000TurboPro"
            ,strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name ="sequenceGenerator3000TurboPro",
            sequenceName = "users_custom_id_seq", allocationSize = 1)
    private Long id;

    @Embedded
    @AttributeOverride(name = "birthDay", column = @Column(name = "birth_date"))
    @AttributeOverride(name = "deathDay", column = @Column(name = "death_date"))
    private PersonInfo info;

    @Enumerated(EnumType.STRING)
    private Role role;

    @JdbcTypeCode(SqlTypes.JSON)
    private Recipe recipe;
}
