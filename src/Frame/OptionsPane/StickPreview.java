package Frame.OptionsPane;

import AltLib.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Math.abs;

public class StickPreview extends JPanel {
    private BufferedImage photo;

    public StickPreview(int i){
        setPreferredSize(new Dimension(120,40));
        setPhoto(i);
    }

    @Override
    public void paintComponent(Graphics g){
        g.drawImage(photo,10,10,100,20,this);
    }

    public void setPhoto(int i){
        photo= ImageLoader.stickImage[abs((i)% ImageLoader.stickImage.length)];
    }
}
