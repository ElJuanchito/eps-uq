package co.edu.uniquindio.eps_uq.model;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;

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
	private LocalDate date;
	private User user;
	private PriorityLevel priorityLevel;
	private Doctor doctor;
	private String detail;
	private Duration duration;

	@Override
	public int compareTo(Appointment appointment) {
		return date.compareTo(appointment.getDate());
	}
}
