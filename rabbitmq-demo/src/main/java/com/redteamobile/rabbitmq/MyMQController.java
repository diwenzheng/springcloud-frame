package com.redteamobile.rabbitmq;


import com.redteamobile.rabbitmq.common.MyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Created by toutou on 2019/1/1.
 */
@RestController
public class MyMQController {
    @Autowired
    MyProducer myProducers;

    @GetMapping("/mq/producer")
    public String myProducer(String content){
        MyModel model = new MyModel();
        model.setId(UUID.randomUUID());
        model.setInfo(content);
        myProducers.sendMsg(model);
        return "已发送：" + content;
    }
}