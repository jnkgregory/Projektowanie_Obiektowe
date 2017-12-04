import java.io.*;
import java.util.*;
import java.time.LocalDate;
import java.time.Period;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Interval;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.util.InputMismatchException;

interface Hotel
{    
    void loadRooms(String file);
    void saveRooms(String file);
    
    
    RoomInfo addRoom(String name, int nOfBeds, roomStandard standard);
    RoomInfo addRoom(String name, RoomInfo room);
    void deleteRoom(String name);

    void printRoomPrices();
    
    boolean addClient(Client client);
    Client getHotelClient(String email);
    void printClients();

    void addHoliday(String name, int startMM, int startDD, int endMM, int endDD, double price);
    void printHolidays();


    boolean makeReservation(Client client,  ReservationInfo request, roomStandard minStandard);
    void printClientsReservations(Client searchClient);
    void printRoomsInfo();
}   


public class SheratonHotel implements Hotel
{

public static DateTime dateCh(int year,int month,int day){
                    
                   
                    DateTime date = new DateTime(year, month, day, 0, 0, 0, 0);
	return date;
}

public static String printDate(DateTime date){
                   String string=date.toString();
                   String[] niceDate = string.split("T");
                    
	return niceDate[0];
}

private static SheratonHotel instance = null;
private static int basePrice=100;

    TreeMap<String, RoomInfo> hotelRooms;
    TreeMap<String, Holiday> holidays;
    TreeMap<String, Client> clients;



    protected SheratonHotel()
    {
        this.hotelRooms = new TreeMap<String, RoomInfo>();
        this.holidays = new TreeMap<String, Holiday>();
        this.clients= new TreeMap<String, Client>();
    }

public static SheratonHotel getInstance() {
      if(instance == null) {
         instance = new SheratonHotel();
      }
      return instance;
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
        System.out.println("nie ma takiego pliku: "+file);
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
	for( Map.Entry<String, Client> client : clients.entrySet() ){
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
    

    public void loadHolidays(String file){
    BufferedReader reader =null;
    String line = "";

        try {
	    reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                String[] rekord = line.split(",");
                String[] dates = rekord[1].split("/");
		this.holidays.put(rekord[0], new Holiday(rekord[0], new Interval(new DateTime(dates[0]),new DateTime(dates[1])), Double.parseDouble(rekord[2])));
		
            }

        } catch (FileNotFoundException e) {
        System.out.println("nie ma takiego pliku: "+file);
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



    public void saveHolidays(String file){
        BufferedWriter writer=null;		
try {
	 writer = new BufferedWriter(new FileWriter(file));
	String content = "";
	for( Map.Entry<String, Holiday> holiday : holidays.entrySet() ){
            content=content+(holiday.getValue().name+','+holiday.getValue().interval+','+(holiday.getValue().priceModifier)+"\n");
	
                        
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
        System.out.println("nie ma takiego pliku: "+file);
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
//        roomPrices.put(name, price);        
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
    }



    @Override
    public void printRoomPrices()
    {
        System.out.println("[ printRoomPrices ] INFO: Rooms prices per day are calculated as follows:");
        System.out.println("base price * number of beds * room type * client type * holiday modifier");
        
        System.out.println("\t"+"base price = "+ basePrice);
        
        System.out.println("possible room type modifiers:");
        
        for (roomStandard p : roomStandard.values())
        System.out.println("\t"+p +" - "+ p.modifier);

        System.out.println("possible client type modifiers:");
        for (clientType p : clientType.values())
        System.out.println("\t"+p +" - "+ p.modifier);

        System.out.println("possible holiday modifiers:");
        for( Map.Entry<String, Holiday> holiday : holidays.entrySet() )
        {
            String name = holiday.getValue().name;
            DateTime start = holiday.getValue().interval.getStart();
            DateTime end = holiday.getValue().interval.getEnd();
            double price = holiday.getValue().priceModifier;

            System.out.println("\tHoliday name: " + name +
                               "\n\t\tStart: " + printDate(start) +
                               "\n\t\tEnd: " + printDate(end) +
                               "\n\t\tPrice modifier: " + price);
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

public void deleteClient(Client client){
        
for( Map.Entry<String, RoomInfo> room : hotelRooms.entrySet() )
        {

        ArrayList<ReservationInfo> thisRoomReservations = room.getValue().getReservations();
	Iterator<ReservationInfo> i = thisRoomReservations.iterator();
while (i.hasNext()) {
   ReservationInfo existingReservation = i.next(); 
   
if (client == existingReservation.getClient()){
   System.out.println("[ deleteClient ] INFO: Deleting clients reservation");
   i.remove();
   }
}

        
}


        clients.remove( client.getEmail(), client );
        System.out.println("[ deleteClients ] INFO: Deleted Client");
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
    public void addHoliday(String name, int startMM, int startDD, int endMM, int endDD, double price)
    {
    DateTime currentTime = new DateTime();
int currentYear = currentTime.getYear();
DateTime start = dateCh(currentYear,startMM,startDD);
if (start.compareTo(currentTime) < 0){ start = dateCh(currentYear+1,startMM,startDD);}
DateTime end = dateCh(currentYear,endMM,endDD);
if (end.compareTo(currentTime) < 0){ end = dateCh(currentYear+1,endMM,endDD);}
        Interval holidayPeriod = new Interval(start, end);
//hotel.addHoliday("Boże_Narodzenie",,dateCh(currentYear,12,28), 1.8);
        holidays.put(name, new Holiday(name,holidayPeriod,price));
    }

    @Override
    public void printHolidays()
    {
        System.out.println("[ printHolidays ] INFO: Holidays, registered in the system.");
        for( Map.Entry<String, Holiday> holiday : holidays.entrySet() )
        {
            String name = holiday.getValue().name;
            DateTime start = holiday.getValue().interval.getStart();
            DateTime end = holiday.getValue().interval.getEnd();
            double price = holiday.getValue().priceModifier;

            System.out.println("\tHoliday name: " + name +
                               "\n\t\tStart: " + printDate(start) +
                               "\n\t\tEnd: " + printDate(end) +
                               "\n\t\tPrice modifier: " + price);
        }
    }

    public void deleteHoliday(String name)
    {
        holidays.remove(name);
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
    public boolean makeReservation(Client client, ReservationInfo request, roomStandard minStandard)
    {
        boolean BOOKED = false;

        int requestedBeds = request.getBedsRequested();
        ArrayList<String> roomsWithFreeTimeSlot = findFreeRooms( request );

        int collectedBeds = 0;
        int totalPrice = 0;
        ArrayList<String> roomsToReserve = new ArrayList<String>();


        for( String roomID: roomsWithFreeTimeSlot )
        {
        if (minStandard.modifier <= hotelRooms.get(roomID).getRoomStandard().modifier){
            System.out.println("[ makeReservation ] DEBUG: Room with free timeslot: "+roomID+", nOfBeds: "
                    +hotelRooms.get(roomID).getnOfBeds() );

            if( collectedBeds >= requestedBeds ) break;

            collectedBeds = collectedBeds + hotelRooms.get(roomID).getnOfBeds();
            roomsToReserve.add(roomID);

	}
	else { System.out.println("[ makeReservation ] DEBUG: standard too low"); }
        }

        if(collectedBeds >= requestedBeds )
        {
            System.out.println("[ makeReservation ] DEBUG: collectedBeds: " + collectedBeds);
            for (String roomID : roomsToReserve)
            {
                System.out.println("[ makeReservation ] DEBUG: roomsToReserve: " + roomID);
                //hotelRooms.get(roomID).addReservation(request);
            }
            
            //makeBookedReservation(client, request, totalPrice, roomsToReserve);
            BOOKED = true;
        }
        
if (BOOKED==true){
int bedsNumber;

for( String roomID: roomsToReserve ){
bedsNumber=hotelRooms.get(roomID).getnOfBeds();
if (requestedBeds<bedsNumber){ bedsNumber=requestedBeds; }
else{ requestedBeds=requestedBeds-bedsNumber; }
hotelRooms.get(roomID).addReservation(new Reservation(request.getStart(), request.getEnd(), bedsNumber, request.getClient()));

}

}
        return BOOKED;
    }
    
    

    @Override
    public void printClientsReservations(Client searchClient)
    {
    double price=0;
    Days d=null;
    int days;
    int overlapDays;
    Interval interval=null;
    Interval overlap=null;
    for( Map.Entry<String, RoomInfo> room : hotelRooms.entrySet() )
        {
        ArrayList<ReservationInfo> thisRoomReservations = room.getValue().getReservations();
        for (ReservationInfo existingReservation : thisRoomReservations)
                {
                if (searchClient == existingReservation.getClient()){
                price=0;
                
                interval = new Interval( existingReservation.getStart(),existingReservation.getEnd() );
                d = Days.daysBetween(existingReservation.getStart(), existingReservation.getEnd());
                days = d.getDays();

             for( Map.Entry<String, Holiday> holiday : holidays.entrySet() )
        {
            overlap = interval.overlap( holiday.getValue().interval );
            if (overlap != null){
            d = Days.daysBetween(overlap.getStart(),overlap.getEnd());
            overlapDays=d.getDays();
            price=price + (basePrice * room.getValue().getRoomStandard().modifier * searchClient.getType().modifier * overlapDays * holiday.getValue().priceModifier);
            days=days-overlapDays;
            
            }
      
        }
             
                price=price+(basePrice * room.getValue().getRoomStandard().modifier * searchClient.getType().modifier * days);
                
                System.out.println("RESERVATION: " +
                               "\n\tRoom: " + room.getValue().getRoomName() +
                               "\n\tStart: " + printDate(existingReservation.getStart()) +
                               "\n\tEnd: " + printDate(existingReservation.getEnd()) +
                               "\n\tBeds: " + existingReservation.getBedsRequested() +
                               "\n\tPrice: " + price
                               );
                               

                }
                }
        
        
        }

    }    
    
    
public void deleteReservation(ReservationInfo reservation){
        
for( Map.Entry<String, RoomInfo> room : hotelRooms.entrySet() )
        {
        
        ArrayList<ReservationInfo> thisRoomReservations = room.getValue().getReservations();
	Iterator<ReservationInfo> i = thisRoomReservations.iterator();



while (i.hasNext()) {
   ReservationInfo existingReservation = i.next(); 
   
     if( (printDate(existingReservation.getStart()).equals(printDate(reservation.getStart())) ) &&
      (printDate(existingReservation.getEnd()).equals(printDate(reservation.getEnd())) ) &&
      (existingReservation.getClient() == reservation.getClient() ) ){
   System.out.println("[ deleteReservation ] INFO: Deleted reservation");
   i.remove();
       }
   
  }

}



    }    
    
    
}
