import java.util.Date;

/**
 * Class Quotation is used to group all the attributes related to quotaion and associate that with the hall class.
 *
 * @author Rohan Nischal,Guanting Chen,Swathi Jadhav
 * @version 19-10-2019
 */
public class Quotation
{
    // instance variables - replace the example below with your own
    private int quotationId;
    private Date startEventDateTime;
    private Date endEventDateTime;
    private int numberOfAttendees;
    private String eventType;
    private boolean requiresCatering;
    private String specialRequirements;
    private double finalPrice;
    private String quotationStatus;
    private int hallId;
    private Customer customer;
    private boolean depositPaid;

    /**
     * Constructor for objects of class Quotation
     */
    public Quotation()
    {
        // initialise instance variables

    }

    /**
     * Parameterized constructor for object of class quotation
     */
    public Quotation(int quotationId, Date startEventDateTime, Date endEventDateTime, int numberOfAttendees,String eventType,
    boolean requiresCatering, String specialRequirements, double finalPrice, String quotationStatus,
    Customer customer, int hallId, boolean depositPaid)
    {
        this.quotationId = quotationId;
        this.startEventDateTime = startEventDateTime;
        this.endEventDateTime = endEventDateTime;
        this.numberOfAttendees = numberOfAttendees;
        this.eventType = eventType;
        this.requiresCatering = requiresCatering;
        this.specialRequirements = specialRequirements;
        this.finalPrice = finalPrice;
        this.quotationStatus = quotationStatus;
        this.customer = customer;
        this.hallId = hallId;
        this.depositPaid = depositPaid;
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
     * Returns value of startEventDateTime
     * @return
     */
    public Date getStartEventDateTime() {
        return startEventDateTime;
    }

    /**
     * Sets new value of startEventDateTime
     * @param
     */
    public void setStartEventDateTime(Date startEventDateTime) {
        this.startEventDateTime = startEventDateTime;
    }

    /**
     * Returns value of endEventDateTime
     * @return
     */
    public Date getEndEventDateTime() {
        return endEventDateTime;
    }

    /**
     * Sets new value of endEventDateTime
     * @param
     */
    public void setEndEventDateTime(Date endEventDateTime) {
        this.endEventDateTime = endEventDateTime;
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
    public boolean getRequiresCatering() {
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

    /**
     * Returns value of hallId
     * @return
     */
    public int getHallId()
    {
        return hallId;
    }

    /**
     * Sets new value of quotationId
     * @param
     */
    public void setHallId(int hallId) 
    {
        this.hallId = hallId;
    }
    
    /**
     * Returns value of deposit paid
     * @return
     */    
    public boolean getDepositPaid()
    {
        return depositPaid;
    }

    /**
     * Sets new value of deposit paid
     * @param
     */
    public void setDepositPaid(boolean depositPaid) 
    {
        this.depositPaid = depositPaid;
    }
}
