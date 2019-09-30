/**
 * Write a description of class PrimeEvent here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PrimeEvent
{
    // instance variables - replace the example below with your own
    private EventController event;
    /**
     * Constructor for objects of class PrimeEvent
     */
    public PrimeEvent()
    {
        event = new EventController();
    }

    public void start()
    {
        event.start();
    }

    
}
