import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.*;
import org.joda.time.DateTime;
import org.joda.time.Interval;


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
        System.out.println("\t6: Add holiday's price modifier");
        System.out.println("\t7: Print holiday's price modifier");
        System.out.println("\t8: Add reservation");
        System.out.println("\t9: Print rooms info");
        System.out.println("\t11: Exit");
        System.out.println("\n---------------------------------------------------\n");

        System.out.println("[ MAIN ] Please enter needed option: ");
        chosenOption = optionReader.nextInt();

        return chosenOption;
    }


    public static void main(String[] args)
    {
        // ---------- TEST CODE ----------

        System.out.println("\n");

        SheratonHotel hotel = new SheratonHotel();
        
        
        
        hotel.addRoom("A", 3, roomStandard.NORMAL);
        hotel.addRoom("B", 3, roomStandard.NORMAL);
        hotel.addRoom("C", 3, roomStandard.HIGH);
        hotel.addRoom("D", 3, roomStandard.LUXURY);
	
        ClientData client = new ClientData("Zenon", "zenon@wp.pl", clientType.STUDENT);
        ClientData client2 = new ClientData("Zenona", "enon@wp.pl", clientType.STUDENT);
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

        hotel.addHolidayPriceModifier("Easter", 1.5);
        hotel.addHolidayPriceModifier("Majowy Weekend", 1.8);
        hotel.addHolidayPriceModifier("Boże_Ciało", 1.4);
        hotel.addHolidayPriceModifier("Boże_Narodzenie", 2.5);
        hotel.addHolidayPriceModifier("Sylwester", 4.5);


        System.out.println("\n");

	  hotel.saveClients("clients.txt");  
          hotel.saveRooms("dane.txt");  


        // ---------- END OF TEST CODE ----------

        // ----------- Program's main loop
//        boolean powerOn = false;
        boolean powerOn = true;
        int chosenOption;

        while( powerOn )
        {
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
                    roomStandard standard = roomStandard.valueOf( new Scanner(System.in).nextLine() );

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
                    double roomPrice = new Scanner(System.in).nextDouble();

                    hotel.setRoomPrice(roomID, roomPrice);
                    break;
                case 4:
                    System.out.println("[ MAIN ] INFO: Printing rooms prices");
                    hotel.printRoomPrices();
                    break;
                case 5:
                    System.out.println("[ MAIN ] INFO: Adding holiday");
                    break;
                case 6:
                    System.out.println("[ MAIN ] INFO: Adding holiday's price modifier");

                    System.out.println("Enter Holiday's name: ");
                    String holiday = new Scanner(System.in).nextLine();
                    System.out.println("Enter holiday price modifier (please use comma, ex. 1,5): ");
                    double roomPriceModifier = new Scanner(System.in).nextDouble();

                    hotel.addHolidayPriceModifier(holiday, roomPriceModifier);
                    break;
                case 7:
                    System.out.println("[ MAIN ] INFO: Printing holidays price modifiers");

                    hotel.printHolidayPriceModifiers();
                    break;
                case 8:
                    System.out.println("[ MAIN ] INFO: Option: Add reservation");
                    System.out.println("Enter Clients email: ");
                    String clientX = new Scanner(System.in).nextLine();
                    System.out.println("Enter starting date(please use proper format, ex. 2017-12-05T00:00:00.000+01:00): ");
                    String startDate = new Scanner(System.in).nextLine();
                    System.out.println("Enter finishing date(please use proper format, ex. 2017-12-05T00:00:00.000+01:00): ");
                    String finishDate = new Scanner(System.in).nextLine();
                    System.out.println("Enter beds number:  ");
                    int nOfBeds2 = new Scanner(System.in).nextInt();
                    
                    ReservationInfo reservationX = new Reservation( new DateTime(startDate),
                                                       new DateTime(finishDate),
                                                       nOfBeds2,
                                                       hotel.clients.get(clientX));
                    boolean reserved2 = hotel.makeReservation( hotel.clients.get(clientX), reservationX );
                    if(reserved2) System.out.println("Success");
                    else {System.out.println("Failure");}                            
                    break;
                case 9:
                    System.out.println("[ MAIN ] INFO: Option: Print rooms info");
                    hotel.printRoomsInfo();
                    break;
                case 10:
                    System.out.println("[ MAIN ] INFO: Option: Test option");
                    break;
                case 11:
                    System.out.println("[ MAIN ] Exiting the system.");
                    powerOn = false;
                    break;
                default:
                    break;
            }
        }

hotel.saveRooms("dane.txt");  
    }
}
