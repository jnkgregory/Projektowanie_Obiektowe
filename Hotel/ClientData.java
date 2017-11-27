enum clientType
{
    STUDENT,
    NORMAL,
    LUXURY,
    PRESIDENT
}

interface Client
{
    String getName();
    String getEmail();
    clientType getType();
}

public class ClientData implements Client
{
    String name;
    String email;
    clientType type;

    public ClientData(String name, String email, clientType type)
    {
        this.name = name;
        this.email = email;
        this.type = type;
    }

    @java.lang.Override
    public String getName()
    {
        return name;
    }

    @java.lang.Override
    public String getEmail()
    {
        return email;
    }

    @java.lang.Override
    public clientType getType()
    {
        return type;
    }
}
