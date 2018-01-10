import org.opencv.imgproc.*;
import org.opencv.core.*;
import org.opencv.imgcodecs.*;
import java.util.*;

public class Shapes
{
    public static void main(String[] args)
    {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Mat img = Imgcodecs.imread("ellipse.png", Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
        Mat hierarchy = new Mat();
        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        System.out.println("[ Shapes ]INFO: Obraz wczytany");
        
        
        Imgproc.findContours(img, contours, hierarchy, Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
        System.out.println("\n[ Shapes ] INFO: contours: " + contours.get(0).rows()+ "\n");

        for (MatOfPoint cnt : contours) 
        {
            MatOfPoint2f curve = new MatOfPoint2f(cnt.toArray());

            System.out.println("[ Shapes ] INFO: curve: " + curve + ", cnt: " + cnt + ", hierarchy: "+hierarchy.get(0,0)[0] );
        }
    }
}