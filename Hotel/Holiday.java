import java.io.*;
import java.util.*;
import java.time.LocalDate;
import java.time.Period;
import org.joda.time.DateTime;
import org.joda.time.Interval;

public class Holiday{

String name;
Interval interval;
double priceModifier;


    public Holiday(String name, Interval interval, double priceModifier)
    {
	this.name=name;
	this.interval=interval;
	this.priceModifier=priceModifier;
    }




}
