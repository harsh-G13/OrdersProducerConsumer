package com.HarshG.MultiThreadedOrdersProcessor.Configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Configuration
public class ExecutorConfig {
   final int producerThreadCount = 1;
   final int consumerThreadCount = 3;
   final int metricsThreadCount = 1;
   @Bean
   public ScheduledExecutorService producerScheduler(){
        return Executors.newScheduledThreadPool(producerThreadCount);
    }
   @Bean
   public ExecutorService consumerScheduler(){
       return Executors.newFixedThreadPool(consumerThreadCount);
    }

    @Bean
    public ScheduledExecutorService metricsScheduler(){
       return Executors.newScheduledThreadPool(metricsThreadCount);
    }
}
