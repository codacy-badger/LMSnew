package ua.pp.jjd.lmsnew.service;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import ua.pp.jjd.lmsnew.BaseDomainTest;
import ua.pp.jjd.lmsnew.dto.TrainerDTO;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ComponentScan
public class TrainerServiceTest extends BaseDomainTest {

    @Autowired
    private TrainerService trainerService;

    @Test
    public void getAll() {
        List<TrainerDTO> all = trainerService.getAll();
        assertThat(all).hasSize(3);
    }

    @Test
    public void getById() {
        List<TrainerDTO> all = trainerService.getAll();
        TrainerDTO trainerDTO = trainerService.getById(all.get(0).getId());
        assertThat(trainerDTO).isNotNull();
    }

    @Test
    public void add() {
        TrainerDTO trainerDTO = TrainerDTO.builder()
                .name("Бейтс Берт")
                .build();
        int sizeBefore = trainerService.getAll().size();
        trainerService.add(trainerDTO);
        int sizeAfter = trainerService.getAll().size();
        assertThat(sizeAfter).isGreaterThan(sizeBefore);
    }

    @Ignore
    @Test
    public void delete() {
        int sizeBefore = trainerService.getAll().size();
        trainerService.delete(trainerService.getAll().get(0).getId());
        int sizeAfter = trainerService.getAll().size();
        assertThat(sizeAfter).isLessThan(sizeBefore);
    }

    @Ignore
    @Test
    public void update() {
        TrainerDTO trainerDTOBefore = trainerService.getAll().get(0);
        TrainerDTO trainerDTOAfter = trainerDTOBefore.toBuilder().name("Бейтс Берт").build();
        trainerService.update(trainerDTOAfter);
        trainerDTOAfter = trainerService.getAll().get(0);
        assertThat(trainerDTOBefore).isNotEqualTo(trainerDTOAfter);
    }

}
