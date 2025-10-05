package repository.jpa;

import config.HibernateJavaConfig;
import entity.Opportunity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class OpportunityRepository {

    private final SessionFactory sessionFactory;

    public OpportunityRepository(){
        this.sessionFactory = HibernateJavaConfig.getSessionFactory();
    }

    public void addOpportunity(Opportunity opportunity){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(opportunity);
        session.getTransaction().commit();
        session.close();
    }
}
