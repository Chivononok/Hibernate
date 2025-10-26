package repository.jpa;

import config.HibernateJavaConfig;
import entity.Opportunity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
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

    public Opportunity getOpportunityWithMinPriceCriteria(){
        EntityManager entityManager = sessionFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Double> criteriaPrice = criteriaBuilder.createQuery(Double.class);
        Root<Opportunity> rootPrice = criteriaPrice.from(Opportunity.class);
        criteriaPrice.select(criteriaBuilder.min(rootPrice.get("price")));
        Double minPrice = entityManager.createQuery(criteriaPrice).getSingleResult();   //Нашли минимальную цену в услугах

        CriteriaQuery<Opportunity> criteriaQuery = criteriaBuilder.createQuery(Opportunity.class);
        Root<Opportunity> rootOpportunity = criteriaQuery.from(Opportunity.class);
        criteriaQuery.select(rootOpportunity).where(criteriaBuilder.equal(rootOpportunity.get("price"), minPrice));
        return entityManager.createQuery(criteriaQuery).getSingleResult();  //нашли услугу, у которой минимальная цена
    }
}
