package com.HarshG.MultiThreadedOrdersProcessor.Model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class Order {
  private final String orderId;
  private final String orderItem;
  private final double orderAmount;

  public Order() {
    this.orderId = "";
    this.orderItem = "";
    this.orderAmount = 0.0;
  }
  public Order(String orderId, String orderItem, double orderAmount) {
    this.orderId = orderId;
    this.orderItem = orderItem;
    this.orderAmount = orderAmount;
  }
}
