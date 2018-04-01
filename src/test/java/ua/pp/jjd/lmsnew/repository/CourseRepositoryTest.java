package ua.pp.jjd.lmsnew.repository;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.pp.jjd.lmsnew.BaseDomainTest;
import ua.pp.jjd.lmsnew.domain.Course;
import ua.pp.jjd.lmsnew.domain.Trainer;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CourseRepositoryTest extends BaseDomainTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void findAll() {
        List<Course> courses = courseRepository.findAll();
        assertThat(courses).hasSize(5);
    }

    @Test
    public void add() {
        Trainer trainer = Trainer.builder()
                .name("Кей Хорстманн")
                .build();
        Course course = Course.builder()
                .name("Шаблоны проектирования")
                .startDate(LocalDate.of(2018, 1, 1))
                .endDate(LocalDate.of(2018, 1, 13))
                .trainer(trainer)
                .build();
        int sizeBefore = courseRepository.findAll().size();
        courseRepository.saveAndFlush(course);
        int sizeAfter = courseRepository.findAll().size();
        assertThat(sizeAfter).isGreaterThan(sizeBefore);
    }
    @Ignore
    @Test
    public void delete() {
        int sizeBefore = courseRepository.findAll().size();
        Course course = courseRepository.findAll().get(0);
        courseRepository.deleteById(course.getId());
        int sizeAfter = courseRepository.findAll().size();
        assertThat(sizeAfter).isLessThan(sizeBefore);
    }

    @Test
    public void getById() {
        List<Course> courses = courseRepository.findAll();
        Course course = courseRepository.getOne(courses.get(0).getId());
        assertThat(course).isNotNull();
    }

    @Ignore
    @Test
    public void update() {
        List<Course> courses = courseRepository.findAll();
        Course course = courseRepository.getOne(courses.get(0).getId());
        course.setName("ООП");
        courseRepository.saveAndFlush(course);
        assertThat(courses).hasSize(5);
    }

}
