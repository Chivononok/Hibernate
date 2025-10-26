package repository.jpa;

import config.HibernateJavaConfig;
import entity.User;
import entity.Worker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

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

    public List<Worker> getAllWorkersCriteria(){
        EntityManager entityManager = sessionFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Worker> criteriaQuery = criteriaBuilder.createQuery(Worker.class);
        Root<Worker> root = criteriaQuery.from(Worker.class);
        criteriaQuery.select(root);
        List<Worker> workerList = entityManager.createQuery(criteriaQuery).getResultList();
        return workerList;
    }
}
