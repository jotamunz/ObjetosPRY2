
package Model;

import java.io.Serializable;

public class User implements Serializable{
    private String username;
    private String password;
    private int level;
    private int goldAmount;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.level = 1;
        this.goldAmount = 2000;
    }

    public String getUsername() {
        return username;
    }

    public int getLevel() {
        return level;
    }

    public void levelUp() {
        this.level += 1;
    }

    public int getGoldAmount() {
        return goldAmount;
    }

    public void reduceGoldAmount(int goldReduction) {
        this.goldAmount -= goldReduction;
    }

    public void increaseGoldAmount(int goldAmount) {
        this.goldAmount += goldAmount;
    }
    
    
    

}
