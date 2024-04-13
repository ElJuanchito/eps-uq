package co.edu.uniquindio.eps_uq.controllers;

import static co.edu.uniquindio.eps_uq.dao.EpsDao.load;
import static co.edu.uniquindio.eps_uq.dao.EpsDao.save;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
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
	
	public List <Request> getRequestList(){
		eps= load();
		PriorityQueue<Request> requests= eps.getRequestsQueue();
		PriorityQueue<Request> aux = new PriorityQueue<Request>();
		List<Request> lista = new ArrayList<Request>();
		while (!requests.isEmpty()) {
			Request solicitud= requests.dequeue();
			lista.add(solicitud);
			aux.enqueue(solicitud);
		}
		
		
		return lista;
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
		eps.getUsersList().print();
	}

	public void removeUser(User user){
		eps = load();
		eps.deleteUser(user);
		save(eps);
		eps.getUsersList().print();
	}

	public void updateUser(User user) {
		eps = load();
		eps.updateUser(user);
		save(eps);
		eps.getUsersList().print();
	}
	
	public List<Appointment> getAppointments(){
		eps= load();
		save(eps);
		return eps.getAppointments().toList();
	}

	public List<User> getUsers(){
		eps = load();
		save(eps);
		eps.getUsersList().print();
		System.out.println(eps.getUsersList().toList());
		return eps.getUsersList().toList();
	}
}
