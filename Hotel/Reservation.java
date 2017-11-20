import java.util.*;
import java.time.LocalDate;
import java.time.Period;

interface ReservationInfo
{
    LocalDate getStart();
    LocalDate getEnd();
    int getBedsRequested();
    Client getClient();
}


public class Reservation implements  ReservationInfo
{
    LocalDate start;
    LocalDate end;
    int bedsRequested;
    Client client;

    public Reservation(LocalDate start, LocalDate end, int bedsRequested, Client client)
    {
        this.start = start;
        this.end = end;
        this.bedsRequested = bedsRequested;
        this.client = client;
    }

    @Override
    public LocalDate getStart()
    {
        return start;
    }

    @Override
    public LocalDate getEnd()
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
