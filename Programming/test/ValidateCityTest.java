import controller.PlaceOrderController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.Assert.assertEquals;


class ValidateCityTest {

  private PlaceOrderController placeOrderController;

  
  /** 
   * @throws Exception
   */
  @BeforeEach
  void setUp() throws Exception {
    placeOrderController = new PlaceOrderController();
  }

  
  /** 
   * @param address
   * @param expected
   */
  @ParameterizedTest
  @CsvSource({ "Hà Nộ,true", "null,false" })

  void test(String address, boolean expected) {
    boolean isValid = placeOrderController.validateAddress(address);
    assertEquals(expected, isValid);
  }
}
