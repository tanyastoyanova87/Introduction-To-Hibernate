package L03IntroductionToHibernateExercise.P09FindTheLatest10Projects;

import L03IntroductionToHibernateExercise.entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        List<Project> resultList = entityManager
                .createQuery("FROM Project ORDER BY startDate DESC", Project.class)
                .setMaxResults(10).getResultList();
        resultList.stream()
                .sorted(Comparator.comparing(Project::getName))
                        .forEach(p -> {
                            System.out.printf("Project name: %s%n" +
                                    " \tProject Description: %s%n" +
                                    " \tProject Start Date:%s%n" +
                                    " \tProject End Date: %s%n",
                                    p.getName(), p.getDescription(), p.getStartDate(), p.getEndDate());
                        });
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
