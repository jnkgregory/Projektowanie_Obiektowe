import java.io.*;
import java.util.*;
import java.time.LocalDate;
import java.time.Period;

interface Hotel
{    
    void loadRooms(Reader reader);
    void saveRooms(Writer writer);
    
    
    void addRoom(String name, int nOfBeds);
    void deleteRoom(String name);
    
    boolean makeReservation(Client client,  ReservationInfo request);
}   


public class SheratonHotel implements Hotel
{
    TreeMap<String, RoomInfo> hotelRooms;
    ArrayList<ReservationInfo> reservations;

    public SheratonHotel()
    {
        this.hotelRooms = new TreeMap<String, RoomInfo>();
        this.reservations = new ArrayList<ReservationInfo>();
    }

    @Override
    public void loadRooms(Reader reader)
    {

    }

    @Override
    public void saveRooms(Writer writer)
    {

    }

    @Override
    public void addRoom(String name, int nOfBeds)
    {
        RoomInfo newRoom = new Room(name, nOfBeds);
        hotelRooms.put(name, newRoom);
    }

    @Override
    public void deleteRoom(String name)
    {
        hotelRooms.remove(name);
    }

    @Override
    public boolean makeReservation(Client client, ReservationInfo request)
    {
        boolean BOOKED = false;

        int requestedBeds = request.getBedsRequested();

        return BOOKED;
    }
}
