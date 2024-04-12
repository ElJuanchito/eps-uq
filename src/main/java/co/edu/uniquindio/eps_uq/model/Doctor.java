package co.edu.uniquindio.eps_uq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Doctor implements Comparable<Doctor>{
	
	
	private Long id;
	private String nombre;

	@Override
	public int compareTo(Doctor doctor) {
		return id.compareTo(doctor.getId());
	}
}
