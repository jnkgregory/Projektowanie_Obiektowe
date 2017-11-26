import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.*;
import org.joda.time.DateTime;
import org.joda.time.Interval;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;


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

        Client client = new ClientData("Zenon", "zenon@wp.pl", clientType.STUDENT);

        ReservationInfo reservation = new Reservation( new DateTime(2017, 12, 5, 0, 0, 0, 0),
                                                       new DateTime(2017, 12, 14, 0, 0, 0, 0),
                                                       4,
                                                       client);

        RoomInfo newRoom = new Room("E", 5, roomStandard.PRESIDENT);
        newRoom.addReservation(reservation);
        hotel.addRoom("E", newRoom);

        boolean reserved = hotel.makeReservation( client, reservation );

          hotel.loadRooms("danu.txt");  

        
        
        
        
        
        
        
        
        
        

        System.out.println("\n[ main ] DEBUG: Status dokonania rezerwacji: "+reserved);
        hotel.printRoomsInfo();

        hotel.addHolidayPriceModifier("Easter", 1.5);
        hotel.addHolidayPriceModifier("Majowy Weekend", 1.8);
        hotel.addHolidayPriceModifier("Boże_Ciało", 1.4);
        hotel.addHolidayPriceModifier("Boże_Narodzenie", 2.5);
        hotel.addHolidayPriceModifier("Sylwester", 4.5);


        System.out.println("\n");
        
        

          hotel.saveRooms("dane.txt");  


        // ---------- END OF TEST CODE ----------

        // ----------- Program's main loop
        boolean powerOn = false;
//        boolean powerOn = true;
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

    }
}
