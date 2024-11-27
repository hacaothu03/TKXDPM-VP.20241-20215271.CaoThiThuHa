
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import controller.PlaceRushOrderController;
import entity.media.Media;
import entity.order.OrderMedia;

class CalculateRushOrderShippingFeeTest {

  private PlaceRushOrderController placeRushOrderController;


  /**
   * @throws Exception
   */
  @BeforeEach
  void setUp() throws Exception {
    placeRushOrderController = new PlaceRushOrderController();
  }


  /**
   * @throws SQLException
   */
  @Test
  public void test() throws SQLException {
    // when
    OrderMedia orderMedia = null;
    int shippingFee = placeRushOrderController.calculateRushOrderShippingFee(orderMedia);
    // then
    assertEquals(0, shippingFee);
  }


  /**
   * @throws SQLException
   */
  @Test
  public void test1() throws SQLException {
    // when
    Media media1 = new Media(1, "POP2", "Music", 5, 9, "CD");
    OrderMedia orderMedia1 = new OrderMedia(media1, 2, 10);
    int shippingFee = placeRushOrderController.calculateRushOrderShippingFee(orderMedia1);
    // then
    assertEquals(20, shippingFee);
  }


  /**
   * @throws SQLException
   */
  @Test
  public void test2() throws SQLException {
    // when
    Media media2 = new Media(2, "Crash", "Music", 2, 9, "CD");
    OrderMedia orderMedia2 = new OrderMedia(media2, 3, 6);
    int shippingFee = placeRushOrderController.calculateRushOrderShippingFee(orderMedia2);
    // then
    assertEquals(30, shippingFee);
  }


  /**
   * @throws SQLException
   */
  @Test
  public void test3() throws SQLException {
    // when
    Media media3 = new Media(3, "Charli", "Music", 7, 9, "CD");
    OrderMedia orderMedia3 = new OrderMedia(media3, 0, 0);
    int shippingFee = placeRushOrderController.calculateRushOrderShippingFee(orderMedia3);
    // then
    assertEquals(0,shippingFee);
  }
}
