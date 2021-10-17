package Frame.OptionsPane;

import Frame.*;
import Game.Model;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class OptionsPane extends JPanel {
    private PongEmUp pongEmUp;


    public OptionsPane(PongEmUp pongEmUp){
        this.pongEmUp=pongEmUp;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;
        JLabel titleLabel=new JLabel("<html><strong><i> Customization Menu " +
                "</i></strong><hr></html>");
        titleLabel.setFont(new Font("Verdana", Font.PLAIN, 30));

        add(titleLabel,gbc);
        titleLabel.setBorder(new EmptyBorder(0,0,200,0));


        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel stickLabel=new JLabel("<html><strong><i>Select the looks of your stick " +
                "</i></strong></html>",SwingConstants.CENTER);
        stickLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        add(stickLabel,gbc);
        StickDrawingArea stickDrawingArea=new StickDrawingArea();
        add(stickDrawingArea,gbc);

        JLabel ballLabel=new JLabel("<html><strong><i>Select the color of your ball " +
                "</i></strong></html>",SwingConstants.CENTER);

        ballLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        add(ballLabel,gbc);
        BallDrawingArea ballDrawingArea=new BallDrawingArea();
        add(ballDrawingArea,gbc);

        gbc.ipadx = 10;
        JButton setButton=new JButton("SET");
        setButton.setPreferredSize(new Dimension(50,50));
        add(setButton,gbc);

        setButton.addActionListener(e -> {
            Model.ballColor=ballDrawingArea.getIndex();
            Model.stickPhoto=stickDrawingArea.getIndex();
        });

        JButton backButton=new JButton("BACK");
        setButton.setPreferredSize(new Dimension(50,50));
        add(backButton,gbc);

        backButton.addActionListener(e -> {
            pongEmUp.exitOptionsMenu();
        });






    }
}
