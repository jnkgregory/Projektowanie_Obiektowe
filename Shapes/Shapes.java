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

public class Shapes
{
    public static void main(String[] args)
    {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    
        String IMG_NAME = "i.png";
        String DEST_IMG = "src_output.png";

        Mat img = Imgcodecs.imread(IMG_NAME, Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
        Mat hierarchy = new Mat();
        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();      
        
        Imgproc.findContours(img, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_NONE);

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

    System.out.println("Hierarchy: " +hierarchy.size());
    for(int i = 0; i < hierarchy.rows(); ++i)
    {
        for(int j = 0; j < hierarchy.cols(); ++j)
        {
            System.out.print(hierarchy.get(i,j)[0]);
        }
        System.out.println(" ");
    }


    // Test of detecting edges through Canny method
    System.out.println("");

    Mat src = Imgcodecs.imread(IMG_NAME);
    Mat gray = new Mat();

    Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
    Mat edges = new Mat();

    Imgproc.Canny(gray, edges, 60, 60*3);

    Imgcodecs.imwrite("src_output.png", edges);
    System.out.println(edges + " | s: " + edges.size() + " | g: " + edges.get(1, 0)[0] + " | r: " + edges.rows() + " | c: " + edges.cols());

    } // end of main
}