package Frame;

import Entities.BackGroundObject;
import Entities.Entity;
import Game.AnimationModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class MainMenuPane extends JPanel {

    private PongEmUp pongemup;
    private AnimationModel model;

    public MainMenuPane(PongEmUp pongemup) throws IOException {
        this.pongemup=pongemup;

        model=new AnimationModel(pongemup,this);
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
        menu.setOpaque(false);

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

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        handleBackground(g);
        ArrayList<Entity> drawingList=new ArrayList<>(model.getDrawables());
        //on doit impérativement utiliser ce type de boucle four sinon on peut pas mettre à jour
        // la liste  des drawables dynamiquement (sinon ça throw une exception)
        for (int i=0;i<drawingList.size();i++){
            Entity entity=drawingList.get(i);
            entity.drawEntity(g);
        }


    }

    private void handleBackground(Graphics g) {

        g.drawImage(model.getPhoto("lvl1"), model.getOffsetX(), model.getOffsetY(), this);
        ArrayList<BackGroundObject> drawingList=new ArrayList<>(model.getBackgroundObjects());
        for (int i=0;i<drawingList.size();i++){
            BackGroundObject bgo=drawingList.get(i);
            bgo.drawEntity(g);
        }
    }


}
