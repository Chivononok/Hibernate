package repository.jpa;

//import config.HibernateConnection;
import config.HibernateJavaConfig;
import entity.Client;

import entity.ClientPremium;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ClientRepository {

    private final SessionFactory sessionFactory;

    public ClientRepository(){
        this.sessionFactory = HibernateJavaConfig.getSessionFactory();
    }
/*
    public List<Client> getAll(){
        EntityManager entityManager = HibernateConnection.getEntityManager();
        List<Client> clients = entityManager.createQuery("select c from Client c").getResultList();
        entityManager.close();
        return clients;
    }

    public void addClient(Client client){
        EntityManager entityManager = HibernateConnection.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(client);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Client getClientById(Long id){
        EntityManager entityManager = HibernateConnection.getEntityManager();
        Query query = entityManager.createQuery("select c from Client c where c.id=:id");
        query.setParameter("id", id);
        Client client = (Client) query.getSingleResult();
        entityManager.close();
        return client;
    }

    public void removeClient(Long id){
        EntityManager entityManager = HibernateConnection.getEntityManager();
        Query query = entityManager.createQuery("select c from Client c where c.id=:id");
        query.setParameter("id", id);
        Client client = (Client) query.getSingleResult();
        if (client != null){
            entityManager.getTransaction().begin();
            entityManager.remove(client);
            entityManager.getTransaction().commit();
        }
        entityManager.close();
    }

    public void updateClient(Client client){
        EntityManager entityManager = HibernateConnection.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(client);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

 */

    public void addClientS(Client client){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(client);
        session.getTransaction().commit();
        session.close();
    }

    public Client findByIdS(Long id){
        Session session = sessionFactory.openSession();
        Client client = session.get(Client.class, id);
        return client;
    }

    public List<ClientPremium> findAllPremium(){
        Session session = sessionFactory.openSession();
        List<ClientPremium> clientPremiumList = session.createQuery("select c from ClientPremium c").getResultList();
        session.close();
        return  clientPremiumList;
    }
    public List<Client> findByNameJPQL(String name){
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select s from Client s where s.name=:name");
        query.setParameter("name", name);
        List<Client> clientList = query.getResultList();
        session.close();
        return clientList;
    }

    public List<Client> getClientBetweenAgeCriteria(Long minAge, Long maxAge){
        EntityManager entityManager = sessionFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Client> criteriaQuery =  criteriaBuilder.createQuery(Client.class);
        Root<Client> clientRoot = criteriaQuery.from(Client.class);
        criteriaQuery.select(clientRoot).where(criteriaBuilder
                .between(clientRoot.get("age"), minAge, maxAge))
                .orderBy(criteriaBuilder.desc(clientRoot.get("age")));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

}
