import com.javaweb.springboot_web.SpringbootWebApplication;
import consumer.SpringRabbitListener;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {SpringbootWebApplication.class, SpringRabbitListener.class})
@EnableRabbit
public class AmqpTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void sendMessage() {
        String queueName = "hello.queue1";
        String message = "hello";
        rabbitTemplate.convertAndSend(queueName, message);
    }


}
