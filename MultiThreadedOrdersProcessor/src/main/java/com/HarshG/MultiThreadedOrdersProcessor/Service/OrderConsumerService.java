package com.HarshG.MultiThreadedOrdersProcessor.Service;

import com.HarshG.MultiThreadedOrdersProcessor.Model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class OrderConsumerService {
 private final OrderProducerService orderProducerService;
 private final ExecutorService executor;
 private final BlockingQueue<Order> orderQueue;
 public  static AtomicInteger ordersConsumed = new AtomicInteger(0);
 public  static AtomicInteger ordersInProgress = new AtomicInteger(0);

 @Autowired
 public OrderConsumerService(OrderProducerService orderProducerService, @Qualifier("consumerScheduler") ExecutorService executor) {

  this.orderProducerService = orderProducerService;
  this.executor = executor;
  this.orderQueue = this.orderProducerService.getOrderQueue();
  startConsuming();
 }
 public void startConsuming(){
  for(int i=0;i<3;i++)
  {
   executor.submit(()->{
    while(true){
     try{
      ordersInProgress.incrementAndGet();
      Order order = orderQueue.take();
      Thread.sleep((long) (Math.random()*1000) + 5000);
      ordersConsumed.incrementAndGet();
      ordersInProgress.decrementAndGet();
     }catch(InterruptedException e){
      System.out.println("Error while consuming order");
     }
    }
   });
  }

 }

 public int getOrdersConsumed() {
   return ordersConsumed.get();
 } public int getOrdersInProgress() {
  return ordersInProgress.get();
 }
}
