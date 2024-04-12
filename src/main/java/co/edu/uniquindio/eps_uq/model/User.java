package co.edu.uniquindio.eps_uq.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User implements Comparable<User> {

    private String name;
    @EqualsAndHashCode.Include
    private String id;
    private Integer age;
    private ClinicHistory history;

    @Override
    public int compareTo(User user) {
        return age.compareTo(user.getAge());
    }
}
