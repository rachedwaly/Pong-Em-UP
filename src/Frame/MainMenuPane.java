package Frame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;

public class MainMenuPane extends JPanel {

    private PongEmUp pongemup;

    public MainMenuPane(PongEmUp pongemup){
        this.pongemup=pongemup;


        setBorder(new EmptyBorder(50, 10, 10, 10));
        //50 10 10 10
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;
        JLabel label=new JLabel("<html><h1><strong><i>Pong'em UP " +
                "</i></strong></h1><hr></html>");


        add(label, gbc);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel menu = new JPanel(new GridBagLayout());


        JButton newGame=new JButton("new Game");
        JButton selectButton=new JButton("Level Select");
        JButton options=new JButton("Options");
        JButton quit=new JButton("Quit");
        newGame.addActionListener(e -> {
            try {
                pongemup.startGame();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        options.addActionListener(e -> pongemup.goToOptions());

        quit.addActionListener(e -> pongemup.quit());

        selectButton.addActionListener(e -> pongemup.goToLevelSelect());

        menu.add(newGame,gbc);
        menu.add(options,gbc);
        menu.add(selectButton,gbc);
        menu.add(quit,gbc);

        gbc.weighty=1;
        add(menu,gbc);


    }
}
