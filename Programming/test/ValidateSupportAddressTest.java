import controller.PlaceRushOrderController;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class ValidateSupportAddressTest {

  private PlaceRushOrderController placeRushOrderController;

  
  /** 
   * @throws Exception
   */
  @BeforeEach
  void setUp() throws Exception {
    placeRushOrderController = new PlaceRushOrderController();
  }

  
  /** 
   * @param address
   * @param expected
   */
  @ParameterizedTest
  @CsvSource({ "Hanoi,true", " 1str hanoi,true", "ha noi,true", "Ha Noi,true", "HaiPhong,false",
      "HaNoi!2,false", })

  public void test(String address, boolean expected) {
    // when
    boolean isValid = placeRushOrderController.validateSupportedAddress(address);
    // then
    assertEquals(expected, isValid);
  }
}
