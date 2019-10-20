import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
/**
 * Class Hall is used to group all the different attributes of an hall object and this could be accessed 
 * as and when required.
 * @author Rohan Nischal,Guanting Chen,Swathi Jadhav
 * @version 19-10-2019
 */
public class Hall
{
    private int hallId;
    private String hallName;
    private String suburb;
    private String address;
    private int capacity;
    private int deposit;
    private int price;
    private ArrayList<String> eventTypes;
    private Owner owner;
    private ArrayList<Quotation> quotations;
    private ArrayList<Review> reviews;
    private ArrayList<Discount> discounts;

    /**
     * Constructor for objects of class Hall
     */
    public Hall()
    {
        // initialise instance variables

    }

    /**
     * Parameterized constructor for object of class Hall
     */    
    public Hall(int hallId, String hallName, String suburb, String address, int capacity, int deposit, ArrayList<String> eventTypes, int price, Owner owner)
    {
        this.hallId = hallId;
        this.hallName = hallName;
        this.suburb = suburb;
        this.address = address;
        this.capacity = capacity;
        this.deposit = deposit;
        this.price = price;
        this.eventTypes = eventTypes;
        this.owner = owner;

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
     * Sets new value of hallId
     * @param
     */
    public void setHallId(int hallId) 
    {
        this.hallId = hallId;
    }

    /**
     * Returns value of hallName
     * @return
     */
    public String getHallName() 
    {
        return hallName;
    }

    /**
     * Sets new value of hallName
     * @param
     */
    public void setHallName(String hallName) 
    {
        this.hallName = hallName;
    }

    /**
     * Returns value of suburb
     * @return
     */
    public String getSuburb() 
    {
        return suburb;
    }

    /**
     * Sets new value of suburb
     * @param
     */
    public void setSuburb(String suburb) 
    {
        this.suburb = suburb;
    }

    /**
     * Returns value of address
     * @return
     */
    public String getAddress() 
    {
        return address;
    }

    /**
     * Sets new value of address
     * @param
     */
    public void setAddress(String address) 
    {
        this.address = address;
    }

    /**
     * Returns value of capacity
     * @return
     */
    public int getCapacity() 
    {
        return capacity;
    }

    /**
     * Sets new value of capacity
     * @param
     */
    public void setCapacity(int capacity) 
    {
        this.capacity = capacity;
    }

    /**
     * Returns value of deposit
     * @return
     */
    public int getDeposit() 
    {
        return deposit;
    }

    /**
     * Sets new value of deposit
     * @param
     */
    public void setDeposit(int deposit) 
    {
        this.deposit = deposit;
    }

    /**
     * Returns value of price
     * @return
     */
    public int getPrice() 
    {
        return price;
    }

    /**
     * Sets new value of price
     * @param
     */
    public void setPrice(int price) 
    {
        this.price = price;
    }

    /**
     * Returns value of eventTypes
     * @return
     */
    public ArrayList<String> getEventTypes() 
    {
        return eventTypes;
    }

    /**
     * Sets new value of eventTypes
     * @param
     */
    public void setEventTypes(ArrayList<String> eventTypes) 
    {
        this.eventTypes = eventTypes;
    }

    /**
     * Returns value of owner
     * @return
     */
    public Owner getOwner() 
    {
        return owner;
    }

    /**
     * Sets new value of owner
     * @param
     */
    public void setOwner(Owner owner) 
    {
        this.owner = owner;
    }

    /**
     * Returns value of quotations
     * @return
     */
    public ArrayList<Quotation> getQuotations() 
    {
        return quotations;
    }

    /**
     * Sets new value of quotations
     * @param
     */
    public void setQuotations(ArrayList<Quotation> quotations) 
    {
        this.quotations = quotations;
    }

    /**
     * resets the value of reviews
     */
    public void resetReviews() 
    {
        this.reviews = new ArrayList<Review>();
    }

    /**
     * resets the value of quotations
     */
    public void resetQuotations() 
    {
        this.quotations = new ArrayList<Quotation>();
    }

    /**
     * Returns value of reviews
     * @return
     */
    public ArrayList<Review> getReviews() 
    {
        return reviews;
    }

    /**
     * Sets new value of reviews
     * @param
     */
    public void setReviews(ArrayList<Review> reviews) 
    {
        this.reviews = reviews;
    }

    /**
     * Returns value of discounts
     * @return
     */
    public ArrayList<Discount> getDiscounts() 
    {
        return discounts;
    }

    /**
     * Sets new value of discounts
     * @param
     */
    public void setDiscounts(ArrayList<Discount> discounts) 
    {
        this.discounts = discounts;
    }

    /**
     * Returns String
     * @return
     */
    public String displayShort()
    {
        return hallId + ". " + hallName + ", " + suburb;
    }

    /**
    * Name: Hall 17
    * Suburb: Clayton
    * Address: 17, Clayton Road, Clayton
    * Capacity: 300
    * Deposit: 50%
    * Event Types: Wedding ceremony, Wedding reception, Birthday, Anniversary
    * Price: $1200 (Catering extra)
    */
    public String displayLong()
    {
        return "Name: " + hallName + "\nSuburb: " + suburb + "\nAddress: " + address + "\nCapacity: "
        + capacity + "\nDeposit: " + deposit + "%\nEvent Types: " +  getAllEventTypes() + "\nPrice: " + price 
        + "\nBooked Dates:" + (getFormattedBookings().equals("") ? "None yet" : getFormattedBookings());
    }
    
    /**
    * Converting the booking date into a string format.
    * @return
    */
    private String getFormattedBookings()
    {
        StringBuffer strBuf = new StringBuffer("");
        for(Quotation quotation : getQuotations())
        {
            if(quotation.getQuotationStatus().equals("ACCEPTED"))
            {
                strBuf.append(" " + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(quotation.getStartEventDateTime()) +" - " 
                + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(quotation.getEndEventDateTime()) + ". " );
            }
        }
        return strBuf.toString();
    }
    
    /**
    * Getting all the list of "event type" values and converitng them into a single comma seperated string value.
    * @return
    */
    private String getAllEventTypes()
    {
        String toReturn = "";
        for(int i = 0 ; i < eventTypes.size(); i++)
        {
            String toAdd = (i == eventTypes.size() - 1) ? "" :  ",";
            toReturn += eventTypes.get(i) + toAdd;
        }
        return toReturn;            
    }

    /**
    * Getting all the review objects associated with this class's Hall object. 
    * @return
    */
    public String getAllReviews()
    {
        String toReturn = "";
        for(int i = 0 ; i < reviews.size(); i++)
        {
            toReturn += i+1 + ". " + reviews.get(i).getRating() + " stars | " + reviews.get(i).getReview() + " | Customer name: " 
            + reviews.get(i).getCustomer().getFirstName() + " " + reviews.get(i).getCustomer().getLastName() + " |\n\n";
        }
        return toReturn;            
    }

    /**
    * Setting review object by passing parameterized constructor values of class Review. 
    * Hall objects are associated to review object. 
    * @param
    */
    public void addReview(String newReview, Customer newCustomer,int newRating)
    {
        getReviews().add(new Review(newReview, newCustomer, newRating));
    }

    /**
    * Setting quotation object by passing parameterized constructor values of class quotation. 
    * Hall objects are associated to quotation objects. 
    * @param
    */
    public void addQuotation(int quotationId, Date startEventDateTime, Date endEventDateTime, int numberOfAttendees,String eventType,
    boolean requiresCatering, String specialRequirements, double finalPrice, String quotationStatus, Customer customer, int hallId, boolean depositPaid)
    {
        getQuotations().add(new Quotation( quotationId, startEventDateTime, endEventDateTime, numberOfAttendees,eventType,
                requiresCatering, specialRequirements,finalPrice, quotationStatus,customer, hallId, depositPaid));
    }

    /**
    * Returns list of quotations based on the parameterized customer id value.    
    * @param
    */
    public ArrayList<Quotation> getQuotationByCustomerId(int customerId)
    {
        ArrayList<Quotation> quotationList = new ArrayList<Quotation>();
        for(Quotation quotation: getQuotations())
        {
            if(quotation.getCustomer().getUserId() == customerId)
            {
                quotationList.add(quotation);
            }
        }
        return quotationList;
    }

    /**
    * Returns list of quotations based on the parameterized customer id value.    
    * @param
    */
    public String getCustomerQuotationResponses(int customerId)
    {
        StringBuffer str = new StringBuffer("");

        for(Quotation quotation: getQuotationByCustomerId(customerId))
        {
            str.append(quotation.getQuotationId() + ". " + getHallName() + ", " + getSuburb() + ", " 
                + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(quotation.getStartEventDateTime()) + " - " + quotation.getQuotationStatus() + "\n");
        }
        return str.toString();
    }

    /**
    * Returns customer quotation details on passing customer id and quotation id values.    
    * @return
    */
    public String getCustomerQuotationDetails(int customerId, int quotationId)
    {
        Quotation currQuotation = null;
        for(Quotation quotation : getQuotationByCustomerId(customerId))
        {
            if(quotation.getQuotationId() == quotationId) 
                currQuotation = quotation;
        }
        if(currQuotation!=null)
        {
            return "Name: " + getHallName() + "\n" + "Suburb: " + getSuburb() + "\n"
            + "Address: " + getAddress()+ "\n" + "Capacity: " + getCapacity()+ "\n" + "Deposit: " + getDeposit() + "%\n"
            + "Event Type: " + currQuotation.getEventType()+ "\n" + "Price: " + currQuotation.getFinalPrice()+ "\n"
            + "Event Start Date: " + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(currQuotation.getStartEventDateTime())+ "\n"
            + "Event End Date: " + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(currQuotation.getEndEventDateTime())+ "\n" + "Total number of attendees: " + currQuotation.getNumberOfAttendees()+ "\n"
            + "Catering required: " + (currQuotation.getRequiresCatering() == true ? "Yes" : "No") + "\n" + "Special Requirements: " + currQuotation.getSpecialRequirements()+ "\n"
            + "Status: " + currQuotation.getQuotationStatus()+ "\n" + "Deposit Paid: " + ((currQuotation.getDepositPaid() == true) ? "Yes" :"No");
        }
        else
        {
            return null;
        }
    }
    
    /**
    * Returns customer receipt details on passing customer id and quotation id values.    
    * @return
    */
    public String getFormattedReceipt(int customerId, int quotationId)
    {
        Quotation currQuotation = null;
        for(Quotation quotation : getQuotationByCustomerId(customerId))
        {
            if(quotation.getQuotationId() == quotationId) 
                currQuotation = quotation;
        }
        if(currQuotation!=null)
        {
            return "Name: " + getHallName() + "\n" + "Suburb: " + getSuburb() + "\n"
            + "Address: " + getAddress()+ "\n" + "Capacity: " + getCapacity()+ "\n" + "Deposit: " + getDeposit() + "%\n"
            + "Event Type: " + currQuotation.getEventType()+ "\n" + "Price: " + currQuotation.getFinalPrice()+ "\n"
            + "Event Start Date: " + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(currQuotation.getStartEventDateTime())+ "\n"
            + "Event End Date: " + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(currQuotation.getEndEventDateTime())+ "\n" + "Total number of attendees: " + currQuotation.getNumberOfAttendees()+ "\n"
            + "Catering required: " + (currQuotation.getRequiresCatering() == true ? "Yes" : "No") + "\n" + "Special Requirements: " + currQuotation.getSpecialRequirements()+ "\n"
            + "Status: " + currQuotation.getQuotationStatus()+ "\n" + "Deposit Paid: " + ((currQuotation.getDepositPaid() == true) ? "Yes" :"No");
        }
        else
        {
            return null;
        }
    }

    /**
    * Returns quotaion object on passing quotation id value.    
    * @return
    */
    public Quotation getQuotationById(int id)
    {
        for(Quotation quotation : getQuotations())
        {
            if(quotation.getQuotationId() == id)
            {
                return quotation;
            }
        }

        return null;
    }
    
    /**
    * Returns quotation object on passing quotation id value.    
    * @return
    */
    public String getQuotationRequests()
    {
        StringBuffer str = new StringBuffer("");

        for(Quotation quotation: getQuotations())
        {
            str.append(quotation.getQuotationId() + ". " + getHallName() + ", " + getSuburb() + ", " 
                + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(quotation.getStartEventDateTime()) + " - " + quotation.getQuotationStatus() + "\n");
        }
        return str.toString();
    }
    
    /**
    * Returns quotation details on passing quotation id value.    
    * @return
    */
    public String getQuotationRequestsByID(int quotationID)
    {
        StringBuffer str = new StringBuffer("");

        for(Quotation quotation: getQuotations())
        {
            if(quotation.getQuotationId() == quotationID)
                str.append("Hall Name: " + getHallName() + "\nStart Date " 
                + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(quotation.getStartEventDateTime()) + "\nEnd Date " + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(quotation.getEndEventDateTime())
                + "\nAttendees: " + quotation.getNumberOfAttendees() + "\nEvent type: " + quotation.getEventType() + "\nSpecial Requirements: " + quotation.getSpecialRequirements()
                + "\nFinal Price: " + quotation.getFinalPrice() + "\nQuotationStatus: " + quotation.getQuotationStatus() + "\n");
        }
        return str.toString();
    }

}
