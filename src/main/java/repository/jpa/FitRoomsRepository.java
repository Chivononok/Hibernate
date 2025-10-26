package repository.jpa;

import config.HibernateJavaConfig;
import entity.Client;
import entity.FitRoom;
import entity.FitroomWithSubselect;
import entity.Opportunity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FitRoomsRepository {
    private final SessionFactory sessionFactory;

    public FitRoomsRepository(){
        this.sessionFactory = HibernateJavaConfig.getSessionFactory();
    }

    public void addFitRoom(FitRoom fitRoom){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(fitRoom);
        session.getTransaction().commit();
        session.close();
    }

    public FitRoom updatePrice(Long id, Double price){
        Session session = sessionFactory.openSession();
        FitRoom fitRoom = session.get(FitRoom.class, id);
        fitRoom.setPrice(price);
        session.beginTransaction();
        session.merge(fitRoom);
        session.getTransaction().commit();
        session.close();
        return fitRoom;
    }

    public FitRoom updateRoomnumberDetach(Long id, String roomNumber){
        Session session = sessionFactory.openSession();
        FitRoom fitRoom = session.get(FitRoom.class, id);
        session.detach(fitRoom);
        fitRoom.setId(null);
        fitRoom.setRoomNumber(roomNumber);
        session.beginTransaction();
        session.merge(fitRoom);
        session.getTransaction().commit();
        session.close();
        return fitRoom;
    }

    public List<FitroomWithSubselect> findAllSub(){
        Session session = sessionFactory.openSession();
        List<FitroomWithSubselect> fitRoomList = session.createQuery("select s from FitroomWithSubselect s").getResultList();
        session.getTransaction().begin();
        session.close();
        return fitRoomList;
    }

    public void deleteById(Long id){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        FitRoom fitRoom = session.get(FitRoom.class, id);
        if (fitRoom != null){
            session.remove(fitRoom);
        }
        session.getTransaction().commit();
        session.close();
    }

    public Map<String, Double> getPricePerUser(){
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select f.roomName as roomName, f.price/f.capacity as price from FitRoom f group by roomName, price");
        List<Object[]> fitrooms = (List<Object[]>) query.getResultList();
        Map<String, Double> pricePerUser = new HashMap<>();
        fitrooms.forEach(o -> {
            pricePerUser.put(String.valueOf(o[0]), (Double) o[1]);
        });
        session.close();
        return pricePerUser;
    }

    public Long getAllCountClientsInTimeCriteria(){
        EntityManager entityManager = sessionFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery =  criteriaBuilder.createQuery(Long.class);
        Root<FitRoom> rootFitroom = criteriaQuery.from(FitRoom.class);
        criteriaQuery.select(criteriaBuilder.sum(rootFitroom.get("capacity")));
        Long countClients = entityManager.createQuery(criteriaQuery).getSingleResult();
        entityManager.close();
        return countClients;
    }

    public List<FitRoom> getRoomsVisitedByClientsOverThan(Long age) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<FitRoom> criteriaQuery = criteriaBuilder.createQuery(FitRoom.class);
        Root<FitRoom> fitRoomRoot = criteriaQuery.from(FitRoom.class);
        Join<Object, Object> signJoin = fitRoomRoot.join("signList");
        Join<Object, Object> clientJoin = signJoin.join("client");
        criteriaQuery.select(fitRoomRoot)
                .where(criteriaBuilder.gt(clientJoin.get("age"), age))
                .distinct(true);

        List<FitRoom> fitRoomList = entityManager.createQuery(criteriaQuery).getResultList();
        entityManager.close();
        return fitRoomList;
    }
}
