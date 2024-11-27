package controller;

import entity.cart.Cart;
import entity.cart.CartMedia;

import entity.invoice.Invoice;
import entity.order.Order;
import entity.order.OrderMedia;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

// -------------Procedural Cohesion----------------

/**
 * This class controls the flow of place order usecase in our AIMS project.
 * 
 * @author Quynhanh
 * @version 1.0
 */
public class PlaceOrderController extends BaseController {

  private static Logger LOGGER = utils.Utils.getLogger(PlaceOrderController.class.getName());

  /**
   * This method checks the avalibility of product when user click PlaceOrder button.
   * 
   * @throws SQLException when SQLException happen
   */
  public void placeOrder() throws SQLException {
    Cart.getCart().checkAvailabilityOfProduct();

  }

  /**
   * This method creates the new Order based on the Cart.
   * 
   * @return Order
   * @throws SQLException when SQLException happen
   */
  public Order createOrder() throws SQLException {
    Order order = new Order();
    for (Object object : Cart.getCart().getListMedia()) {
      CartMedia cartMedia = (CartMedia) object;
      OrderMedia orderMedia =
          new OrderMedia(cartMedia.getMedia(), cartMedia.getQuantity(), cartMedia.getPrice());
      order.getlstOrderMedia().add(orderMedia);
    }
    return order;
  }

  /**
   * This method creates the new Invoice based on order.
   * 
   * @param order order
   * @return Invoice
   */
  public Invoice createInvoice(Order order) {
    order.createOrderEntity();
    return new Invoice(order);
  }

  /**
   * This method takes responsibility for processing the shipping info from user.
   * 
   * @param info info
   * @throws InterruptedException when InterruptException happen
   * @throws IOException when IOException happen
   */
  public int processDeliveryInfo(HashMap<String, String> info)
      throws InterruptedException, IOException {
    LOGGER.info("Process Delivery Info");
    LOGGER.info(info.toString());
    return validateDeliveryInfo(info);
  }

  /**
   * The method validates the info.
   * 
   * @param info info
   * @return int
   * @throws InterruptedException when InterruptException happen
   * @throws IOException when IOException happen
   */
  public int validateDeliveryInfo(HashMap<String, String> info)
      throws InterruptedException, IOException {

    if (!validateName(info.get("name"))) {
      System.out.println("Invalid name!");
      JOptionPane.showMessageDialog(null, "Invalid name!",
      "Error", JOptionPane.ERROR_MESSAGE);
      return 0;
    }
    if (!validatePhoneNumber(info.get("phone"))) {
      System.out.println("Invalid phone number!");
      JOptionPane.showMessageDialog(null, "Invalid phone number!",
      "Error", JOptionPane.ERROR_MESSAGE);
      return 0;
    }
    if(!validateCity(info.get("province"))){
      System.out.println("You should choose city!");
      JOptionPane.showMessageDialog(null, "You should choose city!",
              "Error", JOptionPane.ERROR_MESSAGE);
      return 0;
    }
    if (!validateAddress(info.get("address"))) {
      System.out.println("Invalid address!");
      JOptionPane.showMessageDialog(null, "Invalid address!",
      "Error", JOptionPane.ERROR_MESSAGE);
      return 0;
    }
    return 1;
  }

  public boolean validateCity(String city) {
      return city != null ;
  }


  public boolean validateAddress(String address) {
    if (address == null || address.trim().length() == 0 || address.equals("null")) {
      return false;
    }
    return address.matches("^[.0-9a-zA-Z\\s,-]+$");
  }


  public boolean validatePhoneNumber(String phoneNumber) {
    // Regular expression kiểm tra số điện thoại có đúng 10 chữ số, bắt đầu bằng số 0, và không chứa toàn số 0
    String phoneRegex = "^0[1-9][0-9]{8}$";

    // Kiểm tra nếu số điện thoại truyền vào khớp với regular expression
    return phoneNumber.matches(phoneRegex);
  }




  public boolean validateName(String name) {
    // Kiểm tra xem name có null không
    if (name == null) {
      return false;
    }

    // Kiểm tra xem name có chỉ chứa dấu cách không
    if (name.trim().isEmpty()) {
      return false;
    }

    // Kiểm tra xem name có chứa ký tự không phải chữ cái không hoặc có ký tự đặc biệt không
    for (int i = 0; i < name.length(); i++) {
      char c = name.charAt(i);
      if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
        return false;
      }
    }

    // Nếu tất cả các ký tự đều là chữ cái hoặc dấu cách, trả về true
    return true;
  }

}
