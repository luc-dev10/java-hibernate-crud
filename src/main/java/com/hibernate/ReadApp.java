package com.hibernate;

import com.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ReadApp {

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

            // query students
            List<Student> students = session.createQuery("from Student").getResultList();

            // print all emails from students
            students.forEach(student -> System.out.println(student.getEmail()));

            // query students where clause
            List<Student> filteredStudents = session.createQuery("from Student s where s.lastName = 'zhao'").getResultList();
            for (Student filteredStudent : filteredStudents) {
                System.out.println(filteredStudent.getFirstName());
            }
            // commit transaction
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
