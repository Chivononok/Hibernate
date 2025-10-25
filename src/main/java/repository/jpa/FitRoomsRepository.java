package repository.jpa;

import config.HibernateJavaConfig;
import entity.FitRoom;
import entity.FitroomWithSubselect;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

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
}
