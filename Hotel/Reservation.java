import java.util.*;
import java.time.LocalDate;
import java.time.Period;
import org.joda.time.DateTime;
import org.joda.time.Interval;

interface ReservationInfo
{
    DateTime getStart();
    DateTime getEnd();
    int getBedsRequested();
    Client getClient();
}


public class Reservation implements  ReservationInfo
{
    DateTime start;
    DateTime end;
    int bedsRequested;
    Client client;

    public Reservation(DateTime start, DateTime end, int bedsRequested, Client client)
    {
        this.start = start;
        this.end = end;
        this.bedsRequested = bedsRequested;
        this.client = client;
    }

    @Override
    public DateTime getStart()
    {
        return start;
    }

    @Override
    public DateTime getEnd()
    {
        return end;
    }

    @Override
    public int getBedsRequested()
    {
        return bedsRequested;
    }

    @Override
    public Client getClient()
    {
        return client;
    }
}
