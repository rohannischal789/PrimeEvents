
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.lang.Object;  

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
    private ArrayList<Payment> payments;
    private ArrayList<Booking> bookings;
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

    public ArrayList<Payment> getPayments()
    {
        return payments;
    }

    public void setPayments(ArrayList<Payment> newPayments)
    {
        payments = newPayments;
    }

    public void setUsers(ArrayList<User> newUsers)
    {
        users = newUsers;
    }

    public void setHalls(ArrayList<Hall> newHalls)
    {
        halls = newHalls;
    }

    public Owner getOwnerById(int id)
    {
        for(int i = 0; i<getUsers().size();i++)
        {
            if(getUsers().get(i).getUserId() == id)
            {
                User currentUser = getUsers().get(i);
                return new Owner(currentUser.getUserId(),currentUser.getFirstName(),currentUser.getLastName(),
                    currentUser.getPhoneNumber(),currentUser.getEmail(),currentUser.getPassword(),currentUser.getRole());      
            }
        }
        return null;
    }

    public Customer getCustomerById(int id)
    {
        for(int i = 0; i<getUsers().size();i++)
        {
            if(getUsers().get(i).getUserId() == id)
            {
                User currentUser = getUsers().get(i);
                return new Customer(currentUser.getUserId(),currentUser.getFirstName(),currentUser.getLastName(),
                    currentUser.getPhoneNumber(),currentUser.getEmail(),currentUser.getPassword(),currentUser.getRole());      
            }
        }
        return null;
    }

    public String getUserRole(int userID)
    {
        for(int i = 0; i<getUsers().size();i++)
        {
            if(getUsers().get(i).getUserId() == userID)
            {
                return getUsers().get(i).getRole();      
            }
        }
        return null;
    }

    public Hall getHallById(int id)
    {
        for(int i = 0; i< getHalls().size();i++)
        {
            if(getHalls().get(i).getHallId() == id)
            {
                return getHalls().get(i);           
            }
        }
        return null;
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
            return 0;
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
        getUsers().add(new User(getMaxUserId() + 1,newFirstName,newLastName,newPhoneNo,newEmail,newPassword,newRole,false,0));
        writeToUsersFile("users.txt",true,true);
    }

    private void fetchBookingAndPaymentData(int userId)
    {
        try
        {
            String fileData = readFile("quotations.txt");
            String[] data = fileData.split("\\n"); // split data by new line character
            ArrayList<Quotation> acceptedQuotations = new ArrayList<Quotation>();
            for(int i = 0 ; i < data.length ; i++)
            {
                String[] values = data[i].split(";");

                if(values[9].toLowerCase() == "ACCEPTED")
                {
                    acceptedQuotations.add(new Quotation(Integer.parseInt(values[1]),
                            new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(values[2]),
                            new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(values[3]),Integer.parseInt(values[4]),values[5],Boolean.parseBoolean(values[6]),
                            values[7],Double.parseDouble(values[8]),values[9],getCustomerById(Integer.parseInt(values[10])),Integer.parseInt(values[0])));
                }
            }

            fileData = readFile("bookings_payments.txt");
            data = fileData.split("\\n"); // split data by new line character
            for(int i = 0 ; i < data.length ; i++)
            {
                String[] values = data[i].split(";");
                // get accepted quotations
                // use quotation to search and find completed payment through hall id
                Quotation currentQuotation = getHallById(Integer.parseInt(values[0])).getQuotationById(Integer.parseInt(values[7]));
                if(acceptedQuotations.contains(currentQuotation))
                {
                    Payment payment = new Payment(values[2], Double.parseDouble(values[3]),Double.parseDouble(values[4]),values[5],values[6]);
                    Booking booking = new Booking(values[1],payment,getHallById(Integer.parseInt(values[0])).getQuotationById(Integer.parseInt(values[7])));
                    payments.add(payment);
                    bookings.add(booking);
                }
            }
        }
        catch(Exception e)
        {
        }
    }

    private void fetchQuotationData()
    {
        try
        {
            String fileData = readFile("quotations.txt");
            String[] data = fileData.split("\\n"); // split data by new line character
            for(Hall hall : halls)
            {
                hall.resetQuotations();
            }
            for(int i = 0 ; i < data.length ; i++)
            {
                String[] values = data[i].split(";");
                getHallById(Integer.parseInt(values[0])).addQuotation(Integer.parseInt(values[1]),
                    new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(values[2]),
                    new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(values[3]),Integer.parseInt(values[4]),values[5],Boolean.parseBoolean(values[6]),
                    values[7],Double.parseDouble(values[8]),values[9],getCustomerById(Integer.parseInt(values[10])),Integer.parseInt(values[0]));
            }
        }
        catch(Exception e)
        {
            System.out.println("Error");
        }
    }

    private void fetchHallsData()
    {
        String fileData = readFile("halls.txt");
        setHalls(new ArrayList<Hall>());
        String[] data = fileData.split("\\n"); // split data by new line character
        for(int i = 0 ; i < data.length ; i++)
        {
            String[] values = data[i].split(";");
            ArrayList<String> events = new ArrayList<String>();
            String[] eventTypes = values[6].split(",");
            for(int j = 0 ; j < eventTypes.length ; j++)
            {
                events.add(eventTypes[j]);
            }
            halls.add(new Hall( Integer.parseInt(values[0]),values[1],values[2],values[3],Integer.parseInt(values[4]),
                    Integer.parseInt(values[5]),events ,Integer.parseInt(values[7]), getOwnerById(Integer.parseInt(values[8]))));
        }
        fetchQuotationData();
        fetchReviewData();
    }

    private void fetchReviewData()
    {
        String fileData = readFile("reviews.txt");
        String[] data = fileData.split("\\n"); // split data by new line character
        for(Hall hall : halls)
        {
            hall.resetReviews();
        }
        for(int i = 0 ; i < data.length ; i++)
        {
            String[] values = data[i].split(";");
            getHallById(Integer.parseInt(values[0])).addReview(values[1],getCustomerById(Integer.parseInt(values[2])),Integer.parseInt(values[3]));
        }
    }

    public void fetchUsersData()
    {
        setUsers(new ArrayList<User>());
        String fileData = readFile("users.txt");
        String[] data = fileData.split("\\n"); // split data by new line character
        for(int i = 0 ; i < data.length ; i++)
        {
            String[] values = data[i].split(";");            
            users.add(new User( Integer.parseInt(values[0]),values[1],values[2],values[3],values[4],values[5],values[6],
                    Boolean.parseBoolean(values[7]),Integer.parseInt(values[8])));
        }        
    }

    private void writeToBookingsAndPaymentsFile(String path, Booking booking)
    {
        StringBuffer strBuf = new StringBuffer("");

        strBuf.append(booking.getStatus() 
            + ";" + booking.getPayment().getReceiptNo() + ";" + booking.getPayment().getDepositAmount()
            + ";" + booking.getPayment().getBalanceAmount() + ";" + booking.getPayment().getPaymentType()
            + ";" + booking.getPayment().getPaymentStatus()
            + ";" + booking.getQuotation().getQuotationId()
            +"\n");

        writeFile(path, strBuf.toString(), true);
    }

    private void writeToQuotationsFile(String path, boolean toAppend, boolean onlyUpdatedOnes, ArrayList<Quotation> quotations)
    {
        StringBuffer strBuf = new StringBuffer("");
        if(onlyUpdatedOnes)
        {
            int lastIndex = quotations.size() - 1;
            strBuf.append(quotations.get(lastIndex).getHallId() + ";"+ quotations.get(lastIndex).getQuotationId() + ";" + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(quotations.get(lastIndex).getStartEventDateTime()) 
                + ";" + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(quotations.get(lastIndex).getEndEventDateTime()) + ";" + quotations.get(lastIndex).getNumberOfAttendees() + ";" + quotations.get(lastIndex).getEventType()  
                + ";" + (quotations.get(lastIndex).getRequiresCatering() == true ? "y" : "n") + ";" + quotations.get(lastIndex).getSpecialRequirements()  
                + ";" + quotations.get(lastIndex).getFinalPrice()+ ";" + quotations.get(lastIndex).getQuotationStatus()+ ";" + quotations.get(lastIndex).getCustomer().getUserId()
                +"\n");
        }
        writeFile(path, strBuf.toString(), toAppend);
    }

    private void writeToUsersFile(String path, boolean toAppend, boolean onlyUpdatedOnes)
    {
        StringBuffer strBuf = new StringBuffer("");
        if(onlyUpdatedOnes)
        {
            int lastIndex = getUsers().size() - 1;
            strBuf.append(getUsers().get(lastIndex).getUserId() + ";" + getUsers().get(lastIndex).getFirstName() 
                + ";" + getUsers().get(lastIndex).getLastName() + ";" + getUsers().get(lastIndex).getPhoneNumber()  
                + ";" + getUsers().get(lastIndex).getEmail()  + ";" + getUsers().get(lastIndex).getPassword()  
                + ";" + getUsers().get(lastIndex).getRole()+ ";" + getUsers().get(lastIndex).getIsLockedOut()+ ";" + getUsers().get(lastIndex).getInvalidLogoutAttempts());
        }
        else
        {
            for(int i = 0; i<getUsers().size(); i++)
            {
                strBuf.append(getUsers().get(i).getUserId() + ";" + getUsers().get(i).getFirstName() 
                    + ";" + getUsers().get(i).getLastName() + ";" + getUsers().get(i).getPhoneNumber()  
                    + ";" + getUsers().get(i).getEmail()  + ";" + getUsers().get(i).getPassword()  
                    + ";" + getUsers().get(i).getRole()+ ";" + getUsers().get(i).getIsLockedOut()+ ";" + getUsers().get(i).getInvalidLogoutAttempts()+"\n");
            }
        }
        writeFile(path, strBuf.toString(), toAppend);

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
    public void writeFile(String fileName,String contents,boolean toAppend)
    {
        PrintWriter pw = null;
        try
        {
            pw = new PrintWriter(new FileOutputStream(new File(fileName),toAppend));
            String[] writeableContents = contents.split("\\n");
            for(int i = 0 ; i < writeableContents.length ; i++)
            {
                pw.println(writeableContents[i]); // write contents to the file line by line
            }
        }
        catch(FileNotFoundException fileEx)
        {
            System.out.println("Could not find File to write details to at " + fileName );
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

    public boolean isValidCredentials(String email, String password)
    {
        for(int i = 0; i < getUsers().size();i++)
        {
            if(getUsers().get(i).getEmail().equalsIgnoreCase(email))
            {
                User currUser = getUsers().get(i);
                if(currUser.getPassword().equalsIgnoreCase(password))
                {
                    if(!currUser.getIsLockedOut())
                    {
                        getUsers().get(i).setInvalidLogoutAttempts(0);
                        getUsers().get(i).setIsLoggedIn(true);
                        writeToUsersFile("users.txt",false,false);
                        return true;
                    }
                    else
                    {
                        System.out.println("Account has locked for 3 previous failed attempts. Contact the administrator to unlock your account");
                    }
                }
                else
                {        
                    currUser.setInvalidLogoutAttempts(currUser.getInvalidLogoutAttempts() + 1);
                    if(currUser.getInvalidLogoutAttempts() >= 3)
                    {
                        System.out.println("You've been locked out of your account for 3 consecutive failed attempts. Contact the administrator to unlock your account");
                        currUser.setIsLockedOut(true);
                    }
                    else
                    {
                        System.out.println("Incorrect password entered for the email. Please try again");
                    }  
                    writeToUsersFile("users.txt",false,false);
                    return false;
                }

            }        
        }
        System.out.println("Email is not registered with prime events");
        return false;
    }

    public void initializeData()
    {
        setHalls(new ArrayList<Hall>());
        fetchHallsData();
        sortUserByLoginStatus();
        fetchBookingAndPaymentData(getUsers().get(0).getUserId());
    }

    public void doLogout()
    {
        sortUserByLoginStatus();
        getUsers().get(0).setIsLoggedIn(false);
    }

    public String getHallData(String searchSuburb, String searchEventType)
    {
        ArrayList<Hall> filteredHalls = filterHalls(searchSuburb, searchEventType);
        setHalls(filteredHalls);
        StringBuffer strBuf = new StringBuffer("");
        for(int i = 0; i < filteredHalls.size(); i++)
        {
            strBuf.append(filteredHalls.get(i).displayShort() + "\n");
        }
        return strBuf.toString();
    }

    private ArrayList<Hall> filterHalls(String searchSuburb, String searchEventType)
    {
        ArrayList<Hall> filteredHalls = new ArrayList<Hall>();
        if(searchSuburb.length() == 0 && searchEventType.length() == 0)
        {
            fetchHallsData();
            filteredHalls = getHalls();
        }
        else
        {
            if(searchSuburb.length() > 0)
            {
                for(int i = 0 ; i < getHalls().size(); i++)
                {
                    if(getHalls().get(i).getSuburb().equalsIgnoreCase(searchSuburb))
                    {
                        filteredHalls.add(getHalls().get(i));
                    }
                }
            }
            else if(searchEventType.length() > 0)
            {
                for(int i = 0 ; i < getHalls().size(); i++)
                {
                    for(String str : getHalls().get(i).getEventTypes())
                    {
                        if(str.equalsIgnoreCase(searchEventType))
                        {
                            filteredHalls.add(getHalls().get(i));
                        }
                    }
                }
            }
        }       
        return filteredHalls;
    }

    public boolean doesHallExist(int id)
    {
        return getHallById(id) != null ? true : false;
    }

    public String getHallDetails(int id)
    {
        return getHallById(id).displayLong();
    }

    public void requestQuotation(int hallID, Date startEventDate, Date endEventDate, int noOfAttendees, String eventType, char requiresCatering, String specialReq,
    double finalPrice)
    {
        Hall selectedHall = getHallById(hallID);
        selectedHall.addQuotation(1,startEventDate,endEventDate,noOfAttendees,eventType, requiresCatering == 'y' ? true : false,specialReq,
            finalPrice,"PENDING",getCustomerById(getUsers().get(0).getUserId()),hallID);
        writeToQuotationsFile("quotations.txt",true,true, selectedHall.getQuotations());
    }

    public int daysBetween(Date d1, Date d2)
    {
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }

    public double calculatePrice(Date startEventDate, Date endEventDate, int noOfAttendees, String eventType, char requiresCatering, String specialReq, int hallID)
    {
        double multiplier = 1;
        Hall currentHall = getHallById(hallID);
        int dayDiff = daysBetween(startEventDate, endEventDate);
        if(dayDiff>0)
            multiplier *= dayDiff; 
        double atendeesMul = 1;
        if(currentHall.getCapacity()>0)
            atendeesMul = noOfAttendees/currentHall.getCapacity();
        multiplier *= atendeesMul;
        double typeMul = requiresCatering == 'y' ?  atendeesMul : 0;
        multiplier += typeMul;
        return currentHall.getPrice() * multiplier;
    }

    public String getHallReviews(int hallID)
    {
        return getHallById(hallID).getAllReviews();
    }

    public String getQuotationResponse(int customerID)
    {
        StringBuffer strBuf = new StringBuffer("");
        for(Hall hall : halls)
        {
            if(!hall.getCustomerQuotationResponses(customerID).equals(""))
                strBuf.append(hall.getCustomerQuotationResponses(customerID));
        }
        return strBuf.toString();
    }

    public String getQuotationDetails(int userID, int quotationID)
    {
        StringBuffer strBuf = new StringBuffer("");
        if(getUserRole(userID) == "CUSTOMER")
        {
            for(Hall hall : halls)
            {
                if(hall.getCustomerQuotationDetails(userID, quotationID) != null)
                    strBuf.append(hall.getCustomerQuotationDetails(userID, quotationID) );
            }
        }
        else
        {
            for(Hall hall : halls)
            {
                if(hall.getOwner().getUserId() == userID)
                    if(hall.getQuotationRequestsByID(quotationID) != null)
                        strBuf.append(hall.getQuotationRequestsByID(quotationID) );
            }
        }
        return strBuf.toString();
    }

    public void makeBooking(int customerId, int quotationId, String paymentType )
    {
        Quotation currQuotation = null;
        Hall currHall = null;
        for(Hall hall : getHalls())
        {
            for(Quotation quotation : hall.getQuotationByCustomerId(customerId))
            {
                if(quotation.getQuotationId() == quotationId) 
                {
                    currQuotation = quotation;
                    currHall = hall;
                }
            }
        }
        double depositAmount = currQuotation.getFinalPrice()*(currHall.getDeposit()/100);
        double balanceAmount = currQuotation.getFinalPrice() - depositAmount;
        String receiptNo = new SimpleDateFormat("ddMMyyyyHHmmss").format(System.currentTimeMillis());
        receiptNo += customerId;
        Payment payment = new Payment(receiptNo,depositAmount, balanceAmount,paymentType,"PARTIAL");
        Booking booking = new Booking("UPCOMING",payment,currQuotation);
        writeToBookingsAndPaymentsFile("bookings_payments.txt",booking);
    }

    public String getReceipt(int userID, int quotationID)
    {
        StringBuffer strBuf = new StringBuffer("");
        for(Hall hall : halls)
        {
            if(hall.getCustomerQuotationDetails(userID, quotationID) != null)
                System.out.println(hall.getCustomerQuotationDetails(userID, quotationID) );
        }
        return strBuf.toString();
    }

    public String getQuotationRequests(int ownerID)
    {
        StringBuffer strBuf = new StringBuffer("");
        for(Hall hall : halls)
        {
            if(hall.getOwner().getUserId() == ownerID)
                if(hall.getQuotationRequests() != null)
                    strBuf.append(hall.getQuotationRequests());
        }
        return strBuf.toString();
    }

    public void updateQuotationStatus(int userID, int quotationID, String status)
    {
        for(Hall hall : halls)
        {
            if(hall.getOwner().getUserId() == userID)
                if(hall.getQuotationRequestsByID(quotationID) != null)
                    hall.getQuotationById(quotationID).setQuotationStatus(status);
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
            //showHome();
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
            //showHome();
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

    private void showManageHalls()
    {
        displayHeader("MANAGE HALLS");
        char input = acceptStringInput("1. Create Hall\n2. Modify Hall\n3. Delete Hall\nB. Go back to home\nEnter your choice:").charAt(0);
        switch(input)
        {
            case '1': createHall(); break;
            case '2': chooseHall(false); break;
            case '3': deleteHall(); break;
            case 'b': //showHome(); break;
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
                case 'f' | 'F': //applyFilter(); break;
                case 'b' | 'B': showManageHalls(); break;
            }
        }
        else
        {
            char input = acceptStringInput("Options:\nPress number to choose hall to delete\nF. Apply a Filter\nB. Go Back to Manage Halls").charAt(0);

            switch(input)
            {
                case '1': deletePrompt(); break;
                case '2': //applyFilter(); break;
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
            System.out.println("Invalid Input. Please try again!\n" + displayMessage);
            console.next();
        }
        return console.nextInt();
    }
}
