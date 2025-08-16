package com.HarshG.MultiThreadedOrdersProcessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class MultiThreadedOrdersProcessorApplication {

	public static void main(String[] args) {
		System.out.println("Starting MultiThreadedOrdersProcessorApplication");
		ConfigurableApplicationContext context = SpringApplication.run(MultiThreadedOrdersProcessorApplication.class, args);

		ScheduledExecutorService exector = Executors.newScheduledThreadPool(1);

		exector.schedule(()->{
			context.close();
		},15, TimeUnit.SECONDS);

	}

}
