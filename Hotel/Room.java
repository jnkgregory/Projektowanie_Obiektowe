import java.util.*;

enum roomStandard
{
    NORMAL(1.0),
    HIGH(2.0),
    LUXURY(3.0),
    PRESIDENT(4.0);
    
    public final double modifier;
    roomStandard(double modifier) {
        this.modifier = modifier;
    }
    
    
}

//nazwa pokoju i ilość ludzi w nim zakwaterowanych
interface RoomInfo
{
    String getRoomName();
    int getnOfBeds();
    roomStandard getRoomStandard();
    ArrayList<ReservationInfo> getReservations();
    void addReservation(ReservationInfo reservation);
}


public class Room implements RoomInfo
{
    String roomName;
    int nOfBeds;
    roomStandard standard;
    ArrayList<ReservationInfo> reservations;

    public Room(String roomName, int nOfBeds, roomStandard standard)
    {
        this.roomName = roomName;
        this.nOfBeds = nOfBeds;
        this.standard = standard;
        this.reservations = new ArrayList<ReservationInfo>();
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

    @Override
    public roomStandard getRoomStandard() { return standard; }

    @Override
    public ArrayList<ReservationInfo> getReservations()
    {
        return reservations;
    }

    @Override
    public void addReservation(ReservationInfo reservation)
    {
        reservations.add( reservation );
    }
}
