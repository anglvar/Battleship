
package finalproject;

import java.io.Serializable;

public class Square implements Serializable{
    private Ship ship;
    private String status;  // can be hit, miss, filled, empty
    private String icon;
    
    public Square(){
        status = "empty";
        icon = "_";
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        
        // sets icon to display according to status
        switch (status){
            case "hit": this.icon = "x";
                break;
            case "miss": this.icon = "o";
                break;
            case "filled": this.icon = "*";
                break;
            default: this.icon = "_";
        }
    }
    
    public boolean isFilled(){
        if (status.equals("filled"))
            return true;
        else
            return false;
    }

    public void flipSquare(){
        if (status.equals("filled"))
            icon = "_";
    }
    
    public void removeShip(){
        this.ship = null;
        this.status = "empty";
    }
    
    @Override
    public String toString(){
        return icon;            // icon is displayed when toString is called
    }
}
