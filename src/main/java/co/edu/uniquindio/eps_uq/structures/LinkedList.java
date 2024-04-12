package co.edu.uniquindio.eps_uq.structures;

import java.util.Comparator;
import java.util.List;

public interface LinkedList<T> extends Iterable<T>{

	void addHead(T element);
	void addTail(T element);
	void add(int index, T element);
	boolean validIndex(int index);
	boolean isEmpty();
	void removeHead();
	void removeTail();
	void remove(int index);
	void remove(T element);
	void sort();
	void print();
	void clean();
	int getIndex(T element);
	T getValue(int index);
	boolean contains(T element);
	void update(T element);
	List<T> toList();

//obtenerValorNodo
//obtenerNodo
//obtenerPosicionNodo
//modificarNodo


}
