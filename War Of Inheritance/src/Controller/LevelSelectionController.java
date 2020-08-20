
package Controller;

import Model.Board;
import View.ArmySelectionDisplay;
import View.LevelSelectionDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LevelSelectionController implements ActionListener{
    private Board board;
    private LevelSelectionDisplay display;

    public LevelSelectionController(Board board, LevelSelectionDisplay display) {
        this.board = board;
        this.display = display;
        init();
    }
    
    private void init(){
        display.jButton_lvl1.addActionListener(this);
        display.jButton_lvl2.addActionListener(this);
        display.jButton_lvl3.addActionListener(this);
        display.jButton_lvl4.addActionListener(this);
        display.jButton_lvl5.addActionListener(this);
        display.jButton_lvl6.addActionListener(this);
        display.jButton_lvl7.addActionListener(this);
        display.jButton_lvl8.addActionListener(this);
        display.jButton_lvl9.addActionListener(this);
        display.jButton_lvl10.addActionListener(this);
        display.jButton_lvl11.addActionListener(this);
        display.jButton_lvl12.addActionListener(this);
        display.jButton_lvl13.addActionListener(this);
        display.jButton_lvl14.addActionListener(this);
        display.jButton_lvl15.addActionListener(this);
        display.jButton_lvl16.addActionListener(this);
        display.jButtonContinue.addActionListener(this);
        display.jPanelBG.setSize(Model.Board.WIDTH*Model.Board.DIMENSION,Model.Board.HEIGHT*Model.Board.DIMENSION);
        display.setLocationRelativeTo(null);
        display.setSize(1518,940);
        display.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(display.jButton_lvl1))
            try {
                board.generateLevel(1, "D:\\NetBeans\\Projects\\War Of Inheritance\\Levels\\Level_1.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LevelSelectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (e.getSource().equals(display.jButton_lvl2))
            try {
                board.generateLevel(2, "D:\\NetBeans\\Projects\\War Of Inheritance\\Levels\\Level_2.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LevelSelectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (e.getSource().equals(display.jButton_lvl3))
            try {
                board.generateLevel(3, "D:\\NetBeans\\Projects\\War Of Inheritance\\Levels\\Level_3.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LevelSelectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (e.getSource().equals(display.jButton_lvl4))
            try {
                board.generateLevel(4, "D:\\NetBeans\\Projects\\War Of Inheritance\\Levels\\Level_4.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LevelSelectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (e.getSource().equals(display.jButton_lvl5))
            try {
                board.generateLevel(5, "D:\\NetBeans\\Projects\\War Of Inheritance\\Levels\\Level_5.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LevelSelectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (e.getSource().equals(display.jButton_lvl6))
            try {
                board.generateLevel(6, "D:\\NetBeans\\Projects\\War Of Inheritance\\Levels\\Level_6.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LevelSelectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (e.getSource().equals(display.jButton_lvl7))
            try {
                board.generateLevel(7, "D:\\NetBeans\\Projects\\War Of Inheritance\\Levels\\Level_7.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LevelSelectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (e.getSource().equals(display.jButton_lvl8))
            try {
                board.generateLevel(8, "D:\\NetBeans\\Projects\\War Of Inheritance\\Levels\\Level_8.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LevelSelectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (e.getSource().equals(display.jButton_lvl9))
            try {
                board.generateLevel(9, "D:\\NetBeans\\Projects\\War Of Inheritance\\Levels\\Level_9.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LevelSelectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (e.getSource().equals(display.jButton_lvl10))
            try {
                board.generateLevel(10, "D:\\NetBeans\\Projects\\War Of Inheritance\\Levels\\Level_10.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LevelSelectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (e.getSource().equals(display.jButton_lvl11))
            try {
                board.generateLevel(11, "D:\\NetBeans\\Projects\\War Of Inheritance\\Levels\\Level_11.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LevelSelectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (e.getSource().equals(display.jButton_lvl12))
            try {
                board.generateLevel(12, "D:\\NetBeans\\Projects\\War Of Inheritance\\Levels\\Level_12.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LevelSelectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (e.getSource().equals(display.jButton_lvl13))
            try {
                board.generateLevel(13, "D:\\NetBeans\\Projects\\War Of Inheritance\\Levels\\Level_13.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LevelSelectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (e.getSource().equals(display.jButton_lvl14))
            try {
                board.generateLevel(14, "D:\\NetBeans\\Projects\\War Of Inheritance\\Levels\\Level_14.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LevelSelectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (e.getSource().equals(display.jButton_lvl5))
            try {
                board.generateLevel(15, "D:\\NetBeans\\Projects\\War Of Inheritance\\Levels\\Level_15.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LevelSelectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (e.getSource().equals(display.jButton_lvl16))
            try {
                board.generateLevel(16, "D:\\NetBeans\\Projects\\War Of Inheritance\\Levels\\Level_16.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LevelSelectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
         if (e.getSource().equals(display.jButtonContinue)){
            display.hide();
            ArmySelectionDisplay armySelectionDisplay = new ArmySelectionDisplay();
            ArmySelectionController armySelectionController = new ArmySelectionController(board, armySelectionDisplay);
        }
    } 
}
