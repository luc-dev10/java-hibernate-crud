package com.hibernate;

import com.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateApp {

    public static void main(String[] args) {

        // does not need to specify name of the configuration file. Hibernate will look for hibernate.cfg.xml on its own
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        try {
            // session
            Session session = factory.openSession();

            // start transaction
            session.beginTransaction();

            // read the user 1
            // Student student = session.get(Student.class, 1);
            // System.out.println(student.getEmail());
            //
            // change student properties
            // student.setEmail("lucio.changed@gmail.com");

            // perform mass update
            session.createQuery("Update Student set email= 'email.changed@gmail.com'").executeUpdate();

            session.getTransaction().commit();

            // close session
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }

    }

}


