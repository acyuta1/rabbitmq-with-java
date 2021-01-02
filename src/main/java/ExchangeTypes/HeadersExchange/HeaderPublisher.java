package ExchangeTypes.HeadersExchange;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

/**
 * 4. HEader-Exchange:
 *         Every message coming from a producer, has a properties (key-value mapping).
 *         Ex:
 *              header = {
 *                  "item1 = mobile",
 *                  "item2 = television"
 *              }
 *
 *         We will create queues, with specific headers comprising of properties.
 *         Queue1, header = {
 *             "x-match = any",
 *             "item1 = tv",
 *             "item2 = television"
 *         }
 *         Queue2, header = {
 *             "x-match = all",
 *             "item1 = mobile",
 *             "item2 = tv"
 *         }
 *
 *         x-match any - performs "OR". Either the message must have a property of type item1 = mobile or item2 = television.
 *         x-match all - performs "AND". The message must have all the properties matching that of the queue.
 *
 */
public class HeaderPublisher {

    public static void main(String[] args) throws IOException, TimeoutException {
        var connection = new ConnectionFactory().newConnection();
        var channel = connection.createChannel();

        var headers = new HashMap<String, Object>();
        headers.put("item1","tv");
        headers.put("item2","mob");

        AMQP.BasicProperties br = new AMQP.BasicProperties();
        br.builder().headers(headers).build();

        // Basic properties object is sent instead of a key.
        channel.basicPublish("Header-exchange","", br, "header-exchange".getBytes());

        channel.close();
        connection.close();
    }
}
