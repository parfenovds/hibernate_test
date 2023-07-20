package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Profile {
    @Id
    @Column(name = "user_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Basic
    private String street;
    @Basic
    private String language;

    public void setUser(User user){
        user.setProfile(this);
        this.user = user;
        this.id = user.getId();
    }
}
