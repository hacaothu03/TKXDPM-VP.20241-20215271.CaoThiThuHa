import controller.PlaceOrderController;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class ValidateAddressTest {

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
  @CsvSource({ "hanoi,true", "Hai Ba Trung Hanoi,true", "$# Hanoi,false",
       ",false", "    ,false" })

  void test(String address, boolean expected) {
    boolean isValid = placeOrderController.validateAddress(address);
    assertEquals(expected, isValid);
  }
}
