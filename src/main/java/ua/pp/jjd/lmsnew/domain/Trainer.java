package ua.pp.jjd.lmsnew.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "trainer")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "trainer_id", length = 4)
    private Long trainerId;

    @Column(name = "name", length = 30)
    private String name;

}