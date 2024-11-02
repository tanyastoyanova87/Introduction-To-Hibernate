package L03IntroductionToHibernateExercise.P07AddressesWithEmployeeCount;

import L03IntroductionToHibernateExercise.entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager
                .createQuery("FROM Address ORDER BY employees.size DESC", Address.class)
                .setMaxResults(10)
                .getResultList()
                .forEach(e -> System.out.printf("%s, %s - %d employees",
                        e.getText(), e.getTown().getName(), e.getEmployees().size()));

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
