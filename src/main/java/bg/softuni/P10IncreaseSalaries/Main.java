package L03IntroductionToHibernateExercise.P10IncreaseSalaries;

import L03IntroductionToHibernateExercise.entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        List<Employee> resultList = entityManager
                .createQuery("FROM Employee WHERE department.name IN" +
                "('Engineering', 'Tool Design', 'Marketing', 'Information Services')", Employee.class)
                .getResultList();

        resultList.forEach(e -> {
            e.setSalary(e.getSalary().multiply(BigDecimal.valueOf(1.12)));
            System.out.printf("%s %s ($%.2f)%n",
                    e.getFirstName(), e.getLastName(), e.getSalary());
        });
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
