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
        try {
            throw new Exception("Bla bla");
        }
        catch(Exception e){
            log.error("At try block error was occurred", e);
        }

        Configuration configuration = new Configuration().configure();
        configuration.addAttributeConverter(DeathdayConverter.class, true);
        configuration.addAttributeConverter(BirthdayConverter.class, true);
        configuration.addAnnotatedClass(User.class);
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());

        try (SessionFactory factory = configuration.buildSessionFactory();
             Session session = factory.openSession()) {
            session.beginTransaction();
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
            session.persist(user);
            session.getTransaction().commit();





        }
    }
}
