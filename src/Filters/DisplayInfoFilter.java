package Filters;

import Interfaces.PixelFilter;
import core.DImage;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class DisplayInfoFilter implements PixelFilter {
    public DisplayInfoFilter() {
        System.out.println("Filter running...");
    }

    @Override
    public DImage processImage(DImage img) {
        short[][] grid = img.getBWPixelGrid();
        ArrayList<String>answerList = new ArrayList<>();
        grid = crop(grid, 0, 0, 800, 500);
        int answer1 = scanQuestion(grid, 80, 80);


        System.out.println(answer1);
        System.out.println("Image is " + grid.length + " by "+ grid[0].length);

        /*
        TODO :

        bubble1Count = countBlackPixelsAt(startR, startC);
        bubble2Count = countBlackPixelsAt(startR, startC + gapSize);
        bubble3Count = countBlackPixelsAt(startR, startC);
        bubble4Count = countBlackPixelsAt(startR, startC);
        bubble5Count = countBlackPixelsAt(startR, startC);



         */

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

    private int scanQuestion(short[][] grid, int r, int c) {
        int highestBlackPixels = 0;
        int answerNum = -1;
        int count = 0;
        int gap = 25;
        for (int i = r; i < 210; i+=25){
            count++;
            int blackPixels = countBlackPixels(grid, 160, r, 110, c, 105);
            if (blackPixels > highestBlackPixels){
                highestBlackPixels = blackPixels;
                answerNum = count;
            }
        }
        return answerNum;
        }
    private int countBlackPixels(short[][] grid,int threshold, int r1, int c1, int r2, int c2){
        //threshold should be 160;
        int count = 0;
        for (int i = r1; i < r2; i++) {
            for (int j = c1; j < c2; j++){
                if (grid[i][j] <= threshold){
                    count++;
                }
            }
        }
        return count;
    }

    public String getAnswersFrom(DImage img) {
        short[][] grid = img.getBWPixelGrid();

        int count = 0;
        for (int r = 0; r < grid.length; r++){
            for (int c = 0; c < grid[0].length; c++){
                if (grid[r][c] > 200){count++;}
            }
        }
        return "Page has " + count + " white pixels";
    }
    public short[][] crop(short[][] grid,int r1,int c1,int r2,int c2){
        int rLength = r2 - r1;
        int cLength = c2 - c1;
        short[][] newGrid = new short[rLength][cLength];
        for (int i = r1; i < r2; i++){
            for(int j = c1; j < c2; j++){
                newGrid[i][j] = grid[i][j];
            }
        }
        return newGrid;
    }
}

