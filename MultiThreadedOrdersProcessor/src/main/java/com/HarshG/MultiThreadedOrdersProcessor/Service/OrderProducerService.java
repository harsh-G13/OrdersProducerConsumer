package com.HarshG.MultiThreadedOrdersProcessor.Service;
import java.sql.SQLOutput;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.HarshG.MultiThreadedOrdersProcessor.Model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OrderProducerService{
    private final BlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();
    private final ScheduledExecutorService scheduler;
    private AtomicInteger producedOrders = new AtomicInteger(0);

    @Autowired
    public OrderProducerService(@Qualifier("producerScheduler") ScheduledExecutorService scheduler){
        this.scheduler = scheduler;
        startProducing();
    }

    public void startProducing(){
        scheduler.scheduleAtFixedRate(()->{
            String orderId = String.valueOf(UUID.randomUUID());
            Order order = new Order(orderId,"orderX",(Math.random()*1000));
            try {
                orderQueue.put(order);
                System.out.println("Order with orderId" + orderId + " placed successfully");
                producedOrders.incrementAndGet();
            } catch (InterruptedException e) {
                System.out.println("Unable to put item " + orderId + e);
                throw new RuntimeException(e);
            }
        },0,1, TimeUnit.SECONDS);
    }
    public BlockingQueue<Order> getOrderQueue(){
        return orderQueue;
    }

    public int getProducedOrders() {
        return producedOrders.get();
    }
}
