import javax.swing.*;
import java.io.File;
import FileIO.*;
import Filters.DisplayInfoFilter;
import core.DImage;
import processing.core.PImage;

// Author: David Dobervich (this is my edit)
// ANOTHER EDIT.
public class OpticalMarkReaderMain {
    public static void main(String[] args) {
//        String pathToPdf = fileChooser();
//        System.out.println("Loading pdf at " + pathToPdf);

        PImage in = PDFHelper.getPageImage("assets/omrtest.pdf",1);
        DImage img = new DImage(in);       // you can make a DImage from a PImage

        DisplayInfoFilter filter = new DisplayInfoFilter();
        String result = filter.getAnswersFrom(img);
//        filter.processImage(img);

        PDFHelper test = new PDFHelper();







//        (2).  Loop over its pages
//        (3).  Create a DImage from each page and process its pixels
//        (4).  Output 2 csv files
//

    }
//    private static void RunTheFilter() {
//        System.out.println("Loading pdf....");// you can make a DImage from a PImage
//
//        System.out.println("Running filter on page 1....");
//        DisplayInfoFilter filter = new DisplayInfoFilter();
//        for (int i = 1; i <= 3; i++) {
//            PImage in = PDFHelper.getPageImage("assets/OfficialOMRSampleDoc.pdf", i);
//            DImage img = new DImage(in);
//            filter.processImage(img);  // if you want, you can make a different method
//            // that does the image processing an returns a DTO with
//            // the information you want
//        }
//    }

    private static String fileChooser() {
        String userDirLocation = System.getProperty("user.dir");
        File userDir = new File(userDirLocation);
        JFileChooser fc = new JFileChooser(userDir);
        int returnVal = fc.showOpenDialog(null);
        File file = fc.getSelectedFile();
        return file.getAbsolutePath();
    }
}
