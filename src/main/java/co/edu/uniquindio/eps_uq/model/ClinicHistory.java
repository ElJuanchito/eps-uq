package co.edu.uniquindio.eps_uq.model;

import co.edu.uniquindio.eps_uq.structures.LinkedList;
import co.edu.uniquindio.eps_uq.structures.SimpleList;
import lombok.Data;

import java.io.Serializable;

@Data
public class ClinicHistory implements Serializable {
    private LinkedList<String> history;

    /**
     * Constructor
     */
    public ClinicHistory(){
        history = new SimpleList<>();
    }

    /**
     * Update clinic history with a new appointment
     * @param appointment is a update for history clinic
     */
    public void update(String appointment){
        history.addTail(appointment);
    }

    /**
     * return clinic history
     * @return
     */
    public String get(){
        return history.toString();
    }
}
