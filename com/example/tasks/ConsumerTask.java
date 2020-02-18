package com.example.tasks;

import com.example.model.MessageQueue;

public class ConsumerTask<E> implements Runnable{
    private MessageQueue<E> queue;

    public ConsumerTask(MessageQueue<E> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String message = (String) queue.removeElement();
                System.out.println("Message received: " + message.toLowerCase()); // Processing of data !!!
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("ConsumerTask.run: " + e.getMessage());
            }
        }
    }
}
