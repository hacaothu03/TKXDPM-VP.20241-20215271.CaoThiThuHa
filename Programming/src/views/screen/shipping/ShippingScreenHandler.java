package views.screen.shipping;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;

import common.exception.MediaNotAvailableException;
import controller.PlaceOrderController;
import controller.PlaceRushOrderController;
import controller.calculate.CalculateController;
import common.exception.InvalidDeliveryInfoException;
import controller.calculate.CalculateRushShippingFee;
import entity.invoice.Invoice;
import entity.order.Order;
import entity.shipping.Shipment;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.invoice.InvoiceScreenHandler;
import views.screen.popup.PopupScreen;

public class ShippingScreenHandler extends BaseScreenHandler implements Initializable {

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

	public ShippingScreenHandler(Stage stage, String screenPath, Order order) throws IOException {
		super(stage, screenPath);
		this.order = order;
	}

	
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

	
	/** 
	 * @param event
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws SQLException
	 */
	@FXML
	private CheckBox rushOrderCheckBox;
	int typeDelivery;
	@FXML
	void submitDeliveryInfo(MouseEvent event) throws IOException, InterruptedException, SQLException {

		// add info to messages
		HashMap messages = new HashMap<>();
		messages.put("name", name.getText());
		messages.put("phone", phone.getText());
		messages.put("address", address.getText());
		messages.put("instructions", instructions.getText());
		messages.put("province", province.getValue());
		order.setName(name.getText());
		order.setPhone(phone.getText());
		order.setProvince(province.getValue());
		order.setAddress(address.getText());
		System.out.println(order.getAddress());
		order.setInstruction(instructions.getText());
		try {
			// process and validate delivery info
			int valid = getBController().processDeliveryInfo(messages);
			if (valid == 0) return;
		} catch (InvalidDeliveryInfoException e) {
			throw new InvalidDeliveryInfoException(e.getMessage());
		}

		if (rushOrderCheckBox.isSelected()) {
			typeDelivery = utils.Configs.PLACE_RUSH_ORDER;
			requestToPlaceRushOrder(order);

		} else {
			// Logic when the checkbox is unchecked
			// calculate shipping fees
			typeDelivery = utils.Configs.PLACE_ORDER;
			var shipment = new Shipment(typeDelivery);
			CalculateController calController = new CalculateController();
			int shippingFees = calController.calculateShippingFee(order);
			order.setShippingFees(shippingFees);
			order.setShipment(shipment);
			// create invoice screen
			Invoice invoice = getBController().createInvoice(order);
			System.out.println(invoice.getOrder().getName());
			BaseScreenHandler InvoiceScreenHandler = new InvoiceScreenHandler(this.stage, Configs.INVOICE_SCREEN_PATH, invoice);
			InvoiceScreenHandler.setPreviousScreen(this);
			InvoiceScreenHandler.setHomeScreenHandler(homeScreenHandler);
			InvoiceScreenHandler.setScreenTitle("Invoice Screen");
			InvoiceScreenHandler.setBController(getBController());
			InvoiceScreenHandler.show();
		}
	}
	/**
	 * @throws SQLException
	 * @throws IOException
	 */
	private void requestToPlaceRushOrder(Order order) throws SQLException, IOException{
		try {
			PlaceRushOrderController placeRushOrderController= new PlaceRushOrderController();
			placeRushOrderController.placeRushOrder(order, address.getText());

			ShippingRushOrderHandler shippingRushOrderHandler = new ShippingRushOrderHandler(this.stage, Configs.SHIPPING_RUSH_SCREEN_PATH, order);
////			ShippingRushOrderHandler.setPreviousScreen(this);
////			ShippingRushOrderHandler.setHomeScreenHandler(homeScreenHandler);
			shippingRushOrderHandler.setBController(placeRushOrderController);
			shippingRushOrderHandler.setScreenTitle("Shipping Rush Order");
			shippingRushOrderHandler.show();

		}catch (InvalidDeliveryInfoException e) {
			throw new InvalidDeliveryInfoException(e.getMessage());
		}
    }


	/** 
	 * @return PlaceOrderController
	 */
	public PlaceOrderController getBController(){
		return (PlaceOrderController) super.getBController();
	}

	public void notifyError(){
		// TODO: implement later on if we need
	}

}
