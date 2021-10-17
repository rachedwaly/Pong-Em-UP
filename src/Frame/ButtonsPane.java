package Frame;

import javax.swing.*;
import java.awt.*;

public class ButtonsPane extends JPanel {
    private PongEmUp pongEmUp;
    private GridBagConstraints gbc;

    public ButtonsPane(PongEmUp pongEmUp){
        super(new GridBagLayout());
        JButton gameToMenu = new JButton("Pause");
        this.pongEmUp=pongEmUp;
        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(gameToMenu,gbc);
        gameToMenu.addActionListener(e2 -> { //Eventually, layeredPane transition
            pongEmUp.showPauseMenu();
        });

    }

    public void addRetryButton(){
        JButton retry=new JButton("retry");
        add(retry,gbc);
        retry.addActionListener(e2 -> { //Eventually, layeredPane transition
                pongEmUp.startGame();
        });
    }
}
