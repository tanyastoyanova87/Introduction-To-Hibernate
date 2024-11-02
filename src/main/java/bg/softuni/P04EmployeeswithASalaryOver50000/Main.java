package L03IntroductionToHibernateExercise.P04EmployeeswithASalaryOver50000;

import L03IntroductionToHibernateExercise.entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        List<Employee> resultList = entityManager
                .createQuery("FROM Employee WHERE salary > 50000", Employee.class)
                .getResultList();

        for (Employee employee : resultList) {
            System.out.println(employee.getFirstName());
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
