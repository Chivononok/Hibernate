package config;

import entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class HibernateJavaConfig {

    private final static StandardServiceRegistryBuilder serviceRegistryBuilder;
    private final static Configuration configuration;

    static {
        Properties properties = new Properties();
        properties.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/SportCenter");
        properties.setProperty("hibernate.connection.username", "postgres");
        properties.setProperty("hibernate.connection.password", "123");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.format_sql", "true");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        configuration = new Configuration();

        configuration.addAnnotatedClass(Client.class);
        configuration.addAnnotatedClass(FitRoom.class);
        configuration.addAnnotatedClass(Opportunity.class);
        //configuration.addAnnotatedClass(FitroomWithSubselect.class);
        //configuration.addAnnotatedClass(ClientPremium.class);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Visitor.class);
        configuration.addAnnotatedClass(Worker.class);
        configuration.addAnnotatedClass(Visit.class);
        configuration.addAnnotatedClass(Sign.class);
        serviceRegistryBuilder = new StandardServiceRegistryBuilder();
        serviceRegistryBuilder.applySettings(properties);
    }

    public static SessionFactory getSessionFactory(){
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistryBuilder.build());
        return sessionFactory;
    }

}
