package BasicPublisherAndConsumer;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        var connection = new ConnectionFactory().newConnection();
        var channel = connection.createChannel();

        DeliverCallback deliverCallBack = (consumerTag, delivery) -> {
          var message = new String(delivery.getBody());
            System.out.println("message received: " + message);
        };

        channel.basicConsume("queuq-1", true, deliverCallBack, consumerTag -> {});
    }
}
