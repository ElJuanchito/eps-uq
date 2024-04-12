package co.edu.uniquindio.eps_uq.structures;

import java.util.NoSuchElementException;

public class ProrityQueue<T extends Comparable<T>> {

    private Node<T> head;
    private int size = 0;

    private class Node<T>{
        T value;
        Node<T> next;

        Node(T element){
            this.value = element;
        }
    }

    public void Enqueue(T element) {
        var node = new Node<T>(element);
        if(head == null || element.compareTo(head.value) > 0){
            node.next = node;
            head = node;
        }
        else {
            Node<T> current = head;
            while(current.next != null && element.compareTo(current.next.value) <= 0){
                current = current.next;
            }
            node.next = current.next;
            current.next = node;
        }
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

    public T peek(){
        return head.value;
    }
}
