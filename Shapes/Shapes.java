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
//System.out.println(System.getProperty("java.library.path"));

	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("\n[ Shapes ] Info: Start!\n");
String imgsrc="11.png";    

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
List<Point> temp = null;
List<Point> temp2 = null;
List<Point> suspect = new LinkedList<Point>();

double x;
double y;
double tx;
double ty;
double sx;
double sy;

//        System.out.println("[ Shapes ] INFO: contours size: " + contours.size());
//        for (int i = 0; i < contours.size(); ++i) 
//        {
//            System.out.println(contours.get(i).getClass().getName() + ", " + contours.get(i).size() + ", rows: " +contours.get(i).rows());
//        }


        for (int i = 0; i < contours.size(); ++i) 
        {

temp = contours.get(i).toList();
System.out.println(temp.size());
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
temp2 = contours.get(q).toList();
        for (int s = 0; s < temp2.size(); ++s) 
        {

            sx=temp2.get(s).x;
            sy=temp2.get(s).y;
//System.out.println("p3["+sx+";"+sy+"]");
if ((sx==x+2)&&(sy==ty+2)) { suspect.add(new Point(x+1,ty+1));}
  }
 }
}

if((tx==x+1)&&(ty==y+1)){
        for (int q = 0; q < contours.size(); ++q) 
        {

temp2 = contours.get(q).toList();
        for (int s = 0; s < temp2.size(); ++s) 
        {

            sx=temp2.get(s).x;
            sy=temp2.get(s).y;
if ((sx==x-1)&&(sy==ty+1)) { suspect.add(new Point(x,ty));}
  }
 }
}

if((tx==x-1)&&(ty==y+1)){
        for (int q = 0; q < contours.size(); ++q) 
        {

temp2 = contours.get(q).toList();
        for (int s = 0; s < temp2.size(); ++s) 
        {

            sx=temp2.get(s).x;
            sy=temp2.get(s).y;
if ((sx==x-2)&&(sy==ty-2)) { suspect.add(new Point(x-1,ty-1)); 

}
  }
 }
}

if((tx==x-1)&&(ty==y-1)){
        for (int q = 0; q < contours.size(); ++q) 
        {

temp2 = contours.get(q).toList();
        for (int s = 0; s < temp2.size(); ++s) 
        {

            sx=temp2.get(s).x;
            sy=temp2.get(s).y;
if ((sx==x+1)&&(sy==ty-1)) { suspect.add(new Point(x,ty));}
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


        for (int j = 0; j < temp.size(); ++j) 
        {
            System.out.println(temp.get(j));
        }
            //System.out.println(contours.get(i).getClass().getName() + ", " + contours.get(i).size() + ", rows: " +contours.get(i).rows());
        }
System.out.println("Suspects:");
        for (int i = 0; i < suspect.size(); ++i) 
        {
            System.out.println(suspect.get(i));
        }

/*


for(int i = 0; i < contours.size(); i++)
  {
    for(int j = 0; j < contours.get(i).size(); j++){

System.out.println( "x:" + contours[i][j].x + " y:" + contours[i][j].y );
}
// cout << contours[i][j].x << "x" << contours[i][j].y << " ";
  }
/*

        System.out.println("Hierarchy: " +hierarchy.size());
        for(int i = 0; i < hierarchy.rows(); ++i)
        {
            for(int j = 0; j < hierarchy.cols(); ++j)
            {
                System.out.println(hierarchy.get(i,j)[0]);
            }
            System.out.println(" ");
        }

//System.out.println(contours);


        // Test code
/*
        MatOfPoint2f approxCurve = new MatOfPoint2f();

        //For each contour found
        for (int i=0; i<contours.size(); i++)
        {
            //Convert contours(i) from MatOfPoint to MatOfPoint2f
            MatOfPoint2f contour2f = new MatOfPoint2f( contours.get(i).toArray() );
            //Processing on mMOP2f1 which is in type MatOfPoint2f
            double approxDistance = Imgproc.arcLength(contour2f, true)*0.02;
            Imgproc.approxPolyDP(contour2f, approxCurve, approxDistance, true);

            //Convert back to MatOfPoint
            MatOfPoint points = new MatOfPoint( approxCurve.toArray() );

            // Get bounding rect of contour
            Rect rect = Imgproc.boundingRect(points);

            // draw enclosing rectangle (all same color, but you could use variable i to make them unique)
            Imgproc.rectangle(edges, new Point(rect.x,rect.y), new Point(rect.x+rect.width,rect.y+rect.height), new Scalar(81, 190, 0), 3);
        }
        //Imgcodecs.imwrite("src_output.png", edges);
        System.out.println("[ Shapes ] INFO: edges size: " + edges.size());
        Mat cnt = contours.get(0);
        System.out.println("[ Shapes ] INFO: contours.get(0).contourArea() : " + Imgproc.contourArea(cnt));
*/
        // End of test code
/*

        // cornerHarris tests - wlasciwy kod programu.

        Mat testImg = Imgcodecs.imread(imgsrc);
        Mat grayImg = new Mat();
        Mat dstImg = new Mat();
        Mat grayFloat = new Mat();
        MatOfPoint corners = new MatOfPoint();
        Mat circles = new Mat();

        Imgproc.cvtColor(testImg, grayImg, Imgproc.COLOR_BGR2GRAY);
        grayImg.convertTo(grayFloat, CvType.CV_32F);
        
        Imgproc.cornerHarris(grayFloat, dstImg, 2, 3, 0.04);
        //Imgproc.dilate(grayImg, dstImg, Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(25,25)));
        System.out.println("[ Shapes ] INFO: dst (corners): "+ dstImg.size());
        
        Imgcodecs.imwrite("A.png", dstImg);
 
        // Znajdowanie prostokatow.

        Imgproc.goodFeaturesToTrack(dstImg, corners, 50, 0.35, 3);
        System.out.println("[ Shapes ] INFO: corners size (wierzcholki): " + corners.size());

        for(int r = 0; r < corners.rows(); ++r)
            for(int c = 0; c < corners.cols(); ++c)
                System.out.println("[ Shapes ] INFO: corner: " + corners.get(r, c)[0]);

        Point [] cornersPoints = corners.toArray();
        for(Point point : cornersPoints)
            System.out.println(point);
*/
    } // end of main
}
