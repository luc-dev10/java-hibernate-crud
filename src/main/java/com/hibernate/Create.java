package com.hibernate;

import com.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Create {
    public static void main(String[] args) {

        // does not need to specify name of the configuration file. Hibernate will look for hibernate.cfg.xml on its own
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Student student = new Student();
        student.setFirstName("Dev");
        student.setLastName("zhao");
        student.setEmail("dev.1008@gmail.com");

        try {
            // session
            Session session = factory.openSession();

            // start transaction
            session.beginTransaction();

            // save the student
            session.save(student);

            // commit transaction
            session.getTransaction().commit();

            // close session
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // fetch user
        try {
            // session
            Session session = factory.openSession();

            // start transaction
            session.beginTransaction();

            // use session to save java object
            Student studentFetch = session.get(Student.class, student.getUserId());

            // commit transaction
            session.getTransaction().commit();

            // close session
            session.close();

            // output values
            System.out.print(studentFetch.getEmail());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }

    }
}
