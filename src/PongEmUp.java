import javax.swing.*;
import java.awt.*;

import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;


public class PongEmUp extends JFrame {
    public PongEmUp(){
        super("Pong'em up");
        setVisible(true);
        setResizable(false);
        PlayGround p=new PlayGround();
        addKeyListener(p);
        add(p,BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();


    }

}
