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
    public static void main(String[] args)
    {
        System.out.println("\n");

        SheratonHotel hotel = new SheratonHotel();
        hotel.addRoom("A", 3);
        hotel.addRoom("B", 3);
        hotel.addRoom("C", 3);
        hotel.addRoom("D", 3);

        Client client = new ClientData("Zenon", "zenon@wp.pl", clientType.STUDENT);

        ReservationInfo reservation = new Reservation( new DateTime(2017, 12, 5, 0, 0, 0, 0),
                                                       new DateTime(2017, 12, 14, 0, 0, 0, 0),
                                                       4,
                                                       client);

        RoomInfo newRoom = new Room("E", 5);
        newRoom.addReservation(reservation);
        hotel.addRoom("E", newRoom);

        hotel.printRoomsInfo();

        ArrayList<String> freeRooms = hotel.findFreeRooms( reservation );
        System.out.println("[ main ] Wypis rezerwacji: ");
        for(String roomID : freeRooms )
        {
            System.out.println("\t[ main ] ID wolnego pokoju: "+roomID);
        }

        hotel.makeReservation( client, reservation );

        System.out.println("\n");
    }
}