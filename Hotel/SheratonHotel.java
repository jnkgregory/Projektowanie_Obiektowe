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
    
    
    void addRoom(String name, int nOfBeds, roomStandard standard);
    void addRoom(String name, RoomInfo room);
    void deleteRoom(String name);

    void setRoomPrice(String roomID, double priceForNight);
    void printRoomPrices();

    boolean addClient(Client client);
    Client getHotelClient(String email);
    void printClients();

    void addHoliday(String name, DateTime start, DateTime end);
    void printHolidays();
    void addHolidayPriceModifier(String holidayName, double priceModifier);
    void printHolidayPriceModifiers();

    boolean makeReservation(Client client,  ReservationInfo request);
    void printBookedReservations();
    void printRoomsInfo();
}   


public class SheratonHotel implements Hotel
{
    TreeMap<String, RoomInfo> hotelRooms;
    TreeMap<String, Double> roomPrices;
    TreeMap<String, Client> clients;
    ArrayList<Booking> bookedReservations;
    TreeMap<String, Interval> holidays;
    TreeMap<String, Double> holidayPriceModifiers;


    public SheratonHotel()
    {
        this.hotelRooms = new TreeMap<String, RoomInfo>();
        this.roomPrices = new TreeMap<String, Double>();
        this.clients = new TreeMap<String, Client>();
        this.bookedReservations = new ArrayList<Booking>();
        this.holidays = new TreeMap<String, Interval>();
        this.holidayPriceModifiers = new TreeMap<String, Double>();
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
                               ", No of beds: " + room.getValue().getnOfBeds() +
                               ", standard: " + room.getValue().getRoomStandard() );

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
    public void addRoom(String name, int nOfBeds, roomStandard standard, double price)
    {
        RoomInfo newRoom = new Room(name, nOfBeds, standard);
        hotelRooms.put(name, newRoom);
        roomPrices.put(name, price);
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
        roomPrices.remove(name);
    }


    @Override
    public void setRoomPrice(String roomID, double priceForNight)
    {
        roomPrices.put(roomID, priceForNight);
    }


    @Override
    public void printRoomPrices()
    {
        System.out.println("[ printRoomPrices ] INFO: Rooms prices by room IDs");
        for( Map.Entry<String, Double> roomPrice : roomPrices.entrySet() )
        {
            System.out.println("\n\tRoom: "+ roomPrice.getKey() +
                               "\n\tBasic price: "+ roomPrice.getValue());
        }
    }


    @Override
    public boolean addClient(Client client)
    {
        boolean ADDED = false;

        if( clients.containsKey( client.getEmail() ) )
            return ADDED;

        clients.put( client.getEmail(), client );
        ADDED = true;

        return ADDED;
    }


    @Override
    public Client getHotelClient(String email)
    {
        return clients.get( email );
    }


    @Override
    public void printClients()
    {
        System.out.println("[ printClients ] INFO: Printing clients, registered in the system");
        for( Map.Entry<String, Client> client : clients.entrySet() )
        {
            System.out.println("\tEntry: " + client.getKey() +
                               "\n\t\tName: " + client.getValue().getName() +
                               "\n\t\tEmail: " + client.getValue().getEmail() +
                               "\n\t\tType: " + client.getValue().getType());
        }
    }


    @Override
    public void addHoliday(String name, DateTime start, DateTime end)
    {
        Interval holidayPeriod = new Interval(start, end);

        holidays.put(name, holidayPeriod);
    }


    @Override
    public void printHolidays()
    {
        System.out.println("[ printHolidays ] INFO: Holidays, registered in the system.");
        for( Map.Entry<String, Interval> holiday : holidays.entrySet() )
        {
            String name = holiday.getKey();
            DateTime start = holiday.getValue().getStart();
            DateTime end = holiday.getValue().getEnd();

            System.out.println("\tHoliday name: " + name +
                               "\n\t\tStart: " + start +
                               "\n\t\tEnd: " + end);
        }
    }


    @Override
    public void addHolidayPriceModifier(String holidayName, double priceModifier)
    {
        holidayPriceModifiers.put(holidayName, priceModifier);
    }


    @Override
    public void printHolidayPriceModifiers()
    {
        System.out.println("[ printHolidayPriceModifiers ] INFO: Holidays price modifiers:");

        for( Map.Entry<String, Double> holiday : holidayPriceModifiers.entrySet() )
        {
            System.out.println("\tHoliday: " + holiday.getKey() +
                               "\n\tPrice Modifier: " + holiday.getValue() + "\n");
        }
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


    void makeBookedReservation(Client client, ReservationInfo request, double totalPrice, ArrayList<String> roomIDs)
    {
        Booking booked = new BookedReservation(client, request, totalPrice, roomIDs);
        bookedReservations.add(booked);
    }


    @Override
    public boolean makeReservation(Client client, ReservationInfo request)
    {
        boolean BOOKED = false;

        int requestedBeds = request.getBedsRequested();
        ArrayList<String> roomsWithFreeTimeSlot = findFreeRooms( request );

        int collectedBeds = 0;
        int totalPrice = 0;
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
            makeBookedReservation(client, request, totalPrice, roomsToReserve);
            BOOKED = true;
        }


        return BOOKED;
    }
}
