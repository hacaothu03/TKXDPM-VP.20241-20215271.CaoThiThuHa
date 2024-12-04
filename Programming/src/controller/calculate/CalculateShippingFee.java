package controller.calculate;

import java.util.Random;
import entity.order.Order;

public class CalculateShippingFee implements ShippingFeeCalculator{
  public int calculateShippingFee(Order order){
    Random rand = new Random();
    int fees = (int) (((rand.nextFloat() * 10) / 100) * order.getAmount());
    // LOGGER.info("Order Amount: " + order.getAmount() + " -- Shipping Fees: " + fees);
    return fees;
  }
}
