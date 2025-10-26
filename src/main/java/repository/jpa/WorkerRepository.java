package repository.jpa;

import config.HibernateJavaConfig;
import entity.User;
import entity.Worker;
import jakarta.persistence.Query;
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

    public Worker getWorkerWithMaxSalary(){
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select w from Worker w where w.salary = (select max(w1.salary) from Worker w1)");
        Worker workerWithMaxSalary = (Worker)query.getSingleResult();
        session.close();
        return workerWithMaxSalary;
    }

    public Worker getWorkerWithMinSalary(){
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select w from Worker w where w.salary = (select min(w1.salary) from Worker w1)");
        Worker workerWithMinSalary = (Worker)query.getSingleResult();
        session.close();
        return workerWithMinSalary;
    }

    public Long getCostPerMonth(){
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select sum(w.salary) from Worker w");
        Long costPerMonth = (Long) query.getSingleResult();
        session.close();
        return costPerMonth;
    }
}
