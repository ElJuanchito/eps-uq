package co.edu.uniquindio.eps_uq.model;

import java.io.Serializable;
import java.time.Duration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded =true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Request implements Comparable<Request>, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
