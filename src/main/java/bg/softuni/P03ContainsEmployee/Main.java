package L03IntroductionToHibernateExercise.P03ContainsEmployee;

import L03IntroductionToHibernateExercise.entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
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

        String[] name = READER.readLine().split("\\s+");
        Query query = entityManager
                .createQuery("FROM Employee WHERE firstName = :firstName " +
                                "AND lastName = :lastName", Employee.class);

        query.setParameter("firstName", name[0]);
        query.setParameter("lastName", name[1]);

        List<Employee> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            System.out.println("No");
        } else {
            System.out.println("Yes");
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
