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

        System.out.println("\n---------------------------------------------------");
        System.out.println("\n[ MAIN ] Hotel's main Control Panel. Options: ");

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
        System.out.println("\n---------------------------------------------------");

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

        hotel.printRoomsInfo();

        //ArrayList<String> freeRooms = hotel.findFreeRooms( reservation );
        //System.out.println("[ main ] Wypis rezerwacji: ");
        //for(String roomID : freeRooms )
        //{
        //    System.out.println("\t[ main ] ID wolnego pokoju: "+roomID);
        //}

        boolean reserved = hotel.makeReservation( client, reservation );

        System.out.println("\n[ main ] DEBUG: Status dokonania rezerwacji: "+reserved);
        hotel.printRoomsInfo();

        hotel.addHolidayPriceModifier("Easter", 1.5);
        hotel.addHolidayPriceModifier("Majowy Weekend", 1.8);
        hotel.addHolidayPriceModifier("Boże_Ciało", 1.4);
        hotel.addHolidayPriceModifier("Boże_Narodzenie", 2.5);
        hotel.addHolidayPriceModifier("Sylwester", 4.5);

        System.out.println("\n[ main ] DEBUG: Wypis modyfikatorów cen za okresy świąteczne.");
        hotel.printHolidayPriceModifiers();

        System.out.println("\n");

        // ---------- END OF TEST CODE ----------

        // ----------- Program's main loop
        boolean powerOn = true;
        int chosenOption = printMainMenu();

        while( powerOn )
        {
            switch (chosenOption)
            {
                case 1:
                    System.out.println("[ MAIN ] INFO: Option: Add room");

                    System.out.println("Enter room name: ");
                    String roomName = new Scanner(System.in).nextLine();

                    System.out.println("Enter room beds number: ");
                    int nOfBeds = new Scanner(System.in).nextInt();

                    System.out.println("Enter room's standard (NORMAL, HIGH, LUXURY, PRESIDENT");
                    roomStandard standard = roomStandard.valueOf( new Scanner(System.in).nextLine() );

                    hotel.addRoom(roomName, nOfBeds, standard);

                    chosenOption = printMainMenu();
                    break;
                case 2:
                    System.out.println("[ MAIN ] INFO: Option: Delete room");
                    chosenOption = printMainMenu();
                    break;
                case 3:
                    System.out.println("[ MAIN ] INFO: Option: Set room price");
                    chosenOption = printMainMenu();
                    break;
                case 4:
                    System.out.println("[ MAIN ] INFO: Option: Print rooms prices");
                    chosenOption = printMainMenu();
                    break;
                case 5:
                    System.out.println("[ MAIN ] INFO: Option: Add holiday");
                    chosenOption = printMainMenu();
                    break;
                case 6:
                    System.out.println("[ MAIN ] INFO: Option: Add holiday's price modifier");
                    chosenOption = printMainMenu();
                    break;
                case 7:
                    System.out.println("[ MAIN ] INFO: Option: Print holidays price modifiers");
                    chosenOption = printMainMenu();
                    break;
                case 8:
                    System.out.println("[ MAIN ] INFO: Option: Add reservation");
                    chosenOption = printMainMenu();
                    break;
                case 9:
                    System.out.println("[ MAIN ] INFO: Option: Print rooms info");
                    hotel.printRoomsInfo();
                    chosenOption = printMainMenu();
                    break;
                case 10:
                    System.out.println("[ MAIN ] INFO: Option: Test option");
                    chosenOption = printMainMenu();
                    break;
                case 11:
                    System.out.println("[ MAIN ] Exiting the system.");
                    powerOn = false;
                    break;
                default:
                    chosenOption = printMainMenu();
                    break;
            }
        }

    }
}