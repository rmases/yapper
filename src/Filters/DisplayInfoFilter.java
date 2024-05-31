package Filters;

import Interfaces.PixelFilter;
import core.DImage;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class DisplayInfoFilter implements PixelFilter {
    public DisplayInfoFilter() {
        System.out.println("Filter running...");
    }

    public String getAnswers(DImage img) {
        short[][] grid = img.getBWPixelGrid();
        //ArrayList<String> answerList = new ArrayList<>();
        grid = crop(grid, 0, 0, 800, 500);
        String answers = checkPaper(grid, 12, 105, 105);
        return answers;
    }

    @Override
    public DImage processImage(DImage img) {
        short[][] grid = img.getBWPixelGrid();
        ArrayList<String> answerList = new ArrayList<>();
        grid = crop(grid, 0, 0, 800, 500);
        System.out.println(checkPaper(grid, 12, 105, 105));
        int answer1 = scanQuestion(grid, 105, 105);




        System.out.println(answer1);
       // System.out.println("Image is " + grid.length + " by " + grid[0].length)

        int bubble1Count = countBlackPixels(grid, 160, 105, 20, 105, 20);
        int bubble2Count = countBlackPixels(grid, 160, 105, 20, 130, 20);
        int bubble3Count = countBlackPixels(grid, 160, 105, 20, 155, 20);
        int bubble4Count = countBlackPixels(grid, 160, 105, 20, 180, 20);
        int bubble5Count = countBlackPixels(grid, 160, 105, 20, 205, 20);
        System.out.println(bubble1Count + " " + bubble2Count + " " + bubble3Count + " " + bubble4Count + " " +bubble5Count);


       /* int blackCount = 0;
        int whiteCount = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] < 10) blackCount++;
                if (grid[r][c] > 240) whiteCount++;
            }
        }
*/
        //System.out.println(blackCount + " nearly black pixels and " + whiteCount + " nearly white pixels");
        //System.out.println("----------------------------------------");
        //System.out.println("If you want, you could output information to a file instead of printing it.");

        img.setPixels(grid);

        return img;
    }

    public String checkPaper(short[][] grid, int numQuestions, int r, int c){
        String answers = "";
        for (int i = 0; i < numQuestions; i++){
            answers += scanQuestion(grid, r + (i*50), c);
        }
        return answers;
    }

    private int scanQuestion(short[][] grid, int r, int c) {
        int highestBlackPixels = 0;
        int answerNum = -1;
        int count = 0;
        int gap = 25;
            int bubble1Count = countBlackPixels(grid, 160, r, 20, 105, 20);
            int bubble2Count = countBlackPixels(grid, 160, r, 20, 130, 20);
            int bubble3Count = countBlackPixels(grid, 160, r, 20, 155, 20);
            int bubble4Count = countBlackPixels(grid, 160, r, 20, 180, 20);
            int bubble5Count = countBlackPixels(grid, 160, r, 20, 205, 20);
            highestBlackPixels = bubble1Count;
            answerNum = 1;
            if (highestBlackPixels < bubble2Count){
                highestBlackPixels = bubble2Count;
                answerNum = 2;
            }
            if (highestBlackPixels < bubble3Count){
                highestBlackPixels = bubble3Count;
                answerNum = 3;
            }
            if (highestBlackPixels < bubble4Count){
                highestBlackPixels = bubble4Count;
                answerNum = 4;
            }
            if (highestBlackPixels < bubble5Count){
                highestBlackPixels = bubble5Count;
                answerNum = 5;
            }

        return answerNum;
    }

    private int countBlackPixels(short[][] grid, int threshold, int r, int width, int c, int height) {
        //threshold should be 160;
        int count = 0;
        for (int i = r; i < width + r; i++) {
            for (int j = c; j < height + c; j++) {
//                System.out.println(grid[i][j]);
                if (grid[i][j] <= threshold) {
                    count++;
                }
            }
        }
        return count;
    }

    public String getAnswersFrom(DImage img) {
        short[][] grid = img.getBWPixelGrid();

        int count = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] > 200) {
                    count++;
                }
            }
        }
        return "Page has " + count + " white pixels";
    }

    public short[][] crop(short[][] grid, int r1, int c1, int r2, int c2) {
        int rLength = r2 - r1;
        int cLength = c2 - c1;
        short[][] newGrid = new short[rLength][cLength];
        for (int i = r1; i < r2; i++) {
            for (int j = c1; j < c2; j++) {
                newGrid[i][j] = grid[i][j];
            }
        }
        return newGrid;
    }
}

