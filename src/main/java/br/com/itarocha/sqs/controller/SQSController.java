package br.com.itarocha.sqs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

@RestController
public class SQSController {

    @Autowired
    private QueueMessagingTemplate template;

    @Value("${sqs.queue}")
    private String queueName;

    @GetMapping("/put/{msg}")
    public ResponseEntity<?> putMessageToQueue(@PathVariable("msg")String message){
        template.send(queueName, MessageBuilder.withPayload(message).build());
        return ResponseEntity.ok("Enviado: "+message);
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendOrder(@RequestBody String message){
        template.send(queueName, MessageBuilder.withPayload(message).build());
        return ResponseEntity.ok("Enviado: "+message);
    }
}
