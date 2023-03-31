package com.codefuentes.javacodechallenge;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    @Bean
    public CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        return connectionFactory;
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

    @Bean
    public FanoutExchange clientCreatedExchange() {
        return new FanoutExchange("client.created");
    }

    @Bean
    public FanoutExchange clientUpdatedExchange() {
        return new FanoutExchange("client.updated");
    }

    @Bean
    public FanoutExchange clientDeletedExchange() {
        return new FanoutExchange("client.deleted");
    }

    @Bean
    public Binding clientCreatedBinding(Queue clientCreatedQueue, FanoutExchange clientCreatedExchange) {
        return BindingBuilder.bind(clientCreatedQueue).to(clientCreatedExchange);
    }

    @Bean
    public Binding clientUpdatedBinding(Queue clientUpdatedQueue, FanoutExchange clientUpdatedExchange) {
        return BindingBuilder.bind(clientUpdatedQueue).to(clientUpdatedExchange);
    }

    @Bean
    public Binding clientDeletedBinding(Queue clientDeletedQueue, FanoutExchange clientDeletedExchange) {
        return BindingBuilder.bind(clientDeletedQueue).to(clientDeletedExchange);
    }

    @Bean
    public Queue clientCreatedQueue() {
        return new Queue("client.created");
    }

    @Bean
    public Queue clientUpdatedQueue() {
        return new Queue("client.updated");
    }

    @Bean
    public Queue clientDeletedQueue() {
        return new Queue("client.deleted");
    }
}
