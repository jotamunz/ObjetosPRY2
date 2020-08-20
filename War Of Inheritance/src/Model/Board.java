
package Model;

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Scanner;
import java.util.Arrays;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Board implements Serializable{
    
    public static int WIDTH = 30;
    public static int HEIGHT = 18;
    public static int DIMENSION = 50;
    
    //GAME BALANCE
    //HEALTH
    public static int CONTACTWARRIOR_H = 60;
    public static int RANGEDWARRIOR_H = 40;
    public static int AERIALWARRIOR_H = 100;
    public static int BEAST_H = 8000;
    public static int HERO_H = 1500;
    public static int CANNON_H = 80;
    public static int RANGEDTOWER_H = 80;
    public static int MORTAR_H = 100;
    public static int AERIALDEFENSE_H = 120;
    public static int BOMB_H = 10;
    public static int WALL_H = 200;
    public static int MAINHOUSE_H = 400;
    //DAMAGE
    public static int CONTACTWARRIOR_D = 40;
    public static int RANGEDWARRIOR_D = 50;
    public static int AERIALWARRIOR_D = 80;
    public static int BEAST_D = 800;
    public static int HERO_D = 1000;
    public static int CANNON_D = 10;
    public static int RANGEDTOWER_D = 20;
    public static int MORTAR_D = 30;
    public static int AERIALDEFENSE_D = 20;
    public static int BOMB_D = 50;
    public static int WALL_D = 0;
    public static int MAINHOUSE_D = 0;
    //RANGE
    public static int CONTACTWARRIOR_R = 1;
    public static int RANGEDWARRIOR_R = 2;
    public static int AERIALWARRIOR_R = 10;
    public static int BEAST_R = 2;
    public static int HERO_R = 1;
    public static int CANNON_R = 2;
    public static int RANGEDTOWER_R = 2;
    public static int MORTAR_R = 4;
    public static int AERIALDEFENSE_R = 10;
    public static int BOMB_R = 1;
    public static int WALL_R = 0;
    public static int MAINHOUSE_R = 0;
    //SPACE
    public static int CONTACTWARRIOR_S = 1;
    public static int RANGEDWARRIOR_S = 2;
    public static int AERIALWARRIOR_S = 3;
    public static int BEAST_S = 5;
    public static int HERO_S = 8;
    public static int CANNON_S = 2; 
    public static int RANGEDTOWER_S = 4;
    public static int MORTAR_S = 8;
    public static int AERIALDEFENSE_S = 5;
    public static int BOMB_S = 4;
    public static int WALL_S = 1;
    public static int MAINHOUSE_S = 1;
    //UNLOCK LEVEL
    public static int CONTACTWARRIOR_U = 1;
    public static int RANGEDWARRIOR_U = 2;
    public static int AERIALWARRIOR_U = 3;
    public static int BEAST_U = 5;
    public static int HERO_U = 10;
    public static int CANNON_U = 1;
    public static int RANGEDTOWER_U = 2;
    public static int MORTAR_U = 5;
    public static int AERIALDEFENSE_U = 3;
    public static int BOMB_U = 8;
    public static int WALL_U = 1;
    public static int MAINHOUSE_U = 1;
    //COST
    public static int CONTACTWARRIOR_C = 100;
    public static int RANGEDWARRIOR_C = 200;
    public static int AERIALWARRIOR_C = 300;
    public static int BEAST_C = 500;
    public static int HERO_C = 800;
    public static int CANNON_C = 0;
    public static int RANGEDTOWER_C = 0;
    public static int MORTAR_C = 0;
    public static int AERIALDEFENSE_C = 0;
    public static int BOMB_C = 0;
    public static int WALL_C = 0;
    public static int MAINHOUSE_C = 0;
    //ASSETS
    public static ArrayList<String> CONTACTWARRIOR_I = new ArrayList<>(Arrays.asList("D:\\NetBeans\\Projects\\War Of Inheritance\\Assets\\WarriorMove.gif", "D:\\NetBeans\\Projects\\War Of Inheritance\\Assets\\WarriorAtack.gif"));
    public static ArrayList<String> RANGEDWARRIOR_I  = new ArrayList<>(Arrays.asList("D:\\NetBeans\\Projects\\War Of Inheritance\\Assets\\ArcherMove.gif", "D:\\NetBeans\\Projects\\War Of Inheritance\\Assets\\ArcherAtack.gif"));
    public static ArrayList<String> AERIALWARRIOR_I  = new ArrayList<>(Arrays.asList("D:\\NetBeans\\Projects\\War Of Inheritance\\Assets\\DragonMove.gif", "D:\\NetBeans\\Projects\\War Of Inheritance\\Assets\\DragonAtack.gif"));
    public static ArrayList<String> BEAST_I  = new ArrayList<>(Arrays.asList("D:\\NetBeans\\Projects\\War Of Inheritance\\Assets\\BeastMove.gif", "D:\\NetBeans\\Projects\\War Of Inheritance\\Assets\\BeastAtack.gif"));
    public static ArrayList<String> HERO_I = new ArrayList<>(Arrays.asList("D:\\NetBeans\\Projects\\War Of Inheritance\\Assets\\HeroMove.gif", "D:\\NetBeans\\Projects\\War Of Inheritance\\Assets\\HeroAtack.gif"));
    public static ArrayList<String> CANNON_I = new ArrayList<>(Arrays.asList("D:\\NetBeans\\Projects\\War Of Inheritance\\Assets\\Cannon.png"));
    public static ArrayList<String> RANGEDTOWER_I = new ArrayList<>(Arrays.asList("D:\\NetBeans\\Projects\\War Of Inheritance\\Assets\\ArcherTower.png"));
    public static ArrayList<String> MORTAR_I = new ArrayList<>(Arrays.asList("D:\\NetBeans\\Projects\\War Of Inheritance\\Assets\\Mortar.png")); 
    public static ArrayList<String> AERIALDEFENSE_I = new ArrayList<>(Arrays.asList("D:\\NetBeans\\Projects\\War Of Inheritance\\Assets\\AirDefense.png"));
    public static ArrayList<String> BOMB_I = new ArrayList<>(Arrays.asList("D:\\NetBeans\\Projects\\War Of Inheritance\\Assets\\Bomb.png"));
    public static ArrayList<String> WALL_I  = new ArrayList<>(Arrays.asList("D:\\NetBeans\\Projects\\War Of Inheritance\\Assets\\Wall.png")); 
    public static ArrayList<String> MAINHOUSE_I = new ArrayList<>(Arrays.asList("D:\\NetBeans\\Projects\\War Of Inheritance\\Assets\\TownHall.png"));
    public static ArrayList<String> EMPTY_I = new ArrayList<>(Arrays.asList("D:\\NetBeans\\Projects\\War Of Inheritance\\Assets\\Empty.png"));
    
    private User user;
    private ArrayList<Token> army = new ArrayList<>();
    private ArrayList<Token> customWarriors = new ArrayList<>();
    private int levelGold = 0;
    private int totalStructures = 0;
    private int levelStructures = 0;
    private int levelArmy = 0;
    private int goldLost = 0;
    public Token[][] logicalBoard = new Token[WIDTH][HEIGHT];

    public Board(User user) {
        this.user = user;
        this.generateBoard();
    }

    public User getUser() {
        return user;
    }
    
    public void generateBoard(){
        for (int i = 0; i < WIDTH; i++){
            for (int j = 0; j < HEIGHT; j++){
                logicalBoard[i][j] = TokenFactory.createToken("", TokenType.Empty, 0, 0, 0, 0, 0, 0, 0, this, EMPTY_I);
            }
        }
        this.printBoard();
    }
    
    public void printBoard(){
        for (int i = 0; i < HEIGHT; i++){
            for (int j = 0; j < WIDTH; j++){
                if (logicalBoard[j][i].getNumericType() < 10)
                    System.out.print(logicalBoard[j][i].getNumericType() + "  ");
                else
                     System.out.print(logicalBoard[j][i].getNumericType() + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }
    
    public void addToArmy(Token troop){
        if (troop.unlockLevel <= user.getLevel()){
            int currentSpace = troop.space;
            boolean spaceAvailable = true;
            for (int i = 0; i < army.size(); i++){
                currentSpace += army.get(i).space;
                if (currentSpace > (15 + (user.getLevel()*5))){
                    spaceAvailable = false;
                    break;
                }
            }
            if (spaceAvailable){
                if ((user.getGoldAmount() - troop.cost) >= 0){
                    this.army.add(troop);
                    levelArmy++;
                    user.reduceGoldAmount(troop.cost);
                    goldLost += troop.cost;
                    System.out.println("Added " + troop.getTokenName());
                }
                else
                    System.out.println("Not enough gold");
            }
            else
                System.out.println("Maximum troop capacity reached");
        }
        else
            System.out.println("Troop has not been unlocked");
    }
    
        
    public void placeArmy() {
        int randomPosX;
        int randomPosY;
        for (int i = 0; i < army.size(); i++){
            boolean tokenPlaced = false;
            if (army.get(i).getType() == TokenType.AerialWarrior){
                for (int j = 5; j < (HEIGHT-4); j++){
                    if (logicalBoard[5][j].getType() == TokenType.Empty){
                        logicalBoard[5][j] = army.get(i);
                        tokenPlaced = true;
                        System.out.println("placed");
                        break;
                    }
                }
            }
            if (!tokenPlaced){
                do{
                    randomPosX = (int)(Math.random()*100)%WIDTH;
                    randomPosY = (int)(Math.random()*100)%HEIGHT;
                }while(logicalBoard[randomPosX][randomPosY].getType() != TokenType.Empty);
                logicalBoard[randomPosX][randomPosY] = army.get(i);
            }
        }
    }
    
    public int getArmySpace(){
        int currentSpace = 0;
        for (int i = 0; i < army.size(); i++){
            currentSpace += army.get(i).space;
        }
        return ((15 + (user.getLevel()*5)) - currentSpace);
    }
    
    public void generateLevel(int level, String fileName) throws FileNotFoundException{
        if (level <= user.getLevel()){
            this.levelGold = (level*4000); 
            this.totalStructures = 0;
            if (level > 15)
                generateRandomLevel();
            else{
                FileInputStream file = new FileInputStream(fileName);
                Scanner scanner = new Scanner(file);
                int maxDefenseSpace = (25 + (user.getLevel()*10));
                int usedDefenseSpace = 0;
                while(scanner.hasNextLine() && usedDefenseSpace < maxDefenseSpace){
                    String line[] = scanner.nextLine().toLowerCase().split(",", 0); //x,y,objName
                    int posX = Integer.parseInt(line[0]);
                    int posY = Integer.parseInt(line[1]);
                    String objectName = line[2];
                    if (objectName.equals("home")){
                        if (maxDefenseSpace >= (usedDefenseSpace +  MAINHOUSE_S)){
                            logicalBoard[posX][posY] = TokenFactory.createToken("House", TokenType.MainHouse, MAINHOUSE_H, MAINHOUSE_D, MAINHOUSE_R, MAINHOUSE_S, MAINHOUSE_U, user.getLevel(), MAINHOUSE_C, this, MAINHOUSE_I);
                            usedDefenseSpace += MAINHOUSE_S;
                            totalStructures++;
                        }
                    }
                    else if (objectName.equals("wall")){ 
                        if (maxDefenseSpace >= (usedDefenseSpace +  WALL_S)){
                            logicalBoard[posX][posY] = TokenFactory.createToken("Wall", TokenType.Wall, WALL_H, WALL_D, WALL_R, WALL_S, WALL_U, user.getLevel(), WALL_C, this, WALL_I);
                            usedDefenseSpace += WALL_S;
                            totalStructures++;
                        }
                    }
                    else {
                        Token defense = generateRandomDefense();
                        if (maxDefenseSpace >= (usedDefenseSpace + defense.space)){
                            logicalBoard[posX][posY] = defense;
                            if (defense.getType() != TokenType.Bomb)
                                totalStructures++;
                        }
                        else if (maxDefenseSpace >= (usedDefenseSpace + CANNON_S)){
                            logicalBoard[posX][posY] = TokenFactory.createToken("Cannon", TokenType.Cannon, CANNON_H, CANNON_D, CANNON_R, CANNON_S, CANNON_U, user.getLevel(), CANNON_C, this, CANNON_I);
                            totalStructures++;
                        }
                        usedDefenseSpace += defense.space;
                    }
                }
            }
            this.levelStructures = this.totalStructures;
            System.out.println("Level generated with: " + totalStructures + " structures");
        }
        else
            System.out.println("Level has not been unlocked");
    }
    
    public Token generateRandomDefense(){
        Token randDef;
        do{
            int rand = (int) (Math.random()*100)%5;
            switch(rand){
            case 0:
                randDef = TokenFactory.createToken("Cannon", TokenType.Cannon, CANNON_H, CANNON_D, CANNON_R, CANNON_S, CANNON_U, user.getLevel(), CANNON_C, this, CANNON_I);
                break;
            case 1:
                randDef = TokenFactory.createToken("Tower", TokenType.RangedTower, RANGEDTOWER_H, RANGEDTOWER_D, RANGEDTOWER_R, RANGEDTOWER_S, RANGEDTOWER_U, user.getLevel(), RANGEDTOWER_C, this, RANGEDTOWER_I);
                break;
            case 2:
                randDef = TokenFactory.createToken("Aerial", TokenType.AerialDefense, AERIALDEFENSE_H, AERIALDEFENSE_D, AERIALDEFENSE_R, AERIALDEFENSE_S, AERIALDEFENSE_U, user.getLevel(), AERIALDEFENSE_C, this, AERIALDEFENSE_I);
                break;
            case 3:
                randDef = TokenFactory.createToken("Mortar", TokenType.Mortar, MORTAR_H, MORTAR_D, MORTAR_R, MORTAR_S, MORTAR_U, user.getLevel(), MORTAR_C, this, MORTAR_I);
                break;
            case 4:
                randDef = TokenFactory.createToken("Bomb", TokenType.Bomb, BOMB_H, BOMB_D, BOMB_R, BOMB_S, BOMB_U, user.getLevel(), BOMB_C, this, BOMB_I);
                break;
            default:
                randDef = null;
                break;       
            }         
        } while(randDef == null || randDef.unlockLevel > this.user.getLevel());
        return randDef;
    }
    
    public void generateRandomLevel(){
        int maxDefenseSpace = (20 + (user.getLevel()*10));
        int usedDefenseSpace = 0;
        int posX;
        int posY;
        while(usedDefenseSpace < maxDefenseSpace){
            do{
                posX = (int) (Math.random()*100)%logicalBoard.length;
                posY = (int) (Math.random()*100)%logicalBoard[1].length; 
            } while(posX >= logicalBoard.length || posY >= logicalBoard[1].length || logicalBoard[posX][posY].getType() != TokenType.Empty);
            Token defense = generateRandomDefense();
            if (maxDefenseSpace >= (usedDefenseSpace + defense.space)){
                logicalBoard[posX][posY] = defense;
                if (defense.getType() != TokenType.Beast)
                    totalStructures++;
            }
            else if (maxDefenseSpace >= (usedDefenseSpace + CANNON_S)){
                logicalBoard[posX][posY] = TokenFactory.createToken("Cannon", TokenType.Cannon, CANNON_H, CANNON_D, CANNON_R, CANNON_S, CANNON_U, user.getLevel(), CANNON_C, this, CANNON_I);
                totalStructures++;
            }
            usedDefenseSpace += defense.space;
        }
    }
    
    public void decreaseStructuresLeft(){
        this.levelStructures -= 1;
    }
    
    public void decreaseArmyLeft(){
        this.levelArmy -= 1;
    }
    
    public void earnGold(){
        if (this.levelStructures == 0)
            this.user.increaseGoldAmount(this.levelGold);
        else{
            int percent = (totalStructures - levelStructures)/totalStructures;
            this.user.increaseGoldAmount(this.levelGold*percent);
        }    
    }
    
    public boolean isVictory(){
        if (this.destructionPercent() >= 50)
            return true;
        return false;
    }
    
    public int destructionPercent(){
        System.out.println("Total: " + totalStructures + " Left: " + levelStructures);
        if ((int) ((totalStructures - levelStructures)/(totalStructures/(double)100)) >= 100)
            return 100;
        return (int) ((totalStructures - levelStructures)/(totalStructures/(double)100));
    }
            
    public boolean isGameOver(){
        return (this.levelStructures == 0 || this.levelArmy == 0);
    }
    
    public void gameOver(){
        if (isVictory()){
            this.earnGold();
            this.user.levelUp();
        }
        else
            this.user.increaseGoldAmount(this.goldLost);
        army = new ArrayList<>();
        goldLost = 0;
        levelGold = 0;
        totalStructures = 0;
        levelStructures = 0;
        levelArmy = 0;
        this.generateBoard();
    }
    
    public void startGame(){
        for (int i = 0; i < WIDTH; i++){
            for (int j = 0; j < HEIGHT; j++){
                logicalBoard[i][j].start();
            }
        }
    }
    
    public void stopGame(){
        for (int i = 0; i < WIDTH; i++){
            for (int j = 0; j < HEIGHT; j++){
                logicalBoard[i][j].setRunning(false);
            }
        } 
    }
    
    public void pauseGame(){
        for (int i = 0; i < WIDTH; i++){
            for (int j = 0; j < HEIGHT; j++){
                logicalBoard[i][j].setPaused(true);
            }
        }          
    }
    
    public void unPauseGame(){
        for (int i = 0; i < WIDTH; i++){
            for (int j = 0; j < HEIGHT; j++){
                logicalBoard[i][j].setPaused(false);
            }
        }          
    }
    
    public void saveGame(){
        try
        {           
            FileOutputStream file = new FileOutputStream(this.user.getUsername()); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
            out.writeObject(this); 
            out.close(); 
            file.close(); 
            System.out.println("Object has been serialized"); 
        }  
        catch(IOException ex) 
        { 
            System.out.println("Failed to Save Game"); 
        } 
    }
    
    public void addCustomWarrior(Token newWarrior){
        this.customWarriors.add(newWarrior);
    }

    public ArrayList<Token> getCustomWarriors() {
        return customWarriors;
    }

}
