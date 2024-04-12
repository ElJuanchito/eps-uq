package co.edu.uniquindio.eps_uq.controllers;

import static co.edu.uniquindio.eps_uq.dao.EpsDao.load;
import static co.edu.uniquindio.eps_uq.dao.EpsDao.save;

import java.time.Duration;
import java.util.UUID;

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
		PriorityQueue<Request> queue = eps.getRequestsQueue();
		queue.enqueue(Request.builder().id(UUID.randomUUID().toString())
				.user(User.builder().id("Juan").name("Juan LOW 1").age(10).build()).priorityLevel(PriorityLevel.LOW)
				.duration(Duration.ofMinutes(20)).doctor(Doctor.builder().id("1234l").nombre("Pepe").build())
				.detail("Ninguna").build());
		queue.enqueue(Request.builder().id(UUID.randomUUID().toString())
				.user(User.builder().id("Juan").name("Juan HIGH 1").age(10).build()).priorityLevel(PriorityLevel.HIGH)
				.duration(Duration.ofHours(1)).doctor(Doctor.builder().id("1234l").nombre("Pepe").build())
				.detail("Ninguna").build());
		queue.enqueue(Request.builder().id(UUID.randomUUID().toString())
				.user(User.builder().id("Juan").name("Juan MID 1").age(10).build()).priorityLevel(PriorityLevel.MEDIUM)
				.duration(Duration.ofMinutes(30)).doctor(Doctor.builder().id("1234l").nombre("Pepe").build())
				.detail("Ninguna").build());
		queue.enqueue(Request.builder().id(UUID.randomUUID().toString())
				.user(User.builder().id("Juan").name("Juan LOW 2").age(10).build()).priorityLevel(PriorityLevel.LOW)
				.doctor(Doctor.builder().id("1234l").nombre("Pepe").build()).detail("Ninguna")
				.duration(Duration.ofHours(1).plusMinutes(30)).build());
		save(eps);
		return queue;
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

}
