package controller;

import common.exception.PaymentException;
import common.exception.UnrecognizedException;
import entity.cart.Cart;
import subsystem.IPayment;
import subsystem.VnPaySubsystem;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Hashtable;
import java.util.Map;

/**
 * This {@code PaymentController} class control the flow of the payment process
 * in our AIMS Software.
 *
 * @author hieud
 */
public class PaymentController extends BaseController {


	/**
	 * Represent the Interbank subsystem
	 */
	private IPayment vnPayService;


	public Map<String, String> makePayment(Map<String, String> res, int orderId) {
		Map<String, String> result = new Hashtable<String, String>();

		try {
			this.vnPayService = new VnPaySubsystem();
			var trans = vnPayService.makePaymentTransaction(res);
			trans.save(orderId);
			result.put("RESULT", "PAYMENT SUCCESSFUL!");
			result.put("MESSAGE", "You have succesffully paid the order!");
		} catch (PaymentException | UnrecognizedException | SQLException ex) {
			result.put("MESSAGE", ex.getMessage());
			result.put("RESULT", "PAYMENT FAILED!");

		} catch (ParseException ex) {
			result.put("MESSAGE", ex.getMessage());
			result.put("RESULT", "PAYMENT FAILED!");
		}
		return result;
	}

	/**
	 * Gen url thanh toán vnPay
	 * @param amount
	 * @param content
	 * @return
	 */
	public String getUrlPay(int amount, String content){
		vnPayService = new VnPaySubsystem();
		var url = vnPayService.generatePayUrl(amount, content);
		return url;
	}

	public void emptyCart() {
		Cart.getCart().emptyCart();
	}
}