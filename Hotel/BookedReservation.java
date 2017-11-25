import java.util.*;
import java.time.LocalDate;
import java.time.Period;
import org.joda.time.DateTime;
import org.joda.time.Interval;

interface Booking
{
    ArrayList<String> getRoomIDs();
    ClientData getClient();
    ReservationInfo getBookedReservation();
}

public class BookedReservation implements Booking
{
    float totalPrice;
    ArrayList<String> roomIDs;
    ClientData client;
    ReservationInfo reservation;

    public BookedReservation(ClientData client, ReservationInfo reservation, float totalPrice, ArrayList<String> roomIDs)
    {
        this.totalPrice = totalPrice;
        this.roomIDs = roomIDs;
        this.client = client;
        this.reservation = reservation;
    }

    @Override
    public ArrayList<String> getRoomIDs()
    {
        return roomIDs;
    }

    @Override
    public ClientData getClient()
    {
        return client;
    }

    @Override
    public ReservationInfo getBookedReservation()
    {
        return reservation;
    }
}
