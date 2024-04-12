package co.edu.uniquindio.eps_uq.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@Builder
public class User implements Comparable<User>, Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
