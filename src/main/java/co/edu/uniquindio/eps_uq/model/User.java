package co.edu.uniquindio.eps_uq.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class User {

    private String name;
    @EqualsAndHashCode.Include
    @Id
    private String id;
    private Integer age;
    private ClinicHistory history;
}
