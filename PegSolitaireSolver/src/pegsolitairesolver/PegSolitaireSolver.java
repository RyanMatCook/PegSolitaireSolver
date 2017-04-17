/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pegsolitairesolver;

import data.Board;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import solver.Solver;

/**
 *
 * @author Ryan
 */
public class PegSolitaireSolver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        System.out.println(LocalDateTime.now() + "");
        System.out.println("Input your peg solitaire board:");
        Board b = new Board();
        Scanner sc = new Scanner(System.in);
        
        String[] row = sc.nextLine().split(" ");
        char[][] boardData = new char[row.length][row.length];
        for (int j = 0; j < row.length; j++) {
            char[] rowChar = new char[row.length];
            for (int i = 0; i < rowChar.length; i++) {
                rowChar[i] = row[i].charAt(0);
            }
            boardData[j] = rowChar;
            row = sc.nextLine().split(" ");
        }
        
        b.setBoard(boardData);
        
        //System.out.println("Enter character for open: ");
        b.setOpen('0');
        //System.out.println("Enter character for closed: ");
        b.setClosed('1');
        //System.out.println("Enter character for out of bounds: ");
        b.setOut('x');
        sc.close();
        b.printBoard();
        
        Solver solver = new Solver(b);
        
        Instant t1, t2;
        t1 = Instant.now();
        
        System.out.println("Commence solving...");
        if(solver.solve()){
            t2 = Instant.now();
            System.out.println("Solved!");
            long timeMillis = Duration.between(t1, t2).toMillis();
            long timeNanosec = Duration.between(t1, t2).toNanos();
            if(timeMillis > 0){ // time is of higher order than nano seconds
                if(timeMillis >= 1000){ // time is in seconds
                    System.out.println("Raw time:" + timeMillis);
                    long timeSec = timeMillis/1000;
                    timeMillis = timeMillis - (timeSec*1000);
                    System.out.println("Time taken in seconds: " + timeSec + "s and " + timeMillis + "ms");
                }else{ // time is in milli seconds
                    System.out.println("Time taken in milli seconds: " + timeMillis);
                }
            }else{ // checking time in the order of nanoseconds
                System.out.println("Time taken in nanoseconds: " + timeNanosec);
            }
            b.printBoard();
        }else{
            System.out.println("Unsolvable!");
        }
        
        
        
        ArrayList<String[]> rec = new ArrayList();
        for (int i = 0; i < b.moves.size(); i++) {
            String [] move = b.moves.get(i);
            switch(b.moves.get(i)[0]){
                case "NORTH":
                    move[2] = (Integer.parseInt(move[2])-2) + "";
                    break;
                case "SOUTH":
                    move[2] = (Integer.parseInt(move[2])+2) + "";
                    break;
                case "EAST":
                    move[1] = (Integer.parseInt(move[1])-2) + "";
                    break;
                case "WEST":
                    move[1] = (Integer.parseInt(move[1])+2) + "";
                    break;
            }
            rec.add(move);
        }
        
        /* 
        // Code to get boards for Experiment 1's input data
        // Apologeies for not implementing it as a function
        solver.start.setClosed(b.getClosed());
        solver.start.setOpen(b.getOpen());
        solver.start.setOut(b.getOut());
        System.out.println("Starting with");
        solver.start.printBoard();
        System.out.println("");

        for (int i = 0; i < rec.size(); i++) {
            System.out.println("(" + rec.get(i)[1] + "," + rec.get(i)[2] + ")");
            
            System.out.println("Move Index: " + i + ", Direction: " + rec.get(i)[0]);
            
            solver.start.makeMove(Integer.parseInt(rec.get(i)[1]), Integer.parseInt(rec.get(i)[2]), rec.get(i)[0]);
            System.out.println("Input:\n");
            solver.start.printBoard();
            System.out.println("\nOutput:\n\n");
            
        }
        
        */
    }

}
