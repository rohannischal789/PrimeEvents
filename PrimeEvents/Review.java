
/**
 * Class Review is used to group all the attributes related to review and associate that with the hall class.
 *
 * @author Rohan Nischal
 * @version 5-10-2019
 */
public class Review
{
    // instance variables of class review
    private int rating;
    private String review;
    private Customer customer;

    /**
     * Constructor for objects of class Review
     */
    public Review()
    {
        // initialise instance variables
    }
    
    /**
     * Parameterized constructor for object of class review
     */        
    public Review(String newReview, Customer newCustomer,int newRating)
    {
        rating = newRating;
        review = newReview;
        customer = newCustomer;
    }
    
    
	/**
	* Returns value of rating
	* @return
	*/
	public int getRating() {
		return rating;
	}

	/**
	* Sets new value of rating
	* @param
	*/
	public void setRating(int rating) {
		this.rating = rating;
	}

	/**
	* Returns value of review
	* @return
	*/
	public String getReview() {
		return review;
	}

	/**
	* Sets new value of review
	* @param
	*/
	public void setReview(String review) {
		this.review = review;
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
