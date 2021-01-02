package ExchangeTypes.DirectExchange;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class DirectConsumer {
    public static void main(String[] args) throws IOException, TimeoutException {

        var connection = new ConnectionFactory().newConnection();
        var channel = connection.createChannel();

        // Consumer only deals with queue and not the exchange, hence code will be same.
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
          var message = new String(delivery.getBody());
            System.out.println(message);
        };

        channel.basicConsume("Mobile", true, deliverCallback, (consumerTag) -> {});
        channel.basicConsume("TV", true, deliverCallback, (consumerTag) -> {});
        channel.basicConsume("AC", true, deliverCallback, (consumerTag) -> {});
    }

}
