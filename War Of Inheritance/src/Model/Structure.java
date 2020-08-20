
package Model;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Structure extends Token{

    public Structure(String name, TokenType type, ArrayList<TokenType> targets, int health, int damagePerSecond, int range, int space, int unlockLevel, int cost, int level, Board board, ArrayList<String> images) {
        super(name, type, targets, health, damagePerSecond, range, space, unlockLevel, cost, level, board, images);
    }

    @Override
    public void run() {
        while(running && !board.isGameOver()){
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Structure.class.getName()).log(Level.SEVERE, null, ex);
            }
            while(paused && running){
                try {
                    sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Structure.class.getName()).log(Level.SEVERE, null, ex);
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
   
}
