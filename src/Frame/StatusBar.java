package Frame;

import AltLib.ImageLoader;
import Entities.Bonus.Bonus;
import Game.Model;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class StatusBar extends JPanel {

    private Image healthPhoto;
    private Model model;

    public StatusBar(Model model){
        this.model = model;
        setBackground(new Color(211,211,211));
        setPreferredSize(new Dimension(Model.WIDTH,50));
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(ImageLoader.healthImage,10,10,29,30,this);
        g.setFont(new Font("Purisa", Font.BOLD, 13));
        g.drawString("X "+ model.getPlayerSpawnLeft(),45,30);
        g.drawString("Score: "+ model.getPlayerScore(),80,30);
        ArrayList<Bonus> bonuses=model.getBonuses();
        int x=150;
        for (int i=0;i<bonuses.size();i++){
            if (bonuses.get(i).acquired) {
                bonuses.get(i).drawInStatusBar(g, x, 5);
                x += 40;
            }
        }
    }

    public void update(){
        repaint();
    }




}
