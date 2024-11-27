import controller.PlaceRushOrderController;
import entity.media.Media;
import entity.order.OrderMedia;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ValidateSupportMediaTest {

  private PlaceRushOrderController placeRushOrderController;

  
  /** 
   * @throws Exception
   */
  @BeforeEach
  void setUp() throws Exception {
    placeRushOrderController = new PlaceRushOrderController();
  }

  @Test
  public void test() {
    OrderMedia orderMedia = null;
    boolean isValid = placeRushOrderController.validateSupportedMedia(orderMedia);
    assertEquals(false, isValid);
  }

  
  /** 
   * @throws SQLException
   */
  @Test
  public void test1() throws SQLException {
    Media media1 = new Media(1, "POP2", "Music", 5, 9, "CD", false);
    OrderMedia orderMedia1 = new OrderMedia(media1, 1, 5);
    boolean isValid = placeRushOrderController.validateSupportedMedia(orderMedia1);
    assertEquals(false, isValid);
  }


  
  /** 
   * @throws SQLException
   */
  @Test
  public void test4() throws SQLException {
    Media media4 = new Media(4, "Melodrama", "Music", 13, 9, "DVD", true);
    OrderMedia orderMedia4 = new OrderMedia(media4, 1, 13);
    boolean isValid = placeRushOrderController.validateSupportedMedia(orderMedia4);
    assertEquals(true, isValid);
  }

}
