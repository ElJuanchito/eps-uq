
package co.edu.uniquindio.eps_uq.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import co.edu.uniquindio.eps_uq.model.Eps;

public class EpsDao {
	private static final String ROUTE = "src/main/resources/data/eps.dat";

	public static Eps load() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ROUTE))) {
			return (Eps) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			Eps eps = new Eps();
			save(eps);
			return eps;
		}
	}

	public static void save(Eps eps) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ROUTE))) {
			oos.writeObject(eps);
		} catch (Exception e) {
		}

	}

}
