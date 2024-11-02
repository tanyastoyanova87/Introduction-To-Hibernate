package L03IntroductionToHibernateExercise.P06AddingANewAddressAndUpdatingTheEmployee;

import L03IntroductionToHibernateExercise.entities.Address;
import L03IntroductionToHibernateExercise.entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        String lastName = READER.readLine();

        Address address = new Address();
        address.setText("Vitoshka 15");
        entityManager.persist(address);

        Employee employee = entityManager
                .createQuery("FROM Employee WHERE lastName = :lastName", Employee.class)
                .setParameter("lastName", lastName).getSingleResult();

        employee.setAddress(address);
        entityManager.persist(employee);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
