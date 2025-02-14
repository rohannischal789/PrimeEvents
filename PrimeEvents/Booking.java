import java.util.Date;
/**
 * Class Booking is used to group all the attributes related to booking and associate that with the Event Controller class.
 *
 * @author Guanting Chen
 * @version 5-10-2019
 */
public class Booking
{
    // instance variables - replace the example below with your own
    private String status;
    private Payment payment;
    private Quotation quotation;

    /**
     * Constructor for objects of class Booking
     */
    public Booking()
    {
        // initialise instance variables
    }

    /**
     * Parameterized constructor for object of class booking
     */ 
    public Booking(String status, Payment payment, Quotation quotation)
    {
        this.status = status;
        this.payment = payment;
        this.quotation = quotation;
    }

    /**
     * Returns value of status
     * @return
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets new value of status
     * @param
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Returns value of payment
     * @return
     */
    public Payment getPayment() {
        return payment;
    }

    /**
     * Sets new value of payment
     * @param
     */
    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    /**
     * Returns value of quotation
     * @return
     */
    public Quotation getQuotation() {
        return quotation;
    }

    /**
     * Sets new value of quotation
     * @param
     */
    public void setQuotation(Quotation quotation) {
        this.quotation = quotation;
    }

}
