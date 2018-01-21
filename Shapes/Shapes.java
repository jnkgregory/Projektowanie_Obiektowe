import org.opencv.*;
import org.opencv.imgproc.*;
import org.opencv.core.*;
import org.opencv.imgcodecs.*;

import java.util.*;



public class Shapes
{
    public static void main(String[] args)
    {


	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("\n[ Shapes ] Info: Start!\n");

   

   System.out.println("Podaj nazwę pliku z figurami: ");
   String imgsrc = new Scanner(System.in).nextLine();


        Mat img = Imgcodecs.imread(imgsrc, Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);

        Mat hierarchy = new Mat();
        List<MatOfPoint> contour = new ArrayList<MatOfPoint>();      

        Imgproc.findContours(img, contour, hierarchy, Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE );
        

       
List<Point> temp = null;
List<Point> temp2 = null;
List<Point> suspect = new LinkedList<Point>();
List<Rectangle> rectangles = new LinkedList<Rectangle>();

ArrayList<LinkedList<Point>> contours = new ArrayList<LinkedList<Point>>();
if(contour.size() == 0){
System.out.println("Nie znaleziono figur lub nie poprawna nazwa pliku");
System.exit(0);
}
     for (int i = 0; i < contour.size(); ++i) 
        {
	temp = contour.get(i).toList();
contours.add(new LinkedList<Point>());
        for (int j = 0; j < temp.size(); ++j) 
        {

  contours.get(i).add(temp.get(j));      
}
	
	}



double x;
double y;
double tx;
double ty;
double sx;
double sy;

Point p1=new Point(0.0,0.0);
Point p2=new Point(0.0,0.0);
Point p3=new Point(0.0,0.0);
Point p4=new Point(0.0,0.0);
boolean duplicate=false;



        for (int i = 0; i < contours.size(); ++i) 
        {

temp = contours.get(i);
//System.out.println(temp.size());

if(temp.size()==4) {

        for (int j = 0; j < temp.size(); ++j) 
        {
            //System.out.println(temp.get(j));
        }


Collections.sort(temp,new Comparator<Point>() {
public int compare(Point o1, Point o2) {
    return Double.compare(o1.y, o2.y);
}
});
Collections.sort(temp,new Comparator<Point>() {
public int compare(Point o1, Point o2) {
    return Double.compare(o1.x, o2.x);
}
});


p1.x=temp.get(0).x;
p1.y=temp.get(0).y;

p2.x=temp.get(1).x;
p2.y=temp.get(1).y;

p3.x=temp.get(3).x;
p3.y=temp.get(3).y;

p4.x=temp.get(2).x;
p4.y=temp.get(2).y;

for (int m = 0; m < rectangles.size(); ++m) 
{

if(
(rectangles.get(m).p1.x == p1.x)&&(rectangles.get(m).p1.y == p1.y)&&
(rectangles.get(m).p2.x == p2.x)&&(rectangles.get(m).p2.y == p2.y)&&
(rectangles.get(m).p3.x == p3.x)&&(rectangles.get(m).p3.y == p3.y)&&
(rectangles.get(m).p4.x == p4.x)&&(rectangles.get(m).p4.y == p4.y)
){
//System.out.println("DUPLICATE");
duplicate=true;
}

}
if(duplicate == false) rectangles.add(new Rectangle(p1,p2,p3,p4));
duplicate =false;

p1=new Point(0.0,0.0);
p2=new Point(0.0,0.0);
p3=new Point(0.0,0.0);
p4=new Point(0.0,0.0);


}


else if(temp.size()==8) {

Collections.sort(temp,new Comparator<Point>() {
public int compare(Point o1, Point o2) {
    return Double.compare(o1.y, o2.y);
}
});
Collections.sort(temp,new Comparator<Point>() {
public int compare(Point o1, Point o2) {
    return Double.compare(o1.x, o2.x);
}
});



if(
(temp.get(0).x == temp.get(2).x-1)&&(temp.get(0).y == temp.get(2).y+1)&&
(temp.get(1).x == temp.get(3).x-1)&&(temp.get(1).y == temp.get(3).y-1)&&
(temp.get(4).x == temp.get(6).x-1)&&(temp.get(4).y == temp.get(6).y-1)&&
(temp.get(5).x == temp.get(7).x-1)&&(temp.get(5).y == temp.get(7).y+1)
){
p1.x=temp.get(0).x;
p1.y=temp.get(0).y-1;

p2.x=temp.get(1).x;
p2.y=temp.get(1).y+1;

p3.x=temp.get(5).x+1;
p3.y=temp.get(5).y;

p4.x=temp.get(4).x+1;
p4.y=temp.get(4).y;


for (int m = 0; m < rectangles.size(); ++m) 
{

if(
(rectangles.get(m).p1.x == p1.x)&&(rectangles.get(m).p1.y == p1.y)&&
(rectangles.get(m).p2.x == p2.x)&&(rectangles.get(m).p2.y == p2.y)&&
(rectangles.get(m).p3.x == p3.x)&&(rectangles.get(m).p3.y == p3.y)&&
(rectangles.get(m).p4.x == p4.x)&&(rectangles.get(m).p4.y == p4.y)
){
//System.out.println("DUPLICATE");
duplicate=true;
}

}
if(duplicate == false) rectangles.add(new Rectangle(p1,p2,p3,p4));
duplicate =false;

p1=new Point(0.0,0.0);
p2=new Point(0.0,0.0);
p3=new Point(0.0,0.0);
p4=new Point(0.0,0.0);



}


}
else{


        for (int j = 0; j < temp.size(); ++j) 
        {

            x=temp.get(j).x;
            y=temp.get(j).y;

        for (int k = 0; k < temp.size(); ++k) 
        {
            tx=temp.get(k).x;
            ty=temp.get(k).y;

if((tx==x+1)&&(ty==y-1)){
        for (int q = 0; q < contours.size(); ++q) 
        {
temp2 = contours.get(q);
        for (int s = 0; s < temp2.size(); ++s) 
        {

            sx=temp2.get(s).x;
            sy=temp2.get(s).y;

if ((sx==x)&&(sy==ty)) { suspect.add(new Point(sx,sy));}
  }
 }
}

if((tx==x+1)&&(ty==y+1)){
        for (int q = 0; q < contours.size(); ++q) 
        {

temp2 = contours.get(q);
        for (int s = 0; s < temp2.size(); ++s) 
        {

            sx=temp2.get(s).x;
            sy=temp2.get(s).y;
if ((sx==x+1)&&(sy==ty-1)) { suspect.add(new Point(sx,sy));}
  }
 }
}

if((tx==x-1)&&(ty==y+1)){
        for (int q = 0; q < contours.size(); ++q) 
        {

temp2 = contours.get(q);
        for (int s = 0; s < temp2.size(); ++s) 
        {

            sx=temp2.get(s).x;
            sy=temp2.get(s).y;
if ((sx==x)&&(sy==ty)) { suspect.add(new Point(sx,sy)); }
  }
 }
}

if((tx==x-1)&&(ty==y-1)){
        for (int q = 0; q < contours.size(); ++q) 
        {

temp2 = contours.get(q);
        for (int s = 0; s < temp2.size(); ++s) 
        {

            sx=temp2.get(s).x;
            sy=temp2.get(s).y;
if ((sx==x-1)&&(sy==ty+1)) { suspect.add(new Point(sx,sy));}
  }
 }
}


        }//for k



        }// for j

}
        for (int j = 0; j < temp.size(); ++j) 
        {
            //System.out.println(temp.get(j));
        }

        }// for i


Collections.sort(suspect,new Comparator<Point>() {
public int compare(Point o1, Point o2) {
    return Double.compare(o1.y, o2.y);
}
});

Collections.sort(suspect,new Comparator<Point>() {
public int compare(Point o1, Point o2) {
    return Double.compare(o1.x, o2.x);
}
});


//System.out.println("Suspects:");
        for (int i = 0; i < suspect.size(); ++i) 
        {
            //System.out.println(suspect.get(i));
        }

int i=0;
int j=0;
int k=0;
int l=0;

int size=suspect.size();

while (i < size){
	p1=suspect.get(i);
	while (j < size){
		if((p1.y!=suspect.get(j).y)&&(p1.x==suspect.get(j).x)){
		p2=suspect.get(j);
		while (k < size){

			if((p2.y==suspect.get(k).y)&&(p2.x!=suspect.get(k).x)){

			p3=suspect.get(k);

			while (l < size){

				if( (p1.y==suspect.get(l).y)&&(p3.x==suspect.get(l).x) ){
				p4=suspect.get(l);

for (int m = 0; m < rectangles.size(); ++m) 
{

if(
(rectangles.get(m).p1.x == p1.x)&&(rectangles.get(m).p1.y == p1.y)&&
(rectangles.get(m).p2.x == p2.x)&&(rectangles.get(m).p2.y == p2.y)&&
(rectangles.get(m).p3.x == p3.x)&&(rectangles.get(m).p3.y == p3.y)&&
(rectangles.get(m).p4.x == p4.x)&&(rectangles.get(m).p4.y == p4.y)
){
//System.out.println("DUPLICATE");
duplicate=true;
}

}
if(duplicate == false) rectangles.add(new Rectangle(p1,p2,p3,p4));
duplicate =false;

suspect.remove(p1);
suspect.remove(p2);
suspect.remove(p3);
suspect.remove(p4);

i=-1;
j=size;
k=size;
l=size;


}
			l++;		
			}
			}
		k++;
		}
		}
	j++;
	}
i++;
j=0;
k=0;
l=0;
size=suspect.size();
}



//ELI

Iterator<Point> it = null;



        for (int m = 0; m < rectangles.size(); ++m) 
        {
        for (int i2 = 0; i2 < contours.size(); ++i2) 
        {
it = contours.get(i2).iterator();

while (it.hasNext()) {
   Point p = it.next(); 

if(
(p.x == rectangles.get(m).p1.x)||
(p.x == rectangles.get(m).p3.x)||
(p.y == rectangles.get(m).p2.y)||
(p.y == rectangles.get(m).p4.y)
){
it.remove();
}   
}

}
}

int pointsLeft=0;
    
            for (int m = 0; m < contours.size(); ++m) 
        {

pointsLeft=pointsLeft+contours.get(m).size();



}
double a1=0.0;
double a2=999999.0;
double b1=0.0;
double b2=999999.0;
    
        if( pointsLeft > 50 ){
        for (int m = 0; m < contours.size(); ++m) 
        {

temp = contours.get(m);

        for (int n = 0; n < temp.size(); ++n) 
        {
            if(temp.get(j).x < a1) a1 = temp.get(j).x;
            if(temp.get(j).x > a2) a2 = temp.get(j).x;            
            if(temp.get(j).y < b1) b1 = temp.get(j).y;            
            if(temp.get(j).y > b2) b2 = temp.get(j).y;

            
        }
        }
Elipsa eli = new Elipsa(a1,a2,b1,b2);

        for (int m = 0; m < rectangles.size(); ++m) 
        {
        System.out.println("Elipsa i prostokąt nr "+m+":");
            rectangles.get(m).judgeEli(eli);
        }

}

//ELI




System.out.println();
        for (int m = 0; m < rectangles.size(); ++m) 
        {
        System.out.println("Prostokąt nr "+m+":");
            rectangles.get(m).printRec();
        }






System.out.println();






        for (int m = 0; m < rectangles.size(); ++m) 
        {
        for (int n = 0; n < rectangles.size(); ++n) 
        {
        if(m!=n){
        System.out.println("Prostokąty nr "+m+" i nr "+n+" :");
            rectangles.get(m).judge(rectangles.get(n));
        }
        }
System.out.println();
        }


System.out.println("Wybierz nr figury, którą chcesz przesunąć");
int figNum = new Scanner(System.in).nextInt();

if(rectangles.size() <= figNum){
System.out.println("Nie ma figury o tym numerze");
System.exit(0);
}

System.out.println("Podaj wartość x wektora przesunięcia");
double vx = new Scanner(System.in).nextDouble();
System.out.println("Podaj wartość y wektora przesunięcia");
double vy = new Scanner(System.in).nextDouble();
rectangles.get(figNum).move(vx,vy);


        for (int n = 0; n < rectangles.size(); ++n) 
        {
        if(figNum!=n){
        System.out.println("Prostokąty nr "+figNum+" i nr "+n+" :");
            rectangles.get(figNum).judge(rectangles.get(n));
}
        }













    } // end of main
}


