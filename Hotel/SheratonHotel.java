import java.io.*;
import java.util.*;
import java.time.LocalDate;
import java.time.Period;
import org.joda.time.DateTime;
import org.joda.time.Interval;

interface Hotel
{    
    void loadRooms(Reader reader);
    void saveRooms(Writer writer);
    
    
    void addRoom(String name, int nOfBeds);
    void addRoom(String name, RoomInfo room);
    void deleteRoom(String name);

    boolean makeReservation(Client client,  ReservationInfo request);
    void printRoomsInfo();
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
    public void printRoomsInfo()
    {
        for( Map.Entry<String, RoomInfo> room : hotelRooms.entrySet() )
        {
            System.out.println("[ printRoomsInfo ] INFO: Room name: " + room.getKey() +
                               ", No of beds: " + room.getValue().getnOfBeds());

            System.out.println("[ printRoomsInfo ] INFO: Room " + room.getKey() + " reservations: ");

            ArrayList<ReservationInfo> thisRoomReservations = room.getValue().getReservations();
            if( thisRoomReservations != null && thisRoomReservations.size() > 0 )
            {
                for (ReservationInfo reservation : thisRoomReservations)
                {
                    System.out.println("\tRESERVATION");
                    System.out.println("\t\tStart: " + reservation.getStart() +
                                       "\n\t\tEnd: " + reservation.getEnd() +
                                       "\n\t\tNo of beds: " + reservation.getBedsRequested() +
                                       "\n\t\tClient: " + reservation.getClient().getEmail() +
                                       " ("+reservation.getClient().getType()+")\n");
                }
            }
            else
            {
                System.out.println("\t[ printRoomsInfo ] INFO: Room "+room.getKey()+" has no reservations.");
            }
        }
    }


    @Override
    public void addRoom(String name, int nOfBeds)
    {
        RoomInfo newRoom = new Room(name, nOfBeds);
        hotelRooms.put(name, newRoom);
    }


    @Override
    public void addRoom(String name, RoomInfo room)
    {
        hotelRooms.put(name, room);
    }


    @Override
    public void deleteRoom(String name)
    {
        hotelRooms.remove(name);
    }


    private ArrayList<String> findFreeRooms(ReservationInfo newReservation)
    {
        ArrayList<String> roomsID = new ArrayList<String>();

        for( Map.Entry<String, RoomInfo> room : hotelRooms.entrySet() )
        {
            ArrayList<ReservationInfo> thisRoomReservations = room.getValue().getReservations();
            if(thisRoomReservations.size() == 0 )
            {
                roomsID.add( room.getKey() );
            }
            if( thisRoomReservations != null && thisRoomReservations.size() > 0 )
            {
                for (ReservationInfo existingReservation : thisRoomReservations)
                {
                    Interval existingReservationIntv = new Interval( existingReservation.getStart(),
                                                                     existingReservation.getEnd() );
                    Interval newReservationIntv = new Interval( newReservation.getStart(), newReservation.getEnd() );

                    System.out.println("[ findFreeRooms ] DEBUG: existingReservation "+existingReservation.getStart());
                    if( ! existingReservationIntv.overlaps( newReservationIntv) )
                    {
                        roomsID.add(room.getKey());
                    }
                    else { System.out.println("[ findFreeRooms ] DEBUG: Pokoj "+room.getKey()+" zajety.");}
                }
            }
        }

        return roomsID;
    }


    @Override
    public boolean makeReservation(Client client, ReservationInfo request)
    {
        boolean BOOKED = false;

        int requestedBeds = request.getBedsRequested();
        ArrayList<String> roomsWithFreeTimeSlot = findFreeRooms( request );

        int collectedBeds = 0;
        ArrayList<String> roomsToReserve = new ArrayList<String>();

        if( roomsWithFreeTimeSlot.size() == 0 )
        {
            //return BOOKED;
        }

        for( String roomID: roomsWithFreeTimeSlot )
        {
            System.out.println("[ makeReservation ] DEBUG: Room with free timeslot: "+roomID+", nOfBeds: "
                    +hotelRooms.get(roomID).getnOfBeds() );

            if( collectedBeds >= requestedBeds ) break;

            collectedBeds = collectedBeds + hotelRooms.get(roomID).getnOfBeds();
            roomsToReserve.add(roomID);


        }

        if(collectedBeds >= requestedBeds )
        {
            System.out.println("[ makeReservation ] DEBUG: collectedBeds: " + collectedBeds);
            for (String roomID : roomsToReserve)
            {
                System.out.println("[ makeReservation ] DEBUG: roomsToReserve: " + roomID);
                hotelRooms.get(roomID).addReservation(request);
            }

            BOOKED = true;
        }


        return BOOKED;
    }
}
