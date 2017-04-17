/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.ArrayList;

/**
 *
 * @author Ryan
 */
public class Board {

    private char[][] board;
    private char open, closed, out; // open (empty) slot, closed (filled) slot, out of bounds slot
    public ArrayList<String[]> moves = new ArrayList();
    
    
    public Board() {
        
    }

    public Board(char open, char closed, char outOfBounds) {
        this.open = open;
        this.closed = closed;
        this.out = outOfBounds;
    }
    
    public int boardDim(){
        return board.length;
    }
    
    public int numPegs(){
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if(board[i][j]==closed){
                    count++;
                }
            }
        }
        return count;
    }
    
    // undoes the last move made according to last element in moves
    public void undoLastMove(){ 
        String [] move = moves.get(moves.size()-1);
        moves.remove(moves.size()-1);
        int xPos = Integer.parseInt(move[1]);
        int yPos = Integer.parseInt(move[2]);
        if(move[0].equals("NORTH")){ // move south
            board[xPos][yPos] = open;
            board[xPos][yPos-1] = closed;
            board[xPos][yPos-2] = closed;
        }
        if(move[0].equals("SOUTH")){ // move north
            board[xPos][yPos] = open;
            board[xPos][yPos+1] = closed;
            board[xPos][yPos+2] = closed;
        }
        if(move[0].equals("EAST")){ // move west
            board[xPos][yPos] = open;
            board[xPos-1][yPos] = closed;
            board[xPos-2][yPos] = closed;
        }
        if(move[0].equals("WEST")){ // move east
            board[xPos][yPos] = open;
            board[xPos+1][yPos] = closed;
            board[xPos+2][yPos] = closed;
        }
    }
    
    // makes a move in the given direction
    public void makeMove(int xPos, int yPos, String direction){
        String dir = direction;
        if(dir.equals("NORTH")/* && isValid(xPos, yPos, dir)*/){
            String [] move = {"NORTH", xPos + "", yPos+2 + ""}; // store the landing position as move            
            moves.add(move);
            board[xPos][yPos] = open;
            board[xPos][yPos+1] = open;
            board[xPos][yPos+2] = closed;
        }
        if(dir.equals("SOUTH")/* && isValid(xPos, yPos, dir)*/){
            String [] move = {"SOUTH", xPos + "", yPos-2 + ""};
            moves.add(move);
            board[xPos][yPos] = open;
            board[xPos][yPos-1] = open;
            board[xPos][yPos-2] = closed;
        }
        if(dir.equals("EAST")/* && isValid(xPos, yPos, dir)*/){ // right
            String [] move = {"EAST", xPos+2 + "", yPos + ""};
            moves.add(move);
            board[xPos][yPos] = open;
            board[xPos+1][yPos] = open;
            board[xPos+2][yPos] = closed;
        }
        if(dir.equals("WEST")/* && isValid(xPos, yPos, dir)*/){ // left
            String [] move = {"WEST", xPos-2 + "", yPos + ""};
            moves.add(move);
            board[xPos][yPos] = open;
            board[xPos-1][yPos] = open;
            board[xPos-2][yPos] = closed;
        }
    }
    
    // checks for two closed and one open slot. Also catches out of bounds
    // does not check that the move will be in board bounds,
    // but thats covered by checking if the landing location == open (empty)
    public boolean isValid(int xPos, int yPos, String dir) { // x and y pos of piece to be moved
        // check out of bounds and for two consecutive closed spots
        try {
            if (dir.equals("NORTH")) {            // up
                char test = board[xPos][yPos + 2]; // Out of bounds check
                if (board[xPos][yPos]==(closed)
                        && board[xPos][yPos + 1]==(closed)
                        && board[xPos][yPos + 2]==(open)) // end if conditional
                {
                    return true;
                }
            }
            if (dir.equals("SOUTH")) {            // down
                char test = board[xPos][yPos - 2]; // Out of bounds check
                if (board[xPos][yPos]==(closed)
                        && board[xPos][yPos - 1]==(closed)
                        && board[xPos][yPos - 2]==(open)) // end if conditional
                {
                    return true;
                }
            }
            if (dir.equals("EAST")) {            // right
                char test = board[xPos + 2][yPos]; // Out of bounds check
                if (board[xPos][yPos]==(closed)
                        && board[xPos + 1][yPos]==(closed)
                        && board[xPos + 2][yPos]==(open)) // end if conditional
                {
                    return true;
                }
            }
            if (dir.equals("WEST")) {             // left
                char test = board[xPos - 2][yPos]; // Out of bounds check
                if (board[xPos][yPos]==(closed)
                        && board[xPos - 1][yPos]==(closed)
                        && board[xPos - 2][yPos]==(open)) // end if conditional
                {
                    return true;
                }
            }
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return false;
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }

    public char getOpen() {
        return open;
    }

    public void setOpen(char open) {
        this.open = open;
    }

    public void setClosed(char closed) {
        this.closed = closed;
    }

    public void setOut(char out) {
        this.out = out;
    }

    public char getClosed() {
        return closed;
    }

    public char getOut() {
        return out;
    }
    
    public void printBoard(){
        for (int i = 0; i < board.length; i++) {
            String temp = "";
            for (int j = 0; j < board.length; j++) {
                temp = temp + board[i][j] + " ";
            }
            System.out.println(temp);
        }
    }
}
