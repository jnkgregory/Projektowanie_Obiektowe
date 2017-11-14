import java.io.*;
import java.util.*;

interface Hotel
{    
    void loadRooms(Reader reader);
    void saveRooms(Writer writer);
    
    
    void addRoom(String name, int nOfBeds);
    void deleteRoom(String name);
    
    //rooms jest listą liczb określających ile osób chcemy zakwaterować w pokoju
    //np.: { 1, 2} oznacza, że potrzebujemy pokoju dla jednej osoby i drugiego pokoju dla dwu osób.

    List<ReservationInfo>  findFreeRooms(Period period, List<Integer> rooms);
    boolean makeReservation(Client client,  ReservationInfo request); 
}   


interface ReservationInfo 
{
    Period getPeriod();
    List<RoomInfo> getRoomsInfo();  
}


class Reservation implements  ReservationInfo
{
    Period period;
    List<RoomInfo> roomInfo;

    public Reservation(Period period, List<RoomInfo> roomInfo)
    {
        this.period = period;
        this.roomInfo = roomInfo;
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
}

//Okres rezerwacjo od-do
interface Period 
{
}


//nazwa pokoju i ilość ludzi w nim zakwaterowanych
interface RoomInfo 
{
    String getRoomName();
    int getnOfBeds();
    int getPeopleAccomodated();
}


class Room implements RoomInfo
{
    String roomName;
    int nOfBeds;
    int peopleAccomodated;

    public Room(String roomName, int nOfBeds)
    {
        this.roomName = roomName;
        this.nOfBeds = nOfBeds;
        this.peopleAccomodated = 0;
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
    public int getPeopleAccomodated()
    {
        return peopleAccomodated;
    }
}


interface Client
{
    String getName();
    String getEmail();
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
    public List<ReservationInfo> findFreeRooms(Period period, List<Integer> rooms)
    {
        return null;
    }

    @Override
    public boolean makeReservation(Client client, ReservationInfo request)
    {
        return false;
    }


}
