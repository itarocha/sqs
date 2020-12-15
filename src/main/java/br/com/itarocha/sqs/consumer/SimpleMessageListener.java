package br.com.itarocha.sqs.consumer;

import br.com.itarocha.sqs.model.Evento;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class SimpleMessageListener {

    private final ObjectMapper objectMapper;
    public SimpleMessageListener(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /*
    @SqsListener(value = "${sqs.queue}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void processMessage(@Payload String message) throws JsonProcessingException {
        System.out.println(String.format("Recebido: \"%s\"", message));
    }
     */

    @SqsListener(value = "${sqs.queue}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void processMessage(@Payload Evento evento) throws JsonProcessingException {
        System.out.println("Incoming order: " + evento.getId() + "-" + evento.getMessage());
        System.out.println("Successfully uploaded order to S3");
    }
}