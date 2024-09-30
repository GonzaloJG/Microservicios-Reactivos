package com.app.example.kafka;

import com.app.example.kafka.components.Productor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(Application.class, args);

		Productor productor = new Productor();
		for(int i=1; i<=10; i++) {
			productor.send("topicTest", "Mensaje generado a las " + LocalDateTime.now() + "para topicTest");
			Thread.sleep(100);
		}
		productor.close();
	}

}
