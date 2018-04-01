package ua.pp.jjd.lmsnew.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class CourseDTO implements Serializable {

    private static final long serialVersionUID = 686502124073769746L;

    private Long id;
    private String name;
    private String startDate;
    private String endDate;
    private String trainer;
    private List<String> students;

}
