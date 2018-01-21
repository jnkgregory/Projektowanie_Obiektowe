import org.opencv.*;
import org.opencv.imgproc.*;
import org.opencv.core.*;
import org.opencv.imgcodecs.*;

import java.util.*;

public class Rectangle{

Point p1;
Point p2;
Point p3;
Point p4;


    public Rectangle (Point p1, Point p2, Point p3, Point p4){
this.p1=p1;
this.p2=p2;
this.p3=p3;
this.p4=p4;
    }


public void move(double x, double y){
this.p1.x=p1.x+x;
this.p2.x=p2.x+x;
this.p3.x=p3.x+x;
this.p4.x=p4.x+x;

this.p1.y=p1.y+y;
this.p2.y=p2.y+y;
this.p3.y=p3.y+y;
this.p4.y=p4.y+y;
}

    
public void printRec(){


System.out.println("p1:"+p1 );
System.out.println("p2:"+p2 );
System.out.println("p3:"+p3 );
System.out.println("p4:"+p4 );

}  

public void judge(Rectangle r){

if(
(this.p1.x > r.p3.x)||
(this.p3.x < r.p1.x)||
(this.p4.y > r.p2.y)||
(this.p2.y < r.p4.y)
){
System.out.println("są rozłączne");
}

else if(
(this.p1.x <= r.p1.x)&&(this.p1.y <= r.p1.y)&&
(this.p2.x <= r.p2.x)&&(this.p2.y >= r.p2.y)&&
(this.p3.x >= r.p3.x)&&(this.p3.y >= r.p3.y)&&
(this.p4.x >= r.p4.x)&&(this.p4.y <= r.p4.y)
){
System.out.println("pierwszy zawiera drugi");
}

else if(
(this.p1.x >= r.p1.x)&&(this.p1.y >= r.p1.y)&&
(this.p2.x >= r.p2.x)&&(this.p2.y <= r.p2.y)&&
(this.p3.x <= r.p3.x)&&(this.p3.y <= r.p3.y)&&
(this.p4.x <= r.p4.x)&&(this.p4.y >= r.p4.y)
){
System.out.println("drugi zawiera pierwszy");
}

else if(
(this.p1.x == r.p3.x)||
(this.p3.x == r.p1.x)||
(this.p4.y == r.p2.y)||
(this.p2.y == r.p4.y)
){
System.out.println("są styczne");
}
else{
System.out.println("przecinają się");
}

}

public void judgeEli(Elipsa e){

if(
(this.p1.x > e.a2.x)||
(this.p3.x < e.a1.x)||
(this.p4.y > e.a2.y)||
(this.p2.y < e.a1.y)
){
System.out.println("są rozłączne");
}

else if(
(this.p1.x <= e.a1.x)&&
(this.p2.y >= e.b1.y)&&
(this.p3.x >= e.a2.x)&&
(this.p4.y <= e.b2.y)
){
System.out.println("prostokąt zawiera elipsę");
}

else if(
(this.p1.x >= e.a1.x)&&
(this.p2.y <= e.b1.y)&&
(this.p3.x <= e.a2.x)&&
(this.p4.y >= e.b2.y)
){
System.out.println("elipsa zawiera prostokąt");
}

else if(
(this.p1.x == e.a2.x)||
(this.p3.x == e.a1.x)||
(this.p4.y == e.b2.y)||
(this.p2.y == e.b1.y)
){
System.out.println("są styczne");
}

if(
(this.p1.x > e.a2.x)||
(this.p3.x < e.a1.x)||
(this.p4.y > e.a2.y)||
(this.p2.y < e.a1.y)
){
System.out.println("są rozłączne");
}

if(
(this.p1.x-e.f1)

){
System.out.println("są rozłączne");
}

}



    
/*
public Rectangle fitRectangle (Point p1, Point p2, Point p3, Point p4){
return null;
}
*/
}
