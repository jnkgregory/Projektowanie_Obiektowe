import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.*;
import org.joda.time.DateTime;
import org.joda.time.Interval;

import java.io.IOException;

import java.util.regex.Pattern;
import java.util.InputMismatchException;

// KOMPILACJA:
// javac -classpath "/.../Projektowanie_obiektowe/Hotel/joda-time-2.9.9.jar:lib/*;." *java
// ----------
// URUCHAMIANIE:
// java -cp /.../Projektowanie_Obiektowe/Hotel/joda-time-2.9.9.jar: Main

// LINK:
// https://stackoverflow.com/questions/9395207/how-to-include-jar-files-with-java-file-and-compile-in-command-prompt
// https://stackoverflow.com/questions/18938152/check-if-two-date-periods-overlap



public class Main
{




    static int printMainMenu()
    {
        int chosenOption = 0;
        Scanner optionReader = new Scanner(System.in);

        System.out.println("\n---------------------------------------------------\n");
        System.out.println("[ MAIN ] Hotel's main Control Panel. Options: ");

        System.out.println("\t1: Add room");
        System.out.println("\t2: Delete room");
        System.out.println("\t3: Print rooms info");        
        System.out.println("\t4: Add client");
        System.out.println("\t5: Delete client");
        System.out.println("\t6: Print clients info");        
        System.out.println("\t7: Add holiday");
        System.out.println("\t8: Delete holiday");        
        System.out.println("\t9: Print holidays");
        System.out.println("\t10: Print rooms prices");
        System.out.println("\t11: Add reservation");
        System.out.println("\t12: Delete reservation");
        System.out.println("\t13: Search clients reservations");
        System.out.println("\t14: load rooms from file");
        System.out.println("\t15: save rooms to file");
        System.out.println("\t16: load clients from file");
        System.out.println("\t17: save clients to file");
        System.out.println("\t18: load holidays from file");
        System.out.println("\t19: save holidays to file");
        System.out.println("\t99: Exit");
        System.out.println("\n---------------------------------------------------\n");

        System.out.println("[ MAIN ] Please enter needed option: ");
        chosenOption = optionReader.nextInt();

        return chosenOption;
    }


    public static void main(String[] args)
    {
        // ---------- TEST CODE ----------

        System.out.println("\n");

        SheratonHotel hotel = SheratonHotel.getInstance();
        /*
        
        
        hotel.addRoom("A", 3, roomStandard.NORMAL);
        hotel.addRoom("B", 3, roomStandard.NORMAL);
        hotel.addRoom("C", 3, roomStandard.HIGH);
        hotel.addRoom("D", 3, roomStandard.LUXURY);
	
        ClientData client = new ClientData("Zenon", "zenon@wp.pl", clientType.NORMAL);
        ClientData client2 = new ClientData("Zenona", "enon@wp.pl", clientType.NORMAL);
	hotel.clients.put(client.getEmail(),client);
	hotel.clients.put(client2.getEmail(),client2);
	
        ReservationInfo reservation = new Reservation( new DateTime(2017, 12, 5, 0, 0, 0, 0),
                                                       new DateTime(2017, 12, 14, 0, 0, 0, 0),
                                                       4,
                                                       client);
                                                       
                ReservationInfo reservation2 = new Reservation( new DateTime(2017, 10, 5, 0, 0, 0, 0),
                                                       new DateTime(2017, 12, 14, 0, 0, 0, 0),
                                                       3,
                                                       client2);

        RoomInfo newRoom = new Room("E", 5, roomStandard.PRESIDENT);
  //      newRoom.addReservation(reservation);
    //    newRoom.addReservation(reservation2);
        hotel.addRoom("E", newRoom);

      //  boolean reserved = hotel.makeReservation( client, reservation );









	  hotel.loadClients("clients.txt");  
          hotel.loadRooms("danu.txt");
   	  hotel.loadHolidays("holidays.txt");  
        //System.out.println("\n[ main ] DEBUG: Status dokonania rezerwacji: "+reserved);
        //hotel.printRoomsInfo();

        //hotel.addHolidayPriceModifier("Easter", 1.5);
        //hotel.addHolidayPriceModifier("Majowy Weekend", 1.8);
        //hotel.addHolidayPriceModifier("Boże_Ciało", 1.4);
        //hotel.addHolidayPriceModifier("Boże_Narodzenie", 2.5);
        //hotel.addHolidayPriceModifier("Sylwester", 4.5);
        
        
        hotel.addHoliday("Boże_Narodzenie",12,22,12,28, 2.5);
        hotel.addHoliday("Majowy Weekend",04,29,05,05, 1.8);
        hotel.addHoliday("Sylwester",12,29,01,03, 4.5);
        */
        
          hotel.loadClients("clientsIN.txt");  
          hotel.loadRooms("daneIN.txt");
   	  hotel.loadHolidays("holidaysIN.txt");  
        
        
//        System.out.println("/////////////////////////////////////////////////////////////");
  // current time
//
//System.out.println(month);
//System.out.println("/////////////////////////////////////////////////////////////");



        // ---------- END OF TEST CODE ----------

        // ----------- Program's main loop
//        boolean powerOn = false;
        boolean powerOn = true;
        Pattern roomType=Pattern.compile("NORMAL|HIGH|LUXURY|PRESIDENT");
        Pattern clientBonus=Pattern.compile("NORMAL|SUPER|PREMIUM|VIP");
        String fileName="";

        int chosenOption;

        while( powerOn )
        {
        try{
            chosenOption = printMainMenu();

            switch (chosenOption)
            {
                case 1:
                    System.out.println("[ MAIN ] INFO: Adding new room");

                    System.out.println("Enter room name: ");
                    String roomName = new Scanner(System.in).nextLine();

                    System.out.println("Enter room beds number: ");
                    int nOfBeds = new Scanner(System.in).nextInt();

                    System.out.println("Enter room's standard (NORMAL, HIGH, LUXURY, PRESIDENT)");
                    roomStandard standard = roomStandard.valueOf( new Scanner(System.in).next(roomType) );

                    hotel.addRoom(roomName, nOfBeds, standard);
                    break;
                case 2:
                    System.out.println("[ MAIN ] INFO: Deleting room");

                    System.out.println("[ MAIN ] INFO: Available rooms:");
                    hotel.printRoomsInfo();
                    System.out.println("\nEnter name (ID) of the room to be deleted:");
                    hotel.deleteRoom( new Scanner(System.in).nextLine() );
                    break;
                case 3:
                    System.out.println("[ MAIN ] INFO: Option: Print rooms info");
                    hotel.printRoomsInfo();
                    break;
                case 4:
                    System.out.println("[ MAIN ] INFO: Adding client");
                    System.out.println("Enter client's name: ");
                    String clientName = new Scanner(System.in).nextLine();
                    System.out.println("Enter client's email: ");
                    String clientEmail = new Scanner(System.in).nextLine();
                    System.out.println("Enter client's type (STUDENT, NORMAL, LUXURY, PRESIDENT):");
                    clientType type = clientType.valueOf( new Scanner(System.in).nextLine() );

                    Client newClient = new ClientData(clientName, clientEmail, type);
                    hotel.addClient( newClient );

                    break;
                case 5:
                System.out.println("[ MAIN ] INFO: Adding client");
                
                System.out.println("Enter client's email: ");
                String dclientEmail = new Scanner(System.in).nextLine();
                Client dclient = hotel.getHotelClient(dclientEmail);
                if( dclient != null ){ hotel.deleteClient(dclient); }
                else { System.out.println("no such client"); }
                    break; 
                case 6:
                    System.out.println("[ MAIN ] INFO: Printing clients");

                    hotel.printClients();
                    break;                
              
                case 7:
                    System.out.println("[ MAIN ] INFO: Adding holiday");
                    System.out.println("Enter Holiday's name: ");
                    String holidayName = new Scanner(System.in).nextLine();
                    System.out.println("Please enter start date month (MM): ");
                    int startMM = new Scanner(System.in).nextInt();
                    System.out.println("Please enter start date day (DD): ");
                    int startDD = new Scanner(System.in).nextInt();
                    System.out.println("Please enter end date month (MM): ");
                    int endMM = new Scanner(System.in).nextInt();
                    System.out.println("Please enter end date day (DD): ");
                    int endDD = new Scanner(System.in).nextInt();
                    System.out.println("Enter holiday price modifier (please use comma, ex. 1,5): ");
                    double roomPriceModifier = new Scanner(System.in).nextDouble();

                    hotel.addHoliday(holidayName,startMM,startDD,endMM,endDD, roomPriceModifier);

                    break;
                case 8:
                System.out.println("[ MAIN ] INFO: Deleting holiday");
                System.out.println("Enter Holiday's name: ");
                String dholidayName = new Scanner(System.in).nextLine();
                hotel.deleteHoliday(dholidayName);
                    break;
                 case 9:
                    System.out.println("[ MAIN ] INFO: Printing holidays");
                    hotel.printHolidays();
                    break;                    
                case 10:                
                    System.out.println("[ MAIN ] INFO: Printing rooms prices");
                    hotel.printRoomPrices();
                    break;             
                case 11:
                    System.out.println("[ MAIN ] INFO: Option: Add reservation");
                    int[] rStartDateArr = new int[3];
                    int[] rEndDateArr = new int[3];
                    int bedsNumber;
                    Scanner rDateReader = new Scanner(System.in);

                    System.out.println("Enter client's email: ");
                    String rClientEmail = new Scanner(System.in).nextLine();
                    Client requestClient = hotel.getHotelClient(rClientEmail);

                    if( requestClient == null )
                    {
                        System.out.println("Adding new client to the system");
                        System.out.println("Enter client's name: ");
                        String rClientName = new Scanner(System.in).nextLine();
                        System.out.println("Enter client's type (NORMAL, SUPER, PREMIUM, VIP):");
                        clientType rType = clientType.valueOf( new Scanner(System.in).next(clientBonus) );
                        requestClient = new ClientData(rClientName, rClientEmail, rType);
                        hotel.addClient( requestClient );
                    }

                    System.out.println("Please enter reservation start date in a manner: YYYY MM DD");
                    for(int i = 0; i < 3; ++i)
                        rStartDateArr[i] = rDateReader.nextInt();

                    System.out.println("Please enter reservation end date in a manner: YYYY MM DD");
                    for(int i = 0; i < 3; ++i)
                        rEndDateArr[i] = rDateReader.nextInt();

                    System.out.println("Please enter no of needed beds");
                    bedsNumber = rDateReader.nextInt();

                    System.out.println("Please enter minimum room standard (NORMAL, HIGH, LUXURY, PRESIDENT)");
		    roomStandard rstandard = roomStandard.valueOf( new Scanner(System.in).next(roomType) );


                    DateTime rStartDate = new DateTime(rStartDateArr[0], rStartDateArr[1], rStartDateArr[2], 0, 0, 0, 0);
                    DateTime rEndDate = new DateTime(rEndDateArr[0], rEndDateArr[1], rEndDateArr[2], 0, 0, 0, 0);

                    ReservationInfo reservationRequest = new Reservation(rStartDate, rEndDate, bedsNumber, requestClient);
                    boolean reservationSuccess = hotel.makeReservation( requestClient, reservationRequest, rstandard );

                    if( reservationSuccess )
                        System.out.println("[ MAIN ] INFO: Adding reservation success");
                    else
                        System.out.println("[ MAIN ] WARNING: Reservation rejected");                            
                    break;
                case 12:
                    System.out.println("[ MAIN ] INFO: Option: Removing reservation");
                    int[] drStartDateArr = new int[3];
                    int[] drEndDateArr = new int[3];

                    Scanner drDateReader = new Scanner(System.in);

                    System.out.println("Enter client's email: ");
                    String drClientEmail = new Scanner(System.in).nextLine();
                    Client drequestClient = hotel.getHotelClient(drClientEmail);                
                     if( drequestClient == null ) { System.out.println("no such client"); }
                     else {

                    System.out.println("Please enter reservation start date in a manner: YYYY MM DD");
                    for(int i = 0; i < 3; ++i)
                        drStartDateArr[i] = drDateReader.nextInt();

                    System.out.println("Please enter reservation end date in a manner: YYYY MM DD");
                    for(int i = 0; i < 3; ++i)
                        drEndDateArr[i] = drDateReader.nextInt();
                    DateTime drStartDate = new DateTime(drStartDateArr[0], drStartDateArr[1], drStartDateArr[2], 0, 0, 0, 0);
                    DateTime drEndDate = new DateTime(drEndDateArr[0], drEndDateArr[1], drEndDateArr[2], 0, 0, 0, 0);

                    ReservationInfo dreservationRequest = new Reservation(drStartDate, drEndDate, 0, drequestClient);
                    hotel.deleteReservation(dreservationRequest);
                                         
                     }
                    
                    break;
                case 13:
                    System.out.println("[ MAIN ] INFO: Option: Print one clients reservations");
                    
                    System.out.println("Enter client's email: ");
                    String sClientEmail = new Scanner(System.in).nextLine();
                    Client searchClient = hotel.getHotelClient(sClientEmail);
                    if( searchClient == null ){ System.out.println("No such client in  system"); }
                    else{ hotel.printClientsReservations(searchClient); }
                                        
                    break;
                case 14:
                System.out.println("[ MAIN ] INFO: Option: loading rooms from file");
                System.out.println("Enter file name: ");
                fileName = new Scanner(System.in).nextLine();
                hotel.loadRooms(fileName);
                break;
                case 15:
                System.out.println("[ MAIN ] INFO: Option: saving rooms to file");
                System.out.println("Enter file name: ");
                fileName = new Scanner(System.in).nextLine();
                hotel.saveRooms(fileName);
                break;

                case 16:
                System.out.println("[ MAIN ] INFO: Option: loading clients from file");
                System.out.println("Enter file name: ");
                fileName = new Scanner(System.in).nextLine();
                hotel.loadClients(fileName);
                break;

                case 17:
                System.out.println("[ MAIN ] INFO: Option: saving clients to file");
                System.out.println("Enter file name: ");
                fileName = new Scanner(System.in).nextLine();
                hotel.saveClients(fileName);
                break;

                case 18:
                System.out.println("[ MAIN ] INFO: Option: loading holidays from file");
                System.out.println("Enter file name: ");
                fileName = new Scanner(System.in).nextLine();
                hotel.loadHolidays(fileName);
                break;

                case 19:
                System.out.println("[ MAIN ] INFO: Option: saving holidays from file");
                System.out.println("Enter file name: ");
                fileName = new Scanner(System.in).nextLine();
                hotel.saveHolidays(fileName);
                break;

                case 99:
                    System.out.println("[ MAIN ] Exiting the system.");
                    powerOn = false;
                    break;
                default:
                    break;
            }
            }catch (InputMismatchException | IllegalArgumentException e) {
            System.out.println("ERROR Input format is incorrect please try again");
        }
            
            
System.out.println("\n---------------------------------------------------\n");
        System.out.println("[ MAIN ] Press ENTER to continue");
        Scanner stoper = new Scanner(System.in);
        stoper.nextLine();

        }

	  hotel.saveHolidays("holidaysOUT.txt");  
	  hotel.saveClients("clientsOUT.txt");  
          hotel.saveRooms("daneOUT.txt");    
    }
}
