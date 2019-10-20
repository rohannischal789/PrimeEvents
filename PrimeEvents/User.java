
/**
 * Class User defines all the attributes related to a users and is the parent of 3 associated sub-classes.
 * 1. Customer
 * 2. Owner
 * 3. Admin
 * @author Rohan Nischal
 * @version 5-10-2019
 */
public class User
{
    // instance variables of class User
    private int userId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
    private String role;
    private Boolean isLoggedIn;
    private Boolean isLockedOut;
    private int invalidLogoutAttempts;

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
        isLoggedIn = false;
    }

    /**
     * Parameterized constructor for objects of class User
     */
    public User(int newUserId, String newFirstName, String newLastName, 
    String newPhoneNo,String newEmail,String newPassword,String newRole, Boolean newIsLockedOut, int newInvalidLogoutAtt)
    {
        userId = newUserId;
        firstName = newFirstName;
        lastName = newLastName;
        phoneNumber = newPhoneNo;
        email = newEmail;
        password = newPassword;
        role = newRole;
        isLockedOut = newIsLockedOut;
        invalidLogoutAttempts = newInvalidLogoutAtt;
        isLoggedIn = false;
    }

    /**
     * Returns value of userId
     * @return
     */
    public int getUserId()
    {
        return userId;

    }

    /**
     * Returns value of First Name
     * @return
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * Returns value of Last name
     * @return
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * Returns value of phone number
     * @return
     */    
    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    /**
     * Returns value of email
     * @return
     */    
    public String getEmail()
    {
        return email;
    }

    /**
     * Returns value of password
     * @return
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * Returns value of role
     * @return
     */
    public String getRole()
    {
        return role;
    }

    /**
     * Returns value of loggen in status
     * @return
     */
    public Boolean getIsLoggedIn()
    {
        return isLoggedIn;
    }

    /**
     * Returns value of locked out status
     * @return
     */
    public Boolean getIsLockedOut()
    {
        return isLockedOut;
    }

    /**
     * Sets new value of userId
     * @param
     */
    public void setUserId(int newUserId)
    {
        userId = newUserId;

    }

    /**
     * Sets new value of first name
     * @param
     */
    public void setFirstName(String newFirstName)
    {
        firstName = newFirstName ;
    }

    /**
     * Sets new value of last name
     * @param
     */
    public void setLastName(String newLastName)
    {
        lastName = newLastName;
    }

    /**
     * Sets new value of phone number
     * @param
     */
    public void setPhoneNumber(String newPhone)
    {
        phoneNumber = newPhone;
    }

    /**
     * Sets new value of email
     * @param
     */
    public void setEmail(String newEmail)
    {
        email = newEmail;
    }

    /**
     * Sets new value of password
     * @param
     */
    public void setPassword(String newPass)
    {
        password = newPass;
    }

    /**
     * Sets new value of role
     * @param
     */
    public void setRole(String newRole)
    {
        role = newRole;
    }

    /**
     * Sets new value of logged in status
     * @param
     */    
    public void setIsLoggedIn(Boolean newlog)
    {
        isLoggedIn = newlog;
    }
    
    /**
     * Sets new value of locked out status
     * @param
     */
    public void setIsLockedOut(Boolean newLockedOut)
    {
        isLockedOut = newLockedOut;
    }

    /**
     * Returns value of invalidLogoutAttempts
     * @return
     */
    public int getInvalidLogoutAttempts() 
    {
        return invalidLogoutAttempts;
    }

    /**
     * Sets new value of invalidLogoutAttempts
     * @param
     */
    public void setInvalidLogoutAttempts(int invalidLogoutAttempts) 
    {
        this.invalidLogoutAttempts = invalidLogoutAttempts;
    }
    
    /**
     * Returns string of variable values.
     * @return
     */
    public String display()
    {
        return getUserId() + ". "+ getFirstName() + " " +getLastName() + " - " + (getIsLockedOut() == true ? "Locked" : "Unlocked");
    }
}
