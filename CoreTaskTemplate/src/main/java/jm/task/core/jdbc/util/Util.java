package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/jm_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";


    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }


    /*
    public static SessionFactory createSessionFactory() {
        Configuration configuration = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

     */

    public static SessionFactory createSessionFactory() {
        Configuration cfg = new Configuration()
                .setProperty("hibernate.connection.url",
                        "jdbc:mysql://localhost:3306/jm_db?useSSL=false&amp;serverTimezone=UTC")
                .setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
                .setProperty("hibernate.connection.username", "root")
                .setProperty("hibernate.connection.password", "root")
                .setProperty("hibernate.current_session_context_class", "thread")
                .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                .setProperty("hibernate.show_sql", "true")
                .configure()
                .addAnnotatedClass(User.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(cfg.getProperties())
                .build();
        return cfg.buildSessionFactory(serviceRegistry);
    }
}
