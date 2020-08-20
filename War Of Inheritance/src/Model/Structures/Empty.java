
package Model.Structures;

import Model.Board;
import Model.Structure;
import Model.TokenType;
import java.util.ArrayList;

public class Empty extends Structure{
    
    public Empty(String name, TokenType type, ArrayList<TokenType> targets, int health, int damagePerSecond, int range, int space, int unlockLevel, int cost, int level, Board board, ArrayList<String> images) {
        super(name, type, targets, health, damagePerSecond, range, space, unlockLevel, cost, level, board, images);
    }
    
    @Override
    public void run(){
    }
    
}
