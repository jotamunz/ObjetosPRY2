
package Model;

import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ImageIcon;

public abstract class Token extends Thread implements Serializable{
    
    public static ArrayList<TokenType> targetWall = new ArrayList<>(Arrays.asList(TokenType.Wall));
    
    protected Board board;
    protected String name;
    protected TokenType type;
    protected ArrayList<TokenType> targets;
    protected ArrayList<String> images;
    protected ImageIcon currentImage;
    protected int health;
    protected int damagePerSecond;
    protected int range;
    protected int level;
    protected int space;
    protected int unlockLevel;
    protected int cost;
    protected boolean running;
    protected boolean paused;
    

    public Token(String name, TokenType type, ArrayList<TokenType> targets, int health, int damagePerSecond, int range, int space, int unlockLevel, int cost, int level, Board board, ArrayList<String> images) {
        this.board = board;
        this.images = images;
        this.name = name;
        this.type = type;
        this.targets = targets;
        this.health = health;
        this.damagePerSecond = damagePerSecond;
        this.range = range;
        this.level = level;
        this.space = space;
        this.unlockLevel = unlockLevel;
        this.cost = cost;
        this.running = true;
        this.paused = false;
        setCurrentImage(images.get(0));
    }

    public ImageIcon getCurrentImage() {
        return currentImage;
    }

    public void setCurrentImage(String imagePath) {
        ImageIcon image = new ImageIcon(imagePath);
        image.setImage(image.getImage().getScaledInstance(board.DIMENSION, board.DIMENSION, Image.SCALE_DEFAULT));
        this.currentImage = image;
    }

    public TokenType getType() {
        return type;
    }
    
    public int getNumericType(){
        return type.ordinal();
    }

    public String getTokenName() {
        return name;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }
    
    public void takeDamage(int damage){
        if (this.health > 0 && running){
            this.health -= damage;        
            System.out.println(this.name + " current health: " + this.health);
            if (this.health <= 0){
                this.running = false;
                Coordinate currentPosition = findToken();
                board.logicalBoard[currentPosition.positionX][currentPosition.positionY].damagePerSecond = 0;
            }
        }
    }

    public int getHealth() {
        return health;
    }
    
    protected Coordinate findToken(){
        for (int i = 0; i < board.WIDTH; i++){
            for (int j = 0; j < board.HEIGHT; j++){
                if (board.logicalBoard[i][j] == this){
                    return new Coordinate(i, j);
                }
            }
        }
        return new Coordinate(-1, -1);
    }
    
    @Override
    public abstract void run(); 
}
