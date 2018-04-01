package ua.pp.jjd.lmsnew.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class Student implements Serializable {

    private static final long serialVersionUID = -4643588551442498019L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name", length = 30)
    private String name;

    @ManyToMany(mappedBy = "students", fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL})
    private List<Course> courses;

}