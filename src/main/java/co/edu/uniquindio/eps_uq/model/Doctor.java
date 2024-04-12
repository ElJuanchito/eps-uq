package co.edu.uniquindio.eps_uq.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Doctor implements Comparable<Doctor>{
	
	
	private String id;
	private String nombre;

	@Override
	public int compareTo(Doctor doctor) {
		return id.compareTo(doctor.getId());
	}
}
