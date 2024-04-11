package co.edu.uniquindio.eps_uq.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

    private String name;
    @EqualsAndHashCode.Include
    private String id;
    private Integer age;
    private ClinicHistory history;
}
