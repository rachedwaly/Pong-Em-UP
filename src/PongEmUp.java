import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;


public class PongEmUp extends JFrame {
    public final static String PLAYGROUND = "playground";
    public final static String MAINMENU = "mainmenu";
    public final static String OPTIONS = "options";

    private Model model;
    private JPanel containerPane;
    private String currentScene;
    private PlayGround playground;
    private JPanel menu;
    private JPanel levelSelect;
    private JPanel optionsMenu;

    public PongEmUp(){

        super("Pong'em up");
        containerPane = new JPanel();
        add(containerPane);
        containerPane.setFocusable(true);

        setPreferredSize(new Dimension(PlayGround.WIDTH,PlayGround.HEIGHT));
        setVisible(true);
        setResizable(true);

        ///// MAIN MENU /////
        menu = new JPanel(new GridLayout(0,1));

        JLabel title = new JLabel("Pong Em' Up");
        JButton newGame = new JButton("New Game");
        JButton selectButton = new JButton("Level Select");
        selectButton.setEnabled(false);                 //TODO Enable condition ?
        JButton options = new JButton("Options");
        JButton quit = new JButton("Exit");

        menu.add(title);
        menu.add(newGame);
        menu.add(selectButton);
        menu.add(options);
        menu.add(quit);
        containerPane.add(menu);

        ///// LEVEL SELECTION /////
        levelSelect = new JPanel(new GridLayout(1,3));
        JButton lvl1 = new JButton("1");
        JButton lvl2 = new JButton("2");
        lvl2.setEnabled(false);                 //TODO Define clear conditions to enable them
        JButton lvl3 = new JButton("3");
        lvl2.setEnabled(false);
        containerPane.add(levelSelect);

        ///// OPTIONS MENU /////
        optionsMenu = new JPanel();
        JLabel placeholder = new JLabel("Placeholder");
        JSlider volume = new JSlider(0,100,50);
        JButton optionsToMain = new JButton("Menu");
        optionsMenu.add(placeholder);
        optionsMenu.add(volume);
        optionsMenu.add(optionsToMain);
        containerPane.add(optionsMenu);
        optionsMenu.setVisible(false);

        newGame.addActionListener(e -> {        //TODO Reset playground instead of making a new one ?
            playground = new PlayGround();
            containerPane.add(playground);
            model= new Model(playground);
            containerPane.addKeyListener(model);
            playground.gameToMenu.addActionListener(e2 -> { //Eventually, layeredPane transition
                playground.setVisible(false);
                menu.setVisible(true);
            });
            menu.setVisible(false);
        });

        options.addActionListener(e -> {
            menu.setVisible(false);
            optionsMenu.setVisible(true);
        });

        optionsToMain.addActionListener(e -> {
            menu.setVisible(true);
            optionsMenu.setVisible(false);
        });
        quit.addActionListener(e -> System.exit(0));


        pack();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void initTransitionsButtons(){

    }

}
