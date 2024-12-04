package controller;

import entity.order.Order;
import entity.order.OrderMedia;
import java.util.List;

/**
 * This class controls the flow of place rush order UC in our AIMS project.
 * 
 * @author HaPham
 * @version 1.0
 */
public class PlaceRushOrderController extends BaseController {

  private int total = 0;


  /**
   * This method check whether order can be placed rush orders or not and place rush order.
   * 
   * @param order order
   * @param address address
   * @return int
   */
  public int placeRushOrder(Order order, String address) {
    List<OrderMedia> lstOrderMedia = order.getlstOrderMedia();
    if (!validateSupportedAddress(address)) {
      System.out.println("This address is not supported to rush order!");
      return 0;
    }
    if (lstOrderMedia == null) {
      return 0;
    }
    for (OrderMedia i : lstOrderMedia) {
      if (!validateSupportedMedia(i)) {
        System.out.println("This media is not support: " + i.getMedia().getTitle());
      } else {
        total += calculateRushOrderShippingFee(i);
      }
    }
    return total;
  }

  public int calculateRushOrderShippingFee(OrderMedia orderMedia) {
    if (orderMedia == null || orderMedia.getQuantity() == 0) {
      System.out.print("The order must not be empty!");
      return 0;
    }
      return orderMedia.getQuantity() * 10;
  }

  public boolean validateSupportedMedia(OrderMedia orderMedia) {
    if (orderMedia == null) {
      return false;
    }
//    return true;
    return orderMedia.getMedia().isSupport();
  }

  public boolean validateSupportedAddress(String address) {
    address = address.toLowerCase();
    if (address == null || address.isBlank() || address == "null") {
      return false;
    }
    if (address.contains("hanoi") || address.contains("ha noi") || address.contains("Hà Nội")) {
      if (address.matches("^[.0-9a-zA-Z\\s,-]+$")) {
        return true;
      }
    }
    return false;
  }
}
