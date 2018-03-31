package ua.pp.jjd.lmsnew.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.pp.jjd.lmsnew.domain.Trainer;
import ua.pp.jjd.lmsnew.dto.TrainerDTO;
import ua.pp.jjd.lmsnew.repository.TrainerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TrainerService {

    private final TrainerRepository trainerRepository;

    @Transactional
    public List<TrainerDTO> getAll() {
        return trainerRepository.findAll().stream()
                .map(this::fromTrainer)
                .collect(Collectors.toList());
    }

    @Transactional
    public TrainerDTO getById(Long id) {
        return fromTrainer(trainerRepository.getOne(id));
    }

    @Transactional
    public TrainerDTO add(TrainerDTO trainerDTO) {
        Trainer trainerSaved = trainerRepository.saveAndFlush(fromDTO(trainerDTO));
        return fromTrainer(trainerSaved);
    }

    @Transactional
    public TrainerDTO update(TrainerDTO trainerDTO) {
        Trainer trainerSaved = trainerRepository.saveAndFlush(fromDTO(trainerDTO));
        return fromTrainer(trainerSaved);
    }

    @Transactional
    public void delete(Long id) {
        trainerRepository.deleteById(id);
    }





    private TrainerDTO fromTrainer(Trainer trainer) {
        TrainerDTO trainerDTO = null;
        if (trainer != null) {
            trainerDTO = TrainerDTO.builder()
                    .trainerId(trainer.getTrainerId())
                    .name(trainer.getName())
                    .build();
        }
        return trainerDTO;
    }

    private Trainer fromDTO(TrainerDTO trainerDTO) {
        Trainer trainer = null;
        if (trainerDTO != null) {
            trainer = Trainer.builder()
                    .trainerId(trainerDTO.getTrainerId())
                    .name(trainerDTO.getName())
                    .build();
        }
        return trainer;
    }

}
