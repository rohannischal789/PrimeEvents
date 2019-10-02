import java.util.Date;

/**
 * Write a description of class Quotation here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Quotation
{
    // instance variables - replace the example below with your own
    private int quotationId;
    private Date eventDate;
    private int numberOfAttendees;
    private String eventType;
    private boolean requiresCatering;
    private String specialRequirements;
    private double finalPrice;
    private String quotationStatus;
    private Customer customer;

    /**
     * Constructor for objects of class Quotation
     */
    public Quotation()
    {
        // initialise instance variables

    }

    public Quotation(int quotationId, Date eventDate, int numberOfAttendees,String eventType,
    boolean requiresCatering, String specialRequirements, double finalPrice, String quotationStatus,
    Customer customer)
    {
        this.quotationId = quotationId;
        this.eventDate = eventDate;
        this.numberOfAttendees = numberOfAttendees;
        this.eventType = eventType;
        this.requiresCatering = requiresCatering;
        this.specialRequirements = specialRequirements;
        this.finalPrice = finalPrice;
        this.quotationStatus = quotationStatus;
        this.customer = customer;
    }

    /**
     * Returns value of quotationId
     * @return
     */
    public int getQuotationId()
    {
        return quotationId;
    }

    /**
     * Sets new value of quotationId
     * @param
     */
    public void setQuotationId(int quotationId) 
    {
        this.quotationId = quotationId;
    }

    /**
     * Returns value of eventDate
     * @return
     */
    public Date getEventDate() {
        return eventDate;
    }

    /**
     * Sets new value of eventDate
     * @param
     */
    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    /**
     * Returns value of numberOfAttendees
     * @return
     */
    public int getNumberOfAttendees() {
        return numberOfAttendees;
    }

    /**
     * Sets new value of numberOfAttendees
     * @param
     */
    public void setNumberOfAttendees(int numberOfAttendees) {
        this.numberOfAttendees = numberOfAttendees;
    }

    /**
     * Returns value of eventType
     * @return
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * Sets new value of eventType
     * @param
     */
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    /**
     * Returns value of requiresCatering
     * @return
     */
    public boolean isRequiresCatering() {
        return requiresCatering;
    }

    /**
     * Sets new value of requiresCatering
     * @param
     */
    public void setRequiresCatering(boolean requiresCatering) {
        this.requiresCatering = requiresCatering;
    }

    /**
     * Returns value of specialRequirements
     * @return
     */
    public String getSpecialRequirements() {
        return specialRequirements;
    }

    /**
     * Sets new value of specialRequirements
     * @param
     */
    public void setSpecialRequirements(String specialRequirements) {
        this.specialRequirements = specialRequirements;
    }

    /**
     * Returns value of finalPrice
     * @return
     */
    public double getFinalPrice() {
        return finalPrice;
    }

    /**
     * Sets new value of finalPrice
     * @param
     */
    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    /**
     * Returns value of quotationStatus
     * @return
     */
    public String getQuotationStatus() {
        return quotationStatus;
    }

    /**
     * Sets new value of quotationStatus
     * @param
     */
    public void setQuotationStatus(String quotationStatus) {
        this.quotationStatus = quotationStatus;
    }

    /**
     * Returns value of customer
     * @return
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets new value of customer
     * @param
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
