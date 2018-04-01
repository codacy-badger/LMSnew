package ua.pp.jjd.lmsnew.repository;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.pp.jjd.lmsnew.BaseDomainTest;
import ua.pp.jjd.lmsnew.domain.Course;
import ua.pp.jjd.lmsnew.domain.Trainer;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TrainerRepositoryTest extends BaseDomainTest {

    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void findAll() {
        List<Trainer> trainers = trainerRepository.findAll();
        assertThat(trainers).hasSize(3);
    }

    @Test
    public void add() {
        Trainer trainer = Trainer.builder()
                .name("Кей Хорстманн")
                .build();
        int sizeBefore = trainerRepository.findAll().size();
        trainerRepository.saveAndFlush(trainer);
        int sizeAfter = trainerRepository.findAll().size();
        assertThat(sizeAfter).isGreaterThan(sizeBefore);
    }

    @Test
    public void delete() {
        int sizeBefore = trainerRepository.findAll().size();
        Trainer trainer = trainerRepository.findAll().get(0);
        for (Course course : trainer.getCourses()) {
            course.setTrainer(null);
            courseRepository.saveAndFlush(course);
        }
        trainerRepository.deleteById(trainer.getId());
        int sizeAfter = trainerRepository.findAll().size();
        assertThat(sizeAfter).isLessThan(sizeBefore);
    }

    @Test
    public void getById() {
        List<Trainer> trainers = trainerRepository.findAll();
        Trainer trainer = trainerRepository.getOne(trainers.get(0).getId());
        assertThat(trainer).isNotNull();
    }

    @Ignore
    @Test
    public void update() {
        List<Trainer> trainers = trainerRepository.findAll();
        Trainer trainer = trainerRepository.getOne(trainers.get(0).getId());
        trainer.setName("Кей Хорстманн");
        trainer.setCourses(null);
        System.out.println(trainer);
        trainerRepository.saveAndFlush(trainer);
        assertThat(trainers).hasSize(3);
    }

}
