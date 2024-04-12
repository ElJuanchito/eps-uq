package co.edu.uniquindio.eps_uq.model;

import co.edu.uniquindio.eps_uq.structures.PriorityQueue;
import listas.LinkedList;
import listas.SimpleList;

public class Eps {

    private LinkedList<User> usersList;
    private LinkedList<Doctor> doctorsList;
    private LinkedList<Appointment> appointments;
    private PriorityQueue<Request> requestsQueue;

    public Eps() {
        usersList = new SimpleList<>();
        doctorsList = new SimpleList<>();
        appointments = new SimpleList<>();
        requestsQueue = new PriorityQueue<>();
    }

    public void addUser(User user) {
        //TODO
    }
    public void deleteUser(Long id) {
        //TODO
    }

}
