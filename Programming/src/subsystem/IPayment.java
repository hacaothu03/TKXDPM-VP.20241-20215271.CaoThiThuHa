package subsystem;

import common.exception.PaymentException;
import common.exception.UnrecognizedException;
import entity.payment.Transaction;

import java.text.ParseException;
import java.util.Map;

/**
 * The {@code InterbankInterface} class is used to communicate with the
 * {@link VnPaySubsystem InterbankSubsystem} to make transaction.
 *
 * @author hieud
 */
public interface IPayment {

    /**
     * Pay order, and then return the payment transaction
     * @param amount   - the amount to pay
     * @param contents - the transaction contents
     * @return {@link entity.payment.Transaction PaymentTransaction} - if the
     * payment is successful
     * @throws PaymentException      if responded with a pre-defined error code
     * @throws UnrecognizedException if responded with an unknown error code or
     *                               something goes wrong
     */
    public abstract String generatePayUrl(int amount, String contents)
            throws PaymentException, UnrecognizedException;


    public Transaction
    makePaymentTransaction(Map<String, String> response) throws ParseException;
}
