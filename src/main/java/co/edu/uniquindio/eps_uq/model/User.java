package co.edu.uniquindio.eps_uq.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class User implements Comparable<User> {

    private String name;
    @EqualsAndHashCode.Include
    @Id
    private String id;
    private Integer age;
    private ClinicHistory history;

    @Override
    public int compareTo(User user) {
        return age.compareTo(user.getAge());
    }
}
