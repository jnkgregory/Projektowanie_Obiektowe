import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.assertEquals;
import org.joda.time.DateTime;


public class TestSheratonHotel {

   
   SheratonHotel hotel = SheratonHotel.getInstance();
   
   @Test
   public void test_all() {	
   
      System.out.println("test_loading");    
      
                  
      assertEquals(hotel.hotelRooms.size(),0); 
      assertEquals(hotel.clients.size(),0);
      assertEquals(hotel.holidays.size(),0);
      
      	  hotel.loadClients("TESTclients.txt");  
          hotel.loadRooms("TESTdanu.txt");
   	  hotel.loadHolidays("TESTholidays.txt");
      
      assertEquals(hotel.hotelRooms.size(),4); 
      assertEquals(hotel.clients.size(),4);
      assertEquals(hotel.holidays.size(),3);
      

   System.out.println("test_adding"); 	
      assertEquals(hotel.hotelRooms.size(),4); 
      assertEquals(hotel.clients.size(),4);
      assertEquals(hotel.holidays.size(),3);
      
    hotel.addRoom("Z", 1, roomStandard.LUXURY);
    hotel.addRoom("Z", 1, roomStandard.LUXURY);
    hotel.addClient( new ClientData("Tester", "tester@test.pl", clientType.VIP));
    hotel.addClient( new ClientData("Tester", "tester@test.pl", clientType.VIP));
    hotel.addHoliday("Sesja", 9, 1, 9, 15, 0.5);
    hotel.addHoliday("Sesja", 9, 1, 9, 15, 0.5);
     
      assertEquals(hotel.hotelRooms.size(),5); 
      assertEquals(hotel.clients.size(),5);
      assertEquals(hotel.holidays.size(),4);  
      


System.out.println("test_adding_reservations"); 	
DateTime rStartDate = null;
DateTime rEndDate = null;
ReservationInfo request = null;

      assertEquals(hotel.hotelRooms.get("A").getReservations().size(),1);
      assertEquals(hotel.hotelRooms.get("B").getReservations().size(),0);
      assertEquals(hotel.hotelRooms.get("C").getReservations().size(),0);
      assertEquals(hotel.hotelRooms.get("D").getReservations().size(),0);
      assertEquals(hotel.hotelRooms.get("Z").getReservations().size(),0);

                    rStartDate = hotel.dateCh(2017,12,12);
                    rEndDate = hotel.dateCh(2017,12,15);
                    request = new Reservation(rStartDate, rEndDate, 1, hotel.getHotelClient("tester@test.pl"));
assertEquals( hotel.makeReservation(hotel.getHotelClient("tester@test.pl"), request, roomStandard.LUXURY), true);
 
                     rStartDate = hotel.dateCh(2017,12,12);
                    rEndDate = hotel.dateCh(2017,12,15);
                    request = new Reservation(rStartDate, rEndDate, 10, hotel.getHotelClient("tester@test.pl"));
assertEquals( hotel.makeReservation(hotel.getHotelClient("tester@test.pl"), request, roomStandard.NORMAL), false);
 
                     rStartDate = hotel.dateCh(2017,12,24);
                    rEndDate = hotel.dateCh(2017,12,26);
                    request = new Reservation(rStartDate, rEndDate, 10, hotel.getHotelClient("tester@test.pl"));
assertEquals( hotel.makeReservation(hotel.getHotelClient("tester@test.pl"), request, roomStandard.NORMAL), true);
 
                     rStartDate = hotel.dateCh(2017,12,12);
                    rEndDate = hotel.dateCh(2017,12,15);
                    request = new Reservation(rStartDate, rEndDate, 100, hotel.getHotelClient("tester@test.pl"));
assertEquals( hotel.makeReservation(hotel.getHotelClient("tester@test.pl"), request, roomStandard.LUXURY), false);

      assertEquals(hotel.hotelRooms.get("A").getReservations().size(),2);
      assertEquals(hotel.hotelRooms.get("B").getReservations().size(),1);
      assertEquals(hotel.hotelRooms.get("C").getReservations().size(),2);
      assertEquals(hotel.hotelRooms.get("D").getReservations().size(),0);
      assertEquals(hotel.hotelRooms.get("Z").getReservations().size(),0);


   System.out.println("test_deleting_all"); 
   
   
                    rStartDate = hotel.dateCh(2017,12,12);
                    rEndDate = hotel.dateCh(2017,12,15);
                    request = new Reservation(rStartDate, rEndDate, 1, hotel.getHotelClient("tester@test.pl"));

hotel.deleteReservation(request);

      assertEquals(hotel.hotelRooms.get("A").getReservations().size(),2);
      assertEquals(hotel.hotelRooms.get("B").getReservations().size(),1);
      assertEquals(hotel.hotelRooms.get("C").getReservations().size(),1);
      assertEquals(hotel.hotelRooms.get("D").getReservations().size(),0);
      assertEquals(hotel.hotelRooms.get("Z").getReservations().size(),0);
   
hotel.deleteClient(hotel.getHotelClient("tester@test.pl"));

      assertEquals(hotel.clients.size(),4);
   
      assertEquals(hotel.hotelRooms.get("A").getReservations().size(),1);
      assertEquals(hotel.hotelRooms.get("B").getReservations().size(),0);
      assertEquals(hotel.hotelRooms.get("C").getReservations().size(),0);
      assertEquals(hotel.hotelRooms.get("D").getReservations().size(),0);
      assertEquals(hotel.hotelRooms.get("Z").getReservations().size(),0);
      
      
      
   }
}
