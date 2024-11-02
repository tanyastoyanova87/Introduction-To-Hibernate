package L03IntroductionToHibernateExercise.P13RemoveTowns;

import L03IntroductionToHibernateExercise.entities.Address;
import L03IntroductionToHibernateExercise.entities.Employee;
import L03IntroductionToHibernateExercise.entities.Town;

import javax.persistence.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws IOException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        String townName = READER.readLine();
        List<Employee> employees = entityManager
                .createQuery("FROM Employee WHERE address.town.name = :name", Employee.class)
                .setParameter("name", townName)
                .getResultList();

        employees.forEach(e -> {
            e.setAddress(null);
            entityManager.persist(e);
        });

        List<Address> addresses = entityManager
                .createQuery("FROM Address WHERE town.name = :name", Address.class)
                .setParameter("name", townName)
                .getResultList();

        addresses.forEach(entityManager::remove);

        Town town = entityManager
                .createQuery("FROM Town WHERE name = :name", Town.class)
                .setParameter("name", townName)
                .getSingleResult();

        entityManager.remove(town);

        System.out.printf("%d address in %s deleted%n", addresses.size(), townName);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
