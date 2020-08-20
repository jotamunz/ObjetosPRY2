
package Model.Structures;

import Model.Board;
import Model.Structure;
import Model.TokenType;
import java.util.ArrayList;

public class Wall extends Structure{
    
    public Wall(String name, TokenType type, ArrayList<TokenType> targets, int health, int damagePerSecond, int range, int space, int unlockLevel, int cost, int level, Board board, ArrayList<String> images) {
        super(name, type, targets, health, damagePerSecond, range, space, unlockLevel, cost, level, board, images);
        levelUp();
    }
    
    private void levelUp(){
        super.health += (super.level*40);
    }
    
}
