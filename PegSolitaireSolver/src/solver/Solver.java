/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver;

import data.Board;
import java.util.ArrayList;

/**
 *
 * @author Ryan
 */
public class Solver {
    
    public Board board, start;
    String [] moves = {"NORTH", "SOUTH", "EAST", "WEST"};
    public ArrayList<String> path = new ArrayList<String>();
    
    int counter = 0;
    
    public Solver(Board board) throws Exception{
        this.board = board;
        start = new Board();
        start.setBoard(setStart(board));
    }
    
    public boolean solve(){
        //System.out.println(counter);
        counter++;
        if(board.numPegs()==1){
            System.out.println("Number of iterations: " + counter);
            return true;
        }
        for (int i = 0; i < board.boardDim(); i++) {
            for (int j = 0; j < board.boardDim(); j++) {
                for(String dir:moves){
                    if(board.isValid(i, j, dir)){
                        board.makeMove(i,j,dir);
                        path.add(dir);
                        boolean found = solve();
                        if(found){
                            return true;
                        }else{
                            //board.printBoard();
                            board.undoLastMove();
                            //board.printBoard();
                            path.remove(path.size()-1);
                        }
                    }
                }
            }
        }
        return false;
    }
    
    private char [][] setStart(Board board)throws Exception{
        char [][] data = board.getBoard();
        board.printBoard();
        char [][] sameData = new char[data.length][data.length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                sameData[i][j] = data[i][j];
            }            
        }        
        return sameData;
    }
    
}
