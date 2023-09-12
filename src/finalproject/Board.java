
package finalproject;

import java.io.Serializable;

public class Board implements Serializable{
    private Square[][] squares = new Square[10][10];
    private String red = "\u001B[31m";
    private String white = "\u001B[37m";
    private String green = "\u001B[32m";
    private String reset = "\u001B[0m";
    
    // board headers
    private char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
    private int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    public Board(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                squares[i][j] = new Square();
            }
        }
    }

    public void display(){
        // display column heading
        System.out.print("  ");
        for (int h = 0; h < 10; h++)
            System.out.print(green + numbers[h] + " " );
        
        System.out.println();
        for (int i = 0; i < 10; i++){
            System.out.print(green + letters[i] + "|" + reset);     // display row heading
            for (int j = 0; j < 10; j++){
                switch (squares[i][j].getStatus()){
                    case "hit": System.out.print(red + squares[i][j] + green + "|");
                        break;
                    case "miss": System.out.print(white + squares[i][j] + green + "|");
                        break;
                    default: System.out.print(green + squares[i][j] + "|" );    // display the board in 10 by 10 squares
                        break;
                }
            }
            System.out.println();
        }
    }

    public Square[][] getSquares() {
        return squares;
    }
    
}
