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

        /*
        =)
        Your code here to...
        (1).  Load the pdf
        (2).  Loop over its pages
        (3).  Create a DImage from each page and process its pixels
        (4).  Output 2 csv files
         */

    }

    private static String fileChooser() {
        String userDirLocation = System.getProperty("user.dir");
        File userDir = new File(userDirLocation);
        JFileChooser fc = new JFileChooser(userDir);
        int returnVal = fc.showOpenDialog(null);
        File file = fc.getSelectedFile();
        return file.getAbsolutePath();
    }
}
