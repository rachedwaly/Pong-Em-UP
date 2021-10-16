package Frame;

import Game.Model;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class StatusBar extends JPanel {

    private Image healthPhoto;
    private Model model;

    public StatusBar(Model model) throws IOException {
        this.model = model;
        setBackground(new Color(211,211,211));
        setPreferredSize(new Dimension(Model.WIDTH,50));
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(model.getPhoto("health"),10,10,29,30,this);
        g.setFont(new Font("Purisa", Font.BOLD, 13));
        g.drawString("X "+ model.getPlayerSpawnLeft(),45,30);
        g.drawString("Score: "+ model.getPlayerScore(),100,30);
    }

    public void update(){
        repaint();
    }




}
