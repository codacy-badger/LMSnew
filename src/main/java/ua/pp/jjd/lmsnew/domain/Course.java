package ua.pp.jjd.lmsnew.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "course")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Course implements Serializable {

    private static final long serialVersionUID = -8108478004525978447L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name", length = 30)
    private String name;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

}