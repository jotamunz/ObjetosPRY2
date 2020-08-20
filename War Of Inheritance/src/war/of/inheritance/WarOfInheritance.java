
package war.of.inheritance;

import Controller.LogInController;
import View.LogInDisplay;

public class WarOfInheritance {

    public static void main(String[] args) {
        
        LogInDisplay display= new LogInDisplay();
        LogInController controller = new LogInController(display);
        
    }

}
