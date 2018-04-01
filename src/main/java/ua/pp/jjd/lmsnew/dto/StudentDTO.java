package ua.pp.jjd.lmsnew.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class StudentDTO implements Serializable {

    private static final long serialVersionUID = -2283501002179782172L;

    private Long id;
    private String name;
    private List<String> courses;

}
