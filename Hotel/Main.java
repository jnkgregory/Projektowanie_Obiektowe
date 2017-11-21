import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

class Main
{
    public static void main(String[] args)
    {
        System.out.println("\n");

        System.out.println("[ main ] Dzialam!");

        SheratonHotel hotel = new SheratonHotel();
        hotel.addRoom("A", 3);
        hotel.addRoom("B", 3);
        hotel.addRoom("C", 3);
        hotel.addRoom("D", 3);

        Client client = new ClientData("Zenon", "zenon@wp.pl", clientType.STUDENT);

        ReservationInfo reservation = new Reservation( LocalDate.of(2017, Month.DECEMBER, 5),
                                                       LocalDate.of(2017, Month.DECEMBER, 14),
                                                       4,
                                                       client);

        System.out.println( "[ main ] Reservation. Start:"+reservation.getStart()+", stop: "+reservation.getEnd() );

        System.out.println("[ main ] Client. Type: "+client.getType());

        RoomInfo newRoom = new Room("E", 5);
        newRoom.addReservation(reservation);
        hotel.addRoom("E", newRoom);

        hotel.printRoomsInfo();

        System.out.println("\n");
    }
}