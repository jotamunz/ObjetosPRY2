
package Controller;

import Model.Board;
import View.GameDisplay;
import View.GameOverDisplay;
import View.LevelSelectionDisplay;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

public class GameController extends Thread{
    private Board board;
    private GameDisplay display;
    private GameOverDisplay gameOverDisplay;

    public GameController(Board board, GameDisplay display) throws FileNotFoundException {
        this.board = board;
        this.display = display;
        this.gameOverDisplay = new GameOverDisplay();
        init();
    }
    
    private void init() throws FileNotFoundException{
        display.jPanelGameBG.setSize(Model.Board.WIDTH*Model.Board.DIMENSION,Model.Board.HEIGHT*Model.Board.DIMENSION);
        for (int i = 0; i < Model.Board.WIDTH; i++){
            for (int j = 0; j < Model.Board.HEIGHT; j++){
                display.displayBoard[i][j] = new JLabel();
                display.displayBoard[i][j].setOpaque(true);
                display.displayBoard[i][j].setBackground(Color.WHITE);
                display.displayBoard[i][j].setText("");
                display.displayBoard[i][j].setBounds(Model.Board.DIMENSION*i, Model.Board.DIMENSION*j, Model.Board.DIMENSION, Model.Board.DIMENSION);
                display.displayBoard[i][j].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153))); 
                display.jPanelGameBG.add(display.displayBoard[i][j]);
            }
        }
        gameOverDisplay.setLocationRelativeTo(null);
        gameOverDisplay.setVisible(true);
        gameOverDisplay.hide();
        display.setLocationRelativeTo(null);
        display.setSize(1518,940);
        display.setVisible(true);
        board.placeArmy();
        this.start();
        board.startGame();
    }
    
    @Override
    public void run(){
        while(true && !board.isGameOver()){
            for (int i = 0; i < board.WIDTH; i++){
                for (int j = 0; j < board.HEIGHT; j++){
                    display.displayBoard[i][j].setIcon(board.logicalBoard[i][j].getCurrentImage());                   
                }
            }
            //board.printBoard();
            try {
                sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < board.WIDTH; i++){
            for (int j = 0; j < board.HEIGHT; j++){
                display.displayBoard[i][j].setIcon(board.logicalBoard[i][j].getCurrentImage());                   
            }
        }
        if (board.isVictory())
            gameOverDisplay.jLabel_Message.setText("VICTORY");
        else
            gameOverDisplay.jLabel_Message.setText("DEFEAT");
        gameOverDisplay.jLabel_Percent.setText("Score: " + board.destructionPercent() + "%");
        gameOverDisplay.show();
        board.gameOver();
        board.saveGame();
        try {
            sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        LevelSelectionDisplay levelSelectionDisplay = new LevelSelectionDisplay();
        LevelSelectionController controller = new LevelSelectionController(board, levelSelectionDisplay);
        display.hide(); 
        gameOverDisplay.hide();
    } 
}
