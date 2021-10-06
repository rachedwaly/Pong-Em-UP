import javax.swing.*;
import java.awt.*;

public class ButtonsPane extends JPanel {
    private PongEmUp pongEmUp;
    public ButtonsPane(PongEmUp pongEmUp){
        super(new GridBagLayout());
        JButton gameToMenu = new JButton("Menu");
        this.pongEmUp=pongEmUp;
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(gameToMenu,gbc);
        gameToMenu.addActionListener(e2 -> { //Eventually, layeredPane transition
            pongEmUp.pauseGame();
        });

    }
}
