import java.util.*;
import java.time.LocalDate;
import java.time.Period;

interface ReservationInfo
{
    RPeriod getRPeriod();
    List<RoomInfo> getRoomsInfo();
    int getBedsRequested();
}


public class Reservation implements  ReservationInfo
{
    RPeriod period;
    List<RoomInfo> roomInfo;
    int bedsRequested;

    public Reservation(RPeriod period, List<RoomInfo> roomInfo, int bedsRequested)
    {
        this.period = period;
        this.roomInfo = roomInfo;
        this.bedsRequested = bedsRequested;
    }

    @java.lang.Override
    public RPeriod getRPeriod()
    {
        return period;
    }

    @Override
    public List<RoomInfo> getRoomsInfo()
    {
        return roomInfo;
    }

    @Override
    public int getBedsRequested()
    {
        return bedsRequested;
    }
}
