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
        configuration.addAnnotatedClass(Company.class);
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        Company company = Company.builder()
                .name("Google")
                .build();
        User user = User.builder()
                .info(PersonInfo.builder()
                        .userName("Vredina")
                        .firstName("Dmitriy")
                        .lastName("Parfenov")
                        .birthDay(new Birthday(LocalDate.now()))
                        .deathDay(new Deathday(LocalDate.now(), LocalDate.now().plusDays(10)))
                        .build())
                .company(company)
                .role(Role.ADMIN)
                .recipe(new Recipe("cancer", "lie in bed"))
                .build();
        try (SessionFactory factory = configuration.buildSessionFactory();
             Session session = factory.openSession()) {
            session.beginTransaction();
            User user1 = session.get(User.class, 3L);
            Company company1 = user1.getCompany();
            System.out.println(company1.toString());
            session.getTransaction().commit();
        }
    }
}
