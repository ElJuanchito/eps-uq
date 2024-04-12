package co.edu.uniquindio.eps_uq.structures;

import co.edu.uniquindio.eps_uq.model.Doctor;
import co.edu.uniquindio.eps_uq.model.PriorityLevel;
import co.edu.uniquindio.eps_uq.model.Request;
import co.edu.uniquindio.eps_uq.model.User;

public class Prueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PriorityQueue<Request> solicitudes= new PriorityQueue<Request>();
		User usuario1= new User("Santiago", "01", 32, null);
		Doctor doctor1= new Doctor("01", "Juan");
		Request solicitud1 = new Request("09", usuario1, PriorityLevel.MEDIUM, doctor1, "General", null);
		Request solicitud2 = new Request("08", usuario1, PriorityLevel.HIGH, doctor1, "General", null);
		Request solicitud3 = new Request("11", usuario1, PriorityLevel.LOW, doctor1, "General", null);
		Request solicitud4 = new Request("12", usuario1, PriorityLevel.LOW, doctor1, "General", null);
		
		solicitudes.Enqueue(solicitud1);
		solicitudes.Enqueue(solicitud2);
		solicitudes.Enqueue(solicitud3);
		solicitudes.Enqueue(solicitud4);
		
		
		while(!solicitudes.isEmpty()) {
			System.out.println(solicitudes.Dequeue().getId());
		}
	}

}
