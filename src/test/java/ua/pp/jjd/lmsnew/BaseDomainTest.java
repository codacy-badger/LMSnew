package ua.pp.jjd.lmsnew;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import ua.pp.jjd.lmsnew.domain.Course;
import ua.pp.jjd.lmsnew.domain.Trainer;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan
public abstract class BaseDomainTest {

    @Autowired
    private EntityManager entityManager;

    @Before
    public void initDataBase() {

        Trainer trainer1 = Trainer.builder()
                .name("Брюс Эккель")
                .build();
        Trainer trainer2 = Trainer.builder()
                .name("Яков Файн")
                .build();
        Trainer trainer3 = Trainer.builder()
                .name("Джошуа Блох")
                .build();

        Course course1 = Course.builder()
                .name("Основы программирования")
                .startDate(LocalDate.of(2018, 1, 1))
                .endDate(LocalDate.of(2018, 1, 13))
                .trainer(trainer1)
                .build();

        Course course2 = Course.builder()
                .name("Программироваине на Java")
                .startDate(LocalDate.of(2018, 2, 1))
                .endDate(LocalDate.of(2018, 3, 31))
                .trainer(trainer1)
                .build();

        Course course3 = Course.builder()
                .name("Базы данных")
                .startDate(LocalDate.of(2018, 2, 1))
                .endDate(LocalDate.of(2018, 3, 31))
                .trainer(trainer2)
                .build();

        Course course4 = Course.builder()
                .name("Использование фреймворков")
                .startDate(LocalDate.of(2018, 1, 15))
                .endDate(LocalDate.of(2018, 2, 15))
                .trainer(trainer2)
                .build();

        Course course5 = Course.builder()
                .name("Java EE")
                .startDate(LocalDate.of(2018, 4, 1))
                .endDate(LocalDate.of(2018, 4, 30))
                .trainer(trainer3)
                .build();

        trainer1.setCourses(Arrays.asList(course1, course2));
        trainer2.setCourses(Arrays.asList(course3, course4));
        trainer3.setCourses(Collections.singletonList(course5));

        entityManager.persist(trainer1);
        entityManager.persist(trainer2);
        entityManager.persist(trainer3);

    }

}
