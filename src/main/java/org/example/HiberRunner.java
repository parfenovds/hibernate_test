package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.entity.Language;
import org.example.entity.Manager;
import org.example.entity.Programmer;
import org.example.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

@Slf4j(topic = "org.example")
public class HiberRunner {

    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Programmer.class);
        configuration.addAnnotatedClass(Manager.class);
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());



        try (SessionFactory factory = configuration.buildSessionFactory();
             Session session = factory.openSession()) {
            session.beginTransaction();

            var programmer = new Programmer(Language.Ruby);
            session.persist(programmer);
            var manager = new Manager("starter");
            session.persist(manager);

            session.get(Programmer.class, 1L);
            session.getTransaction().commit();
        }




    }
}
