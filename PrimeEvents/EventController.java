import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.Collections;
import java.util.Comparator;
/**
 * Write a description of class EventController here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class EventController
{
    private ArrayList<Hall> halls;
    private ArrayList<User> users;
    //private ArrayList<Owner> owners;
    //private ArrayList<Customer> customers;
    //private ArrayList<Admin> admins;

    /**
     * Constructor for objects of class EventController
     */
    public EventController()
    {
        users = new ArrayList<User>();
        halls = new ArrayList<Hall>();
    }

    public ArrayList<Hall> getHalls()
    {
        return halls;
    }

    public ArrayList<User> getUsers()
    {
        return users;
    }

    public int getMaxUserId()
    {
        if(getUsers().size() != 0)
        {
            Collections.sort(getUsers(), new Comparator<User>()
                {
                    public int compare(User u1, User u2) {
                        return u2.getUserId() - u1.getUserId();
                    }
                });

            return getUsers().get(0).getUserId();
        }
        else
        {
            return 1;
        }
    }

    public void sortUserByLoginStatus()
    {

        Collections.sort(getUsers(), new Comparator<User>()
            {
                public int compare(User u1, User u2) {
                    return Boolean.compare(u2.getIsLoggedIn(), u1.getIsLoggedIn());
                }
            });
    }

    public void addUser(String newFirstName, String newLastName, 
    String newPhoneNo,String newEmail,String newPassword,String newRole)
    {
        getUsers().add(new User(getMaxUserId() + 1,newFirstName,newLastName,newPhoneNo,newEmail,newPassword,newRole));
        writeToUsersFile("users.txt");
    }

    public void setUsers(ArrayList<User> newUsers)
    {
        users = newUsers;
    }

    public void setHalls(ArrayList<Hall> newHalls)
    {
        halls = newHalls;
    }    

    public void start()
    {
        displayHeader("PRIME EVENTS");
        showMenu();
    }

    private void readUsersFile()
    {
        String fileData = readFile("users.txt");
        String[] data = fileData.split("\\n"); // split data by new line character
        for(int i = 0 ; i < data.length ; i++)
        {
            String[] values = data[i].split(";");            
            users.add(new User( Integer.parseInt(values[0]),values[1],values[2],values[3],values[4],values[5],values[6])); // add a venue to the collection
        }        
    }

    private void writeToUsersFile(String path)
    {
        int lastIndex = getUsers().size() - 1;
        String toWrite = getUsers().get(lastIndex).getUserId() + ";" + getUsers().get(lastIndex).getFirstName() 
            + ";" + getUsers().get(lastIndex).getLastName() + ";" + getUsers().get(lastIndex).getPhoneNumber()  
            + ";" + getUsers().get(lastIndex).getEmail()  + ";" + getUsers().get(lastIndex).getPassword()  
            + ";" + getUsers().get(lastIndex).getRole();
        writeFile(path, toWrite);
    }

    /**
     * Method readFile
     *
     * @return All the file's contents. Each Line end replaced by \n character.
     */
    public String readFile(String fileName)
    {
        String readData = "";
        try
        {            
            File csvFile = new File(fileName);
            if (csvFile != null && csvFile.isFile()) // check if file is null or is actually a file or not
            {  
                Scanner scanner = new Scanner(new FileReader(fileName)); // set scanner source as the file by its name
                StringBuffer sb1 =  new StringBuffer("");
                while (scanner.hasNextLine()) // go through each line, check if file has next line
                {  
                    sb1.append(scanner.nextLine() + "\n"); // append next line with \n character into a stringbuffer
                }
                readData = sb1.toString(); // convert stringbuffer to string
            }
            else
            {
                System.out.println("File not loaded");
            }
        }
        catch(FileNotFoundException fileEx)
        {
            System.out.println("Could not find file at " + fileName + " \n Please create the file");
        }
        catch(IOException ioEx)
        {
            System.out.println("IO Exception occured. Possible Causes are : 1.Reading a network file and got disconnected.\n2.Reading a local file" +
                " that was no longer available.\n3.Using some stream to read data and some other process closed the stream.\n4.Trying to read/write a file" +
                " but don't have permission.\n5.Trying to write to a file but disk space was no longer available.");
        }
        catch(Exception ex)
        {
            System.out.println("Exception occured " + ex);
        }
        return readData;
    }

    /**
     * Method writeFile
     *
     * @param contents The contents to write into the file. Each line end must be represented by \n character.
     */
    public void writeFile(String fileName,String contents)
    {
        PrintWriter pw = null;
        try
        {
            pw = new PrintWriter(new FileOutputStream(new File(fileName),true));
            String[] writeableContents = contents.split("\\n");
            for(int i = 0 ; i < writeableContents.length ; i++)
            {
                pw.println(writeableContents[i]); // write contents to the file line by line
            }
        }
        catch(FileNotFoundException fileEx)
        {
            System.out.println("Could not find File to write driver details to at " + fileName + " \n Please create the file");
        }
        catch(IOException ioEx)
        {
            System.out.println("IO Exception occured. Possible Causes are : 1.Reading a network file and got disconnected.\n2.Reading a local file" +
                " that was no longer available.\n3.Using some stream to read data and some other process closed the stream.\n4.Trying to read/write a file" +
                " but don't have permission.\n5.Trying to write to a file but disk space was no longer available.");
        }
        catch(Exception ex)
        {
            System.out.println("Exception occured " + ex);
        }
        finally
        {
            if(pw != null)
                pw.close();
        }
    }

    private void showMenu()
    {
        boolean isValid = false;
        while(!isValid)
        {
            int input = acceptIntegerInput("1. Login\n2. Registration\n3. Exit\nEnter your choice:");
            switch(input)
            {
                case 1: 
                isValid = true;
                doLogin();
                break;
                case 2: 
                isValid = true;
                registerUser();
                break;
                case 3:
                isValid = true;
                System.out.println("Exiting Prime Events. Bye!!"); 
                break;
                default:
                System.out.println("Invalid choice. Please try again!\n");
            }
        }
    }

    private void registerUser()
    {
        boolean isValid = false;
        while(!isValid)
        {
            displayHeader("REGISTRATION");
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
                addUser(fname,lname,phone,email,password,"CUSTOMER");
                System.out.println(fname + ", you've been successfully registered to Prime events. Kindly login to continue..");
                doLogin();
                break;
                case 2:
                isValid = true;
                String firstname = acceptStringInput("Enter your first name");
                String lastname = acceptStringInput("Enter your last name");
                String phone1 = acceptStringInput("Enter your phone no");
                String email1 = acceptStringInput("Enter your email");
                String password1 = acceptStringInput("Enter your password");
                addUser(firstname,lastname,phone1,email1,password1,"OWNER");
                System.out.println(firstname + ", you've been successfully registered to Prime events as an Owner. Kindly login to continue..");
                doLogin();
                break;
                default:
                System.out.println("Invalid choice. Please try again");
            }
        }
    }

    private void doLogin()
    {
        readUsersFile();
        displayHeader("LOGIN");
        String email = acceptStringInput("Enter your email");
        String password = acceptStringInput("Enter your password");
        if(isValidCredentials(email,password))
        {
            System.out.println("Login Successful!!");
            showHome();
        }
        else
        {
            doLogin();
        }
    }

    private boolean isValidCredentials(String email, String password)
    {
        for(int i = 0; i < getUsers().size();i++)
        {
            if(getUsers().get(i).getEmail().equalsIgnoreCase(email))
            {
                if(getUsers().get(i).getPassword().equalsIgnoreCase(password))
                {
                    getUsers().get(i).setIsLoggedIn(true);
                    return true;
                }
                else
                {
                    System.out.println("Incorrect password entered for the email. Please try again");
                    return false;
                }

            }        
        }
        System.out.println("Email is not registered with prime events");
        return false;
    }

    private void showHome()
    {
        displayHeader("HOME");
        sortUserByLoginStatus();
        if(getUsers().get(0).getRole().equalsIgnoreCase("CUSTOMER"))
        {
            int input = acceptIntegerInput("1. Search Halls\n2. View Quotation Responses\n3. Manage Bookings\n4. Review Halls\n0. Logout\nEnter your choice:");
            switch(input)
            {
                case 1: searchHalls(); break;
                case 2: showQuotationResponse(); break;
                case 3: manageBooking(); break;
                case 4: showReviewHall(); break;
                case 0: logout(); break;
            }
        }
        else
        {
            int input = acceptIntegerInput("1. Manage Halls\n2. Manage Bookings\n3. Manage Payments\n4. Manage Discounts\n5. View Quotation Requests\n0. Logout\nEnter your choice:");
            switch(input)
            {
                case 1: showManageHalls();  break;
                case 2:  break;
                case 3:  break;
                case 4:  break;
                case 5:  break;
                case 0: logout(); break;
            }

        }
    }

    private void showManageHalls()
    {
        displayHeader("MANAGE HALLS");
        char input = acceptStringInput("1. Create Hall\n2. Modify Hall\n3. Delete Hall\nB. Go back to home\nEnter your choice:").charAt(0);
        switch(input)
        {
            case '1': createHall(); break;
            case '2': chooseHall(false); break;
            case '3': deleteHall(); break;
            case 'b': showHome(); break;
        }
    }

    private void createHall()
    {
        displayHeader("CREATE HALL");
        String fname = acceptStringInput("Enter the hall name");
        String lname = acceptStringInput("Enter the suburb");
        String dob = acceptStringInput("Enter the address");
        String phone = acceptStringInput("Enter the capacity");
        String isVet = acceptStringInput("Enter the deposit%");
        String isVet1 = acceptStringInput("Enter the approximate price");
        String isVet2 = acceptStringInput("Enter the event types(comma separated)");
        System.out.println("Hall Created!! Going back to manage Halls");
        showManageHalls();
    }

    private void chooseHall(boolean isDelete)
    {
        displayHeader("CHOOSE HALL");
        System.out.println("1. Hall 1, Caulfield\n2. Hall 2, Oakleigh\n3. Hall 3, Clayton"+
            "\n4. Hall 4, CBD\n5. Hall 5, Brighton\n6. Hall 6, Clayton");

        if(!isDelete)
        {
            char input = acceptStringInput("Options:\nPress number to choose hall to modify\nF. Apply a Filter\nB. Go Back to Manage Halls").charAt(0);

            switch(input)
            {
                case '1': modHall(); break;
                case 'f' | 'F': applyFilter(); break;
                case 'b' | 'B': showManageHalls(); break;
            }
        }
        else
        {
            char input = acceptStringInput("Options:\nPress number to choose hall to delete\nF. Apply a Filter\nB. Go Back to Manage Halls").charAt(0);

            switch(input)
            {
                case '1': deletePrompt(); break;
                case '2': applyFilter(); break;
                case '3': showManageHalls(); break;
            }
        }
    }

    private void deletePrompt()
    {
        char input = acceptStringInput("Are you sure you want to delete Hall 6, Clayton? (Y/N)").charAt(0);
        switch(input)
        {
            case 'y': 
            System.out.println("Hall deleted!! Going back to manage Halls");
            showManageHalls();
            break;
            case 'n': 
            System.out.println("Operation cancelled! Going back to manage halls");
            showManageHalls();
            break;
        }
    }

    private void modHall()
    {
        displayHeader("MODIFY HALL");
        System.out.println("Name: Hall 2\nSuburb: Oakleigh\nAddress: 17, Oakleigh Road, Oakleigh"+
            "\nCapacity: 500\nDeposit: 50%\nEvent Types: Wedding ceremony, Wedding reception, Birthday\nPrice: $2000 (Catering extra)");
        char input = acceptStringInput("Do you want to change hall name (Y/N)").charAt(0);
        if(input == 'y'|| input == 'Y')
        {
            String name = acceptStringInput("Enter the new hall name");        
        }
        input = acceptStringInput("Do you want to change hall suburb (Y/N)").charAt(0);
        if(input == 'y'|| input == 'Y')
        {
            String name = acceptStringInput("Enter the new hall suburb");        
        }
        input = acceptStringInput("Do you want to change hall address (Y/N)").charAt(0);
        if(input == 'y'|| input == 'Y')
        {
            String name = acceptStringInput("Enter the new hall address");        
        }
        input = acceptStringInput("Do you want to change hall capacity (Y/N)").charAt(0);
        if(input == 'y'|| input == 'Y')
        {
            String name = acceptStringInput("Enter the new hall capacity");        
        }
        input = acceptStringInput("Do you want to change hall deposit% (Y/N)").charAt(0);
        if(input == 'y'|| input == 'Y')
        {
            String name = acceptStringInput("Enter the new hall deposit%");        
        }
        input = acceptStringInput("Do you want to change hall event types (Y/N)").charAt(0);
        if(input == 'y'|| input == 'Y')
        {
            String name = acceptStringInput("Enter the new hall event types(comma separated)");        
        }
        input = acceptStringInput("Do you want to change hall price (Y/N)").charAt(0);
        if(input == 'y'|| input == 'Y')
        {
            String name = acceptStringInput("Enter the new hall price");        
        }
        System.out.println("Hall details modified");
        System.out.println("Name: Hall 2\nSuburb: Oakleigh\nAddress: 17, Oakleigh Road, Oakleigh"+
            "\nCapacity: 450\nDeposit: 50%\nEvent Types: Birthday, Wedding ceremony, Wedding reception, Anniversary\nPrice: $2000 (Catering extra)");
        System.out.println("\nGoing back to Manage Halls");
        showManageHalls();
    }

    private void deleteHall()
    {
        chooseHall(true);
    }

    private void logout()
    {
        char input = acceptStringInput("Are you sure you want to log out(y/n)").charAt(0);
        switch(input)
        {
            case 'y':
            System.out.println("Successfully logged out!!");
            sortUserByLoginStatus();
            getUsers().get(0).setIsLoggedIn(false);
            start();
            break;
            case 'n':
            showHome();
            break;
        }
    }

    private void searchHalls()
    {
        displayHeader("SEARCH HALLS");
        System.out.println("1. Hall 1, Caulfield\n2. Hall 2, Oakleigh\n3. Hall 3, Clayton"+
            "\n4. Hall 4, CBD\n5. Hall 5, Brighton\n6. Hall 6, Clayton\n7. Hall 7, CBD"+
            "\n8. Hall 8, Caulfield\n9. Hall 9, Clayton\n10. Hall 10, CBD");
        System.out.println("\nOptions:");
        char searchInput = acceptStringInput("Press number to view hall details"+
                "\n----OR----\nF. Apply a Filter\nB. Go Back to Home"+
                "\nN. View Next Page\nEnter your choice:").charAt(0);
        switch(searchInput)
        {
            case '1': viewHallDetails(); break;
            case 'f': applyFilter(); break;
            case 'b': showHome(); break;
            case 'n': searchHalls(); break;
        }
    }

    private void viewHallDetails()
    {
        displayHeader("VIEW HALL");
        System.out.println("Name: Hall 17\nSuburb: Clayton\nAddress: 17, Clayton Road, Clayton"+
            "\nCapacity: 300\nDeposit: 50%\nEvent Types: Wedding ceremony, Wedding reception, Birthday, Anniversary\nPrice: $1200 (Catering extra)");
        char hallDetInput = acceptStringInput("\nQ. Request Quotation\nR. View Reviews\nB. Go Back to Search Halls\nEnter your choice:").charAt(0);
        switch(hallDetInput)
        {
            case 'q': requestQuotation(); break;
            case 'r': showReviews(); break;
            case 'b': searchHalls(); break;
        }
    }

    private void requestQuotation()
    {
        displayHeader("REQUEST QUOTATION");
        String fname = acceptStringInput("Enter Event Date (dd/MM/yyyy)");
        String lname = acceptStringInput("Enter total number of attendees");
        String dob = acceptStringInput("Enter your event type (Wedding ceremony, Wedding reception, Birthday, Anniversary)");
        String phone = acceptStringInput("Do you require Catering by us(y/n):");
        String isVet = acceptStringInput("Any special requirements:");
        System.out.println("Your quotation request has been sent to the owner. Once the owner responds, you can use the View Quotation Responses option to view it!");
        searchHalls();
    }

    private void showReviews()
    {
        displayHeader("VIEW REVIEWS");
        System.out.println("Name: Hall 17\nSuburb: Clayton\n\nReviews:"+
            "\nIt is a spacious hall with plenty of sitting. 5 stars.\n\nGreat hall in the heart of Clayton. 4 stars.");
        char goBack = acceptStringInput("\nB. Go Back to View Hall\nEnter your choice:").charAt(0);
        viewHallDetails();
    }

    private void applyFilter()
    {
        displayHeader("APPLY FILTER");
        int filterInp = acceptIntegerInput("1. Filter by Suburb\n2. Filter by Available Date"+
                "\n3. Filter by Event Type\nEnter your choice:");
        switch(filterInp)
        {
            case 1:
            acceptStringInput("Enter the suburb name to search");
            break;
            case 2:
            acceptStringInput("Enter the available date to search (dd/MM/yyyy)");
            break;
            case 3:
            acceptStringInput("Enter the event type to search");
            break;
            default:
            System.out.println("Invalid choice. Please try again!");
        }
        sortUserByLoginStatus();
        if(getUsers().get(0).getRole().equalsIgnoreCase("CUSTOMER"))
        {
            searchHalls();
        }
        else
        {
            chooseHall(false);
        }
    }

    private void showQuotationResponse()
    {
        displayHeader("QUOTATION RESPONSES");
        System.out.println("1. Hall 17, Clayton - Accepted\n2. Hall 2, Oakleigh - Rejected");
        char choice = acceptStringInput("\nEnter number to view accepted quotation and pay deposit"+
                "\n----OR-----\nB. Go back to Home\nEnter your choice:").charAt(0);
        switch(choice)
        {
            case '1':viewQuotationDetails(); break;
            case 'b':showHome(); break;
        }
    }

    private void viewQuotationDetails()
    {
        displayHeader("QUOTATION DETAILS");
        System.out.println("Name: Hall 17\nSuburb: Clayton\nAddress: 17, Clayton Road, Clayton" +
            "\nCapacity: 300\nDeposit: 50%\nEvent Type: Birthday\nPrice: $1000"+
            "\nEvent Date: 12/09/2019\nTotal number of attendees: 150\nCatering required: Yes"+
            "\nAny special requirements: Require Vegan food options\nStatus: Accepted\nDeposit Paid: No");
        char choice = acceptStringInput("\n1. Pay deposit\nB. Go Back to Quotation Responses\nEnter your choice:").charAt(0);
        switch(choice)
        {
            case '1':payDeposit(); break;
            case 'b':showQuotationResponse(); break;
        }
    }

    private void payDeposit()
    {
        displayHeader("PAY DEPOSIT");
        System.out.println("Name: Hall 17\nSuburb: Clayton\nDeposit: 50%"+
            "\nPrice: $1000\nEvent Date: 12/09/2019");
        char choice = acceptStringInput("1. Pay by Cash\n2. Pay by Card\nB. Go back to Quotation Responses\nEnter your choice:").charAt(0);
        switch(choice)
        {
            case '1':
            char accept = acceptStringInput("Do you accept charges of $500 for the booking(y/n)?").charAt(0);
            switch(accept)
            {
                case 'y':
                System.out.println("Pay deposit accepted!! Booking successful.");
                viewReceipt();
                break;
                case 'n':
                System.out.println("Pay deposit cancelled!! Going back to Quotation details");
                viewQuotationDetails();
                break;
            }
            break;
            case '2':
            // do card payment
            break;
            case 'b':
            showQuotationResponse();
            break;
        }
    }

    private void viewReceipt()
    {
        displayHeader("VIEW RECEIPT");
        System.out.println("Name: Hall 17\nSuburb: Clayton\nAddress: 17, Clayton Road, Clayton\nDeposit: 50%\nEvent Type: Birthday\nPrice: $1000\n"+
            "Event Date: 12/09/2019\nTotal number of attendees: 150\nCatering required: Yes\nAny special requirements: Require Vegan food options\nStatus: Accepted\nDeposit Paid: YES");
        char accept = acceptStringInput("\nB. Go back to Quotation Responses\nEnter your choice:").charAt(0);
        switch(accept)
        {
            case 'b':
            showQuotationResponse();
            break;
        }
    }

    private void manageBooking()
    {
        displayHeader("MANAGE BOOKING");
        System.out.println("1. Hall 17, Clayton - 12/09/2019\n2. Hall 1, Caulfield - 5/06/2019");
        char accept = acceptStringInput("Enter number to select booking\n----OR-----\nB. Go back to Home\nEnter your choice:").charAt(0);
        switch(accept)
        {
            case '1':
            showBookingDetails();
            break;
            case 'b':
            showHome();
            break;
        }
    }

    private void showBookingDetails()
    {
        displayHeader("BOOKING DETAILS");
        System.out.println("Name: Hall 17\nSuburb: Clayton\nAddress: 17, Clayton Road, Clayton"+
            "\nCapacity: 300\nDeposit: 50%\nEvent Type: Birthday\nPrice: $1000\nEvent Date: 12/09/2019\n"+
            "Total number of attendees: 150\nCatering required: Yes\nAny special requirements: Require Vegan food options"+
            "\nDeposit Paid: Yes\nTotal Amount Paid: No\nBooking Status: Upcoming");
        char accept = acceptStringInput("\n1. Change Booking Date\n2. Cancel Booking\nB. Go Back to Manage Bookings\nEnter your choice:").charAt(0);
        switch(accept)
        {
            case '1':
            changeDate();
            break;
            case '2':
            cancelBooking();
            break;
            case 'b':
            manageBooking();
            break;
        }
    }

    private void changeDate()
    {
        displayHeader("CHANGE DATE");
        System.out.println("Name: Hall 17\nSuburb: Clayton");
        String date = acceptStringInput("Enter new booking date (dd/MM/yyyy)");
        switch(acceptStringInput("Are you sure you want to change booking date to " +date+" (Y/N):").charAt(0))
        {
            case 'y':
            System.out.println("Booking date changed!!");
            showBookingDetails();
            break;
            case 'n':
            System.out.println("Change date cancelled!!");
            showBookingDetails();

            break;

        }

    }

    private void cancelBooking()
    {
        displayHeader("CANCEL BOOKING");
        System.out.println("Name: Hall 17\nSuburb: Clayton\nEvent Date: 11/09/2019"+
            "\nDeposit: 50%\nPrice: $1000\nDeposit Paid: Yes\nTotal Amount Paid: No");
        switch(acceptStringInput("\nAre you sure you want to cancel booking on 11/09/2019. It is less than 7 days away and would cause a 50% cancellation charge (Y/N").charAt(0))
        {
            case 'y':
            System.out.println("Booking cancelled! 50% of the amount should be refunded to you shortly.");
            showBookingDetails();
            break;
            case 'n':
            System.out.println("Operation cancelled. Going back to booking details!!");
            showBookingDetails();
            break;
        }
    }

    private void showReviewHall()
    {
        displayHeader("REVIEW HALLS");
        System.out.println("1. Hall 1, Caulfield - 5/06/2019");
        switch(acceptStringInput("Enter number to review hall\n----OR-----\nB. Go back to Home").charAt(0))
        {
            case '1':
            rateAndReview();
            break;
            case 'b':
            showHome();
            break;

        }
    }

    private void rateAndReview()
    {
        displayHeader("REVIEW HALLS");
        System.out.println("Name: Hall 11\nSuburb: Caulfield\nAddress: 321, Balaclava Road, Caulfield"+
            "\nCapacity: 100\nDeposit: 50%\nEvent Type: Birthday\nPrice: $700\nEvent Date: 5/06/2019\n"+
            "\nTotal number of attendees: 100\nCatering required: Yes\nAny special requirements: Require Vegan food options\n"+
            "\nDeposit Paid: Yes\nTotal Amount Paid: Yes\nBooking Status: Completed");
        acceptIntegerInput("Enter your rating for the hall (Between 1-5)");
        acceptStringInput("Enter your review for the hall:");

        switch(acceptStringInput("Are you sure you want to add this review (Y/N):").charAt(0))
        {
            case 'y':
            System.out.println("Hall Reviewed!! Thank you for the review.");
            showReviewHall();
            break;
            case 'n':
            System.out.println("Hall Review cancelled!");
            showReviewHall();
            break;

        }
    }

    private void displayHeader(String header)
    {
        System.out.println("\n***********************************");
        System.out.println("           " + header + "            ");
        System.out.println("***********************************\n");   
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
            System.out.println("Incorrect Input!!\n " + displayMessage);
            console.next();
        }
        return console.nextInt();
    }
}
