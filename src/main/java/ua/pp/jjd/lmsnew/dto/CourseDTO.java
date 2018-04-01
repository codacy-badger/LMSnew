package ua.pp.jjd.lmsnew.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CourseDTO implements Serializable {

    private static final long serialVersionUID = 686502124073769746L;

    private Long id;
    private String name;
    private String startDate;
    private String endDate;
    private String trainer;

}
