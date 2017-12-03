import java.util.*;
import java.time.LocalDate;
import java.time.Period;
import org.joda.time.DateTime;
import org.joda.time.Interval;

interface Booking
{
    double getTotalPrice();
    ArrayList<String> getRoomIDs();
    Client getClient();
    ReservationInfo getBookedReservation();
}

public class BookedReservation implements Booking
{
    double totalPrice;
    ArrayList<String> roomIDs;
    Client client;
    ReservationInfo reservation;

    public BookedReservation(Client client, ReservationInfo reservation, double totalPrice, ArrayList<String> roomIDs)
    {
        this.totalPrice = totalPrice;
        this.roomIDs = roomIDs;
        this.client = client;
        this.reservation = reservation;
    }

    @Override
    public double getTotalPrice()
    {
        return totalPrice;
    }

    @Override
    public ArrayList<String> getRoomIDs()
    {
        return roomIDs;
    }

    @Override
    public Client getClient()
    {
        return client;
    }

    @Override
    public ReservationInfo getBookedReservation()
    {
        return reservation;
    }
}

