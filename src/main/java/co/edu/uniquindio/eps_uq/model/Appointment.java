package co.edu.uniquindio.eps_uq.model;

import java.time.Duration;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded =true)
@AllArgsConstructor
public class Appointment {
	@EqualsAndHashCode.Include
	private String id;
	
	
	private LocalDate date;
	private User user;
	private PriorityLevel priorityLevel;
	private Doctor doctor;
	private String detail;
	private Duration duration;
	
	
}
