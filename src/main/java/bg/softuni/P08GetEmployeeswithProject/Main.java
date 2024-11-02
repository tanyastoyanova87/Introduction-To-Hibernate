package L03IntroductionToHibernateExercise.P08GetEmployeeswithProject;

import L03IntroductionToHibernateExercise.entities.Employee;
import L03IntroductionToHibernateExercise.entities.Project;

import javax.persistence.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;

public class Main {
    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        int id = Integer.parseInt(READER.readLine());
        Employee employee = entityManager.find(Employee.class, id);
        System.out.printf("%s %s - %s%n",
                employee.getFirstName(), employee.getLastName(), employee.getJobTitle());

        employee.getProjects()
                .stream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(p -> {
                    System.out.printf("\t%s%n", p.getName());
        });
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
