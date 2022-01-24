package com.hibernate;

import com.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteApp {

    public static void main(String[] args) {

        try (SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory()) {

            // session
            Session session = factory.openSession();

            // start transaction
            session.beginTransaction();

            // query students where clause
            //            Student student = session.get(Student.class, 9);
            // delete user
            //            session.delete(student);

            // delete student directly
            session.createQuery("delete from Student where user_id = '7'").executeUpdate();

            // commit transaction
            session.getTransaction().commit();

            // close session
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
