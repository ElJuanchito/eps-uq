package co.edu.uniquindio.eps_uq.model;

import java.time.LocalDate;
import java.util.UUID;

import co.edu.uniquindio.eps_uq.structures.LinkedList;
import co.edu.uniquindio.eps_uq.structures.PriorityQueue;
import co.edu.uniquindio.eps_uq.structures.SimpleList;
import lombok.Getter;

@Getter
public class Eps {

    private final LinkedList<User> usersList;
    private final LinkedList<Doctor> doctorsList;
    private final LinkedList<Appointment> appointments;
    private final PriorityQueue<Request> requestsQueue;

    public Eps() {
        usersList = new SimpleList<>();
        doctorsList = new SimpleList<>();
        appointments = new SimpleList<>();
        requestsQueue = new PriorityQueue<>();
    }

    public void addUser(User user) {
        if(usersList.contains(user)) throw new IllegalArgumentException("User already exists");
        usersList.addTail(user);
    }
    public void deleteUser(User user) {
        if(!usersList.contains(user)) throw new IllegalArgumentException("User does not exist");
        usersList.remove(user);
    }

    public void addDoctor(Doctor doctor) {
        if(doctorsList.contains(doctor)) throw new IllegalArgumentException("Doctor already exists");
        doctorsList.addTail(doctor);
    }

    public void deleteDoctor(Doctor doctor) {
        if(!doctorsList.contains(doctor)) throw new IllegalArgumentException("Doctor does not exist");
        doctorsList.remove(doctor);
    }

    public void addRequest(Request request){
        requestsQueue.Enqueue(request);
    }

    public Request processRequest(Request request){
        return requestsQueue.Dequeue();
    }

    public Appointment processAppointment(LocalDate date) {
        Appointment appointment = mapRequestToAppointment(requestsQueue.Dequeue());
        appointment.setDate(date);
        appointments.addTail(appointment);
        appointments.sort();
        return appointment;
    }

    private Appointment mapRequestToAppointment(Request request) {
        return Appointment.builder()
                .id(UUID.randomUUID().toString())
                .user(request.getUser())
                .priorityLevel(request.getPriorityLevel())
                .doctor(request.getDoctor())
                .detail(request.getDetail())
                .duration(request.getDuration())
                .build();
    }
}
