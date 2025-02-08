package com.siva.rebbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitmqConfiguration {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.queue.json.name}")
    private String jsonQueue;

    @Value("${rabbitmq.routing.json.key}")
    private String routingJsonKey;



    //spring bean for rabbitmq queue
    //to store Json Objects
    @Bean
    public Queue jsonQueue() {
        return new Queue(jsonQueue,true); // Set the queue to be durable by set true. so when queue is restarted it still remain data.
    }

    @Bean
    public Queue dlq() {
        return new Queue("dlq_retry", true); // Durable DLQ
    }

    //spring bean for rabbitmq exchange
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }


    //binding between json queue and exchange using routing key

    @Bean
    public Binding jsonBinding() {
        return BindingBuilder.bind(jsonQueue())
                .to(exchange())
                .with(routingJsonKey);
    }

    @Bean
    public Binding dlqBinding() {
        return BindingBuilder.bind(dlq()).to(exchange())
                .with("dlq_retry_routing_key");
    }

    @Bean
    public Queue queueWithDLQ() {
        Map<String, Object> arguments = new HashMap<>();
        // Configure DLQ
        arguments.put("x-dead-letter-exchange", "");  // Default exchange
        arguments.put("x-dead-letter-routing-key", "dlq_retry_routing_key");  // Set routing key for the DLQ

        return new Queue(jsonQueue, true, false, false, arguments);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

    //ConnectionFactory
    //RabbitTemplate
    //RabbitAdmin
}
