import org.opencv.imgproc.*;
import org.opencv.core.*;
import org.opencv.imgcodecs.*;
import java.util.*;

public class Shapes
{
    public static void main(String[] args)
    {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Mat img = Imgcodecs.imread("square.png", Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        System.out.println("[ Shapes ]INFO: Obraz wczytany");
        Imgproc.findContours(img, contours, new Mat(), Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_SIMPLE);
        for(MatOfPoint element : contours)
        { System.out.println("[ Shapes ] INFO: Contour: " + element); }

    }
}