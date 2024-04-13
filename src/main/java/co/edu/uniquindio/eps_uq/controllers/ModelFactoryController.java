package co.edu.uniquindio.eps_uq.controllers;

import static co.edu.uniquindio.eps_uq.dao.EpsDao.load;
import static co.edu.uniquindio.eps_uq.dao.EpsDao.save;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

import co.edu.uniquindio.eps_uq.model.Appointment;
import co.edu.uniquindio.eps_uq.model.Doctor;
import co.edu.uniquindio.eps_uq.model.Eps;
import co.edu.uniquindio.eps_uq.model.PriorityLevel;
import co.edu.uniquindio.eps_uq.model.Request;
import co.edu.uniquindio.eps_uq.model.User;
import co.edu.uniquindio.eps_uq.structures.LinkedList;
import co.edu.uniquindio.eps_uq.structures.PriorityQueue;
import javafx.util.Callback;

public class ModelFactoryController {
	private static ModelFactoryController instance;
	private Eps eps;

	public static ModelFactoryController getInstance() {
		if (instance == null) {
			instance = new ModelFactoryController();
		}
		return instance;
	}

	private ModelFactoryController() {
		// chambonada
		this.eps = load();
	}

	public PriorityQueue<Request> getRequests() {
		eps = load();
		return eps.getRequestsQueue();
	}

	public void addRequest(User user, PriorityLevel priorityLevel, Doctor doctor, String detail) {
		Request request = Request.builder().id(UUID.randomUUID().toString()).user(user).priorityLevel(priorityLevel)
				.doctor(doctor).detail(detail).build();
		eps = load();
		eps.addRequest(request);
		save(eps);

	}

	public void addDoctor(String id, String nombre) {
		Doctor doctor = Doctor.builder().id(id).nombre(nombre).build();
		eps = load();
		eps.addDoctor(doctor);
		save(eps);
	}

	public LinkedList<Doctor> getDoctors() {
		load();
		return eps.getDoctorsList();
	}

	public void addUser(String id, String name, Integer age) {
		User user = User.builder()
				.id(id)
				.age(age)
				.name(name)
				.build();

		eps = load();
		eps.addUser(user);
		save(eps);
	}

	public void removeUser(User user){
		eps = load();
		eps.deleteUser(user);
		save(eps);
	}

	public void updateUser(User user) {
		eps = load();
		eps.updateUser(user);
		save(eps);
	}
	
	public List<Appointment> getAppointments(){
		eps= load();
		eps.addAppointment(Appointment.builder().id("093").build());
		save(eps);
		return eps.getAppointments().toList();
	}

	public List<User> getUsers(){
		eps = load();
		eps.addUser(User.builder()
				.id("100")
				.age(50)
				.name("Arnolfo")
				.build());
		eps.addUser(User.builder()
				.id("200")
				.age(20)
				.name("Veregildo")
				.build());
		eps.addUser(User.builder()
				.id("300")
				.age(10)
				.name("Adonai")
				.build());
		save(eps);
		return eps.getUsersList().toList();
	}
}
