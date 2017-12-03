enum clientType
{
    NORMAL(1.0),
    SUPER(0.9),
    PREMIUM(0.8),
    VIP(0.7);
    
        public final double modifier;
    clientType(double modifier) {
        this.modifier = modifier;
    }
    
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
