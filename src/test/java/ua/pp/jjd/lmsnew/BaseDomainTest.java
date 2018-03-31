package ua.pp.jjd.lmsnew;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.pp.jjd.lmsnew.domain.Trainer;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@DataJpaTest
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

        entityManager.persist(trainer1);
        entityManager.persist(trainer2);
        entityManager.persist(trainer3);

    }

}
