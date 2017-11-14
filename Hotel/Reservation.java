import java.util.*;
import java.time.LocalDate;
import java.time.Period;

interface ReservationInfo
{
    Period getPeriod();
    List<RoomInfo> getRoomsInfo();
}


class Reservation implements  ReservationInfo
{
    Period period;
    List<RoomInfo> roomInfo;
    int howManyBeds;

    public Reservation(Period period, List<RoomInfo> roomInfo, int howManyBeds)
    {
        this.period = period;
        this.roomInfo = roomInfo;
        this.howManyBeds = howManyBeds;
    }

    @java.lang.Override
    public Period getPeriod()
    {
        return period;
    }

    @Override
    public List<RoomInfo> getRoomsInfo()
    {
        return roomInfo;
    }

    public int getHowManyBeds()
    {
        return howManyBeds;
    }
}
