package ua.pp.jjd.lmsnew.dto;

import lombok.*;

import java.io.Serializable;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TrainerDTO implements Serializable {

    private static final long serialVersionUID = 3718994379639747055L;

    private Long trainerId;
    private String name;

}
