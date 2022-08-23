package com.miltron.miltron;

import com.miltron.miltron.connection.ConnectionTcpServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class MiltronApplication  implements CommandLineRunner {

	@Value("${miltron.rocket.start.port}")
	private int startPort;
	@Value("${miltron.rocket.end.port}")
	private int endPort;
	@Value("${miltron.rockets.host}")
	private String host;

	@Autowired
	private SimpMessagingTemplate client;

	public static void main(String[] args) {
		SpringApplication.run(MiltronApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ExecutorService executor = Executors.newFixedThreadPool(10);
		for (int i = startPort; i <= endPort ; i++) {
			ConnectionTcpServer c = new ConnectionTcpServer(client,host,i);
			executor.execute(c);
		}

	}
}
