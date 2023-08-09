package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.converter.BirthdayConverter;
import org.example.converter.DeathdayConverter;
import org.example.entity.Address;
import org.example.entity.Company;
import org.example.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;
import org.hibernate.graph.GraphSemantic;

import java.util.List;
import java.util.Map;

@Slf4j(topic = "org.example")
public class HiberRunner {

    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure();
        configuration.addAttributeConverter(DeathdayConverter.class, true);
        configuration.addAttributeConverter(BirthdayConverter.class, true);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Company.class);
        configuration.addAnnotatedClass(Address.class);
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        try (SessionFactory factory = configuration.buildSessionFactory();
             Session session = factory.openSession()) {
            session.beginTransaction();
            Map<String, Object> graph = Map.of(
                    GraphSemantic.LOAD.getJakartaHintName(), session.getEntityGraph("UserWithCompany")
            );
            var user = session.find(User.class, 1L, graph);
            List<User> selectUFromUserU = session.createQuery("SELECT u FROM User u", User.class)
                    .setHint(GraphSemantic.LOAD.getJakartaHintName(), session.getEntityGraph("UserWithCompany"))
                    .getResultList();

            for (User us : selectUFromUserU) {
                System.out.println(us);

            }

            session.createQuery("select u from User u", User.class);
            session.getTransaction().commit();
        }
    }
}
