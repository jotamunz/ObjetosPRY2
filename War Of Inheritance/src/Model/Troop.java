
package Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Troop extends Token{
    protected Semaphore mutexTroops;
    protected Semaphore mutexDefenses;
    
    public Troop(String name, TokenType type, ArrayList<TokenType> targets, int health, int damagePerSecond, int range, int space, int unlockLevel, int cost, int level, Board board, ArrayList<String> images, Semaphore mutexTroops, Semaphore mutexDefenses) {
        super(name, type, targets, health, damagePerSecond, range, space, unlockLevel, cost, level, board, images);
        this.mutexTroops = mutexTroops;
        this.mutexDefenses = mutexDefenses;
    }

    @Override
    public void run() {
        while(running && !board.isGameOver()){
            try {
                makeSound();
            } catch (IOException ex) {
                Logger.getLogger(Troop.class.getName()).log(Level.SEVERE, null, ex);
            } catch (LineUnavailableException ex) {
                Logger.getLogger(Troop.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedAudioFileException ex) {
                Logger.getLogger(Troop.class.getName()).log(Level.SEVERE, null, ex);
            }
            move();
            atack();
            while(paused && running){
                try {
                    sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Troop.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        Coordinate currentPosition = findToken();
        if (currentPosition.positionX >= 0 && currentPosition.positionY >= 0 )
            board.logicalBoard[currentPosition.positionX][currentPosition.positionY] = TokenFactory.createToken("", TokenType.Empty, 0, 0, 0, 0, 0, 0, 0, board, board.EMPTY_I);
        if (health <= 0)
            board.decreaseArmyLeft();
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
                    mutexDefenses.acquire();
                    this.setCurrentImage(this.images.get(1));
                    System.out.println(this.name + " deals " + this.damagePerSecond + " damage to defense");
                    target.takeDamage(this.damagePerSecond);
                    mutexDefenses.release();
                    sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Troop.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.setCurrentImage(this.images.get(0));
        }
        else{ 
            targetPos = targetInRange(currentPosition, Token.targetWall);
            if (targetPos != null){
                Token target = board.logicalBoard[targetPos.positionX][targetPos.positionY];
                while(target.getHealth() > 0 && running){
                    try {
                        mutexDefenses.acquire();
                        this.setCurrentImage(this.images.get(1));
                        System.out.println(this.name + " deals damage to wall");
                        target.takeDamage(this.damagePerSecond);
                        mutexDefenses.release();
                        sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Troop.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                this.setCurrentImage(this.images.get(0));
            }
        }
    }
    
    public void move() {
        Coordinate currentPosition = findToken();
        if(targetInRange(currentPosition, targets) == null)
        {
            ArrayList<Coordinate> path = findShortestPath(currentPosition, targets);
            if (path != null){
                for (int i = 1; i < path.size() && running; i++){
                    try {
                        mutexTroops.acquire();
                        Token currentToken = this;
                        currentPosition = findToken();
                        if (currentPosition.positionX < 0 || currentPosition.positionY < 0)
                            return;
                        board.logicalBoard[currentPosition.positionX][currentPosition.positionY] = board.logicalBoard[path.get(i).positionX][path.get(i).positionY];
                        board.logicalBoard[path.get(i).positionX][path.get(i).positionY] = currentToken;                         
                        mutexTroops.release();
                        sleep(500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Troop.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                return;
            }
            else if (targetInRange(currentPosition, Token.targetWall) ==  null){
                path = findShortestPath(currentPosition, Token.targetWall);
                if (path != null){
                    for (int i = 1; i < path.size() && running; i++){
                        try {
                            mutexTroops.acquire();
                            Token currentToken = this;
                            currentPosition = findToken();
                            board.logicalBoard[currentPosition.positionX][currentPosition.positionY] = board.logicalBoard[path.get(i).positionX][path.get(i).positionY];
                            board.logicalBoard[path.get(i).positionX][path.get(i).positionY] = currentToken;
                            mutexTroops.release();
                            sleep(500);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Troop.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    return;
                }
            }
        }
    }
    
    private Coordinate targetInRange(Coordinate currentPosition, ArrayList<TokenType> targets){    
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
    
    private ArrayList<Coordinate> findShortestPath(Coordinate currentPosition, ArrayList<TokenType> targets){
       if (currentPosition.positionX < 0 || currentPosition.positionY < 0)
            return null;
        boolean[][] visited = new boolean[board.WIDTH][board.HEIGHT];
        ArrayList<Coordinate> path = new ArrayList<>();
        Queue<ArrayList> queue = new LinkedList<>();
        Coordinate temp;
        int posX, posY;
        int[] directionX = {-1, 1, 0, 0};
        int[] directionY = {0, 0, 1, -1};
        int nodesLeftInLayer = 1;
        int nodesInNextLayer = 0;
        boolean reachedEnd = false;
        path.add(currentPosition);
        queue.add(path);
        visited[currentPosition.positionX][currentPosition.positionY] = true;
        try {
            mutexTroops.acquire();
            while(!queue.isEmpty()){
                path = queue.remove();
                temp = path.get(path.size()-1);
                if (targetInRange(temp, targets) != null){
                    reachedEnd = true;
                    break;
                }
                for (int i = 0; i < 4; i++){
                    posX = temp.positionX + directionX[i];
                    posY = temp.positionY + directionY[i];
                    if (posX < 0 || posX >= board.WIDTH || posY < 0 || posY >= board.HEIGHT)
                        continue;
                    if (visited[posX][posY] == true)
                        continue;
                    if (board.logicalBoard[posX][posY].getType() != TokenType.Empty)
                        continue;
                    ArrayList<Coordinate> pathToNext = new ArrayList<>(path);
                    pathToNext.add(new Coordinate(posX, posY));
                    queue.add(pathToNext);
                    visited[posX][posY] = true;
                    nodesInNextLayer++;
                }
                nodesLeftInLayer--;
                if (nodesLeftInLayer == 0){
                    nodesLeftInLayer = nodesInNextLayer;
                    nodesInNextLayer = 0;
                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Troop.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mutexTroops.release();
            if (reachedEnd)
                return path;
            return null;
        } 
    }
    
    protected void makeSound() throws IOException, LineUnavailableException, UnsupportedAudioFileException{ 
    }

}
