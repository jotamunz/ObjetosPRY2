
package Model;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Defense extends Token{
    protected Semaphore mutexTroops;
    
    public Defense(String name, TokenType type, ArrayList<TokenType> targets, int health, int damagePerSecond, int range, int space, int unlockLevel, int cost, int level, Board board, ArrayList<String> images, Semaphore mutexTroops) {
        super(name, type, targets, health, damagePerSecond, range, space, unlockLevel, cost, level, board, images);
        this.mutexTroops = mutexTroops;
    }
    
    @Override
    public void run() {
        while(running && !board.isGameOver()){
            atack();
            while(paused && running){
                try {
                    sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Defense.class.getName()).log(Level.SEVERE, null, ex);
                }
            }      
        }
        Coordinate currentPosition = findToken();
        if (currentPosition.positionX >= 0 && currentPosition.positionY >= 0 )
            board.logicalBoard[currentPosition.positionX][currentPosition.positionY] = TokenFactory.createToken("", TokenType.Empty, 0, 0, 0, 0, 0, 0, 0, board, board.EMPTY_I);
        if (health <= 0)
            board.decreaseStructuresLeft();
        if (board.isGameOver())
            board.stopGame();
    }
    
    public void atack(){
        Coordinate currentPosition = findToken();
        Coordinate targetPos = targetInRange(currentPosition, targets);
        if (targetPos != null){
            Token target = board.logicalBoard[targetPos.positionX][targetPos.positionY];
            while(target.getHealth() > 0 && running){
                try {
                    mutexTroops.acquire();
                    System.out.println(this.name + " deals " + this.damagePerSecond + " damage to troop");
                    target.takeDamage(this.damagePerSecond);
                    mutexTroops.release();
                    sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Troop.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    protected Coordinate targetInRange(Coordinate currentPosition, ArrayList<TokenType> targets){    
        int posX = currentPosition.positionX;
        int posY = currentPosition.positionY;
        if (posX < 0 || posY < 0)
            return null;
        for (int rangeMeter = 1; rangeMeter <= range; rangeMeter++){
            for (int target = 0; target < targets.size(); target++){
                if ((posX-rangeMeter) >= 0 && board.logicalBoard[posX-rangeMeter][posY].getType() == targets.get(target))
                    return new Coordinate(posX-rangeMeter, posY);
                if ((posX+rangeMeter) < board.WIDTH && board.logicalBoard[posX+rangeMeter][posY].getType() == targets.get(target))
                    return new Coordinate(posX+rangeMeter, posY);
                if ((posY-rangeMeter) >= 0 && board.logicalBoard[posX][posY-rangeMeter].getType() == targets.get(target))
                    return new Coordinate(posX, posY-rangeMeter);
                if ((posY+rangeMeter) < board.HEIGHT && board.logicalBoard[posX][posY+rangeMeter].getType() == targets.get(target))
                    return new Coordinate(posX, posY+rangeMeter);
                if ((posX-rangeMeter) >= 0 && (posY-rangeMeter) >= 0 && board.logicalBoard[posX-rangeMeter][posY-rangeMeter].getType() == targets.get(target))
                    return new Coordinate(posX-rangeMeter, posY-rangeMeter);
                if ((posX+rangeMeter) < board.WIDTH && (posY+rangeMeter) < board.HEIGHT && board.logicalBoard[posX+rangeMeter][posY+rangeMeter].getType() == targets.get(target))
                    return new Coordinate(posX+rangeMeter, posY+rangeMeter);
                if ((posX-rangeMeter) >= 0 && (posY+rangeMeter) < board.HEIGHT && board.logicalBoard[posX-rangeMeter][posY+rangeMeter].getType() == targets.get(target))
                    return new Coordinate(posX-rangeMeter, posY+rangeMeter);
                if ((posX+rangeMeter) < board.WIDTH && (posY-rangeMeter) >= 0 && board.logicalBoard[posX+rangeMeter][posY-rangeMeter].getType() == targets.get(target))
                    return new Coordinate(posX+rangeMeter, posY-rangeMeter);
            }
        }
        return null;
    }
    
}
