
/**
 * Class Payment is used to group all the attributes related to payment and associate that with the Booking class.
 *
 * @author Guanting Chen
 * @version 5-10-2019
 */
public class Payment
{
    // instance variables - replace the example below with your own
    private String receiptNo;
    private double depositAmount;
    private double balanceAmount;
    private String paymentType;
    private String paymentStatus;

    /**
     * Constructor for objects of class Payment
     */
    public Payment()
    {
        // initialise instance variables
    }

    /**
     * Parameterized constructor for object of class payment
     */ 
    public Payment(String receiptNo, double depositAmount, double balanceAmount, String paymentType, String paymentStatus)
    {
        this.receiptNo = receiptNo;
        this.depositAmount = depositAmount;
        this.balanceAmount = balanceAmount;
        this.paymentType = paymentType;
        this.paymentStatus = paymentStatus;
    }

    /**
     * Returns value of paymentId
     * @return
     */
    public String getReceiptNo() {
        return receiptNo;
    }

    /**
     * Sets new value of paymentId
     * @param
     */
    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    /**
     * Returns value of depositAmount
     * @return
     */
    public double getDepositAmount() {
        return depositAmount;
    }

    /**
     * Sets new value of depositAmount
     * @param
     */
    public void setDepositAmount(double depositAmount) {
        this.depositAmount = depositAmount;
    }

    /**
     * Returns value of balanceAmount
     * @return
     */
    public double getBalanceAmount() {
        return balanceAmount;
    }

    /**
     * Sets new value of balanceAmount
     * @param
     */
    public void setBalanceAmount(double balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    /**
     * Returns value of paymentType
     * @return
     */
    public String getPaymentType() {
        return paymentType;
    }

    /**
     * Sets new value of paymentType
     * @param
     */
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * Returns value of paymentStatus
     * @return
     */
    public String getPaymentStatus() {
        return paymentStatus;
    }

    /**
     * Sets new value of paymentStatus
     * @param
     */
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
