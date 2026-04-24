package consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SpringRabbitListener {
    @RabbitListener(queues = "hello.queue1")
    public void receiveMessage(String message)throws InterruptedException{
        System.out.println("接收到的消息为："+message);
    }
}
