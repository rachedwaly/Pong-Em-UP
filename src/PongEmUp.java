import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;


public class PongEmUp extends JFrame {
    public final static String PLAYGROUND = "playground";
    public final static String MAINMENU = "mainmenu";
    public final static String OPTIONS = "options";

    private JPanel containerPane;
    private String currentScene;
    private PlayGround playground;
    private JPanel menu;
    private JPanel options;

    public PongEmUp(){

        super("Pong'em up");
        containerPane = new JPanel();
        add(containerPane);

        setPreferredSize(new Dimension(600,400));
        setVisible(true);
        setResizable(true);

        menu = new JPanel(new GridLayout(3,1));

        JButton newGame = new JButton("New Game");
        JButton quit = new JButton("Exit");
        JButton options = new JButton("options");

        menu.add(newGame);
        menu.add(options);
        menu.add(quit);

        containerPane.add(menu);

        newGame.addActionListener(e -> {        //Reset playground instead of making a new one ?
            playground = new PlayGround();
            containerPane.add(playground);
            playground.backToMenu.addActionListener(e2 -> { //Eventually, layeredPane transition
                playground.setVisible(false);
                menu.setVisible(true);
            });
            menu.setVisible(false);
        });

        quit.addActionListener(e -> System.exit(0));


        pack();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void initTransitionsButtons(){

    }

}
