package ua.pp.jjd.lmsnew.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.pp.jjd.lmsnew.domain.Trainer;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {

    Trainer findTrainerByName(String name);

}
