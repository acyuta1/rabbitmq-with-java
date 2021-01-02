package ExchangeTypes.FanoutExchange;

import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 2. Fanout-Exchange:
 *      When a message arrives from producer to fanout, it will be sent to each and every
 *      queue that is bound to it.
 */
public class FanoutPublisher {
    public static void main(String[] args) throws IOException, TimeoutException {
        var connection = new ConnectionFactory().newConnection();
        var channel = connection.createChannel();

        /*
        Message will be redirected to all queues bound to the exchange "Fanout-Exchange".
         */
        channel.basicPublish("Fanout-Exchange","",null, "message from fanout".getBytes());

        channel.close();
        connection.close();
    }
}
