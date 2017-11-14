//nazwa pokoju i ilość ludzi w nim zakwaterowanych
interface RoomInfo
{
    String getRoomName();
    int getnOfBeds();
}


class Room implements RoomInfo
{
    String roomName;
    int nOfBeds;

    public Room(String roomName, int nOfBeds)
    {
        this.roomName = roomName;
        this.nOfBeds = nOfBeds;
    }

    @Override
    public String getRoomName()
    {
        return roomName;
    }

    @Override
    public int getnOfBeds()
    {
        return nOfBeds;
    }
}
