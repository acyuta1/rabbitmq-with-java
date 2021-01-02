package ExchangeTypes.DirectExchange;

import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 1. Direct-Exchange:
 *      When a message arrives from producer to direct-exchange, it will be redirected to
 *      appropriate queue based on the "key" passed.
 */
public class DirectPublisher {

    public static void main(String[] args) throws IOException, TimeoutException {
        var connection = new ConnectionFactory().newConnection();
        var channel = connection.createChannel();

        // Three queues [Mobile, TV, AC] are bound to exchange "Direct-Exchange" with keys ["mobile", "tv", "ac"] respectively.
        channel.basicPublish("Direct-Exchange","mobile",null,"mobile message 1".getBytes());
        channel.basicPublish("Direct-Exchange","tv",null,"tv message 1".getBytes());
        channel.basicPublish("Direct-Exchange","ac",null,"ac message 1".getBytes());

        channel.close();
        connection.close();
    }
}
