class Main
{
    public static void main(String[] args)
    {
        System.out.println("Dzialam!");

        SheratonHotel hotel = new SheratonHotel();
        hotel.addRoom("A", 3);
        hotel.deleteRoom("B");
    }
}