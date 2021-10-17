package Frame.OptionsPane.Buttons;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class NextButton extends BasicButtonUI {

    @Override
    public void paint(Graphics g, JComponent c){
        g.setColor(Color.BLACK);
        g.fillPolygon(new int[] {5, 30, 5}, new int[] {5, 20, 35}, 3);
    }
}
