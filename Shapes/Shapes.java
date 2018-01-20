import org.opencv.*;
import org.opencv.imgproc.*;
import org.opencv.core.*;
import org.opencv.imgcodecs.*;

import java.util.*;


// https://docs.opencv.org/java/2.4.9/org/opencv/imgproc/Imgproc.html#findContours(org.opencv.core.Mat, java.util.List, org.opencv.core.Mat, int, int)
// https://docs.opencv.org/java/2.4.8/org/opencv/core/MatOfPoint.html
// https://docs.opencv.org/java/2.4.8/org/opencv/core/Point.html
// https://www.programcreek.com/java-api-examples/index.php?api=org.opencv.core.MatOfPoint
// http://opencvexamples.blogspot.com/2013/09/find-contour.html
// https://stackoverflow.com/questions/18581633/fill-in-and-detect-contour-rectangles-in-java-opencv
// http://answers.opencv.org/question/25755/drawing-bounding-box-in-java/
// https://docs.opencv.org/3.4.0/d9/d8b/tutorial_py_contours_hierarchy.html
// http://answers.opencv.org/question/97124/find-if-line-and-polygon-are-intersecting-in-java-opencv/
// https://stackoverflow.com/questions/16021832/using-method-convertto-in-opencv-java-wrapper


// https://media.readthedocs.org/pdf/opencv-java-tutorials/stable/opencv-java-tutorials.pdf
// http://blog.ayoungprogrammer.com/2013/04/tutorial-detecting-multiple-rectangles.html/
// https://docs.opencv.org/3.2.0/dc/d0d/tutorial_py_features_harris.html
// https://laxmaredy.blogspot.co.uk/2014/06/blog-post_6263.html
// https://stackoverflow.com/questions/41413509/opencv-java-harris-corner-detection
// http://answers.opencv.org/question/11571/getting-a-point-from-a-contour-opencv-android/
// https://stackoverflow.com/questions/31746044/mat-to-matofpoint2f
// http://answers.opencv.org/question/75473/opencv-blob-detection-java-with-params/
// http://answers.opencv.org/question/38885/how-to-detect-ellipse-and-get-centers-of-ellipse/
// https://docs.opencv.org/2.4.1/doc/tutorials/imgproc/shapedescriptors/bounding_rotated_ellipses/bounding_rotated_ellipses.html
// http://opencvexamples.blogspot.com/2013/10/fitting-rotated-boxes-and-ellipses-for.html

// https://boofcv.org/index.php?title=Main_Page



public class Shapes
{
    public static void main(String[] args)
    {
        System.out.println("hello!");
        /*
//System.out.println(System.getProperty("java.library.path"));

	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("\n[ Shapes ] Info: Start!\n");
String imgsrc="4.png";    

        String IMG_NAME = imgsrc;

        String DEST_IMG = "src_output.png";

        // Edges detection through Canny + write result to output file.

        Mat src = Imgcodecs.imread(IMG_NAME);
        Mat gray = new Mat();

        Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
        Mat edges = new Mat();

        Imgproc.Canny(gray, edges, 60, 60*3);

        Imgcodecs.imwrite("src_output.png", edges);
        System.out.println(edges + " | s: " + edges.size() + " | g: " + edges.get(1, 0)[0] + " | r: " + edges.rows() + " | c: " + edges.cols());


        // Detection using findContours on the output image from above.

        //Mat img = Imgcodecs.imread(DEST_IMG, Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
        Mat img = Imgcodecs.imread(imgsrc, Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
        Mat hierarchy = new Mat();
        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();      
        
//        Imgproc.findContours(img, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_NONE);
        Imgproc.findContours(img, contours, hierarchy, Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE );
        */
        
//TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT
       

       LinkedList<Point> L1 = new LinkedList<Point>();
       LinkedList<Point> L2 = new LinkedList<Point>();
       LinkedList<Point> L3 = new LinkedList<Point>();
       LinkedList<Point> L4 = new LinkedList<Point>();
       LinkedList<Point> L5 = new LinkedList<Point>();

L1.add(new Point(148.0, 177.0));
L1.add(new Point(148.0, 233.0));
L1.add(new Point(268.0, 233.0));
L1.add(new Point(268.0, 177.0));

L2.add(new Point(148.0, 118.0));
L2.add(new Point(148.0, 175.0));
L2.add(new Point(268.0, 175.0));
L2.add(new Point(268.0, 118.0));

L3.add(new Point(70.0, 60.0));
L3.add(new Point(70.0, 175.0));
L3.add(new Point(146.0, 175.0));
L3.add(new Point(146.0, 117.0));
L3.add(new Point(147.0, 116.0));
L3.add(new Point(269.0, 116.0));
L3.add(new Point(270.0, 117.0));
L3.add(new Point(270.0, 175.0));
L3.add(new Point(299.0, 175.0));
L3.add(new Point(299.0, 60.0));

L4.add(new Point(68.0, 59.0));
L4.add(new Point(69.0, 58.0));
L4.add(new Point(300.0, 58.0));
L4.add(new Point(301.0, 59.0));
L4.add(new Point(301.0, 176.0));
L4.add(new Point(300.0, 177.0));
L4.add(new Point(270.0, 177.0));
L4.add(new Point(270.0, 234.0));
L4.add(new Point(269.0, 235.0));
L4.add(new Point(147.0, 235.0));
L4.add(new Point(146.0, 234.0));
L4.add(new Point(146.0, 177.0));
L4.add(new Point(69.0, 177.0));
L4.add(new Point(68.0, 176.0));

L5.add(new Point(0.0, 0.0));
L5.add(new Point(0.0, 289.0));
L5.add(new Point(386.0, 289.0));
L5.add(new Point(386.0, 0.0));
       
       ArrayList<LinkedList<Point>> contours = new ArrayList<LinkedList<Point>>();       
       
contours.add(L1);
contours.add(L2);
contours.add(L3);
contours.add(L4);
contours.add(L5);       
//TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT

       
List<Point> temp = null;
List<Point> temp2 = null;
List<Point> suspect = new LinkedList<Point>();
List<Rectangle> rectangles = new LinkedList<Rectangle>();

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

//        System.out.println("[ Shapes ] INFO: contours size: " + contours.size());
//        for (int i = 0; i < contours.size(); ++i) 
//        {
//            System.out.println(contours.get(i).getClass().getName() + ", " + contours.get(i).size() + ", rows: " +contours.get(i).rows());
//        }


        for (int i = 0; i < contours.size(); ++i) 
        {

temp = contours.get(i);//.toList();
System.out.println(temp.size());

if(temp.size()==4) {

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

if ((p1.x!=0.0)&&(p1.y!=0.0)){

p1.x=p1.x-1;
p1.y=p1.y-1;

p2.x=temp.get(1).x;
p2.y=temp.get(1).y;

p2.x=p2.x-1;
p2.y=p2.y+1;

p3.x=temp.get(3).x;
p3.y=temp.get(3).y;

p3.x=p3.x+1;
p3.y=p3.y+1;

p4.x=temp.get(2).x;
p4.y=temp.get(2).y;

p4.x=p4.x+1;
p4.y=p4.y-1;

rectangles.add(new Rectangle(p1,p2,p3,p4));
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
temp2 = contours.get(q);//.toList();
        for (int s = 0; s < temp2.size(); ++s) 
        {

            sx=temp2.get(s).x;
            sy=temp2.get(s).y;
//System.out.println("p3["+sx+";"+sy+"]");

if ((sx==x+2)&&(sy==ty+2)) {System.out.println("sus"); suspect.add(new Point(x+1,ty+1));}
  }
 }
}

if((tx==x+1)&&(ty==y+1)){
        for (int q = 0; q < contours.size(); ++q) 
        {

temp2 = contours.get(q);//.toList();
        for (int s = 0; s < temp2.size(); ++s) 
        {

            sx=temp2.get(s).x;
            sy=temp2.get(s).y;
if ((sx==x-1)&&(sy==ty+1)) {System.out.println("sus"); suspect.add(new Point(x,ty));}
  }
 }
}

if((tx==x-1)&&(ty==y+1)){
        for (int q = 0; q < contours.size(); ++q) 
        {

temp2 = contours.get(q);//.toList();
        for (int s = 0; s < temp2.size(); ++s) 
        {

            sx=temp2.get(s).x;
            sy=temp2.get(s).y;
if ((sx==x-2)&&(sy==ty-2)) {System.out.println("sus"); suspect.add(new Point(x-1,ty-1)); 

}
  }
 }
}

if((tx==x-1)&&(ty==y-1)){
        for (int q = 0; q < contours.size(); ++q) 
        {

temp2 = contours.get(q);//.toList();
        for (int s = 0; s < temp2.size(); ++s) 
        {

            sx=temp2.get(s).x;
            sy=temp2.get(s).y;
if ((sx==x+1)&&(sy==ty-1)) {System.out.println("sus"); suspect.add(new Point(x,ty));}
  }
 }
}

/*
System.out.println("&&&&&&&&&&");
System.out.println("p1("+x+";"+y+")");
System.out.println("p2("+tx+";"+ty+")");
System.out.println("p3["+sx+";"+sy+"]");
*/

//System.out.println("!!!!!!!!!!!!!");
//System.out.println("p3["+sx+";"+sy+"]");

//if((x==tx+1)&&(y==ty-1)){temp.get(j).x=tx;temp.get(j).y=ty;}
//if((x==tx+1)&&(y==ty-1)){temp.get(j).x=tx;temp.get(j).y=ty;}
//if((x==tx+1)&&(y==ty-1)){temp.get(j).x=tx;temp.get(j).y=ty;}


//System.out.println("!!!!!!!!!!!!!");
//System.out.println("("+x+";"+y+")");
//System.out.println("("+tx+";"+ty+")");


        }//for k


            //System.out.println(temp.get(j));
            //System.out.println("("+x+";"+y+")");
        }// for j

}
        for (int j = 0; j < temp.size(); ++j) 
        {
            System.out.println(temp.get(j));
        }
            //System.out.println(contours.get(i).getClass().getName() + ", " + contours.get(i).size() + ", rows: " +contours.get(i).rows());
        }// for i
System.out.println("Suspects:");
        for (int i = 0; i < suspect.size(); ++i) 
        {
            System.out.println(suspect.get(i));
        }



//movies.forEach(System.out::println);

Collections.sort(suspect,new Comparator<Point>() {
public int compare(Point o1, Point o2) {
    return Double.compare(o1.y, o2.y);
}
});

System.out.println("Suspects Y:");
        for (int i = 0; i < suspect.size(); ++i) 
        {
            System.out.println(suspect.get(i));
        }


Collections.sort(suspect,new Comparator<Point>() {
public int compare(Point o1, Point o2) {
    return Double.compare(o1.x, o2.x);
}
});


System.out.println("Suspects X:");
        for (int i = 0; i < suspect.size(); ++i) 
        {
            System.out.println(suspect.get(i));
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
//System.out.println("k");
			if((p2.y==suspect.get(k).y)&&(p2.x!=suspect.get(k).x)){
//System.out.println("l fou");
			p3=suspect.get(k);
//System.out.println(i +" "+ j +" "+ k +" "+ l +" "+ size +" "+ suspect.size());
			while (l < size){
//System.out.println("l");
				if( (p1.y==suspect.get(l).y)&&(p3.x==suspect.get(l).x) ){
				p4=suspect.get(l);


rectangles.add(new Rectangle(p1,p2,p3,p4));

suspect.remove(p1);
suspect.remove(p2);
suspect.remove(p3);
suspect.remove(p4);
//suspect.remove(l);
//suspect.remove(k);
//suspect.remove(j);
//suspect.remove(i);
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

System.out.println("----");
System.out.println("----");
System.out.println("----");

double vx=10.0;
double vy=-10.0;

rectangles.get(1).move(vx,vy);

        for (int m = 0; m < rectangles.size(); ++m) 
        {
        System.out.println("Prostokąt nr "+m+":");
            rectangles.get(m).printRec();
        }


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



















    } // end of main
}




/*

4
{148.0, 177.0}
{148.0, 233.0}
{268.0, 233.0}
{268.0, 177.0}
4
{148.0, 118.0}
{148.0, 175.0}
{268.0, 175.0}
{268.0, 118.0}
10
{70.0, 60.0}
{70.0, 175.0}
{146.0, 175.0}
{146.0, 117.0}
{147.0, 116.0}
{269.0, 116.0}
{270.0, 117.0}
{270.0, 175.0}
{299.0, 175.0}
{299.0, 60.0}
14
{68.0, 59.0}
{69.0, 58.0}
{300.0, 58.0}
{301.0, 59.0}
{301.0, 176.0}
{300.0, 177.0}
{270.0, 177.0}
{270.0, 234.0}
{269.0, 235.0}
{147.0, 235.0}
{146.0, 234.0}
{146.0, 177.0}
{69.0, 177.0}
{68.0, 176.0}
4
{0.0, 0.0}
{0.0, 289.0}
{386.0, 289.0}
{386.0, 0.0}



Suspects:
{147.0, 117.0}
{269.0, 117.0}
{69.0, 59.0}
{300.0, 59.0}
{300.0, 176.0}
{269.0, 234.0}
{147.0, 234.0}
{69.0, 176.0}
Suspects Y:
{69.0, 59.0}
{300.0, 59.0}
{147.0, 117.0}
{269.0, 117.0}
{300.0, 176.0}
{69.0, 176.0}
{269.0, 234.0}
{147.0, 234.0}
Suspects X:
{69.0, 59.0}
{69.0, 176.0}
{147.0, 117.0}
{147.0, 234.0}
{269.0, 117.0}
{269.0, 234.0}
{300.0, 59.0}
{300.0, 176.0}




*/


