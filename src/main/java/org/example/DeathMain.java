package org.example;

import org.example.converter.BirthdayConverter;
import org.example.converter.DeathdayConverter;
import org.example.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

public class DeathMain {
    public static void main(String[] args) {

        Configuration configuration = new Configuration().configure();
        configuration.addAttributeConverter(DeathdayConverter.class, true);
        configuration.addAttributeConverter(BirthdayConverter.class, true);
        configuration.addAnnotatedClass(User.class);
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());

        try (SessionFactory factory = configuration.buildSessionFactory();
             Session session = factory.openSession()) {
            session.beginTransaction();
            User user = User.builder()
                    .firstName("Dmitriy")
                    .lastName("Parfenov")
                    .role(Role.ADMIN)
                    .recipe(new Recipe("cancer", "lie in bed"))
                    .birthDay(new Birthday(LocalDate.now()))
                    .deathDay(new Deathday(LocalDate.now(), LocalDate.now().plusDays(10)))
                    .build();

            session.persist(user);
            session.getTransaction().commit();
        }
    }
}
