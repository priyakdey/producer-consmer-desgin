package com.example.model;

import java.util.LinkedList;

public class MessageQueue<E> {
    private LinkedList<E> queue;
    private int capacity = 10;
    private Object isFull = new Object();
    private Object isEmpty = new Object();


    /**
     * default no-args constructor
     */
    public MessageQueue() {
        queue = new LinkedList<>();
    }

    /**
     * constructor with capacity as argument
     * @param capacity
     */
    public MessageQueue(int capacity) {
        this();
        this.capacity = capacity;
    }

    /**
     * method to add an element to the tail of the queue
     * @return void
     * @param e
     */
    public void addElement(E e) {
        synchronized (isFull) {
            try{
                if (queue.size() == capacity) {
                    isFull.wait();
                }

                synchronized (isEmpty) {
                    queue.addLast(e);
                    isEmpty.notifyAll();
                }
            } catch (InterruptedException ex) {
                System.out.println("MessageQueue.addElement: " + ex.getMessage());
            }
        }
    }

    /**
     * method to remove an element from the head of the queue
     * @return E
     */
    public E removeElement() {
        synchronized (isEmpty) {
            try{
                if (queue.size() == 0) {
                    isEmpty.wait();
                }

                synchronized (isFull) {
                    E e = queue.removeFirst();
                    isFull.notifyAll();
                    return e;
                }
            } catch (InterruptedException ex) {
                System.out.println("MessageQueue.addElement: " + ex.getMessage());
            }
        }
        return null; // Logically unreachable statement
    }


}
