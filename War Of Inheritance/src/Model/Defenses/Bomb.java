
package Model.Defenses;

import Model.Board;
import Model.Coordinate;
import Model.Defense;
import Model.TokenType;
import Model.Troop;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bomb extends Defense{
    
    public Bomb(String name, TokenType type, ArrayList<TokenType> targets, int health, int damagePerSecond, int range, int space, int unlockLevel, int cost, int level, Board board, ArrayList<String> images, Semaphore mutexTroops) {
        super(name, type, targets, health, damagePerSecond, range, space, unlockLevel, cost, level, board, images, mutexTroops);
        levelUp();
    }
    
    @Override
    public void atack(){
        Coordinate currentPosition = findToken();
        Coordinate targetPos = super.targetInRange(currentPosition, targets);
        if (targetPos != null){
            try {
                mutexTroops.acquire();
                System.out.println(this.name + " deals " + this.damagePerSecond + " damage to troop");
                areaDamage(currentPosition);
                mutexTroops.release();
                this.running = false;
            } catch (InterruptedException ex) {
                Logger.getLogger(Troop.class.getName()).log(Level.SEVERE, null, ex);
            }          
        }
    }
    
    private void areaDamage(Coordinate currentPosition){
        int posX = currentPosition.positionX;
        int posY = currentPosition.positionY;
        for (int rangeMeter = 1; rangeMeter <= range; rangeMeter++){
            for (int target = 0; target < targets.size(); target++){
                if ((posX-rangeMeter) >= 0 && board.logicalBoard[posX-rangeMeter][posY].getType() == targets.get(target))
                    board.logicalBoard[posX-rangeMeter][posY].takeDamage(this.damagePerSecond);
                if ((posX+rangeMeter) < board.WIDTH && board.logicalBoard[posX+rangeMeter][posY].getType() == targets.get(target))
                    board.logicalBoard[posX+rangeMeter][posY].takeDamage(this.damagePerSecond);
                if ((posY-rangeMeter) >= 0 && board.logicalBoard[posX][posY-rangeMeter].getType() == targets.get(target))
                    board.logicalBoard[posX][posY-rangeMeter].takeDamage(this.damagePerSecond);
                if ((posY+rangeMeter) < board.HEIGHT && board.logicalBoard[posX][posY+rangeMeter].getType() == targets.get(target))
                    board.logicalBoard[posX][posY+rangeMeter].takeDamage(this.damagePerSecond);
                if ((posX-rangeMeter) >= 0 && (posY-rangeMeter) >= 0 && board.logicalBoard[posX-rangeMeter][posY-rangeMeter].getType() == targets.get(target))
                    board.logicalBoard[posX-rangeMeter][posY-rangeMeter].takeDamage(this.damagePerSecond);
                if ((posX+rangeMeter) < board.WIDTH && (posY+rangeMeter) < board.HEIGHT && board.logicalBoard[posX+rangeMeter][posY+rangeMeter].getType() == targets.get(target))
                    board.logicalBoard[posX+rangeMeter][posY+rangeMeter].takeDamage(this.damagePerSecond);
                if ((posX-rangeMeter) >= 0 && (posY+rangeMeter) < board.HEIGHT && board.logicalBoard[posX-rangeMeter][posY+rangeMeter].getType() == targets.get(target))
                    board.logicalBoard[posX-rangeMeter][posY+rangeMeter].takeDamage(this.damagePerSecond);
                if ((posX+rangeMeter) < board.WIDTH && (posY-rangeMeter) >= 0 && board.logicalBoard[posX+rangeMeter][posY-rangeMeter].getType() == targets.get(target))
                    board.logicalBoard[posX+rangeMeter][posY-rangeMeter].takeDamage(this.damagePerSecond);
            }
        }
    }
    
    private void levelUp() {
        super.damagePerSecond += (super.damagePerSecond*10);
    } 
}
