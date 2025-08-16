package com.HarshG.MultiThreadedOrdersProcessor.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class MetricsService {
    private final ScheduledExecutorService metricsScheduler;
    private final OrderProducerService orderProducerService;
    private final OrderConsumerService orderConsumerService;
   @Autowired
   public MetricsService(@Qualifier("metricsScheduler") ScheduledExecutorService metricsScheduler, OrderProducerService orderProducerService, OrderConsumerService orderConsumerService){

        this.metricsScheduler = metricsScheduler;
       this.orderProducerService = orderProducerService;
       this.orderConsumerService = orderConsumerService;

       startMertrics();
   }

    public void startMertrics() {
        metricsScheduler.scheduleAtFixedRate(()->{
            int queueSize = orderProducerService.getOrderQueue().size();
            int inProgressOrderes = orderConsumerService.getOrdersInProgress();
            int ordersConsumed = orderConsumerService.getOrdersConsumed();
            int totalOrdersProduced = orderProducerService.getProducedOrders();
            System.out.printf("\n[METRICS] %s | Produced:%d | Queue: %d | Consumed: %d | In Progress: %d\n",
                    LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME),
                    totalOrdersProduced,
                    queueSize,
                    ordersConsumed,
                    inProgressOrderes);
        },5,5, TimeUnit.SECONDS);
    }
}
