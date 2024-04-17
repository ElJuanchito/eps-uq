package co.edu.uniquindio.eps_uq.model;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.TreeSet;
import java.util.UUID;

import co.edu.uniquindio.eps_uq.structures.LinkedList;
import co.edu.uniquindio.eps_uq.structures.PriorityQueue;
import co.edu.uniquindio.eps_uq.structures.SimpleList;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Eps implements Serializable {

    /**
	 * 
	 */
	private  static final long serialVersionUID = 1L;
	private  LinkedList<User> usersList;
    private  LinkedList<Doctor> doctorsList;
	private  LinkedList<Appointment> appointments;
    private  TreeSet<TimeInterval> schedule;
    private  PriorityQueue<Request> requestsQueue;
    private  LocalTime workdayStart, workdayEnd;
    private  DayOfWeek[] daysOfWork;
	private static final DayOfWeek[] monToFriday = { DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
			DayOfWeek.THURSDAY, DayOfWeek.FRIDAY }; 

    public Eps() {
        usersList = new SimpleList<>();
        doctorsList = new SimpleList<>();
        appointments = new SimpleList<>();
        schedule = new TreeSet<>();
        requestsQueue = new PriorityQueue<>();
        workdayStart = LocalTime.of(8, 0);
		workdayEnd = LocalTime.of(18, 0);
		daysOfWork = monToFriday;
    }

    public void addUser(User user) {
        if(usersList.contains(user)) throw new IllegalArgumentException("User already exists");
        usersList.addTail(user);
    }
    public void deleteUser(User user) {
        if(!usersList.contains(user)) throw new IllegalArgumentException("User does not exist");
        usersList.remove(user);
    }

    public void updateUser(User user){
        if(!usersList.contains(user)) throw new IllegalArgumentException();
        usersList.update(user);
    }

    public void addDoctor(Doctor doctor) {
        if(doctorsList.contains(doctor)) throw new IllegalArgumentException("Doctor already exists");
        doctorsList.addTail(doctor);
    }
    
    public void addAppointment(Appointment appointment) {
    	if(appointments.contains(appointment)) throw new IllegalArgumentException("Appointment already exists");
		appointments.addTail(appointment);
    }

    public void deleteDoctor(Doctor doctor) {
        if(!doctorsList.contains(doctor)) throw new IllegalArgumentException("Doctor does not exist");
        doctorsList.remove(doctor);
    }

    public void addRequest(Request request){
        requestsQueue.enqueue(request);
    }

    public Request processRequest(Request request){
        return requestsQueue.dequeue();
    }

	private Appointment mapRequestToAppointment(Request request, TimeInterval interval) {
        return Appointment.builder()
                .id(UUID.randomUUID().toString())
                .user(request.getUser())
                .priorityLevel(request.getPriorityLevel())
                .doctor(request.getDoctor())
                .detail(request.getDetail())
                .timeInterval(interval)
                .build();
    }

	public void createAppointmentsFromRequests() {
		if (daysOfWork == null || daysOfWork.length == 0)
			throw new RuntimeException("No hay días de trabajo");
		if (workdayStart == null || workdayEnd == null || workdayStart.isAfter(workdayEnd))
			throw new RuntimeException("Arregla las fechas de trabajo primero");
		LocalDateTime initialTime = LocalDateTime.now().plusMinutes(1).withSecond(0).withNano(0);
		if (initialTime.getMinute() < 30) initialTime = initialTime.withMinute(30);
		else initialTime = initialTime.plusHours(1).withMinute(0);
		
		int failed = 0;
		while (requestsQueue.size() - failed > 0) {
			Request next = requestsQueue.seek();
			try {
				createAppointment(initialTime, next);
				requestsQueue.dequeue();
			} catch (RuntimeException e) {
				failed++;
			}
		}
	}

	private void createAppointment(LocalDateTime initialTime, Request next) {
		TimeInterval interval = TimeInterval.builder().time(initialTime).duration(next.getDuration()).build();
		updateDayTillWorkDay(interval);
		
		while (!canSchedule(interval)) {
			add30Minutes(interval);
			updateDayTillWorkDay(interval);
		}
		agregarCita(next, interval);
	}

	private void agregarCita(Request next, TimeInterval interval) {
		System.out.println("Eps.agregarCita()");
		appointments.print();
		appointments.addTail(mapRequestToAppointment(next, interval));
		appointments.print();
		schedule.add(interval);
	}

	private void add30Minutes(TimeInterval interval) {
		interval.setTime(interval.getTime().plusMinutes(30));
	}

	private void updateDayTillWorkDay(TimeInterval interval) {
		while (!interval.withAnyDayOf(daysOfWork))
			interval.setTime(interval.getTime().withHour(workdayStart.getHour())
					.withMinute(workdayStart.getMinute()).plusDays(1));
	}

	private boolean canSchedule(TimeInterval interval) {
		if (!interval.isBetween(workdayStart, workdayEnd))
			return false;
		// aprovecha ventajas del TreeSet
	    TimeInterval higher = schedule.higher(interval);
		TimeInterval lower = schedule.lower(interval);
		return (lower == null || !interval.intersects(lower))
				&& (higher == null || !interval.intersects(higher));
	}

}
