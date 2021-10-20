package Frame;

import Frame.OptionsPane.OptionsPane;
import Game.GameModel;
import Game.Model;
import Game.PlayGround;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


public class PongEmUp extends JFrame {
    public final static String PLAYGROUND = "playground";
    public final static String MAINMENU = "mainmenu";
    public final static String OPTIONS = "options";

    private boolean running=false;
    private boolean paused=false;
    private Model model;
    private JPanel containerPane;
    private PlayGround playground;

    private JPanel optionsPane;
    private PausePane pausePane;
    private MainMenuPane mainMenuPane;
    private ButtonsPane buttonsPanel;


    public PongEmUp(){

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



    public void showPauseMenu(){
        pausePane.setOpaque(false);
        pausePane.setVisible(true);
        model.stopTimer();
        paused=true;
    }

    public void resumeGame(){
        containerPane.requestFocusInWindow();
        model.startTimer();
    }

    public void goToOptions(){
        containerPane.removeAll();
        optionsPane = new OptionsPane(this);
        containerPane.add(optionsPane);
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

    public void startGame() {
        running=true;
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
            running=false;
        }

    }

    public void gameOver(){
        buttonsPanel.addRetryButton();
        buttonsPanel.revalidate();
        buttonsPanel.repaint();
    }

    public void exitOptionsMenu(){
        if (paused){
            containerPane.removeAll();
            containerPane.add(playground,BorderLayout.CENTER);
            containerPane.add(buttonsPanel,BorderLayout.EAST);
            showPauseMenu();
        }
        if (!running){
            containerPane.removeAll();
            showMainMenu();
        }
    }


    public void restartGame() {

    }
}
