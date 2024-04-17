package co.edu.uniquindio.eps_uq.model;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@Builder
@NonNull
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TimeInterval implements Comparable<TimeInterval>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EqualsAndHashCode.Include
	private LocalDateTime time;
	@EqualsAndHashCode.Include
	private Duration duration;

	public boolean intersects(TimeInterval o) {
		return !(time.isAfter(o.time.plus(o.duration)) || time.plus(duration).isBefore(o.time));
	}

	@Override
	public int compareTo(TimeInterval o) {
		int timeComp = time.compareTo(o.time);
		return timeComp != 0 ? timeComp : time.plus(duration).compareTo(o.time.plus(o.duration));
	}

	public boolean isBetween(LocalTime workdayStart, LocalTime workdayEnd) {
		if (time.getDayOfWeek() != time.plus(duration).getDayOfWeek())
			return false;
		return !time.toLocalTime().isBefore(workdayStart) && !time.plus(duration).toLocalTime().isAfter(workdayEnd);
	}

	public boolean withAnyDayOf(DayOfWeek... daysOfWork) {
		if (Objects.isNull(daysOfWork))
			return false;
		for (DayOfWeek dayOfWeek : daysOfWork)
			if (time.getDayOfWeek() == dayOfWeek)
				return true;
		return false;
	}

	public TimeInterval plusMinutes(int i) {
		return TimeInterval.builder().duration(duration).time(time.plusMinutes(i)).build();
	}
}
