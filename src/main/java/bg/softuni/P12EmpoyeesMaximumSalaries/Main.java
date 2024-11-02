package L03IntroductionToHibernateExercise.P12EmpoyeesMaximumSalaries;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        List<Object[]> resultList = entityManager
                .createQuery("SELECT d.name, MAX(e.salary) FROM Department d " +
                        "JOIN d.employees e " +
                        "GROUP BY d.name " +
                        "HAVING MAX(salary) NOT BETWEEN 30000 AND 70000", Object[].class)
                .getResultList();

        resultList.forEach(e -> {
            System.out.printf("%s %s%n", e[0], e[1]);
        });

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
