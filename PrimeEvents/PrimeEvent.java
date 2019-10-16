import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException; 
/**
 * Write a description of class PrimeEvent here.
 *
 * @author (your name)
 * @version (a version number or a date)
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
     * @return
     */
    public EventController getEvent() {
        return event;
    }

    /**
     * Sets new value of event
     * @param
     */
    public void setEvent(EventController event) {
        this.event = event;
    }

    public void start()
    {
        showMenu();
    }

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

    private void displayRegistration()
    {
        displayHeader("REGISTRATION");
        getEvent().fetchUsersData();
        boolean isValid = false;
        while(!isValid)
        {
            switch(acceptIntegerInput("1. Customer\n2. Owner\nEnter your choice:"))
            {
                case 1:
                isValid = true;
                String fname = acceptStringInput("Enter your first name");
                String lname = acceptStringInput("Enter your last name");
                String dob = acceptStringInput("Enter your date of birth (dd/MM/yyyy)");
                String phone = acceptStringInput("Enter your phone no");
                String isVet = acceptStringInput("Are you a veteran (Y/N)");
                String email = acceptStringInput("Enter your email");
                String password = acceptStringInput("Enter your password");
                getEvent().addUser(fname,lname,phone,email,password,"CUSTOMER");
                System.out.println(fname + ", you've been successfully registered to Prime events. Kindly login to continue..");
                displayLogin();
                break;
                case 2:
                isValid = true;
                String firstname = acceptStringInput("Enter your first name");
                String lastname = acceptStringInput("Enter your last name");
                String phone1 = acceptStringInput("Enter your phone no");
                String email1 = acceptStringInput("Enter your email");
                String password1 = acceptStringInput("Enter your password");
                getEvent().addUser(firstname,lastname,phone1,email1,password1,"OWNER");
                System.out.println(firstname + ", you've been successfully registered to Prime events as an Owner. Kindly login to continue..");
                displayLogin();
                break;
                default:
                System.out.println("Invalid choice. Please try again");
            }
        }
    }

    private void displayLogin()
    {
        displayHeader("LOGIN");
        getEvent().fetchUsersData();
        String email = acceptStringInput("Enter your email");
        String password = acceptStringInput("Enter your password");
        if(getEvent().isValidCredentials(email,password))
        {
            System.out.println("Login Successful!!");
            displayHome();
        }
        else
        {
            displayLogin();
        }
    }

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
                    //manageBooking(); 
                    break;
                    case 4: isValid = true;
                    //showReviewHall(); 
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
                    //showManageHalls(); 
                    break;
                    case 2: isValid = true; break;
                    case 3: isValid = true; break;
                    case 4: isValid = true; break;
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
            displayHeader("HOME - ADMIN");
            boolean isValid = false;
            while(!isValid)
            {
                int input = acceptIntegerInput("1. Manage Users\n2. Manage Discounts\n0. Logout\nEnter your choice:");
                switch(input)
                {
                    case 1: isValid = true; 
                    //showManageHalls(); 
                    break;
                    case 2: isValid = true; break;
                    case 0: isValid = true; 
                    displayLogout(); 
                    break; 
                    default:
                    System.out.println("Invalid choice. Please try again");
                }
            }
        }
    }

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

    private void promptForKey()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Press any key to try again");
        sc.nextLine();   
    }

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

            }
        }
        int noOfAttendees = acceptIntegerInput("Enter total number of attendees");
        String eventType = acceptStringInput("Enter your event type (Wedding ceremony, Wedding reception, Birthday, Anniversary)");
        char requiresCatering = acceptStringInput("Do you require Catering by us(y/n):").toLowerCase().charAt(0);
        String specialReq = acceptStringInput("Any special requirements:");
        Double finalPrice = getEvent().calculatePrice();
        System.out.println("Total Price: " + finalPrice);
        getEvent().requestQuotation(id, startEventDate,endEventDate,noOfAttendees,eventType, requiresCatering,specialReq,finalPrice);
        System.out.println("Your quotation request has been sent to the owner. Once the owner responds, you can use the View Quotation Responses option to view it!");
        displayAllHalls("","");
    }

    private boolean isDateAfterToday(Date inputDate)
    {
        Date currentDate = new Date();      
        if(inputDate.after(currentDate)){
            return true;
        }else
            return false;
    }

    private void displayHallReviews(int hallID)
    {
        displayHeader("VIEW REVIEWS");
        String hallReviews = getEvent().getHallReviews(hallID);
        if(hallReviews != "")
        {
            //System.out.println(viewableHall.displayShort());
            System.out.println("\nREVIEWS:\n");
            System.out.println(hallReviews);
        }
        else
        {
            System.out.println("There are no reviews currently for the hall");
        }
        boolean isValid = false;
        System.out.println("\nOptions:");
        while(!isValid)
        {
            char goBack = acceptStringInput("\nB. Go Back\nEnter your choice:").toLowerCase().charAt(0);
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

    private void displayQuotationResponse(int customerID)
    {
        displayHeader("QUOTATION RESPONSES");
        String quotationResponse = getEvent().getQuotationResponse(customerID);
        if(quotationResponse != "")
        {
            System.out.println(quotationResponse);
        }
        else
        {
            System.out.println("You have not made any quotations yet");
        }
        System.out.println();
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

    private void displayQuotationDetail(int userID, int quotationID)
    {
        displayHeader("QUOTATION DETAILS");
        String quotationResponse = getEvent().getQuotationDetails(userID, quotationID);
        if(quotationResponse != "")
        {
            System.out.println(quotationResponse);
        }
        else
        {
            System.out.println("Invalid quotation ID");
        }
        boolean isValid = false;
        System.out.println("\nOptions:");
        if(getEvent().getUserRole(userID) == "CUSTOMER")
        {
            while(!isValid)
            {
                char choice = acceptStringInput("1. Pay deposit\nB. Go Back to Quotation Responses\nEnter your choice:").charAt(0);
                switch(choice)
                {
                    case '1': isValid = true; displayPayDeposit(userID, quotationID); break;
                    case 'b': isValid = true; displayQuotationResponse(userID); break;
                    default:
                    System.out.println("Invalid choice. Please try again!");
                }
            }
        }
        else
        {
            while(!isValid)
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
        }
    }

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

    // to update
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

    private void displayQuotationRequests(int ownerID)
    {
        displayHeader("QUOTATION REQUESTS");
        String quotationRequests = getEvent().getQuotationRequests(ownerID);
        if(quotationRequests != "")
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

    private void displayPayments()
    {

    }

    private void displayPaymentInfo()
    {

    }

    private void displayBookings()
    {

    }

    public void askForConfirmation()
    {

    }

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

    private void displayHeader(String header)
    {
        System.out.println("\n***********************************");
        System.out.println("           " + header + "            ");
        System.out.println("***********************************\n");   
    }    
}
