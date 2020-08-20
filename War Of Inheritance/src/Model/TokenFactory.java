
package Model;

import Model.Structures.Wall;
import Model.Structures.Empty;
import Model.Troops.*;
import Model.Defenses.*;
import Model.Structures.MainHouse;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class TokenFactory {
    
    private static Semaphore mutexTroops = new Semaphore(1);
    private static Semaphore mutexDefenses = new Semaphore(1);
    
    public static Token createToken(String name, TokenType type, int health, int damagePerSecond, int range, int space, int unlockLevel, int level, int cost, Board board, ArrayList<String> images) {
        ArrayList<TokenType> targets = new ArrayList();
        switch(type){
            case ContactWarrior:
                targets.add(TokenType.MainHouse);
                targets.add(TokenType.Cannon);
                targets.add(TokenType.RangedTower);
                targets.add(TokenType.Mortar);
                targets.add(TokenType.AerialDefense);
                return new ContactWarrior(name, type, targets, health, damagePerSecond, range, space, unlockLevel, cost, level, board, images, mutexTroops, mutexDefenses);
            case RangedWarrior:
                targets.add(TokenType.MainHouse);
                targets.add(TokenType.Cannon);
                targets.add(TokenType.RangedTower);
                targets.add(TokenType.Mortar);
                targets.add(TokenType.AerialDefense);
                return new RangedWarrior(name, type, targets, health, damagePerSecond, range, space, unlockLevel, cost, level, board, images, mutexTroops, mutexDefenses);
            case AerialWarrior:
                targets.add(TokenType.MainHouse);
                targets.add(TokenType.AerialDefense);
                targets.add(TokenType.Cannon);
                targets.add(TokenType.RangedTower);
                targets.add(TokenType.Mortar);
                return new AerialWarrior(name, type, targets, health, damagePerSecond, range, space, unlockLevel, cost, level, board, images, mutexTroops, mutexDefenses);
            case Beast:
                targets.add(TokenType.MainHouse);
                targets.add(TokenType.Mortar);
                targets.add(TokenType.Cannon);
                targets.add(TokenType.RangedTower);
                targets.add(TokenType.AerialDefense);
                return new Beast(name, type, targets, health, damagePerSecond, range, space, unlockLevel, cost, level, board, images, mutexTroops, mutexDefenses);
            case Hero:
                targets.add(TokenType.MainHouse);
                targets.add(TokenType.Mortar);
                targets.add(TokenType.Cannon);
                targets.add(TokenType.RangedTower);
                targets.add(TokenType.AerialDefense);
                return new Hero(name, type, targets, health, damagePerSecond, range, space, unlockLevel, cost, level, board, images, mutexTroops, mutexDefenses);
            case Cannon:
                targets.add(TokenType.ContactWarrior);
                targets.add(TokenType.RangedWarrior);
                targets.add(TokenType.Hero);
                targets.add(TokenType.Beast);
                return new Cannon(name, type, targets, health, damagePerSecond, range, space, unlockLevel, cost, level, board, images, mutexTroops);
            case RangedTower:   
                targets.add(TokenType.RangedWarrior);
                targets.add(TokenType.AerialWarrior);
                targets.add(TokenType.ContactWarrior);
                targets.add(TokenType.Hero);
                targets.add(TokenType.Beast);
                return new RangedTower(name, type, targets, health, damagePerSecond, range, space, unlockLevel, cost, level, board, images, mutexTroops);
            case Mortar:
                targets.add(TokenType.Hero);
                targets.add(TokenType.Beast);
                targets.add(TokenType.ContactWarrior);
                targets.add(TokenType.RangedWarrior);
                return new Mortar(name, type, targets, health, damagePerSecond, range, space, unlockLevel, cost, level, board, images, mutexTroops);
            case AerialDefense:
                targets.add(TokenType.AerialWarrior);
                return new AerialDefense(name, type, targets, health, damagePerSecond, range, space, unlockLevel, cost, level, board, images, mutexTroops);
            case Bomb:
                targets.add(TokenType.Hero);
                targets.add(TokenType.Beast);
                targets.add(TokenType.ContactWarrior);
                targets.add(TokenType.RangedWarrior);
                return new Bomb(name, type, targets, health, damagePerSecond, range, space, unlockLevel, cost, level, board, images, mutexTroops);
            case Wall:
                return new Wall(name, type, targets, health, damagePerSecond, range, space, unlockLevel, cost, level, board, images);
            case Empty:
                return new Empty(name, type, targets, health, damagePerSecond, range, space, unlockLevel, cost, level, board, images);
            case MainHouse:
                return new MainHouse(name, type, targets, health, damagePerSecond, range, space, unlockLevel, cost, level, board, images);
            default:
                System.out.println("Incorrect token type");
                return null;
        }
    }
}
