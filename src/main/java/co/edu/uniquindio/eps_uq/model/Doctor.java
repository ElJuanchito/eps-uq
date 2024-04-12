package co.edu.uniquindio.eps_uq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Doctor implements Comparable<Doctor>{
	
	@EqualsAndHashCode.Include	
	private String id;
	private String nombre;

	@Override
	public int compareTo(Doctor doctor) {
		return id.compareTo(doctor.getId());
	}
}
