package Controller;

import Model.Board;
import Model.TokenFactory;
import Model.TokenType;
import View.ArmySelectionDisplay;
import View.ConfigDisplay;
import View.GameDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ArmySelectionController implements ActionListener{
    private Board board;
    private ArmySelectionDisplay display;
    private ConfigDisplay config;

    public ArmySelectionController(Board board, ArmySelectionDisplay display) {
        this.board = board;
        this.display = display;
        this.config = new ConfigDisplay();
        init();
    }
    
    private void init(){
        for (int i = 0; i < board.getCustomWarriors().size(); i++){
            display.jComboBox_CostumWarriors.addItem(board.getCustomWarriors().get(i).getTokenName());
        }
        config.jButton_Exit.addActionListener(this);
        config.jButton_Save.addActionListener(this);
        config.setLocationRelativeTo(null);
        config.setVisible(true);
        config.hide();
        display.jButton1.addActionListener(this);
        display.jButton2.addActionListener(this);
        display.jButton3.addActionListener(this);
        display.jButton4.addActionListener(this);
        display.jButton5.addActionListener(this);
        display.jButton_Config.addActionListener(this);
        display.jButtonStart.addActionListener(this);
        display.jButton_AddCostum.addActionListener(this);
        display.jPanelBG.setSize(Model.Board.WIDTH*Model.Board.DIMENSION,Model.Board.HEIGHT*Model.Board.DIMENSION);
        display.jLabelArmySpace.setText("Space available: " + board.getArmySpace());
        display.jLabel_Gold.setText("Gold: " + board.getUser().getGoldAmount());
        display.jLabel_Level.setText("Level: " + board.getUser().getLevel());
        display.setLocationRelativeTo(null);
        display.setSize(1518,940);
        display.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(display.jButton1))
            board.addToArmy(TokenFactory.createToken("Barbarian", TokenType.ContactWarrior, board.CONTACTWARRIOR_H, board.CONTACTWARRIOR_D, board.CONTACTWARRIOR_R, board.CONTACTWARRIOR_S, board.CONTACTWARRIOR_U, board.getUser().getLevel(), board.CONTACTWARRIOR_C, board, board.CONTACTWARRIOR_I));
        if (e.getSource().equals(display.jButton2))
            board.addToArmy(TokenFactory.createToken("Archer", TokenType.RangedWarrior, board.RANGEDWARRIOR_H, board.RANGEDWARRIOR_D, board.RANGEDWARRIOR_R, board.RANGEDWARRIOR_S, board.RANGEDWARRIOR_U, board.getUser().getLevel(), board.RANGEDWARRIOR_C, board, board.RANGEDWARRIOR_I));
        if (e.getSource().equals(display.jButton3))
            board.addToArmy(TokenFactory.createToken("Dragon", TokenType.AerialWarrior, board.AERIALWARRIOR_H, board.AERIALWARRIOR_D, board.AERIALWARRIOR_R, board.AERIALWARRIOR_S, board.AERIALWARRIOR_U, board.getUser().getLevel(), board.AERIALWARRIOR_C, board, board.AERIALWARRIOR_I));
        if (e.getSource().equals(display.jButton4))
            board.addToArmy(TokenFactory.createToken("Beast", TokenType.Beast, board.BEAST_H, board.BEAST_D, board.BEAST_R, board.BEAST_S, board.BEAST_U, board.getUser().getLevel(), board.BEAST_C, board, board.BEAST_I));
        if (e.getSource().equals(display.jButton5))
            board.addToArmy(TokenFactory.createToken("Hero", TokenType.Hero, board.HERO_H, board.HERO_D, board.HERO_R, board.HERO_S, board.HERO_U, board.getUser().getLevel(), board.HERO_C, board, board.HERO_I));
        if (e.getSource().equals(display.jButtonStart)){
            display.hide();
            GameDisplay gameDisplay = new GameDisplay();
            try {
                GameController gameController = new GameController(board, gameDisplay);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ArmySelectionController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (e.getSource().equals(display.jButton_Config)){
            if ("Admin".equals(board.getUser().getUsername()))
                config.show();
            else
                System.out.println(board.getUser().getUsername() + " Incorrect Credentials");
        }
        if (e.getSource().equals(config.jButton_Save)){
            TokenType allTypes[] = {TokenType.ContactWarrior, TokenType.RangedWarrior, TokenType.AerialWarrior, TokenType.Beast, TokenType.Hero};
            String name = config.jTextField_Name.getText();
            TokenType type = allTypes[config.jComboBox_Type.getSelectedIndex()];
            int health = (int) config.jSpinner_Health.getValue();
            int damage = (int) config.jSpinner_Damage.getValue();
            int range = (int) config.jSpinner_Range.getValue();
            int space = (int) config.jSpinner_Space.getValue();
            int unlock = (int) config.jSpinner_Unlock.getValue();
            int cost = (int) config.jSpinner_Cost.getValue();
            String imageMove = config.jTextField_ImageMovement.getText();
            String imageAtack = config.jTextField_ImageAtack.getText();
            ArrayList<String> images = new ArrayList<>(Arrays.asList(imageMove, imageAtack));
            board.addCustomWarrior(TokenFactory.createToken(name, type, health, damage, range, space, unlock, board.getUser().getLevel(), cost, board, images));
            display.jComboBox_CostumWarriors.addItem(name);
            config.hide();
        }
        if (e.getSource().equals(config.jButton_Save)){
            config.hide();
        } 
        if (e.getSource().equals(display.jButton_AddCostum)){
            int index = (int) config.jComboBox_Type.getSelectedIndex();
            if (index >= 0)
                board.addToArmy(board.getCustomWarriors().get(index));
        }
        display.jLabelArmySpace.setText("Space available: " + board.getArmySpace());
        display.jLabel_Gold.setText("Gold: " + board.getUser().getGoldAmount());
    }
    
    
}