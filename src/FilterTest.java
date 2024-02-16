import FileIO.PDFHelper;
import Filters.DisplayInfoFilter;
import core.DImage;
import core.DisplayWindow;
import processing.core.PImage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FilterTest {
    public static String currentFolder = System.getProperty("user.dir") + "/";

    public static void main(String[] args) {
            //SaveAndDisplayExample();
        RunTheFilter();
    }

    private static void RunTheFilter() {
        System.out.println("Loading pdf....");// you can make a DImage from a PImage

        System.out.println("Running filter on page 1....");
        DisplayInfoFilter filter = new DisplayInfoFilter();

        ArrayList<PImage> pageList = PDFHelper.getPImagesFromPdf("assets/OfficialOMRSampleDoc.pdf");
        int pageAmount = pageList.size();
        ArrayList<String> results = new ArrayList<>();
        for (int i = 1; i <= pageAmount; i++) {
            PImage in = PDFHelper.getPageImage("assets/OfficialOMRSampleDoc.pdf", i);
            DImage img = new DImage(in);
            results.add(filter.getAnswers(img));
            //filter.processImage(img);  // if you want, you can make a different method
            // that does the image processing an returns a DTO with
            // the information you want
        }
        String key = results.get(0);
        String allLines = "page, # right, q1, q2, q3 q4 q5 q6 q7 q8 q9 q10 q11 q12";
        Integer wrongQuestions[] = {0,0,0,0,0,0,0,0,0,0,0,0};

        for (int i = 1; i <= pageAmount; i++) {
            String line = i + ", ";
            String answers = "";
            String pageAnswers = results.get(i - 1);
            int correctCount = 0;
            for (int b = 0; b < 12; b++) {
                if (pageAnswers.substring(b, b + 1).equals(key.substring(b, b + 1))) {
                    correctCount++;
                    answers += ", right";
                } else {
                    answers += ", wrong";
                    wrongQuestions[b]++;
                }
            }

            line += correctCount;
            line += answers;
            allLines += "\n" + line;
            System.out.println(line);
            //System.out.println(wrongTotal);

        }
        String wrongTotal = "";
        for (int b = 0; b < 12; b++){
            int c = b+ 1;
            wrongTotal += "\n" + wrongQuestions[b] + " students got question " + c + " wrong.";
        }
        System.out.println(results);
        System.out.println(wrongTotal);

        try (FileWriter f = new FileWriter("answers.txt");
             BufferedWriter b = new BufferedWriter(f);
             PrintWriter writer = new PrintWriter(b);) {


            writer.println(allLines);


        } catch (IOException errorObj) {
            System.err.println("There was a problem writing to the file: answers.txt");
                    errorObj.printStackTrace();
        }


        try (FileWriter f = new FileWriter("itemAnalysis.txt");
             BufferedWriter b = new BufferedWriter(f);
             PrintWriter writer = new PrintWriter(b);) {


            writer.println(wrongTotal);


        } catch (IOException errorObj) {
            System.err.println("There was a problem writing to the file: itemAnalysis.txt");
            errorObj.printStackTrace();
        }





    }

    private static void SaveAndDisplayExample() {
        int page = 3;
        String fileName = "assets/page" + page + ".png";
        PImage img = PDFHelper.getPageImage("assets/OfficialOMRSampleDoc.pdf",page);
        img.save(currentFolder + fileName);

        DisplayWindow.showFor(fileName);
    }
}