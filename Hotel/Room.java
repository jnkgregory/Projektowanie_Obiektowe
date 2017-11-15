import java.util.*;

//nazwa pokoju i ilość ludzi w nim zakwaterowanych
interface RoomInfo
{
    String getRoomName();
    int getnOfBeds();
    ArrayList<ReservationInfo> getReservations();
}


public class Room implements RoomInfo
{
    String roomName;
    int nOfBeds;
    ArrayList<ReservationInfo> reservations;

    public Room(String roomName, int nOfBeds)
    {
        this.roomName = roomName;
        this.nOfBeds = nOfBeds;
    }

    @Override
    public String getRoomName()
    {
        return roomName;
    }

    @Override
    public int getnOfBeds()
    {
        return nOfBeds;
    }

    public ArrayList<ReservationInfo> getReservations()
    {
        return reservations;
    }

    public void addReservation(ReservationInfo reservation)
    {
        reservations.add( reservation );
    }
}
