import java.util.*;
import java.time.LocalDate;
import java.time.Period;
import org.joda.time.DateTime;
import org.joda.time.Interval;

interface Booking
{
    ClientData getClient();
    ReservationInfo getBookedReservation();
}

public class BookedReservation implements Booking
{
    @Override
    public ClientData getClient()
    {
        return null;
    }

    @Override
    public ReservationInfo getBookedReservation()
    {
        return null;
    }
}
