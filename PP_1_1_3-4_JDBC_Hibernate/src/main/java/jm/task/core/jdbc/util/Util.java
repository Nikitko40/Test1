package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "212159pin";
    private static SessionFactory sessionFactory = null;


    public static Connection getPostgreSQLConnectionJDBC() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Ошибка подключения");
        }
        System.out.println("Подключение установленно");
        return connection;
    }

    public static SessionFactory getPostgreSQLConnectionHibernate() {

        try {
            Configuration configuration = new Configuration()
                    .setProperty("hibernate.connection.url", DB_URL)
                    .setProperty("hibernate.connection.username", USER)
                    .setProperty("hibernate.connection.password", PASSWORD)
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL82Dialect")
                    .addAnnotatedClass(User.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (HibernateException e) {
            e.printStackTrace();
            System.out.println("Ошибка подключения");
        }
        System.out.println("Подключение установленно");
        return sessionFactory;
    }


    public static void main(String[] args) {
        getPostgreSQLConnectionHibernate();

    }
}
