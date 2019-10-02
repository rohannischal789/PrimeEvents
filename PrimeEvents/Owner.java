
/**
 * Write a description of class Owner here.
 *
 * @author (your name)
 * @version (a version number or a date)
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
    
    public Owner(int newUserId, String newFirstName, String newLastName, 
    String newPhoneNo,String newEmail,String newPassword,String newRole)
    {
        super(newUserId, newFirstName, newLastName,newPhoneNo,newEmail,newPassword,newRole,false,0);
    }

    
}
