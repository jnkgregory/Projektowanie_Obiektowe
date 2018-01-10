import org.opencv.imgproc.*;
import org.opencv.core.*;
import org.opencv.imgcodecs.*;
import java.util.*;

// https://docs.opencv.org/java/2.4.9/org/opencv/imgproc/Imgproc.html#findContours(org.opencv.core.Mat, java.util.List, org.opencv.core.Mat, int, int)
// https://docs.opencv.org/java/2.4.8/org/opencv/core/MatOfPoint.html
// https://docs.opencv.org/java/2.4.8/org/opencv/core/Point.html
// https://www.programcreek.com/java-api-examples/index.php?api=org.opencv.core.MatOfPoint

public class Shapes
{
    public static void main(String[] args)
    {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Mat img = Imgcodecs.imread("eclipse.png", Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
        Mat hierarchy = new Mat();
        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();      
        
        Imgproc.findContours(img, contours, hierarchy, Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_NONE);

        //System.out.println("\n[ Shapes ] INFO: contours: " + contours.get(0).rows()+ "\n");
        System.out.println("[ Shapes ] INFO: contours size: " + contours.size());
        for (int i = 0; i < contours.size(); ++i) 
        {
            System.out.println(contours.get(i).getClass().getName() + ", " + contours.get(i).size());
            for(int j = 0; j < contours.get(i).rows(); ++j)
            {
                //System.out.println(contours.get(i));
                Point[] points = contours.get(i).toArray();
                //System.out.println("[ Shapes ] INFO: points length: " + points.length);

                //for(Point point: points)
                //{
                //    System.out.println("[ Shapes ] INFO: point: "+ point.toString());
                //}
            }
        }
        
        //Mat destination = new Mat(img.rows(), img.cols(), img.type());
        //Imgcodecs.imwrite("Shapes_output.png", img);

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

            //draw enclosing rectangle (all same color, but you could use variable i to make them unique)
            Imgproc.rectangle(img,
                    new Point(rect.x,rect.y),
                    new Point(rect.x+rect.width,rect.y+rect.height),
                    new Scalar(20, 0, 0, 20),3);

        }
    }
}