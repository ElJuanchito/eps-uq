package co.edu.uniquindio.eps_uq.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded =true)
@AllArgsConstructor
@Builder
public class Appointment implements Comparable<Appointment>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EqualsAndHashCode.Include
	private String id;
	private TimeInterval timeInterval;
	private User user;
	private PriorityLevel priorityLevel;
	private Doctor doctor;
	private String detail;

	@Override
	public int compareTo(Appointment appointment) {
		return timeInterval.compareTo(appointment.timeInterval);
	}
}
