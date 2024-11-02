package L03IntroductionToHibernateExercise.P11FindEmployeesByFirstName;

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

        String text = READER.readLine();
        String pattern = String.format("%s%%", text);
        Query query = entityManager.createQuery("FROM Employee WHERE firstName LIKE :pattern");

        query.setParameter("pattern", pattern);
        List<Employee> resultList = query.getResultList();

        resultList.forEach(e -> {
            System.out.printf("%s %s - %s - ($%.2f)%n",
                    e.getFirstName(), e.getLastName(), e.getJobTitle(), e.getSalary());
        });
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
