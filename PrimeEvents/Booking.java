import java.util.Date;
/**
 * Write a description of class Booking here.
 *
 * @author (your name)
 * @version (a version number or a date)
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
    
    public String getBookingDetails()
    {
        // 1. Hall 1, 13/12/19 - 15/12/19 - Accepted
        return getQuotation().getQuotationId() + ". " + getQuotation().getHallId() + " " + getQuotation().getStartEventDateTime() + " "
        + getQuotation().getEndEventDateTime() + " - " + getPayment().getPaymentStatus();
    
    }

}
