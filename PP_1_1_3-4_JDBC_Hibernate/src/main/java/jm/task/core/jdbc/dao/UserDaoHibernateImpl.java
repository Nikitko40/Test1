package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private final SessionFactory sessionFactory = Util.getPostgreSQLConnectionHibernate();

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.createSQLQuery("create table if not exists USERS " +
                            "(id serial Primary key, " +
                            "name VARCHAR(30), " +
                            "lastName VARCHAR(30), " +
                            "age INT)")
                    .executeUpdate();
            transaction.commit();
            System.out.println("Таблица создана");
        } catch (HibernateException e) {
            transaction.rollback();
            System.out.println("Ошибка при создании таблицы");
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS users").executeUpdate();
            transaction.commit();
            System.out.println("Таблица удалена");
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Ошибка при удалении таблицы");
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            User user = new User(name,lastName,age);
//            session.save(new User(name, lastName, age));
            session.save(user);
            transaction.commit();
            System.out.println("Пользователь " + name + " " + lastName + " добавлен в таблицу");
        } catch (HibernateException e) {
            transaction.rollback();
            System.out.println("Ошибка при добавлении пользователя");
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(session.get(User.class, id));
            transaction.commit();
            System.out.println("Пользователь удален из таблицы");
        } catch (HibernateException e) {
            transaction.rollback();
            System.out.println("Ошибка при удалении пользователя");
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<User> users = new ArrayList<>();
        try {
            transaction = session.beginTransaction();
            users = session.createSQLQuery("SELECT * from users").addEntity(User.class).list();
            System.out.println("Вывод списка пользователей");
            for (User user : users) {
                System.out.println(user);
            }
        } catch (HibernateException e) {
            System.out.println("Ошибка при выведение списка пользователей");
        } finally {
            session.close();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.createSQLQuery("truncate table users ").executeUpdate();
            transaction.commit();
            System.out.println("Таблица очищена");
        } catch (HibernateException e) {
            transaction.rollback();
            System.out.println("Ошибка при очистке таблицы");
        } finally {
            session.close();
        }
    }
}
