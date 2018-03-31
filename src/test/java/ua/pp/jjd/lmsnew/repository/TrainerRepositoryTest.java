package ua.pp.jjd.lmsnew.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.pp.jjd.lmsnew.BaseDomainTest;
import ua.pp.jjd.lmsnew.domain.Trainer;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TrainerRepositoryTest extends BaseDomainTest {

    @Autowired
    private TrainerRepository trainerRepository;

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
        trainerRepository.deleteById(trainerRepository.findAll().get(0).getTrainerId());
        int sizeAfter = trainerRepository.findAll().size();
        assertThat(sizeAfter).isLessThan(sizeBefore);
    }

    @Test
    public void getById() {
        List<Trainer> trainers = trainerRepository.findAll();
        Trainer trainer = trainerRepository.getOne(trainers.get(0).getTrainerId());
        assertThat(trainer).isNotNull();
    }

    @Test
    public void update() {
        List<Trainer> trainers = trainerRepository.findAll();
        Trainer trainer = trainerRepository.getOne(trainers.get(0).getTrainerId());
        trainer = trainer.toBuilder().name("Кей Хорстманн").build();
        trainerRepository.saveAndFlush(trainer);
        assertThat(trainers).hasSize(3);
    }

}
