package repository.jpa;

import config.HibernateJavaConfig;
import entity.Visit;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class VisitRepository {
    private final SessionFactory sessionFactory;

    public VisitRepository(){this.sessionFactory = HibernateJavaConfig.getSessionFactory();};

    public void addVisit(Visit visit){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(visit);
        session.getTransaction().commit();
        session.close();
    }
}
