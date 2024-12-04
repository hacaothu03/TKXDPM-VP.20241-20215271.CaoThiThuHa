package controller.calculate;

import entity.order.Order;

interface ShippingFeeCalculator {
  public int calculateShippingFee(Order order);

}
