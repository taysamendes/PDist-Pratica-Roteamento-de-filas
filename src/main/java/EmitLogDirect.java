
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class EmitLogDirect {
    private static String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);

        String text = "Taysa Samara Mendes Pinheiro";
        String tipo = "string";

        try (
                Connection connection = factory.newConnection();
                Channel channel = connection.createChannel();
        ) {
            channel.exchangeDeclare(EXCHANGE_NAME, "direct");
            channel.basicPublish(EXCHANGE_NAME, tipo, null, text.getBytes("UTF-8"));
            System.out.println(String.format("Send '%s' - %s.", text, tipo));
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }
}