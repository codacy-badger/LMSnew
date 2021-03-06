package ua.pp.jjd.lmsnew.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TrainerDTO implements Serializable {

    private static final long serialVersionUID = 3718994379639747055L;

    private Long id;
    private String name;
    private List<String> courses;

}
