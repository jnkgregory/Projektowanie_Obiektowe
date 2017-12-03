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
        System.out.println("\t3: Set room price");
        System.out.println("\t4: Print rooms prices");
        System.out.println("\t5: Add holiday");
        System.out.println("\t6: Print holidays");
        System.out.println("\t7: Add holiday's price modifier");
        System.out.println("\t8: Print holiday's price modifier");
        System.out.println("\t9: Add reservation");
        System.out.println("\t10: Print booked reservations");
        System.out.println("\t11: Print rooms info");
        System.out.println("\t12: Add client");
        System.out.println("\t13: Print clients info");
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
        newRoom.addReservation(reservation);
        newRoom.addReservation(reservation2);
        hotel.addRoom("E", newRoom);

        boolean reserved = hotel.makeReservation( client, reservation );









	  hotel.loadClients("clients.txt");  
          hotel.loadRooms("danu.txt");
   
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
        
        
//        System.out.println("/////////////////////////////////////////////////////////////");
  // current time
//
//System.out.println(month);
//System.out.println("/////////////////////////////////////////////////////////////");
        System.out.println("\n");

	  hotel.saveClients("clients.txt");  
          hotel.saveRooms("dane.txt");  


        // ---------- END OF TEST CODE ----------

        // ----------- Program's main loop
//        boolean powerOn = false;
        boolean powerOn = true;
        Pattern roomType=Pattern.compile("NORMAL|HIGH|LUXURY|PRESIDENT");
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
                    //roomStandard standard = roomStandard.valueOf( new Scanner(System.in).nextLine() );
                    roomStandard standard = roomStandard.valueOf( new Scanner(System.in).next(roomType) );
                    //System.out.println("Enter room's price per night (please use comma, ex. 4,5): ");
                    //double nRoomPrice = new Scanner(System.in).nextDouble();

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
                    System.out.println("[ MAIN ] INFO: Setting room price");

                    System.out.println("Enter roomID: ");
                    String roomID = new Scanner(System.in).nextLine();
                    System.out.println("Enter room's price per night (please use comma, ex. 4,5): ");
                    //double roomPrice = new Scanner(System.in).nextDouble();

                    //hotel.setRoomPrice(roomID, roomPrice);
                    break;
                case 4:
                    System.out.println("[ MAIN ] INFO: Printing rooms prices");
                    hotel.printRoomPrices();
                    break;
                case 5:
                   // System.out.println("[ MAIN ] INFO: Adding holiday");
                   
//                    int[] startDateArr = new int[3];
  //                  int[] endDateArr = new int[3];
    //                Scanner dateReader = new Scanner(System.in);
//
  //                  System.out.println("Enter Holiday's name: ");
    //                String holidayName = new Scanner(System.in).nextLine();
      //              System.out.println("Please enter start date in a manner: YYYY MM DD");
        //            for(int i = 0; i < 3; ++i)
          //              startDateArr[i] = dateReader.nextInt();
            //        System.out.println("Please enter end date in a manner: YYYY MM DD");
              //      for(int i = 0; i < 3; ++i)
                //        endDateArr[i] = dateReader.nextInt();
//
  //                  DateTime hStartDate = new DateTime(startDateArr[0], startDateArr[1], startDateArr[2], 0, 0, 0, 0);
    //                DateTime hEndDate = new DateTime(endDateArr[0], endDateArr[1], endDateArr[2], 0, 0, 0, 0);
//
  //                  hotel.addHoliday(holidayName, hStartDate, hEndDate);

                    break;
                 case 6:
                    System.out.println("[ MAIN ] INFO: Printing holidays");
                    hotel.printHolidays();
                    break;                    
             
                case 7:
                    //System.out.println("[ MAIN ] INFO: Adding holiday's price modifier");

//                    System.out.println("Enter Holiday's name: ");
  //                  String holiday = new Scanner(System.in).nextLine();
    //                System.out.println("Enter holiday price modifier (please use comma, ex. 1,5): ");
      //              double roomPriceModifier = new Scanner(System.in).nextDouble();

        //            hotel.addHolidayPriceModifier(holiday, roomPriceModifier);
                    break;
                case 8:
          //          System.out.println("[ MAIN ] INFO: Printing holidays price modifiers");

            //        hotel.printHolidayPriceModifiers();
                    break;
                case 9:
                    System.out.println("[ MAIN ] INFO: Option: Add reservation");
                    int[] rStartDateArr = new int[3];
                    int[] rEndDateArr = new int[3];
                    int bedsNumber;
                    Scanner rDateReader = new Scanner(System.in);

                    System.out.println("Enter client's email: ");
                    String clientEmail = new Scanner(System.in).nextLine();
                    Client requestClient = hotel.getHotelClient(clientEmail);

                    if( requestClient == null )
                    {
                        System.out.println("Adding new client to the system");
                        System.out.println("Enter client's name: ");
                        String rClientName = new Scanner(System.in).nextLine();
                        System.out.println("Enter client's email: ");
                        String rClientEmail = new Scanner(System.in).nextLine();
                        System.out.println("Enter client's type (STUDENT, NORMAL, LUXURY, PRESIDENT):");
                       clientType rType = clientType.valueOf( new Scanner(System.in).nextLine() );

                        requestClient = new ClientData(rClientName, rClientEmail, rType);
                        hotel.addClient( requestClient );
                    }

                    System.out.println("Please enter reserwation start date in a manner: YYYY MM DD");
                    for(int i = 0; i < 3; ++i)
                        rStartDateArr[i] = rDateReader.nextInt();

                    System.out.println("Please enter reservation end date in a manner: YYYY MM DD");
                    for(int i = 0; i < 3; ++i)
                        rEndDateArr[i] = rDateReader.nextInt();

                    System.out.println("Please enter no of needed beds");
                    bedsNumber = rDateReader.nextInt();

                    DateTime rStartDate = new DateTime(rStartDateArr[0], rStartDateArr[1], rStartDateArr[2], 0, 0, 0, 0);
                    DateTime rEndDate = new DateTime(rEndDateArr[0], rEndDateArr[1], rEndDateArr[2], 0, 0, 0, 0);

                    ReservationInfo reservationRequest = new Reservation(rStartDate, rEndDate, bedsNumber, requestClient);
                    boolean reservationSuccess = hotel.makeReservation( requestClient, reservationRequest );

                    if( reservationSuccess )
                        System.out.println("[ MAIN ] INFO: Adding reservation success");
                    else
                        System.out.println("[ MAIN ] WARNING: Reservation rejected");                            
                    break;
                case 10:
                    System.out.println("[ MAIN ] INFO: Option: Print booked reservations");
                    hotel.printBookedReservations();
                    break;
                case 11:
                    System.out.println("[ MAIN ] INFO: Option: Print rooms info");
                    hotel.printRoomsInfo();
                    break;
                case 12:
                    System.out.println("[ MAIN ] INFO: Adding client");

                    System.out.println("Enter client's name: ");
                    String clientName = new Scanner(System.in).nextLine();
                    System.out.println("Enter client's email: ");
                    String clientEmail2 = new Scanner(System.in).nextLine();
                    System.out.println("Enter client's type (STUDENT, NORMAL, LUXURY, PRESIDENT):");
                    clientType type = clientType.valueOf( new Scanner(System.in).nextLine() );

                    Client newClient = new ClientData(clientName, clientEmail2, type);
                    hotel.addClient( newClient );

                    break;
                case 13:
                    System.out.println("[ MAIN ] INFO: Printing clients");

                    hotel.printClients();
                    break;

                case 99:
                    System.out.println("[ MAIN ] Exiting the system.");
                    powerOn = false;
                    break;
                default:
                    break;
            }
            }catch (InputMismatchException e) {
            System.out.println("ERROR Input format is incorrect please try again");
        }
            
            
System.out.println("\n---------------------------------------------------\n");
        System.out.println("[ MAIN ] Press ENTER to continue");
        Scanner stoper = new Scanner(System.in);
        stoper.nextLine();

        }

hotel.saveRooms("dane.txt");  
    }
}
