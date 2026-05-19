import com.google.common.util.concurrent.ListenableFuture;
import com.javaweb.springboot_web.SpringbootWebApplication;
import com.javaweb.springboot_web.pojo.Note;
import consumer.SpringRabbitListener;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;

@SpringBootTest(classes = {SpringbootWebApplication.class, SpringRabbitListener.class})
@EnableRabbit
public class AmqpTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

//    @Test
//    public void sendMessage() {
//
//        CorrelationData cd = new CorrelationData();
//
//        CompletableFuture<CorrelationData.Confirm> future = cd.getFuture();
//
//        future.whenComplete((result,ex) -> {
//            if(ex != null){
//                System.out.println("消息发送异常: " + ex.getMessage());
//            }else if(result.isAck()){
//                System.out.println("消息发送成功");
//            }else {
//                System.out.println("消息发送失败: " + result.getReason());
//            }
//        });
//
//        rabbitTemplate.convertAndSend("text.direct","d","hello",cd);
//    }

//    @Test
//    public void testDelayMessage(){
//        String message = "hello delay";
//        rabbitTemplate.convertAndSend("delay.direct","delay",message);
//    }

}
