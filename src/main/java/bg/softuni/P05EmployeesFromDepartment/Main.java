package L03IntroductionToHibernateExercise.P05EmployeesFromDepartment;

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
                .createQuery("FROM Employee WHERE department.name = 'Research and Development' " +
                "ORDER BY salary, id", Employee.class).getResultList();

        resultList.forEach(e -> {
            System.out.printf("%s %s from %s - $%.2f%n",
                    e.getFirstName(), e.getLastName(), e.getDepartment().getName(), e.getSalary());
        });
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
