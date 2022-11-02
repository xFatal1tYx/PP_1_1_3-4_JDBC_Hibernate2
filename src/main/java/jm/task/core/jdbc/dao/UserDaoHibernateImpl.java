package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String comm = "CREATE TABLE IF NOT EXISTS Users (id BIGINT NOT NULL AUTO_INCREMENT, name CHAR(50) NOT NULL, lastName CHAR(50) NOT NULL, age TINYINT NOT NULL, primary key (id));";

        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.createSQLQuery(comm).executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
        String comm = "DROP TABLE IF EXISTS Users";

        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.createSQLQuery(comm).executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);

        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }

        System.out.println("User с именем - " + name + " добавлен в базу данных");
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("DELETE User WHERE id = " + id).executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users;
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            users = session.createQuery("FROM User").getResultList();
            session.getTransaction().commit();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("DELETE User").executeUpdate();
            session.getTransaction().commit();
        }
    }
}
