package controller.calculate;

import entity.order.Order;

public class CalculateController {
  /**
   * This method calculates the shipping fees of order.
   * 
   * @param order order
   * @return shippingFee
   */
  public int calculateShippingFee(Order order) {
    ShippingFeeCalculator cal = new CalculateShippingFee();
    return cal.calculateShippingFee(order);
  }
  
}
