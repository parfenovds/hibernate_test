package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"company", "profile"})
@Entity
@Table(name = "users", schema = "public")
public class User {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(info, user.info) && role == user.role && Objects.equals(company, user.company) && Objects.equals(recipe, user.recipe);
    }

    @PrePersist
    public void  soutGreetings(){
        System.out.println("User is saved");
    }

    @Override
    public int hashCode() {
        return 13;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverride(name = "birthDay", column = @Column(name = "birth_date"))
    @AttributeOverride(name = "deathDay", column = @Column(name = "death_date"))
    private PersonInfo info;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company company;

    @JdbcTypeCode(SqlTypes.JSON)
    private Recipe recipe;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Profile profile;

    @Builder.Default
    @ManyToMany
    @JoinTable(name = "users_chat",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name ="chat_id"))
    private Set<Chat> chats = new HashSet<>();

    public void addChat(Chat chat){
        chats.add(chat);
        chat.getUsers().add(this);
    }
}
