package repository.jpa;

import config.HibernateJavaConfig;
import entity.Visitor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class VisitorRepository {
    private final SessionFactory sessionFactory;

    public VisitorRepository(){this.sessionFactory = HibernateJavaConfig.getSessionFactory();};

    public void addVisitor(Visitor visitor){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(visitor);
        session.getTransaction().commit();
        session.close();
    }
}
