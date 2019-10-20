
/**
 *Class Owner is the child of parent class User.
 *
 * @author Rohan Nischal
 * @version 5-10-2019
 */
public class Owner extends User
{
    // instance variables - replace the example below with your own
    

    /**
     * Constructor for objects of class Owner
     */
    public Owner()
    {
        
    }
    
    /**
     * Parameterized constructor for owner class
     */
    public Owner(int newUserId, String newFirstName, String newLastName, 
    String newPhoneNo,String newEmail,String newPassword,String newRole)
    {
        super(newUserId, newFirstName, newLastName,newPhoneNo,newEmail,newPassword,newRole,false,0);
    }

    
}
