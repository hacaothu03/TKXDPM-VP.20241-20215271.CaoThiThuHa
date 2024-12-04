import static org.junit.jupiter.api.Assertions.*;
import controller.PlaceOrderController;
import entity.media.Book;
import entity.media.Media;
import entity.order.Order;
import entity.order.OrderMedia;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import controller.calculate.CalculateShippingFee;
import org.junit.jupiter.api.Test;
import entity.order.Order;

import java.util.Random;

public class CalculateShippingFeeTest {

    private PlaceOrderController placeOrderController;

    @BeforeEach
    public void SetUp(){
        placeOrderController = new PlaceOrderController();
    }

    @ParameterizedTest
    @CsvSource({
            "Hà Nội, 5.0, 105, 0 ",
            "Hà Nội, 3.0, 30, 22 ",
            "Hà Nội, 5.0, 30, 32 ",
            "Hải Dương, 5.0, 105, 0 ",
            "Hưng Yên, 0.5, 30, 30 ",
            "Hưng Yên, 2.5, 30, 40 ",
    })

    public void calculateShippingFeeTest(String province, double maxWeigh, int price , int expectedFees) throws SQLException {
        HashMap<String, String> info = new HashMap<>();
        info.put("province", province);
        Order order = new Order();
        order.setDeliveryInfo(info);
        Book book = new Book();
        book.setPrice(price);
        book.setWeigh(maxWeigh);
        List<OrderMedia> orderMedia = new ArrayList<>();
        orderMedia.add(new OrderMedia(book,1,price));
        order.setlstOrderMedia(orderMedia);

        int fees = placeOrderController.calculateShippingFee(order);

        assertEquals(fees, expectedFees);
    }
}