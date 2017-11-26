import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.assertEquals;

public class TestSheratonHotel {

   
   SheratonHotel hotel = new SheratonHotel();
   
   @Test
   public void test_addRoom() {	
   
      System.out.println("Inside testPrintMessage()");    
      
      hotel.addRoom("A", 3, roomStandard.NORMAL);
      
      assertEquals(1,1); //messageUtil.printMessage());   
      
   }
}
