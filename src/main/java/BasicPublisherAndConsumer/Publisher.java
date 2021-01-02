package BasicPublisherAndConsumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class Publisher {

    Logger logger = LoggerFactory.getLogger(Publisher.class);

    public static void main(String[] args) throws IOException, TimeoutException {

        var connection = new ConnectionFactory().newConnection();
        var channel = connection.createChannel();

        // Instantiation just to access publishMessage method.
        var publisher = new Publisher();

        var messages = List.of("message-1", "message-2", "message-3", "message-4");

        publisher.publishMessage(channel, messages);

        channel.close();
        connection.close();
    }

    /**
     * Publishes a list of messages to the queue specified.
     * For now, exchange is not used.
     *
     * @param channel  The channel created using connection.
     * @param messages The list of messages.
     */
    private void publishMessage(Channel channel, List<String> messages) {
        messages
                .forEach(message -> {
                    try {
                        channel.basicPublish("", "queuq-1", null, message.getBytes());
                    } catch (IOException e) {
                        logger.error("error occurred {}", e.getMessage());
                    }
                });
    }
}
