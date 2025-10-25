package repository.jpa;

import config.HibernateJavaConfig;
import entity.Sign;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class SignRepository {
    private final SessionFactory sessionFactory;
    public SignRepository(){this.sessionFactory = HibernateJavaConfig.getSessionFactory();};

    public void addSign(Sign sign){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(sign);
        session.getTransaction().commit();
        session.close();
    }
}
