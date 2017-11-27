import java.io.*;
import java.util.*;
import java.time.LocalDate;
import java.time.Period;
import org.joda.time.DateTime;
import org.joda.time.Interval;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

interface Hotel
{    
    void loadRooms(String file);
    void saveRooms(String file);
    
    
    RoomInfo addRoom(String name, int nOfBeds, roomStandard standard);
    RoomInfo addRoom(String name, RoomInfo room);
    void deleteRoom(String name);

    void setRoomPrice(String roomID, double priceForNight);
    void printRoomPrices();

    void addHoliday(String name, DateTime start, DateTime end);
    void addHolidayPriceModifier(String holidayName, double priceModifier);
    void printHolidayPriceModifiers();

    boolean makeReservation(Client client,  ReservationInfo request);
    void printRoomsInfo();
}   


public class SheratonHotel implements Hotel
{

    TreeMap<String, RoomInfo> hotelRooms;
    TreeMap<String, Double> roomPrices;
    ArrayList<Booking> bookedReservations;
    TreeMap<String, Interval> holidays;
    TreeMap<String, Double> holidayPriceModifiers;
    TreeMap<String, ClientData> clients;



    public SheratonHotel()
    {
        this.hotelRooms = new TreeMap<String, RoomInfo>();
        this.roomPrices = new TreeMap<String, Double>();
        this.bookedReservations = new ArrayList<Booking>();
        this.holidays = new TreeMap<String, Interval>();
        this.holidayPriceModifiers = new TreeMap<String, Double>();
        this.clients= new TreeMap<String, ClientData>();
    }


    public void loadClients(String file){
    BufferedReader reader =null;
    String line = "";

        try {
	    reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                String[] rekord = line.split(",");
		this.clients.put(rekord[1], new ClientData(rekord[0], rekord[1], clientType.valueOf(rekord[2])));
		
            }

        } catch (FileNotFoundException e) {
        System.out.println("nie ma takiego pliku");
//            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    public void saveClients(String file){
        BufferedWriter writer=null;		
try {
	 writer = new BufferedWriter(new FileWriter(file));
	String content = "";
	for( Map.Entry<String, ClientData> client : clients.entrySet() ){
            content=content+(client.getValue().getName()+','+client.getValue().getEmail()+','+(client.getValue().getType())+"\n");
                        
        }
			writer.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
}
    

    @Override
    public void loadRooms(String file)
    {
    BufferedReader reader =null;
    String line = "";

        try {
	    reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {

                String[] rekord = line.split(",");
		RoomInfo newRoom=this.addRoom(rekord[0],Integer.parseInt(rekord[1]),roomStandard.valueOf(rekord[3]));
		if(!(rekord[2].equals("null"))){
		String[] rezerwacjeList = rekord[2].split("^");
		for (int i = 0; i < rezerwacjeList.length; i++) {
		    String[] rezerwacjaInfo = rezerwacjeList[i].split(";");
		    Client client=clients.get(rezerwacjaInfo[3]);
		    newRoom.addReservation(new Reservation( new DateTime(rezerwacjaInfo[0]),
                                                       new DateTime(rezerwacjaInfo[1]),
                                                       Integer.parseInt(rezerwacjaInfo[2]),
                                                       client));
		    
		}
		}
            }
        } catch (FileNotFoundException e) {
        System.out.println("nie ma takiego pliku");
//            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


@Override
    public void saveRooms(String file){
    BufferedWriter writer=null;
		
try {
	 writer = new BufferedWriter(new FileWriter(file));
			String content = "";
	for( Map.Entry<String, RoomInfo> room : hotelRooms.entrySet() ){
            content=content+(room.getKey()+ ','+ room.getValue().getnOfBeds()+',');
            ArrayList<ReservationInfo> thisRoomReservations = room.getValue().getReservations();
            if( thisRoomReservations != null && thisRoomReservations.size() > 0 ){
                for (ReservationInfo reservation : thisRoomReservations){
                    content=content+("" + reservation.getStart() +';'+ reservation.getEnd() +';'+ reservation.getBedsRequested() +';'+ reservation.getClient().getEmail() +';'+reservation.getClient().getType()+'|');
                }
            }
            else {content=content+"null";}

            content=content+','+room.getValue().getRoomStandard()+"\n";
        }
			writer.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) writer.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
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
    public RoomInfo addRoom(String name, int nOfBeds, roomStandard standard)
    {
        RoomInfo newRoom = new Room(name, nOfBeds, standard);
        hotelRooms.put(name, newRoom);
        return newRoom;
    }
/////

    @Override
    public RoomInfo addRoom(String name, RoomInfo room)
    {
        hotelRooms.put(name, room);
        return room;
    }
/////

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
    public void addHoliday(String name, DateTime start, DateTime end)
    {
        Interval holidayPeriod = new Interval(start, end);

        holidays.put(name, holidayPeriod);
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
