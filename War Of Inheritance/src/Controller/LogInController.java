
package Controller;

import Model.Board;
import Model.User;
import View.LevelSelectionDisplay;
import View.LogInDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LogInController implements ActionListener{
    private LogInDisplay display;

    public LogInController (LogInDisplay display) {
        this.display = display;
        init();
    }
    
    private void init(){
        display.jButton_LogIn.addActionListener(this);
        display.jButton_SingUp.addActionListener(this);
        display.setLocationRelativeTo(null);
        display.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(display.jButton_LogIn) && !"".equals(display.textField_Username.getText()) && !"".equals(display.textField_Password.getText())){
            Board board = upload(display.textField_Username.getText());
            if (board != null){
                LevelSelectionDisplay levelSelectionDisplay = new LevelSelectionDisplay();
                LevelSelectionController controller = new LevelSelectionController(board, levelSelectionDisplay);
                display.hide();   
            }
            else
                System.out.println("File Not Found");
        }
        if (e.getSource().equals(display.jButton_SingUp)){
            User user = new User(display.textField_Username.getText(), display.textField_Password.getText());
            Board board = new Board(user); 
            board.saveGame();
            LevelSelectionDisplay levelSelectionDisplay = new LevelSelectionDisplay();
            LevelSelectionController controller = new LevelSelectionController(board, levelSelectionDisplay);
            display.hide(); 
        }
    }
    
    public Board upload(String filename){
        Board board;
        try
        {    
            FileInputStream file = new FileInputStream(filename); 
            ObjectInputStream in = new ObjectInputStream(file);      
            board = (Board)in.readObject(); 
            in.close(); 
            file.close(); 
            System.out.println("Object has been deserialized");  
            return board;
        } 
        catch(IOException ex) 
        { 
            System.out.println("Error Uploading File"); 
        } 
          
        catch(ClassNotFoundException ex) 
        { 
            System.out.println("Error Uploading File"); 
        } 
        return null;
    }
    
}
