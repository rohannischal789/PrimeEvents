
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
 * EventController is the control class for the system.
 * It is responsible for interacting with all the entity classes to retrieve and manipulate data.
 * It communicates with the PrimeEvent class by passing the data to be displayed.
 * @author Rohan Nischal,Guanting Chen,Swathi Jadhav
 * @version 19-10-2019
 */
public class EventController
{
    private ArrayList<Hall> halls;
    private ArrayList<User> users;
    private ArrayList<Payment> payments;
    private ArrayList<Booking> bookings;

    /**
     * Constructor for objects of class EventController
     */
    public EventController()
    {
        users = new ArrayList<User>();
        halls = new ArrayList<Hall>();
        bookings = new ArrayList<Booking>();
        payments = new ArrayList<Payment>();
    }

    /**
     * Method getHalls
     * accessor method for the arraylist of halls
     * @return The list of halls
     */
    public ArrayList<Hall> getHalls()
    {
        return halls;
    }

    /**
     * Method getUsers
     * accessor method for the arraylist of users
     * @return The list of users
     */
    public ArrayList<User> getUsers()
    {
        return users;
    }

    /**
     * Method getPayments
     * accessor method for the arraylist of payments
     * @return The list of payments
     */
    public ArrayList<Payment> getPayments()
    {
        return payments;
    }

    /**
     * Method setPayments
     * mutator method for the arraylist of payments
     * @param newPayments A list of payments
     */
    public void setPayments(ArrayList<Payment> newPayments)
    {
        payments = newPayments;
    }

    /**
     * Method setUsers
     * mutator method for the arraylist of users
     * @param newUsers A list of users
     */
    public void setUsers(ArrayList<User> newUsers)
    {
        users = newUsers;
    }

    /**
     * Method setHalls
     * mutator method for the arraylist of halls
     * @param newHalls A list of halls
     */
    public void setHalls(ArrayList<Hall> newHalls)
    {
        halls = newHalls;
    }

    /**
     * Method isUserLocked
     * This method checks if a user is locked out or not
     * @param userId A user's id
     * @return true if the user is locked out else false
     */
    public boolean isUserLocked(int userId)
    {
        for(int i = 0; i<getUsers().size();i++)
        {
            if(getUsers().get(i).getUserId() == userId)
            {
                return getUsers().get(i).getIsLockedOut();     
            }
        }
        return false;
    }

    /**
     * Method updateUserLockStatus
     * This method is used to update a user's locked out status
     * @param userId A user's id
     * @param status the new status
     */
    public void updateUserLockStatus(int userId, boolean status)
    {
        for(int i = 0; i<getUsers().size();i++)
        {
            if(getUsers().get(i).getUserId() == userId)
            {
                getUsers().get(i).setIsLockedOut(status);   
                getUsers().get(i).setInvalidLogoutAttempts(0);   
            }
        }
        writeToUsersFile("users.txt",false,false);
    }

    /**
     * Method getUserById
     * This method is used to get a user by id
     * @param id A user id
     * @return The formatted user's details
     */
    public String getUserById(int id)
    {
        for(int i = 0; i<getUsers().size();i++)
        {
            if(getUsers().get(i).getUserId() == id && !getUsers().get(i).getRole().equals("ADMINISTRATOR"))
            {
                return getUsers().get(i).display();     
            }
        }
        return "";
    }

    /**
     * Method getOwnerById
     * This method is used to get a owner by id
     * @param id A user id
     * @return The owner corresponding to the id, if any
     */
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

    /**
     * Method getCustomerById
     * This method is used to get a customer by id
     * @param id A user id
     * @return The customer corresponding to the id, if any
     */
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

    /**
     * Method getUserRole
     * This method is used to get a particular user's role
     * @param userID A user id
     * @return The role corresponding to the id, if any
     */
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

    /**
     * Method getHallById
     * This method is used to get a hall by id
     * @param id A hall id
     * @return The hall corresponding to the id, if any
     */
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

    /**
     * Method getMaxUserId
     * The method is used to get the maximum user id that has been used so far
     * @return The max user id used
     */
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

    /**
     * Method getMaxQuotationId
     * The method is used to get the maximum quotation id that has been used so far
     * @return The maximum quotation id
     */
    private int getMaxQuotationId()
    {
        String fileData = readFile("quotations.txt");
        String[] data = fileData.split("\\n"); // split data by new line character
        String[] lastQuotation = data[data.length - 1].split(";");
        return Integer.parseInt(lastQuotation[1]);
    }

    /**
     * Method sortUserByLoginStatus
     * The method sorts the user list by isLoggedIn (from true to false)
     */
    public void sortUserByLoginStatus()
    {
        Collections.sort(getUsers(), new Comparator<User>()
            {
                public int compare(User u1, User u2) {
                    return Boolean.compare(u2.getIsLoggedIn(), u1.getIsLoggedIn());
                }
            });
    }

    /**
     * Method addUser
     * This method is used to add a user to the user's list
     * @param newFirstName A first name
     * @param newLastName A last name
     * @param newPhoneNo A phone no
     * @param newEmail An email
     * @param newPassword A password
     * @param newRole A role
     */
    public void addUser(String newFirstName, String newLastName, 
    String newPhoneNo,String newEmail,String newPassword,String newRole)
    {
        getUsers().add(new User(getMaxUserId() + 1,newFirstName,newLastName,newPhoneNo,newEmail,newPassword,newRole,false,0));
        writeToUsersFile("users.txt",true,true);
    }

    /**
     * Method fetchBookingAndPaymentData
     * This method is used to retrieve booking and payment data according to the user's id from the file and add them to the list
     * @param userId A user's id
     */
    private void fetchBookingAndPaymentData(int userId)
    {
        try
        {
            String fileData = readFile("quotations.txt");
            String[] data = fileData.split("\\n"); // split data by new line character4
            payments = new ArrayList<Payment>();
            bookings = new ArrayList<Booking>();
            ArrayList<Quotation> acceptedQuotations = new ArrayList<Quotation>();
            for(int i = 0 ; i < data.length ; i++)
            {
                String[] values = data[i].split(";");

                if(values[9].toUpperCase().equals("ACCEPTED"))
                {
                    acceptedQuotations.add(new Quotation(Integer.parseInt(values[1]),
                            new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(values[2]),
                            new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(values[3]),Integer.parseInt(values[4]),values[5],Boolean.parseBoolean(values[6]),
                            values[7],Double.parseDouble(values[8]),values[9],getCustomerById(Integer.parseInt(values[10])),Integer.parseInt(values[0]),Boolean.parseBoolean(values[6])));
                }
            }

            fileData = readFile("bookings_payments.txt");
            data = fileData.split("\\n"); // split data by new line character
            for(int i = 0 ; i < data.length ; i++)
            {
                String[] values = data[i].split(";");
                // get accepted quotations
                // use quotation to search and find completed payment through hall id
                Quotation currentQuotation = getOwnerHallQuotation(userId,Integer.parseInt(values[6]),acceptedQuotations);
                if(currentQuotation != null && acceptedQuotations.contains(currentQuotation))
                {
                    Payment payment = new Payment(values[1], Double.parseDouble(values[2]),Double.parseDouble(values[3]),values[4],values[5]);
                    Booking booking = new Booking(values[0],payment,currentQuotation);
                    payments.add(payment);
                    bookings.add(booking);
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("Error");
        }
    }

    /*private void writeAllBookings()
    {
    String fileData = readFile("bookings_payments.txt");
    String[] data = fileData.split("\\n"); // split data by new line character
    ArrayList<Booking> allBookings = new ArrayList<>();
    for(int i = 0 ; i < data.length ; i++)
    {
    String[] values = data[i].split(";");
    // get accepted quotations
    // use quotation to search and find completed payment through hall id
    Quotation currentQuotation = getOwnerHallQuotation(userId,Integer.parseInt(values[6]),acceptedQuotations);
    if(currentQuotation != null)
    {
    Payment payment = new Payment(values[1], Double.parseDouble(values[2]),Double.parseDouble(values[3]),values[4],values[5]);
    Booking booking = new Booking(values[0],payment,currentQuotation);
    if(!bookings.contains(booking))
    allBookings.add(booking);
    }
    }
    StringBuffer strBuf = new StringBuffer("");
    for(Booking booking : allBookings)
    {

    strBuf.append(booking.getStatus() 
    + ";" + booking.getPayment().getReceiptNo() + ";" + booking.getPayment().getDepositAmount()
    + ";" + booking.getPayment().getBalanceAmount() + ";" + booking.getPayment().getPaymentType()
    + ";" + booking.getPayment().getPaymentStatus()
    + ";" + booking.getQuotation().getQuotationId()
    +"\n");
    }

    writeFile(path, strBuf.toString(), true);
    }*/
    /**
     * Method getOwnerHallQuotation
     * This methods checks if a quotation id for the owner's hall is present in the list of accepted quotations and returns it
     * @param userID A user's id
     * @param quotationID A quotation id
     * @param acceptedQuotations A list of all accepted quotations
     * @return The matched quotation, if any
     */
    private Quotation getOwnerHallQuotation(int userID,int quotationID, ArrayList<Quotation> acceptedQuotations)
    {
        for(Hall hall : getHalls())
        {
            if(hall.getOwner().getUserId() == userID)
            {
                for(Quotation quotation: acceptedQuotations)
                {
                    if(quotation.getQuotationId()== quotationID && quotation.getQuotationStatus().toUpperCase().equals("ACCEPTED"))
                        return quotation;

                }

            }

        }
        return null;
    }

    /**
     * Method fetchQuotationData
     * This method is used to retrieve quotation data from the file and add them to the corresponding hall
     */
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
                    values[7],Double.parseDouble(values[8]),values[9],getCustomerById(Integer.parseInt(values[10])),Integer.parseInt(values[0]),Boolean.parseBoolean(values[11]));
            }
        }
        catch(Exception e)
        {
            System.out.println("Error");
        }
    }

    /**
     * Method fetchHallsData
     * This method is used to retrieve hall data from the file and add them to the halls list
     */
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

    /**
     * Method fetchReviewData
     * This method is used to retrieve review data from the file and add them to the corresponding hall
     */
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

    /**
     * Method fetchUsersData
     * This method is used to retrieve user data from the file and add them to the list of users
     */
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

    /**
     * Method writeToBookingsAndPaymentsFile
     * This methods write a booking's detail to the file
     * @param path A file's location
     * @param booking A booking object
     */
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

    /**
     * Method writeToQuotationsFile
     * This method writes the quotations' details to the file
     * @param path A file path
     * @param toAppend true if the details are to be append, false if to be replaced
     * @param onlyUpdatedOnes true if only the last index of the quotation list is to be added, false if all are to be added
     * @param quotations A list of quotations to write
     */
    private void writeToQuotationsFile(String path, boolean toAppend, boolean onlyUpdatedOnes, ArrayList<Quotation> quotations)
    {
        StringBuffer strBuf = new StringBuffer("");
        if(onlyUpdatedOnes)
        {
            int lastIndex = quotations.size() - 1;
            strBuf.append(quotations.get(lastIndex).getHallId() + ";"+ quotations.get(lastIndex).getQuotationId() + ";" + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(quotations.get(lastIndex).getStartEventDateTime()) 
                + ";" + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(quotations.get(lastIndex).getEndEventDateTime()) + ";" + quotations.get(lastIndex).getNumberOfAttendees() + ";" + quotations.get(lastIndex).getEventType()  
                + ";" + (quotations.get(lastIndex).getRequiresCatering() == true ? "y" : "n") + ";" + quotations.get(lastIndex).getSpecialRequirements()  
                + ";" + quotations.get(lastIndex).getFinalPrice()+ ";" + quotations.get(lastIndex).getQuotationStatus()+ ";" + quotations.get(lastIndex).getCustomer().getUserId()+ ";" + quotations.get(lastIndex).getDepositPaid()
                +"\n");
        }
        else
        {
            for(Hall hall: halls)
            {
                for(Quotation quotation: hall.getQuotations())
                {
                    strBuf.append(quotation.getHallId() + ";"+ quotation.getQuotationId() + ";" + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(quotation.getStartEventDateTime()) 
                        + ";" + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(quotation.getEndEventDateTime()) + ";" + quotation.getNumberOfAttendees() + ";" + quotation.getEventType()  
                        + ";" + (quotation.getRequiresCatering() == true ? "y" : "n") + ";" + quotation.getSpecialRequirements()  
                        + ";" + quotation.getFinalPrice()+ ";" + quotation.getQuotationStatus()+ ";" + quotation.getCustomer().getUserId()+ ";" + quotation.getDepositPaid()
                        +"\n");
                }
            }
        }
        writeFile(path, strBuf.toString(), toAppend);
    }

    /**
     * Method writeToUsersFile
     * This method writes the users' details to the file
     * @param path A file path
     * @param toAppend true if the details are to be append, false if to be replaced
     * @param onlyUpdatedOnes true if only the last index of the user list is to be added, false if all are to be added
     */
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
     * This method reads all the contents from a file and returns it in string format
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
     * This method writes all the contents to the file
     * @param fileName A file name
     * @param contents The contents to write into the file. Each line end must be represented by \n character.
     * @param toAppend true if contents are to be appended, false if not
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

    /**
     * Method isValidCredentials
     * This method checks if the credentials correspond to a user or not.
     * It also maintains the locked out status functionality. It locks out the user if 3 consecutive incorrect login attempts are done
     * @param email An email id
     * @param password A user's password
     * @return true if credentials are valid, false if not
     */
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

    /**
     * Method initializeData
     * This method is used to initialize all data related to the system on the home screen
     */
    public void initializeData()
    {
        setHalls(new ArrayList<Hall>());
        fetchHallsData();
        sortUserByLoginStatus();
        fetchBookingAndPaymentData(getUsers().get(0).getUserId());
    }

    /**
     * Method doLogout
     * This method performs the logout operation
     */
    public void doLogout()
    {
        sortUserByLoginStatus();
        getUsers().get(0).setIsLoggedIn(false);
    }

    /**
     * Method getHallData
     * The method returns the formatted details of the filtered halls
     * @param searchSuburb A suburb name
     * @param searchEventType An event type
     * @return The formatted details of the filtered halls
     */
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

    /**
     * Method filterHalls
     * This method filters the list of halls according to the search criteria
     * @param searchSuburb A suburb name
     * @param searchEventType An event type
     * @return The list of filtered halls
     */
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

    /**
     * Method doesHallExist
     * This method checks if the hall corresponding to the id exists or not
     * @param id A hall id
     * @return true if hall exists, else false
     */
    public boolean doesHallExist(int id)
    {
        return getHallById(id) != null ? true : false;
    }

    /**
     * Method getHallDetails
     * This method reurns the formatted details of a particular hall
     * @param id A hall id
     * @return The formatted hall details
     */
    public String getHallDetails(int id)
    {
        return getHallById(id).displayLong();
    }

    /**
     * Method requestQuotation
     * This method adds a quotation to the list of quotations and writes it to the file
     * @param hallID A hall id
     * @param startEventDate the start date time of the event
     * @param endEventDate the ending date time of the event
     * @param noOfAttendees A total number of attendees
     * @param eventType A event type
     * @param requiresCatering true if catering is required
     * @param specialReq The special requirements
     * @param finalPrice The final price of quotation
     */
    public void requestQuotation(int hallID, Date startEventDate, Date endEventDate, int noOfAttendees, String eventType, char requiresCatering, String specialReq,
    double finalPrice)
    {
        Hall selectedHall = getHallById(hallID);
        selectedHall.addQuotation(getMaxQuotationId()+1,startEventDate,endEventDate,noOfAttendees,eventType, requiresCatering == 'y' ? true : false,specialReq,
            finalPrice,"PENDING",getCustomerById(getUsers().get(0).getUserId()),hallID, false);
        writeToQuotationsFile("quotations.txt",true,true, selectedHall.getQuotations());
    }

    /**
     * Method daysBetween
     * This method is used to calculate the number of days between 2 dates
     * @param d1 A first date
     * @param d2 A second date
     * @return The number of days between them
     */
    public int daysBetween(Date d1, Date d2)
    {
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }

    /**
     * Method calculatePrice
     * This method is used to calculate the final price for the quotation based on the details
     * @param startEventDate the start date time of the event
     * @param endEventDate the ending date time of the event
     * @param noOfAttendees A total number of attendees
     * @param eventType A event type
     * @param requiresCatering true if catering is required
     * @param specialReq The special requirements
     * @param hallID A hall id
     * @return The return value
     */
    public double calculatePrice(Date startEventDate, Date endEventDate, int noOfAttendees, String eventType, char requiresCatering, String specialReq, int hallID)
    {
        double multiplier = 1;
        Hall currentHall = getHallById(hallID);
        int dayDiff = daysBetween(startEventDate, endEventDate);
        if(dayDiff>0)
            multiplier *= dayDiff; 
        double atendeesMul = 1;
        if(currentHall.getCapacity()>0)
            atendeesMul = (double)noOfAttendees/(double)currentHall.getCapacity();
        multiplier *= atendeesMul;
        double typeMul = requiresCatering == 'y' ?  atendeesMul : 0;
        multiplier += typeMul;
        double scale = Math.pow(10, 2);
        return Math.round((currentHall.getPrice() * multiplier) * scale) / scale;
    }

    /**
     * Method getHallReviews
     * This method returns all the formatted reviews for a hall
     * @param hallID A hall id
     * @return The formmated reviews for the hall
     */
    public String getHallReviews(int hallID)
    {
        return getHallById(hallID).getAllReviews();
    }

    /**
     * Method getQuotationResponse
     * This method returns all the formatted quotation responses relating to a customer
     * @param customerID A customer id
     * @return The formatted quotation responses
     */
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

    /**
     * Method getQuotationDetails
     * This method returns the formatted quotation details for a user and quotation
     * @param userID A user id
     * @param quotationID A quotation id
     * @return The formatted quotation details
     */
    public String getQuotationDetails(int userID, int quotationID)
    {
        StringBuffer strBuf = new StringBuffer("");
        if(getUserRole(userID).equals("CUSTOMER"))
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

    /**
     * Method isQuotationAccepted
     * This method checks if a quotation has been accepted by the owner or not
     * @param quotationID A quotation id
     * @return true if quotation has been accepted, false if not
     */
    public boolean isQuotationAccepted(int quotationID)
    {
        for(Hall hall : halls)
        {
            for(Quotation quotation :hall.getQuotations())
            {
                if(quotation.getQuotationId() == quotationID && quotation.getQuotationStatus().equals("ACCEPTED"))
                {
                    return true;
                }

            }
        }
        return false;
    }

    /**
     * Method isBookingDepositPaid
     * This method checks if the deposit related to a booking has been paid
     * @param customerId A customer id
     * @param quotationId A quotation id
     * @return true if deposit has been paid, false if not
     */
    public boolean isBookingDepositPaid(int customerId, int quotationId)
    {
        for(Hall hall : getHalls())
        {
            for(Quotation quotation : hall.getQuotationByCustomerId(customerId))
            {
                if(quotation.getQuotationId() == quotationId) 
                {
                    return quotation.getDepositPaid();
                }
            }
        }
        return false;
    }

    /**
     * Method makeBooking
     * This method is used to make a booking related a quotation. It also writes the details to the file
     * @param customerId A customer id
     * @param quotationId A quotation id
     * @param paymentType A payment type
     */
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
        double scale = Math.pow(10, 2);
        double depositAmount = Math.round(((double)currQuotation.getFinalPrice() * ((double)currHall.getDeposit()/(double)100))*scale)/scale;
        double balanceAmount = Math.round((currQuotation.getFinalPrice() - depositAmount)*scale )/scale;
        currQuotation.setDepositPaid(true);
        String receiptNo = new SimpleDateFormat("ddMMyyyyHHmmss").format(System.currentTimeMillis());
        receiptNo += customerId;
        Payment payment = new Payment(receiptNo,depositAmount, balanceAmount,paymentType,"PARTIAL");
        Booking booking = new Booking("UPCOMING",payment,currQuotation);
        writeToBookingsAndPaymentsFile("bookings_payments.txt",booking);
    }

    /**
     * Method getReceipt
     * This method returns the formatted receipt for a booking
     * @param userID A user id
     * @param quotationID A quotation id
     * @return The formatted receipt 
     */
    public String getReceipt(int userID, int quotationID)
    {
        double scale = Math.pow(10, 2);
        StringBuffer strBuf = new StringBuffer("");
        for(Hall hall : halls)
        {
            for(Quotation quotation : hall.getQuotationByCustomerId(userID))
            {
                if(quotation.getQuotationId() == quotationID) 
                {
                    double depositAmount = Math.round(((double)quotation.getFinalPrice() * ((double)hall.getDeposit()/(double)100))*scale)/scale;
                    strBuf.append("Hall Name: " + hall.getHallName() + "\nBooking Start Date: " 
                        + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(quotation.getStartEventDateTime()) + "\nBooking End Date: " 
                        + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(quotation.getEndEventDateTime()) + "\nTotal Price: " + quotation.getFinalPrice()
                        + "\nDeposit Paid: " + depositAmount + "\nBalance: " + (Math.round((quotation.getFinalPrice() - depositAmount)*scale )/scale));
                }
            }
        }
        return strBuf.toString();
    }

    /**
     * Method getQuotationRequests
     * This method returns the formatted quotation requests for an owner's halls
     * @param ownerID An owner id
     * @return The formatted quotation requests
     */
    public String getQuotationRequests(int ownerID)
    {
        StringBuffer strBuf = new StringBuffer("");
        for(Hall hall : halls)
        {
            if(hall.getOwner().getUserId() == ownerID)
                if(!hall.getQuotationRequests().equals(""))
                    strBuf.append(hall.getQuotationRequests());
        }
        return strBuf.toString();
    }

    /**
     * Method updateQuotationStatus
     * This method is used to update a quotation's status
     * @param userID A user id
     * @param quotationID A quotation id
     * @param status the new status
     */
    public void updateQuotationStatus(int userID, int quotationID, String status)
    {
        for(Hall hall : halls)
        {
            if(hall.getOwner().getUserId() == userID)
                if(!hall.getQuotationRequestsByID(quotationID).equals(""))
                {
                    hall.getQuotationById(quotationID).setQuotationStatus(status);
                    break;
                }
        }
        writeToQuotationsFile("quotations.txt",false,false, null);

    }

    /**
     * Method getPaymentsForOwner
     * This method returns all the formatted payments for the owner
     * @return The return value
     */
    public String getPaymentsForOwner()
    {
        StringBuffer strBuf = new StringBuffer("");
        for(Booking booking : bookings)
        {
            strBuf.append(booking.getQuotation().getQuotationId() + ". " + getHallById(booking.getQuotation().getHallId()).getHallName() + ", (" 
                + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(booking.getQuotation().getStartEventDateTime()) + " -"
                + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(booking.getQuotation().getEndEventDateTime()) + ") - " + booking.getPayment().getPaymentStatus() + "\n");

        }
        return strBuf.toString();
    }

    /**
     * Method getPaymentDetails
     * This method returns the formatted payment details for a particular payment
     * @param quotationID A quotation id
     * @return The formatted payment details
     */
    public String getPaymentDetails(int quotationID)
    {
        StringBuffer strBuf = new StringBuffer("");
        for(Booking booking : bookings)
        {
            if(booking.getQuotation().getQuotationId() == quotationID)
            {
                strBuf.append(booking.getQuotation().getQuotationId() + ". " + getHallById(booking.getQuotation().getHallId()).getHallName() + ", " 
                    + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(booking.getQuotation().getStartEventDateTime()) + " "
                    + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(booking.getQuotation().getEndEventDateTime()) + " - " + booking.getPayment().getPaymentStatus());
                break;
            }
        }
        return strBuf.toString();
    }

    /**
     * Method updatePaymentnStatus
     * This method is used to update a booking's payment status
     * @param quotationID A quotation id
     * @param status the new status
     */
    public void updatePaymentnStatus(int quotationID, String status)
    {
        for(Booking booking : bookings)
        {
            if(booking.getQuotation().getQuotationId() == quotationID)
            {
                booking.getPayment().setPaymentStatus(status);
                break;
            }
        }
    }

    /*
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
    }*/

    /**
     * Method getAllUsers
     * This method returns the formatted details of all the users except administrator
     * @return The formatted user details
     */
    public String getAllUsers()
    {
        StringBuffer strBuff = new StringBuffer("");
        for(int i = 0 ; i < getUsers().size() ; i++)
        {
            if(!getUsers().get(i).getRole().equals("ADMINISTRATOR"))
            {
                strBuff.append(getUsers().get(i).display()+ "\n");
            }
        }
        return strBuff.toString();
    }

    public boolean isEmailUsed(String email)
    {
        for(User user: users)
        {
            if(user.getEmail().equalsIgnoreCase(email))
                return true;
        }
        return false;
    }
}
