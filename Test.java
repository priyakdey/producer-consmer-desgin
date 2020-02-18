import com.example.model.MessageQueue;
import com.example.tasks.ConsumerTask;
import com.example.tasks.ProducerTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    public static void main(String[] args) {
        MessageQueue<String> queue = new MessageQueue<>();
        ProducerTask<String> producerTask = new ProducerTask<>(queue);
        ConsumerTask<String> consumerTask = new ConsumerTask<>(queue);

        int numberOfCores = Runtime.getRuntime().availableProcessors();

        ExecutorService producerService = Executors.newFixedThreadPool(numberOfCores);
        ExecutorService consumerService = Executors.newFixedThreadPool(numberOfCores);

        // Submit tasks
        producerService.submit(producerTask);
        consumerService.submit(consumerTask);


//        new Thread(producerTask, "producer-1").start();
//        new Thread(consumerTask, "consumer-1").start();

    }
}
