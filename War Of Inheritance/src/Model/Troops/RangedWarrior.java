
package Model.Troops;

import Model.Board;
import Model.TokenType;
import Model.Troop;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class RangedWarrior extends Troop{

    public RangedWarrior(String name, TokenType type, ArrayList<TokenType> targets, int health, int damagePerSecond, int range, int space, int unlockLevel, int cost, int level, Board board, ArrayList<String> images, Semaphore mutexTroops, Semaphore mutexDefenses) {
        super(name, type, targets, health, damagePerSecond, range, space, unlockLevel, cost, level, board, images, mutexTroops, mutexDefenses);
        levelUp();
    }

    private void levelUp() {
        if (super.level%2 == 0){
            super.range += (super.level/2);
        }
        super.health += (super.level*10);
        super.damagePerSecond += (super.damagePerSecond*20);
    } 
}
