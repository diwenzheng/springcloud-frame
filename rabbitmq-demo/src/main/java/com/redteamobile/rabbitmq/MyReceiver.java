package com.redteamobile.rabbitmq;

import com.redteamobile.rabbitmq.common.MyModel;
import com.redteamobile.rabbitmq.common.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by toutou on 2019/1/1.
 */
@Component
@RabbitListener(queues = RabbitConfig.QUEUE_A)
public class MyReceiver {
    @RabbitHandler
    public void process(MyModel model) {
        System.out.println("接收处理队列A当中的消息： " + model.getInfo());
    }
}