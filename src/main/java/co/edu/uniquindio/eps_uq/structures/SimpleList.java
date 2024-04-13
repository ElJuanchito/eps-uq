package co.edu.uniquindio.eps_uq.structures;

import java.io.Serializable;
import java.util.*;

public class SimpleList<T extends Comparable<T>> implements LinkedList<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Node<T> head;
	private int size;

	public SimpleList(){
		size = 0;
	}

	public SimpleList(Node<T> head){this.head = head; this.size = 0;}

	private class Node<T> implements Serializable {
		T element;
		Node<T> next;

		Node(T element) {this.element = element;}
	}

	private class SimpleListIterator implements Iterator<T>{

		private Node<T> node;

		SimpleListIterator(Node<T> node){this.node = node;}

		@Override
		public boolean hasNext() {
			return node != null;
		}

		@Override
		public T next() {
			if(!hasNext()) throw new NoSuchElementException();
			T value = node.element;
			node = node.next;
			return value;
		}

	}

	@Override
	public Iterator<T> iterator() {
		return new SimpleListIterator(head);
	}

	@Override
	public void addHead(T element) {
		var node = new Node<>(element);
		if (!isEmpty()) node.next = head;
		head = node;
		size++;
	}

	@Override
	public void addTail(T element) {
		var node = new Node<>(element);
		if(isEmpty()) head = node;
		else{
			var current = head;
			while(current.next != null) current = current.next;
			current.next = node;
		}
		size++;
	}

	@Override
	public void add(int index, T element) {
		if(!validIndex(index)) throw new IndexOutOfBoundsException();
		var node = new Node<>(element);
		if(isEmpty() || index == 0){
			node.next =head;
			head = node;
		}
		else{
			int currentPos = 0;
			var current = head;
			while(currentPos != index-1){current = current.next; currentPos++;}
			node.next = current.next;
			current.next = node;
		}
		size++;
	}

	@Override
	public boolean validIndex(int index) {
		return size > index;
	}

	@Override
	public boolean isEmpty() {
		return head == null;
	}

	@Override
	public void removeHead() {
		if(isEmpty()) throw new NoSuchElementException();
		head = head.next;
		size--;
	}

	@Override
	public void removeTail() {
		if(isEmpty()) throw new NoSuchElementException();
		if(head.next == null) head = null;
		else{
			var current = head;
			Node<T> previous = null;
			while(current.next != null){
				previous = current;
				current=current.next;
			}
			previous.next = null;
		}
		size--;
	}

	@Override
	public void remove(int index) {
		if(isEmpty()) throw new NoSuchElementException();
		if(!validIndex(index)) throw new IndexOutOfBoundsException();
		if(index == 0) head = null;
		else{
			int currentPos = 0;
			var current = head;
			while(currentPos != index-1) {current = current.next; currentPos ++;}
			current.next = current.next.next;
		}
		size--;
	}

	@Override
	public void remove(T element) {
		if(isEmpty()) throw new NoSuchElementException();
		if(head.element.equals(element)) head = head.next;
		else{
			var current = head;
			Node<T> previous = null;
			while (!current.element.equals(element)) {
				if(current.next == null) throw new NoSuchElementException();
				previous=current;
				current=current.next;
			}
			previous.next = current.next;
		}
		size--;
	}

	@Override
	public void sort() {
		if (isEmpty() || size == 1) return;
		head = quickSort(head);
	}

	private Node<T> quickSort(Node<T> node) {
		if (node == null || node.next == null) return node;

		Node<T> pivot = node;
		Node<T> lessHead = null;
		Node<T> greaterHead = null;
		Node<T> equalHead = pivot;
		Node<T> current = node.next;
		Node<T> next = null;

		while (current != null) {
			next = current.next;
			if (current.element.compareTo(pivot.element) < 0) {
				current.next = lessHead;
				lessHead = current;
			} else if (current.element.compareTo(pivot.element) > 0) {
				current.next = greaterHead;
				greaterHead = current;
			} else {
				current.next = equalHead;
				equalHead = current;
			}
			current = next;
		}

		// Recursivamente ordenar las sublistas
		lessHead = quickSort(lessHead);
		greaterHead = quickSort(greaterHead);

		// Unir las sublistas ordenadas
		if (lessHead != null) {
			Node<T> temp = lessHead;
			while (temp.next != null) {
				temp = temp.next;
			}
			temp.next = equalHead;
		} else {
			lessHead = equalHead;
		}
		equalHead.next = greaterHead;

		return lessHead;
	}

	@Override
	public void print() {
		if (isEmpty()) {
			System.out.println("La lista está vacía");
			return;
		}

		Node<T> current = head;
		while (current != null) {
			System.out.print(current.element);
			if (current.next != null) {
				System.out.print(" -> ");
			}
			current = current.next;
		}
		System.out.println();
	}


	@Override
	public void clean() {
		head = null;
		size = 0;
	}

	@Override
	public int getIndex(T element) {
		if(isEmpty()) throw new NoSuchElementException("Is empty");
		if(!contains(element)) return -1;
		int index = 0;
		var node = head;
		while(!element.equals(node.element) && node.next != null){
			node = node.next;
			index++;
		}
		return index;
	}

	@Override
	public T getValue(int index) {
		if(isEmpty()) throw new NoSuchElementException("Is empty");
		if(!validIndex(index)) throw new IndexOutOfBoundsException("Index out of bounds");
		var node = head;
		for(int i = 0; i < index; i++) node = node.next;
		return node.element;
	}

	@Override
	public boolean contains(T element) {
		if(isEmpty()) return false;
		var node = head;
		while(node != null){
			if(node.element.equals(element)) return true;
			node = node.next;
		}
		return false;
	}

	@Override
	public void update(T element){
		if(isEmpty()) throw new NoSuchElementException();
		var node = head;
		while(node.next != null || !node.element.equals(element)) node = node.next;
		node.element = element;
	}

	@Override
	public List<T> toList(){
		List<T> list = new ArrayList<>();
		Iterator<T> iterator = this.iterator();
		while(iterator.hasNext()){
			T next = iterator.next();
			list.add(next);
		}
		return list;
	}


}
