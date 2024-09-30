package com.app.example.kafka;

import com.app.example.kafka.components.Consumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		Consumer consumer = new Consumer();
		consumer.subscribe("topicTest");
	}

}
