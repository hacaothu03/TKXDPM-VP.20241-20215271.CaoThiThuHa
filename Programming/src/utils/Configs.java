package utils;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * @author NTVu 20204625
 */
public class Configs {


    public static final String PROCESS_TRANSACTION_URL = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
    public static final String vnp_SecureHash = "HUQHTRVXVRGJJWHMBFCAUBAXOSAJBIND";
    public static final String vnp_TmnCode = "TXOOZNX4";


    // database Configs
    public static final String DB_NAME = "aims";
    public static final String DB_USERNAME = System.getenv("DB_USERNAME");
    public static final String DB_PASSWORD = System.getenv("DB_PASSWORD");
    // static resource
    public static final String IMAGE_PATH = "assets/images";
    public static final String INVOICE_SCREEN_PATH = "/views/fxml/invoice.fxml";
    public static final String INVOICE_MEDIA_SCREEN_PATH = "/views/fxml/media_invoice.fxml";
    public static final String PAYMENT_SCREEN_PATH = "/views/fxml/payment.fxml";
    public static final String RESULT_SCREEN_PATH = "/views/fxml/result.fxml";
    public static final String SPLASH_SCREEN_PATH = "/views/fxml/splash.fxml";
    public static final String CART_SCREEN_PATH = "/views/fxml/cart.fxml";
    public static final String SHIPPING_SCREEN_PATH = "/views/fxml/deliveryInformation.fxml";
    public static final String SHIPPING_RUSH_SCREEN_PATH = "/views/fxml/shipping.fxml";
    public static final String CART_MEDIA_PATH = "/views/fxml/media_cart.fxml";
    public static final String HOME_PATH = "/views/fxml/home.fxml";
    public static final String HOME_MEDIA_PATH = "/views/fxml/media_home.fxml";
    public static final String POPUP_PATH = "/views/fxml/popup.fxml";
    public static final String DELIVERY_METHODS_PATH = "/views/fxml/delivery_methods_form.fxml";
    public static final int PLACE_RUSH_ORDER = 1;
    public static final int PLACE_ORDER = 2;
    public static String CURRENCY = "VND";
    public static float PERCENT_VAT = 10;
    public static Font REGULAR_FONT = Font.font("Segoe UI", FontWeight.NORMAL, FontPosture.REGULAR, 24);
    public static String[] PROVINCES = {"Bắc Giang", "Bắc Kạn", "Cao Bằng", "Hà Giang", "Lạng Sơn", "Phú Thọ",
            "Quảng Ninh", "Thái Nguyên", "Tuyên Quang", "Yên Bái", "Điện Biên", "Hòa Bình", "Lai Châu", "Sơn La",
            "Bắc Ninh", "Hà Nam", "Hải Dương", "Hưng Yên", "Nam Định", "Ninh Bình", "Thái Bình", "Vĩnh Phúc", "Hà Nội",
            "Hải Phòng", "Hà Tĩnh", "Nghệ An", "Quảng Bình", "Quảng Trị", "Thanh Hóa", "Thừa Thiên-Huế", "Đắk Lắk",
            "Đắk Nông", "Gia Lai", "Kon Tum", "Lâm Đồng", "Bình Định", "Bình Thuận", "Khánh Hòa", "Ninh Thuận",
            "Phú Yên", "Quảng Nam", "Quảng Ngãi", "Đà Nẵng", "Bà Rịa-Vũng Tàu", "Bình Dương", "Bình Phước", "Đồng Nai",
            "Tây Ninh", "Hồ Chí Minh", "An Giang", "Bạc Liêu", "Bến Tre", "Cà Mau", "Đồng Tháp", "Hậu Giang",
            "Kiên Giang", "Long An", "Sóc Trăng", "Tiền Giang", "Trà Vinh", "Vĩnh Long", "Cần Thơ"};
}
