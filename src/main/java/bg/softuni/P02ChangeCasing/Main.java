package L03IntroductionToHibernateExercise.P02ChangeCasing;

import L03IntroductionToHibernateExercise.entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        List<Town> resultList = entityManager
                .createQuery("FROM Town WHERE LENGTH(name) > 5", Town.class).getResultList();
        resultList
                .forEach(t -> {
                    t.setName(t.getName().toUpperCase());
                    entityManager.persist(t);
                });

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
