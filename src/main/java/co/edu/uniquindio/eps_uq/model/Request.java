package co.edu.uniquindio.eps_uq.model;

import java.time.Duration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded =true)
@AllArgsConstructor
public class Request implements Comparable<Request>{
	
	@EqualsAndHashCode.Include
	private String id;
	
	
	private User user;
	private PriorityLevel priorityLevel;
	private Doctor doctor;
	private String detail;
	private Duration duration;
	@Override
	public int compareTo(Request o) {
		return this.priorityLevel.getValue()-o.priorityLevel.getValue();
	}
	

}
