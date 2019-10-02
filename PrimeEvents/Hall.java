import java.util.ArrayList;
import java.util.Date;
/**
 * Write a description of class Hall here.
 *
 * @author (your name)
 * @version (a version number or a date)
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
    
    public void resetReviews() 
    {
        this.reviews = new ArrayList<Review>();
    }
    
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
    
    public String displayShort()
    {
        return hallId + ". " + hallName + ", " + suburb;
    }
    
    /*
       Name: Hall 17
        Suburb: Clayton
        Address: 17, Clayton Road, Clayton
        Capacity: 300
        Deposit: 50%
        Event Types: Wedding ceremony, Wedding reception, Birthday, Anniversary
        Price: $1200 (Catering extra)
       */
    public String displayLong()
    {
        return "Name: " + hallName + "\nSuburb: " + suburb + "\nAddress: " + address + "\nCapacity: "
        + capacity + "\nDeposit: " + deposit + "%\nEvent Types: " +  getAllEventTypes() + "\nPrice: " + price;
    }
    
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
    
    public String getAllReviews()
    {
        String toReturn = "";
        for(int i = 0 ; i < reviews.size(); i++)
        {
            toReturn += reviews.get(i).getRating() + " stars | " + reviews.get(i).getReview() + " | Customer name: " 
                        + reviews.get(i).getCustomer().getFirstName() + " " + reviews.get(i).getCustomer().getLastName() + " |\n\n";
        }
        return toReturn;            
    }
    
    public void addReview(String newReview, Customer newCustomer,int newRating)
    {
        getReviews().add(new Review(newReview, newCustomer, newRating));
    }
    
    public void addQuotation(int quotationId, Date eventDate, int numberOfAttendees,String eventType,
    boolean requiresCatering, String specialRequirements, double finalPrice, String quotationStatus,
    Customer customer)
    {
        getQuotations().add(new Quotation( quotationId, eventDate, numberOfAttendees,eventType,
    requiresCatering, specialRequirements,finalPrice, quotationStatus,customer));
    }
    
}
