import java.util.Date;
/**
 * Class Customer is the child of parent class User.
 *
 * @author Rohan Nischal
 * @version 5-10-2019
 */
public class Customer extends User
{
    // instance variables - replace the example below with your own
    private Date dob;
    private boolean isVeteran;

    /**
     * Constructor for objects of class Customer
     */
    public Customer(int newUserId, String newFirstName, String newLastName, 
    String newPhoneNo,String newEmail,String newPassword,String newRole)
    {
        super(newUserId, newFirstName, newLastName,newPhoneNo,newEmail,newPassword,newRole,false,0);
    }
}
