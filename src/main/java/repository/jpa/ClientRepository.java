package repository.jpa;

import config.HibernateConnection;
import entity.Client;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class ClientRepository {

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

}
