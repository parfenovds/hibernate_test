package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.converter.BirthdayConverter;
import org.example.converter.DeathdayConverter;
import org.example.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

@Slf4j(topic = "org.example")
public class HiberRunner {

    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure();
        configuration.addAttributeConverter(DeathdayConverter.class, true);
        configuration.addAttributeConverter(BirthdayConverter.class, true);
        configuration.addAnnotatedClass(User.class);
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        User user = User.builder()
                .info(PersonInfo.builder()
                        .userName("Vredina")
                        .firstName("Dmitriy")
                        .lastName("Parfenov")
                        .birthDay(new Birthday(LocalDate.now()))
                        .deathDay(new Deathday(LocalDate.now(), LocalDate.now().plusDays(10)))
                        .build())
                .role(Role.ADMIN)
                .recipe(new Recipe("cancer", "lie in bed"))
                .build();
        try (SessionFactory factory = configuration.buildSessionFactory()) {
            try (Session session = factory.openSession()) {
                session.beginTransaction();
                session.persist(user);
                session.flush();
                session.detach(user);
                session.getTransaction().commit();
            }
            try (Session session = factory.openSession()) {
                session.beginTransaction();
                User userPrepareToMerge = session.get(User.class, 1L);
                user.setRole(Role.USER);
                session.getTransaction().commit();
            }
            try (Session session = factory.openSession()) {
                session.beginTransaction();
                User mergedUser = session.merge(user);
                session.getTransaction().commit();
            }

        }
    }
}