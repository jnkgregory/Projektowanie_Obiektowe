import java.time.LocalDate;
import java.time.Period;

interface PeriodInfo
{
    LocalDate getStartDate();
    LocalDate getEndDate();
    Period getPeriod();
}

// R w nazwie ze wzgledu na kolizje z java.time.Period.
public class RPeriod implements PeriodInfo
{
    LocalDate start;
    LocalDate end;
    Period period;

    public RPeriod(LocalDate start, LocalDate end)
    {
        this.start = start;
        this.end = end;
        this.period = Period.between(start, end);
    }

    @Override
    public LocalDate getStartDate() { return start; }

    @Override
    public LocalDate getEndDate() { return end; }

    @Override
    public Period getPeriod() { return period; }
}