
/**
 * Write a description of class User here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class User
{
    // instance variables - replace the example below with your own
    private int userId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
    private String role;

    /**
     * Constructor for objects of class User
     */
    public User()
    {
        userId = 0;
        firstName = "";
        lastName = "";
        phoneNumber = "";
        email = "";
        password = "";
        role = "";
    }

    public User(int newUserId, String newFirstName, String newLastName, 
    String newPhoneNo,String newEmail,String newPassword,String newRole)
    {
        userId = newUserId;
        firstName = newFirstName;
        lastName = newLastName;
        phoneNumber = newPhoneNo;
        email = newEmail;
        password = newPassword;
        role = newRole;
    }
}
