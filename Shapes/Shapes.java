import org.opencv.imgproc.*;
import org.opencv.core.*;
import org.opencv.imgcodecs.*;
import java.util.*;

public class Shapes
{
    public static void main(String[] args)
    {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Mat img = Imgcodecs.imread("square.png");
        System.out.println(img);
    }
}