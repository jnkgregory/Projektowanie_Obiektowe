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

public class Shapes
{
    public static void main(String[] args)
    {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("\n[ Shapes ] Info: Start!\n");
    
        String IMG_NAME = "i.png";
        String DEST_IMG = "src_output.png";

        // Edges detection through Canny + write result to output file.

        Mat src = Imgcodecs.imread(IMG_NAME);
        Mat gray = new Mat();

        Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
        Mat edges = new Mat();

        Imgproc.Canny(gray, edges, 60, 60*3);

        Imgcodecs.imwrite("src_output.png", edges);
        System.out.println(edges + " | s: " + edges.size() + " | g: " + edges.get(1, 0)[0] + " | r: " + edges.rows() + " | c: " + edges.cols());


        // Detecction using findContours on the output image from above.

        Mat img = Imgcodecs.imread(DEST_IMG, Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
        Mat hierarchy = new Mat();
        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();      
        
        Imgproc.findContours(img, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_NONE);

        System.out.println("[ Shapes ] INFO: contours size: " + contours.size());
        for (int i = 0; i < contours.size(); ++i) 
        {
            System.out.println(contours.get(i).getClass().getName() + ", " + contours.get(i).size());
        }

        System.out.println("Hierarchy: " +hierarchy.size());
        for(int i = 0; i < hierarchy.rows(); ++i)
        {
            for(int j = 0; j < hierarchy.cols(); ++j)
            {
                System.out.print(hierarchy.get(i,j)[0]);
            }
            System.out.println(" ");
        }

    
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
            Imgproc.rectangle(edges, new Point(rect.x,rect.y), new Point(rect.x+rect.width,rect.y+rect.height), new Scalar(120, 0, 255), 3); 

        }
        Imgcodecs.imwrite("src_output.png", edges);


    } // end of main
}