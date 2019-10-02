import java.util.Date;
/**
 * Write a description of class Customer here.
 *
 * @author (your name)
 * @version (a version number or a date)
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
