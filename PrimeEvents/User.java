

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
    private Boolean isLoggedIn;
    private Boolean isLockedOut;
    
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
        isLoggedIn = false;
    }
    
    public int getUserId()
    {
        return userId;
       
    }
    
    public String getFirstName()
    {
        return firstName;
    }
    
    public String getLastName()
    {
        return lastName;
    }
    
    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public String getRole()
    {
        return role;
    }
    
    public Boolean getIsLoggedIn()
    {
        return isLoggedIn;
    }
    
    public Boolean getIsLockedOut()
    {
        return isLockedOut;
    }
    
    public void setUserId(int newUserId)
    {
        userId = newUserId;
       
    }
    
    public void setFirstName(String newFirstName)
    {
        firstName = newFirstName ;
    }
    
    public void setLastName(String newLastName)
    {
        lastName = newLastName;
    }
    
    public void setPhoneNumber(String newPhone)
    {
        phoneNumber = newPhone;
    }
    
    public void setEmail(String newEmail)
    {
        email = newEmail;
    }
    
    public void setPassword(String newPass)
    {
        password = newPass;
    }
    
    public void setRole(String newRole)
    {
        role = newRole;
    }
    
    public void setIsLoggedIn(Boolean newlog)
    {
        isLoggedIn = newlog;
    }
    
    public void setIsLockedOut(Boolean newLockedOut)
    {
        isLockedOut = newLockedOut;
    }
}
