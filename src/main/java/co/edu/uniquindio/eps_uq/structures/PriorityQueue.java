package co.edu.uniquindio.eps_uq.structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PriorityQueue<T extends Comparable<T>> implements Iterable<T>{

    private Node head;
    private int size = 0;

    private class Node {
        T value;
        Node next;

        Node(T element){
            this.value = element;
        }
    }

    public void Enqueue(T element) {
        var node = new Node(element);
        if(head == null || element.compareTo(head.value) > 0){
            node.next = head;
            head = node;
            size ++;
	        return;
        }
        Node current = head;
        while(current.next != null && element.compareTo(current.next.value) < 0){
            current = current.next;
        }
        node.next = current.next;
        current.next = node;
        size ++;
    }

    public T Dequeue() {
        if(head == null) throw new NoSuchElementException("Queue is empty");
        T request = head.value;
        head = head.next;
        size --;
        return request;
    }

    public boolean isEmpty(){
        return head == null;
    }

    @Override
	public Iterator<T> iterator(){
		return new PriorityQueueIterator(head, size);
	}
    
    private class PriorityQueueIterator implements Iterator<T>{
    	private Node current;
    	private int i;
		private final int size;
    	
    	private PriorityQueueIterator (Node head, int size) {
    		this.current= head;
    		this.size = size;
    	}
		@Override
		public boolean hasNext() {
			return i<size;
		}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException("No hay mas elementos a iterar");
			}
			T value= current.value;
			current=current.next;
			i++;
			return value;
		}
    
    }
}
