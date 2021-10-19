package Frame.OptionsPane.Buttons;

import javax.swing.*;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class PreviousButton extends BasicButtonUI {
    @Override
    public void paint(Graphics g, JComponent c){
        g.setColor(Color.BLACK);
        g.fillPolygon(new int[] {30, 5, 30}, new int[] {5, 20, 35}, 3);
    }
}
