
package finalproject;

import java.io.Serializable;

public class AI extends Player implements Serializable{
    private int[] nextHit = null;
    
    public AI() {
        super(2);
    }
    
    public int[] placeShips(int shipSize){
        int x = (int) (Math.random() * (10 - shipSize)) + 1;
        int y = (int) (Math.random()* 10) + 1;
        
        int[] array = {x, y};
        return array;
    }
    
    public int[] shoot(){
        if (nextHit == null){
            int x = (int) (Math.random() * (10) + 1);
            int y = (int) (Math.random() * (10) + 1);

            int[] array = {x, y};
            System.out.println(x + " " +  y);
            return array;
        }
        else
            
            return nextHit;
    }
   
    public int[] nextHit(int x, int y, String direction){
        if (direction.equals("up"))
            x -= 1;
        else 
            x += 1;
        nextHit = new int[]{x, y};
        return nextHit;
    }
    
    public void setNextHitNull(){
        nextHit = null;
    }
}
