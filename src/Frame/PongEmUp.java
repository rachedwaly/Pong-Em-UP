package Frame;

import Game.GameModel;
import Game.Model;
import Game.PlayGround;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;


public class PongEmUp extends JFrame {
    public final static String PLAYGROUND = "playground";
    public final static String MAINMENU = "mainmenu";
    public final static String OPTIONS = "options";

    private boolean running=false;
    private boolean paused=false;
    private Model model;
    private JPanel containerPane;
    private PlayGround playground;
    private JPanel levelSelect;
    private JPanel optionsMenu;
    private PausePane pausePane;
    private MainMenuPane mainMenuPane;
    private ButtonsPane buttonsPanel;


    public PongEmUp() throws IOException {

        super("Pong'em up");

        pausePane= new PausePane(this);
        mainMenuPane=new MainMenuPane(this);
        this.setGlassPane(pausePane);
        containerPane = new JPanel(new BorderLayout());
        add(containerPane,BorderLayout.CENTER);
        containerPane.setBorder(new EmptyBorder(0,10,0,5));
        setMinimumSize(new Dimension(PlayGround.WIDTH+100,PlayGround.HEIGHT+100));
        setVisible(true);
        setResizable(false);

        showMainMenu();

        setVisible(true);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void initTransitionsButtons(){

    }

    public void pauseGame(){
        pausePane.setOpaque(false);
        pausePane.setVisible(true);
        model.stopTimer();
    }

    public void resumeGame(){
        containerPane.requestFocusInWindow();
        model.startTimer();
    }

    public void goToOptions(){
        optionsMenu = new JPanel();
        if (!running){
        containerPane.removeAll();

        JLabel placeholder = new JLabel("Placeholder");
        JSlider volume = new JSlider(0,100,50);
        JButton optionsToMain = new JButton("Menu");
        optionsMenu.add(placeholder);
        optionsMenu.add(volume);
        optionsMenu.add(optionsToMain);
        containerPane.add(optionsMenu);
        revalidate();
        repaint();}
        else {
            containerPane.removeAll();
            JLabel placeholder = new JLabel("Placeholder");
            JSlider volume = new JSlider(0,100,50);
            JButton optionsToMain = new JButton("Menu");
            optionsMenu.add(placeholder);
            optionsMenu.add(volume);
            optionsMenu.add(optionsToMain);
            containerPane.add(optionsMenu);
            revalidate();
            repaint();

        }


    }

    public void goToLevelSelect(){
        containerPane.remove(mainMenuPane);
        levelSelect = new JPanel(new GridLayout(1,3));
        JButton lvl1 = new JButton("1");
        JButton lvl2 = new JButton("2");
        lvl2.setEnabled(false);                 //TODO Define clear conditions to enable them
        JButton lvl3 = new JButton("3");
        lvl2.setEnabled(false);
        levelSelect.add(lvl1);
        containerPane.add(levelSelect);
        revalidate();
        repaint();


    }

    public void goBackToMainMenu(){


        stopTheGame();
        showMainMenu();

    }

    public void showMainMenu(){

        containerPane.add(mainMenuPane);
        revalidate();
        repaint();
    }

    public void quit() {
        System.exit(0);
    }

    public void startGame() throws IOException {
        running=true;
        //put this inside a gamePane class maybe?
        containerPane.removeAll();
        model = new GameModel(this);
        playground = model.getView();
        containerPane.add(playground,BorderLayout.CENTER);
        containerPane.addKeyListener(model);
        containerPane.requestFocusInWindow();
        buttonsPanel=new ButtonsPane(this);
        containerPane.add(buttonsPanel,BorderLayout.EAST);
        revalidate();
        repaint();
    }

    public void stopTheGame(){
        if (running){
            containerPane.removeAll();
            buttonsPanel.removeAll();
            playground=null;
            buttonsPanel=null;
            model.stopTimer();
            model =null;
        }
    }

    public void gameOver(){
        buttonsPanel.addRetryButton();
        buttonsPanel.revalidate();
        buttonsPanel.repaint();
    }


}
