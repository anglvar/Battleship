
package finalproject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

public class Game implements Serializable{
    private Logic logic = new Logic();
    private boolean ended = false;
    //private final Scanner input = new Scanner(System.in);
    private int[] coordinates;
    private String green = "\u001B[32m";
    
    public Game(){
    }
    
    public void menu(){
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("\n" + green + "1. New Game\n" + green + "2. Load Game");
            System.out.print(green + "Choose an option: ");

            try {
                int option = input.nextInt();
                switch (option) {
                    case 1:
                        startGame();
                        break;
                    case 2:
                        Game game = loadGame();
                        game.playGame();
                        break;
                    default:
                        System.out.println(green + "Error. Enter 1 or 2. ");
                        break;
                }
            } catch (Exception ex) {
                System.out.println(green + ex);
                input.nextLine();
            }
        }while (true);
            
    }
    
    private void startGame(){
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < 5; i++){
            try{
                System.out.println("\n" + green + logic.getCurrentPlayer()); // displays current player
                boolean valid = false;
                logic.shoMine();
                coordinates = enterCord("Enter the coordinates to place a ship. A-J and 1-10. Example(A1): ");
                valid = logic.setShips(coordinates[0], coordinates[1], i);

                if (!valid)
                    i--;
            } catch (Exception ex){
                System.out.println(ex);
            }
        }

        /*for (int i = 0; i < 5; i++) {
            try {
                System.out.println("\n" + green + logic.getCurrentPlayer() + "(Your Board)"); // displays current player
                boolean valid = false;
                do {
                    // asks user for coordinates to set ships, returned as array of string
                    coordinates = enterCord("Enter the coordinates to place a ship. A-J and 1-10. Example(A1): ");

                    // sets ship on board if valid == true
                    valid = logic.setShips(coordinates[0], coordinates[1], i);
                } while (valid == false);

            } catch (Exception ex) {
                System.out.println(green + "Error. Ship will be out of bounds, enter a higher letter.");
            }
            
        }   */
        logic.flipSquares();            // flips squares so ships cannot be seen
        
        // AI sets ships
        logic.changeCurrentPlayer();
        logic.AIPlaceShips();
        logic.flipSquares();
        System.out.println("\n" + green + "Francis Drake is setting his ships...");
        logic.changeCurrentPlayer();

        playGame();
    }
    
    private void playGame(){
        Scanner input = new Scanner(System.in);
        while (ended == false){
            saveGame();
            System.out.println("\n" + green + logic.getCurrentPlayer() + " your turn.");

            System.out.print(green + "Enter coordinates to shoot or 'q' to quit: ");     // gives player opportunity to quit
            String key = input.next().toLowerCase();
            
            if (key.equals("q"))
                menu();
            else{
                logic.showEnemy();
                coordinates = enterCord("Enter coordinates to shoot: ");
                ended = logic.playerTurn(coordinates[0], coordinates[1]);     // method will return true if the game has ended
                if (ended)
                    break;
                else 
                    ended = logic.AITurn();
            }
        }
        
        System.out.println("\n" + green + "Game ended. " + logic.getCurrentPlayer() + " won.");
    }
    
    private Game loadGame(){
        Game game = new Game();
        try {
            FileInputStream fis = new FileInputStream("game.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            game = (Game) ois.readObject(); // Reads object from file and stores it in myBoard
        } catch (Exception ex) {
            System.out.println("Error Occured." + ex);
        }
        return game;
    }
    
    private void saveGame(){
        try {
            FileOutputStream fos = new FileOutputStream("game.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this); // writes the Board object using ObjectOutputStream
            oos.close();
            System.out.println(green + "Saved Game.");
        } catch (Exception ex) {
            System.out.println("Error Occurred." + ex);
        }
    }
    
    private int[] enterCord(String message){
        Scanner input = new Scanner(System.in);
        boolean error = true;
        String cord = "";
        String letter = "";
        int number = -1;
        
        do {
            while (true){
                System.out.print(green + message);      // ask user for coordinates
                cord = input.next();
                if (cord.length() > 3 || cord.length() < 2) {
                    System.out.println(green + "Error. Try again.");
                }
                else 
                    break;
            }
            letter = cord.substring(0, 1).toUpperCase();    // first section is letter
            number = Integer.parseInt(cord.substring(1));   // second section is number
            error = false;

            do {
                error = validateLetter(letter);
                if (error){
                    break;
                }
                error = validateNumber(number);
                if (error){
                    break;
                }

                error = false;
            }while (error);
            
        } while (error);
        
        int letterToNum = letterToNum(letter);
        int[] array = {letterToNum, number };
        return array;
    }
    
    private boolean validateLetter(String letter){
        if (letter.compareTo("A") < 10 && letter.compareTo("A") >= 0) {
                return false;
            } else {
                System.out.println(green  + "Error. Letter must be between A and J.");
                return true;
            }
        
    }
    
    private boolean validateNumber(int number){
        if (number < 11 && number >= 0) 
            return false;
        else{
            System.out.println(green + "Error. Number must be between 0 and 10.");
            return true;
        }
    }
    
    private int letterToNum(String letter){        
        switch (letter){
            case "A": return 1;
            case "B": return 2;
            case "C": return 3;
            case "D": return 4;
            case "E": return 5;
            case "F": return 6;
            case "G": return 7;
            case "H": return 8;
            case "I": return 9;
            case "J": return 10;
            default: return 0;
        }
    }
}
