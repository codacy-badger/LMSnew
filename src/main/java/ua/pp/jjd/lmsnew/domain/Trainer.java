package ua.pp.jjd.lmsnew.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "trainer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class Trainer implements Serializable {

    private static final long serialVersionUID = -4563086931025194988L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name", length = 30)
    private String name;

    @OneToMany(mappedBy = "trainer", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Course> courses;

}
