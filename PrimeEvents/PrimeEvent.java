import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException; 
/**
 * PrimeEvent is the boundary class for the system.
 * It contains the start method which is used to run it. It also has private methods which are used internally.
 * The main functionality is to display to the screen and validate inputs.
 * It communicates directly with the EventController class.
 * @author Rohan Nischal,Guanting Chen,Swathi Jadhav
 * @version 19-10-2019
 */
public class PrimeEvent
{
    private EventController event;
    /**
     * Constructor for objects of class PrimeEvent
     */
    public PrimeEvent()
    {
        event = new EventController();
    }

    /**
     * Returns value of event
     * @return EventController object
     */
    public EventController getEvent() {
        return event;
    }

    /**
     * Sets new value of event
     * @param EventController object
     */
    public void setEvent(EventController event) {
        this.event = event;
    }

    /**
     * Method start
     * This method is used to run the system. 
     */
    public void start()
    {
        showMenu();
    }

    /**
     * Method showMenu
     * This method displays the menu on the screen and prompts for input from the user in order to navigate to a screen
     */
    private void showMenu()
    {
        displayHeader("PRIME EVENTS");
        boolean isValid = false;
        while(!isValid)
        {
            int input = acceptIntegerInput("1. Login\n2. Registration\n3. Exit\nEnter your choice:");
            switch(input)
            {
                case 1: 
                isValid = true;
                displayLogin();
                break;
                case 2: 
                isValid = true;
                displayRegistration();
                break;
                case 3:
                isValid = true;
                System.out.println("Exiting Prime Events. Bye!!"); 
                System.exit(0);
                break;
                default:
                System.out.println("Invalid choice. Please try again!\n");
            }
        }
    }

    /**
     * Method displayRegistration
     * This method is used to display and validate the registration process
     */
    private void displayRegistration()
    {
        try
        {
            displayHeader("REGISTRATION");
            getEvent().fetchUsersData();
            boolean isValid = false;
            String regexName = "(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$";
            String regexDate = "^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])[0-9][0-9]$";
            String regexPhoneNumber = "^[+]{1}[0-9]{11}$";
            String regexEmail = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
            String regexPassword = "^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$";
            while(!isValid)
            {
                switch(acceptIntegerInput("1. Customer\n2. Owner\n0. Go Back\nEnter your choice:"))
                {
                    case 1:
                    isValid = true;
                    String fname = acceptStringInput("Enter your first name");
                    while(fname.matches(regexName) != true){
                        System.out.println("Please enter a valid first name");
                        fname = acceptStringInput("Enter your first name");                                    
                    }
                    String lname = acceptStringInput("Enter your last name");
                    while(lname.matches(regexName) != true){
                        System.out.println("Please enter a valid last name");
                        lname = acceptStringInput("Enter your last name");                                    
                    }
                    String dob = acceptStringInput("Enter your date of birth (dd/MM/yyyy)");
                    while(dob.matches(regexDate) != true || !isDateBeforeToday( new SimpleDateFormat("dd/MM/yyyy").parse(dob))){
                        if(!isDateBeforeToday( new SimpleDateFormat("dd/MM/yyyy").parse(dob)))
                        {
                            System.out.println("Entered date should be before today's date");
                        }
                        else
                        {
                            System.out.println("Please enter a valid date in the format dd/mm/yyyy");
                        }
                        dob = acceptStringInput("Enter your date of birth (dd/MM/yyyy)");                                    
                    }
                    String phone = acceptStringInput("Enter your phone no");
                    while(phone.matches(regexPhoneNumber) != true){
                        System.out.println("Please enter a valid phone number which is in the format +61*********");
                        phone = acceptStringInput("Enter your phone no");                                    
                    }
                    String isVet = acceptStringInput("Are you a veteran (Y/N)");
                    while(!(isVet.equalsIgnoreCase("Y")  || isVet.equalsIgnoreCase("N"))){
                        System.out.println("Please enter a valid veteran input");
                        isVet = acceptStringInput("Are you a veteran (Y/N)");                                    
                    }
                    String email = acceptStringInput("Enter your email");
                    while(email.matches(regexEmail) != true || getEvent().isEmailUsed(email)){
                        if(getEvent().isEmailUsed(email))
                        {
                            System.out.println("Email is already registered with Prime Events");
                        }
                        else
                        {
                            System.out.println("Please enter a valid email address");
                        }
                        email = acceptStringInput("Enter your email"); 
                    }
                    String password = acceptStringInput("Enter your password");
                    while(password.matches(regexPassword) != true){
                        System.out.println("Password should contain at least 8 chars including: \nAt least one digit \nAt least one lower alpha char and one upper alpha char \nContains at least one special character ");
                        password = acceptStringInput("Enter your password");
                    }
                    getEvent().addUser(fname,lname,phone,email,password,"CUSTOMER");
                    System.out.println(fname + ", you've been successfully registered to Prime events. Kindly login to continue..");
                    displayLogin();
                    break;

                    case 2:
                    isValid = true;
                    String firstname = acceptStringInput("Enter your first name");
                    while(firstname.matches(regexName) != true){
                        System.out.println("Please enter a valid first name");
                        firstname = acceptStringInput("Enter your first name");                                    
                    }
                    String lastname = acceptStringInput("Enter your last name");
                    while(lastname.matches(regexName) != true){
                        System.out.println("Please enter a valid last name");
                        lastname = acceptStringInput("Enter your last name");                                    
                    }
                    String phone1 = acceptStringInput("Enter your phone no");
                    while(phone1.matches(regexPhoneNumber) != true){
                        System.out.println("Please enter a valid phone number which is in the format +61*********");
                        phone1 = acceptStringInput("Enter your phone no");                                    
                    }
                    String email1 = acceptStringInput("Enter your email");
                    while(email1.matches(regexEmail) != true || getEvent().isEmailUsed(email1)){
                        if(getEvent().isEmailUsed(email1))
                        {
                            System.out.println("Email is already registered with Prime Events");
                        }
                        else
                        {
                            System.out.println("Please enter a valid email address");
                        }
                        email1 = acceptStringInput("Enter your email"); 
                    }
                    while(getEvent().isEmailUsed(email1))
                    {
                        System.out.println("Email address is also registered with Prime Events");
                        email = acceptStringInput("Enter your email"); 
                    }
                    String password1 = acceptStringInput("Enter your password");
                    while(password1.matches(regexPassword) != true){
                        System.out.println("Your password is not valid it should contain minimum eight characters, at least one uppercase letter, one lowercase letter and one number:");
                        password1 = acceptStringInput("Enter your password");
                    }
                    getEvent().addUser(firstname,lastname,phone1,email1,password1,"OWNER");
                    System.out.println(firstname + ", you've been successfully registered to Prime events as an Owner. Kindly login to continue..");
                    displayLogin();
                    break;
                    case 0:
                    showMenu();
                    break;
                    default:
                    System.out.println("Invalid choice. Please try again");
                }
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error");
        }
    }

    /**
     * Method displayLogin
     * This method is used to prompt for and validate login credentials
     */
    private void displayLogin()
    {
        displayHeader("LOGIN");
        getEvent().fetchUsersData();

        String email = acceptStringInput("Enter your email");
        String regexEmail = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        while(email.matches(regexEmail) != true){
            System.out.println("Please enter valid email address");
            email = acceptStringInput("Enter your email");
        }
        String password = acceptStringInput("Enter your password");
        if(getEvent().isValidCredentials(email,password))
        {
            System.out.println("Login Successful!! Welcome to Prime Events");
            displayHome();
        }
        else
        {
            char choice = acceptStringInput("Do you wish to continue with login(y/n)?").toLowerCase().charAt(0);
            if(choice == 'y')
            {
                displayLogin();
            }
            else
            {
                showMenu();
            }
        }

    }

    /**
     * Method displayHome
     * This method displays the home screen according to the user's role on the screen and prompts for input from the user in order to navigate to a screen
     */
    private void displayHome()
    {
        getEvent().initializeData();
        int userId = getEvent().getUsers().get(0).getUserId();
        if(getEvent().getUsers().get(0).getRole().equalsIgnoreCase("CUSTOMER"))
        {
            displayHeader("HOME - CUSTOMER");
            boolean isValid = false;
            while(!isValid)
            {
                int input = acceptIntegerInput("1. Search Halls\n2. View Quotation Responses\n3. Manage Bookings\n4. Review Halls\n0. Logout\nEnter your choice:");
                switch(input)
                {
                    case 1: 
                    isValid = true; 
                    displayAllHalls("","");
                    break;
                    case 2: isValid = true;
                    displayQuotationResponse(userId); 
                    break;
                    case 3: isValid = true;
                    displayUnderImplementation(); 
                    break;
                    case 4: isValid = true;
                    displayUnderImplementation(); 
                    break;
                    case 0: isValid = true;
                    displayLogout(); 
                    break;
                    default:
                    System.out.println("Invalid choice. Please try again");
                }
            }
        }
        else if(getEvent().getUsers().get(0).getRole().equalsIgnoreCase("OWNER"))
        {
            displayHeader("HOME - OWNER");
            boolean isValid = false;
            while(!isValid)
            {
                int input = acceptIntegerInput("1. Manage Halls\n2. Manage Bookings\n3. Manage Payments\n4. Manage Discounts\n5. View Quotation Requests\n0. Logout\nEnter your choice:");
                switch(input)
                {
                    case 1: isValid = true; 
                    displayUnderImplementation(); 
                    break;
                    case 2: isValid = true; displayUnderImplementation(); break;
                    case 3: isValid = true; displayPayments(userId); break;
                    case 4: isValid = true; displayUnderImplementation(); break;
                    case 5: isValid = true; displayQuotationRequests(userId); break;
                    case 0: isValid = true; 
                    displayLogout(); 
                    break; 
                    default:
                    System.out.println("Invalid choice. Please try again");
                }
            }
        }
        else if(getEvent().getUsers().get(0).getRole().equalsIgnoreCase("ADMINISTRATOR"))
        {            
            boolean isValid = false;
            while(!isValid)
            {
                displayHeader("HOME - ADMIN");
                int input = acceptIntegerInput("1. Manage Users\n2. Manage Discounts\n0. Logout\nEnter your choice:");
                switch(input)
                {
                    case 1: 
                    isValid = true; 
                    displayManageUsers();              
                    break;
                    case 2: 
                    isValid = true; 
                    displayUnderImplementation();
                    break;
                    case 0: 
                    isValid = true; 
                    displayLogout(); 
                    break; 
                    default:
                    System.out.println("Invalid choice. Please try again");
                }
            }
        }
    }

    /**
     * Method displayManageUsers
     * This method displays the manage users option for the administrator.
     * It displays the entire list of users and prompts to select one.
     */
    private void displayManageUsers()
    {
        displayHeader("MANAGE USERS");
        System.out.println(getEvent().getAllUsers());
        System.out.println("\nOptions:");
        boolean isValid = false;
        while(!isValid)
        {  
            String manageUserOption = acceptStringInput("Enter number to select the user \nB. Go Back\nEnter your choice:");    

            if(isInteger(manageUserOption) && Integer.parseInt(manageUserOption) != 0)
            {
                int selectedID = Integer.parseInt(manageUserOption);
                if(!getEvent().getUserById(selectedID).equals(""))
                {
                    isValid = true;
                    displayUserDetails(selectedID);
                }
                else
                {
                    System.out.println("\nInvalid number entered. Please try again!");
                    promptForKey();
                    displayManageUsers();
                }

            }
            else
            {
                char optionValue = manageUserOption.toLowerCase().charAt(0);
                switch(optionValue)
                {
                    case 'b': isValid = true; displayHome(); break;
                    case '0': isValid = true; displayLogout(); break;
                    default:
                    System.out.println("\nInvalid choice. Please try again");
                    promptForKey();
                }
            }
        }  
    }

    /**
     * Method displayUserDetails
     * This method is used to print a single user's details on the screen.
     * Also, it gives an option to lock or unlock user based on the user's locked out state.
     * @param userID A parameter
     */
    private void displayUserDetails(int userID)
    {
        displayHeader("SELECT USER");
        String userDetail = getEvent().getUserById(userID);
        if(!userDetail.equals(""))
        {
            System.out.println(userDetail);
            if(!getEvent().isUserLocked(userID))
            {
                switch(acceptStringInput("Do you wish to LOCK this user? (Y/N)").charAt(0))
                {
                    case 'y':
                    getEvent().updateUserLockStatus(userID, true);
                    System.out.println("User Locked successfully!!");                
                    break;
                    case 'n':
                    System.out.println("User was not Locked!!");           
                    break;           
                }
            }
            else
            {
                switch(acceptStringInput("Do you wish to UNLOCK this user? (Y/N)").charAt(0))
                {
                    case 'y':
                    getEvent().updateUserLockStatus(userID, false);
                    System.out.println("User unlocked successfully!!");                        
                    break;
                    case 'n':
                    System.out.println("User is still Locked!!");           
                    break;           
                }
            }   
        }
        else
        {
            System.out.println("Invalid id entered. Please try again");
        }  
        displayManageUsers();
    }

    /**
     * Method displayAllHalls
     * This method displays all the filtered halls for search hall feature. It also provides options to navigate to view hall and filter hall.
     * @param suburbName the name of the suburb to filter with
     * @param eventType the event type to filter with
     */
    private void displayAllHalls(String suburbName, String eventType)
    {
        displayHeader("SEARCH HALLS");
        System.out.println(getEvent().getHallData(suburbName,eventType));
        System.out.println("Options:");
        boolean validSelection = false;
        while(!validSelection)
        {
            String searchInput = acceptStringInput("Enter number to view hall details"+
                    "\n----OR----\nF. Apply a Filter\nR. Reset Filter\nB. Go Back\n0. Logout"+
                    "\nEnter your choice:");

            if(isInteger(searchInput) && Integer.parseInt(searchInput) != 0)
            {
                int selectedID = Integer.parseInt(searchInput);
                if(getEvent().doesHallExist(selectedID))
                {
                    validSelection = true;
                    displayHallDetails(selectedID);
                }
                else
                {
                    System.out.println("\nInvalid Hall ID entered. Please try again!");
                    promptForKey();
                    displayAllHalls(suburbName,eventType);
                }

            }
            else
            {
                char selectInput = searchInput.toLowerCase().charAt(0);
                switch(selectInput)
                {
                    case 'f': validSelection = true; displayFilterHalls(); break;
                    case 'r': validSelection = true; displayAllHalls("",""); break;
                    case 'b': validSelection = true; displayHome(); break;
                    case '0': validSelection = true; displayLogout(); break;
                    default:
                    System.out.println("\nInvalid choice. Please try again");
                    promptForKey();
                }
            }
        }

    }

    /**
     * Method promptForKey
     * This method simply prompts for the enter key
     */
    private void promptForKey()
    {
        Scanner sc = new Scanner(System.in);
        sc.nextLine();   
    }

    /**
     * Method displayFilterHalls
     * This method is used to display the filter hall option
     * It gives the options to filter by suburb or event type.
     */
    private void displayFilterHalls()
    {
        displayHeader("FILTER HALLS");
        boolean isValid = false;
        String searchSuburb = "";
        String searchEvent = "";
        System.out.println("Options:");
        while(!isValid)
        {
            int filterInp = acceptIntegerInput("1. Filter by Suburb\n2. Filter by Event Type\nEnter your choice:");
            switch(filterInp)
            {
                case 1:
                isValid = true;
                searchSuburb = acceptStringInput("Enter the suburb name to search");
                break;
                case 2:
                isValid = true;
                searchEvent = acceptStringInput("Enter the event type to search");
                break;
                default:
                System.out.println("Invalid choice. Please try again!");
            }
        }
        getEvent().sortUserByLoginStatus();
        displayAllHalls(searchSuburb, searchEvent);
    }

    /**
     * Method displayHallDetails
     * This method is used to display a particular hall's details.
     * It gives options to navigate to request quotation and view reviews
     * @param hallID the id of a hall
     */
    private void displayHallDetails(int hallID)
    {
        displayHeader("VIEW HALL");
        System.out.println(getEvent().getHallDetails(hallID));
        System.out.println("\nOptions:");
        boolean isValid = false;
        while(!isValid)
        {
            char input = acceptStringInput("Q. Request Quotation\nR. View Reviews\nB. Go Back\nEnter your choice:")
                .toLowerCase().charAt(0);
            switch(input)
            {
                case 'q': isValid = true; displayRequestForQuotation(hallID); break;
                case 'r': isValid = true; displayHallReviews(hallID); break;
                case 'b': isValid = true; displayAllHalls("",""); break;
                default:
                System.out.println("Invalid choice. Please try again!");
            }
        }

    }

    /**
     * Method displayRequestForQuotation
     * This method is used to display request quotation screen.
     * It prompts and validates for all the quotation details
     * @param id the selected hall id
     */
    private void displayRequestForQuotation(int id)
    {
        displayHeader("REQUEST QUOTATION");
        boolean isValid = false;
        Date startEventDate = new Date();
        while(!isValid)
        {
            String inpEventDate = acceptStringInput("Enter Event Start Date Time (dd/MM/yyyy HH:mm)");

            try
            {
                startEventDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(inpEventDate);
                if(isDateAfterToday(startEventDate))
                    isValid = true;
                else
                    System.out.println("Entered Datetime should be greater than current Datetime");
            }
            catch(ParseException ex)
            {
                System.out.println("Invalid Datetime format entered");
            }
        }
        isValid = false;
        Date endEventDate = new Date();
        while(!isValid)
        {
            String inpEndEventDate = acceptStringInput("Enter Event End Date Time (dd/MM/yyyy HH:mm)");

            try
            {
                endEventDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(inpEndEventDate);
                if(isDateAfterToday(endEventDate) && endEventDate.after(startEventDate))
                    isValid = true;
                else
                    System.out.println("Event end date should be greater than event start date");
            }
            catch(ParseException ex)
            {
                System.out.println("Invalid Datetime format entered");
            }
        }
        isValid = false;
        int noOfAttendees = 0;
        while(!isValid)
        {
            noOfAttendees = acceptIntegerInput("Enter total number of attendees");        
            if(getEvent().getHallById(id).getCapacity() >= noOfAttendees)
            {
                isValid = true;
            }
            else
            {
                System.out.println("Total number of atendees should be less than the hall's capacity");
            }
        }
        isValid = false;
        String eventType = "";
        while(!isValid)
        {
            eventType = acceptStringInput("Enter your event type (Wedding ceremony, Wedding reception, Birthday, Anniversary)");
            if(getEvent().getHallById(id).getEventTypes().contains(eventType.toLowerCase()))
            {
                isValid = true;
            }
            else
            {
                System.out.println("Invalid event type entered. Kindly choose from any of the available options");
            }
        }
        char requiresCatering = acceptStringInput("Do you require Catering by us(y/n):").toLowerCase().charAt(0);
        String specialReq = acceptStringInput("Any special requirements:");
        Double finalPrice = getEvent().calculatePrice(startEventDate,endEventDate,noOfAttendees,eventType, requiresCatering,specialReq,id);
        System.out.println("Total Price: " + finalPrice);
        char input = acceptStringInput("Do you confirm this quotation with price "+ finalPrice + "? (y/n)").toLowerCase().charAt(0);
        isValid = false;
        while(!isValid)
        {
            switch(input)
            {
                case 'y':
                isValid = true;
                getEvent().requestQuotation(id, startEventDate,endEventDate,noOfAttendees,eventType, requiresCatering,specialReq,finalPrice);
                System.out.println("Your quotation request has been sent to the owner. Once the owner responds, you can use the View Quotation Responses option to view it! Press any key to go to search halls");
                break;
                case 'n':
                isValid = true;
                System.out.println("Quotation request not confirmed. Going back to View Hall");
                displayHallDetails(id);
                break;
                default:
                System.out.println("Invalid choice. Please try again");
            }
        }
        promptForKey();
        displayAllHalls("","");
    }

    /**
     * Method isDateAfterToday
     * this method checks if a date is after the current date
     * @param inputDate A date to compare
     * @return true if after the current date or false if before
     */
    private boolean isDateAfterToday(Date inputDate)
    {
        Date currentDate = new Date();      
        if(inputDate.after(currentDate)){
            return true;
        }else
            return false;
    }

    /**
     * Method isDateBeforeToday
     * this method checks if a date is before the current date
     * @param inputDate A date to compare
     * @return true if before the current date or false if after
     */
    private boolean isDateBeforeToday(Date inputDate)
    {
        Date currentDate = new Date();      
        if(inputDate.before(currentDate)){
            return true;
        }else
            return false;
    }

    /**
     * Method displayHallReviews
     * This method is used to display the view reviews screen
     * @param hallID the selected hall id
     */
    private void displayHallReviews(int hallID)
    {
        displayHeader("VIEW REVIEWS");
        String hallReviews = getEvent().getHallReviews(hallID);
        if(!hallReviews.equals(""))
        {
            System.out.println(getEvent().getHallById(hallID).displayShort());
            System.out.println("\nREVIEWS:\n");
            System.out.println(hallReviews);
            boolean isValid = false;
            System.out.println("\nOptions:");
            while(!isValid)
            {
                char goBack = acceptStringInput("B. Go Back\nEnter your choice:").toLowerCase().charAt(0);
                switch(goBack)
                {
                    case 'b': 
                    isValid = true; 
                    displayHallDetails(hallID);
                    break;
                    default:
                    System.out.println("Invalid choice. Please try again!");
                }
            }
        }
        else
        {
            System.out.println("There are no reviews currently for the hall! Press any key to go back");
            promptForKey();
            displayHallDetails(hallID);
        }

    }

    /**
     * Method displayQuotationResponse
     * this method is used to display the quotation response screen.
     * @param customerID the current customer id
     */
    private void displayQuotationResponse(int customerID)
    {
        displayHeader("QUOTATION RESPONSES");
        String quotationResponse = getEvent().getQuotationResponse(customerID);
        if(!quotationResponse.equals(""))
        {
            System.out.println(quotationResponse);
            boolean isValid = false;
            System.out.println("\nOptions:");
            while(!isValid)
            {
                String choice = acceptStringInput("\nEnter number to view accepted quotation and pay deposit"+
                        "\n----OR-----\nB. Go back\nEnter your choice:").toLowerCase();
                if(isInteger(choice) && Integer.parseInt(choice) != 0)
                {
                    isValid = true;
                    displayQuotationDetail(customerID ,Integer.parseInt(choice));
                }
                else
                {
                    char selectInput = choice.toLowerCase().charAt(0);
                    switch(selectInput)
                    {
                        case 'b': 
                        isValid = true; 
                        displayHome(); 
                        break;
                        default:
                        System.out.println("Invalid choice. Please try again!");
                    }
                }
            }
        }
        else
        {
            System.out.println("You have not made any quotations yet! Press any key to go back");
            promptForKey();
            displayHome();
        }        
    }

    /**
     * Method displayQuotationDetail
     * this method is used to display a particular quotation's detail.
     * For the customer, it prompts to pay the deposit if the quotation has been accepted.
     * For the owner, it prompts to accept or reject a quotation.
     * @param userID the current user's id
     * @param quotationID the selected quotation id
     */
    private void displayQuotationDetail(int userID, int quotationID)
    {
        displayHeader("QUOTATION DETAILS");
        String quotationResponse = getEvent().getQuotationDetails(userID, quotationID);
        if(!quotationResponse.equals(""))
        {
            System.out.println(quotationResponse);
            boolean isValid = false;

            if(getEvent().getUserRole(userID).equals("CUSTOMER"))
            {
                while(!isValid)
                {
                    if(getEvent().isBookingDepositPaid(userID, quotationID))
                    {
                        System.out.println("You have already paid the deposit for the booking. Press any key to go back");
                        promptForKey();
                        displayQuotationResponse(userID);
                    }
                    else if(getEvent().isQuotationAccepted(quotationID))
                    {
                        System.out.println("\nOptions:");
                        char choice = acceptStringInput("1. Pay deposit\nB. Go Back to Quotation Responses\nEnter your choice:").charAt(0);
                        switch(choice)
                        {
                            case '1': isValid = true; displayPayDeposit(userID, quotationID); break;
                            case 'b': isValid = true; displayQuotationResponse(userID); break;
                            default:
                            System.out.println("Invalid choice. Please try again!");
                        }
                    }
                    else
                    {
                        System.out.println("The quotation has not been accepted yet! Kindly wait for the hall owner to accept it and then proceed with the deposit. Press any key to go back");
                        promptForKey();
                        displayQuotationResponse(userID);
                    }
                }
            }
            else
            {

                while(!isValid)
                {
                    if(!getEvent().isQuotationAccepted(quotationID))
                    {
                        char choice = acceptStringInput("1. Accept Quotation\n2. Reject Quotation\nB. Go Back\nEnter your choice:").charAt(0);
                        switch(choice)
                        {
                            case '1': isValid = true; 
                            char accept = acceptStringInput("Do you want to accept the quotation(y/n)?").charAt(0);
                            switch(accept)
                            {
                                case 'y':
                                isValid = true;
                                getEvent().updateQuotationStatus(userID, quotationID, "ACCEPTED");
                                System.out.println("Quotation accepted!! The requester can now book the hall");
                                displayQuotationRequests(userID);
                                break;
                                case 'n':
                                isValid = true;
                                System.out.println("Action cancelled!! Going back to Quotation details");
                                displayQuotationDetail(userID, quotationID);
                                break;
                                default:
                                System.out.println("Invalid choice. Please try again!");
                            }
                            break;
                            case '2': isValid = true;
                            char input = acceptStringInput("Do you want to reject the quotation(y/n)?").charAt(0);
                            switch(input)
                            {
                                case 'y':
                                isValid = true;
                                getEvent().updateQuotationStatus(userID, quotationID, "REJECTED");
                                displayQuotationRequests(userID);
                                System.out.println("Quotation rejected!!");
                                break;
                                case 'n':
                                isValid = true;
                                System.out.println("Action cancelled!! Going back to Quotation details");
                                displayQuotationDetail(userID, quotationID);
                                break;
                                default:
                                System.out.println("Invalid choice. Please try again!");
                            }
                            break;
                            case 'b': isValid = true; 
                            displayQuotationRequests(userID); break;
                            default:
                            System.out.println("Invalid choice. Please try again!");
                        }
                    }
                    else
                    {
                        System.out.println("The quotation has already been accepted/rejected. Press any key to go back");
                        promptForKey();
                        displayQuotationRequests(userID);
                    }
                }
            }
        }
        else
        {
            System.out.println("Invalid quotation ID! Press any key to go back");
            promptForKey();
            if(getEvent().getUserRole(userID).equals("CUSTOMER"))
            {
                displayQuotationResponse(userID);
            }
            else
            {
                displayQuotationRequests(userID); 
            }
        }

    }

    /**
     * Method displayPayDeposit
     * This method displays the pay deposit screen.
     * It prompts to pay by cash or card and interacts with EventController to make a booking
     * @param customerID the current customer's id
     * @param quotationID the selected quotation id
     */
    private void displayPayDeposit(int customerID, int quotationID)
    {
        displayHeader("PAY DEPOSIT");
        System.out.println(getEvent().getQuotationDetails(customerID, quotationID));
        boolean isValid = false;
        System.out.println("\nOptions:");
        while(!isValid)
        {
            char choice = acceptStringInput("1. Pay by Cash\n2. Pay by Card\nB. Go back\nEnter your choice:").charAt(0);
            switch(choice)
            {
                case '1':
                char accept = acceptStringInput("Do you accept the mentioned charges for the booking by cash(y/n)?").charAt(0);
                switch(accept)
                {
                    case 'y':
                    isValid = true;
                    getEvent().makeBooking(customerID, quotationID, "CASH");
                    System.out.println("Pay deposit accepted!! Booking successful.");
                    displayReceipt(customerID, quotationID);
                    break;
                    case 'n':
                    isValid = true;
                    System.out.println("Pay deposit cancelled!! Going back to Quotation details");
                    displayQuotationDetail(customerID, quotationID);
                    break;
                    default:
                    System.out.println("Invalid choice. Please try again!");
                }
                break;
                case '2':
                String cardNumber = acceptStringInput("Enter the card number");
                String cardExpiry = acceptStringInput("Enter the card expiry date");
                String cvv = acceptStringInput("Enter the 3 digit CVV");
                char cardAccept = acceptStringInput("Do you accept the mentioned charges for the booking by card(y/n)?").charAt(0);
                switch(cardAccept)
                {
                    case 'y':
                    isValid = true;
                    getEvent().makeBooking(customerID, quotationID, "CASH");
                    System.out.println("Pay deposit accepted!! Booking successful.");
                    displayReceipt(customerID, quotationID);
                    break;
                    case 'n':
                    isValid = true;
                    System.out.println("Pay deposit cancelled!! Going back to Quotation details");
                    displayQuotationDetail(customerID, quotationID);
                    break;
                    default:
                    System.out.println("Invalid choice. Please try again!");
                }
                break;
                case 'b':
                isValid = true;
                displayQuotationDetail(customerID, quotationID);
                break;
                default:
                System.out.println("Invalid choice. Please try again!");
            }
        }
    }

    /**
     * Method displayReceipt
     * This method is used to display the receipt.
     * @param customerID The current customer's id
     * @param quotationID The selected quotation id
     */
    private void displayReceipt(int customerID, int quotationID)
    {
        displayHeader("VIEW RECEIPT");
        String receipt = getEvent().getReceipt(customerID, quotationID);
        System.out.println(receipt);
        System.out.println("\nOptions:");
        boolean isValid = false;
        while(!isValid)
        {
            char choice = acceptStringInput("B. Go back\nEnter your choice:").charAt(0);
            switch(choice)
            {
                case 'b': 
                isValid = true; 
                displayQuotationResponse(customerID); break;
                default:
                System.out.println("Invalid choice. Please try again!");
            }
        }
    }

    /**
     * Method displayQuotationRequests
     * This method displays all the quotation requests.
     * It also prompts to view a particular quotation's details
     * @param ownerID the current owner's id
     */
    private void displayQuotationRequests(int ownerID)
    {
        displayHeader("QUOTATION REQUESTS");
        String quotationRequests = getEvent().getQuotationRequests(ownerID);
        if(!quotationRequests.equals(""))
        {
            System.out.println(quotationRequests);
        }
        else
        {
            System.out.println("There are no quotation requests for any of your halls yet");
        }
        boolean isValid = false;
        System.out.println("\nOptions:");
        while(!isValid)
        {
            String choice = acceptStringInput("Enter number to view quotation details"+
                    "\n----OR-----\nB. Go back\nEnter your choice:").toLowerCase();
            if(isInteger(choice) && Integer.parseInt(choice) != 0)
            {
                isValid = true;
                displayQuotationDetail(ownerID ,Integer.parseInt(choice));
            }
            else
            {
                char selectInput = choice.toLowerCase().charAt(0);
                switch(selectInput)
                {
                    case 'b': 
                    isValid = true; 
                    displayHome(); 
                    break;
                    default:
                    System.out.println("Invalid choice. Please try again!");
                }
            }
        }
    }

    /**
     * Method displayPayments
     * This method is used to display the manage payments option.
     * It also prompts to select a particular payment
     * @param ownerID the current owner's id
     */
    private void displayPayments(int ownerID)
    {
        displayHeader("MANAGE PAYMENTS");
        String payments = getEvent().getPaymentsForOwner();
        if(payments.trim() != "")
        {
            System.out.println(payments);
        }
        else
        {
            System.out.println("There are no payments done for any of your halls yet");
        }
        boolean isValid = false;
        System.out.println("\nOptions:");
        while(!isValid)
        {
            String choice = acceptStringInput("Enter number to view payment details"+
                    "\n----OR-----\nB. Go back\nEnter your choice:").toLowerCase();
            if(isInteger(choice) && Integer.parseInt(choice) != 0)
            {
                isValid = true;
                displayPaymentDetail(ownerID ,Integer.parseInt(choice));
            }
            else
            {
                char selectInput = choice.toLowerCase().charAt(0);
                switch(selectInput)
                {
                    case 'b': 
                    isValid = true; 
                    displayHome(); 
                    break;
                    default:
                    System.out.println("Invalid choice. Please try again!");
                }
            }
        }
    }

    /**
     * Method displayPaymentDetail
     * This method is used to display a particular payment's detail.
     * It also prompts to accept and reject payment
     * @param userID the current user id
     * @param quotationID the selected quotation id
     */
    private void displayPaymentDetail(int userID, int quotationID)
    {
        displayHeader("PAYMENT DETAILS");
        String paymentDetail = getEvent().getPaymentDetails(quotationID);
        if(!paymentDetail.equals(""))
        {
            System.out.println(paymentDetail);
            boolean isValid = false;
            System.out.println("\nOptions:");
            while(!isValid)
            {
                char choice = acceptStringInput("1. Accept Payment\n2. Reject Payment\nB. Go Back\nEnter your choice:").charAt(0);
                switch(choice)
                {
                    case '1': isValid = true; 
                    char accept = acceptStringInput("Do you want to accept the payment(y/n)?").charAt(0);
                    switch(accept)
                    {
                        case 'y':
                        isValid = true;
                        getEvent().updatePaymentnStatus(quotationID, "ACCEPTED");
                        System.out.println("Payment accepted!!");
                        displayPayments(userID);
                        break;
                        case 'n':
                        isValid = true;
                        System.out.println("Action cancelled!! Going back to Payment details");
                        displayPaymentDetail(userID,quotationID);
                        break;
                        default:
                        System.out.println("Invalid choice. Please try again!");
                    }
                    break;
                    case '2': isValid = true;
                    char input = acceptStringInput("Do you want to reject the payment(y/n)?").charAt(0);
                    switch(input)
                    {
                        case 'y':
                        isValid = true;
                        getEvent().updatePaymentnStatus(quotationID, "REJECTED");
                        displayPayments(userID);
                        System.out.println("Quotation rejected!!");
                        break;
                        case 'n':
                        isValid = true;
                        System.out.println("Action cancelled!! Going back to Quotation details");
                        displayPaymentDetail(userID,quotationID);
                        break;
                        default:
                        System.out.println("Invalid choice. Please try again!");
                    }
                    break;
                    case 'b': isValid = true; 
                    displayPayments(userID); break;
                    default:
                    System.out.println("Invalid choice. Please try again!");
                }
            }
        }
        else
        {
            System.out.println("Invalid payment ID! Press any key to try again");
            promptForKey();
            displayPayments(userID);
        }
    }

    private void displayBookings()
    {

    }

    /**
     * Method displayLogout
     * This method displays the logout option and interacts with EventController to do the same.
     */
    public void displayLogout()
    {
        char input = acceptStringInput("Are you sure you want to log out(y/n)").toLowerCase().charAt(0);
        boolean isValid = false;
        while(!isValid)
        {
            switch(input)
            {
                case 'y':
                isValid = true;
                System.out.println("Successfully logged out!!");
                getEvent().doLogout();
                start();
                break;
                case 'n':
                isValid = true;
                displayHome();
                break;
                default:
                System.out.println("Invalid choice. Please try again");
            }
        }
    }

    public void displayUnderImplementation()
    {
        displayHeader("UNDER IMPLEMENTATION");
        System.out.println("This functionality is not a part of the additional functionalitites. Press enter to go back to home");
        promptForKey();
        displayHome();
    }

    /**
     * Method acceptStringInput
     * This method uses a scanner to trim and accept a string input (not empty)
     * @param displayMessage The message to display before prompting
     * @return The entered value by the user
     */
    private String acceptStringInput(String displayMessage)
    {
        Scanner console = new Scanner(System.in);
        System.out.println(displayMessage);
        String input = console.nextLine().trim();
        while(input.length() == 0)
        {
            System.out.println("Kindly enter a value");
            input = console.nextLine();
        }
        return input;
    }

    /**
     * Method acceptIntegerInput
     * This method uses a scanner to accept only an integer input
     * @param displayMessage The message to display before prompting
     * @return The entered int value by the user
     */
    private int acceptIntegerInput(String displayMessage)
    {
        Scanner console = new Scanner(System.in);
        System.out.println(displayMessage);
        while(!console.hasNextInt())
        {
            System.out.println("Invalid Input. Please try again!\n" + displayMessage);
            console.next();
        }
        return console.nextInt();
    }

    /**
     * Method isInteger
     * This method is used to check a string value can be parsed to an integer
     * @param s The string value
     * @return true if string could be an integer, false if not
     */
    private boolean isInteger(String s)
    {
        try 
        { 
            Integer.parseInt(s); 
        } 
        catch(NumberFormatException e) 
        { 
            return false; 
        }
        catch(NullPointerException e) 
        {
            return false;
        }
        return true;
    }

    /**
     * Method displayHeader
     * This method displays the header to the screen
     * @param header The screen name to display
     */
    private void displayHeader(String header)
    {
        System.out.println("\n***********************************");
        System.out.println("           " + header + "            ");
        System.out.println("***********************************\n");   
    }    
}
