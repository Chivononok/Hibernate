package repository.jpa;

import config.HibernateJavaConfig;
import entity.Worker;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class WorkerRepository {
    private final SessionFactory sessionFactory;

    public WorkerRepository(){this.sessionFactory = HibernateJavaConfig.getSessionFactory();};

    public void addWorker(Worker worker){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(worker);
        session.getTransaction().commit();
        session.close();
    }
}
