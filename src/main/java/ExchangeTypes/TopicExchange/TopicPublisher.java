package ExchangeTypes.TopicExchange;

import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 2. Topic-Exchange:
 *         Every message coming from a producer, has a key composed of words separated by "."
 *         Ex: ac.tv.message
 *
 *         We will create queues, with specific patterned key.
 *         Queue1, key - "*.tv.*"
 *         Queue2, key - "#.ac"
 *
 *         * - Exactly one word before and after tv. That is, tv must be the middle key.
 *         # - One or more and ends with ac.
 *
 *         Matching above pattern, will result in redirection to the appropriate queue.
 */
public class TopicPublisher {
    public static void main(String[] args) throws IOException, TimeoutException {
        var connection = new ConnectionFactory().newConnection();
        var channel = connection.createChannel();

        /*
        Create queues:
            i. "Mobile", bind it to exchange "Topic-Exchange" with key "*.mobile.*"
            ii. "TV", bind it to exchange "Topic-Exchange" with key "*.tv.*"
            iii. "AC", bind it to exchange "Topic-Exchange" with key "#.ac"
         */

        // Will be redirected to queue iii.
        channel.basicPublish("Topic-Exchange","a.b.ac",null,"topic exchange message - 1".getBytes());

        // Will be redirected to both queue i. and iii.
        channel.basicPublish("Topic-Exchange","tv.mobile.ac",null,"topic exchange message - 2".getBytes());


        channel.close();
        connection.close();
    }
}
