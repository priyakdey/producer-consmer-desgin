package com.example.tasks;

import com.example.model.MessageQueue;

import java.security.SecureRandom;
import java.util.Random;

public class ProducerTask<E> implements Runnable {
    private MessageQueue<E> queue;

    public ProducerTask(MessageQueue<E> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while(true) {
            try{
                String message = generateRandomString();
                queue.addElement((E) message);
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                System.out.println("ProducerTask.run: " + e.getMessage());
            }
        }
    }

    private String generateRandomString() {
        final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final Random random = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            stringBuilder.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
        }

        return stringBuilder.toString();
    }
}
