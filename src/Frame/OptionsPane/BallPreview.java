package Frame.OptionsPane;

import AltLib.ImageLoader;

import javax.swing.*;
import java.awt.*;

import static java.lang.Math.abs;

public class BallPreview extends JPanel {
    public static Color[] colors={Color.black,Color.yellow,Color.PINK,Color.orange};
    private Color color;


    public BallPreview(int i){
        setPreferredSize(new Dimension(120,40));
        setColor(i);
    }

    @Override
    public void paintComponent(Graphics g){
        g.setColor(color);
        g.fillOval(50,10,20,20);
    }

    public void setColor(int i){
        color=colors[abs(i%colors.length)];
    }
}
