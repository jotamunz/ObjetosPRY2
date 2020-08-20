
package Model.Troops;

import Model.Board;
import Model.TokenType;
import Model.Troop;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Beast extends Troop{
    
    public Beast(String name, TokenType type, ArrayList<TokenType> targets, int health, int damagePerSecond, int range, int space, int unlockLevel, int cost, int level, Board board, ArrayList<String> images, Semaphore mutexTroops, Semaphore mutexDefenses) {
        super(name, type, targets, health, damagePerSecond, range, space, unlockLevel, cost, level, board, images, mutexTroops, mutexDefenses);
    }
    
}
