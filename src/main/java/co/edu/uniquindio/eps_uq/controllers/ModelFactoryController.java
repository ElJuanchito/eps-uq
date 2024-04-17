package co.edu.uniquindio.eps_uq.controllers;

import static co.edu.uniquindio.eps_uq.dao.EpsDao.load;
import static co.edu.uniquindio.eps_uq.dao.EpsDao.save;

import java.time.Duration;
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
		this.eps = load();
	}

	public PriorityQueue<Request> getRequests() {
		eps = load();
		return eps.getRequestsQueue();
	}

	public void addRequest(User user, PriorityLevel priorityLevel, Doctor doctor, String detail, Duration duration) {
		Request request = Request.builder().id(UUID.randomUUID().toString()).user(user).priorityLevel(priorityLevel)
				.doctor(doctor).detail(detail).duration(duration).build();
		eps = load();
		eps.getRequestsQueue().print();
		eps.addRequest(request);
		eps.getRequestsQueue().print();
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
		
		eps.setRequestsQueue(aux);
		save(eps);
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
		eps.getAppointments().print();
		return eps.getAppointments().toList();
	}

	public List<User> getUsers(){
		eps = load();
		return eps.getUsersList().toList();
	}

	public void createAppointmentsFromRequests() {
		eps = load();
		eps.getRequestsQueue().print();
		eps.createAppointmentsFromRequests();
		eps.getRequestsQueue().print();
		save(eps);
	}
}
