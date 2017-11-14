//nazwa pokoju i ilość ludzi w nim zakwaterowanych
interface RoomInfo
{
    String getRoomName();
    int getnOfBeds();
    int getPeopleAccomodated();
}


class Room implements RoomInfo
{
    String roomName;
    int nOfBeds;
    int peopleAccomodated;

    public Room(String roomName, int nOfBeds, int peopleAccomodated)
    {
        this.roomName = roomName;
        this.nOfBeds = nOfBeds;
        this.peopleAccomodated = peopleAccomodated;
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

    @Override
    public int getPeopleAccomodated()
    {
        return peopleAccomodated;
    }
}
