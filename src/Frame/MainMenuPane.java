package Frame;

import AltLib.ImageLoader;
import Entities.BackgroundObject;
import Entities.Entity;
import Game.AnimationHandler;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class MainMenuPane extends JPanel {

    private PongEmUp pongemup;
    private AnimationHandler model;

    public MainMenuPane(PongEmUp pongemup){
        this.pongemup=pongemup;

        model=new AnimationHandler(pongemup,this);
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

        JButton options=new JButton("Options");
        JButton quit=new JButton("Quit");
        newGame.addActionListener(e -> {pongemup.startGame();
        });
        options.addActionListener(e -> pongemup.goToOptions());
        quit.addActionListener(e -> pongemup.quit());



        menu.add(newGame,gbc);
        menu.add(options,gbc);
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

        g.drawImage(ImageLoader.bgImage[0], model.getOffsetX(), model.getOffsetY(), this);
        ArrayList<BackgroundObject> drawingList=new ArrayList<>(model.getBackgroundObjects());
        for (int i=0;i<drawingList.size();i++){
            BackgroundObject bgo=drawingList.get(i);
            bgo.drawEntity(g);
        }
    }


}
