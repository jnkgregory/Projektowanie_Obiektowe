interface Client
{
    String getName();
    String getEmail();
}

public class ClientData implements Client
{
    String name;
    String email;

    public ClientData(String name, String email)
    {
        this.name = name;
        this.email = email;
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
}