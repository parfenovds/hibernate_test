package org.example;

import java.time.LocalDate;
import org.example.converter.BirthdayConverter;
import org.example.entity.Birthday;
import org.example.entity.Test;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
  public static void main(String[] args) {
    Configuration configuration = new Configuration();
    configuration.configure();
    configuration.addAnnotatedClass(Test.class);
    configuration.addAttributeConverter(new BirthdayConverter(), true);

    try(SessionFactory sessionFactory = configuration.buildSessionFactory();
    Session session = sessionFactory.openSession()) {
      session.beginTransaction();
      Test test = Test.builder().text("Hello?").birthday(new Birthday(LocalDate.of(1991, 4, 15))).build();
      session.persist(test);

      Test test1 = session.get(Test.class, 1);
      System.out.println(test1);
      session.getTransaction().commit();

//      test1.setText("Bye bye");
//      session.merge(test1);
    }
  }
}

