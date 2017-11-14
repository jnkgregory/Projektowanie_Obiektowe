import java.time.LocalDate;
import java.time.Period;

class Main
{
    public static void main(String[] args)
    {
        System.out.println("Dzialam!");

        SheratonHotel hotel = new SheratonHotel();
        hotel.addRoom("A", 3);
        hotel.addRoom("B", 3);
        hotel.addRoom("C", 3);
        hotel.addRoom("D", 3);

        ReservationInfo reservation = new Reservation(null, null, 4);
    }
}