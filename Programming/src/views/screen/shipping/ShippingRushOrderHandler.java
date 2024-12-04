package views.screen.shipping;

import common.exception.InvalidDeliveryInfoException;
import controller.PlaceOrderController;
import controller.calculate.CalculateController;
import entity.invoice.Invoice;
import entity.order.Order;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.invoice.InvoiceScreenHandler;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ShippingRushOrderHandler extends BaseScreenHandler implements Initializable
{
    @FXML
    private Label screenTitle;

    @FXML
    private TextField name;

    @FXML
    private TextField phone;

    @FXML
    private TextField address;

    @FXML
    private TextField instructions;

    @FXML
    private ComboBox<String> province;

    private Order order;



    /**
     * @param arg0
     * @param arg1
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        final BooleanProperty firstTime = new SimpleBooleanProperty(true); // Variable to store the focus on stage load
        name.focusedProperty().addListener((observable,  oldValue,  newValue) -> {
            if(newValue && firstTime.get()){
                content.requestFocus(); // Delegate the focus to container
                firstTime.setValue(false); // Variable value changed for future references
            }
        });
        this.province.getItems().addAll(Configs.PROVINCES);
    }

    public ShippingRushOrderHandler(Stage stage, String screenPath, Order order) throws IOException {
        super(stage, screenPath);
        this.order = order;
    }

    public PlaceOrderController getBController(){
        return (PlaceOrderController) super.getBController();
    }
}
