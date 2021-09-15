import javax.swing.*;
import java.awt.*;

import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;


public class Pong extends JFrame {
    public Pong(){
        super("Pong Game");
        setVisible(true);
        setResizable(false);
        PlayGround p=new PlayGround();
        addKeyListener(p);
        add(p,BorderLayout.CENTER);


        pack();


    }

}
